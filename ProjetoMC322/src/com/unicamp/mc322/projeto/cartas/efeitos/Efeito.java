package com.unicamp.mc322.projeto.cartas.efeitos;

import java.util.Scanner;

import com.unicamp.mc322.projeto.Campo;

public abstract class Efeito {
	private TipoEfeito tipoEfeito = TipoEfeito.SELECIONADO;
	private boolean usaCartaAliada;
	private boolean usaCartaInimiga;
	private String nome, info;
	
	public Efeito(boolean usaCartaAliada, boolean usaCartaInimiga) {
		this.usaCartaAliada = usaCartaAliada;
		this.usaCartaInimiga = usaCartaInimiga;
	}
	
	public abstract void ativarEfeito(Campo campo);
	
	protected void usuarioEscolherUnidade(String mensagemAoUsuario) {
		System.out.print(mensagemAoUsuario);
	}
	
	public TipoEfeito getTipoEfeito() {
		return tipoEfeito;
	}
	
	protected void setTipoEfeito(TipoEfeito tipo) {
		tipoEfeito = tipo;
	}

	public boolean requerCartaAliada() {
		return usaCartaAliada;
	}

	public boolean requerCartaInimiga() {
		return usaCartaInimiga;
	}
	
	public String getNome() {
		return nome;
	}
	
	protected void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getInfo() {
		return info;
	}
	
	protected void setInfo(String info) {
		this.info = info;
	}
}
