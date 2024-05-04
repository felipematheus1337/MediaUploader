package com.mediaupload.spring.domain.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UnsuportedMediaContentTypeException extends RuntimeException {

    public UnsuportedMediaContentTypeException(String msg) {

        super(msg);
    }
}
