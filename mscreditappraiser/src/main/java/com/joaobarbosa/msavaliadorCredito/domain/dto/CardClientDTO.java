package com.joaobarbosa.msavaliadorCredito.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CardClientDTO {
    private String name;
    private String flag;
    private BigDecimal limit;

    public CardClientDTO(String name, String flag, BigDecimal limit) {
        this.name = name;
        this.flag = flag;
        this.limit = limit;
    }


}

