package com.apisistemaVotacao.sistemaVotacao.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.apisistemaVotacao.sistemaVotacao.model.enums.VotoStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "sessaoVotacao")
@Entity
@Table(name = "tb_voto")
public class Voto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @NotNull(message = "CPF do eleitor obrigatório.")
    private String cpfUsuario;

    @NotNull(message = "Mensagem de voto é obrigatório e precisa seguir o padrão: SIM/NAO")
    @Column(name = "Mensagem_voto")
    @Enumerated(EnumType.STRING)
    private VotoStatus mensagemVoto;
    
    @Column(name = "data")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "id_sessao_votacao")
    private SessaoVotacao sessaoVotacao;
}
