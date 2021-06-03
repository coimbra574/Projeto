package com.unicamp.mc322.projeto.main;

public class Rodada {
	private int numeroRodada;
	
	public Rodada() {
		numeroRodada = 1;
	}
	
	public void incrementarRodada() {
		numeroRodada++;
	}
	
	public int getRodada() {
		return numeroRodada;
	}
}
