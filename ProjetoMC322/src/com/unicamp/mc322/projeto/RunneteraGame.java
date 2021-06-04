package com.unicamp.mc322.projeto;

import com.unicamp.mc322.projeto.Interface.*;
import com.unicamp.mc322.projeto.jogador.*;
import com.unicamp.mc322.projeto.main.Campo;

public class RunneteraGame {
	private boolean exitSelected = false;
	private InterfaceTerminal interfaceTerminal = new InterfaceTerminal();
	private ModoDeJogo modoDeJogo;
	private Deck[] listaDecks;
	private Jogador player1;
	private Jogador player2;
	private Campo campoBatalha;
	
	public RunneteraGame(Deck[] listaDecks) {
		this.listaDecks = listaDecks;
	}
	
	public void runGame() {
		interfaceTerminal.GameStart();
		
		//Escolha do modo de jogo:
		modoDeJogo = interfaceTerminal.escolherModo();
		criarJogadores();
		
		//Escolha dos decks:
		player1.setDeck(interfaceTerminal.escolherDeck("Jogador 1", listaDecks));
		player2.setDeck(interfaceTerminal.escolherDeck("Jogador 2", listaDecks));
		
		//Escolha das cartas iniciais:
		player1.substituirCartas(interfaceTerminal.substituirCartasIniciais(player1.getMao()));
		player2.substituirCartas(interfaceTerminal.substituirCartasIniciais(player2.getMao()));
		
		this.campoBatalha = new Campo(player1, player2);
		
		while(!interfaceTerminal.getExitSelected()) {
			interfaceTerminal.mostrarCampo(campoBatalha);
			
		}
		interfaceTerminal.exitGame();
	}
	
	private void criarJogadores() {
		if(modoDeJogo == ModoDeJogo.HUMANOXHUMANO) {
			//player1 = new Humano()
			//player2 = new Humano()
		}
		else if(modoDeJogo == ModoDeJogo.HUMANOXCOMPUTADOR) {
			//player1 = new Humano()
			//player2 = new Computador()
		}
		else {
			//player1 = new Computador()
			//player2 = new Computador()
		}
	}
}
