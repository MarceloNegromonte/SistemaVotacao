package com.apisistemaVotacao.sistemaVotacao.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apisistemaVotacao.sistemaVotacao.model.Usuario;
import com.apisistemaVotacao.sistemaVotacao.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class UsuarioController {

	@Autowired
	public UsuarioService usuarioService;
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		log.info("Buscando usuario por Id");
		return new ResponseEntity<>(usuarioService.buscarPorId(id), HttpStatus.OK);
	}
	
    @GetMapping
    @Transactional
    public ResponseEntity<Usuario> buscarPorNome(@RequestParam String name){
    	log.info("Buscando por nome");
        return new ResponseEntity<>(usuarioService.buscarPorNome(name), HttpStatus.OK);
    }
	
    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario){
        log.info("Criando usuario");
    	return new ResponseEntity(usuarioService.criarUsuario(usuario), HttpStatus.CREATED);
    }
	
	@PutMapping("/atualizar")
	@Transactional
    public ResponseEntity<Usuario> atualizar(@Valid @RequestBody Usuario usuario){
        log.info("Atualizando usuario");
		return new ResponseEntity<>(usuarioService.atualizarUsuario(usuario), HttpStatus.OK);
    }

	@DeleteMapping("/{id}")
	@Transactional
	public void deletarUsuario(@PathVariable Long id) {
		log.info("Deletando usuario");
		usuarioService.deletaUsuario(id);
	}

}
