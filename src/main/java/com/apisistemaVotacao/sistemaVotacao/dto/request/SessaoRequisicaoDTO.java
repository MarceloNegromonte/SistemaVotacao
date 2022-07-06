package com.apisistemaVotacao.sistemaVotacao.dto.request;

import java.time.LocalDateTime;

//import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder(toBuilder = true)
@Data
@EqualsAndHashCode
public class SessaoRequisicaoDTO {

	//@ApiModelProperty(value = "Data/Hora de fechamento de votação", example = "2021-07-07T18:20:21.223Z")
    private LocalDateTime dataFechamento;
}
