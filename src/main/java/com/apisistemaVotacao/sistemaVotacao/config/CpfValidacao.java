package com.apisistemaVotacao.sistemaVotacao.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apisistemaVotacao.sistemaVotacao.dto.CpfDTO;

@FeignClient(name = "CpfValidacao", url = "https://cpf-api-almfelipe.herokuapp.com")
public interface CpfValidacao {
	
	@RequestMapping(method = RequestMethod.GET, value = "/cpf/{cpf}")
	CpfDTO validarCPF(@PathVariable String cpf);
}
