package com.apisistemaVotacao.sistemaVotacao.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.apisistemaVotacao.sistemaVotacao.exception.NotFoundException;
import com.apisistemaVotacao.sistemaVotacao.model.Pauta;
import com.apisistemaVotacao.sistemaVotacao.model.SessaoVotacao;
import com.apisistemaVotacao.sistemaVotacao.repository.PautaRepository;
import com.apisistemaVotacao.sistemaVotacao.repository.SessaoVotacaoRepository;

@Service
public class SessaoService {

    @Value("${tempo.sessao.votacao.segundos}")
    private Integer tempoSessaoPadrao;
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Transactional()
    public List<SessaoVotacao> encontrarTodasAsSessoes() {
        return sessaoVotacaoRepository.findAll();
    }
	
    private Optional<SessaoVotacao> getSessaoVotacao(Pauta pauta) {
        return sessaoVotacaoRepository.findByPauta(pauta);
    }
	
    public Optional<Pauta> getPauta(Long id) {
        return pautaRepository.findById(id);
    }
    
    public Integer getTempoSessaoPadrao() {
        return tempoSessaoPadrao;
    }
	
    @Transactional
    public void iniciarSessaoVotacao(Long idPauta, LocalDateTime dataFechamento) {
        Pauta pauta = getPauta(idPauta).orElseThrow(() -> new NotFoundException("PAUTA_NAO_ENCONTRADA"));

        if(Objects.requireNonNull(getSessaoVotacao(pauta)).isPresent()){
            throw new NotFoundException("SESSAO_JA_EXISTE");
        }

        criaSessaoVotacao(pauta, dataFechamento);
    }

    private void criaSessaoVotacao(Pauta pauta, LocalDateTime dataFechamento) {
        SessaoVotacao sessaoVotacao = SessaoVotacao.builder()
                .dataHoraInicio(LocalDateTime.now())
                .dataHoraFim(dataFechamento(dataFechamento))
                .pauta(pauta)
                .build();

        sessaoVotacaoRepository.save(sessaoVotacao);
    }
    
    private LocalDateTime dataFechamento(LocalDateTime dataFechamento) {
        return dataFechamento == null ? LocalDateTime.now().plusSeconds(tempoSessaoPadrao) : dataFechamento;
    }
	
}
