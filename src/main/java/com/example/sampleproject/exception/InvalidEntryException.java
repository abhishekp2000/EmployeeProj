package com.example.sampleproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidEntryException extends RuntimeException {


        public InvalidEntryException(String exception) {
            super(exception);
        }
    }

