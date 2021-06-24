package com.unicamp.mc322.projeto.Interface;

import java.util.ArrayList;
import java.util.Scanner;

import com.unicamp.mc322.projeto.ModoDeJogo;
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.deckFactory.Deck;
import com.unicamp.mc322.projeto.deckFactory.TipoDeck;

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
	
	public void unidadesEvocadas() {
		
	}
	
	public static TipoDeck EscolhaTipoDeck() {
		int escolha;
		TipoDeck[] Decks = TipoDeck.values();
		Scanner teclado = new Scanner(System.in);
		boolean continuar = true;
		while(continuar) {
			continuar = false;
			System.out.println("Qual deck escolhera? ");
			System.out.println("Digite 0 para ajuda (Ver decks disponiveis)");
			escolha = Integer.valueOf(teclado.nextLine());
			if(escolha == 0) {
				ajudaEscolhaDeck(Decks);
				continuar = true;
			}else if(escolha-1 < Decks.length){
				teclado.close();
				return Decks[escolha-1];
			}
		}
		System.out.println("Foi fornecido um argumento invalido para escolha do deck");
		System.out.println("Por padrao sera criado um deck do tipo Lutador");
		teclado.close();
		return TipoDeck.LUTADOR;
	}
		 
	private static void ajudaEscolhaDeck(TipoDeck[] Decks) {
		for(int i = 0; i < Decks.length; i++) {
			System.out.printf("Para criar um deck do tipo %s digite: %d\n", Decks[i].toString(), i+1);
		}
		System.out.print("Qualquer outro valor resultarao em um deck padrao do tipo Lutador\n");
	}
	

}
