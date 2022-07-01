package com.apisistemaVotacao.sistemaVotacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apisistemaVotacao.sistemaVotacao.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findById(Long id);

	Optional<Usuario> findByNome(String nome);
	
	Optional<Usuario> findByCpf(String cfp);
	
	Optional<Usuario> findByEmail(String email);

	
	
}
