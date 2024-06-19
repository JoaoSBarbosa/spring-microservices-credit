package com.joaobarbosa.mscards.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "tb_cliente_cartao")
public class CardClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    @ManyToOne // Muitos cartoes para um cliente
    @JoinColumn(name = "id_cartao")
    private Card card;

    @Column(name = "limite_aprovado")
    private BigDecimal approvedLimit;

    public CardClient(BigDecimal approvedLimit, Card card, String cpf) {
        this.approvedLimit = approvedLimit;
        this.card = card;
        this.cpf = cpf;
    }
}
