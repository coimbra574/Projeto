/* Ao ser destruido, ganha uma carta
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.Rodada;
import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;

public class TrocaJusta extends Efeito {
	
	public TrocaJusta() {
		super(true, false);
		super.setTipoEfeito(TipoAtivacao.FIM_DA_RODADA);
		setNome("Troca Justa");
		setInfo("Ao ser destruído voce ganha uma carta");
	}

	@Override
	public void ativarEfeito(Campo campo) {
		Rodada rodada = campo.getRodada();
		Jogador jogador = rodada.getJogador(rodada.getNumeroJogadorAplicarEfeito());
		int numeroCarta = rodada.getIndexCartaAplicarEfeito();
		Seguidor carta = jogador.getEvocadas().get(numeroCarta);
		
		if(carta.getVida() == 0) {
			System.out.println("\n Efeito Troca Justa ativado");
			jogador.pegarCarta();
		}
	}
	
}