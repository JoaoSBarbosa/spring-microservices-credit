package com.joaobarbosa.mscards.infra.repositories;

import com.joaobarbosa.mscards.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("SELECT c FROM Card c WHERE c.income <= :income")
    List<Card> findCardsByIncomeLessOrEqualIncome(@Param("income") BigDecimal income);
}
