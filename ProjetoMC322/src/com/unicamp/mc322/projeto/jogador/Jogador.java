package com.unicamp.mc322.projeto.jogador;

import com.unicamp.mc322.projeto.campo.Campo;
import com.unicamp.mc322.projeto.cartas.*;
import com.unicamp.mc322.projeto.cartas.efeitos.TipoAtivacao;
import com.unicamp.mc322.projeto.deckFactory.Deck;
import com.unicamp.mc322.projeto.deckFactory.DeckFactory;
import com.unicamp.mc322.projeto.deckFactory.TipoDeck;
import com.unicamp.mc322.projeto.rodada.*;

import java.util.ArrayList;

public abstract class Jogador {
	protected int mana = 0;
	private int nexus = 20;
	private int manaDeFeitico = 0;
	protected Turno turno;
	protected ArrayList<Carta> mao = new ArrayList<Carta>();  //Ira representar as cartas que podem ser compradas pelo jogador
	protected ArrayList<Seguidor> evocadas = new ArrayList<Seguidor>();  //Ira representar as cartas compradas
	protected ArrayList<Seguidor> emCampo = new ArrayList<Seguidor>();  //Ira representar as cartas no campo de batalha
	protected Deck deckJogador;
	private int numeroJogadorNoCampo=0;
	
	Jogador(Turno turnoInicial) {
		super();
		TipoDeck tipoDeck = escolhaTipoDeck();
		this.turno = turnoInicial;
		this.deckJogador = DeckFactory.getDeck(tipoDeck);
		obter4CartasIniciais();
		emCampo.add(null);
		emCampo.add(null);
		emCampo.add(null);
		emCampo.add(null);
		emCampo.add(null);
		emCampo.add(null);
	}
	
	// Escolhe qual deck ira usar
	protected abstract TipoDeck escolhaTipoDeck();
	
	// Seleciona uma carta evocada ou em campo do seu deck
	public abstract Seguidor selecionarUmaUnidadeAliada(Campo campo);
	
	// Seleciona uma carta evocada ou em campo do inimigo
	public abstract Seguidor selecionarUmaUnidadeInimiga(Campo campo);
	
	// Seleciona uma carta para comprar ou passa a vez
	public abstract boolean acaoRodadaCompra(Campo campo);
	
	// Seleciona uma carta para comprar ou uma evocada para atacar ou passa a vez
	public abstract boolean acaoRodadaCompraOuAtaque(Campo campo);
	
	// Seleciona cartas evocadas para defender ou passa a vez
	public abstract boolean acaoRodadaDefesa(Campo campo);
	
	public void setNumeroEmCampo(int n) {
		numeroJogadorNoCampo = n;
	}
	
	public int getNumeroEmCampo() {
		return numeroJogadorNoCampo;
	}
	
	public Turno getTurno() {
		return turno;
	}
	
	public int getMana() {
		return mana;
	}
	
	public int getManaDeFeitico() {
		return manaDeFeitico;
	}
	
	public ArrayList<Carta> getMao() {
		return mao;
	}
	
	public ArrayList<Seguidor> getEvocadas() {
		return evocadas;
	}
	
	public ArrayList<Seguidor> getEmCampo() {
		return emCampo;
	}
	
	public int getNexus() {
		return nexus;
	}
	
	public void adicionarAoNexus(int valorAdicionado) {
		nexus += valorAdicionado;
	}
	
	public void colocarEmCampo(int posicaoEvocada, int posicaoCampo) {
		emCampo.add(posicaoCampo, evocadas.get(posicaoEvocada));
		evocadas.remove(posicaoEvocada);
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
	
	protected boolean verificarCarta(Carta carta, Campo campo) {
		/*
		 * Verificar se o jogadore possui mana suficiente para invocar/ativar uma carta
		 * O numero maximo de cartas evocadas eh campo.LARGURA_CAMPO = 6
		 */
		if(carta.getTipo() == TipoCarta.FEITICO && mana+manaDeFeitico >= carta.getMana()) {
			Feitico feitico = (Feitico) carta;
			// Verifica se existem unidades para se aplicar o feitico
			if(feitico.ehPossivel(campo)) {
				return true;
			}
			else {
				return false;
			}
		}
		else if(mana >= carta.getMana() && evocadas.size() < campo.LARGURA_CAMPO) {
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
		if(!carta.equals(null)) {
		
			if(verificarCarta(carta, campo)) {
				if(carta.getTipo() == TipoCarta.FEITICO) {
					Feitico feitico = (Feitico) mao.get(posicaoMao);
					if(manaDeFeitico >= feitico.getMana()) {
						manaDeFeitico -= feitico.getMana();
					}
					else {
						mana += manaDeFeitico;
						manaDeFeitico = 0;
						mana -= feitico.getMana();
					}
					feitico.ativarEfeitos(campo, TipoAtivacao.NA_COMPRA);
					mao.remove(posicaoMao);
					return true;
				}
				else {
					mana -= carta.getMana();
					if(carta.ehPossivel(campo)) {
						carta.ativarEfeitos(campo, TipoAtivacao.NA_COMPRA);
					}
					evocadas.add((Seguidor) carta);
					mao.remove(posicaoMao);
					return true;
				}
			} else {
				System.out.println("Sem mana suficiente para invocar esta carta");
				return false;
			}
		}
		
		else return false;
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

	public void adicionarEvocada(Seguidor carta) {
		evocadas.add(carta);
	}

	public void removerEmCampo(int index) {
		emCampo.set(index, null);
	}
	
}
