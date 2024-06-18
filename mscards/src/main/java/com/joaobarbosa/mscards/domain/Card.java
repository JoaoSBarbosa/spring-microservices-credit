package com.joaobarbosa.mscards.domain;

import com.joaobarbosa.mscards.domain.enums.FlagCard;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_cartoes")
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_cartao")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "bandeira_cartao")
    private FlagCard flag;
    @Column(name = "renda")
    private BigDecimal income;
    @Column(name = "limite_cartao")
    private BigDecimal limit;


    public Card(BigDecimal limit, BigDecimal income, FlagCard flag, String name) {
        this.limit = limit;
        this.income = income;
        this.flag = flag;
        this.name = name;
    }
}
