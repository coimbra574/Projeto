package com.unicamp.mc322.projeto.jogador;

import java.util.ArrayList;
import java.util.Scanner;

import com.unicamp.mc322.projeto.Campo;
import com.unicamp.mc322.projeto.Interface.InterfaceTerminal;
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.turno.Turno;
import com.unicamp.mc322.projeto.deckFactory.Deck;
import com.unicamp.mc322.projeto.deckFactory.TipoDeck;

public class Humano  extends Jogador{
	private Scanner teclado = new Scanner(System.in);

	public Humano(Turno turnoInicial) {
		super(turnoInicial);
	}

	@Override
	protected ArrayList<Carta> substituirCartas(ArrayList<Carta> mao) {
		ArrayList<Integer> listaDeSubstituicao = InterfaceTerminal.substituirCartasIniciais(mao);
		
		for(int i: listaDeSubstituicao) {
			Carta novaCarta = deckJogador.pegarCartaAleatoriaDeck();
			deckJogador.recolocarNoBaralho(mao.get(i));
			mao.add(i, novaCarta);
		}
		return mao;
	}

	@Override
	public void escolherCartaUtilizar(Campo campo) {
		/*
		 * O jogador deve escolher as cartas na mão que irá invocar ou ativar o efeito
		 * 
		 * Talvez adicionar a funcao de ativar feiti�o ou invocar seguidor/campeao aqui na hora em que escolhe? ok
		 */
		boolean continuar=false;
		do {
			
			System.out.println("Escolha uma carta para utilizar");
			int cartaParaJogar;
			do {
				cartaParaJogar=  Integer.valueOf(teclado.nextLine());
			}while(cartaParaJogar>mao.size());
			
			if(verificarCarta(mao.get(cartaParaJogar))) {
				if(mao.get(cartaParaJogar).getTipo().equals("Campeão") || mao.get(cartaParaJogar).getTipo().equals("Seguidor")) {
					int posicaoNoCampo;
					do {
						posicaoNoCampo = Integer.valueOf(teclado.nextLine());
					}while(posicaoNoCampo>6);
					//invocarCarta(mao.get(cartaParaJogar), campo, posicaoNoCampo);//como eu instancio o campo dentro do jogador??
				}else {
					ativarEfeito(mao.get(cartaParaJogar));
				}
			}else {
				System.out.println("Você não possui mana suficiente para utilizar esse carta!!!");
			}
			
			if(mana>0) {
				System.out.print("Deseja continuar? ");
				continuar =  Boolean.valueOf(teclado.nextLine());//ver aqui se da pra melhoras sla
			}
		}while(mana>0 && continuar);
		
	}

}
