/* --------------------------------------------------------------
 * Aqui uns exemplos de como as cartas vão ser instanciadas
 *
 * --------------------------------------------------------------
 */
package com.unicamp.mc322.projeto.main;

import com.unicamp.mc322.projeto.cartas.Campeao;
import com.unicamp.mc322.projeto.cartas.Condicao;
import com.unicamp.mc322.projeto.cartas.DadosEvolucao;
import com.unicamp.mc322.projeto.cartas.Feitico;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.cartas.efeitos.GolpeAoNexus;
import com.unicamp.mc322.projeto.cartas.efeitos.Melhoria;
import com.unicamp.mc322.projeto.cartas.efeitos.MelhoriaATodos;
import com.unicamp.mc322.projeto.cartas.tracos.Furia;

public class Runner {

	public static void main(String[] args) {

		// Efeitos e Tracos
		Furia traco1 = new Furia(1,1);
		MelhoriaATodos efeito1 = new MelhoriaATodos(1,2);
		Melhoria efeito1_2 = new Melhoria(3,4);
		GolpeAoNexus efeito3 = new GolpeAoNexus();
		
		/* Carta 1: Campeão Garen
		 * Características:
		 */
		DadosEvolucao evolucaoGaren = new DadosEvolucao(Condicao.NUM_ATAQUE, 2, 1, 1, traco1);
		Campeao garen = new Campeao("Garen", 2, 4, 3, evolucaoGaren, null, efeito1);
		
		/* Carta 2: Seguidor Tiana
		 * Características:
		 */
		Seguidor tiana = new Seguidor("Tiana", 8, 7, 7, null, efeito1);
		
		/* Carta 3: Feitiço Julgamento
		 * Características:
		 */
		Feitico julgamento = new Feitico("Julgamento", 4, efeito1_2);

	}

}
