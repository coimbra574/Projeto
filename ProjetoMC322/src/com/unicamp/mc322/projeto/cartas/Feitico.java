package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.efeitos.Efeito;

public class Feitico extends Carta {
	
	public Feitico(String nome, int custo, Efeito ... listaEfeitos) {
		super(nome,custo, TipoCarta.FEITICO);
		
		if(listaEfeitos != null) {
			for(Efeito efeitoAux : listaEfeitos) {
				super.listaEfeitos.add(efeitoAux);
			}
		}
	}
	
	
	@Override
	public String toString() {
		String texto = this.tipo + " " + this.nome + ", Custo: " + this.custo;
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
	public String toStringDetalhes() {
		String texto = "\n Nome: " + nome + ", Custo: " + custo;
		boolean temEfeito = false;
		texto += "\n Efeitos: ";
		for(Efeito efeitoAux : super.listaEfeitos) {
			if(efeitoAux != null) {
				temEfeito = true;
				texto += "\n " + efeitoAux.getNome() + " (" + efeitoAux.getInfo() + ") ";
			}
		}
		
		if(!temEfeito) {
			texto += "sem efeitos";
		}
		return texto;
	}
	

}
