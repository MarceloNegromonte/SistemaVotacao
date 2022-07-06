package com.apisistemaVotacao.sistemaVotacao.dto.request;

import javax.validation.constraints.NotNull;

import com.apisistemaVotacao.sistemaVotacao.model.enums.VotoStatus;

//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class VotoRequisicaoDTO {

	//@ApiModelProperty(value = "CPF do usuario", example = "12345678910")
    @NotNull(message = "CPF do Usuario obrigatório.")
    private String cpfUsuario;

    //@ApiModelProperty(value = "Mensagem de voto do usuario", example = "SIM")
    @NotNull(message = "Mensagem de voto é obrigatório e precisa seguir o padrão: SIM/NAO")
    private VotoStatus mensagemVoto;
}
