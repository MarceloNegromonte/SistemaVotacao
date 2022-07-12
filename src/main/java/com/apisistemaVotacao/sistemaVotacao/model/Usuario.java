package com.apisistemaVotacao.sistemaVotacao.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.apisistemaVotacao.sistemaVotacao.model.dataPauta.DataPauta;
import com.apisistemaVotacao.sistemaVotacao.model.enums.TipoEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class Usuario extends DataPauta implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	
	@Column(name =  "Nome")
	@NotNull(message = "Insira o seu nome")
	@Size(min = 2, max = 100)
	private String nome;
	
	@Column(name = "Cpf", unique = true)
	@NotNull(message = "Insira somente os numeros")
	private String cpf;
	
	@Column(name= "Tipo")
	@NotNull(message = "Usuario Administrativo ou Não")
	@Enumerated(EnumType.STRING)
	private TipoEnum tipo;
	
	@Column(name = "Email")
	@NotNull(message = "Insira email valido")
	private String email;
	
	@Column(name = "Senha")
	@NotNull(message = "Digite a senha")
	@Size(min = 5, max = 100)
	private String senha;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)     
	@JsonIgnoreProperties("usuario")     
	private List<Voto> listaVoto = new ArrayList<>();
		
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(tipo.name()));
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
