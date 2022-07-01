package com.apisistemaVotacao.sistemaVotacao.dto.requisicao;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder(toBuilder = true)
@Data
@EqualsAndHashCode
public class SessaoRequisicaoDTO {

    private LocalDateTime dataFechamento;
}
