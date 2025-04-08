package com.SpringEcommerce.SpringBootProject_With_JPA.exceptions;

import com.SpringEcommerce.SpringBootProject_With_JPA.payload.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> fieldNotBlank(MethodArgumentNotValidException e)
    {
        Map<String, String> response = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(err->{
            String fieldName = ((FieldError)err).getField();
            String message = err.getDefaultMessage();

            response.put(fieldName, message);
        });

        return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomResourceNotFoundException.class)
    public ResponseEntity<APIResponse> ResourceNotFound(CustomResourceNotFoundException e)
    {
        APIResponse response = new APIResponse(e.getMessage(), false);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public  ResponseEntity<APIResponse > customAPIException(APIException e)
    {
        String message = e.getMessage();
        APIResponse response = new APIResponse(message, false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
    