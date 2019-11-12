package com.gudilov.lunchvotingsystem.common.exceptions;

public class BusinessRuleViolationException extends RuntimeException  {
    public BusinessRuleViolationException(String message) {
        super(message);
    }
}
