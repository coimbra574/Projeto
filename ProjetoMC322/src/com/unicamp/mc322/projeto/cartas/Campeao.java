package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;
import com.unicamp.mc322.projeto.cartas.efeitos.Efeito;
import com.unicamp.mc322.projeto.cartas.tracos.Traco;

public class Campeao extends Seguidor {
	private Nivel nivel;
	private int numeroAtaques=0, seguidoresMortos=0, danoCausado=0, danoAdicionado=0;
	private DadosEvolucao evolucao;
	private Traco traco; // SÓ 1 TRAÇO?
	private ArrayList<Efeito> efeitos = new ArrayList<Efeito>();

	public Campeao(String nome, int custo, int poder, int vida, DadosEvolucao evolucao, Traco traco, Efeito ... efeitos) {
		super(nome, custo, poder, vida, traco, efeitos);
		this.evolucao = evolucao;
		this.nivel = Nivel.NORMAL;
		
		for(Efeito aux : efeitos) {
			this.efeitos.add(aux);
		}
	}
	
	public void setNivel(Nivel novoNivel) {
		nivel = novoNivel;
	}
	
	public void incrementarNumAtaques() {
		numeroAtaques++;
	}
	
	public void incrementarSeguidoresMortos() {
		seguidoresMortos++;
	}
	
	public void incrementarDanoCausado() {
		danoCausado++;
	}
	
	public void incrementarDanoAdicionado() {
		danoAdicionado++;
	}
	

	// Verifica se a condição de evolução foi atendida
	public boolean chequeDeNivel(int n) {
		boolean condicaoAtendida;
		Condicao condicaoDeEvolucao = evolucao.getCondicaoEvolucao();
		int quantParaEvolucao = evolucao.getQuantEvolucao();
		
		if(condicaoDeEvolucao.equals(Condicao.NUM_ATAQUE) && numeroAtaques >= quantParaEvolucao) {
			condicaoAtendida = true;
		}
		else if (condicaoDeEvolucao.equals(Condicao.NUM_SEGUIDORES_MORTOS) && seguidoresMortos >= quantParaEvolucao) {
			condicaoAtendida = true;
		}
		else if (condicaoDeEvolucao.equals(Condicao.NUM_DANO_CAUSADO) && danoCausado >= quantParaEvolucao) {
			condicaoAtendida = true;
		}
		else if (condicaoDeEvolucao.equals(Condicao.NUM_DANO_ADD) && danoAdicionado >= quantParaEvolucao) {
			condicaoAtendida = true;
		}
		else {
			condicaoAtendida = false;
		}
		return condicaoAtendida;
	}
	
	
	public void subirNivel() {
		setVida(getVida() + evolucao.getMaisVida());
		setPoder(getPoder() + evolucao.getMaisPoder());
		traco = evolucao.getNovoTraco();
		
		for(Efeito aux : evolucao.getNovosEfeitos()) {
			efeitos.add(aux);
		}
	}
	
}