package com.apisistemaVotacao.sistemaVotacao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.apisistemaVotacao.sistemaVotacao"))		
		.paths(PathSelectors.ant("/**"))
		.build()
		.apiInfo(metadata());
	}
	
public static ApiInfo metadata() {
		
		return new ApiInfoBuilder()
		.title("API - Sistema Votacao")		
		.description("Projeto API Spring - Sistema Votacao")
		.version("1.0.0")
		.license("Apache License Version 2.0")
		.licenseUrl("https://github.com/MarceloNegromonte")
		.contact(contact())
		.build();
	}

	private static Contact contact() {
	
		return new Contact("Marcelo Negromonte","github.com/MarceloNegromonte","mjncavalcante@gmail.com");
	
	}
	
}