/* 
 * 								   Matriz de Unidades Evocadas
 * 								 -------------------------------
 * 											Posi��o
 *  unidades evocadas jogador 1    |1|  |2|  |3|  |4|  |5|  |6|
 *  unidades evocadas jogador 2	   |1|  |2|  |3|  |4|  |5|  |6|
 * 
 */

package com.unicamp.mc322.projeto;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.Interface.InterfaceGrafica;
import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;
import com.unicamp.mc322.projeto.turno.Turno;

public class Campo {
	public final int LARGURA_CAMPO = 6;
	private Seguidor[][] unidadesEvocadas = new Seguidor[2][LARGURA_CAMPO];
	private ArrayList<Seguidor> evocadasP1 = new ArrayList<Seguidor>();
	private ArrayList<Seguidor> evocadasP2 = new ArrayList<Seguidor>();
	private Jogador jogador1,jogador2;
	private Rodada rodada;
	private int nexusP1, nexusP2;
	private InterfaceGrafica interfaceGrafica;

	public Campo(Jogador p1, Jogador p2) {
		this.jogador1 = p1;
		this.jogador2 = p2;
		this.nexusP1 = 20;
		this.nexusP2 = 20;
		definirNumeroEmCampo(p1, p2);
		this.rodada = new Rodada(p1,p2);
		this.interfaceGrafica = new InterfaceGrafica(this, rodada);
		interfaceGrafica.iniciarTurno();
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
	
	public void adicionarCartaEmCampo(int numeroJogador, int posicao, Carta carta) {
		unidadesEvocadas[numeroJogador][posicao-1] = (Seguidor) carta;
	}
	
	
	public void removerCartaEmCampo(int numeroJogador, int posicao) {
		unidadesEvocadas[numeroJogador][posicao-1] = null;
	}
	
	
	public void alterarCartaEmCampo(numeroJogador numeroJogador, int posicao, Caracteristica caracteristica, int novoValor) {
		if(caracteristica.equals(Caracteristica.PODER)) {
			//unidadesEvocadas[numeroJogador][posicao-1].setPoder(novoValor);
		}
		else {
			//unidadesEvocadas[numeroJogador][posicao-1].setVida(novoValor);
		}
	}
	
	public boolean possuiCartaEscolhivel(numeroJogador numJogador) {
		return false;
	}
	
	public Seguidor selecionarUmaUnidadeAliada() {
		if(rodada.getNumeroJogadorAtual() == numeroJogador.PLAYER1) {
			return interfaceGrafica.selecionarCartaP1();
		}
		else {
			return interfaceGrafica.selecionarCartaP2();
		}
	}
	
	public Seguidor selecionarUmaUnidadeInimiga() {
		if(rodada.getNumeroJogadorAtual() == numeroJogador.PLAYER1) {
			return interfaceGrafica.selecionarCartaP2();
		}
		else {
			return interfaceGrafica.selecionarCartaP1();
		}
	}
	

	/*public ArrayList<Carta> selecionarUnidades(int numeroJogador, int ...posicoes) {
		ArrayList<Carta> cartasSelecionadas = null;
		for(int posicaoAux : posicoes) {
			cartasSelecionadas.add(selecionarUmaUnidade(numeroJogador, posicaoAux));
		}
		return cartasSelecionadas;
	}*/
	
	
	public int procurarCartaEmCampo(int numeroJogador, Carta carta) {
		int indexCarta = -1;
		for(int i=0; i<LARGURA_CAMPO; i++) {
			if(unidadesEvocadas[numeroJogador][i].equals(carta)) {
				indexCarta = i;
			}
		}
		return indexCarta;
	}
	
	
	// Remove cartas sem vida em campo
	public void verificarCartasComVida(int numeroJogador) {
		for(int i=0; i<LARGURA_CAMPO; i++) {
			if(unidadesEvocadas[numeroJogador][i].getVida() <= 0) {
				removerCartaEmCampo(numeroJogador,i);
			}
		}
	}
	
	
	public void adicionarAoNexusJogador(numeroJogador numeroJogador, int valorAdicionado) {
		if(numeroJogador == numeroJogador.PLAYER1) {
			jogador1.adicionarAoNexus(valorAdicionado);
		} else {
			jogador2.adicionarAoNexus(valorAdicionado);
		}
	}
	
	public Jogador getP1() {
		return jogador1;
	}
	
	public Jogador getP2() {
		return jogador2;
	}
	
	public void terminarTurno() {
		
	}


	public ArrayList<Seguidor> getEvocadas(numeroJogador numeroJogador) {
		if(numeroJogador == numeroJogador.PLAYER1) {
			return evocadasP1;
		} else {
			return evocadasP2;
		}
	}
}
