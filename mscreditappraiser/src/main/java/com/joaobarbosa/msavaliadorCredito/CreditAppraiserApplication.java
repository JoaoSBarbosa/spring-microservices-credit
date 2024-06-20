package com.joaobarbosa.msavaliadorCredito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CreditAppraiserApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditAppraiserApplication.class, args);
	}

}
