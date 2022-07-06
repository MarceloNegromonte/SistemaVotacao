package com.apisistemaVotacao.sistemaVotacao.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apisistemaVotacao.sistemaVotacao.dto.request.VotoRequestDTO;
import com.apisistemaVotacao.sistemaVotacao.exception.NotFoundException;
import com.apisistemaVotacao.sistemaVotacao.model.SessaoVotacao;
import com.apisistemaVotacao.sistemaVotacao.model.Voto;
import com.apisistemaVotacao.sistemaVotacao.repository.SessaoVotacaoRepository;
import com.apisistemaVotacao.sistemaVotacao.repository.VotoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VotoService {

	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
 	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	@Autowired
	private SessaoService sessaoService;
	
	@Autowired
    public VotoService(VotoRepository votoRepository, SessaoVotacaoRepository sessaoVotacaoRepository, SessaoService sessaoService) {
        this.votoRepository = votoRepository;
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
        this.sessaoService = sessaoService;
    }
	
	@Transactional
	public Voto votacao(VotoRequestDTO dto) {
		Optional<SessaoVotacao> sessaoVotacao = sessaoVotacaoRepository.findById(dto.getSessaoVotacaoId());
		log.info("Verificando sessao votacao");
		if (sessaoVotacao.isEmpty()) {
			throw new NotFoundException("Sessao votacao nao encontrada");
		}
		
		Voto voto = Voto.builder()
				.voto(dto.getVoto())
				.votoInstant(Instant.now())
				.sessaoVotacao(sessaoVotacao.get()).build();
		
		verificaSessaoVotoValidoTempo(voto);
		
		return votoRepository.save(voto);
	}
	
	@Transactional
	private void verificaSessaoVotoValidoTempo(Voto voto) {
		log.info("Verificando tempo sessao valida");
		if (voto.getVotoInstant().isAfter(voto.getSessaoVotacao().getFechado())) {
			throw new RuntimeException("Sessao votacao expirada");
		}
	}
    
}


