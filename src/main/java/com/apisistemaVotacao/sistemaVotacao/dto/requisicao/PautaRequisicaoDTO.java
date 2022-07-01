package com.apisistemaVotacao.sistemaVotacao.dto.requisicao;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class PautaRequisicaoDTO {
    
    @NotNull(message = "Nome é obrigatótio")
    private String nome;
    
    @NotNull(message = "Nome é obrigatótio")
    private String descricao;
}
