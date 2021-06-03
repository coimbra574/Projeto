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
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.jogador.Jogador;

public class Campo {
	Carta[][] unidadesEvocadas = new Carta[2][6];
	//private Jogador p1,p2;

	public Campo(Jogador p1, Jogador p2) {
		definirNumeroEmCampo(p1, p2);
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
	
	
	public ArrayList<Carta> selecionarUnidades(int numeroJogador, int ... unidades) {
		ArrayList<Carta> unidadesSelecionadas = new ArrayList<Carta>();
		for(int i : unidades) {
			if(i > 0 && i < 7) {
				unidadesSelecionadas.add(unidadesEvocadas[numeroJogador][i-1]);
			} else {
				System.out.println("Seleção fora dos limites do campo");
			}
		}
		return unidadesSelecionadas;
	}

}
