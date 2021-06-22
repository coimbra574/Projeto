package com.unicamp.mc322.projeto.deckFactory;

public class DeckFactory {
	
	public static Deck getDeck(TipoDeck tipo) {
		switch(tipo) {
		case MAGICO:
			return new DeckMagico();
		case LUTADOR:
			return new DeckLutador();
		case NAOEXISTENTE:
			System.out.println("Não existe esse tipo de Deck!!!");
			System.out.println("Por padrão será criado um deck do tipo Lutador");
			return new DeckLutador();
		}
		return null;
	}
}
