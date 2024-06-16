package com.joaobarbosa.msclients.application.controllers;

import com.joaobarbosa.msclients.application.dto.ClientSaveDTO;
import com.joaobarbosa.msclients.application.services.ClientService;
import com.joaobarbosa.msclients.domain.Client;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
@Slf4j
public class ClientsResource {

    private final ClientService clientService;

    @GetMapping
    public String status() {
        log.info("Obtendo o stataus do microserviço de cliente");
        return "OK";
    }

    @PostMapping
    public ResponseEntity addClient(@RequestBody ClientSaveDTO source) {
        Client client = source.convertToClient();
        client = clientService.saveEntity(client);
        // construindo a URI do recurso criado para adicionar ao cabeçalho da resposta
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest() // cria uma instancia do UriComponetsBuilder com base na requisição atual
                .query("cpf={cpf}") // adiciona um caminho adicional ao URI, neste caso, o cpf do cliente
                .buildAndExpand(client.getCpf()) // expande a URI com o valor do ID do cliente
                .toUri(); // Converte a URI construída para uma instância de URI
        return ResponseEntity.created(headerLocation).build();
    }

    /**
     * Endpoint para obter os dados de um cliente pelo CPF.
     *
     * @param cpf O CPF do cliente a ser buscado.
     * @return Um ResponseEntity contendo os dados do cliente se encontrado, ou status 404 se não encontrado.
     */
    @GetMapping(params = "cpf")
    public ResponseEntity<Client> getClientDataByCpf(@RequestParam("cpf") String cpf){

        Optional<Client> client = clientService.findByCpf(cpf);
        // Usa map para transformar o Optional<Client> em Optional<ResponseEntity<Client>>
        // e orElseGet para retornar 404 se o cliente não for encontrado
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }



}
