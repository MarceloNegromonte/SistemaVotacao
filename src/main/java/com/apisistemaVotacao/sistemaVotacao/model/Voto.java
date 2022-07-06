package com.apisistemaVotacao.sistemaVotacao.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.apisistemaVotacao.sistemaVotacao.model.enums.VotoStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "tb_voto")
public class Voto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	
    @Column(name = "VotoInstant")
    private Instant votoInstant;
    
    @Column(name = "Voto")
    private VotoStatus voto;

    @ManyToOne
    @JsonIgnoreProperties("votos")
    @JoinColumn(name = "id_sessao_votacao")
    private SessaoVotacao sessaoVotacao;
}
