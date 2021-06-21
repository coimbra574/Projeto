/* Um aliado atacante golpeia todos os oponentes defensores
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.Campo;
import com.unicamp.mc322.projeto.Rodada;
import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Seguidor;

public class Strike extends Efeito {
	private int unidade;
	
	@Override
	public void ativarEfeito(Campo campo) {
		Rodada rodada = campo.getRodada();
		this.unidade = usuarioEscolherUnidade("Selecione a unidade o qual deseja ativar o efeito: ");
		Seguidor cartaSelecionada = (Seguidor) campo.selecionarUmaUnidade(rodada.getNumeroJogadorAtual(), unidade);

		for(int i=1; i<=campo.LARGURA_CAMPO; i++) {  // Tá certo chamar uma constante publica assim?
			Seguidor cartaOponente = (Seguidor) campo.selecionarUmaUnidade(rodada.getNumeroJogadorOponente(), i);
			if(cartaOponente != null) {
				// Atacar 
				campo.alterarCartaEmCampo(rodada.getNumeroJogadorOponente(), i, Caracteristica.VIDA, cartaOponente.getVida() - cartaSelecionada.getPoder());
			}
		}
	}
	
}
