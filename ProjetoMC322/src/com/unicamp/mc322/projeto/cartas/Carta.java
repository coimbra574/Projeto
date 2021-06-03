package com.unicamp.mc322.projeto.cartas;

public abstract class Carta {
	private int custo;
	private String nome;

	public Carta(String nome, int custo) {
		this.nome = nome;
		this.custo = custo;
	}

	public String getNome() {
		return nome;
	}
	
	public int getMana() {
		return custo;
	}
	
	public void setMana(int novoCusto) {
		custo = novoCusto;
	}
	
}