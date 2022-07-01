package com.apisistemaVotacao.sistemaVotacao.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.apisistemaVotacao.sistemaVotacao.config.CpfValidacao;

@Service
public class CpfService {

	@Resource
	private CpfValidacao cpfValidacao;
	
	public boolean validarCpf(String cpf) {
		return cpfValidacao.validarCPF(cpf).getIsValid();
	}
}
