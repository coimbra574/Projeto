/* Um aliado atacante golpeia todos os oponentes defensores
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.Campo;
import com.unicamp.mc322.projeto.Rodada;
import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Seguidor;

public class Strike extends Efeito {
	private int unidade;
	
	public Strike() {
		super(true, true);
		setNome("Strike");
		setInfo("Um aliado atacante golpeia todos os oponentes defensores");
	}
	
	@Override
	public void ativarEfeito(Campo campo) {
		Rodada rodada = campo.getRodada();
		usuarioEscolherUnidade("Selecione a unidade o qual deseja ativar o efeito: ");
		Seguidor cartaSelecionada = (Seguidor) campo.selecionarUmaUnidadeAliada();
		ArrayList<Seguidor> inimigos = campo.getEvocadas(rodada.getNumeroJogadorOponente());
		
		for(Seguidor carta: inimigos) {
			carta.setVida(carta.getVida() - cartaSelecionada.getPoder());
		}
	}
	
}
