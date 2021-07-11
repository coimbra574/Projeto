package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.efeitos.Efeito;
import com.unicamp.mc322.projeto.cartas.tracos.TipoTraco;
import com.unicamp.mc322.projeto.cartas.tracos.Traco;
import com.unicamp.mc322.projeto.jogador.Jogador;

public class Campeao extends Seguidor {
	private Nivel nivel;
	private int numeroAtaques=0, seguidoresMortos=0, danoCausado=0, danoAdicionado=0;
	private DadosEvolucao evolucao;
	private Traco traco; 
	private ArrayList<Efeito> efeitos = new ArrayList<Efeito>();

	public Campeao(String nome, int custo, int poder, int vida, DadosEvolucao evolucao, Traco traco, Efeito ... efeitos) {
		super(nome, custo, TipoCarta.CAMPEAO, poder, vida, traco, efeitos);
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
	
	public void incrementarDanoCausado(int n) {
		danoCausado += n;
	}
	
	public void incrementarDanoAdicionado(int n) {
		danoAdicionado += n;
	}
	

	// Verifica se a condicao de evolucao foi atendida
	public boolean chequeDeNivel() {
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
		nivel = Nivel.SUPERIOR;
		traco = evolucao.getNovoTraco();
		
		for(Efeito aux : evolucao.getNovosEfeitos()) {
			efeitos.add(aux);
		}
	}
	
	@Override
	public String toStringEvocada() {
		String texto = "<html>";
		texto += this.nome + "<br />";
		texto += "Vida: " + Integer.toString(getVida()) + "<br />";
		texto += "Poder: " + Integer.toString(getPoder()) + "<br />";
		if(nivel == Nivel.NORMAL) {
			texto += "Nivel: N<br />";
		}
		else {
			texto += "Nivel: S<br />";
		}
		texto += "</html>";
		return texto;
	}
	
	@Override 
	public String toStringDetalhes() {
		String texto = super.toStringDetalhes();
		texto += ", Nivel: " + nivel.toString();
		texto += ", Condicao para evolucao: " + evolucao.getQuantEvolucao() + " " + evolucao.getCondicaoEvolucao().toString();
		return texto;
	}
}