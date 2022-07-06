package com.apisistemaVotacao.sistemaVotacao.dto.request;

import java.time.LocalDateTime;

//import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder(toBuilder = true)
@Data
@EqualsAndHashCode
public class SessaoRequestDTO {

	private Long pautaID;
	
	private Integer duracao;
}
