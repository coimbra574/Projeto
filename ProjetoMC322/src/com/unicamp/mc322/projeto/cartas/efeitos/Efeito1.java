/* Adiciona +n de poder e +m de vida a todas as unidades aliadas evocadas
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;
import com.unicamp.mc322.projeto.main.Jogo;

public class Efeito1 extends Efeito {
	int n, m;
	
	public Efeito1(int n, int m) {
		this.n = n;
		this.m = m;
	}

	@Override
	public void ativarEfeito(Jogo jogo) {
/*		Jogador jogadorAtual = jogo.getJogadorAtacante();
		Seguidor carta;
		for(int i=0; i<6; i++) {
			carta = jogadorAtual.selecionarCartaEvocada(i);
			carta.setPoder(carta.getPoder() + n);
			carta.setMana(carta.getMana() + m);
		}
*/
	}

}
