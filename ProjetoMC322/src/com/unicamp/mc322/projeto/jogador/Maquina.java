package com.unicamp.mc322.projeto.jogador;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.deckFactory.TipoDeck;
import com.unicamp.mc322.projeto.rodada.Turno;

import java.util.Random;

public class Maquina extends Jogador{
	

	public Maquina(Turno turnoInicial) {
		super(turnoInicial);
	}

	@Override
	protected ArrayList<Carta> substituirCartas(ArrayList<Carta> mao)  {
		/*
		 * Esse método deverá ser responsável por substituir as cartas 
		 */
		Random geradorAleatorio = new Random();
		int indiceCarta;
		int numCartas;
		
		numCartas = geradorAleatorio.nextInt(4);

		for(int i=0;i<numCartas;i++) {
			indiceCarta =  geradorAleatorio.nextInt(4);
			deckJogador.recolocarNoBaralho(mao.get(indiceCarta));
			mao.add(indiceCarta, deckJogador.pegarCartaAleatoriaDeck());
		}
		
		return mao;
	}

	@Override
	public TipoDeck escolhaTipoDeck() {
		Random geradorAleatorio = new Random();
		int escolha = geradorAleatorio.nextInt(TipoDeck.values().length);
		
		return TipoDeck.values()[escolha];
	}
	
	

}
