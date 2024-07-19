package com.joaobarbosa.msclients.application.services;

import com.joaobarbosa.msclients.domain.Client;
import com.joaobarbosa.msclients.infra.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;


    @Transactional
    public Client saveEntity(Client entity) {
        return clientRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public Optional<Client> findByCpf(String cpf) {
        return clientRepository.findByCpf(cpf);
    }

}
