package com.apisistemaVotacao.sistemaVotacao.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.apisistemaVotacao.sistemaVotacao.dto.request.SessaoRequestDTO;
import com.apisistemaVotacao.sistemaVotacao.dto.request.SessaoStartRequestDTO;
import com.apisistemaVotacao.sistemaVotacao.exception.NotFoundException;
import com.apisistemaVotacao.sistemaVotacao.model.Pauta;
import com.apisistemaVotacao.sistemaVotacao.model.SessaoVotacao;
import com.apisistemaVotacao.sistemaVotacao.model.enums.StatusEnum;
import com.apisistemaVotacao.sistemaVotacao.model.enums.VotoStatus;
import com.apisistemaVotacao.sistemaVotacao.repository.PautaRepository;
import com.apisistemaVotacao.sistemaVotacao.repository.SessaoVotacaoRepository;

@Service
public class SessaoService {

	private final PautaRepository pautaRepository;

	private final PautaService pautaService;

	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;

	public SessaoService(SessaoVotacaoRepository sessaoRepository, PautaRepository pautaRepository,
			PautaService pautaService) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
		this.pautaRepository = pautaRepository;
		this.pautaService = pautaService;
	}

	@Transactional
	private boolean verificarExistenciaPauta(Long idPauta) {

		return sessaoVotacaoRepository.existsByPautaAndId(idPauta);
	}

	@Transactional()
	public List<SessaoVotacao> buscarTodas() {
		return sessaoVotacaoRepository.findAll();
	}

	@Transactional
	public Optional<Pauta> buscaPorId(Long id) {
		return pautaRepository.findById(id);
	}

	@Transactional
	public SessaoVotacao criarSessao(SessaoRequestDTO dto) {
		if (verificarExistenciaPauta(dto.getPautaID())) {
			throw new NotFoundException("Pauta nao existe");
		}

		SessaoVotacao sessaoVotacao = SessaoVotacao.builder()
				.duracao(tempoFechado(dto.getDuracao()))
				.pauta(buscarPauta(dto))
				.build();

		return sessaoVotacaoRepository.save(sessaoVotacao);
	}

	@Transactional
	private Integer tempoFechado(Integer duracao) {

		return Objects.isNull(duracao) ? 60 : duracao;
	}

	@Transactional
	private Pauta buscarPauta(SessaoRequestDTO dto) {
		return pautaRepository.findById(dto.getPautaID())
				.orElseThrow(() -> new RuntimeException("Pauta nao encontrada"));
	}
	
	@Transactional
	public SessaoVotacao iniciarVotacao(SessaoStartRequestDTO dto) {
		SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(dto.getSessaoId())
				.orElseThrow(() -> new NotFoundException("Sessao votacao nao encontrada"));
		
		if (sessaoVotacao.getPauta().getStatus().equals(StatusEnum.valueOf("FECHADA"))) {
			throw new RuntimeException("A pauta esta FECHADA");
		}
		
		sessaoVotacao.setFechado(Instant.now().plus(sessaoVotacao.getDuracao(), ChronoUnit.SECONDS));
		
		return sessaoVotacaoRepository.save(sessaoVotacao);
	}
	
	@Scheduled(fixedDelay = 5000)
	@Transactional
	public void fecharSessao() {
		List<SessaoVotacao> listSessaoVotacao = obterVotacaoExpiradaMasNaoFechada();
		
		listSessaoVotacao.forEach(votacao -> {
			votacao.getPauta().setQtdVotos(votacao.getVotos().size());
			votacao.getPauta().setQtdVotosSim(qtSim(votacao));
			votacao.getPauta().setQtdVotosNao(qtNao(votacao));
			sessaoVotacaoRepository.save(votacao);
			pautaService.mudancaStatus(votacao.getPauta());
			pautaService.definirPercentual(votacao.getPauta());
			pautaService.definirVencedor(votacao.getPauta());
		});
	}
	
	@Transactional
	public Integer qtSim(SessaoVotacao sessaoVotacao) {
		return Math.toIntExact(sessaoVotacao.getVotos().stream()
				.filter(c -> c.getVoto().equals(VotoStatus.valueOf("SIM")))
				.count());
	}
	
	@Transactional
	public Integer qtNao(SessaoVotacao sessaoVotacao) {
		return Math.toIntExact(sessaoVotacao.getVotos().stream()
				.filter(c -> c.getVoto().equals(VotoStatus.valueOf("NAO")))
				.count());
	}
	
	@Transactional
    private List<SessaoVotacao> obterVotacaoExpiradaMasNaoFechada() {

        return sessaoVotacaoRepository.findAll().stream().filter(
                votingSession -> votingSession.fechada() && votingSession.aberta()
        ).collect(Collectors.toList());
    }
	
	
	// private Optional<SessaoVotacao> buscaSessaoVotacao(Pauta pauta) {
	// return sessaoVotacaoRepository.findByPauta(pauta);
	// }

	/*
	 * public Integer getTempoSessaoPadrao() { return tempoSessaoPadrao; }
	 */

	/*
	 * @Transactional public void iniciarSessaoVotacao(Long idPauta, LocalDateTime
	 * dataFechamento) { Pauta pauta = getPauta(idPauta).orElseThrow(() -> new
	 * NotFoundException("PAUTA_NAO_ENCONTRADA"));
	 * 
	 * if(Objects.requireNonNull(getSessaoVotacao(pauta)).isPresent()){ throw new
	 * NotFoundException("SESSAO_JA_EXISTE"); }
	 * 
	 * criaSessaoVotacao(pauta, dataFechamento); }
	 */

	/*
	 * private void criaSessaoVotacao(Pauta pauta, LocalDateTime dataFechamento) {
	 * SessaoVotacao sessaoVotacao = SessaoVotacao.builder()
	 * .dataHoraInicio(LocalDateTime.now())
	 * .dataHoraFim(dataFechamento(dataFechamento)) .pauta(pauta) .build();
	 * 
	 * sessaoVotacaoRepository.save(sessaoVotacao); }
	 */

	/*
	 * private LocalDateTime dataFechamento(LocalDateTime dataFechamento) { return
	 * dataFechamento == null ? LocalDateTime.now().plusSeconds(tempoSessaoPadrao) :
	 * dataFechamento; }
	 */

}
