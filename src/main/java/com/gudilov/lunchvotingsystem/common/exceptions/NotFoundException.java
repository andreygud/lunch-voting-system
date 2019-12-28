package com.gudilov.lunchvotingsystem.common.exceptions;

public class NotFoundException extends ApplicationException {
    public static final String NOT_FOUND_EXCEPTION = "exception.common.notFound";

    public NotFoundException(String arg) {
        super(ErrorType.DATA_NOT_FOUND, NOT_FOUND_EXCEPTION, arg);
    }
}