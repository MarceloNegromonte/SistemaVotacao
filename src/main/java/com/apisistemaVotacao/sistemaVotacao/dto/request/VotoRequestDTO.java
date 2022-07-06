package com.apisistemaVotacao.sistemaVotacao.dto.request;

import javax.validation.constraints.NotNull;

import com.apisistemaVotacao.sistemaVotacao.model.enums.VotoStatus;

//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class VotoRequestDTO {

	//@ApiModelProperty(value = "Id sessão votacão")
	private Long sessaoVotacaoId;
	
    //@ApiModelProperty(value = "Mensagem de voto do usuario", example = "SIM")
    @NotNull(message = "Mensagem de voto é obrigatório e precisa seguir o padrão: SIM/NAO")
    private VotoStatus mensagemVoto;
}
