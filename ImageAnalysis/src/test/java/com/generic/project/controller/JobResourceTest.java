package com.generic.project.controller;

import com.generic.project.exceptions.ErrorMessage;
import com.generic.project.exceptions.InvalidJobException;
import com.generic.project.exceptions.JobNotFoundException;
import com.generic.project.service.JobProcessingService;
import com.generic.project.test.util.TestUtil;
import com.generic.project.validation.ValidationService;
import com.generic.project.vo.Job;
import org.junit.Test;

import java.util.Arrays;

import static org.easymock.EasyMock.*;

import static org.junit.Assert.*;

/**
 * tests the Job rest resrouce
 */
public class JobResourceTest {
    @Test
    public void testAddJob() throws Exception {
        JobResource jobResource = new JobResource();
        TestUtil.setValue(jobResource , getMockedValidationService(), "validationService");
        TestUtil.setValue(jobResource , getMockedJobProcessor(), "jobProcessingService");
        Job job = new Job();
        job.setUrls(Arrays.asList("abcdefghi" ,
                "jklm", "nopqrstuvwx" ,"y","z","uevoli"));
        Job serverJob = jobResource.addJob(job);
        assertEquals("G_U_I_D" , serverJob.getJobId());
    }

    @Test(expected = InvalidJobException.class)
    public void testAddJobWithFaultyJob() throws Exception {
        JobResource jobResource = new JobResource();
        TestUtil.setValue(jobResource , getMockedValidationServiceWithException(), "validationService");
        TestUtil.setValue(jobResource , getMockedJobProcessor(), "jobProcessingService");
        Job job = new Job();
        job.setUrls(Arrays.asList());
        jobResource.addJob(job);
    }

    @Test
    public void getJob() throws Exception {
        JobResource jobResource = new JobResource();
        TestUtil.setValue(jobResource , getMockedValidationServiceWithException(), "validationService");
        TestUtil.setValue(jobResource , getMockedJobProcessor(), "jobProcessingService");
        assertEquals(jobResource.getJob("abcd").getJobId() , "abcd");

    }


    @Test(expected = JobNotFoundException.class)
    public void getJobNonExistent() throws Exception {
        JobResource jobResource = new JobResource();
        TestUtil.setValue(jobResource , getMockedValidationServiceWithException(), "validationService");
        TestUtil.setValue(jobResource , getMockedJobProcessor(), "jobProcessingService");
        jobResource.getJob("1234");

    }

    private JobProcessingService getMockedJobProcessor() throws Exception{
        JobProcessingService response =  createMock(JobProcessingService.class);
        expect(response.process(anyObject())).andReturn("G_U_I_D").once();
        Job validJob = new Job();
        validJob.setJobId("abcd");
        expect(response.getJobStatus("abcd")).andReturn(validJob).once();
        expect(response.getJobStatus("1234")).
                andThrow(new JobNotFoundException("message"));
        replay(response);
        return response;
    }

    private ValidationService getMockedValidationService() {
        ValidationService response =  createMock(ValidationService.class);
        expect(response.validate(anyObject())).andReturn(Arrays.asList()).once();
        replay(response);
        return response;
    }

    private ValidationService getMockedValidationServiceWithException() {
        ValidationService response =  createMock(ValidationService.class);
        expect(response.validate(anyObject())).andReturn(Arrays.asList(new ErrorMessage("001","a")
                , new ErrorMessage("002","a"))).once();
        replay(response);
        return response;
    }

}