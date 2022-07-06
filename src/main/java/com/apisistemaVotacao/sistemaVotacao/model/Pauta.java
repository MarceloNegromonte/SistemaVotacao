package com.apisistemaVotacao.sistemaVotacao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.apisistemaVotacao.sistemaVotacao.model.enums.MensagemVoto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_pauta")
public class Pauta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;

	@Column(name = "Nome")
	@Size(min = 3)
	private String nome;

	@Column(name = "Descricao")
	@Size(min = 3)
	private String descricao;
	
	@Column(name = "QtdVotosSim")
	private Integer qtdVotosSim = 0;

	@Column(name = "QtdVotosNao")
	private Integer qtdVotosNao = 0;

	@Column(name = "PercentualSim")
	private Double percentualSim = 0.00;

	@Column(name = "PercentualNao")
	private Double percentualNao = 0.00;
	
	@Column(name = "Vencedor")
	private MensagemVoto vencedor;

	@OneToOne
	private SessaoVotacao sessaVotacao;

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	public Usuario usuario;

}
