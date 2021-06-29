package com.unicamp.mc322.projeto.cartas;

import com.unicamp.mc322.projeto.Campo;
import com.unicamp.mc322.projeto.cartas.efeitos.Efeito;

public class Feitico extends Carta {
	Efeito efeito;
	
	public Feitico(String nome, int custo, Efeito efeito) {
		super(nome,custo, TipoCarta.FEITICO);
		this.efeito = efeito;
	}

	public void ativarCarta(Campo campo) {
		efeito.ativarEfeito(campo);
	}
	
	public boolean ehPossivel(Campo campo) {
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
		return true;
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
	public String toStringDetalhes() {
		String texto = "";
		return texto;
	}

}
