package com.unicamp.mc322.projeto.cartas.tracos;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;

public class Elusivo extends Traco{

	@Override
	public void ativarTraco(Seguidor cartaAtacante, Seguidor cartaDefensor, Jogador defensor, Campo campo) {
		if(cartaDefensor.getTipoTraco()!=TipoTraco.ELUSIVO) {
			cartaAtacante.setVida(cartaAtacante.getVida() + cartaDefensor.getPoder());
			cartaDefensor.setVida(cartaDefensor.getVida() + cartaAtacante.getPoder());
			defensor.adicionarAoNexus(-1*cartaAtacante.getPoder());
			
		}
	}
	
	@Override
	public TipoTraco getTraco() {
		return TipoTraco.ELUSIVO;
	}
}
