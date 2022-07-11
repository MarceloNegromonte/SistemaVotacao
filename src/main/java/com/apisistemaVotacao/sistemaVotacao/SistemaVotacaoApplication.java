package com.apisistemaVotacao.sistemaVotacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
@EnableScheduling
@EnableRetry
@EnableCaching
public class SistemaVotacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaVotacaoApplication.class, args);
	}

}
