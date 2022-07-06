package com.apisistemaVotacao.sistemaVotacao.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.apisistemaVotacao.sistemaVotacao.dto.PautaDTO;
import com.apisistemaVotacao.sistemaVotacao.dto.request.PautaRequisicaoDTO;
import com.apisistemaVotacao.sistemaVotacao.dto.response.PautaResponseDTO;
import com.apisistemaVotacao.sistemaVotacao.model.Pauta;
import com.apisistemaVotacao.sistemaVotacao.service.PautaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/v1/pauta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class PautaController {

	@Autowired
	private PautaService pautaService;
    private final ObjectMapper objectMapper;
	
	@PostMapping("/criar")
    public ResponseEntity<PautaResponseDTO> criarPauta(@RequestBody @Valid PautaRequisicaoDTO pautaRequest) {
        log.info("Criando pauta...");
        Pauta pauta = objectMapper.convertValue(pautaRequest, Pauta.class);
        pauta = pautaService.criarPauta(pauta);
        log.info("Pauta criada com sucesso!");

        ResponseEntity.ok(objectMapper.convertValue(pauta, PautaResponseDTO.class));
		return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
	
	   @GetMapping(value = "/{id}")
	   @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<PautaDTO> buscarPautaPeloID(@PathVariable("id") Long id) {
	        log.debug("Buscando a pauta pelo ID = {}", id);
	        return ResponseEntity.ok(pautaService.buscarPautaPeloID(id));
	    }
	   
	    @GetMapping(value = "/resultado")
	    public List<PautaResponseDTO> buscarPautasComResultado() {
	        log.info("Consultando pautas...");
	        return pautaService.buscarPautas().stream()
	                .map(this::getPautaResponse)
	                .collect(Collectors.toList());
	    }
	    
	    private PautaResponseDTO getPautaResponse(Pauta pauta) {
	        PautaResponseDTO pautaResponse = objectMapper.convertValue(pauta, PautaResponseDTO.class);
	        pautaResponse.setResultado(pautaService.resultado(pauta));

	        return pautaResponse;
	    }
	
}

//    public ResponseEntity<Pauta> criarPauta(@Valid @RequestBody Pauta pauta) {
//log.info("Criando a pauta  = {}", pauta.getDescricao());
//return new ResponseEntity<> (pautaService.criarPauta(pauta),
//		HttpStatus.CREATED);
//}
