/* Uma unidade evocada ataca nexus do oponente
 * 
 */
package com.unicamp.mc322.projeto.cartas.efeitos;

import com.unicamp.mc322.projeto.Campo;
import com.unicamp.mc322.projeto.Rodada;
import com.unicamp.mc322.projeto.cartas.Seguidor;

public class GolpeAoNexus extends Efeito {
	private int unidade;
	private GolpeAoNexusLimitado golpeNexus;
	
	public GolpeAoNexus() {
		super(true, false);
		setNome("Golpe ao  Nexus");
		setInfo("Uma unidade evocada ataca o nexus do oponente");
	}
	
	@Override
	public void ativarEfeito(Campo campo) {
		//this.unidade = usuarioEscolherUnidade("Selecione a unidade o qual deseja ativar o efeito: ");
		Rodada rodada = campo.getRodada();
		Seguidor cartaSelecionada = campo.selecionarUmaUnidadeAliada();
		golpeNexus = new GolpeAoNexusLimitado(cartaSelecionada.getPoder());
		golpeNexus.ativarEfeito(campo);
	}
	
	@Override
	public String toString() {
		String texto = "";
		return texto;
	}

}
