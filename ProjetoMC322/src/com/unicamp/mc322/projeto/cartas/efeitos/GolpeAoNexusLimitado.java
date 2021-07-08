/* Golpeia o Nexus do adversário em n pontos de dano
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.Rodada;
import com.unicamp.mc322.projeto.campo.Campo;

public class GolpeAoNexusLimitado extends Efeito {
	private int n;
	
	public GolpeAoNexusLimitado(int n) {
		super(false, false);
		this.n = n;
		setNome("Golpe ao Nexus Limitado");
		setInfo("Golpeia o nexus do adversário em "+n+" ponto(s) de dano");
	}
	
	@Override
	public void ativarEfeito(Campo campo) {
		Rodada rodada = campo.getRodada();
		campo.adicionarAoNexusJogador(rodada.getNumeroJogadorOponente(), -n);
	}
}
