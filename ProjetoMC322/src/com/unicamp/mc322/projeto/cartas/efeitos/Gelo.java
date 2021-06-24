/*  Altera o poder de uma unidade pra 0 nesta rodada
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.Campo;
import com.unicamp.mc322.projeto.Rodada;
import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Seguidor;

public class Gelo extends Efeito {
	private int valorPoderOriginal=0, numeroCartaSelecionada=-1;; // Por conta desses parametros, pra cada carta precisamos instanciar os efeitos, msm que o mesmo efeito

	@Override
	public void ativarEfeito(Campo campo) {
		Rodada rodada = campo.getRodada();
		
		if(this.getTipoEfeito().equals(TipoEfeito.SELECIONADO)) {
			//numeroCartaSelecionada = rodada.getNumeroCartaSelecionada; // recebe através do resultado da rodada
			Seguidor carta = (Seguidor) campo.selecionarUmaUnidade(rodada.getNumeroJogadorOponente(), numeroCartaSelecionada);
			valorPoderOriginal = carta.getVida();
			carta.setPoder(0);  // Valor muito alto
			this.setTipoEfeito(TipoEfeito.AUTOMATICO);  // valor é atualizado ao original no final da rodada
		} else {
			Seguidor carta = (Seguidor) campo.selecionarUmaUnidade(rodada.getNumeroJogadorAtual(), numeroCartaSelecionada);
			carta.setVida(valorPoderOriginal);
			valorPoderOriginal = 0;
			numeroCartaSelecionada = -1;
			this.setTipoEfeito(TipoEfeito.SELECIONADO);
		}	
	}
	
}