/* Cria uma barreira que anula o proximo dano que uma unidade levaria
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.Campo;
import com.unicamp.mc322.projeto.Rodada;
import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Seguidor;

public class Escudo extends Efeito {
	private int valorVidaOriginal=0, indexCartaEvocada=-1;
	private Seguidor carta;

	public Escudo() {
		super(true, false);
		setNome("Escudo");
		setInfo(" Cria uma barreira que anula o próximo dano que uma unidade levaria");
	}
	
	@Override
	public void ativarEfeito(Campo campo) {
		Rodada rodada = campo.getRodada();
		
		if(this.getTipoAtivacao().equals(TipoAtivacao.NA_COMPRA)) {
			System.out.println("\n Efeito Escudo ativado");
			carta = campo.selecionarUmaUnidadeAliada();
			indexCartaEvocada = campo.procurarCartaEvocada(rodada.getNumeroJogadorAtual(), carta);
			valorVidaOriginal = carta.getVida();
			carta.setVida(10000);  // Valor muito alto
			this.setTipoEfeito(TipoAtivacao.FIM_DA_RODADA);  // valor é atualizado ao original no final da rodada
		} else {
			System.out.println("\n Efeito Escudo desativado");
			carta.setVida(valorVidaOriginal);
			valorVidaOriginal = 0;
			indexCartaEvocada = -1;
			this.setTipoEfeito(TipoAtivacao.NA_COMPRA);
		}
	}
	
}
