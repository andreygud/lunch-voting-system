package com.gudilov.lunchvotingsystem.common.web.errorhandling;

public class ErrorInfo {
    private final String url;
    private final String errorMessage;
    private final String[] details;

    public ErrorInfo(CharSequence url, String errorMessage, String... details) {
        this.url = url.toString();
        this.errorMessage = errorMessage;
        this.details = details;
    }

    public String getUrl() {
        return url;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String[] getDetails() {
        return details;
    }
}