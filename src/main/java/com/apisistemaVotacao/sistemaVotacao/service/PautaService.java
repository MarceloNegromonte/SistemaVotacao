package com.apisistemaVotacao.sistemaVotacao.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.apisistemaVotacao.sistemaVotacao.model.Pauta;
import com.apisistemaVotacao.sistemaVotacao.model.enums.StatusEnum;
import com.apisistemaVotacao.sistemaVotacao.model.enums.VotoStatus;
import com.apisistemaVotacao.sistemaVotacao.repository.PautaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PautaService {

	private final PautaRepository pautaRepository;
	
	@Autowired
	public PautaService(PautaRepository pautaRepository) {
		this.pautaRepository = pautaRepository;
	}
	
    @Transactional
	public List<Pauta> buscarTodasPautas(){
		log.info("Buscando todas as pautas");
		return pautaRepository.findAll();
	}

    @Transactional
    public Pauta buscarPautaPeloID(Long id){
		log.info("Buscando pauta pelo Id {}", id);
        return pautaRepository.findById(id).orElse(null);
    }
	
	@Transactional
	public Pauta criarPauta(Pauta estado) {
		estado.setStatus(StatusEnum.ABERTA);
		log.info("Salvando pauta");
		return pautaRepository.save(estado);
	}
	
	@Transactional
	public void mudancaStatus(Pauta estado) {
		estado.setStatus(StatusEnum.FECHADA);
		log.info("Salvando mudanca de pauta");
		pautaRepository.save(estado);
	}
	
	@Transactional
	public void definirVencedor(Pauta pauta) {
		log.info("Informando vencedor da pauta");
		if (pauta.getPercentualSim() > pauta.getPercentualNao()){

            pauta.setVencedor(VotoStatus.SIM.getLabel());
        }else if(pauta.getPercentualSim() < pauta.getPercentualNao()){

            pauta.setVencedor(VotoStatus.NAO.getLabel());
        }else {

            pauta.setVencedor("EMPATE");
        }
	}
    
	@Transactional
	public void definirPercentual(Pauta pauta) {
        log.info("Definindo a porcentagem");
		pauta.setPercentualSim(Precision.round(((
                Double.valueOf(pauta.getQtdVotosSim())/ pauta.getQtdVotos())*100), 2));
        pauta.setPercentualNao(Precision.round(((
                Double.valueOf(pauta.getQtdVotosNao())/ pauta.getQtdVotos())*100), 2));
    }	
    
}


