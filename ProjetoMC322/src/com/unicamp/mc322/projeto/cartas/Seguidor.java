package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.cartas.efeitos.Efeito;
import com.unicamp.mc322.projeto.cartas.tracos.Traco;

public class Seguidor extends Carta {
	private int poder, vida;
	private Traco traco;
	private ArrayList<Efeito> listaEfeitos = new ArrayList<Efeito>();

	public Seguidor(String nome, int custo, int poder, int vida, Traco traco, Efeito... efeitos) {
		super(nome,custo);
		this.poder = poder;
		this.vida = vida;
		this.traco = traco;
		
		for(Efeito aux : efeitos) {
			listaEfeitos.add(aux);
		}
	}
	
	public int getPoder() {
		return poder;
	}
	
	public void setPoder(int novoPoder) {
		poder = novoPoder;
	}
	
	public int getVida() {
		return vida;
	}
	
	public void setVida(int novaVida) {
		vida = novaVida;
	}
}
	