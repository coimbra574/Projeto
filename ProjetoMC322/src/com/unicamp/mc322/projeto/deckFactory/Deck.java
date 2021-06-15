package com.unicamp.mc322.projeto.deckFactory;

import java.util.ArrayList;
import com.unicamp.mc322.projeto.cartas.*;
import java.util.Random;

public abstract class Deck {
	protected ArrayList<Carta> baralho = new ArrayList<Carta>();
	protected int numCartasDeck = 40;	
	
	//Deck(String tipoDeck) {
	protected Deck() {
		super();
		//baralho = contruirDeck();//Eh aqui?? ou nas subclasses?
	}
	
	protected abstract ArrayList<Carta> construirDeck();
	
	public ArrayList<Carta> obterCartasIniciais(ArrayList<Carta> mao){
		for(int i=0;i<4;i++) {
			Carta carta = baralho.get(i);
			mao.add(carta);
		}
		for(int i=0;i<4;i++) {
			Carta carta = baralho.get(i);
			baralho.remove(carta);
			numCartasDeck-=1;
		}
		return mao;
	}
	
	public Carta pegarCartaAleatoriaDeck() {
		int numero;
		Carta carta;
		
		Random geradorAleatorio = new Random();
		numero = geradorAleatorio.nextInt(numCartasDeck);
		carta = baralho.get(numero);
		baralho.remove(carta);
		numCartasDeck-=1;
		return carta;
	}
	
	public void recolocarNoBaralho(Carta carta) {
		int numero;
		
		Random geradorAleatorio = new Random();
		numero = geradorAleatorio.nextInt(numCartasDeck);
		baralho.add(numero, carta);
		numCartasDeck+=1;
	}
	
	public Carta obterCartaDeck() {
		Carta carta;
		
		carta = baralho.get(0);
		baralho.remove(carta);
		numCartasDeck-=1;
		return carta;
	}

}
