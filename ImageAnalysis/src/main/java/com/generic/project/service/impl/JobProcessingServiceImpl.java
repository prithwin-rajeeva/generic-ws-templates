package com.generic.project.service.impl;

import com.generic.project.dal.DataAccess;
import com.generic.project.exceptions.JobNotFoundException;
import com.generic.project.service.JobProcessingService;
import com.generic.project.vo.Job;
import com.generic.project.vo.JobStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This is the Job processing service interface.
 */
@Service
public class JobProcessingServiceImpl implements JobProcessingService {

    private static Logger logger = LoggerFactory.getLogger(JobProcessingServiceImpl.class);

    private Map<String,Job> jobRegistry = new ConcurrentHashMap<>();

    @Resource
    private ExecutorService executor;

    @Resource
    private DataAccess dao;

    @Value("${job.level.parallelism}")
    private boolean para;

    /**
     * fires a job process on a set of urls
     * @param job the client job
     * @return the Job id of the server resource.
     */
    @Override
    public String process(Job job) {
        String jobId = UUID.randomUUID().toString();
        logger.debug("generated unique id {} for the job" , jobId);
        Job response = new Job();
        response.setJobId(jobId);
        response.setStatus(JobStatus.SUBMITTED);
        response.setUrls(job.getUrls());
        executor.execute(() -> parallelFetch(job.getUrls(),jobId));
        jobRegistry.put(jobId , response);
        return jobId;
    }

    @Override
    public Job getJobStatus(String jobId) throws JobNotFoundException {
        if(!jobRegistry.containsKey(jobId)) {
            logger.debug("{} job id not found in the job registry" , jobId);
            throw new JobNotFoundException("the job was not found in the job registry");
        }
        logger.debug("{} jobId found", jobId);
        return jobRegistry.get(jobId);
    }

    /**
     * parallel processes all the URLs that are part of the requests and updates job status in the job store
     * @param urls the urls to process
     * @param jobId the job id that comprises of the above request.
     */
    private void parallelFetch(List<String> urls,String jobId) {
        jobRegistry.get(jobId).setStatus(JobStatus.RUNNING);
        jobRegistry.get(jobId).setUrlReports(
                getUrlStream(urls)
                .map(url -> dao.getURLReport(url))
                .collect(Collectors.toList()));
        jobRegistry.get(jobId).setStatus(JobStatus.COMPLETE);
    }

    /**
     * gets a URL stream based upon job configuration.
     * @param urls the list of urls to process
     * @return a {@link Stream} or urls
     */
    private Stream<String> getUrlStream(List<String> urls) {
        if(para) return urls.parallelStream();
        return urls.stream();
    }

}
