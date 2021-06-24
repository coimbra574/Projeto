package com.unicamp.mc322.projeto.deckFactory;

public enum TipoDeck {
	MAGICO("Magico"), 
	LUTADOR("Lutador"),
	NAOEXISTENTE("Nao existente");
	
	private String nome;
	TipoDeck(String nome) {
		this.nome = nome;
	}
	
	
	@Override
	public String toString() {
		return nome;
	}
}
