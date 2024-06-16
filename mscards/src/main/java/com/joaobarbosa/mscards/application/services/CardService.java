package com.joaobarbosa.mscards.application.services;

import com.joaobarbosa.mscards.domain.Card;
import com.joaobarbosa.mscards.infra.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    @Transactional
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }
}
