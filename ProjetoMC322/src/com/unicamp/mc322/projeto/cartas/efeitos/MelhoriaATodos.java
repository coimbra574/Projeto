/* Adiciona +n de poder e +m de vida a todas as unidades aliadas evocadas
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;
import com.unicamp.mc322.projeto.rodada.Rodada;

public class MelhoriaATodos extends Efeito {
	int n, m;
	
	public MelhoriaATodos(int n, int m) {
		super(true, false);
		this.n = n;
		this.m = m;
		setNome("Melhoria a todos");
		setInfo("Adiciona +"+n+" de poder e +"+m+" de vida a todas as unidades aliadas");
	}

	@Override
	public void ativarEfeito(Campo campo) {
		System.out.println("Efeito Melhoria a Todos ativado");
		int novoPoder;
		int novaVida;
		Rodada rodada = campo.getRodada();
		ArrayList<Seguidor> evocadas = campo.getEvocadas(rodada.getNumeroJogadorAtual());
		for(Seguidor carta: evocadas) {
			novoPoder = carta.getPoder() + n;
			novaVida = carta.getVida() + m;
			carta.setPoder(novoPoder);
			carta.setVida(novaVida);
		}
	}

}
