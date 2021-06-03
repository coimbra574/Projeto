package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;
import com.unicamp.mc322.projeto.cartas.efeitos.Efeito;
import com.unicamp.mc322.projeto.cartas.tracos.Traco;

public class Campeao extends Seguidor {
	private Nivel nivel;
	private int numeroAtaques=0, seguidoresMortos=0, danoCausado=0, danoAdicionado=0;
	private int quantParaEvolucao;
	private Condicao condicaoDeEvolucao;
	private Campeao evolucao;

	public Campeao(String nome, int custo, int poder, int vida, Traco traco, Condicao condicaoDeEvolucao, int quantParaEvolucao, Campeao evolucao, Efeito ... efeitos) {
		super(nome, custo, poder, vida, traco, efeitos);
		this.condicaoDeEvolucao = condicaoDeEvolucao;
		this.evolucao = evolucao;
		this.quantParaEvolucao = quantParaEvolucao;
		
		// Se não tiver condição de evolução, já é um campeão de nível superior
		if(condicaoDeEvolucao.equals(Condicao.SEM_EVOLUCAO)) {
			nivel = Nivel.SUPERIOR;
		} else {
			nivel = Nivel.NORMAL;
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
	
	
	// Se durante o jogo o cheque de nível for true, então troca a carta pela sua evolução
	public Carta subirNivel(int n) {
		return evolucao;
	}
	
}