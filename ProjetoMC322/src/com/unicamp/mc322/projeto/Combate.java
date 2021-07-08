package com.unicamp.mc322.projeto;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;
import java.util.ArrayList;

public class Combate {
	
	public static void iniciar(Campo campo, Jogador atacante, Jogador defensor) {
		int danoAoNexus = 0;
		ArrayList<Seguidor> emCampoAtacante = atacante.getEmCampo();
		ArrayList<Seguidor> emCampoDefensor = defensor.getEmCampo();
		
		for(int i = 0; i < campo.LARGURA_CAMPO; i++) {
			Seguidor cartaAtacante = emCampoAtacante.get(i);
			Seguidor cartaDefensor = emCampoDefensor.get(i);
			if(cartaAtacante != null) {
				if(cartaDefensor == null) {
					danoAoNexus += cartaAtacante.getPoder();
					atacante.adicionarEvocada(cartaAtacante);
					atacante.removerEmCampo(i);
				}
				else {
					cartaAtacante.setVida(cartaAtacante.getVida() - cartaDefensor.getPoder());
					cartaDefensor.setVida(cartaDefensor.getVida() - cartaAtacante.getPoder());
					atacante.removerEmCampo(i);
					defensor.removerEmCampo(i);
					if(cartaAtacante.getHaTraco()) {
						cartaAtacante.ativacaoTraco(cartaDefensor, defensor);//coloquei isso aqui para ativar o traco
					}
					if(cartaAtacante.getVida() > 0) {
						atacante.adicionarEvocada(cartaAtacante);
					}
					if(cartaDefensor.getVida() > 0) {
						defensor.adicionarEvocada(cartaDefensor);
					}
				}
			}
		}
		
		defensor.adicionarAoNexus(danoAoNexus * -1);
	}

}
