package com.joaobarbosa.msavaliadorCredito.application.controllers;

import com.joaobarbosa.msavaliadorCredito.application.services.CreditAppraiserService;
import com.joaobarbosa.msavaliadorCredito.domain.dto.ClientSituationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/analysis_credit")
public class CreditAppraiserController {

    private final CreditAppraiserService creditAppraiserService;

    @GetMapping
    public String status (){
        return "MS CREDIT OK";
    }

    @GetMapping(value = "/situation_client", params = "cpf")
    public ResponseEntity<ClientSituationDTO> queryClientSituation(@RequestParam("cpf") String cpf){

        // Consultar microserviço de cliente para obter os dados do client

        ClientSituationDTO clientSituationDTO = creditAppraiserService.creditAppraiser(cpf);
        // consultar miscroserviço de cartões para obter os dados de cartões associado ao cliente

        return ResponseEntity.ok(clientSituationDTO);
    }


}
