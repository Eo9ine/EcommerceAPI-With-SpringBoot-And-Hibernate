package com.SpringEcommerce.SpringBootProject_With_JPA.exceptions;

public class CustomResourceNotFoundException extends RuntimeException{
    public String re0sourceName;
    public String field;
    public String fieldName;
    public Long fieldId;

    public CustomResourceNotFoundException(String re0sourceName, String field, String fieldName) {
        super(String.format("%s not found in %s: %s", re0sourceName,field,fieldName));
        this.re0sourceName = re0sourceName;
        this.field = field;
        this.fieldName = fieldName;
    }

    public CustomResourceNotFoundException(String re0sourceName, String field, Long fieldId) {
        super(String.format("%s not found in %s: %d", re0sourceName,field,fieldId));
        this.re0sourceName = re0sourceName;
        this.field = field;
        this.fieldId = fieldId;
    }
}
