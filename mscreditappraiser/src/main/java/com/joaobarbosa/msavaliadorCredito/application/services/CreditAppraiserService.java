package com.joaobarbosa.msavaliadorCredito.application.services;

import com.joaobarbosa.msavaliadorCredito.domain.dto.ClientDataDTO;
import com.joaobarbosa.msavaliadorCredito.domain.dto.ClientSituationDTO;
import com.joaobarbosa.msavaliadorCredito.infra.clients.ClientResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreditAppraiserService {

    private final ClientResourceClient clientResourceClient;

    public ClientSituationDTO creditAppraiser(String cpf) {

        //realizando comunicação com outro microserviço
        // obtendo dados do MS cliente
        ResponseEntity<ClientDataDTO> clientSituationResponse = clientResourceClient.clientData(cpf);
        // obtendo dados MS CARDS
        System.out.println("TESTE: "+clientSituationResponse.getBody());
        return ClientSituationDTO
                .builder()
                .client(clientSituationResponse.getBody())
                .build();
    }
}
