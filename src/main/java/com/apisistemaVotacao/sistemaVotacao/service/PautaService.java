package com.apisistemaVotacao.sistemaVotacao.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.apisistemaVotacao.sistemaVotacao.dto.PautaDTO;
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
@Cacheable(value = "pautas", key = "#pauta.id")
public class PautaService {

    @Value("${tempo.sessao.votacao.segundos}")
    private Integer tempoSessaoPadrao;
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	@CacheEvict(value = "pautas", allEntries = true)
	@Transactional
    public Pauta criarPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
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
    
    public Optional<Pauta> buscarPauta(Long id) {
        return pautaRepository.findById(id);
    }
    
    private Optional<SessaoVotacao> buscarSessaoVotacao(Pauta pauta) {
        return sessaoVotacaoRepository.findByPauta(pauta);
    }
    
    @Transactional
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
    }
    
    public Map<String, Long> resultado(Pauta pauta) {

        Collection<Voto> votos = buscarSessaoVotacao(pauta).isPresent() ? buscarSessaoVotacao(pauta).get().getVotos() : new ArrayList<>();

        Map<String, Long> resultado = new HashMap<>();
        resultado.put("SIM", votos.stream().filter(v -> v.getMensagemVoto().toString().equalsIgnoreCase("SIM")).count());
        resultado.put("NAO", votos.stream().filter(v -> v.getMensagemVoto().toString().equalsIgnoreCase("NAO")).count());

        return resultado;
    }

    public Integer buscarTempoSessaoPadrao() {
        return tempoSessaoPadrao;
    }
    
    public List<Pauta> buscarPautas() {
        return pautaRepository.findAll();
    }
    
}
