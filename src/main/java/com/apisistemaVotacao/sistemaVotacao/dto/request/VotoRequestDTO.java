package com.apisistemaVotacao.sistemaVotacao.dto.request;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.apisistemaVotacao.sistemaVotacao.model.enums.VotoStatus;

//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VotoRequestDTO {

	@NotNull
	private Long sessaoVotacaoId;
	
    @NotNull(message = "Voto é obrigatório e precisa seguir o padrão: SIM/NAO")
    @Enumerated
    private VotoStatus voto;
    
    @NotNull(message = "CPF obrigatorio")
    private String cpf;
}
