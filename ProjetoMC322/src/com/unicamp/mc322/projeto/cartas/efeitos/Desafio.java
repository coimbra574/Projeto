/* Escolha uma carta aliada e uma oponente para um combate imediato
 * 
 */
package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.Campo;
import com.unicamp.mc322.projeto.Rodada;
import com.unicamp.mc322.projeto.cartas.Seguidor;

public class Desafio extends Efeito {
	private int unidadeAliada, unidadeOponente;
	
	@Override
	public void ativarEfeito(Campo campo) {
		this.unidadeAliada = usuarioEscolherUnidade("Selecione uma unidade aliada para o combate: ");
		this.unidadeOponente = usuarioEscolherUnidade("Selecione uma unidade oponente para o combate: ");
		
		Rodada rodada = campo.getRodada();
		Seguidor cartaSelecionadaAliada = (Seguidor) campo.selecionarUmaUnidade(rodada.getNumeroJogadorAtual(), unidadeAliada);
		Seguidor cartaSelecionadaOponente = (Seguidor) campo.selecionarUmaUnidade(rodada.getNumeroJogadorOponente(), unidadeOponente);
		
		//combate(cartaSelecionadaAliada, cartaSelecionadaOponente);
	}
}
