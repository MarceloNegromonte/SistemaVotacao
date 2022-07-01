package com.apisistemaVotacao.sistemaVotacao.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_sessaoVotacao")
public class SessaoVotacao {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DataHoraInicio")
	private LocalDateTime dataHoraInicio;
	
	@Column(name = "DataHoraFim")
	private LocalDateTime dataHoraFim;
	
	@Column(name = "Status")
	private boolean status;
	
	@OneToOne
	@JoinColumn(name = "id_pauta")
	private Pauta pauta;

	@OneToMany
	private List<Voto> votos;
	 
}
