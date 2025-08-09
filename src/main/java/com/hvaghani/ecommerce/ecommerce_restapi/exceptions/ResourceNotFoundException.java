package com.hvaghani.ecommerce.ecommerce_restapi.exceptions;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
