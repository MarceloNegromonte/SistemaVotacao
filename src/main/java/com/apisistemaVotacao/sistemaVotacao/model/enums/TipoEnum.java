package com.apisistemaVotacao.sistemaVotacao.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum TipoEnum implements GrantedAuthority {

	ROLE_ADMIN("Usuario tipo Administrador"), 
	ROLE_COPERADO("Usuario tipo Coperado");
	
	private final String label;

	TipoEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String getAuthority() {
		return null;
	}
	
}
