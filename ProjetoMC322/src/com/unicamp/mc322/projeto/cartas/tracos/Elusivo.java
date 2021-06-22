package com.unicamp.mc322.projeto.cartas.tracos;

import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;

public class Elusivo extends Traco{
	/*
	 * OBS: Achei estranho essa forma de implementação, mas é a única maneira q eu pensei sem fazer com q essa classe se torne meio inutil
	 * PAD falou q essa é melhor forma orientada a objetos.
	 */

	@Override
	public void ativarTraco(Seguidor cartaAtacante, Seguidor cartaDefensor, Jogador defensor) {
		if(cartaDefensor.getTipoTraco()==TipoTraco.ELUSIVO) {
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
