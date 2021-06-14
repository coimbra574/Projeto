/* Classe para guardar informações quanto a evolução do Campeão
 * 
 */

package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;
import com.unicamp.mc322.projeto.cartas.efeitos.Efeito;
import com.unicamp.mc322.projeto.cartas.tracos.Traco;

public class DadosEvolucao {
	private int quantParaEvolucao, maisVida, maisPoder;
	private Condicao condicaoDeEvolucao;
	private Traco novoTraco;
	private ArrayList<Efeito> novosEfeitos = new ArrayList<Efeito>();
	
	public DadosEvolucao(Condicao condicaoDeEvolucao, int quantParaEvolucao, int maisVida, int maisPoder, Traco novoTraco, Efeito ... novosEfeitos) {
		this.quantParaEvolucao = quantParaEvolucao;
		this.maisVida = maisVida;
		this.maisPoder = maisPoder;
		this.condicaoDeEvolucao = condicaoDeEvolucao;
		this.novoTraco = novoTraco;
		
		for(Efeito aux : novosEfeitos) {
			this.novosEfeitos.add(aux);
		}
	}
	
	protected int getQuantEvolucao() {
		return quantParaEvolucao;
	}
	
	protected int getMaisVida() {
		return maisVida;
	}
	
	protected int getMaisPoder() {
		return maisPoder;
	}
	
	protected Traco getNovoTraco() {
		return novoTraco;
	}
	
	protected ArrayList<Efeito> getNovosEfeitos(){
		return novosEfeitos;
	}
	
	protected Condicao getCondicaoEvolucao() {
		return condicaoDeEvolucao;
	}
	
}
