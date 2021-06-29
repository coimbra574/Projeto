package com.unicamp.mc322.projeto.cartas;

public abstract class Carta {
	protected int custo;
	protected String nome;
	protected TipoCarta tipo;

	public Carta(String nome, int custo, TipoCarta tipo) {
		this.nome = nome;
		this.custo = custo;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}
	
	public int getMana() {
		return custo;
	}
	
	public TipoCarta getTipo() {
		return tipo;
	}
	
	public void setMana(int novoCusto) {
		custo = novoCusto;
	}

	public abstract String toStringCompra();
	
	public abstract String toStringDetalhes();
	
}