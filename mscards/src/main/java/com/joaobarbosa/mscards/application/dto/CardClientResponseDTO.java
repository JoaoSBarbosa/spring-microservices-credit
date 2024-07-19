package com.joaobarbosa.mscards.application.dto;

import com.joaobarbosa.mscards.domain.CardClient;
import com.joaobarbosa.mscards.domain.enums.FlagCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardClientResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private FlagCard flagCard;
    public BigDecimal approvedLimit;


    public static CardClientResponseDTO fromEntity(CardClient entity) {
        return new CardClientResponseDTO(
                entity.getCard().getName(),
                entity.getCard().getFlag(),
                entity.getApprovedLimit()
        );
    }

}
