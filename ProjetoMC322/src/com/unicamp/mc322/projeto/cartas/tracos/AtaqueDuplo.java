package com.unicamp.mc322.projeto.cartas.tracos;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;

public class AtaqueDuplo extends Traco{

	@Override
	public void ativarTraco(Seguidor cartaAtacante, Seguidor cartaDefensor, Jogador defensor, Campo campo) {
		if(cartaDefensor==null){
			defensor.adicionarAoNexus(-1*cartaAtacante.getPoder());
			campo.adicionarAoNexus(2, -1*cartaAtacante.getPoder());
		}else {
			cartaDefensor.setVida(cartaDefensor.getVida() - cartaAtacante.getPoder());
		}
	
	}
	
	@Override
	public TipoTraco getTraco() {
		return TipoTraco.ATAQUEDUPLO;
	}
}
