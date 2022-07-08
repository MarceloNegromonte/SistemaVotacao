package com.apisistemaVotacao.sistemaVotacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apisistemaVotacao.sistemaVotacao.model.SessaoVotacao;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {

	boolean existsByIdPauta(Long idPauta);
	
	Optional<SessaoVotacao> findById(Long id);

}
