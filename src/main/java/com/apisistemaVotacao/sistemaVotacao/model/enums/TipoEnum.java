package com.apisistemaVotacao.sistemaVotacao.model.enums;


public enum TipoEnum {

	ADMIN("Usuario tipo Administrador"), 
	COPERADO("Usuario tipo Coperado");
	
	private String label;

	private TipoEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
}
