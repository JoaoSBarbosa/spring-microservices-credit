package com.joaobarbosa.msavaliadorCredito.domain.dto;

import lombok.Data;

@Data
public class CardIssuanceRequestDataDTO {
    private Long cardId;
    private String cpf;
    private String address;
    private String limitReleased;

    public CardIssuanceRequestDataDTO() {
    }

    public CardIssuanceRequestDataDTO(Long cardId, String cpf, String address, String limitReleased) {
        this.cardId = cardId;
        this.cpf = cpf;
        this.address = address;
        this.limitReleased = limitReleased;
    }
}
