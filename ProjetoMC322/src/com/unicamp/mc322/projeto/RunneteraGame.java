package com.unicamp.mc322.projeto;

import java.util.Random;
import java.util.Scanner;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.jogador.*;
import com.unicamp.mc322.projeto.rodada.Turno;

public class RunneteraGame {
	private ModoDeJogo modoDeJogo;
	private Jogador player1;
	private Jogador player2;
	private Campo campoBatalha;
	
	public void runGame() {
		
		System.out.println("Bem Vindo ao Jogo!");
		
		escolherModo();
		
		criarJogadores();
		
		campoBatalha = new Campo(player1, player2);
		
		System.out.println("Jogo Iniciado!");
		
		campoBatalha.iniciar();
	}
	
	private void escolherModo() {
		Scanner keyboard = new Scanner(System.in);
		int escolha;
		boolean escolhaValida;
		
		do{
			System.out.println("Escolha um modo de jogo:");
			System.out.println("Digite [1] - Humano vs Humano");
			System.out.println("Digite [2] - Humano vs Computador");
			System.out.println("Digite [3] - Computador vs Computador");
			
			escolhaValida = true;
			
			try {
				escolha = Integer.valueOf(keyboard.nextLine());
				if(escolha == 1) {
					modoDeJogo = ModoDeJogo.HUMANOXHUMANO;
				}
				else if(escolha == 2) {
					modoDeJogo = ModoDeJogo.HUMANOXCOMPUTADOR;
				}
				else if(escolha == 3) {
					modoDeJogo = ModoDeJogo.COMPUTADORXCOMPUTADOR;
				}
				else {
					System.out.println("Entrada Invalida!");
					escolhaValida = false;
				}
			} catch (NumberFormatException ex) {
				System.out.println("Entrada invalida. Digite um numero!\n");
				escolhaValida = false;
			}
		}while(escolhaValida == false);
	}
	
	private void criarJogadores() {
		Turno turno[] = turnoInicial();
		if(modoDeJogo == ModoDeJogo.HUMANOXHUMANO) {
			player1 = new Humano(turno[0]);
			player2 = new Humano(turno[1]);
		}
		else if(modoDeJogo == ModoDeJogo.HUMANOXCOMPUTADOR) {
			player1 = new Humano(turno[0]);
			player2 = new Maquina(turno[1]);
		}
		else {
			player1 = new Maquina(turno[0]);
			player2 = new Maquina(turno[1]);
		}
	}
	
	private Turno[] turnoInicial() {
		Random geradorAleatorio = new Random();
		Turno[] turno = new Turno[2];
		int aleatorio = geradorAleatorio.nextInt(100);
		if(aleatorio >= 50) {
			turno[0] = Turno.ATAQUE;
			turno[1] = Turno.DEFESA;
			return turno;
		}
		turno[0] = Turno.DEFESA;
		turno[1] = Turno.ATAQUE;
		return turno;
	}
}
