package com.unicamp.mc322.projeto.jogador;

import java.util.Scanner;
import com.unicamp.mc322.projeto.deckFactory.TipoDeck;

public class EscolhaTipoDeck {
	int escolha;
	Scanner teclado = new Scanner(System.in);
	TipoDeck LeituraTipo() {
		boolean continuar = true;
		
		try {
			while(continuar) {
				 System.out.println("Qual deck escolherá? ");
				 System.out.println("Digite 3 para ajuda");
				 escolha = Integer.valueOf(teclado.nextLine());
				 if(escolha == 1) {
					 return TipoDeck.MAGICO;
				 }else if(escolha == 2) {
					 return TipoDeck.LUTADOR;
				 }else if(escolha == 3) {
					 imprimirAjuda();
				 }else {
					 return TipoDeck.NAOEXISTENTE;
				 }
			}
			return null;
		}catch(NumberFormatException e) {
			System.out.println("Foi fornecido um argumento inválido para escolha do deck");
			System.out.println("Por padrão será criado um deck do tipo Lutador");
			return TipoDeck.LUTADOR;
		}
	 }
	
	private void imprimirAjuda() {
		System.out.print("Para criar um deck do tipo mágico digite: 1\n"+
						"Para criar um deck do tipo Lutador digite: 2\n" +
						"Para ajuda digite: 3\n" +
						"Qualquer outro valor resultará em um deck padrão do tipo Lutador\n");
	}
}
