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
	
	public static void ajudaEscolhaDeck() {
		TipoDeck[] Decks = values();
		for(int i = 0; i < Decks.length; i++) {
			System.out.printf("Para criar um deck do tipo %s digite: %d\n", Decks[i].toString(), i+1);
		}
		System.out.print("Qualquer outro valor resultarao em um deck padrao do tipo Lutador\n");
	}
}
