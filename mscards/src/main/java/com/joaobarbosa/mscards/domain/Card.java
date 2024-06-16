package com.joaobarbosa.mscards.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

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
    @Column(name = "bandeira_cartao")
    private String flag;
    @Column(name = "renda")
    private String income;
    @Column(name = "limite_cartao")
    private String limit;

    public Card(String limit, String income, String flag, String name) {
        this.limit = limit;
        this.income = income;
        this.flag = flag;
        this.name = name;
    }
}
