package com.apisistemaVotacao.sistemaVotacao.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.apisistemaVotacao.sistemaVotacao.dto.PautaDTO;
import com.apisistemaVotacao.sistemaVotacao.exception.NotFoundException;
import com.apisistemaVotacao.sistemaVotacao.model.Pauta;
import com.apisistemaVotacao.sistemaVotacao.model.enums.StatusEnum;
import com.apisistemaVotacao.sistemaVotacao.model.enums.VotoStatus;
import com.apisistemaVotacao.sistemaVotacao.repository.PautaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Cacheable(value = "pautas", key = "#pauta.id")
public class PautaService {

	private final PautaRepository pautaRepository;
	
	@Autowired
	public PautaService(PautaRepository pautaRepository) {
		this.pautaRepository = pautaRepository;
	}
	
	@Cacheable(value="pautas")
    @Transactional
	public List<Pauta> buscarTodasPautas(){
		return pautaRepository.findAll();
	}

	@Cacheable(value="pautas")
    @Transactional
    public PautaDTO buscarPautaPeloID(Long id) {
        Optional<Pauta> pautaOptional = pautaRepository.findById(id);

        if (!pautaOptional.isPresent()) {
            log.error("Pauta não localizada para id {}", id);
            throw new NotFoundException("Pauta não localizada para o id " + id);
        }

        return PautaDTO.toDTO(pautaOptional.get());
    }
	
	@Transactional
	public Pauta buscaPorNome(String nome) {
		return pautaRepository.findByNome(nome).orElse(null);
	}
	
	@CacheEvict(value = "pautas", allEntries = true)
	@Transactional
	public Pauta criarPauta(Pauta estado) {
		estado.setStatus(StatusEnum.ABERTA);
		
		return pautaRepository.save(estado);
	}
	
	@CacheEvict(value = "pautas")
	@Transactional
	public void mudancaStatus(Pauta estado) {
		estado.setStatus(StatusEnum.FECHADA);
		
		pautaRepository.save(estado);
	}
	
	@CacheEvict(value = "pautas")
	@Transactional
	public void definirVencedor(Pauta pauta) {
		if (pauta.getPercentualSim() > pauta.getPercentualNao()) {
			
			pauta.setVencedor(VotoStatus.SIM);
		}else {
			pauta.setVencedor(VotoStatus.NAO);
		}
	}
    
	@CacheEvict(value = "pautas")
	@Transactional
	public void definirPercentual(Pauta pauta) {
        pauta.setPercentualSim(Precision.round(((
                Double.valueOf(pauta.getQtdVotosSim())/ pauta.getQtdVotos())*100), 2));
        pauta.setPercentualNao(Precision.round(((
                Double.valueOf(pauta.getQtdVotosNao())/ pauta.getQtdVotos())*100), 2));
    }
    
	
   /* private Optional<SessaoVotacao> buscarSessaoVotacao(Pauta pauta) {
        return sessaoVotacaoRepository.findByPauta(pauta);
    }*/
    
    /*@Transactional
    public void votar(Long idPauta, Voto voto) {
        SessaoVotacao sessaoVotacao = buscarSessaoVotacao(buscarPauta(idPauta)
                .orElseThrow(() -> new NotFoundException("PAUTA_NAO_ENCONTRADA")))
                .orElseThrow(() -> new NotFoundException("SESSAO_NAO_ENCONTRADA"));

        if (LocalDateTime.now().isAfter(sessaoVotacao.getDataHoraFim())) {
            throw new NotFoundException("SESSAO_FECHADA");
        }

        voto.setSessaoVotacao(sessaoVotacao);
        voto.setDataHora(LocalDateTime.now());

        if(votoRepository.existsBySessaoVotacaoAndCpfUsuario(sessaoVotacao, voto.getCpfUsuario())) {
            throw new NotFoundException("VOTO_JA_REGISTRADO");
        }

        votoRepository.save(voto);
    }*/
    
   /* public Map<String, Long> resultado(Pauta pauta) {

        Collection<Voto> votos = buscarSessaoVotacao(pauta).isPresent() ? buscarSessaoVotacao(pauta).get().getVotos() : new ArrayList<>();

        Map<String, Long> resultado = new HashMap<>();
        resultado.put("SIM", votos.stream().filter(v -> v.getMensagemVoto().toString().equalsIgnoreCase("SIM")).count());
        resultado.put("NAO", votos.stream().filter(v -> v.getMensagemVoto().toString().equalsIgnoreCase("NAO")).count());

        return resultado;
    }*/

    /*public Integer buscarTempoSessaoPadrao() {
        return tempoSessaoPadrao;
    }*/
    
}

/*	@CacheEvict(value = "pautas", allEntries = true)
@Transactional
public Pauta criarPauta(Pauta pauta) {
    return pautaRepository.save(pauta);
}*/
