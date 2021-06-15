package com.unicamp.mc322.projeto.deckFactory;

public class DeckFactory {
	public static Deck getDeck(String tipo) {
		if(tipo.equals("Magico")) {
			return new DeckMagico();
		}else if(tipo.equals("Lutador")) {
			return new DeckLutador();
		}else {
			System.out.println("Não existe esse tipo de Deck!!!");
			System.out.println("Por padrão será criado um deck do tipo Lutador");
			return new DeckLutador();
		}
	}
}
