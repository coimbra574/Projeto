package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.efeitos.Efeito;
import com.unicamp.mc322.projeto.cartas.efeitos.TipoAtivacao;

public abstract class Carta implements Cloneable {
	protected int custo;
	protected String nome;
	protected TipoCarta tipo;
	protected ArrayList<Efeito> listaEfeitos = new ArrayList<Efeito>();

	public Carta(String nome, int custo, TipoCarta tipo) {
		this.nome = nome;
		this.custo = custo;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}
	
	public int getMana() {
		return custo;
	}
	
	public TipoCarta getTipo() {
		return tipo;
	}
	
	public void setMana(int novoCusto) {
		custo = novoCusto;
	}
	
	public ArrayList<Efeito> getEfeitos() {
		return listaEfeitos;
	}
	
	public void addEfeito(Efeito novoEfeito) {
		listaEfeitos.add(novoEfeito);
	}
	
	public void ativarEfeitos(Campo campo, TipoAtivacao tipo) {
		for(Efeito efeitoAux : listaEfeitos) {
			if(efeitoAux.getTipoAtivacao() == tipo) {
				efeitoAux.ativarEfeito(campo);
			}
		}
	}
	
	public boolean ehPossivel(Campo campo) {
		for(Efeito efeito : listaEfeitos) {
			if(efeito.requerCartaAliada()) {
				if(campo.possuiCartaEscolhivel(campo.getRodada().getNumeroJogadorAtual()) == false) {
					return false;
				}
			}
			if(efeito.requerCartaInimiga()) {
				if(campo.possuiCartaEscolhivel(campo.getRodada().getNumeroJogadorOponente()) == false) {
					return false;
				}
			}
		}
		return true;
	}
	
	public Carta getClone() {
		try {
			return (Carta) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.out.println("Nao foi possivel clonar");
			return this;
		}
	}

	public abstract String toStringCompra();
	
	public abstract String toStringDetalhes();
	
}