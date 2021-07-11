package com.unicamp.mc322.projeto.rodada;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.cartas.efeitos.Efeito;
import com.unicamp.mc322.projeto.cartas.efeitos.TipoAtivacao;
import com.unicamp.mc322.projeto.jogador.Jogador;
import com.unicamp.mc322.projeto.jogador.NumeroJogador;

public class Rodada {
	private int numeroRodada;
	private int indexCartaAplicarEfeito;
	private Jogador jogador1, jogador2;
	private NumeroJogador numeroJogadorAtual,numeroJogadorAplicarEfeito;
	private TipoRodada tipo;
	private boolean acaoJogador1;
	private boolean acaoJogador2;
	private Campo campo;
	
	public Rodada(Jogador p1, Jogador p2, Campo campo) {
		numeroRodada = 1;
		this.jogador1 = p1;
		this.jogador2 = p2;
		if(jogador1.getTurno() == Turno.ATAQUE) {
			this.numeroJogadorAtual = NumeroJogador.PLAYER1;
		}
		else {
			this.numeroJogadorAtual = NumeroJogador.PLAYER2;
		}
		this.tipo = TipoRodada.COMPRA_DE_CARTAS;
		this.acaoJogador1 = true;
		this.acaoJogador2 = true;
		this.campo = campo;
	}
	
	public void iniciar() {
		boolean acao;
		
		if(numeroJogadorAtual == NumeroJogador.PLAYER1) {
			if(tipo == TipoRodada.COMPRA_DE_CARTAS) {
				if(jogador1.getTurno() == Turno.ATAQUE) {
					acao = jogador1.acaoRodadaCompraOuAtaque(campo);
				}
				else {
					acao = jogador1.acaoRodadaCompra(campo);
				}
			}
			else {
				acao = jogador1.acaoRodadaDefesa(campo);
			}
		}
		else {
			if(tipo == TipoRodada.COMPRA_DE_CARTAS) {
				if(jogador2.getTurno() == Turno.ATAQUE) {
					acao = jogador2.acaoRodadaCompraOuAtaque(campo);
				}
				else {
					acao = jogador2.acaoRodadaCompra(campo);
				}
			}
			else {
				acao = jogador2.acaoRodadaDefesa(campo);
			}
		}
		
		finalizarTurno(acao);
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
		if(jogador1.getNexus() > 0 && jogador2.getNexus() > 0) {
			this.tipo = TipoRodada.COMPRA_DE_CARTAS;
			numeroRodada++;
			ativarEfeitosFimDaRodada(NumeroJogador.PLAYER1);
			ativarEfeitosFimDaRodada(NumeroJogador.PLAYER2);
			removerSeguidoresSemVida();
			jogador1.acabarRodada();
			jogador1.atualizarMana(numeroRodada);
			jogador2.acabarRodada();
			jogador2.atualizarMana(numeroRodada);
			jogador1.pegarCarta();
			jogador2.pegarCarta();
			this.acaoJogador1 = true;
			this.acaoJogador2 = true;
		}
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
	
	public void ativarEfeitosFimDaRodada(NumeroJogador player) {
		int index = 0;
		ArrayList<Seguidor> cartasEvocadas = campo.getEvocadas(player);
		for(Seguidor carta : cartasEvocadas) {
			for(Efeito efeito : carta.getEfeitos()) {
				if(efeito.getTipoAtivacao() == TipoAtivacao.FIM_DA_RODADA) {
					indexCartaAplicarEfeito = index;
					numeroJogadorAplicarEfeito = player;
					efeito.ativarEfeito(campo);
				}
				index++;
			}
		}
	}
	
	
	public int getIndexCartaAplicarEfeito() {
		return indexCartaAplicarEfeito;
	}
	
	public NumeroJogador getNumeroJogadorAplicarEfeito() {
		return numeroJogadorAplicarEfeito;
	}
	
	private void removerSeguidoresSemVida() {
		ArrayList<Seguidor> cartasEvocadasP1 = campo.getEvocadas(NumeroJogador.PLAYER1);
		ArrayList<Seguidor> cartasEvocadasP2 = campo.getEvocadas(NumeroJogador.PLAYER2);
		for(int i=0; i<cartasEvocadasP1.size(); i++) {
			if(cartasEvocadasP1.get(i).getVida() <= 0) {
				cartasEvocadasP1.remove(i);
			}
		}
		for(int i=0; i<cartasEvocadasP2.size(); i++) {
			if(cartasEvocadasP2.get(i).getVida() <= 0) {
				cartasEvocadasP2.remove(i);
			}
		}
	}
	
	
}
