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

import com.apisistemaVotacao.sistemaVotacao.dto.PautaDTO;
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
	
	@GetMapping("/todasPautas")
	public ResponseEntity<List<Pauta>> buscarTodasPautas(){
		return ResponseEntity.ok(pautaService.buscarTodasPautas());
	}
	
		@GetMapping(value = "/{id}")
	    public ResponseEntity<PautaDTO> buscarPautaPeloID(@PathVariable("id") Long id) {
	        log.debug("Buscando a pauta pelo ID = {}", id);
	        
	        return new ResponseEntity<>(pautaService.buscarPautaPeloID(id), HttpStatus.OK);
	    }
	
		@GetMapping
		public ResponseEntity<Pauta> buscaPorNome(String nome) {
			return new ResponseEntity<>(pautaService.buscaPorNome(nome), HttpStatus.OK);
		}
		
		@PostMapping("/criar")
		public ResponseEntity<Pauta> criarPauta(@Valid @RequestBody Pauta pauta) {
			return new ResponseEntity<>(pautaService.criarPauta(pauta), HttpStatus.CREATED);
		}
	
	/*@PostMapping("/criar")
    public ResponseEntity<PautaResponseDTO> criarPauta(@RequestBody @Valid PautaRequisicaoDTO pautaRequest) {
        log.info("Criando pauta...");
        Pauta pauta = objectMapper.convertValue(pautaRequest, Pauta.class);
        pauta = pautaService.criarPauta(pauta);
        log.info("Pauta criada com sucesso!");

        ResponseEntity.ok(objectMapper.convertValue(pauta, PautaResponseDTO.class));
		return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }*/
	
	   
	   
	   /* @GetMapping(value = "/resultado")
	    public List<PautaResponseDTO> buscarPautasComResultado() {
	        log.info("Consultando pautas...");
	        return pautaService.buscarPautas().stream()
	                .map(this::getPautaResponse)
	                .collect(Collectors.toList());
	    }*/
	    
	   /* private PautaResponseDTO getPautaResponse(Pauta pauta) {
	        PautaResponseDTO pautaResponse = objectMapper.convertValue(pauta, PautaResponseDTO.class);
	        pautaResponse.setResultado(pautaService.resultado(pauta));

	        return pautaResponse;
	    }*/
	
}

//    public ResponseEntity<Pauta> criarPauta(@Valid @RequestBody Pauta pauta) {
//log.info("Criando a pauta  = {}", pauta.getDescricao());
//return new ResponseEntity<> (pautaService.criarPauta(pauta),
//		HttpStatus.CREATED);
//}
