/* 
 * 								   Matriz de Unidades Evocadas
 * 								 -------------------------------
 * 											Posição
 *  unidades evocadas jogador 1    |1|  |2|  |3|  |4|  |5|  |6|
 *  unidades evocadas jogador 2	   |1|  |2|  |3|  |4|  |5|  |6|
 * 
 */

package com.unicamp.mc322.projeto.main;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.Interface.InterfaceGrafica;
import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;
import com.unicamp.mc322.projeto.turno.Turno;

public class Campo {
	public final int LARGURA_CAMPO = 6;
	private Carta[][] unidadesEvocadas = new Carta[2][LARGURA_CAMPO];
	private int nexusP1, nexusP2;
	private Jogador player1,player2;
	private int numeroJogadorAtual;
	private InterfaceGrafica interfaceGrafica;

	public Campo(Jogador p1, Jogador p2) {
		this.player1 = p1;
		this.player2 = p2;
		this.interfaceGrafica = new InterfaceGrafica();
		definirNumeroEmCampo(p1, p2);
		nexusP1 = 20;
		nexusP2 = 20;
		numeroJogadorAtual = 1;  // Jogo começa com p1
		
		interfaceGrafica.iniciar(nexusP1, nexusP2, player1.getMao(), player2.getMao());
		
		//p1.setAtacante()  //Na primeira rodada p1 é atacante e p2 é defesa
		//p2.set.Defesa 
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
	}
	
	public void definirNumeroEmCampo(Jogador jogador1, Jogador jogador2) {
		jogador1.setNumeroEmCampo(1);
		jogador2.setNumeroEmCampo(2);
	}
	
	
	public void adicionarCartaEmCampo(int numeroJogador, int posicao, Carta carta) {
		unidadesEvocadas[numeroJogador][posicao-1] = carta;
	}
	
	
	public void removerCartaEmCampo(int numeroJogador, int posicao) {
		unidadesEvocadas[numeroJogador][posicao-1] = null;
	}
	
	
	public void alterarCartaEmCampo(int numeroJogador, int posicao, Caracteristica caracteristica, int novoValor) {
		if(caracteristica.equals(Caracteristica.PODER)) {
	//		unidadesEvocadas[numeroJogador][posicao-1].setPoder(novoValor);
		}
		else {
	//		unidadesEvocadas[numeroJogador][posicao-1].setVida(novoValor);
		}
	}
	

	public Carta selecionarUnidade(int numeroJogador, int posicao) {
		if(posicao > 0 && posicao <= LARGURA_CAMPO) {
			return unidadesEvocadas[numeroJogador][posicao-1];
		} else {
			System.out.println("Seleção fora dos limites do campo");
			return null;
		}
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
