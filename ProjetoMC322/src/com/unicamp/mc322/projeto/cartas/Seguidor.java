package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.cartas.efeitos.Efeito;
import com.unicamp.mc322.projeto.cartas.tracos.Traco;

public class Seguidor extends Carta {
	private int poder, vida, vidaTotal;
	private Traco traco;
	final private String tipo = "Seguidor";// Coloquei isso pq eu preciso saber qual tipo de carta é pra invocá-la
	private ArrayList<Efeito> listaEfeitos = new ArrayList<Efeito>();

	public Seguidor(String nome, int custo, int poder, int vidaTotal, Traco traco, Efeito... efeitos) {
		super(nome,custo);
		this.poder = poder;
		this.vidaTotal = vidaTotal;
		this.traco = traco;
		vida = vidaTotal;
		
		for(Efeito aux : efeitos) {
			listaEfeitos.add(aux);
		}
	}
	
	@Override
	public String getTipo() {
		return tipo;
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
	
	public int getVidaTotal() {
		return vidaTotal;
	}
	
}
	