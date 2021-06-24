package com.unicamp.mc322.projeto.deckFactory;

import com.unicamp.mc322.projeto.cartas.Campeao;
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.cartas.Condicao;
import com.unicamp.mc322.projeto.cartas.DadosEvolucao;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.cartas.efeitos.*;
import com.unicamp.mc322.projeto.cartas.tracos.*;
import com.unicamp.mc322.projeto.cartas.Feitico;
import java.util.ArrayList;


public class ListaCartas {
	private Campeao garen;
	
	private Seguidor tiana = new Seguidor("Tiana", 8, 7, 7, null, null, null);
	private Seguidor vanguarda = new Seguidor("Vanguarda", 4, 3, 3, null, null, null);
	private Seguidor duelista = new Seguidor("Duelista", 3, 3, 2, null, null, null);
	private Seguidor defensor = new Seguidor("Defensor", 2, 2, 2, null, null, null);
	private Seguidor poro = new Seguidor("Poro", 1, 2, 1, null, null, null);
	private Seguidor poroDefensor = new Seguidor("Poro Defensor", 1, 1, 2, null, null, null);
	
	private Feitico julgamento = new Feitico("Julgamento", 8, null);
	private Feitico valorRedobrado = new Feitico("Valor Redobrado", 6, null);
	private Feitico golpeCerteiro = new Feitico("Golpe Certeiro", 1, null);
	private Feitico combateUmUm = new Feitico("Combate um-a-um", 2, null);
	
	private ArrayList<Carta> listaCampeoes = new ArrayList<Carta>();
	private ArrayList<Carta> listaSeguidores = new ArrayList<Carta>();
	private ArrayList<Carta> listaFeitico = new ArrayList<Carta>();
	
	
	ListaCartas() {
		super();
		DadosEvolucao evolucaoGaren = new DadosEvolucao(Condicao.NUM_DANO_ADD, 2, 1, 1, new Elusivo());
		this.garen = new Campeao("Garen", 5, 5, 5, evolucaoGaren, null, new Regeneracao());
		
		listaCampeoes.add(garen);
		
		listaSeguidores.add(tiana);
		listaSeguidores.add(vanguarda);
		listaSeguidores.add(duelista);
		listaSeguidores.add(defensor);
		listaSeguidores.add(poro);
		listaSeguidores.add(poroDefensor);
		
		listaFeitico.add(julgamento);
		listaFeitico.add(valorRedobrado);
		listaFeitico.add(golpeCerteiro);
		listaFeitico.add(combateUmUm);
	}
	
	int getNumCampeoes() {
		return listaCampeoes.size();
	}
	
	int getNumSeguidores() {
		return listaSeguidores.size();
	}
	
	int getNumFeitico() {
		return listaFeitico.size();
	}
	
	Carta getCampeao(int n) {
		return listaCampeoes.get(n);
	}
	
	Carta getSeguidores(int n) {
		return listaSeguidores.get(n);
	}
	
	Carta getFeitico(int n) {
		return listaFeitico.get(n);
	}
	
}
