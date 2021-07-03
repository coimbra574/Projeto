package com.unicamp.mc322.projeto.cartas.tracos;

public enum TipoTraco {
	ELUSIVO("Elusivo"),
	FURIA("Furia"),
	ATAQUEDUPLO("Ataque Duplo"),
	SEMTRACO("Sem traço");
	
	private String nome;
	
	TipoTraco(String nome) {
		this.nome = nome;
	}
	
	   @Override
	   public String toString() {
	       return nome;
	   }
}
