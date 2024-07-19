package com.joaobarbosa.msavaliadorCredito;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableRabbit
public class CreditAppraiserApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditAppraiserApplication.class, args);
	}

}
