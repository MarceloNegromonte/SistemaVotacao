package com.apisistemaVotacao.sistemaVotacao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.apisistemaVotacao.sistemaVotacao.model.enums.TipoEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "tb_usuario")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	
	@Column(name =  "Nome")
	@NotNull(message = "Insira o seu nome")
	@Size(min = 2, max = 100)
	private String nome;
	
	@Column(name = "Cpf")
	@NotNull(message = "Insira somente os numeros")
	private String cpf;
	
	@Column(name= "Tipo")
	@NotNull(message = "Usuario Administrativo ou Não")
	@Enumerated(EnumType.STRING)
	private TipoEnum tipo;
	
	@Column(name = "Email")
	@NotNull(message = "Insira email valido")
	@Email
	private String email;
	
	@Column(name = "Senha")
	@NotNull(message = "Digite a senha")
	@Size(min = 5, max = 100)
	private String senha;
	

	
}
