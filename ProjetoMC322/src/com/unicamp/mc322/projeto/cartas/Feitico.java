package com.unicamp.mc322.projeto.cartas;

import com.unicamp.mc322.projeto.cartas.efeitos.Efeito;
import com.unicamp.mc322.projeto.main.Jogo;

public class Feitico extends Carta {
	Efeito efeito;
	final private String tipo = "Feitiço";// Coloquei isso pq eu preciso saber qual tipo de carta é pra invocá-la
	
	public Feitico(String nome, int custo, Efeito efeito) {
		super(nome,custo);
		this.efeito = efeito;
	}
	
	@Override
	public String getTipo() {
		return tipo;
	}

	public void ativarCarta(Jogo jogo) {
		efeito.ativarEfeito(jogo);
	}
}
