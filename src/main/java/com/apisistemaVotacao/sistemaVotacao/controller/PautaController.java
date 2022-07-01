package com.apisistemaVotacao.sistemaVotacao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apisistemaVotacao.sistemaVotacao.dto.PautaDTO;
import com.apisistemaVotacao.sistemaVotacao.model.Pauta;
import com.apisistemaVotacao.sistemaVotacao.service.PautaService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/pauta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PautaController {

	@Autowired
	private PautaService pautaService;
	
	@PostMapping("/criar")
    public ResponseEntity<Pauta> salvarPauta(@Valid @RequestBody Pauta pauta) {
        log.debug("Salvando a pauta  = {}", pauta.getDescricao());
        return new ResponseEntity<> (pautaService.criarPauta(pauta),
        		HttpStatus.CREATED);
    }
	
	   @GetMapping(value = "/{id}")
	    public ResponseEntity<PautaDTO> buscarPautaPeloID(@PathVariable("id") Long id) {
	        log.debug("Buscando a pauta pelo ID = {}", id);
	        return ResponseEntity.ok(pautaService.buscarPautaPeloID(id));
	    }
	
}
