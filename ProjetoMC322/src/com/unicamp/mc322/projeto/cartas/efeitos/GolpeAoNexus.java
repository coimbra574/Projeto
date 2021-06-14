/* Uma unidade evocada ataca nexus do oponente
 * 
 */
package com.unicamp.mc322.projeto.cartas.efeitos;

import java.util.Scanner;

import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.main.Campo;

public class GolpeAoNexus extends Efeito {
	private int unidade;
	private GolpeAoNexusLimitado golpeNexus;
	
	@Override
	public void ativarEfeito(Campo campo) {
		this.unidade = usuarioEscolherUnidade("Selecione a unidade o qual deseja ativar o efeito: ");
		Seguidor cartaSelecionada = campo.selecionarUnidade(campo.getNumeroJogadorAtual(), unidade);
		golpeNexus = new GolpeAoNexusLimitado(cartaSelecionada.getPoder());
		golpeNexus.ativarEfeito(campo);
	}

}
