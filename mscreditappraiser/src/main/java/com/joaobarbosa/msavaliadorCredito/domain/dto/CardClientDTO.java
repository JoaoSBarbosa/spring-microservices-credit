package com.joaobarbosa.msavaliadorCredito.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CardClientDTO implements Serializable {
    private String name;
    private String flagCard;
    private BigDecimal approvedLimit;

    public CardClientDTO() {}
    public CardClientDTO(String name, String flagCard, BigDecimal approvedLimit) {
        this.name = name;
        this.flagCard = flagCard;
        this.approvedLimit = approvedLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlagCard() {
        return flagCard;
    }

    public void setFlagCard(String flagCard) {
        this.flagCard = flagCard;
    }

    public BigDecimal getApprovedLimit() {
        return approvedLimit;
    }

    public void setApprovedLimit(BigDecimal approvedLimit) {
        this.approvedLimit = approvedLimit;
    }
}

