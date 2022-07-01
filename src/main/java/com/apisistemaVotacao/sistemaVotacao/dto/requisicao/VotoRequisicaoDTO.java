package com.apisistemaVotacao.sistemaVotacao.dto.requisicao;

import javax.validation.constraints.NotNull;

import com.apisistemaVotacao.sistemaVotacao.model.enums.MensagemVoto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class VotoRequisicaoDTO {

    @NotNull(message = "CPF do Usuario obrigatório.")
    private String cpfUsuario;

    @NotNull(message = "Mensagem de voto é obrigatório e precisa seguir o padrão: SIM/NAO")
    private MensagemVoto mensagemVoto;
}
