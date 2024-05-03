package com.mediaupload.spring.domain.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileEmptyNotAllowedException extends RuntimeException {

    public FileEmptyNotAllowedException(String msg) {

        super(msg);
    }
}
