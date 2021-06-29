package com.unicamp.mc322.projeto;

import com.unicamp.mc322.projeto.jogador.Jogador;
import com.unicamp.mc322.projeto.turno.Turno;

public class Rodada {
	private int numeroRodada;
	private Jogador jogador1, jogador2;
	private NumeroJogador numeroJogadorAtual;
	private TipoRodada tipo;
	private boolean acaoJogador1;
	private boolean acaoJogador2;
	private Campo campo;
	
	public Rodada(Jogador p1, Jogador p2, Campo campo) {
		numeroRodada = 1;
		this.jogador1 = p1;
		this.jogador2 = p2;
		this.numeroJogadorAtual = NumeroJogador.PLAYER1;  // Jogo começa com p1
		this.tipo = TipoRodada.COMPRA_DE_CARTAS;
		this.acaoJogador1 = true;
		this.acaoJogador2 = true;
		this.campo = campo;
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
			if(jogador1.getTurno() == Turno.ATAQUE) {
				Combate.iniciar(campo, jogador1, jogador2);
			}
			else {
				Combate.iniciar(campo, jogador2, jogador1);
			}
			finalizarRodada();
			return;
		}
		if(numeroJogadorAtual == NumeroJogador.PLAYER1) {
			acaoJogador1 = realizouAcao;
			numeroJogadorAtual = NumeroJogador.PLAYER2;
		}
		else {
			acaoJogador2 = realizouAcao;
			numeroJogadorAtual = NumeroJogador.PLAYER1;
		}
		if(!acaoJogador1 && !acaoJogador2) {
			finalizarRodada();
			return;
		}
	}
	
	private void finalizarRodada() {
		if(jogador1.getNexus() <= 0 || jogador2.getNexus() <= 0) {
			campo.fimDeJogo();
		}
		this.tipo = TipoRodada.COMPRA_DE_CARTAS;
		numeroRodada++;
		jogador1.acabarRodada();
		jogador1.atualizarMana(numeroRodada);
		jogador2.acabarRodada();
		jogador2.atualizarMana(numeroRodada);
		this.acaoJogador1 = true;
		this.acaoJogador2 = true;
	}
	
	public NumeroJogador getNumeroJogadorAtual() {
		return numeroJogadorAtual;
	}
	
	
	public NumeroJogador getNumeroJogadorOponente() {
		if(numeroJogadorAtual == NumeroJogador.PLAYER1) {
			return NumeroJogador.PLAYER2;
		} else {
			return NumeroJogador.PLAYER1;
		}
	}
	
	
	public Jogador getJogador(NumeroJogador jogador) {
		if(jogador == NumeroJogador.PLAYER1) {
			return jogador1;
		}else {
			return jogador2;
		}
	}
	
	
	
}
