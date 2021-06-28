package com.unicamp.mc322.projeto.jogador;

import com.unicamp.mc322.projeto.Campo;
import com.unicamp.mc322.projeto.Interface.InterfaceGrafica;
import com.unicamp.mc322.projeto.Interface.InterfaceTerminal;
import com.unicamp.mc322.projeto.cartas.*;
import com.unicamp.mc322.projeto.deckFactory.Deck;
import com.unicamp.mc322.projeto.deckFactory.DeckFactory;
import com.unicamp.mc322.projeto.deckFactory.TipoDeck;
import com.unicamp.mc322.projeto.turno.*;
import java.util.Scanner;
import java.util.ArrayList;

public abstract class Jogador {
	protected int mana = 0;
	private int nexus = 20;
	private int manaDeFeitico = 0;
	private Turno turno;
	private Scanner teclado = new Scanner(System.in);
	protected ArrayList<Carta> mao = new ArrayList<Carta>();  //Ira representar as cartas que podem ser compradas pelo jogador
	protected ArrayList<Seguidor> evocadas = new ArrayList<Seguidor>();  //Ira representar as cartas compradas
	protected Deck deckJogador;
	private int numeroJogadorNoCampo=0;
	
	Jogador(Turno turnoInicial) {
		/*
		 * Arranjar um meio de randomizar quem vai atacar primeiro
		 */
		//Ao inves de ser uma string o tipoDeck será que é melhor um enum??
		super();
		TipoDeck tipoDeck = InterfaceTerminal.EscolhaTipoDeck();
		this.turno = turnoInicial;
		this.deckJogador = DeckFactory.getDeck(tipoDeck);
		obter4CartasIniciais();
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
	
	public ArrayList<Carta> getMao() {
		return mao;
	}
	
	public ArrayList<Seguidor> getEvocadas() {
		return evocadas;
	}
	
	public int getNexus() {
		return nexus;
	}
	
	public void adicionarAoNexus(int valorAdicionado) {
		nexus += valorAdicionado;
	}
	
	private void obter4CartasIniciais() {
		/*
		 * Esse método deve ser responsável por obter as quatro cartas inciais do jogador
		 */
		//Mostrar as cartas para o jogador
		this.mao = deckJogador.obterCartasIniciais();		
		this.mao = substituirCartas(mao);
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
	
	
	// Escolhe cartas invocadas para utilizar
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
	
	public boolean comprarCarta(int posicaoMao, Campo campo) {
		/*
		 * Esse metodo eh chamado quando o jogador tenta comprar uma carta, retorna true se a compra foi possivel.
		 */
		Carta carta = mao.get(posicaoMao);
		
		if(verificarCarta(carta)) {
			if(carta.getTipo() == TipoCarta.FEITICO) {
				Feitico feitico = (Feitico) mao.get(posicaoMao);
				if(feitico.ehPossivel(campo)) {
					mana -= feitico.getMana();
					feitico.ativarCarta(campo);
					mao.remove(posicaoMao);
					return true;
				}
				else {
					return false;
				}
				
			}
			else {
				mana -= carta.getMana();
				evocadas.add((Seguidor) carta);
				mao.remove(posicaoMao);
				return true;
			}
		} else {
			System.out.println("Sem mana suficiente para invocar esta carta");
			return false;
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
//	public void atacar(Jogador oponente, Campo campo, int... posicaoUnidades) {
//		/*
//		 * Esse metodo tem acesso ao campo de invocações para selecionar os combatentes, após isso chama a 
//		 * instancia da classe combate onde será feita a batalha
//		 */
//	//	ArrayList<Carta> unidadesCombatentes = campo.selecionarUnidades(numeroJogadorNoCampo, posicaoUnidades);
//		ArrayList<Carta> unidadesDefensoras = oponente.defender(campo, posicaoUnidades);  // 
//		//combate(unidadesCombatentes, unidadesDefensoras);
//		acabarTurno();
//	}
	
//	ArrayList<Carta> defender(Campo campo, int...posicaoUnidades) {
//		/*
//		 * Esse metodo deve ser responsavel pela escolha das cartas que irao defender o jogador e chamar a instancia de combate
//		 */
//		ArrayList<Carta> unidadesDefensoras = campo.selecionarUnidades(numeroJogadorNoCampo, posicaoUnidades);
//		return unidadesDefensoras;
//		
//	}
	
	public void acabarRodada() {
		if(mana<=3) {
			manaDeFeitico = mana;
			mana=0;
		}else {
			manaDeFeitico = 3;
			mana=0;
		}
		if(turno == Turno.DEFESA) {
			turno = Turno.ATAQUE;
		}else {
			turno = Turno.DEFESA;
		}
	}
	
	
}
