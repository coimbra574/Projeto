package com.unicamp.mc322.projeto.cartas;

public enum TipoCarta {
	FEITICO("Feitico"),
	SEGUIDOR("Seguidor"),
	CAMPEAO("Campeao");
	
	private String tipo;
	
	TipoCarta(String tipo){
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return tipo;
	}
}
