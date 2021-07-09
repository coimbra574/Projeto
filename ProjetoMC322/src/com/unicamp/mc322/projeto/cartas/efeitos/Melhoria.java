/* Adiciona +n de poder e +m de vida a uma unidade evocada selecionada.
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.rodada.Rodada;

import java.util.*;

public class Melhoria extends Efeito {
	int n, m, unidade;
	
	public Melhoria(int n, int m) {
		super(true, false);
		this.n = n;
		this.m = m;
		setNome("Melhoria");
		setInfo("Adiciona +"+n+" de poder e +"+m+" de vida a uma unidade selecionada");
	}

	@Override
	public void ativarEfeito(Campo campo) {
		System.out.println("Selecione a unidade o qual deseja ativar o efeito: ");
		Rodada rodada = campo.getRodada();
		Seguidor cartaSelecionada = campo.getJogadorAtual().selecionarUmaUnidadeAliada(campo);
		
		if(cartaSelecionada != null) {
			int novoPoder = cartaSelecionada.getPoder() + n;
			int novaVida = cartaSelecionada.getVida() + m;
			cartaSelecionada.setPoder(novoPoder);
			cartaSelecionada.setVida(novaVida);
		}
	}

}
