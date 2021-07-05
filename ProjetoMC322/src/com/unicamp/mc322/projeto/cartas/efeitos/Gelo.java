/*  Altera o poder de uma unidade pra 0 nesta rodada
 * 
 */

package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.Campo;
import com.unicamp.mc322.projeto.Rodada;
import com.unicamp.mc322.projeto.cartas.Caracteristica;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;

public class Gelo extends Efeito {
	private int valorPoderOriginal=0, indexCartaEvocada=-1; // Por conta desses parametros, pra cada carta precisamos instanciar os efeitos, msm que o mesmo efeito
	private Seguidor carta;
	
	public Gelo() {
		super(false, true);
		setNome("Gelo");
		setInfo(" Altera o poder de uma unidade para 0 nesta rodada");
	}
	
	@Override
	public void ativarEfeito(Campo campo) {
		Rodada rodada = campo.getRodada();
		
		if(this.getTipoAtivacao().equals(TipoAtivacao.NA_COMPRA)) {
			System.out.println("\n Efeito Gelo ativado");
			carta = campo.selecionarUmaUnidadeInimiga();
			indexCartaEvocada = campo.procurarCartaEvocada(rodada.getNumeroJogadorOponente(), carta);
			valorPoderOriginal = carta.getPoder();
			carta.setPoder(0);  
			this.setTipoEfeito(TipoAtivacao.FIM_DA_RODADA);  // valor é atualizado ao original no final da rodada
		} else {
			System.out.println("\n Efeito Gelo desativado");
			carta.setPoder(valorPoderOriginal);
			valorPoderOriginal = 0;
			indexCartaEvocada = -1;
			this.setTipoEfeito(TipoAtivacao.NA_COMPRA);
		}	
	}
	
}