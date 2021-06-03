package com.unicamp.mc322.projeto.jogador;

import com.unicamp.mc322.projeto.turno.*;
import java.util.Scanner;
import java.util.ArrayList;

public abstract class Jogador {
	private int mana = 0;
	private int nexus = 20;
	private int manaDeFeitico = 0;
	private Turno turno;
	private Scanner teclado = new Scanner(System.in);
	private ArrayList<Carta> mao = new ArrayList<Carta>();  //Irá representar as cartas na mão do jogador
	private Deck deckJogador;
	
	Jogador(Turno turnoInicial) {
		/*
		 * Arranjar um meio de randomizar quem vai atacar primeiro
		 */
		super();
		this.turno = turnoInicial;
		this.mao = obter4CartasIniciais();
	}
	
	private ArrayList<Carta> obter4CartasIniciais() {
		/*
		 * Esse método deve ser responsável por obter as quatro cartas inciais do jogador
		 */
		//Mostrar as cartas para o jogador
		mao = deckJogador.obterCartasIniciais();
		mao = substituirCartas(mao);
	}
	
	private ArrayList<Carta> substituirCartas(mao) {
		/*
		 * Esse método deverá ser responsável por substituir as cartas que o jogador não desejar
		 */
		int indiceCarta;
		int numCartas;
		
		do {
			System.out.print("Quantas cartas deseja substituir? ");
			numCartas =  Integer.valueOf(teclado.nextLine());
			if(numCartas>4) {
				System.out.println("Digite um número de cartas válido!!! Até 4 cartas");
			}	
		}while(numCartas>4);
		
		
		for(int i=0;i<numCartas;i++) {
			Carta novaCarta;
			
			System.out.print("Qual carta deseja substituir? ");
			indiceCarta =  Integer.valueOf(teclado.nextLine());
			novaCarta = deckJogador.pegarCartaAleatoriaDeck(); // Esses três métodos a serem chamados serão da classe deck, imagino
			deckJogador.recolocarNoBaralho(mao.get(indiceCarta));
			mao.get(indiceCarta) = novaCarta;
		}
		
		return mao;
	}
	
	public void pegarCarta() {
		/*
		 * O método deve chamar um método do deck para obter uma carta do mesmo e colocá-lo na sua mao, todo começo de turno de ataque
		 */
		Carta carta;
		carta = deckJogador.obterCartaDeck()//método do deck que retorna uma carta
		if(carta ==  null) {
			//perdeu o jogo, não tem mais cartas
		}else {
			mao.add(carta);
		}
	}
	
	private void mudarTurno() {
		/*
		 * Deve mudar a indicação de se o jogador esta no turno de ataque ou o turno de defesa
		 */
		if(turno == Turno.DEFESA) {
			turno = Turno.ATAQUE;
		}else {
			turno = Turno.DEFESA;
		}
	}
	
	public void atualizarMana(int numRodada) {
		/*
		 * O método deve atualizar a quantidade de mana do jogador a cada rodada
		 */
		if(numRodada<=10) {
			this.mana = numRodada;
		}else {
			this.mana = 10;
		}
	}
	
	public void escolherCartaUtilizar() {
		/*
		 * O jogador deve escolher as cartas na mão que irá invocar ou ativar o efeito
		 */
		do {
			boolean continuar=false;
			System.out.println("Escolha uma carta para utilizar");
			int cartaParaJogar;
			do {
				cartaParaJogar=  Integer.valueOf(teclado.nextLine());
				verificarCarta(mao.get(cartaParaJogar));
			}while(cartaParaJogar>mao.size());
			if(mana>0) {
				System.out.print("Deseja continuar? ");
				continuar =  Boolean.valueOf(teclado.nextLine());//ver aqui se da pra melhoras sla
			}
		}while(mana>0 && continuar);
		
	}
	
	private boolean verificarCarta(Carta carta) {
		/*
		 * Verificar se o jogadore possui mana suficiente para invocar/ativar uma carta
		 */
		if(mana>carta.getMana()) {
			if(carta.getTipo().equals("Campeão") || carta.getTipo().equals("Seguidor")) {
				invocarCarta();
				mana-=carta.getMana();
			}else {
				ativarEfeito()
				mana-=carta.getMana();
			}
			return true;
		}else {
			return false;
		}
	}
	
	private void invocarCarta(Carta carta) {
		/*
		 * Esse método depende da implementação do campo de invocações, deverá invocá-lo nele 
		 */
	}
	
	private void ativarEfeito() {
		/*
		 * Deve ativar o efeito de uma carta
		 */
	}
	
	public void diminuirNexus(int dano) {
		this.nexus-=dano;
	}
	
	public void atacar(Jogador oponente) {
		/*
		 * Esse metodo tem acesso ao campo de invocações para selecionar os combatentes, após isso chama a 
		 * instancia da classe combate onde será feita a batalha
		 */
		ArrayList<Carta> unidadesCombatentes = campo.selecionarUnidades();
		ArrayList<Carta> unidadesDefensoras = openente.defender();
		combate(unidadesCombatentes, unidadesDefensoras);
		acabarTurno();
	}
	
	ArrayList<Carta> defender() {
		/*
		 * Esse metodo deve ser responsavel pela escolha das cartas que irao defender o jogador e chamar a instancia de combate
		 */
		ArrayList<Carta> unidadeDefensoras = campo.selecionarUnidades();
		return unidadesDefensoras;
		
	}
	
	public void acabarTurno() {
		if(mana<=3) {
			manaDeFeitico = mana;
			mana=0;
		}else {
			manaDeFeitico = 3;
			mana=0;
		}
		mudarTurno();
	}
	
}
