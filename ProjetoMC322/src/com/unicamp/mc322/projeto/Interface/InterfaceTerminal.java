package com.unicamp.mc322.projeto.Interface;

import java.util.ArrayList;
import java.util.Scanner;

import com.unicamp.mc322.projeto.ModoDeJogo;
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.deckFactory.Deck;
import com.unicamp.mc322.projeto.deckFactory.TipoDeck;

public class InterfaceTerminal {
	private boolean exitSelected = false;
	private static Scanner keyboard = new Scanner(System.in);
	
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
			if(comando.equals("1")) {
				return ModoDeJogo.HUMANOXHUMANO;
			}
			else if(comando.equals("2")) {
				return ModoDeJogo.HUMANOXCOMPUTADOR;
			}
			else if(comando.equals("3")) {
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
	
	public static ArrayList<Integer> substituirCartasIniciais(ArrayList<Carta> listaCartas) {
		ArrayList<Integer> comandosDigitados = new ArrayList<Integer>();
		String comando;
		Integer comandoInt=-1;
		boolean ehInteiro = true;
		
		System.out.println("Deseja substituir algumas das seguintes cartas iniciais?");
		for(int i = 0; i < listaCartas.size(); i++) {
			System.out.printf("Digite [%d] - Para substituir a carta:", i+1);
			System.out.println(listaCartas.get(i).toString());
		}
		System.out.println("Digite um numero ou [OK] para confirmar:");
		comando = keyboard.nextLine();
		
		while(!comando.equals("OK")) {
			ehInteiro = true;
			try {
				comandoInt = Integer.parseInt(comando)-1;
			} catch (NumberFormatException ex){
				ehInteiro = false;
			}
			
			if(ehInteiro == true) {
				comandosDigitados = realizarTroca(listaCartas, comandosDigitados, comandoInt);
			} else {
				System.out.println("Entrada invalida");
			}
			
			comando = keyboard.nextLine();
		}
		return comandosDigitados;
	}
	
	
	private static ArrayList<Integer> realizarTroca(ArrayList<Carta> listaCartas, ArrayList<Integer> comandosDigitados, int comandoInt){
		if(comandoInt >= 0 && comandoInt < listaCartas.size()) {
			if(comandosDigitados.contains(comandoInt)) {
				comandosDigitados.remove(comandoInt);
				System.out.printf("Carta [%d] nao sera trocada.\n", comandoInt+1);
			}
			else {
				comandosDigitados.add(comandoInt);
				System.out.printf("Carta [%d] sera trocada.\n", comandoInt+1);
			}
		}
		else {
			System.out.println("Entrada Invalida!");
		}
		return comandosDigitados;
	}
	
	
	public static TipoDeck EscolhaTipoDeck() {
		int escolha = -1;
		TipoDeck[] Decks = TipoDeck.values();
		boolean continuar = true;
		while(continuar) {
			continuar = false;
			System.out.println("Qual deck escolhera? ");
			System.out.println("Digite 0 para ajuda (Ver decks disponiveis)");
			
			try {
				escolha = Integer.valueOf(keyboard.nextLine());
			} catch (NumberFormatException ex) {
				System.out.println("Deck não encontrado. Tente novamente\n");
				continuar = true;
			}
			
			if(escolha == 0) {
				ajudaEscolhaDeck(Decks);
				escolha = -1;
				continuar = true;
			}else if(escolha-1 < Decks.length && escolha-1 >= 0){
				return Decks[escolha-1];
			}
		}
		System.out.println("Foi fornecido um argumento invalido para escolha do deck");
		System.out.println("Por padrao sera criado um deck do tipo Lutador");
		return TipoDeck.LUTADOR;
	}
 
	
	private static void ajudaEscolhaDeck(TipoDeck[] Decks) {
		for(int i = 0; i < Decks.length; i++) {
			System.out.printf("Para criar um deck do tipo %s digite: %d\n", Decks[i].toString(), i+1);
		}
		System.out.print("Qualquer outro valor resultarao em um deck padrao do tipo Lutador\n");
	}
	

}
