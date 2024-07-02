package com.joaobarbosa.msavaliadorCredito.application.controllers;

import com.joaobarbosa.msavaliadorCredito.application.exception.ClientDataNotFoundException;
import com.joaobarbosa.msavaliadorCredito.application.exception.MicroserviceCommunicationErrorException;
import com.joaobarbosa.msavaliadorCredito.application.exception.RequestCardException;
import com.joaobarbosa.msavaliadorCredito.application.services.CreditAppraiserService;
import com.joaobarbosa.msavaliadorCredito.domain.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/analysis_credit")
public class CreditAppraiserController {

    private final CreditAppraiserService creditAppraiserService;

    @GetMapping
    public String status() {
        return "MS CREDIT OK";
    }

    @GetMapping(value = "/situation_client", params = "cpf")
    public ResponseEntity queryClientSituation(@RequestParam("cpf") String cpf) {

        try {
            ClientSituationDTO clientSituationDTO = creditAppraiserService.creditAppraiser(cpf);
            return ResponseEntity.ok(clientSituationDTO);

        } catch (ClientDataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (MicroserviceCommunicationErrorException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity carryAssessment(@RequestBody AssessmentData assessmentData) {

        try {
            ReturnCustomerReviewDTO returnCustomerReviewDTO = creditAppraiserService.carryAssessment(assessmentData.getCpf(), assessmentData.getIncome());

            return ResponseEntity.ok().body(returnCustomerReviewDTO);
        } catch (ClientDataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (MicroserviceCommunicationErrorException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("/requestCards")
    public ResponseEntity requestCardIssue(@RequestBody CardIssuanceRequestDataDTO cardIssuanceRequestDataDTO){

        try {
            ProtocolRequestCardDTO protocolRequestCardDTO = creditAppraiserService.requestCardIssue(cardIssuanceRequestDataDTO);
            return ResponseEntity.ok().body(protocolRequestCardDTO);
        }catch (RequestCardException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
