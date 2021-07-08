package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.efeitos.Efeito;

public class Feitico extends Carta {
	private ArrayList<Efeito> listaEfeitos = new ArrayList<Efeito>();
	
	public Feitico(String nome, int custo, Efeito ... listaEfeitos) {
		super(nome,custo, TipoCarta.FEITICO);
		
		if(listaEfeitos != null) {
			for(Efeito efeitoAux : listaEfeitos) {
				this.listaEfeitos.add(efeitoAux);
			}
		}
	}

	public void ativarCarta(Campo campo) {
		for(Efeito efeitoAux : listaEfeitos) {
			efeitoAux.ativarEfeito(campo);
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
	
	@Override
	public String toString() {
		String texto = this.tipo + " " + this.nome + ", Custo: " + this.custo;
		//texto += ", Efeito: "; //@TODO + efeito.toString();
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
		for(Efeito efeitoAux : listaEfeitos) {
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
