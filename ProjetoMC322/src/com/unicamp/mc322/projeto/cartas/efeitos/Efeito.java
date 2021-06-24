package com.unicamp.mc322.projeto.cartas.efeitos;

import java.util.Scanner;

import com.unicamp.mc322.projeto.Campo;

public abstract class Efeito {
	private TipoEfeito tipoEfeito = TipoEfeito.SELECIONADO;
	
	public abstract void ativarEfeito(Campo campo);
	
	protected int usuarioEscolherUnidade(String mensagemAoUsuario) {
		Scanner recebeUnidade = new Scanner(System.in);    //System.in is a standard input stream  
		System.out.print(mensagemAoUsuario);  
		return recebeUnidade.nextInt();
	}
	
	public TipoEfeito getTipoEfeito() {
		return tipoEfeito;
	}
	
	protected void setTipoEfeito(TipoEfeito tipo) {
		tipoEfeito = tipo;
	}
}
