/* 
 * 								   Matriz de Unidades Evocadas
 * 								 -------------------------------
 * 											Posi��o
 *  unidades evocadas jogador 1    |1|  |2|  |3|  |4|  |5|  |6|
 *  unidades evocadas jogador 2	   |1|  |2|  |3|  |4|  |5|  |6|
 * 
 */

package com.unicamp.mc322.projeto.campo;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;
import com.unicamp.mc322.projeto.jogador.NumeroJogador;
import com.unicamp.mc322.projeto.rodada.Rodada;

public class Campo {
	public final int LARGURA_CAMPO = 6;
	private Jogador jogador1,jogador2;
	private Rodada rodada;
	private int nexusP1, nexusP2;
	private InterfaceCampo interfaceGrafica;

	public Campo(Jogador p1, Jogador p2) {
		this.jogador1 = p1;
		this.jogador2 = p2;
		this.nexusP1 = 20;
		this.nexusP2 = 20;
		definirNumeroEmCampo(p1, p2);
		this.rodada = new Rodada(p1,p2, this);
		jogador1.atualizarMana(rodada.getNumeroRodada());
		jogador2.atualizarMana(rodada.getNumeroRodada());
		this.interfaceGrafica = new InterfaceCampo(this, rodada);
	}
	
	public void iniciar() {
		interfaceGrafica.ativar();
	}

	
	public int getNexus(int numeroJogador) {
		if(numeroJogador == 1) {
			return nexusP1;
		} else {
			return nexusP2;
		}
	}
	
	public void adicionarAoNexus(int numeroJogador, int valorAdicionado) {
		if(numeroJogador == 1) {
			nexusP1 += valorAdicionado;
		} else {
			nexusP2 = valorAdicionado;
		}
		interfaceGrafica.atualizarNexus();
	}
	
	public Rodada getRodada() {
		return rodada;
	}
	

	public void definirNumeroEmCampo(Jogador jogador1, Jogador jogador2) {
		jogador1.setNumeroEmCampo(1);
		jogador2.setNumeroEmCampo(2);
	}
	
	public boolean possuiCartaEscolhivel(NumeroJogador numJogador) {
		if(rodada.getNumeroJogadorAtual() == NumeroJogador.PLAYER1) {
			return verificarSeExisteEvocada(jogador1);
		} else {
			return verificarSeExisteEvocada(jogador2);
		}
	}
	
	
	private boolean verificarSeExisteEvocada(Jogador jogador) {
		if(jogador.getEvocadas().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public Seguidor selecionarUmaUnidadeAliada() {
		if(rodada.getNumeroJogadorAtual() == NumeroJogador.PLAYER1) {
			return interfaceGrafica.selecionarCartaP1();
		}
		else {
			return interfaceGrafica.selecionarCartaP2();
		}
	}
	
	public Seguidor selecionarUmaUnidadeInimiga() {
		if(rodada.getNumeroJogadorAtual() == NumeroJogador.PLAYER1) {
			return interfaceGrafica.selecionarCartaP2();
		}
		else {
			return interfaceGrafica.selecionarCartaP1();
		}
	}
	
	public void adicionarAoNexusJogador(NumeroJogador jogador, int valorAdicionado) {
		if(jogador == NumeroJogador.PLAYER1) {
			jogador1.adicionarAoNexus(valorAdicionado);
		} else {
			jogador2.adicionarAoNexus(valorAdicionado);
		}
	}
	
	// Retorna indice de carta evocada se encontrada
	public int procurarCartaEvocada(NumeroJogador player, Seguidor carta) {
		int indexEncontrado=-1;
		Jogador jogador = rodada.getJogador(player);
		ArrayList<Seguidor> evocadas = jogador.getEvocadas();
		for(int i=0; i<evocadas.size(); i++) {
			if(evocadas.get(i).equals(carta)) {
				indexEncontrado=i;
			}
		}
		return indexEncontrado;
	}
	
	public Jogador getP1() {
		return jogador1;
	}
	
	public Jogador getP2() {
		return jogador2;
	}

	public ArrayList<Seguidor> getEvocadas(NumeroJogador jogador) {
		if(jogador == NumeroJogador.PLAYER1) {
			return jogador1.getEvocadas();
		} else {
			return jogador2.getEvocadas();
		}
	}
	
	public void fimDeJogo() {
		interfaceGrafica.setVisible(false);
		System.out.println("Fim de jogo!");
		if(jogador1.getNexus() <= 0) {
			System.out.print("O vencedor foi o Jogador 1");
		}
		else {
			System.out.print("O vencedor foi o Jogador 2");
		}
	}
}
