package com.joaobarbosa.mscards.infra.repositories;

import com.joaobarbosa.mscards.domain.CardClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardClientRepository extends JpaRepository<CardClient, Long> {

    @Query("SELECT cc FROM CardClient cc WHERE cc.cpf = :cpf")
    List<CardClient> findByCPF(@Param("cpf") String cpf);
}
