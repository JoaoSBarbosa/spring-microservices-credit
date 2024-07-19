package com.joaobarbosa.mscards.application.services.exceptions;

public class ControllerNotFoundException extends RuntimeException{

    public ControllerNotFoundException(String message){
        super(message);
    }
}
