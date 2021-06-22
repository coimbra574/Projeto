package com.unicamp.mc322.projeto.cartas.tracos;

import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;

public abstract class Traco {
	
	public abstract void ativarTraco(Seguidor cartaAtacante, Seguidor cartaDefensor, Jogador defensor);
	
	public abstract TipoTraco getTraco();
}
