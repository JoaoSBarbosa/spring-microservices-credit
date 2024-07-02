package com.joaobarbosa.msavaliadorCredito.domain.dto;

import lombok.Data;

@Data
public class ProtocolRequestCardDTO {
    private String protocol;
    public ProtocolRequestCardDTO() {

    }

    public ProtocolRequestCardDTO(String protocol) {
        this.protocol = protocol;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
