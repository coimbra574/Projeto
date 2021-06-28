package com.unicamp.mc322.projeto.jogador;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.Campo;
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.deckFactory.Deck;
import com.unicamp.mc322.projeto.deckFactory.TipoDeck;
import com.unicamp.mc322.projeto.turno.Turno;
import java.util.Random;

public class Maquina extends Jogador{
	Random geradorAleatorio = new Random();

	public Maquina(Turno turnoInicial) {
		super(turnoInicial);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ArrayList<Carta> substituirCartas(ArrayList<Carta> mao)  {
		/*
		 * Esse método deverá ser responsável por substituir as cartas 
		 */
		int indiceCarta;
		int numCartas;
		
		//System.out.print("Quantas cartas deseja substituir? ");//Como é uma maquina acho q nao precisa printar na tela né?
		numCartas = geradorAleatorio.nextInt(4);

		for(int i=0;i<numCartas;i++) {
			Carta novaCarta;
			
			//System.out.print("Qual carta deseja substituir? ");
			indiceCarta =  geradorAleatorio.nextInt(4);
			novaCarta = deckJogador.pegarCartaAleatoriaDeck(); // Esses três métodos a serem chamados serão da classe deck, imagino
			deckJogador.recolocarNoBaralho(mao.get(indiceCarta));
			mao.add(indiceCarta, novaCarta);
		}
		
		return mao;
	}

	@Override
	public void escolherCartaUtilizar(Campo campo) {
		/*
		 * A maquina deve escolher as cartas na mão que irá invocar ou ativar o efeito
		 */
		boolean continuar=false;
		int limite = 4;//Vou colocar um limite de vezes para que ele possa continuar pra evitar loops muito grandes
		int loops = 0;
		do {
			//System.out.println("Escolha uma carta para utilizar");
			int cartaParaJogar;
		
			cartaParaJogar =  geradorAleatorio.nextInt(mao.size());
			if(verificarCarta(mao.get(cartaParaJogar))){
				if(mao.get(cartaParaJogar).getTipo().equals("Campeão") || mao.get(cartaParaJogar).getTipo().equals("Seguidor")) {
					int posicaoNoCampo;
					posicaoNoCampo = geradorAleatorio.nextInt(6);
//					invocarCarta(mao.get(cartaParaJogar), campo, posicaoNoCampo);//como eu instancio o campo dentro do jogador??
				}else {
					ativarEfeito(mao.get(cartaParaJogar));
				}
			}
			
			if(mana>0) {
				System.out.print("Deseja continuar? ");
				continuar =  geradorAleatorio.nextBoolean();//ver aqui se da pra melhoras sla
			}
			loops+=1;
		}while(mana>0 && continuar && loops<limite);
		
	}

}
