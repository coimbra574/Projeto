/* Dobra o ataque e a defesa de uma carta aliada
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.Rodada;
import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.Seguidor;

public class Dobra extends Efeito {

	public Dobra() {
		super(true, false);
		setNome("Dobra");
		setInfo(" Dobra o ataque e a defesa de uma carta aliada");
	}

	@Override
	public void ativarEfeito(Campo campo) {
		System.out.println("Selecione a unidade o qual deseja ativar o efeito: ");
		Seguidor cartaSelecionada = campo.selecionarUmaUnidadeAliada();
		System.out.println("\n Efeito Dobra ativado");
		cartaSelecionada.setPoder(2*cartaSelecionada.getPoder());
		cartaSelecionada.setVida(2*cartaSelecionada.getVida());
	}
	
}
