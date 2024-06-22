package com.joaobarbosa.msavaliadorCredito.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AssessmentData implements Serializable {

    private String cpf;
    private Long income;

    public AssessmentData(String cpf, Long income) {
        this.cpf = cpf;
        this.income = income;
    }
}
