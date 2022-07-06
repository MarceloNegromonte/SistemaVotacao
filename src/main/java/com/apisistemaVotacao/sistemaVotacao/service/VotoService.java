package com.apisistemaVotacao.sistemaVotacao.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apisistemaVotacao.sistemaVotacao.dto.request.VotoRequisicaoDTO;
import com.apisistemaVotacao.sistemaVotacao.exception.NotFoundException;
import com.apisistemaVotacao.sistemaVotacao.model.Pauta;
import com.apisistemaVotacao.sistemaVotacao.model.SessaoVotacao;
import com.apisistemaVotacao.sistemaVotacao.model.Voto;
import com.apisistemaVotacao.sistemaVotacao.repository.PautaRepository;
import com.apisistemaVotacao.sistemaVotacao.repository.SessaoVotacaoRepository;
import com.apisistemaVotacao.sistemaVotacao.repository.VotoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VotoService {

	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
 	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
    public List<Voto> findAllVotacoes() {
    	return votoRepository.findAll();
    }
	
    public Optional<Pauta> getPauta(Long id) {
    	return pautaRepository.findById(id);
    }
    
    private Optional<SessaoVotacao> getSessaoVotacao(Pauta pauta) {
    	return sessaoVotacaoRepository.findByPauta(pauta);
    }
    
    @Transactional
    public Voto votar(Long idPauta, VotoRequisicaoDTO dto) {
    	SessaoVotacao sessaoVotacao = getSessaoVotacao(getPauta(idPauta)
        		.orElseThrow(() -> new NotFoundException("PAUTA_NAO_ENCONTRADA")))
                .orElseThrow(() -> new NotFoundException("SESSAO_NAO_ENCONTRADA"));

        if (LocalDateTime.now().isAfter(sessaoVotacao.getDataHoraFim())) {
            log.error("SESSAO_FECHADA");
        	throw new NotFoundException("SESSAO_FECHADA");
        }
        
        Voto voto = Voto.builder()
        		.cpfUsuario(dto.getCpfUsuario())
        		.mensagemVoto(dto.getMensagemVoto())
        		.dataHora(LocalDateTime.now())
        		.build();
       
        if(votoRepository.existsBySessaoVotacaoAndCpfUsuario(sessaoVotacao, voto.getCpfUsuario())) {
            log.error("VOTO_JA_REGISTRADO");
        	throw new NotFoundException("VOTO_JA_REGISTRADO");
        }
        
        return votoRepository.save(voto);
    }
    
}


