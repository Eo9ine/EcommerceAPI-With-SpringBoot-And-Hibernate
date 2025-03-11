package com.SpringEcommerce.SpringBootProject_With_JPA.exceptions;

public class APIException extends RuntimeException{
    public static Long  idVersionExc = 345234543L;

    public APIException() {
    }

    public APIException(String message) {
        System.out.println("No categories found!");
    }


}
