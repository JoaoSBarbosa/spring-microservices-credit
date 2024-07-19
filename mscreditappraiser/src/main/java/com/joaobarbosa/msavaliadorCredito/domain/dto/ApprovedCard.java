package com.joaobarbosa.msavaliadorCredito.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApprovedCard {

    private String cardName;
    private String cardFlag;
    private BigDecimal approvedLimit;

    public ApprovedCard(){}

    public ApprovedCard(String cardName, String cardFlag, BigDecimal approvedLimit) {
        this.cardName = cardName;
        this.cardFlag = cardFlag;
        this.approvedLimit = approvedLimit;
    }
}
