package com.example.assignment.EventStrat.exception;

public class MissingMandatoryFieldException extends RuntimeException {
    public MissingMandatoryFieldException(String message) {
        super(message);
    }
}