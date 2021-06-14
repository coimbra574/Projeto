/* Adiciona +n de poder e +m de vida a todas as unidades aliadas evocadas
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;
import com.unicamp.mc322.projeto.main.Campo;

public class MelhoriaATodos extends Efeito {
	int n, m;
	
	public MelhoriaATodos(int n, int m) {
		this.n = n;
		this.m = m;
	}

	@Override
	public void ativarEfeito(Campo campo) {
		for(int i=1; i<=campo.LARGURA_CAMPO; i++) {  // Tá certo chamar uma constante publica assim?
			Seguidor cartaSelecionada = campo.selecionarUnidade(campo.getNumeroJogadorAtual(), i);
			if(cartaSelecionada != null) {
				int novoPoder = cartaSelecionada.getPoder() + n;
				int novaVida = cartaSelecionada.getVida() + m;
				campo.alterarCartaEmCampo(campo.getNumeroJogadorAtual(), i, Caracteristica.PODER, novoPoder);
				campo.alterarCartaEmCampo(campo.getNumeroJogadorAtual(), i, Caracteristica.VIDA, novaVida);
			}
		}
	}

}
