package com.unicamp.mc322.projeto.cartas;

public abstract class Carta {
	protected int custo;
	protected String nome;

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
	
	public abstract String getTipo();
	
	public void setMana(int novoCusto) {
		custo = novoCusto;
	}

	public abstract String toStringCompra();
	
	public abstract String toStringEvocada();
	
	public abstract String toStringEmCampo();
	
	public abstract String toStringDetalhes();
	
}