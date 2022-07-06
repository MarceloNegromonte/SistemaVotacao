package com.apisistemaVotacao.sistemaVotacao.dto.request;

import javax.validation.constraints.NotNull;

//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class PautaRequisicaoDTO {
    
	//@ApiModelProperty(value = "Nome da pauta a ser votada")
    @NotNull(message = "Nome é obrigatótio")
    private String nome;
    
	//@ApiModelProperty(value = "Descricao da pauta a ser votada")
    @NotNull(message = "Nome é obrigatótio")
    private String descricao;
}
