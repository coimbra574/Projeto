/* Ao ser destruido, compra una carta
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.Campo;
import com.unicamp.mc322.projeto.Rodada;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;

public class TrocaJusta extends Efeito {
	
	public TrocaJusta() {
		super(true, false);
		super.setTipoEfeito(TipoEfeito.AUTOMATICO);
		setNome("Troca Justa");
		setInfo("Ao ser destruído, compra uma carta");
	}

	@Override
	public void ativarEfeito(Campo campo) {
		/*//numeroCartaSelecionada = rodada.getNumeroCartaSelecionada; // recebe através do resultado da rodada
		Rodada rodada = campo.getRodada();
		Seguidor cartaSelecionada = campo.selecionarUmaUnidade(rodada.getNumeroJogadorAtual(), numeroCartaSelecionada);
		
		if(cartaSelecionada.getVida() == 0) {
			Jogador jogadorAtual = rodada.getJogador(rodada.getNumeroJogadorAtual());
			jogadorAtual.pegarCarta();
		}*/
	}
	
}