package com.joaobarbosa.msavaliadorCredito.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queue.emissao-cartoes}")
    private String cardsEmissionQueue;

    @Bean
    public Queue queueEmissionCards(){
        // Queue recebe dois parametros, nome da fila e se é duravel
        return new Queue(cardsEmissionQueue, true);
    }
}
