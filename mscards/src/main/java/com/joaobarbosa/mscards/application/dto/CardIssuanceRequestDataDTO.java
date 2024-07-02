package com.joaobarbosa.mscards.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardIssuanceRequestDataDTO {
    private Long cardId;
    private String cpf;
    private String address;
    private BigDecimal limitReleased;

    public CardIssuanceRequestDataDTO() {}
    public CardIssuanceRequestDataDTO(Long cardId, String cpf, String address, BigDecimal limitReleased) {
        this.cardId = cardId;
        this.cpf = cpf;
        this.address = address;
        this.limitReleased = limitReleased;
    }
}
