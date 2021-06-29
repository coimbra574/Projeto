package com.unicamp.mc322.projeto;

import java.util.ArrayList;
import java.util.Random;

import com.unicamp.mc322.projeto.Interface.*;
import com.unicamp.mc322.projeto.deckFactory.Deck;
import com.unicamp.mc322.projeto.jogador.*;
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
		
		this.campoBatalha = new Campo(player1, player2);
		
		System.out.println("Jogo Iniciado!");
	}
	
	private void criarJogadores() {
		Turno turno[] = turnoInicial();
		if(modoDeJogo == ModoDeJogo.HUMANOXHUMANO) {
			player1 = new Humano(turno[0]);
			player2 = new Humano(turno[1]);
		}
		else if(modoDeJogo == ModoDeJogo.HUMANOXCOMPUTADOR) {
			player1 = new Humano(turno[0]);
			player2 = new Maquina(turno[1]);
		}
		else {
			player1 = new Maquina(turno[0]);
			player2 = new Maquina(turno[1]);
		}
	}
	
	private Turno[] turnoInicial() {
		Random geradorAleatorio = new Random();
		Turno[] turno = new Turno[2];
		int aleatorio = geradorAleatorio.nextInt(100);
		if(aleatorio >= 50) {
			turno[0] = Turno.ATAQUE;
			turno[1] = Turno.DEFESA;
			return turno;
		}
		turno[0] = Turno.DEFESA;
		turno[1] = Turno.ATAQUE;
		return turno;
	}
}
