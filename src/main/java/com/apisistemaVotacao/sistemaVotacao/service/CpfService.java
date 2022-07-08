package com.apisistemaVotacao.sistemaVotacao.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.apisistemaVotacao.sistemaVotacao.config.CpfValidacao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CpfService {

	@Resource
	private CpfValidacao cpfValidacao;
	
	public boolean validarCpf(String cpf) {
		log.info("Validando CPF");
		return cpfValidacao.validarCPF(cpf).getIsValid();
	}
}
