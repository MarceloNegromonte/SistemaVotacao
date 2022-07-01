package com.apisistemaVotacao.sistemaVotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CpfDTO {

	private Long id;
	private String cpf;
	private Boolean isValid;
	
}
