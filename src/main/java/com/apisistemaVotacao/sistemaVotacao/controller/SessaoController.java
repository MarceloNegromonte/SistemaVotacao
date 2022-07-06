package com.apisistemaVotacao.sistemaVotacao.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.apisistemaVotacao.sistemaVotacao.model.SessaoVotacao;
import com.apisistemaVotacao.sistemaVotacao.repository.SessaoVotacaoRepository;
import com.apisistemaVotacao.sistemaVotacao.service.PautaService;
import com.apisistemaVotacao.sistemaVotacao.service.SessaoService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/sessao")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
@Data
public class SessaoController {
	
	@Autowired
	private PautaService pautaService;
	
	@Autowired
	private SessaoService sessaoService;
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<SessaoVotacao> BuscaById(@PathVariable Long id) {
		return sessaoVotacaoRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

    @PostMapping("/{idPauta}/iniciar-sessao-votacao")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SessaoVotacao> iniciarSessaoVotacao(@PathVariable("idPauta") Long idPauta) {
        log.info("Iniciando sessão de votação...", idPauta);
        sessaoService.iniciarSessaoVotacao(idPauta, LocalDateTime.now().plusSeconds(sessaoService.getTempoSessaoPadrao()));
        log.info("Sessão de votação iniciada com sucesso, o tempo de votação encerra em " + sessaoService.getTempoSessaoPadrao() + " segundos.");

        return ResponseEntity.ok().build();
    }
}

