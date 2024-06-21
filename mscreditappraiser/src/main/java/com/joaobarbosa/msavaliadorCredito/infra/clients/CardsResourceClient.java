package com.joaobarbosa.msavaliadorCredito.infra.clients;

import com.joaobarbosa.msavaliadorCredito.domain.dto.CardClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards", path = "/cards")
public interface CardsResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CardClientDTO>> getCardsClientByCPF(@RequestParam("cpf") String cpf);

}
