package com.joaobarbosa.msavaliadorCredito.infra.clients;

import com.joaobarbosa.msavaliadorCredito.domain.dto.ClientDataDTO;
import com.joaobarbosa.msavaliadorCredito.domain.dto.ClientSituationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// classe cliente da API de Cliente
/*
Criando o feignClient apontando para o microserviço de cliente
Fzabnedo as requisções para /clients
 */
@FeignClient(value = "msclients", path = "/clients")
public interface ClientResourceClient {

    // utilizando a mesma assinatura que esta definida no ms cliente
    @GetMapping(params = "cpf")
    ResponseEntity<ClientDataDTO> clientData(@RequestParam("cpf") String cpf);
}
