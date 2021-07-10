package com.unicamp.mc322.projeto.deckFactory;

public class DeckFactory {
	
	public static Deck getDeck(TipoDeck tipo) {
		switch(tipo) {
		case MAGICO:
			return new DeckMagico();
		case LUTADOR:
			return new DeckLutador();
		case NAOEXISTENTE:
			System.out.println("Foi fornecido um argumento invalido para escolha do deck");
			System.out.println("Por padrao sera criado um deck do tipo Lutador");
			return new DeckLutador();
		}
		return null;
	}
}
