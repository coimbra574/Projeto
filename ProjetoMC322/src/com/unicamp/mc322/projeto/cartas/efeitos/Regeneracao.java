/* Cura totalmente uma carta aliada
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import java.util.Scanner;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.rodada.Rodada;

public class Regeneracao extends Efeito {
	private int unidade;
	
	public Regeneracao() {
		super(true, false);
		setNome("Regeneração");
		setInfo("Cura totalmente uma carta aliada");
	}
	
	@Override
	public void ativarEfeito(Campo campo) {
		Rodada rodada = campo.getRodada();
		System.out.println("Selecione a unidade o qual deseja ativar o efeito: ");
		Seguidor cartaSelecionada = campo.selecionarUmaUnidadeAliada();
		
		if(cartaSelecionada != null) {
			System.out.println("Efeito Regeneração ativado");
			int novaVida = cartaSelecionada.getVidaTotal();
			cartaSelecionada.setVida(novaVida);
		}
	}

}
