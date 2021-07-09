package com.unicamp.mc322.projeto.cartas.tracos;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;

public class Furia extends Traco {
	private int n, m;
	
	public Furia(int n, int m) {
		this.n = n;//pontos de poder
		this.m = m;//pontos de vida
	}

	@Override
	public void ativarTraco(Seguidor cartaAtacante, Seguidor cartaDefensor, Jogador defensor, Campo campo) {
		if(cartaDefensor.getVida()<=0) {
			cartaAtacante.aumentarPoder(n);
			cartaAtacante.aumentarVida(m);
		}
	}

	@Override
	public TipoTraco getTraco() {
		return TipoTraco.FURIA;
	}
}
