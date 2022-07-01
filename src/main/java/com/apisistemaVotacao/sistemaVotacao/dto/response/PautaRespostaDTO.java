package com.apisistemaVotacao.sistemaVotacao.dto.response;

import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class PautaRespostaDTO {

    private String id;

    private String nome;
    
    private String descricao;

    private Map<String, Long> resultado;
}
