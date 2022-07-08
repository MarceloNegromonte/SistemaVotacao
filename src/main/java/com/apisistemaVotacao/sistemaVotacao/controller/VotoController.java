package com.apisistemaVotacao.sistemaVotacao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apisistemaVotacao.sistemaVotacao.dto.request.VotoRequestDTO;
import com.apisistemaVotacao.sistemaVotacao.model.Voto;
import com.apisistemaVotacao.sistemaVotacao.service.VotoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/voto")
@CrossOrigin(origins = "*")
@Slf4j
public class VotoController {

	@Autowired
	private VotoService votoService;
	
	@Autowired
	public VotoController(VotoService votoService) {
		this.votoService = votoService;
	}
	
	@PostMapping("/voto")
	public ResponseEntity<Voto> votar(@Valid @RequestBody VotoRequestDTO dto) {
		log.info("Voto registrado com sucesso!");
		return new ResponseEntity<>(votoService.votacao(dto), HttpStatus.CREATED);
	}
	
}
