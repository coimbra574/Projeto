package com.unicamp.mc322.projeto;

import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;

public class Rodada {
	private int numeroRodada;
	private Jogador jogador1, jogador2;
	private numeroJogador numeroJogadorAtual;
	private TipoRodada tipo;
	
	public Rodada(Jogador p1, Jogador p2) {
		numeroRodada = 1;
		this.jogador1 = p1;
		this.jogador2 = p2;
		this.numeroJogadorAtual = numeroJogador.PLAYER1;  // Jogo começa com p1
		this.tipo = TipoRodada.COMPRA_DE_CARTAS;
	}
	
	public int getNumeroRodada() {
		return numeroRodada;
	}
	
	public TipoRodada getTipo() {
		return tipo;
	}
	
	public void mudarTipo() {
		if(tipo == TipoRodada.COMPRA_DE_CARTAS) {
			tipo = TipoRodada.ESCOLHA_DEFENSORES;
		}
		else {
			tipo = TipoRodada.COMPRA_DE_CARTAS;
		}
	}
	
	public void terminarRodada() {
		numeroRodada++;
		// Atualizar dados jogadores e do jogo no geral
	}
	
	public void finalizarTurno() {
		if(tipo == TipoRodada.ESCOLHA_DEFENSORES) {
			finalizarRodada();
			return;
		}
		if(numeroJogadorAtual == numeroJogador.PLAYER1) {
			numeroJogadorAtual = numeroJogador.PLAYER2;
		}
		else {
			numeroJogadorAtual = numeroJogador.PLAYER1;
		}
	}
	
	private void finalizarRodada() {
		jogador1.acabarRodada();
		jogador2.acabarRodada();
	}
	
	public numeroJogador getNumeroJogadorAtual() {
		return numeroJogadorAtual;
	}
	
	
	public numeroJogador getNumeroJogadorOponente() {
		if(numeroJogadorAtual == numeroJogador.PLAYER1) {
			return numeroJogador.PLAYER2;
		} else {
			return numeroJogador.PLAYER1;
		}
	}
	
	
	public Jogador getJogador(numeroJogador numeroJogador) {
		if(numeroJogador == numeroJogador.PLAYER1) {
			return jogador1;
		}else {
			return jogador2;
		}
	}
	
	
	
}
