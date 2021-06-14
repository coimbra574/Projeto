/* Cura totalmente uma carta aliada
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import java.util.Scanner;
import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.main.Campo;

public class Regeneracao extends Efeito {
	private int unidade;

	@Override
	public void ativarEfeito(Campo campo) {
		this.unidade = usuarioEscolherUnidade("Selecione a unidade o qual deseja ativar o efeito: ");
		Seguidor cartaSelecionada = campo.selecionarUnidade(campo.getNumeroJogadorAtual(), unidade);
		
		if(cartaSelecionada != null) {
			int novaVida = cartaSelecionada.getVidaTotal();
			campo.alterarCartaEmCampo(campo.getNumeroJogadorAtual(), unidade, Caracteristica.VIDA, novaVida);
		}
	}

}
