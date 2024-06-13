package com.joaobarbosa.msclients.application.controllers;

import com.joaobarbosa.msclients.domain.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    @GetMapping
    public String status() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        return ResponseEntity.ok(client);
    }

}
