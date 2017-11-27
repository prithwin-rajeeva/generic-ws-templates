package com.generic.project.service;

import com.generic.project.exceptions.JobNotFoundException;
import com.generic.project.vo.Job;

/**
 * This is the Job processing service interface.
 */
public interface JobProcessingService {
    /**
     * fires a job processing job
     */
    String process(Job job);

    /**
     * gets the job status
     * @param jobId the job identifier.
     * @return gets a job from the job status
     */
    Job getJobStatus(String jobId) throws JobNotFoundException;
}
