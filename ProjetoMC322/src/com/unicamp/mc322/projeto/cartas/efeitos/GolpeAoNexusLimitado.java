/* Golpeia o Nexus do adversário em n pontos de dano
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.main.Campo;

public class GolpeAoNexusLimitado extends Efeito {
	private int n;
	
	public GolpeAoNexusLimitado(int n) {
		this.n = n;
	}
	
	@Override
	public void ativarEfeito(Campo campo) {
		campo.adicionarAoNexus(campo.getNumeroJogadorOponente(), -n);
	}

}
