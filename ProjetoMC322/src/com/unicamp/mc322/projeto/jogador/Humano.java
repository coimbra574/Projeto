package com.unicamp.mc322.projeto.jogador;

import java.util.ArrayList;
import java.util.Scanner;

import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.deckFactory.TipoDeck;
import com.unicamp.mc322.projeto.turno.Turno;

public class Humano  extends Jogador{

	public Humano(Turno turnoInicial) {
		super(turnoInicial);
	}

	@Override
	protected ArrayList<Carta> substituirCartas(ArrayList<Carta> mao) {
		ArrayList<Integer> comandosDigitados = new ArrayList<Integer>();
		Scanner keyboard = new Scanner(System.in);
		String comando;
		Integer comandoInt=-1;
		boolean ehInteiro = true;
		
		System.out.println("Deseja substituir algumas das seguintes cartas iniciais?");
		for(int i = 0; i < mao.size(); i++) {
			System.out.printf("Digite [%d] - Para substituir a carta:", i+1);
			System.out.println(mao.get(i).toString());
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
				comandosDigitados = realizarTroca(mao, comandosDigitados, comandoInt);
			} else {
				System.out.println("Entrada invalida");
			}
			
			comando = keyboard.nextLine();
		}
		
		for(int i: comandosDigitados) {
			Carta novaCarta = deckJogador.pegarCartaAleatoriaDeck();
			deckJogador.recolocarNoBaralho(mao.get(i));
			mao.remove(i);
			mao.add(i, novaCarta);
		}
		
		return mao;
	}

	private ArrayList<Integer> realizarTroca(ArrayList<Carta> listaCartas, ArrayList<Integer> comandosDigitados, int comandoInt){
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
	
	@Override
	public TipoDeck escolhaTipoDeck() {
		Scanner keyboard = new Scanner(System.in);
		int escolha;
		
		do{
			System.out.println("Qual deck escolhera? ");
			System.out.println("Digite 0 para ajuda (Ver decks disponiveis)");
			
			try {
				escolha = Integer.valueOf(keyboard.nextLine());
			} catch (NumberFormatException ex) {
				System.out.println("Deck não encontrado. Tente novamente\n");
				escolha = 0;
			}
			
			if(escolha == 0) {
				TipoDeck.ajudaEscolhaDeck();
			}else if(escolha-1 < TipoDeck.values().length && escolha-1 >= 0){
				return TipoDeck.values()[escolha-1];
			}
		}while(escolha == 0);
		
		System.out.println("Foi fornecido um argumento invalido para escolha do deck");
		System.out.println("Por padrao sera criado um deck do tipo Lutador");
		return TipoDeck.LUTADOR;
	}

}
