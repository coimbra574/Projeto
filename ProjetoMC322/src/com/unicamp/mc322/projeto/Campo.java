/* 
 * 								   Matriz de Unidades Evocadas
 * 								 -------------------------------
 * 											Posição
 *  unidades evocadas jogador 1    |1|  |2|  |3|  |4|  |5|  |6|
 *  unidades evocadas jogador 2	   |1|  |2|  |3|  |4|  |5|  |6|
 * 
 */

package com.unicamp.mc322.projeto;

import java.util.ArrayList;
import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;
import com.unicamp.mc322.projeto.turno.Turno;

public class Campo {
	public final int LARGURA_CAMPO = 6;
	private Seguidor[][] unidadesEvocadas = new Seguidor[2][LARGURA_CAMPO];
	private Jogador p1,p2;
	private Rodada rodada;

	public Campo(Jogador p1, Jogador p2) {
		this.p1 = p1;
		this.p2 = p2;
		definirNumeroEmCampo(p1, p2);
		rodada = new Rodada(p1,p2);
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
	
	
	public void alterarCartaEmCampo(int numeroJogador, int posicao, Caracteristica caracteristica, int novoValor) {
		if(caracteristica.equals(Caracteristica.PODER)) {
			unidadesEvocadas[numeroJogador][posicao-1].setPoder(novoValor);
		}
		else {
			unidadesEvocadas[numeroJogador][posicao-1].setVida(novoValor);
		}
	}
	
	
	public Carta selecionarUmaUnidade(int numeroJogador, int posicao) {
		if(posicao > 0 && posicao <= LARGURA_CAMPO) {
			return unidadesEvocadas[numeroJogador][posicao-1];
		} else {
			System.out.println("Seleção fora dos limites do campo");
			return null;
		}
	}
	

	public ArrayList<Carta> selecionarUnidades(int numeroJogador, int ...posicoes) {
		ArrayList<Carta> cartasSelecionadas = null;
		for(int posicaoAux : posicoes) {
			cartasSelecionadas.add(selecionarUmaUnidade(numeroJogador, posicaoAux));
		}
		return cartasSelecionadas;
	}
	
	
	// Refazer - criar uma classe combate?
	public void combater(Jogador atacante, Jogador defensor) {
		ArrayList<Carta> unidadesCombatentes = atacante.escolherCartaUtilizar(this);
		ArrayList<Carta> unidadesDefensoras = defensor.escolherCartaUtilizar(this);
		int indexCartaAtaque=-1, indexCartaDefesa=-1;
		
		for(int i=0; i<unidadesCombatentes.size(); i++) {
			indexCartaAtaque = procurarCartaEmCampo(atacante.getNumeroEmCampo(), unidadesCombatentes.get(i));
			Seguidor cartaAtacante = (Seguidor) unidadesEvocadas[atacante.getNumeroEmCampo()][indexCartaAtaque];
			
			// Se existir uma unidade defensora para esse ataque, combatem
			if(!unidadesDefensoras.get(i).equals(null)) {  
				indexCartaDefesa = procurarCartaEmCampo(defensor.getNumeroEmCampo(), unidadesDefensoras.get(i));
				Seguidor cartaDefensora = (Seguidor) unidadesEvocadas[defensor.getNumeroEmCampo()][indexCartaDefesa];
				cartaAtacante.setVida(cartaAtacante.getVida() - cartaDefensora.getPoder());
				cartaDefensora.setVida(cartaAtacante.getVida() - cartaDefensora.getPoder());
			}
			// Se não tiver carta de defesa, ataca diretamente o nexus
			if(indexCartaDefesa == -1) {
				defensor.adicionarAoNexus(-1*cartaAtacante.getPoder());
			}
		}
		verificarCartasComVida();
		rodada.terminarRodada();
	}
	
	
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
	private void verificarCartasComVida() {
		int i;
		for(int j=0; j<2; j++) {
			for(i=0; i<LARGURA_CAMPO; i++) {
				if(unidadesEvocadas[j][i].getVida() <= 0) {
					removerCartaEmCampo(j,i);
				}
			}
		}
	}
	
	
	public void adicionarAoNexusJogador(int numeroJogador, int valorAdicionado) {
		if(numeroJogador == 1) {
			p1.adicionarAoNexus(valorAdicionado);
		} else {
			p2.adicionarAoNexus(valorAdicionado);
		}
	}

	
	
}
