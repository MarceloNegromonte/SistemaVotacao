package com.apisistemaVotacao.sistemaVotacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apisistemaVotacao.sistemaVotacao.model.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long> {

	Optional<Pauta> findById(Long id);
	
	Optional<Pauta> findByNome(String id);
}
