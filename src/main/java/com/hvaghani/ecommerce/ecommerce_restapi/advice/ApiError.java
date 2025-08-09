package com.hvaghani.ecommerce.ecommerce_restapi.advice;

import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiError {

    private LocalDateTime timeStamp;

    private String error;

    private List<String> subErrors;

    private HttpStatusCode httpStatusCode;

    public ApiError() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiError(String error, List<String> subErrors, HttpStatusCode httpStatusCode) {
        this();
        this.error = error;
        this.subErrors = subErrors;
        this.httpStatusCode = httpStatusCode;
    }

}
