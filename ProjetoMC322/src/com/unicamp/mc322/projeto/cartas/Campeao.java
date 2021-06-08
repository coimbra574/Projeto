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
	final private String tipo = "Campeão";// Coloquei isso pq eu preciso saber qual tipo de carta é pra invocá-la

	public Campeao(String nome, int custo, int poder, int vida, Traco traco, Condicao condicaoDeEvolucao, int quantParaEvolucao, Campeao evolucao, Efeito ... efeitos) {
		super(nome, custo, poder, vida, traco, efeitos);
		this.condicaoDeEvolucao = condicaoDeEvolucao;
		this.evolucao = evolucao;
		this.quantParaEvolucao = quantParaEvolucao;
		
		// Se n�o tiver condi��o de evolu��o, j� � um campe�o de n�vel superior
		if(condicaoDeEvolucao.equals(Condicao.SEM_EVOLUCAO)) {
			nivel = Nivel.SUPERIOR;
		} else {
			nivel = Nivel.NORMAL;
		}
	}
	
	@Override
	public String getTipo() {
		return tipo;
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
	

	// Verifica se a condi��o de evolu��o foi atendida
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
	
	
	// Se durante o jogo o cheque de n�vel for true, ent�o troca a carta pela sua evolu��o
	public Carta subirNivel(int n) {
		return evolucao;
	}
	
}