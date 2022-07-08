package com.apisistemaVotacao.sistemaVotacao.model.enums;

public enum VotoStatus {
    SIM("SIM"),
    NAO("NAO");
	
	private final String label;
	
	VotoStatus(String label){
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}