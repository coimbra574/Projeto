/* Escolha uma carta aliada e uma oponente para um combate imediato
 * 
 */
package com.unicamp.mc322.projeto.cartas.efeitos;

import java.util.Scanner;
import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.main.Campo;

public class Desafio extends Efeito {
	private int unidadeAliada, unidadeOponente;
	
	@Override
	public void ativarEfeito(Campo campo) {
		this.unidadeAliada = usuarioEscolherUnidade("Selecione uma unidade aliada para o combate: ");
		this.unidadeOponente = usuarioEscolherUnidade("Selecione uma unidade oponente para o combate: ");
		
		Seguidor cartaSelecionadaAliada = campo.selecionarUnidade(campo.getNumeroJogadorAtual(), unidadeAliada);
		Seguidor cartaSelecionadaOponente = campo.selecionarUnidade(campo.getNumeroJogadorOponente(), unidadeOponente);
		
		//combate(cartaSelecionadaAliada, cartaSelecionadaOponente);
	}
}
