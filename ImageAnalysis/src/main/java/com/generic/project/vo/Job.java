package com.generic.project.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Java representation of a Job resources to be exchanged with client.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Job {

    private String jobId;
    private List<String> urls;
    private JobStatus status;
    private List<URLReport> urlReports;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public List<URLReport> getUrlReports() {
        return urlReports;
    }

    public void setUrlReports(List<URLReport> urlReports) {
        this.urlReports = urlReports;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId='" + jobId + '\'' +
                ", urls=" + urls +
                ", status=" + status +
                ", urlReports=" + urlReports +
                '}';
    }


}
