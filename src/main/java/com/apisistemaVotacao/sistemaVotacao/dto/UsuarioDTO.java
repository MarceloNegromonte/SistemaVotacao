package com.apisistemaVotacao.sistemaVotacao.dto;

import com.apisistemaVotacao.sistemaVotacao.model.enums.TipoEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

	private String nome;
	private String cpf;
	private TipoEnum tipo;
	
}
