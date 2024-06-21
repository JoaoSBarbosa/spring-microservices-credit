package com.joaobarbosa.msavaliadorCredito.application.services;

import com.joaobarbosa.msavaliadorCredito.application.exception.ClientDataNotFoundException;
import com.joaobarbosa.msavaliadorCredito.application.exception.MicroserviceCommunicationErrorException;
import com.joaobarbosa.msavaliadorCredito.domain.dto.CardClientDTO;
import com.joaobarbosa.msavaliadorCredito.domain.dto.ClientDataDTO;
import com.joaobarbosa.msavaliadorCredito.domain.dto.ClientSituationDTO;
import com.joaobarbosa.msavaliadorCredito.infra.clients.CardsResourceClient;
import com.joaobarbosa.msavaliadorCredito.infra.clients.ClientResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CreditAppraiserService {

    private final ClientResourceClient clientResourceClient;
    private final CardsResourceClient cardsResourceClient;

    public ClientSituationDTO creditAppraiser(String cpf) throws ClientDataNotFoundException, MicroserviceCommunicationErrorException {

        try {
            //realizando comunicação com outro microserviço

            // obtendo dados do MS cliente
            ResponseEntity<ClientDataDTO> clientSituationResponse = clientResourceClient.clientData(cpf);
            // obtendo dados MS CARDS
            ResponseEntity<List<CardClientDTO>> cardClientDTO = cardsResourceClient.getCardsClientByCPF(cpf);

            return ClientSituationDTO
                    .builder()
                    .client(clientSituationResponse.getBody())
                    .cards(cardClientDTO.getBody())
                    .build();

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientDataNotFoundException("Não foi possivel localizar dados de clientes para o CPF informado: " + cpf);
            } else {
                throw new MicroserviceCommunicationErrorException(e.getMessage(), status);
            }
        }
    }
}
