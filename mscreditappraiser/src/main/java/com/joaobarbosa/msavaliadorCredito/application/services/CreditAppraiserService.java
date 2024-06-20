package com.joaobarbosa.msavaliadorCredito.application.services;

import com.joaobarbosa.msavaliadorCredito.domain.dto.ClientSituationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreditAppraiserService {

    public ClientSituationDTO creditAppraiser(String cpf){

        //realizando comunicação com outro microserviço
        // obtendo dados do MS cliente

        // obtendo dados MS CARDS

        return null;
    }
}
