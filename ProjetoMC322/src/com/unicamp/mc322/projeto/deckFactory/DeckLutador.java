package com.unicamp.mc322.projeto.deckFactory;

import java.util.ArrayList;
import com.unicamp.mc322.projeto.cartas.Carta;
import java.util.Collections;
import java.util.Random;

public class DeckLutador extends Deck{
	/*
	 * Baralho do tipo lutador tera 4 campeoes, 26 seguidores e 10 feiticos
	 */
	
	private ListaCartas listaCartas = new ListaCartas();
	private Random geradorAleatorio = new Random();
	
	DeckLutador() {
		super();
		super.baralho = construirDeck();
	}

	@Override
	protected ArrayList<Carta> construirDeck() {
		ArrayList<Carta> baralho = new ArrayList<Carta>();
		
		for(int i = 0; i<4; i++) {
			int n = geradorAleatorio.nextInt(listaCartas.getNumCampeoes());
			baralho.add(listaCartas.getCampeao(n));
		}
		
		for(int i = 0; i<26; i++) {
			int n = geradorAleatorio.nextInt(listaCartas.getNumSeguidores());
			baralho.add(listaCartas.getSeguidores(n));
		}
		
		for(int i = 0; i<10; i++) {
			int n = geradorAleatorio.nextInt(listaCartas.getNumFeitico());
			baralho.add(listaCartas.getFeitico(n));
		}
		
		Collections.shuffle(baralho);
		return baralho;
	}

}
