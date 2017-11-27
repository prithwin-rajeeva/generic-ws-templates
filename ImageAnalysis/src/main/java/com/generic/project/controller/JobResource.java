package com.generic.project.controller;

import com.generic.project.exceptions.InvalidJobException;
import com.generic.project.exceptions.JobNotFoundException;
import com.generic.project.service.JobProcessingService;
import com.generic.project.vo.Job;
import com.generic.project.exceptions.ErrorMessage;
import com.generic.project.validation.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Controller for the Job resource, allow you to
 * 1. create a job
 * 2. check on the status of the job.
 */
@RestController
@RequestMapping("${job.base.mapping}")
public class JobResource extends ResourceBase {

    private static Logger logger = LoggerFactory.getLogger(JobResource.class);

    @Resource
    private JobProcessingService jobProcessingService;

    @Resource
    private ValidationService validationService;

    /**
     * Asynchronously creates a job and return a job identifier for the registered job
     * @param job the client job resource
     * @return Server side job resource
     * @throws InvalidJobException in case the job doesn't pass basic sanity checks, see
     * {@link ResourceBase#asModelAndView(InvalidJobException)}
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE ,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public @ResponseBody
    Job addJob(@RequestBody Job job) throws InvalidJobException {
        logger.info("creating job of size {}",job.getUrls().size());
        List<ErrorMessage> vMessages = validationService.validate(job);

        if(!vMessages.isEmpty()) {
            logger.debug("could not process job because of validation errors");
            throw new InvalidJobException(vMessages);
        }
        String jobId = jobProcessingService.process(job);
        Job response = new Job();
        response.setJobId(jobId);
        logger.debug("successfully created job with id {}",jobId);
        return response;
    }

    /**
     * fetches the latest state of the job resource.
     * @param jobId the global job identifier.
     * @return the server side job resource.
     * @throws JobNotFoundException in case the job doesn't exist on the server, See
     * {@link ResourceBase#asModelAndView(JobNotFoundException)}
     */
    @RequestMapping(value = "/{jobId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Job getJob(@PathVariable("jobId") String jobId) throws JobNotFoundException {
        logger.info("finding job with id {}", jobId);
        return jobProcessingService.getJobStatus(jobId);
    }

}
