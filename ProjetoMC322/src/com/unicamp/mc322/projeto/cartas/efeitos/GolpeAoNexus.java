/* Uma unidade evocada ataca nexus do oponente
 * 
 */
package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.Campo;
import com.unicamp.mc322.projeto.Rodada;
import com.unicamp.mc322.projeto.cartas.Seguidor;

public class GolpeAoNexus extends Efeito {
	private int unidade;
	private GolpeAoNexusLimitado golpeNexus;
	
	@Override
	public void ativarEfeito(Campo campo) {
		this.unidade = usuarioEscolherUnidade("Selecione a unidade o qual deseja ativar o efeito: ");
		Rodada rodada = campo.getRodada();
		Seguidor cartaSelecionada = (Seguidor) campo.selecionarUmaUnidade(rodada.getNumeroJogadorAtual(), unidade);
		golpeNexus = new GolpeAoNexusLimitado(cartaSelecionada.getPoder());
		golpeNexus.ativarEfeito(campo);
	}

}
