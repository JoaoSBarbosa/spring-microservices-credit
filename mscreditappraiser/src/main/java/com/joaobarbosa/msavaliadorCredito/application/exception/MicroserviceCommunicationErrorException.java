package com.joaobarbosa.msavaliadorCredito.application.exception;

import lombok.Getter;

public class MicroserviceCommunicationErrorException extends Exception {

    @Getter
    private Integer status;
    public MicroserviceCommunicationErrorException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
