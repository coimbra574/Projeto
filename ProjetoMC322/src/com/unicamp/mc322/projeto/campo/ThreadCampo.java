package com.unicamp.mc322.projeto.campo;
/*
 * 	Decidimos utilizar a interface grafica do campo como
 *  um thread separado para conseguir receber as entradas
 *  do clique do jogador humano.
 */

public class ThreadCampo extends Thread {
	private InterfaceCampo interfaceCampo;
	
    public ThreadCampo(Campo campo) {
        super("MyThread");
        this.interfaceCampo = new InterfaceCampo(campo, campo.getRodada());
    }
    
    public InterfaceCampo getInterface() {
    	return interfaceCampo;
    }
    
    public void run() {
    	interfaceCampo.setVisible(true);
    }
}
