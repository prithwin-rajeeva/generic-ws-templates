package com.generic.project.controller;

import com.generic.project.exceptions.ErrorMessage;
import com.generic.project.exceptions.InvalidJobException;
import com.generic.project.exceptions.JobNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base resource for all image api controllers.
 */
public class ResourceBase {

    private static Logger logger = LoggerFactory.getLogger(ResourceBase.class);

    private static final String MESSAGE = "message";
    private static final String REASON = "reason_";

    /**
     * Wraps {@link JobNotFoundException} in {@link HttpStatus}
     * @param ex {@link JobNotFoundException}
     * @return client error
     */
    @ExceptionHandler(JobNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView asModelAndView(JobNotFoundException ex) {
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        Map<String , List<ErrorMessage>> errorMap = new HashMap<>();
        errorMap.put(MESSAGE, Arrays.asList(ex.getErrorMessage()));
        logger.debug("sending {} errors to client", errorMap);
        return new ModelAndView(jsonView,errorMap);
    }

    /**
     * Wraps {@link InvalidJobException} in {@link HttpStatus}
     * @param ex {@link InvalidJobException}
     * @return client error
     */
    @ExceptionHandler(InvalidJobException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView asModelAndView(InvalidJobException ex) {
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        Map<String , List<ErrorMessage>> errorMap = new HashMap<>();
        errorMap.put(MESSAGE , ex.getMessages());
        logger.debug("sending {} errors to client", errorMap);
        return new ModelAndView(jsonView,errorMap);
    }
}
