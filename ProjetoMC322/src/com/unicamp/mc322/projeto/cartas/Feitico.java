package com.unicamp.mc322.projeto.cartas;

import com.unicamp.mc322.projeto.Campo;
import com.unicamp.mc322.projeto.cartas.efeitos.Efeito;

public class Feitico extends Carta {
	Efeito efeito;
	final private String tipo = "Feitico";// Coloquei isso pq eu preciso saber qual tipo de carta é pra invocá-la
	
	public Feitico(String nome, int custo, Efeito efeito) {
		super(nome,custo);
		this.efeito = efeito;
	}
	
	@Override
	public String getTipo() {
		return tipo;
	}

	public void ativarCarta(Campo campo) {
		efeito.ativarEfeito(campo);
	}
	
	@Override
	public String toString() {
		String texto = this.tipo + " " + this.nome + ", Custo: " + this.custo;
		texto += ", Efeito: "; //@TODO + efeito.toString();
		return texto;
	}
	
	@Override
	public String toStringCompra() {
		String texto = "<html>Feitico<br />";
		texto += this.nome + "<br />";
		texto += "Custo: " + Integer.toString(this.custo);
		texto += "</html>";
		return texto;
	}
	
	@Override
	public String toStringEvocada() {
		String texto = "";
		return texto;
	}
	
	@Override
	public String toStringEmCampo() {
		String texto = "";
		return texto;
	}
	
	@Override
	public String toStringDetalhes() {
		String texto = "";
		return texto;
	}
}
