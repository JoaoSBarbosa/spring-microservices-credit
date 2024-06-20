package com.joaobarbosa.mscloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * A aplicação Mscloudgateway é um gateway de API baseado no Spring Cloud Gateway
 * que permite o roteamento de requisições para os microserviços registrados no Eureka Server.
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class MscloudgatewayApplication {

	/**
	 * O ponto de entrada principal da aplicação Spring Boot.
	 * @param args argumentos de linha de comando
	 */
	public static void main(String[] args) {
		SpringApplication.run(MscloudgatewayApplication.class, args);
	}

	/**
	 * Configura o roteamento de rotas para o Spring Cloud Gateway.
	 *
	 * @param builder um construtor de rotas fornecido pelo Spring Cloud Gateway
	 * @return um objeto RouteLocator que define as rotas da aplicação
	 */
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder
				.routes()
				// Define uma rota que redireciona todas as requisições que correspondem ao
				// caminho "/clients/**" para o microserviço "msclients" usando balanceamento de carga (lb)
				.route(r -> r.path("/clients/**").uri("lb://msclients"))
				.route(r -> r.path("/cards/**").uri("lb://mscards"))
				.route(r -> r.path("/analysis_credit/**").uri("lb://mscreditappraiser"))
				.build();
	}
}
