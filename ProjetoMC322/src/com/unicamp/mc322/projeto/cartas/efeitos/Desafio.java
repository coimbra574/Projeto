/* Escolha uma carta aliada e uma oponente para um combate imediato
 * 
 */
package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;
import com.unicamp.mc322.projeto.rodada.Combate;
import com.unicamp.mc322.projeto.rodada.Rodada;

public class Desafio extends Efeito {
	
	public Desafio() {
		super(true, true);
		setNome("Desafio");
		setInfo("Escolha uma carta aliada e uma oponente para um combate imediato");
	}
	
	@Override
	public void ativarEfeito(Campo campo) {		
		System.out.println("\n Efeito Desafio ativado");
		Rodada rodada = campo.getRodada();
		Jogador jogadorAtual = rodada.getJogador(rodada.getNumeroJogadorAtual());
		Jogador jogadorOponente = rodada.getJogador(rodada.getNumeroJogadorOponente());
		Combate.iniciar(campo, jogadorAtual, jogadorOponente);
	}
}
