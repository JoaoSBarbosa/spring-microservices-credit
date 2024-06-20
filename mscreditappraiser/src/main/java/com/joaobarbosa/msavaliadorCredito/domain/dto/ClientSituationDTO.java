package com.joaobarbosa.msavaliadorCredito.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class ClientSituationDTO implements Serializable {

    private ClientDataDTO client;
    private List<CardClientDTO> cards;
}
