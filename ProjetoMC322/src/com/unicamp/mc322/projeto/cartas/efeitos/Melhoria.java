/* Adiciona +n de poder e +m de vida a uma unidade evocada selecionada.
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.main.Campo;
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
		Seguidor cartaSelecionada = campo.selecionarUnidade(campo.getNumeroJogadorAtual(), unidade);
		
		if(cartaSelecionada != null) {
			int novoPoder = cartaSelecionada.getPoder() + n;
			int novaVida = cartaSelecionada.getVida() + m;
			campo.alterarCartaEmCampo(campo.getNumeroJogadorAtual(), unidade, Caracteristica.PODER, novoPoder);
			campo.alterarCartaEmCampo(campo.getNumeroJogadorAtual(), unidade, Caracteristica.VIDA, novaVida);
		}
	}

}
