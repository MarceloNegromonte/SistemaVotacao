package com.apisistemaVotacao.sistemaVotacao.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apisistemaVotacao.sistemaVotacao.dto.PautaDTO;
import com.apisistemaVotacao.sistemaVotacao.exception.NotFoundException;
import com.apisistemaVotacao.sistemaVotacao.model.Pauta;
import com.apisistemaVotacao.sistemaVotacao.repository.PautaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PautaService {

	@Autowired
	private PautaRepository pautaRepository;
	
	@Transactional
    public Pauta criarPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }
	
    @Transactional
    public PautaDTO buscarPautaPeloID(Long id) {
        Optional<Pauta> pautaOptional = pautaRepository.findById(id);

        if (!pautaOptional.isPresent()) {
            log.error("Pauta não localizada para id {}", id);
            throw new NotFoundException("Pauta não localizada para o id " + id);
        }

        return PautaDTO.toDTO(pautaOptional.get());
    }
    
    
}
