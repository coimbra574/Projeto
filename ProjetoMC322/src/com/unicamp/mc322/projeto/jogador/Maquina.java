package com.unicamp.mc322.projeto.jogador;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.deckFactory.TipoDeck;
import com.unicamp.mc322.projeto.rodada.Turno;

import java.util.Random;

public class Maquina extends Jogador{
	

	public Maquina(Turno turnoInicial) {
		super(turnoInicial);
	}
	
	private static void aguardarTempo() {
	    try {
	        Thread.sleep(300);
	    } catch (InterruptedException e) {
	        System.err.format("IOException: %s%n", e);
	    }
	}

	@Override
	protected ArrayList<Carta> substituirCartas(ArrayList<Carta> mao)  {
		/*
		 * Esse método deverá ser responsável por substituir as cartas 
		 */
		Random geradorAleatorio = new Random();
		int indiceCarta;
		int numCartas;
		
		numCartas = geradorAleatorio.nextInt(4);

		for(int i=0;i<numCartas;i++) {
			indiceCarta =  geradorAleatorio.nextInt(4);
			deckJogador.recolocarNoBaralho(mao.get(indiceCarta));
			mao.add(indiceCarta, deckJogador.pegarCartaAleatoriaDeck());
		}
		
		return mao;
	}

	@Override
	public TipoDeck escolhaTipoDeck() {
		Random geradorAleatorio = new Random();
		int escolha = geradorAleatorio.nextInt(TipoDeck.values().length-1);
		
		return TipoDeck.values()[escolha];
	}
	
	@Override
	public Seguidor selecionarUmaUnidadeAliada(Campo campo) {
		aguardarTempo();
		
		// Faz uma lista de todas as possibilidades de escolha e sorteia a escolhida
		ArrayList<Seguidor> cartas = new ArrayList<Seguidor>();
		Random geradorAleatorio = new Random();
		
		for(Seguidor carta: emCampo) {
			if(carta != null) {
				cartas.add(carta);
			}
		}
		for(Seguidor carta: evocadas) {
			if(carta != null) {
				cartas.add(carta);
			}
		}

		int escolha = geradorAleatorio.nextInt(cartas.size());
		
		return cartas.get(escolha);
	}
	
	@Override
	public Seguidor selecionarUmaUnidadeInimiga(Campo campo) {
		aguardarTempo();
		
		// Faz uma lista de todas as possibilidades de escolha e sorteia a escolhida
		ArrayList<Seguidor> cartas = new ArrayList<Seguidor>();
		Random geradorAleatorio = new Random();
		
		for(Seguidor carta: campo.getInimigo().getEmCampo()) {
			if(carta != null) {
				cartas.add(carta);
			}
		}
		for(Seguidor carta: campo.getInimigo().getEvocadas()) {
			if(carta != null) {
				cartas.add(carta);
			}
		}

		int escolha = geradorAleatorio.nextInt(cartas.size());
		
		return cartas.get(escolha);
	}
	
	@Override
	public boolean acaoRodadaCompra(Campo campo) {
		aguardarTempo();
		
		// Se possivel compra carta, do contrario passa a vez.
		for(int i = 0; i < mao.size(); i++) {
			// Se a compra foi bem sucedida, finaliza o turno.
			if(verificarCarta(mao.get(i), campo)) {
				comprarCarta(i, campo);
				return true;
			}
		}
		
		// Se nao atacou ou comprou carta, passa a vez.
		return false;
	}
	
	@Override
	public boolean acaoRodadaCompraOuAtaque(Campo campo) {
		aguardarTempo();
		
		// Se possivel compra carta, do contrario ataca se tiver carta para atacar, do contrario passa a vez.
		for(int i = 0; i < mao.size(); i++) {
			// Se a compra foi bem sucedida, finaliza o turno.
			if(verificarCarta(mao.get(i), campo)) {
				comprarCarta(i, campo);
				return true;
			}
		}
		// Se eh atacante, ataca com todas as cartas disponiveis.
		if(evocadas.size() != 0) {
			for(int i = 0; i < evocadas.size(); i++) {
				emCampo.add(i, evocadas.get(i));
			}
			evocadas.clear();
			campo.getRodada().mudarTipo();
			return true;
		}
		
		// Se nao atacou ou comprou carta, passa a vez.
		return false;
	}
	
	@Override
	public boolean acaoRodadaDefesa(Campo campo) {
		aguardarTempo();
		campo.getRodada().mudarTipo();
		
		// Se possivel se defende, do contrario passa a vez
		if(evocadas.size() != 0) {
			ArrayList<Seguidor> emCampoInimigo = campo.getInimigo().getEmCampo();
			for(int i = 0; i < emCampoInimigo.size(); i++) {
				if(evocadas.size() != 0 && emCampoInimigo.get(i) != null) {
					emCampo.add(i, evocadas.get(0));
					evocadas.remove(0);
				}
			}
			return true;
		}
		return false;
	}

}
