/* Um aliado atacante golpeia todos os oponentes defensores
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import java.util.ArrayList;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.rodada.Rodada;

public class Strike extends Efeito {
	
	public Strike() {
		super(true, true);
		setNome("Strike");
		setInfo("Um aliado atacante golpeia todos os oponentes defensores");
	}
	
	@Override
	public void ativarEfeito(Campo campo) {
		Rodada rodada = campo.getRodada();
		System.out.println("Selecione a unidade o qual deseja ativar o efeito: ");
		Seguidor cartaSelecionada = (Seguidor) campo.getJogadorAtual().selecionarUmaUnidadeAliada(campo);
		System.out.println("\n Efeito Strike ativado");
		ArrayList<Seguidor> inimigos = campo.getEvocadas(rodada.getNumeroJogadorOponente());
		
		for(Seguidor carta: inimigos) {
			carta.setVida(carta.getVida() - cartaSelecionada.getPoder());
		}
	}
	
}
