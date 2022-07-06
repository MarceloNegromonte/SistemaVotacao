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

import com.apisistemaVotacao.sistemaVotacao.dto.request.SessaoRequestDTO;
import com.apisistemaVotacao.sistemaVotacao.dto.request.SessaoStartRequestDTO;
import com.apisistemaVotacao.sistemaVotacao.model.SessaoVotacao;
import com.apisistemaVotacao.sistemaVotacao.service.SessaoService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/sessao")
@CrossOrigin(origins = "*")
@Slf4j
@Data
public class SessaoController {
	
	@Autowired
	private SessaoService sessaoService;
	
	@Autowired
	public SessaoController(SessaoService service) {
		this.sessaoService = sessaoService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<SessaoVotacao> BuscaPorId(@PathVariable Long id) {
		log.info("Buscando por Id {}", id);
		return new ResponseEntity(sessaoService.buscaPorId(id), HttpStatus.OK);
	}
	
	@GetMapping("/todas")
	public ResponseEntity<List<SessaoVotacao>> buscarTodas() {
		log.info("Buscando todas sessoes");
		return new ResponseEntity<>(sessaoService.buscarTodas(), HttpStatus.OK);
	}
	
	@PostMapping("/criar")
	public ResponseEntity<SessaoVotacao> criarSessao(@Valid @RequestBody SessaoRequestDTO dto) {
		
		return new ResponseEntity<>(sessaoService.criarSessao(dto), HttpStatus.CREATED);
	}
	
	@PostMapping("/iniciar")
	public ResponseEntity<SessaoVotacao> iniciarVotacao(@Valid @RequestBody SessaoStartRequestDTO dto) {
		return new ResponseEntity<>(sessaoService.iniciarVotacao(dto), HttpStatus.OK);
	}

}

