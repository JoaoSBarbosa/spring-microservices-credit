package com.joaobarbosa.mscards.infra.mqueue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaobarbosa.mscards.application.services.exceptions.ControllerNotFoundException;
import com.joaobarbosa.mscards.domain.Card;
import com.joaobarbosa.mscards.domain.CardClient;
import com.joaobarbosa.mscards.domain.dto.CardIssuanceRequestDataDTO;
import com.joaobarbosa.mscards.infra.repositories.CardClientRepository;
import com.joaobarbosa.mscards.infra.repositories.CardRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class SubscriberIssuingCard {

    private final CardRepository cardRepository;
    private final CardClientRepository cardClientRepository;

    public SubscriberIssuingCard(CardRepository cardRepository, CardClientRepository cardClientRepository) {
        this.cardRepository = cardRepository;
        this.cardClientRepository = cardClientRepository;
    }

    @RabbitListener(queues = "${mq.queue.emissao-cartoes}")
    public void receiveRequestIssue(@Payload String payload) throws JsonProcessingException {

        try {
            var mapper = new ObjectMapper();
            CardIssuanceRequestDataDTO data = mapper.readValue(payload, CardIssuanceRequestDataDTO.class);

            Card card = cardRepository.findById(data.getCardId()).orElseThrow(()-> new ControllerNotFoundException("Não loclaizados cartão com o id informado"));

            CardClient cardClient = new CardClient();
            cardClient.setCard(card);
            cardClient.setCpf(data.getCpf());
            cardClient.setApprovedLimit(data.getLimitReleased());

            cardClientRepository.save(cardClient);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
