package com.unicamp.mc322.projeto.cartas.efeitos;

import java.util.Scanner;

import com.unicamp.mc322.projeto.main.Campo;

public abstract class Efeito {
	
	public void ativarEfeito(Campo campo) {}
	
	public int usuarioEscolherUnidade(String mensagemAoUsuario) {
		Scanner recebeUnidade = new Scanner(System.in);    //System.in is a standard input stream  
		System.out.print(mensagemAoUsuario);  
		return recebeUnidade.nextInt();
	}
}
