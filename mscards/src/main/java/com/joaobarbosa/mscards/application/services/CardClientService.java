package com.joaobarbosa.mscards.application.services;

import com.joaobarbosa.mscards.application.services.exceptions.ControllerNotFoundException;
import com.joaobarbosa.mscards.domain.CardClient;
import com.joaobarbosa.mscards.infra.repositories.CardClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CardClientService {

    private final CardClientRepository clientRepository;


    @Transactional(readOnly = true)
    public List<CardClient> getCardsByClient(String cpf) {

        List<CardClient> cardClients = clientRepository.findByCPF(cpf);
        if (cardClients.isEmpty()) {
            throw new ControllerNotFoundException("Entidade Cartão de Cliente não possui registros associado ao CPF informado: " + cpf);
        }
        return cardClients;
    }

}
