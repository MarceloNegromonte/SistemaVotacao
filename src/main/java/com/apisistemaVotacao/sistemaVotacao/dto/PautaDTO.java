package com.apisistemaVotacao.sistemaVotacao.dto;

import com.apisistemaVotacao.sistemaVotacao.model.Pauta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PautaDTO {

	private Long id;
	private String nome;
	private String descricao;
	
	public static PautaDTO toDTO(Pauta pauta) {
		return PautaDTO.builder()
				.id(pauta.getId())
				.nome(pauta.getNome())
				.descricao(pauta.getDescricao())
				.build();
	}
	
}
