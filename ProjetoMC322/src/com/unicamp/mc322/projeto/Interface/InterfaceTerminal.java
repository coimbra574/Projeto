package com.unicamp.mc322.projeto.Interface;

import java.util.ArrayList;
import java.util.Scanner;

import com.unicamp.mc322.projeto.ModoDeJogo;
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.jogador.Deck;

public class InterfaceTerminal {
	private boolean exitSelected = false;
	private Scanner keyboard = new Scanner(System.in);
	
	public void GameStart() {
		System.out.println("Bem Vindo ao Jogo!");
	}
	
	public boolean getExitSelected() {
		return exitSelected;
	}
	
	public ModoDeJogo escolherModo() {
		while(true) {
			System.out.println("Escolha um modo de jogo:");
			System.out.println("Digite [1] - Humano vs Humano");
			System.out.println("Digite [2] - Humano vs Computador");
			System.out.println("Digite [3] - Computador vs Computador");
			String comando = keyboard.nextLine();
			if(comando == "1") {
				return ModoDeJogo.HUMANOXHUMANO;
			}
			else if(comando == "2") {
				return ModoDeJogo.HUMANOXCOMPUTADOR;
			}
			else if(comando == "3") {
				return ModoDeJogo.COMPUTADORXCOMPUTADOR;
			}
			else {
				System.out.println("Entrada Invalida!");
			}
		}
	}

	public void exitGame() {
		System.out.println("Fim de Jogo!");
		
	}

	public Deck escolherDeck(String nomePlayer, Deck[] listaDecks) {
		while(true) {
			System.out.printf("Escolha um deck para o %s\n", nomePlayer);
			for(int i = 0; i < listaDecks.length; i++) {
				System.out.printf("Digite [%d] - Para o Deck:\n", i+1);
				System.out.println(listaDecks[i].toString());
				System.out.println();
			}
			String comando = keyboard.nextLine();
			if(Integer.parseInt(comando) >= 1 && Integer.parseInt(comando) <= listaDecks.length) {
				return listaDecks[Integer.parseInt(comando)-1];
			}
		}
	}
	
	public ArrayList<Carta> substituirCartasIniciais(ArrayList<Carta> listaCartas) {
		ArrayList<Integer> comandosDigitados = new ArrayList<Integer>();
		ArrayList<Carta> listaDeSubstituicao = new ArrayList<Carta>();
		String comando;
		
		System.out.println("Deseja substituir algumas das seguintes cartas iniciais?");
		for(int i = 0; i < listaCartas.size(); i++) {
			System.out.printf("Digite [%d] - Para substituir a carta:", i+1);
			System.out.println(listaCartas.get(i).toString());
		}
		do {
			System.out.println("Digite um numero ou [OK] para confirmar:");
			comando = keyboard.nextLine();
			if(Integer.parseInt(comando) >= 1 && Integer.parseInt(comando) <= listaCartas.size()) {
				if(comandosDigitados.contains(Integer.parseInt(comando)-1)) {
					comandosDigitados.remove(Integer.parseInt(comando)-1);
					System.out.printf("Carta [%d] nao sera trocada.\n", Integer.parseInt(comando));
				}
				else {
					comandosDigitados.add(Integer.parseInt(comando)-1, Integer.parseInt(comando)-1);
					System.out.printf("Carta [%d] sera trocada.\n", Integer.parseInt(comando));
				}
			}
			else if(!comando.equals("OK")){
				System.out.println("Entrada Invalida!");
			}
		}while(!comando.equals("OK"));
		
		for(int i: comandosDigitados) {
			listaDeSubstituicao.add(listaCartas.get(i));
		}
		return listaDeSubstituicao;
	}

}
