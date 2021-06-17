/* Adiciona +n de poder e +m de vida a todas as unidades aliadas evocadas
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.Campo;
import com.unicamp.mc322.projeto.Rodada;
import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;

public class MelhoriaATodos extends Efeito {
	int n, m;
	
	public MelhoriaATodos(int n, int m) {
		this.n = n;
		this.m = m;
	}

	@Override
	public void ativarEfeito(Campo campo) {
		Rodada rodada = campo.getRodada();
		for(int i=1; i<=campo.LARGURA_CAMPO; i++) {  // Tá certo chamar uma constante publica assim?
			Seguidor cartaSelecionada = (Seguidor) campo.selecionarUmaUnidade(rodada.getNumeroJogadorAtual(), i);
			if(cartaSelecionada != null) {
				int novoPoder = cartaSelecionada.getPoder() + n;
				int novaVida = cartaSelecionada.getVida() + m;
				campo.alterarCartaEmCampo(rodada.getNumeroJogadorAtual(), i, Caracteristica.PODER, novoPoder);
				campo.alterarCartaEmCampo(rodada.getNumeroJogadorAtual(), i, Caracteristica.VIDA, novaVida);
			}
		}
	}

}
