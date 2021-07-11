/* ---------------------------------------------------------
 * Indica qual é a condição de evolução para a carta Campeao
 *   - Atacar n vezes
 *   - Matar n seguidores inimigos
 *   - Fazer n pontos de dano
 * 	 - Subir n pontos de dano
 * ---------------------------------------------------------
 */

package com.unicamp.mc322.projeto.cartas;

public enum Condicao {
	NUM_ATAQUE("ataques"),
	NUM_SEGUIDORES_MORTOS("seguidores mortos"),
	NUM_DANO_CAUSADO("pontos de dano causado"),
	NUM_DANO_ADD("pontos de dano adicionado"),
	SEM_EVOLUCAO("sem evolução");
	
	private String condicao;
	
	Condicao(String condicao){
		this.condicao = condicao;
	}
	
	@Override
	public String toString() {
		return condicao;
	}
}