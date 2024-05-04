package com.mediaupload.spring.application.amqp.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProducerGenerateException extends RuntimeException {


    public ProducerGenerateException(String msg) {

        super(msg);
    }
}
