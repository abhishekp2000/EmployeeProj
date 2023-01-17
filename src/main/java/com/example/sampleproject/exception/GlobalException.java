package com.example.sampleproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = InvalidEntryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody ErrorResponse InvalidEntryException(InvalidEntryException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(),e.getMessage());
    }
    @ExceptionHandler(value=EmptyListException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse EmptyListException(EmptyListException e){
        return new ErrorResponse((HttpStatus.BAD_REQUEST.value()),e.getMessage());
    }

}
