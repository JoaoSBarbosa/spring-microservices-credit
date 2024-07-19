package com.joaobarbosa.msavaliadorCredito.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class StanderError {

    private String error;
    private String message;
    private Integer status;
    private Instant timestamp;
    private String path;

    public StanderError(String error, String message, Integer status, Instant timestamp, String path) {}
}
