package com.joaobarbosa.mscards.application.resources;

import com.joaobarbosa.mscards.application.services.CardClientService;
import com.joaobarbosa.mscards.domain.CardClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/card_client")
public class CardClientController {

    private final CardClientService service;


}
