package com.unicamp.mc322.projeto;

import com.unicamp.mc322.projeto.jogador.Jogador;

public class Rodada {
	private int numeroRodada;
	private Jogador p1, p2;
	private int numeroJogadorAtual;
	
	public Rodada(Jogador p1, Jogador p2) {
		numeroRodada = 1;
		this.p1 = p1;
		this.p2 = p2;
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
		p1.acabarTurno();
		p2.acabarTurno();
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
}
