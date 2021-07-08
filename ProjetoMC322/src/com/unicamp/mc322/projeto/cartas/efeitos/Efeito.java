package com.unicamp.mc322.projeto.cartas.efeitos;

import java.util.Scanner;

import com.unicamp.mc322.projeto.campo.Campo;

public abstract class Efeito {
	private TipoAtivacao tipoAtivacao = TipoAtivacao.NA_COMPRA;
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
	
	public TipoAtivacao getTipoAtivacao() {
		return tipoAtivacao;
	}
	
	protected void setTipoEfeito(TipoAtivacao tipo) {
		tipoAtivacao = tipo;
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
