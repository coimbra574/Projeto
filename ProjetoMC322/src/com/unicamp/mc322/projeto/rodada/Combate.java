package com.unicamp.mc322.projeto.rodada;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.Campeao;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.cartas.TipoCarta;
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
						cartaAtacante.ativacaoTraco(cartaDefensor, defensor, campo);//coloquei isso aqui para ativar o traco
					}
					
					if(cartaAtacante.getVida() <= 0) {
						cartaDefensor.setMatouUmSeguidor(true);
					}

					if(cartaDefensor.getVida() <= 0) {
						cartaAtacante.setMatouUmSeguidor(true);
					}
					
					verificarCampeao(cartaAtacante);
					verificarCampeao(cartaDefensor);
					
					atacante.adicionarEvocada(cartaAtacante);
					defensor.adicionarEvocada(cartaDefensor);
				}
			}
		}
		
		defensor.adicionarAoNexus(danoAoNexus * -1);
	}
	

	private static void verificarCampeao(Seguidor carta) {
		if(carta.getTipo() == TipoCarta.CAMPEAO) {
			Campeao campeao = (Campeao) carta;
			
			campeao.incrementarDanoCausado(campeao.getPoder());
			campeao.incrementarNumAtaques();
			
			if(campeao.getMatouUmSeguidor()) {
				campeao.incrementarSeguidoresMortos();
			}
			
			int danoAdicionado = campeao.getVidaTotal() - campeao.getVida();
			if(danoAdicionado > 0) {
				campeao.incrementarDanoAdicionado(danoAdicionado);
			}
		}
	}

}
