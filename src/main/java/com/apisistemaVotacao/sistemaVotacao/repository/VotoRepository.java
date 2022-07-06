package com.apisistemaVotacao.sistemaVotacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apisistemaVotacao.sistemaVotacao.model.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

}
