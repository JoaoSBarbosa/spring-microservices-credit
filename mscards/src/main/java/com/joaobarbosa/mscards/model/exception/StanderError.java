package com.joaobarbosa.mscards.model.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class StanderError implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String message;
    private Integer status;
    private String error;
    private String path;
    private Instant timestamp;

    public StanderError(String message, Integer status, String error, String path, Instant timestamp) {
        this.message = message;
        this.status = status;
        this.error = error;
        this.path = path;
        this.timestamp = timestamp;
    }
}
