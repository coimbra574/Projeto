package com.unicamp.mc322.projeto;

import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;

public class Rodada {
	private int numeroRodada;
	private Jogador jogador1, jogador2;
	private int numeroJogadorAtual;
	
	public Rodada(Jogador p1, Jogador p2) {
		numeroRodada = 1;
		this.jogador1 = p1;
		this.jogador2 = p2;
		numeroJogadorAtual = 1;  // Jogo começa com p1
	}
	
	
	public int getNumeroRodada() {
		return numeroRodada;
	}
	
	public void terminarRodada() {
		numeroRodada++;
		// Atualizar dados jogadores e do jogo no geral
	}
	
	
	public void trocarTurno() {
		jogador1.acabarTurno();
		jogador2.acabarTurno();
	}
	
	public int getNumeroJogadorAtual() {
		return numeroJogadorAtual;
	}
	
	
	public int getNumeroJogadorOponente() {
		if(numeroJogadorAtual == 1) {
			return 2;
		} else {
			return 1;
		}
	}
	
	
	public Jogador getJogador(int numeroJogador) {
		if(numeroJogador == 1) {
			return jogador1;
		}else {
			return jogador2;
		}
	}
	
	
	
}
