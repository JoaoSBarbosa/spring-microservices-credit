package com.joaobarbosa.mscards.infra.repositories;

import com.joaobarbosa.mscards.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
