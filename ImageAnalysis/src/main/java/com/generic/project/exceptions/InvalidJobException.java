package com.generic.project.exceptions;

import java.util.List;

/**
 * Represents and malformed job exception
 */
public class InvalidJobException extends Exception {

    private final List<ErrorMessage> messages;

    public InvalidJobException(List<ErrorMessage> messages) {
        this.messages = messages;
    }

    public List<ErrorMessage> getMessages() {
        return messages;
    }
}
