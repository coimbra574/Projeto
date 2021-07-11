package com.unicamp.mc322.projeto.cartas;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.efeitos.Efeito;
import com.unicamp.mc322.projeto.cartas.efeitos.TipoAtivacao;
import com.unicamp.mc322.projeto.cartas.tracos.TipoTraco;
import com.unicamp.mc322.projeto.cartas.tracos.Traco;
import com.unicamp.mc322.projeto.jogador.Jogador;

public class Seguidor extends Carta {
	private int poder, vida, vidaTotal;
	private Traco traco=null;
	//private ArrayList<Efeito> listaEfeitos = new ArrayList<Efeito>();
	private boolean matouUmSeguidor = false;  // Alterado na classe combate

	public Seguidor(String nome, int custo, int poder, int vidaTotal, Traco traco, Efeito... efeitos) {
		super(nome,custo, TipoCarta.SEGUIDOR);
		this.poder = poder;
		this.vidaTotal = vidaTotal;
		this.traco = traco;
		vida = vidaTotal;
		
		if(efeitos != null) {
			for(Efeito aux : efeitos) {
				super.listaEfeitos.add(aux);
			}
		}
	}
	
	public Seguidor(String nome, int custo, TipoCarta tipo, int poder, int vidaTotal, Traco traco, Efeito... efeitos) {
		super(nome,custo, tipo);
		this.poder = poder;
		this.vidaTotal = vidaTotal;
		this.traco = traco;
		vida = vidaTotal;
		
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
	
	public void aumentarPoder(int n) {
		poder+=n;
	}
	
	public int getVida() {
		return vida;
	}
	
	public void setVida(int novaVida) {
		vida = novaVida;
	}
	
	public void aumentarVida(int m) {
		vida+=m;
	}
	
	public int getVidaTotal() {
		return vidaTotal;
	}
	
	public void setMatouUmSeguidor(boolean matouUmSeguidor) {
		this.matouUmSeguidor = matouUmSeguidor;
	}
	
	public boolean getMatouUmSeguidor() {
		return matouUmSeguidor;
	}
	
	public void ativacaoTraco(Seguidor cartaDefensor, Jogador defensor, Campo campo) {//Para ativar o traco
		traco.ativarTraco(this, cartaDefensor, defensor, campo);
	}
	
	public boolean getHaTraco() {
		if(traco!=null) {
			return true;
		}
		return false;
	}
	
	public TipoTraco getTipoTraco() {
		if(traco == null) {
			return null;
		}
		return traco.getTraco();
	}
	
	
	@Override
	public String toString() {
		String texto = this.tipo.toString() + " " + this.nome + ", Custo: " + this.custo;
		texto += ", Vida: " + this.vida + ", Poder: " + this.poder;
		return texto;
	}
	
	@Override
	public String toStringCompra() {
		String texto = "<html>";
		texto += this.nome + "<br />";
		texto += "Vida: " + Integer.toString(this.vida) + "<br />";
		texto += "Poder: " + Integer.toString(this.poder) + "<br />";
		texto += "Custo: " + Integer.toString(this.custo);
		texto += "</html>";
		return texto;
	}
	
	public String toStringEvocada() {
		String texto = "<html>";
		texto += this.nome + "<br />";
		texto += "Vida: " + Integer.toString(this.vida) + "<br />";
		texto += "Poder: " + Integer.toString(this.poder) + "<br />";
		texto += "</html>";
		return texto;
	}
	
	public String toStringEmCampo() {
		String texto = "<html>";
		texto += this.nome + "<br />";
		//texto += "V: " + Integer.toString(this.vida);  <--------------------------
		texto += "V: " + this.vida;
		texto += "  P: " + Integer.toString(this.poder);
		return texto;
	}
	
	@Override 
	public String toStringDetalhes() {
		String texto = "\n " + toString();
		
		if(traco == null) {
			texto += "\n Traco: sem traco";
		} else {
			texto += "\n Traco: " + traco.toString();
		}
		
		boolean temEfeito = false;
		for(Efeito efeitoAux : listaEfeitos) {
			if(efeitoAux != null) {
				temEfeito = true;
				texto += "\n Efeitos: " + efeitoAux.getNome() + " (" + efeitoAux.getInfo() + ") ";
			}
		}
		
		if(!temEfeito) {
			texto += "\n Efeitos: sem efeitos";
		}
		
		return texto;
	}
	
}
	