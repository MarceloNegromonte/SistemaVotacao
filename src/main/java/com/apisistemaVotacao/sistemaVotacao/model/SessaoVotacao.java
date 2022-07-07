package com.apisistemaVotacao.sistemaVotacao.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.apisistemaVotacao.sistemaVotacao.model.dataPauta.DataPauta;
import com.apisistemaVotacao.sistemaVotacao.model.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "tb_sessaoVotacao")
public class SessaoVotacao extends DataPauta {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Duracao")
	private Integer duracao;
	
	@Column(name = "Fechado")
	private Instant fechado;
	
	@OneToOne
	@JoinColumn(name = "id_pauta")
	private Pauta pauta;

	@JsonIgnoreProperties("sessaoVotacao")
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sessaoVotacao", cascade = CascadeType.ALL)
	private List<Voto> votos = new ArrayList<>();
	
	public boolean fechada() {
		return this.getFechado() != null && this.getFechado().isBefore(Instant.now());
	}
	
	public boolean aberta() {
		return getPauta().getStatus().equals(StatusEnum.valueOf("ABERTA"));
	}
	 
}
