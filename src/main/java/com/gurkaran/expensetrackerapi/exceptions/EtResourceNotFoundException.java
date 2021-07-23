package com.gurkaran.expensetrackerapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)//404
public class EtResourceNotFoundException extends RuntimeException{

    public EtResourceNotFoundException(String message){
        super(message);
    }
}
