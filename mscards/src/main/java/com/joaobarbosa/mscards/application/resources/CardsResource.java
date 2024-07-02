package com.joaobarbosa.mscards.application.resources;

import com.joaobarbosa.mscards.application.dto.CardClientResponseDTO;
import com.joaobarbosa.mscards.application.dto.CardRequestDTO;
import com.joaobarbosa.mscards.application.services.CardClientService;
import com.joaobarbosa.mscards.application.services.CardService;
import com.joaobarbosa.mscards.domain.Card;
import com.joaobarbosa.mscards.domain.CardClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/cards")
public class CardsResource {

    private final CardService cardService;
    private final CardClientService cardClientService;

    @GetMapping
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("MS CARDS OK!");
    }

    @PostMapping
    public ResponseEntity<CardRequestDTO> insertCards(@RequestBody CardRequestDTO card){
        cardService.saveCard(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsIncomeLessOrEqual(@RequestParam("income") Long income){
        List<Card> cards = cardService.getCardsIncomeLessOrEqual(income);
        if(cards.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(cards);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Card>> findAll(Pageable pageable){
        Page<Card> cards = cardService.findAll(pageable);
        return ResponseEntity.ok(cards);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardClientResponseDTO>> getCardsClientByCPF(@RequestParam("cpf") String cpf) {
        List<CardClient> cardClients = cardClientService.getCardsByClient(cpf);
        List<CardClientResponseDTO> cardsClientDTO = cardClients.stream().map(CardClientResponseDTO::fromEntity).collect(Collectors.toList());
        System.out.println("EM CARD: " + cardsClientDTO);
        return ResponseEntity.ok(cardsClientDTO);
    }
}
