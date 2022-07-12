package com.apisistemaVotacao.sistemaVotacao.service;

import javax.annotation.Resource;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.apisistemaVotacao.sistemaVotacao.config.CpfValidacao;
import com.apisistemaVotacao.sistemaVotacao.dto.CpfDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CpfService {

	@Resource
	private CpfValidacao cpfValidacao;

	@Retryable(maxAttempts = 3, backoff = @Backoff(2500))
	public boolean validarCpf(String cpf) {
		log.info("Validando CPF");
		return cpfValidacao.validarCPF(cpf).getIsValid();
	}
	
    @Recover
    public boolean salvarCpf(String cpf){

        CpfDTO cpfDto = CpfDTO.builder()
                .cpf(cpf)
                .isValid(true)
                .build();

        log.info("O servico nao pode ser consultado");
        return cpfDto.getIsValid();
    }
}
