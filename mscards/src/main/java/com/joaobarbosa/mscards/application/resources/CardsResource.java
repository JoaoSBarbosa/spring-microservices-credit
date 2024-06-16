package com.joaobarbosa.mscards.application.resources;

import com.joaobarbosa.mscards.application.services.CardService;
import com.joaobarbosa.mscards.domain.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RequiredArgsConstructor
@RestController
@RequestMapping("/cards")
public class CardsResource {

    private final CardService cardService;

    @GetMapping
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Microserviço Cards está rodando!");
    }

    @PostMapping
    public ResponseEntity<Card> insertCards(@RequestBody Card card){

        card = cardService.saveCard(card);

        return ResponseEntity.ok().body(card);
    }

}
