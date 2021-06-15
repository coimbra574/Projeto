package com.unicamp.mc322.projeto.jogador;

import com.unicamp.mc322.projeto.cartas.*;
import com.unicamp.mc322.projeto.main.Campo;
import com.unicamp.mc322.projeto.turno.*;
import java.util.Scanner;
import java.util.ArrayList;

public abstract class Jogador {
	protected int mana = 0;
	private int nexus = 20;
	private int manaDeFeitico = 0;
	private Turno turno;
	//private Scanner teclado = new Scanner(System.in);
	protected ArrayList<Carta> mao = new ArrayList<Carta>();  //Irá representar as cartas na mão do jogador
	protected Deck deckJogador;
	private int numeroJogadorNoCampo=0;
	
	Jogador(Turno turnoInicial) {
		/*
		 * Arranjar um meio de randomizar quem vai atacar primeiro
		 */
		super();
		this.turno = turnoInicial;
		//this.mao = obter4CartasIniciais();
	}
	
	public void setNumeroEmCampo(int n) {
		numeroJogadorNoCampo = n;
	}
	
	public int getNumeroEmCampo() {
		return numeroJogadorNoCampo;
	}
	
	public Turno getTurno() {
		return turno;
	}
	
	private ArrayList<Carta> obter4CartasIniciais() {
		/*
		 * Esse método deve ser responsável por obter as quatro cartas inciais do jogador
		 */
		//Mostrar as cartas para o jogador
		mao = deckJogador.obterCartasIniciais(mao);		
		mao = substituirCartas(mao);
		return mao;
	}
	
	protected abstract ArrayList<Carta> substituirCartas(ArrayList<Carta> mao);
	
	public void pegarCarta() {
		/*
		 * O método deve chamar um método do deck para obter uma carta do mesmo e colocá-lo na sua mao, todo começo de turno de ataque
		 */
		Carta carta;
		carta = deckJogador.obterCartaDeck();//método do deck que retorna uma carta
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
	
	public abstract void escolherCartaUtilizar(Campo campo);
	
	protected boolean verificarCarta(Carta carta) {
		/*
		 * Verificar se o jogadore possui mana suficiente para invocar/ativar uma carta
		 */
		if(mana > carta.getMana()) {
			return true;
		}else {
			return false;
		}
	}
	
	protected void invocarCarta(Carta carta, Campo campo, int posicaoNoCampo) {
		/*
		 * Esse método depende da implementação do campo de invocações, deverá invocá-lo nele 
		 */
		if(verificarCarta(carta)) {
			mana -= carta.getMana();
			campo.adicionarCartaEmCampo(numeroJogadorNoCampo, posicaoNoCampo, carta);
		} else {
			System.out.println("Sem mana suficiente para invocar esta carta");
		}
	}
	
	protected void ativarEfeito(Carta carta) {
		/*
		 * Deve ativar o efeito de uma carta
		 */
		mana -= carta.getMana();
	}
	
	public void diminuirNexus(int dano) {
		this.nexus-=dano;
	}
	
	// Deixar essa parte de combate aqui ou na classe Jogo/Game?
	public void atacar(Jogador oponente, Campo campo, int... posicaoUnidades) {
		/*
		 * Esse metodo tem acesso ao campo de invocações para selecionar os combatentes, após isso chama a 
		 * instancia da classe combate onde será feita a batalha
		 */
		ArrayList<Carta> unidadesCombatentes = campo.selecionarUnidades(numeroJogadorNoCampo, posicaoUnidades);
		ArrayList<Carta> unidadesDefensoras = oponente.defender(campo, posicaoUnidades);  // 
		//combate(unidadesCombatentes, unidadesDefensoras);
		acabarTurno();
	}
	
	ArrayList<Carta> defender(Campo campo, int...posicaoUnidades) {
		/*
		 * Esse metodo deve ser responsavel pela escolha das cartas que irao defender o jogador e chamar a instancia de combate
		 */
		ArrayList<Carta> unidadesDefensoras = campo.selecionarUnidades(numeroJogadorNoCampo, posicaoUnidades);
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
