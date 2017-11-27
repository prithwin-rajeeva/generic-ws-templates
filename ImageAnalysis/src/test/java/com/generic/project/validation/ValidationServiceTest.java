package com.generic.project.validation;

import com.generic.project.test.util.TestUtil;
import com.generic.project.vo.Job;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * validates the job validation service.
 */
public class ValidationServiceTest {
    @Test
    public void validate() throws Exception {
        ValidationService val = new ValidationService();
        TestUtil.setValue(val , 3, "cap");
        Job job = new Job();
        job.setUrls(Arrays.asList("http://abc.com",
                "http://abc.com","http://abc.com"));
        assertEquals(val.validate(job).size(),0);
    }

    @Test
    public void validateInvalidUrl() throws Exception {
        ValidationService val = new ValidationService();
        TestUtil.setValue(val , 4, "cap");
        Job job = new Job();
        job.setUrls(Arrays.asList("a","b" ,"c" ,"d"));
        assertEquals(val.validate(job).size(),4);
    }


    @Test
    public void validateExceedsCapacity() throws Exception {
        ValidationService val = new ValidationService();
        TestUtil.setValue(val , 2, "cap");
        Job job = new Job();
        job.setUrls(Arrays.asList("a","b" ,"c" ,"d"));
        assertEquals(val.validate(job).size(),1);
    }


    @Test
    public void validateNoJobs() throws Exception {
        ValidationService val = new ValidationService();
        TestUtil.setValue(val , 2, "cap");
        Job job = new Job();
        job.setUrls(Arrays.asList());
        assertEquals(val.validate(job).size(),1);
    }

}