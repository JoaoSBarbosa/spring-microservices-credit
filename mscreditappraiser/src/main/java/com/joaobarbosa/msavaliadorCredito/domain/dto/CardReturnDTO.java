package com.joaobarbosa.msavaliadorCredito.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CardReturnDTO implements Serializable {
    private String name;
    private String flag;
    private BigDecimal limit;

    public CardReturnDTO(){}
    public CardReturnDTO(String name, String cardFlag, BigDecimal cardBaseLimit) {
        this.name = name;
        this.flag = cardFlag;
        this.limit = cardBaseLimit;
    }
}
