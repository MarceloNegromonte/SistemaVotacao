package com.apisistemaVotacao.sistemaVotacao.controller;

import java.util.List;

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

import com.apisistemaVotacao.sistemaVotacao.model.Pauta;
import com.apisistemaVotacao.sistemaVotacao.repository.PautaRepository;
import com.apisistemaVotacao.sistemaVotacao.service.PautaService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/v1/pauta")
@CrossOrigin(origins = "*")
public class PautaController {

	@Autowired
	private PautaService pautaService;

	@Autowired
	public PautaController(PautaService pautaService, PautaRepository pautaRepository) {
		this.pautaService = pautaService;
	}
	
	@GetMapping
	public ResponseEntity<List<Pauta>> buscarTodasPautas() {
		log.info("Buscando todas as pautas");

		return ResponseEntity.ok(pautaService.buscarTodasPautas());
	}

    @GetMapping("/{id}")
    public ResponseEntity<Pauta> buscarById(@PathVariable Long id){
    	log.info("Buscando pauta por Id {}", id);
        return new ResponseEntity<>(pautaService.buscarPautaPeloID(id), HttpStatus.OK);
    }

	@PostMapping("/criar")
	public ResponseEntity<Pauta> criarPauta(@Valid @RequestBody Pauta pauta) {
		log.info("Criando Pauta");
		return new ResponseEntity<>(pautaService.criarPauta(pauta), HttpStatus.CREATED);
	}

}
