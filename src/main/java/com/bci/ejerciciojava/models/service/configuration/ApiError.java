package com.bci.ejerciciojava.models.service.configuration;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiError {

    private HttpStatus status;
    private String message;


    public ApiError(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;

    }

}
