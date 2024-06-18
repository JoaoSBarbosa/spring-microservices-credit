package com.joaobarbosa.mscards.application.services;

import com.joaobarbosa.mscards.application.dto.CardRequestDTO;
import com.joaobarbosa.mscards.domain.Card;
import com.joaobarbosa.mscards.infra.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    @Transactional
    public CardRequestDTO saveCard(CardRequestDTO entity) {
        Card card = new Card();
        if (entity.getFlag() != null) card.setFlag(entity.getFlag());
        if (entity.getName() != null) card.setName(entity.getName());
        if (entity.getLimit() != null) card.setLimit(entity.getLimit());
        if (entity.getIncome() != null) card.setIncome(entity.getIncome());
        card  = cardRepository.save(card);
        return new CardRequestDTO(card);
    }

    @Transactional
    public Page<Card> findAll(Pageable pageable) {
        Page<Card> cards = cardRepository.findAll(pageable);
        if(!cards.isEmpty()){
            return cards;
        }
        return null;
    }

    @Transactional
    public List<Card> getCardsIncomeLessOrEqual(Long income) {
        if (income == null) {
            return Collections.emptyList();
        }

        BigDecimal incomeBigDecimal = BigDecimal.valueOf(income);
        return cardRepository.findCardsByIncomeLessOrEqualIncome(incomeBigDecimal);
    }
}
