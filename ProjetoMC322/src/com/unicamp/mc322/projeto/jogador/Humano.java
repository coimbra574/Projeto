package com.unicamp.mc322.projeto.jogador;

import java.util.ArrayList;
import java.util.Scanner;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.deckFactory.TipoDeck;
import com.unicamp.mc322.projeto.rodada.Turno;

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
		
		while(!comando.equals("OK") && !comando.equals("ok") && !comando.equals("Ok") && !comando.equals("oK")) {
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
				return TipoDeck.NAOEXISTENTE;
			}
			
			if(escolha == 0) {
				TipoDeck.ajudaEscolhaDeck();
			}else if(escolha-1 < TipoDeck.values().length-1 && escolha-1 >= 0){
				return TipoDeck.values()[escolha-1];
			}
		}while(escolha == 0);

		return TipoDeck.NAOEXISTENTE;
	}
	
	private static void aguardarTempo() {
	    try {
	        Thread.sleep(50);
	    } catch (InterruptedException e) {
	        System.err.format("IOException: %s%n", e);
	    }
	}
	
	@Override
	public Seguidor selecionarUmaUnidadeAliada(Campo campo) {
		campo.getInterface().selecionarAliadaEvocadaOuEmCampo();
		do {
			aguardarTempo();
		}while(campo.getInterface().getAguardandoCarta() == true);

		return campo.getInterface().getCartaEscolhida();
	}
	
	@Override
	public Seguidor selecionarUmaUnidadeInimiga(Campo campo) {
		campo.getInterface().selecionarInimigoEvocadaOuEmCampo();
		do {
			aguardarTempo();
		}while(campo.getInterface().getAguardandoCarta() == true);
		
		return campo.getInterface().getCartaEscolhida();
	}
	
	@Override
	public boolean acaoRodadaCompra(Campo campo) {
		campo.getInterface().selecionarCompra();
		do {
			aguardarTempo();
		}while(campo.getInterface().getAguardandoAcao() == true);
		
		return campo.getInterface().getAcaoRealizada();
	}
	
	@Override
	public boolean acaoRodadaCompraOuAtaque(Campo campo) {
		campo.getInterface().selecionarCompraOuAtaque();
		do {
			aguardarTempo();
		}while(campo.getInterface().getAguardandoAcao() == true);
		
		return campo.getInterface().getAcaoRealizada();
	}
	
	@Override
	public boolean acaoRodadaDefesa(Campo campo) {
		campo.getInterface().selecionarDefesa();
		do {
			aguardarTempo();
		}while(campo.getInterface().getAguardandoAcao() == true);
		
		return campo.getInterface().getAcaoRealizada();
	}
}
