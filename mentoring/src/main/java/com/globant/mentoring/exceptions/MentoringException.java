package com.globant.mentoring.exceptions;

public class MentoringException extends Exception {

    public MentoringException(final String message) {
        super(message);
    }
    public MentoringException(final String message, Throwable cause) {
        super(message, cause);
    }
}
