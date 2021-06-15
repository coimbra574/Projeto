package com.unicamp.mc322.projeto.jogador;

import java.util.ArrayList;
import com.unicamp.mc322.projeto.cartas.*;
import java.util.Random;

public class Deck {
	private int numCartasDeck = 40;
	private ArrayList<Carta> baralho = new ArrayList<Carta>();
	private String tipoDeck;//Não sei se é necessário, acho q depende da nossa implementação se vamos usar mais cartas do q as recomendadas e tals
	
	
	Deck(String tipoDeck) {
		super();
		this.tipoDeck = tipoDeck;
		contruirDeck(;)//deckFactory
	}
	
	private void contruirDeck() {
		//por enquanto vou deixar como stub, pois não sei como fazer isso de deck factory. Além de precisar das cartas para contruir a carta
	}
	
	ArrayList<Carta> obterCartasIniciais(ArrayList<Carta> mao){
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
	
	Carta pegarCartaAleatoriaDeck() {
		int numero;
		Carta carta;
		
		Random geradorAleatorio = new Random();
		numero = geradorAleatorio.nextInt(numCartasDeck);
		carta = baralho.get(numero);
		baralho.remove(carta);
		numCartasDeck-=1;
		return carta;
	}
	
	void recolocarNoBaralho(Carta carta) {
		int numero;
		
		Random geradorAleatorio = new Random();
		numero = geradorAleatorio.nextInt(numCartasDeck);
		baralho.add(numero, carta);
		numCartasDeck+=1;
	}
	
	Carta obterCartaDeck() {
		Carta carta;
		
		carta = baralho.get(0);
		baralho.remove(carta);
		numCartasDeck-=1;
		return carta;
	}

}
