package com.joaobarbosa.mscards.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SubscriberIssuingCard {

    @RabbitListener(queues = "${mq.queue.emissao-cartoes}")
    public void receiveRequestIssue(@Payload String payload){
        System.out.println(payload);
    }
}
