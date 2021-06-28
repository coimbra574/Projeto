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
	private boolean acaoJogador1;
	private boolean acaoJogador2;
	
	public Rodada(Jogador p1, Jogador p2) {
		numeroRodada = 1;
		this.jogador1 = p1;
		this.jogador2 = p2;
		this.numeroJogadorAtual = numeroJogador.PLAYER1;  // Jogo começa com p1
		this.tipo = TipoRodada.COMPRA_DE_CARTAS;
		this.acaoJogador1 = true;
		this.acaoJogador2 = true;
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
		else if(tipo == TipoRodada.ESCOLHA_DEFENSORES){
			tipo = TipoRodada.BATALHA;
		}
		else {
			tipo = TipoRodada.COMPRA_DE_CARTAS;
		}
	}
	
	public void finalizarTurno(boolean realizouAcao) {
		if(tipo == TipoRodada.BATALHA) {
			//combate
			finalizarRodada();
			return;
		}
		if(numeroJogadorAtual == numeroJogador.PLAYER1) {
			acaoJogador1 = realizouAcao;
			numeroJogadorAtual = numeroJogador.PLAYER2;
		}
		else {
			acaoJogador2 = realizouAcao;
			numeroJogadorAtual = numeroJogador.PLAYER1;
		}
		if(!acaoJogador1 && !acaoJogador2) {
			finalizarRodada();
			return;
		}
	}
	
	private void finalizarRodada() {
		this.tipo = TipoRodada.COMPRA_DE_CARTAS;
		numeroRodada++;
		jogador1.acabarRodada();
		jogador1.atualizarMana(numeroRodada);
		jogador2.acabarRodada();
		jogador2.atualizarMana(numeroRodada);
		this.acaoJogador1 = true;
		this.acaoJogador2 = true;
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
