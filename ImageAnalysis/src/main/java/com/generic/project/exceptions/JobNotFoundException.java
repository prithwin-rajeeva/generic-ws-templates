package com.generic.project.exceptions;

/**
 * thrown when an job was not found in the job registry
 */
public class JobNotFoundException extends Exception {
    private ErrorMessage errorMessage;
    public JobNotFoundException(String message) {
        super(message);
        this.errorMessage = new ErrorMessage("0006" , message);
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
