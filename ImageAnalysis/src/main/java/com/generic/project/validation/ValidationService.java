package com.generic.project.validation;

import com.generic.project.vo.Job;
import com.generic.project.vo.JobStatus;
import com.generic.project.exceptions.ErrorMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Service to perform common validations
 */
@Service
public class ValidationService {

    @Value("${job.size.cap}")
    private int cap;

    public List<ErrorMessage> validate(Job job) {
        List<ErrorMessage> vMessages = new ArrayList<>();
        if(job == null) return Arrays.asList(new ErrorMessage("0001" , "null job provided"));

        if(job.getStatus()!=null && job.getStatus() != JobStatus.SUBMITTED) vMessages.add(
                new ErrorMessage("0002" ,"Job code can initially be" +
                " empty or have a status of SUBMITTED"));

        if(job.getUrls()==null || job.getUrls().size() < 1) {
            vMessages.add(new ErrorMessage("0003" ,"you must supply at least one URL to " +
                    "submit a job for processing"));
        } else if(job.getUrls().size()>cap) {
            vMessages.add(new ErrorMessage("0004" ,String.format("system admin has capped the number of " +
                    "requests to %d please reduce the job" +
                    " size or contact the system administrator",cap)));
        } else {
            job.getUrls().forEach(url -> {
                try {
                    URL p = new URL(url);
                } catch (MalformedURLException e) {
                   vMessages.add(new ErrorMessage("0005" ,String.format("the url %s is malformed",url)));
                }
            });
        }
        return vMessages;
    }
}
