package com.apisistemaVotacao.sistemaVotacao.controller;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.apisistemaVotacao.sistemaVotacao.model.Usuario;
import com.apisistemaVotacao.sistemaVotacao.repository.UsuarioRepository;
import com.apisistemaVotacao.sistemaVotacao.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class UsuarioController {

	@Autowired
	public UsuarioService usuarioService;
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Usuario> getById(@PathVariable Long id) {
		log.info("Buscando usuario por Id");
		return usuarioRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/criar")
	@Transactional
	public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario) {
		log.info("Criando usuario");
		return usuarioService.criarUsuario(usuario)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.build());		
	}
	
	@PutMapping("/atualizar")
	@Transactional
	public ResponseEntity<Usuario> atualizarUsuario(@Valid @RequestBody Usuario usuario) {
		log.info("Atualizando usuario");
		return usuarioService.atualizarUsuario(usuario)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.build());		
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void deletarUsuario(@PathVariable Long id) {
		Optional<Usuario> deletarUsuario = usuarioRepository.findById(id);
		log.info("Deletando usuario");
		if (deletarUsuario.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		usuarioRepository.deleteById(id);
	}
	
    //implementando a autenticação

}
