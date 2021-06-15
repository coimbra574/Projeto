package com.unicamp.mc322.projeto.deckFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import com.unicamp.mc322.projeto.cartas.Carta;

import com.unicamp.mc322.projeto.cartas.Carta;

public class DeckMagico extends Deck{
	/*
	 * Este tipo de baralho tera 2 campeos, 18 seguidores e 20 feiticos
	 */
	private ListaCartas listaCartas = new ListaCartas();
	private Random geradorAleatorio = new Random();

	DeckMagico() {
		super();
		super.baralho = construirDeck();
	}

	@Override
	protected ArrayList<Carta> construirDeck() {
		ArrayList<Carta> baralho = new ArrayList<Carta>();
		
		for(int i = 0; i<2; i++) {
			int n = geradorAleatorio.nextInt(listaCartas.getNumCampeoes());
			baralho.add(listaCartas.getCampeao(n));
		}
		
		for(int i = 0; i<18; i++) {
			int n = geradorAleatorio.nextInt(listaCartas.getNumSeguidores());
			baralho.add(listaCartas.getSeguidores(n));
		}
		
		for(int i = 0; i<20; i++) {
			int n = geradorAleatorio.nextInt(listaCartas.getNumFeitico());
			baralho.add(listaCartas.getFeitico(n));
		}
		
		Collections.shuffle(baralho);
		return baralho;
	}

}
