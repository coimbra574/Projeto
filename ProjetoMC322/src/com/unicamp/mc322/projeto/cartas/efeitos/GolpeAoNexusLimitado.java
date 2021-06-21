/* Golpeia o Nexus do adversário em n pontos de dano
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.Campo;
import com.unicamp.mc322.projeto.Rodada;

public class GolpeAoNexusLimitado extends Efeito {
	private int n;
	
	public GolpeAoNexusLimitado(int n) {
		this.n = n;
	}
	
	@Override
	public void ativarEfeito(Campo campo) {
		Rodada rodada = campo.getRodada();
		campo.adicionarAoNexusJogador(rodada.getNumeroJogadorOponente(), -n);
	}
}
