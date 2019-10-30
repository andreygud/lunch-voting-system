package com.gudilov.lunchvotingsystem.exceptions;

public class BusinessRuleViolationException extends RuntimeException  {
    public BusinessRuleViolationException(String message) {
        super(message);
    }
}
