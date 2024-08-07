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
       return  clientRepository.findByCPF(cpf);
    }

}
