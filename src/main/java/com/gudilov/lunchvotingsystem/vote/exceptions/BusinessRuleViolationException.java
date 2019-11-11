package com.gudilov.lunchvotingsystem.vote.exceptions;

public class BusinessRuleViolationException extends RuntimeException  {
    public BusinessRuleViolationException(String message) {
        super(message);
    }
}
