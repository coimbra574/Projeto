package com.unicamp.mc322.projeto;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.Interface.*;
import com.unicamp.mc322.projeto.jogador.*;
import com.unicamp.mc322.projeto.main.Campo;
import com.unicamp.mc322.projeto.turno.Turno;

public class RunneteraGame {
	private boolean exitSelected = false;
	private InterfaceTerminal interfaceTerminal = new InterfaceTerminal();
	private ModoDeJogo modoDeJogo;
	private ArrayList<Deck> listaDecks = new ArrayList<Deck>();
	private Jogador player1;
	private Jogador player2;
	private Campo campoBatalha;
	
	public RunneteraGame() {
		//this.listaDecks.add(new Deck("Demacia"));
	}
	
	public void runGame() {
		interfaceTerminal.GameStart();
		
		//Escolha do modo de jogo:
		modoDeJogo = interfaceTerminal.escolherModo();
		criarJogadores();
		
		//Escolha dos decks:
		//player1.setDeck(interfaceTerminal.escolherDeck("Jogador 1", listaDecks));
		//player2.setDeck(interfaceTerminal.escolherDeck("Jogador 2", listaDecks));
		
		//Escolha das cartas iniciais:
		//player1.substituirCartas(interfaceTerminal.substituirCartasIniciais(player1.getMao()));
		//player2.substituirCartas(interfaceTerminal.substituirCartasIniciais(player2.getMao()));
		
		this.campoBatalha = new Campo(player1, player2);
		
		InterfaceGraficaCamp compoGrafico = new InterfaceGraficaCamp();
		compoGrafico.setVisible(true);
		
		while(compoGrafico.isVisible()) {
			// Rodando o jogo
			//System.out.println("Rodando");
		}
		interfaceTerminal.exitGame();
	}
	
	private void criarJogadores() {
		if(modoDeJogo == ModoDeJogo.HUMANOXHUMANO) {
			player1 = new Humano(Turno.ATAQUE);
			player2 = new Humano(Turno.DEFESA);
		}
		else if(modoDeJogo == ModoDeJogo.HUMANOXCOMPUTADOR) {
			player1 = new Humano(Turno.ATAQUE);
			player2 = new Maquina(Turno.DEFESA);
		}
		else {
			player1 = new Maquina(Turno.ATAQUE);
			player2 = new Maquina(Turno.DEFESA);
		}
	}
}
