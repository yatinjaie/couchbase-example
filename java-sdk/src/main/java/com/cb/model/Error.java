package com.cb.model;

/**
 * A standardized error format for failing responses, that the frontend application can interpret
 * for all endpoints.
 *
 */
public class Error {

    private final String failure;

    public Error(String failure) {
        this.failure = failure;
    }

    public String getFailure() {
        return failure;
    }
}
