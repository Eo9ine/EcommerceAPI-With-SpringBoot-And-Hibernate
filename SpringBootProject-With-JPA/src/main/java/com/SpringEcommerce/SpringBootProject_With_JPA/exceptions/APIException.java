package com.SpringEcommerce.SpringBootProject_With_JPA.exceptions;

public class APIException extends RuntimeException{

    public APIException() {
    }

    public APIException(String message) {
        super(message);
    }

}
