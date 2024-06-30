package com.joaobarbosa.msavaliadorCredito.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaobarbosa.msavaliadorCredito.domain.dto.CardIssuanceRequestDataDTO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PublisherCardIssuanceRequest {

    private final RabbitTemplate rabbitTemplate;

    private final Queue queueEmissionCard;



    // método que recebe dados para solicitação de emissão de cartão
    public void requestCard(CardIssuanceRequestDataDTO cardIssuanceRequestDataDTO) throws JsonProcessingException {

        // salvando em uma var o json resultado da conversão
        var json = convertIntoJson(cardIssuanceRequestDataDTO);
        // convertAndSend, de RabbitTemplate, recebe dois parametros, a fila e o jason e publica mensagem
        rabbitTemplate.convertAndSend(queueEmissionCard.getName(), json);

    }

    // método que converte para Json
    private String convertIntoJson(CardIssuanceRequestDataDTO cardIssuanceRequestDataDTO) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(cardIssuanceRequestDataDTO);
    }
}
