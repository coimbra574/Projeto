package com.unicamp.mc322.projeto;

import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.jogador.Jogador;
import java.util.ArrayList;

public class Combate {
	private Jogador atacante, defensor;
	private Campo campo;
	private ArrayList<Carta> unidadesCombatentes, unidadesDefensoras;
	

	public Combate(Campo campo, Jogador atacante, Jogador defensor) {
		this.atacante = atacante;
		this.defensor = defensor;
		this.campo = campo;
		
//		prepararCombate();
//		for(int i=0; i<unidadesCombatentes.size(); i++) {
//			iniciarCombateEntreCartas(i);
//		}
//		terminarCombate();
	}
/*	
	public void prepararCombate() {
		//ArrayList<Carta> unidadesCombatentes = atacante.escolherCartaUtilizar(campo);
		//ArrayList<Carta> unidadesDefensoras = defensor.escolherCartaUtilizar(campo);
	}
	
	
	public void iniciarCombateEntreCartas(int numeroCarta) {
		int indexCartaAtaque = campo.procurarCartaEmCampo(atacante.getNumeroEmCampo(), unidadesCombatentes.get(numeroCarta));
		int indexCartaDefesa = -1;
		Seguidor cartaAtacante = (Seguidor) campo.selecionarUmaUnidade(atacante.getNumeroEmCampo(), indexCartaAtaque);
		Seguidor cartaDefensora = null;  //Inicia a cartaDefensora como null para que possa saber no traco se ele vai atacar o nexus ou 
									   //um seguidor.
		// Se existir uma unidade defensora para esse ataque, combatem
		if(!unidadesDefensoras.get(numeroCarta).equals(null)) {  
			indexCartaDefesa = campo.procurarCartaEmCampo(defensor.getNumeroEmCampo(), unidadesDefensoras.get(numeroCarta));
			cartaDefensora = (Seguidor) campo.selecionarUmaUnidade(defensor.getNumeroEmCampo(), indexCartaDefesa);
			cartaAtacante.setVida(cartaAtacante.getVida() - cartaDefensora.getPoder());
			cartaDefensora.setVida(cartaDefensora.getVida() - cartaAtacante.getPoder());//$-Nao seria cartaDefensora.getVida() - cartaAtacante.getPoder()-$ - SIM, tá certo
		}
		// Se nao tiver carta de defesa, ataca diretamente o nexus
		if(indexCartaDefesa == -1) {
			defensor.adicionarAoNexus(-1*cartaAtacante.getPoder());
		}
		
		if(cartaAtacante.getHaTraco()) {
			cartaAtacante.ativacaoTraco(cartaDefensora, defensor);//coloquei isso aqui para ativar o traco
		}
	}
	
	
	public void terminarCombate() {
		campo.verificarCartasComVida(1);  // Verifica cartas jogador 1
		campo.verificarCartasComVida(2);  // verifica cartas jogador 2
		Rodada rodada = campo.getRodada();
		rodada.finalizarTurno();
	}
*/	
}
