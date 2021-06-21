/* Adiciona +n de poder e +m de vida a uma unidade evocada selecionada.
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.Campo;
import com.unicamp.mc322.projeto.Rodada;
import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Seguidor;

import java.util.*;

public class Melhoria extends Efeito {
	int n, m, unidade;
	
	public Melhoria(int n, int m) {
		this.n = n;
		this.m = m;
	}

	@Override
	public void ativarEfeito(Campo campo) {
		this.unidade = usuarioEscolherUnidade("Selecione a unidade o qual deseja ativar o efeito: ");
		Rodada rodada = campo.getRodada();
		Seguidor cartaSelecionada = (Seguidor) campo.selecionarUmaUnidade(rodada.getNumeroJogadorAtual(), unidade);
		
		if(cartaSelecionada != null) {
			int novoPoder = cartaSelecionada.getPoder() + n;
			int novaVida = cartaSelecionada.getVida() + m;
			campo.alterarCartaEmCampo(rodada.getNumeroJogadorAtual(), unidade, Caracteristica.PODER, novoPoder);
			campo.alterarCartaEmCampo(rodada.getNumeroJogadorAtual(), unidade, Caracteristica.VIDA, novaVida);
		}
	}

}
