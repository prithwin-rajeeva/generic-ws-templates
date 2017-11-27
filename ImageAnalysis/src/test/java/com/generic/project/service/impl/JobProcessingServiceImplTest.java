package com.generic.project.service.impl;

import static org.easymock.EasyMock.*;

import com.generic.project.test.util.TestUtil;
import com.generic.project.vo.Job;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import static org.junit.Assert.*;

/**
 * Test cases for Executor service for
 */
public class JobProcessingServiceImplTest {
    /**
     * test for create job
     */
    @Test
    public void process() throws Exception {
        JobProcessingServiceImpl service = new JobProcessingServiceImpl();
        TestUtil.setValue(service,createMock(ExecutorService.class) ,
                "executor");
        Map<String, Job> reg = new HashMap<>();
        TestUtil.setValue(service, reg , "jobRegistry");
        Job job1 = new Job();
        job1.setUrls(Arrays.asList("http://google.com","https://sofasogood.com","http://13floors.com"));
        service.process(job1);
        assertTrue(reg.size()==1);
    }

    /**
     * test for find existing job
     */
    @Test
    public void getJobStatus() throws Exception {
        JobProcessingServiceImpl service = new JobProcessingServiceImpl();
        TestUtil.setValue(service,createMock(ExecutorService.class) ,
                "executor");
        Map<String, Job> reg = new HashMap<>();
        TestUtil.setValue(service, reg , "jobRegistry");
        Job job1 = new Job();
        job1.setUrls(Arrays.asList("http://google.com","https://sofasogood.com","http://13floors.com"));
        String jobIdAdded = service.process(job1);
        assertEquals(jobIdAdded , service.getJobStatus(jobIdAdded).getJobId());
        assertEquals(job1.getUrls() , service.getJobStatus(jobIdAdded).getUrls());
    }

    /**
     * test for job execution.
     */
    @Test
    public void testExecution() throws Exception {
        JobProcessingServiceImpl service = new JobProcessingServiceImpl();
        ExecutorService executorService = createMock(ExecutorService.class);
        executorService.execute(anyObject());
        expectLastCall().once();
        replay(executorService);
        TestUtil.setValue(service, executorService,
                "executor");
        Map<String, Job> reg = new HashMap<>();
        TestUtil.setValue(service, reg , "jobRegistry");
        Job job1 = new Job();
        job1.setUrls(Arrays.asList("http://google.com","https://sofasogood.com","http://13floors.com"));
        String jobIdAdded = service.process(job1);
        assertEquals(jobIdAdded , service.getJobStatus(jobIdAdded).getJobId());
        assertEquals(job1.getUrls() , service.getJobStatus(jobIdAdded).getUrls());
        verify(executorService);

    }
}