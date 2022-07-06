package com.apisistemaVotacao.sistemaVotacao.config;

/*import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;*/

/*@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
		.select()
		.apis(RequestHandlerSelectors
		.basePackage("com.apisistemaVotacao.sistemaVotacao.controller"))		
		.paths(PathSelectors.any())
		.build()
		.apiInfo(metadata())
		.useDefaultResponseMessages(false)
		.globalResponses(HttpMethod.GET, responseMensage())
		.globalResponses(HttpMethod.POST, responseMensage())
		.globalResponses(HttpMethod.PUT, responseMensage())
		.globalResponses(HttpMethod.DELETE, responseMensage());
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
	
private static List<Response> responseMensage(){
		
		return new ArrayList<Response>() {
		
			private static final long serialVersionUID = 1L;
		    {
		    	add(new ResponseBuilder().code("200")
		    	.description("Sucesso!").build());
		    	add(new ResponseBuilder().code("201")
		    	.description("Criado!").build());
		    	add(new ResponseBuilder().code("400")
		    	.description("Erro na requisição!").build());
		    	add(new ResponseBuilder().code("401")
		    	.description("Nao Autorizado!").build());
		    	add(new ResponseBuilder().code("403")
		    	.description("Proibido!").build());
		    	add(new ResponseBuilder().code("404")
		    	.description("Não Encontrado!").build());
		    	add(new ResponseBuilder().code("500")
		    	.description("Erro!").build());
		    }
		};
	}
}
*/