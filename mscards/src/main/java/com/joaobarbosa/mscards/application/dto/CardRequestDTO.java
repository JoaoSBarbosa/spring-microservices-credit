package com.joaobarbosa.mscards.application.dto;

import com.joaobarbosa.mscards.domain.Card;
import com.joaobarbosa.mscards.domain.enums.FlagCard;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class CardRequestDTO implements Serializable {

    private String name;
    private FlagCard flag;
    private BigDecimal income;
    private BigDecimal limit;

    public CardRequestDTO(String name, FlagCard flag, BigDecimal income, BigDecimal limit) {
        this.name = name;
        this.flag = flag;
        this.income = income;
        this.limit = limit;
    }
    public CardRequestDTO(Card entity) {
        name = entity.getName();
        flag = entity.getFlag();
        income = entity.getIncome();
        limit = entity.getLimit();
    }
}
