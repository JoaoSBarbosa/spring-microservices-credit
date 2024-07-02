package com.joaobarbosa.msavaliadorCredito.application.services;

import com.joaobarbosa.msavaliadorCredito.application.exception.ClientDataNotFoundException;
import com.joaobarbosa.msavaliadorCredito.application.exception.MicroserviceCommunicationErrorException;
import com.joaobarbosa.msavaliadorCredito.application.exception.RequestCardException;
import com.joaobarbosa.msavaliadorCredito.domain.dto.*;
import com.joaobarbosa.msavaliadorCredito.infra.clients.CardsResourceClient;
import com.joaobarbosa.msavaliadorCredito.infra.clients.ClientResourceClient;
import com.joaobarbosa.msavaliadorCredito.infra.mqueue.PublisherCardIssuanceRequest;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Serviço de Avaliação de Crédito.
 * Responsável por avaliar a situação de crédito de um cliente e realizar a avaliação de crédito com base na renda.
 */
@RequiredArgsConstructor
@Service
public class CreditAppraiserService {

    private final ClientResourceClient clientResourceClient;
    private final CardsResourceClient cardsResourceClient;
    private final PublisherCardIssuanceRequest publisherCardIssuanceRequest;

    /**
     * Avalia a situação de crédito de um cliente com base no CPF fornecido.
     *
     * @param cpf CPF do cliente.
     * @return ClientSituationDTO contendo os dados do cliente e os cartões associados.
     * @throws ClientDataNotFoundException se os dados do cliente não forem encontrados.
     * @throws MicroserviceCommunicationErrorException se ocorrer um erro de comunicação com o microserviço.
     */
    public ClientSituationDTO creditAppraiser(String cpf) throws ClientDataNotFoundException, MicroserviceCommunicationErrorException {

        try {
            // Obtendo dados do microserviço de clientes
            ResponseEntity<ClientDataDTO> clientSituationResponse = clientResourceClient.clientData(cpf);

            // Obtendo dados do microserviço de cartões
            ResponseEntity<List<CardClientDTO>> cardClientDTO = cardsResourceClient.getCardsClientByCPF(cpf);

            return ClientSituationDTO
                    .builder()
                    .client(clientSituationResponse.getBody())
                    .cards(cardClientDTO.getBody())
                    .build();

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientDataNotFoundException("Não foi possível localizar dados de cliente para o CPF informado: " + cpf);
            } else {
                throw new MicroserviceCommunicationErrorException("Erro de comunicação com o microserviço de clientes: " + e.getMessage(), status);
            }
        }
    }

    /**
     * Realiza a avaliação de crédito de um cliente com base no CPF e na renda fornecidos.
     *
     * @param cpf CPF do cliente.
     * @param income Renda do cliente.
     * @return ReturnCustomerReviewDTO contendo a lista de cartões aprovados com limites calculados.
     * @throws ClientDataNotFoundException se os dados do cliente não forem encontrados.
     * @throws MicroserviceCommunicationErrorException se ocorrer um erro de comunicação com o microserviço.
     */
    public ReturnCustomerReviewDTO carryAssessment(String cpf, Long income) throws ClientDataNotFoundException, MicroserviceCommunicationErrorException {
        try {
            // Obtendo os dados do cliente por meio do CPF
            ResponseEntity<ClientDataDTO> clientDataDTOResponseEntity = clientResourceClient.clientData(cpf);

            // Obtendo a lista de cartões disponíveis através da renda fornecida
            ResponseEntity<List<CardReturnDTO>> cardsResponse = cardsResourceClient.getCardsIncomeLessOrEqual(income);

            // Convertendo a lista de cartões recebidos em uma lista de objetos CardReturnDTO
            List<CardReturnDTO> cardReturnDTOS = cardsResponse.getBody();

            // Obtendo os dados do cliente
            ClientDataDTO clientDataDTO = clientDataDTOResponseEntity.getBody();

            // Processando a lista de cartões para calcular o limite aprovado
            var approvedCardList = cardReturnDTOS.stream().map(card -> {
                // Calculando o limite aprovado com base na idade do cliente
                BigDecimal baseLimit = card.getLimit();
                BigDecimal ageCal = BigDecimal.valueOf(clientDataDTO.getAge());
                BigDecimal factor = ageCal.divide(BigDecimal.valueOf(10));
                BigDecimal approvedLimit = factor.multiply(baseLimit);

                // Criando o objeto ApprovedCard e configurando os dados do cartão
                ApprovedCard approvedCard = new ApprovedCard();
                approvedCard.setCardName(card.getName());
                approvedCard.setCardFlag(card.getFlag());
                approvedCard.setApprovedLimit(approvedLimit);
                return approvedCard;
            }).collect(Collectors.toList());

            return new ReturnCustomerReviewDTO(approvedCardList);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientDataNotFoundException("Não foi possível localizar dados de cliente para o CPF informado: " + cpf);
            } else {
                throw new MicroserviceCommunicationErrorException("Erro de comunicação com o microserviço de clientes: " + e.getMessage(), status);
            }
        }
    }


    public ProtocolRequestCardDTO requestCardIssue(CardIssuanceRequestDataDTO data){
        try {
            publisherCardIssuanceRequest.requestCard(data);
            var protocol = UUID.randomUUID().toString();
            return new ProtocolRequestCardDTO(protocol);
        }catch (Exception e){
            throw new RequestCardException(e.getMessage());
        }
    }
}
