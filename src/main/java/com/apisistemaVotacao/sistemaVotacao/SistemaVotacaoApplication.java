package com.apisistemaVotacao.sistemaVotacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SistemaVotacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaVotacaoApplication.class, args);
	}

}
