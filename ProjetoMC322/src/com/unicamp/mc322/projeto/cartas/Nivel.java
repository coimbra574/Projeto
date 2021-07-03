package com.unicamp.mc322.projeto.cartas;

public enum Nivel {
	NORMAL("normal"), SUPERIOR("superior");
	
	private String nivel;
	
	Nivel(String nivel){
		this.nivel = nivel;
	}
	
	@Override
	public String toString() {
		return nivel;
	}
	
}

