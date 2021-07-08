package com.unicamp.mc322.projeto.campo;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.unicamp.mc322.projeto.Rodada;
import com.unicamp.mc322.projeto.TipoRodada;
import com.unicamp.mc322.projeto.NumeroJogador;
import com.unicamp.mc322.projeto.cartas.Carta;
import com.unicamp.mc322.projeto.cartas.Seguidor;
import com.unicamp.mc322.projeto.cartas.efeitos.Efeito;
import com.unicamp.mc322.projeto.cartas.efeitos.TipoAtivacao;
import com.unicamp.mc322.projeto.turno.Turno;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Panel;
import javax.swing.JLayeredPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;


public class InterfaceCampo extends javax.swing.JFrame {
	private Campo campo;
	private Rodada rodada;
	private boolean aguardandoCarta;
	private boolean aguardandoIndex;
	private Carta cartaEscolhida;
	private int indexEscolhido;
	private boolean realizouAcao;
	private ArrayList<JButton> maoP1 = new ArrayList<JButton>();
	private ArrayList<JButton> maoP2 = new ArrayList<JButton>();
	private ArrayList<JButton> infoP1Mao = new ArrayList<JButton>();
	private ArrayList<JButton> infoP2Mao = new ArrayList<JButton>();
	private ArrayList<JButton> evocadasP1 = new ArrayList<JButton>();
	private ArrayList<JButton> evocadasP2 = new ArrayList<JButton>();
	private ArrayList<JButton> emCampoP1 = new ArrayList<JButton>();
	private ArrayList<JButton> emCampoP2 = new ArrayList<JButton>();

    public InterfaceCampo(Campo campo, Rodada rodada) {
    	getContentPane().setFont(new Font("Arial", Font.PLAIN, 10));
        initComponents();
        this.campo = campo;
        this.rodada = rodada;
		this.aguardandoCarta = false;
		this.aguardandoIndex = false;
		
		desativarTudo();
    }
    
    public void ativar() {
    	this.setVisible(true);
    	iniciarTurno();
    }
    
	public void iniciarTurno() {
		this.realizouAcao = false;
		atualizarNexus();
		atualizarMao();
		atualizarEvocadas();
		atualizarEmCampo();
		atualizarMana();
		atualizarTurno();
		
		//Vez do jogador1
		if(rodada.getNumeroJogadorAtual() == NumeroJogador.PLAYER1) {
			desativarMaoP2();
			desativarEvocadasP2();
			if(rodada.getTipo() == TipoRodada.COMPRA_DE_CARTAS) {
				bntAvancarTurno.setText("<html>Passar</html>");
				ativarMaoP1();
				if(campo.getP1().getTurno() == Turno.ATAQUE) {
					ativarEvocadasP1();
				}
			}
			else {
				bntAvancarTurno.setText("<html>Nao Defender</html>");
				ativarEvocadasP1();
			}
		}
		//Vez do jogador2
		else {
			desativarMaoP1();
			desativarEvocadasP1();
			if(rodada.getTipo() == TipoRodada.COMPRA_DE_CARTAS) {
				bntAvancarTurno.setText("<html>Passar</html>");
				ativarMaoP2();
				if(campo.getP2().getTurno() == Turno.ATAQUE) {
					ativarEvocadasP2();
				}
			}
			else {
				bntAvancarTurno.setText("<html>Nao Defender</html>");
				ativarEvocadasP2();
			}
		}
	}
	
	private void desativarTudo() {
		for(int i = 0; i < maoP1.size(); i++) {
			maoP1.get(i).setVisible(false);
			maoP1.get(i).setEnabled(false);
			maoP2.get(i).setVisible(false);
			maoP2.get(i).setEnabled(false);
			infoP1Mao.get(i).setVisible(false);
			infoP1Mao.get(i).setEnabled(false);
			infoP2Mao.get(i).setVisible(false);
			infoP2Mao.get(i).setEnabled(false);
			evocadasP1.get(i).setVisible(false);
			evocadasP1.get(i).setEnabled(false);
			evocadasP2.get(i).setVisible(false);
			evocadasP2.get(i).setEnabled(false);
			emCampoP1.get(i).setVisible(false);
			emCampoP1.get(i).setEnabled(false);
			emCampoP2.get(i).setVisible(false);
			emCampoP2.get(i).setEnabled(false);
		}
	}
	
	private void desativarMaoP1() {
		for(int i = 0; i < maoP1.size(); i++) {
			maoP1.get(i).setEnabled(false);
			infoP1Mao.get(i).setEnabled(false);
		}
	}
	
	private void desativarMaoP2() {
		for(int i = 0; i < maoP2.size(); i++) {
			maoP2.get(i).setEnabled(false);
			infoP2Mao.get(i).setEnabled(false);
		}
	}
	
	private void ativarMaoP1() {
		for(int i = 0; i < maoP1.size(); i++) {
			maoP1.get(i).setEnabled(true);
			infoP1Mao.get(i).setEnabled(true);
		}
	}
	
	private void ativarMaoP2() {
		for(int i = 0; i < maoP2.size(); i++) {
			maoP2.get(i).setEnabled(true);
			infoP2Mao.get(i).setEnabled(true);
		}
	}
	
	private void desativarEvocadasP1() {
		for(int i = 0; i < evocadasP1.size(); i++) {
			evocadasP1.get(i).setEnabled(false);
		}
	}
	
	private void desativarEvocadasP2() {
		for(int i = 0; i < evocadasP2.size(); i++) {
			evocadasP2.get(i).setEnabled(false);
		}
	}
	
	private void ativarEvocadasP1() {
		for(int i = 0; i < evocadasP1.size(); i++) {
			evocadasP1.get(i).setEnabled(true);
		}
	}
	
	private void ativarEvocadasP2() {
		for(int i = 0; i < evocadasP2.size(); i++) {
			evocadasP2.get(i).setEnabled(true);
		}
	}
	
	private void desativarEmCampoP1() {
		for(int i = 0; i < emCampoP1.size(); i++) {
			emCampoP1.get(i).setEnabled(false);
		}
	}
	
	private void desativarEmCampoP2() {
		for(int i = 0; i < emCampoP2.size(); i++) {
			emCampoP2.get(i).setEnabled(false);
		}
	}
	
	private void ativarEmCampoP1() {
		for(int i = 0; i < emCampoP1.size(); i++) {
			emCampoP1.get(i).setEnabled(true);
		}
	}
	
	private void ativarCampoSelecionavelP1() {
		if(rodada.getTipo() == TipoRodada.COMPRA_DE_CARTAS) {
			for(int i = 0; i < emCampoP1.size(); i++) {
				if(emCampoP1.get(i).isVisible() == false) {
					emCampoP1.get(i).setVisible(true);
					emCampoP1.get(i).setEnabled(true);
				}
			}
		}
		else {
			for(int i = 0; i < emCampoP1.size(); i++) {
				if(emCampoP1.get(i).isVisible() == false && emCampoP2.get(i).isVisible() == true) {
					emCampoP1.get(i).setVisible(true);
					emCampoP1.get(i).setEnabled(true);
				}
			}
		}
	}
	
	private void ativarCampoSelecionavelP2() {
		if(rodada.getTipo() == TipoRodada.COMPRA_DE_CARTAS) {
			for(int i = 0; i < emCampoP2.size(); i++) {
				if(emCampoP2.get(i).isVisible() == false) {
					emCampoP2.get(i).setVisible(true);
					emCampoP2.get(i).setEnabled(true);
				}
			}
		}
		else {
			for(int i = 0; i < emCampoP2.size(); i++) {
				if(emCampoP2.get(i).isVisible() == false && emCampoP1.get(i).isVisible() == true) {
					emCampoP2.get(i).setVisible(true);
					emCampoP2.get(i).setEnabled(true);
				}
			}
		}
	}
	
	private void ativarEmCampoP2() {
		for(int i = 0; i < emCampoP2.size(); i++) {
			emCampoP2.get(i).setEnabled(true);
		}
	}
	
	public void atualizarNexus() {
		int nexusP1 = campo.getP1().getNexus();
		int nexusP2 = campo.getP2().getNexus();
		jblP1VidaNexus.setText(Integer.toString(nexusP1));
		jblP2VidaNexus.setText(Integer.toString(nexusP2));
	}
	
	public void atualizarMao() {
		ArrayList<Carta> maoPlayer1 = campo.getP1().getMao();
		ArrayList<Carta> maoPlayer2 = campo.getP2().getMao();
		
		for(int i = 0; i < maoP1.size() && i < maoP1.size(); i++) {
			maoP1.get(i).setVisible(false);
			infoP1Mao.get(i).setVisible(false);
			maoP2.get(i).setVisible(false);
			infoP2Mao.get(i).setVisible(false);
		}
		
		for(int i = 0; i < maoPlayer1.size() && i < maoP1.size(); i++) {
			maoP1.get(i).setText(maoPlayer1.get(i).toStringCompra());
			maoP1.get(i).setVisible(true);
			infoP1Mao.get(i).setVisible(true);
		}
		for(int i = 0; i < maoPlayer2.size() && i < maoP2.size(); i++) {
			maoP2.get(i).setText(maoPlayer2.get(i).toStringCompra());
			maoP2.get(i).setVisible(true);
			infoP2Mao.get(i).setVisible(true);
		}
	}
	
	public void atualizarEvocadas() {
		ArrayList<Seguidor> evocadasPlayer1 = campo.getP1().getEvocadas();
		ArrayList<Seguidor> evocadasPlayer2 = campo.getP2().getEvocadas();
		
		for(int i = 0; i < evocadasP1.size() && i < maoP1.size(); i++) {
			evocadasP1.get(i).setVisible(false);
			evocadasP2.get(i).setVisible(false);
		}
		
		for(int i = 0; i < evocadasPlayer1.size() && i < maoP1.size(); i++) {
			evocadasP1.get(i).setText(evocadasPlayer1.get(i).toStringEvocada());
			evocadasP1.get(i).setVisible(true);
		}
		for(int i = 0; i < evocadasPlayer2.size() && i < maoP2.size(); i++) {
			evocadasP2.get(i).setText(evocadasPlayer2.get(i).toStringEvocada());
			evocadasP2.get(i).setVisible(true);
		}
	}
	
	public void atualizarEmCampo() {
		ArrayList<Seguidor> emCampoPlayer1 = campo.getP1().getEmCampo();
		ArrayList<Seguidor> emCampoPlayer2 = campo.getP2().getEmCampo();
		
		for(int i = 0; i < emCampoP1.size() && i < maoP1.size(); i++) {
			emCampoP1.get(i).setVisible(false);
			emCampoP1.get(i).setText("<html>Selecionar</html>");
			emCampoP2.get(i).setVisible(false);
			emCampoP2.get(i).setText("<html>Selecionar</html>");
		}
		
		for(int i = 0; i < emCampoPlayer1.size() && i < maoP1.size(); i++) {
			if(emCampoPlayer1.get(i) != null) {
				emCampoP1.get(i).setText(emCampoPlayer1.get(i).toStringEmCampo());
				emCampoP1.get(i).setVisible(true);
			}
		}
		for(int i = 0; i < emCampoPlayer2.size() && i < maoP2.size(); i++) {
			if(emCampoPlayer2.get(i) != null) {
				emCampoP2.get(i).setText(emCampoPlayer2.get(i).toStringEmCampo());
				emCampoP2.get(i).setVisible(true);
			}
		}
	}
	
	private void atualizarMana() {
		jblP1Mana.setText(Integer.toString(campo.getP1().getMana()));
		jblP2Mana.setText(Integer.toString(campo.getP2().getMana()));
		jblP1ManaFeitico.setText(Integer.toString(campo.getP2().getManaDeFeitico()));
		jblP2ManaFeitico.setText(Integer.toString(campo.getP1().getManaDeFeitico()));
	}
	
	private void atualizarTurno() {
		if(campo.getP1().getTurno() == Turno.ATAQUE) {
			jblTipoDeTurnoPlayer1.setText("Ataque");
			jblTipoDeTurnoPlayer2.setText("Defesa");
		}
		else {
			jblTipoDeTurnoPlayer1.setText("Defesa");
			jblTipoDeTurnoPlayer2.setText("Ataque");
		}
	}
	
	private Runnable aguardarSelecaoCarta = new Runnable() {
    	public void run() {
    		try {
    			aguardandoCarta = true;
        		while(aguardandoCarta) {
        			Thread.sleep(100);
        		}
    		} catch (Exception e) {}
    	}
    };
	
	public Seguidor selecionarCartaP1() {
		ativarEvocadasP1();
		ativarEmCampoP1();
		aguardarSelecaoCarta.run();
		desativarEvocadasP1();
		desativarEmCampoP1();
		
		return (Seguidor) cartaEscolhida;
	}
	
	
	
	public Seguidor selecionarCartaP2() {
		ativarEvocadasP2();
		ativarEmCampoP2();
		aguardarSelecaoCarta.run();
		desativarEvocadasP2();
		desativarEmCampoP2();
		
		return (Seguidor) cartaEscolhida;
	}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        panelCampo = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(950, 770));
        setResizable(false);

        panelCampo.setBackground(new java.awt.Color(153, 255, 153));
        panelCampo.setOpaque(true);
        
        panelCampoP1 = new JLayeredPane();
        
        panelCampoP2 = new JLayeredPane();
        
        bntP2EmCampo_1 = new JButton();
        bntP2EmCampo_1.setText("<html>Selecionar</html>");
        bntP2EmCampo_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP2EmCampoActionPerformed(evt, 1);
            }
        });
        bntP2EmCampo_1.setBounds(230, 19, 104, 61);
        panelCampoP2.add(bntP2EmCampo_1);
        
        bntP2EmCampo_2 = new JButton();
        bntP2EmCampo_2.setText("<html>Selecionar</html>");
        bntP2EmCampo_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP2EmCampoActionPerformed(evt, 2);
            }
        });
        bntP2EmCampo_2.setBounds(450, 19, 104, 61);
        panelCampoP2.add(bntP2EmCampo_2);
        
        bntP2EmCampo_3 = new JButton();
        bntP2EmCampo_3.setText("<html>Selecionar</html>");
        bntP2EmCampo_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP2EmCampoActionPerformed(evt, 3);
            }
        });
        bntP2EmCampo_3.setBounds(120, 19, 104, 61);
        panelCampoP2.add(bntP2EmCampo_3);
        
        bntP2EmCampo_0 = new JButton();
        bntP2EmCampo_0.setText("<html>Selecionar</html>");
        bntP2EmCampo_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP2EmCampoActionPerformed(evt, 0);
            }
        });
        bntP2EmCampo_0.setBounds(340, 19, 104, 61);
        panelCampoP2.add(bntP2EmCampo_0);
        
        bntP2EmCampo_4 = new JButton();
        bntP2EmCampo_4.setText("<html>Selecionar</html>");
        bntP2EmCampo_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP2EmCampoActionPerformed(evt, 4);
            }
        });
        bntP2EmCampo_4.setBounds(560, 19, 104, 61);
        panelCampoP2.add(bntP2EmCampo_4);
        
        bntP2EmCampo_5 = new JButton();
        bntP2EmCampo_5.setText("<html>Selecionar</html>");
        bntP2EmCampo_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP2EmCampoActionPerformed(evt, 5);
            }
        });
        bntP2EmCampo_5.setBounds(10, 19, 104, 61);
        panelCampoP2.add(bntP2EmCampo_5);

        javax.swing.GroupLayout gl_panelCampo = new javax.swing.GroupLayout(panelCampo);
        gl_panelCampo.setHorizontalGroup(
        	gl_panelCampo.createParallelGroup(Alignment.LEADING)
        		.addComponent(panelCampoP1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
        		.addComponent(panelCampoP2, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
        );
        gl_panelCampo.setVerticalGroup(
        	gl_panelCampo.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelCampo.createSequentialGroup()
        			.addComponent(panelCampoP2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
        			.addComponent(panelCampoP1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
        );
        
        bntP1EmCampo_1 = new JButton();
        bntP1EmCampo_1.setText("<html>Selecionar</html>");
        bntP1EmCampo_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP1EmCampoActionPerformed(evt, 1);
            }
        });
        bntP1EmCampo_1.setBounds(230, 0, 104, 61);
        panelCampoP1.add(bntP1EmCampo_1);
        
        bntP1EmCampo_2 = new JButton();
        bntP1EmCampo_2.setText("<html>Selecionar</html>");
        bntP1EmCampo_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP1EmCampoActionPerformed(evt, 2);
            }
        });
        bntP1EmCampo_2.setBounds(450, 0, 104, 61);
        panelCampoP1.add(bntP1EmCampo_2);
        
        bntP1EmCampo_3 = new JButton();
        bntP1EmCampo_3.setText("<html>Selecionar</html>");
        bntP1EmCampo_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP1EmCampoActionPerformed(evt, 3);
            }
        });
        bntP1EmCampo_3.setBounds(120, 0, 104, 61);
        panelCampoP1.add(bntP1EmCampo_3);
        
        bntP1EmCampo_0 = new JButton();
        bntP1EmCampo_0.setText("<html>Selecionar</html>");
        bntP1EmCampo_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP1EmCampoActionPerformed(evt, 0);
            }
        });
        bntP1EmCampo_0.setBounds(340, 0, 104, 61);
        panelCampoP1.add(bntP1EmCampo_0);
        
        bntP1EmCampo_4 = new JButton();
        bntP1EmCampo_4.setText("<html>Selecionar</html>");
        bntP1EmCampo_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP1EmCampoActionPerformed(evt, 4);
            }
        });
        bntP1EmCampo_4.setBounds(560, 0, 104, 61);
        panelCampoP1.add(bntP1EmCampo_4);
        
        bntP1EmCampo_5 = new JButton();
        bntP1EmCampo_5.setText("<html>Selecionar</html>");
        bntP1EmCampo_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP1EmCampoActionPerformed(evt, 5);
            }
        });
        bntP1EmCampo_5.setBounds(10, 0, 104, 61);
        panelCampoP1.add(bntP1EmCampo_5);
        panelCampo.setLayout(gl_panelCampo);
        
        JLayeredPane layeredPane = new JLayeredPane();
        
        panelEvocadasP1 = new JLayeredPane();
        
        panelMaoP1 = new JLayeredPane();
        
        panelEvocadasP2 = new JLayeredPane();
        
        bntP2Evocada_0 = new JButton();
        bntP2Evocada_0.setText("<html>Campeao 0<br /><br />Vida: 10<br />Ataque: 5<br />Nivel: 1</html>");
        bntP2Evocada_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP2EvocadaActionPerformed(evt, 0);
            }
        });
        bntP2Evocada_0.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP2Evocada_0.setBounds(160, 0, 80, 90);
        panelEvocadasP2.add(bntP2Evocada_0);
        
        bntP2Evocada_1 = new JButton();
        bntP2Evocada_1.setText("<html>Campeao 1<br /><br />Vida: 10<br />Ataque: 5<br />Nivel: 1</html>");
        bntP2Evocada_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP2EvocadaActionPerformed(evt, 1);
            }
        });
        bntP2Evocada_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP2Evocada_1.setBounds(250, 0, 80, 90);
        panelEvocadasP2.add(bntP2Evocada_1);
        
        bntP2Evocada_2 = new JButton();
        bntP2Evocada_2.setText("<html>Campeao 2<br /><br />Vida: 10<br />Ataque: 5<br />Nivel: 1</html>");
        bntP2Evocada_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP2EvocadaActionPerformed(evt, 2);
            }
        });
        bntP2Evocada_2.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP2Evocada_2.setBounds(340, 0, 80, 90);
        panelEvocadasP2.add(bntP2Evocada_2);
        
        bntP2Evocada_3 = new JButton();
        bntP2Evocada_3.setText("<html>Campeao 3<br /><br />Vida: 10<br />Ataque: 5<br />Nivel: 1</html>");
        bntP2Evocada_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP2EvocadaActionPerformed(evt, 3);
            }
        });
        bntP2Evocada_3.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP2Evocada_3.setBounds(430, 0, 80, 90);
        panelEvocadasP2.add(bntP2Evocada_3);
        
        bntP2Evocada_4 = new JButton();
        bntP2Evocada_4.setText("<html>Campeao 4<br /><br />Vida: 10<br />Ataque: 5<br />Nivel: 1</html>");
        bntP2Evocada_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP2EvocadaActionPerformed(evt, 4);
            }
        });
        bntP2Evocada_4.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP2Evocada_4.setBounds(70, 0, 80, 90);
        panelEvocadasP2.add(bntP2Evocada_4);
        
        bntP2Evocada_5 = new JButton();
        bntP2Evocada_5.setText("<html>Campeao 5<br /><br />Vida: 10<br />Ataque: 5<br />Nivel: 1</html>");
        bntP2Evocada_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP2EvocadaActionPerformed(evt, 5);
            }
        });
        bntP2Evocada_5.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP2Evocada_5.setBounds(520, 0, 80, 90);
        panelEvocadasP2.add(bntP2Evocada_5);
        
        panelMaoP2 = new JLayeredPane();
        
        bntP2Mao_0 = new JButton();
        bntP2Mao_0.setText("<html>Campeao 0<br /><br />Vida: 10<br />Ataque: 5<br />Custo: 0</html>");
        bntP2Mao_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP2MaoActionPerformed(evt, 0);
            }
        });
        bntP2Mao_0.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP2Mao_0.setBounds(160, 0, 80, 90);
        panelMaoP2.add(bntP2Mao_0);
        
        bntP2Mao_1 = new JButton();
        bntP2Mao_1.setText("<html>Campeao 1<br /><br />Vida: 10<br />Ataque: 5<br />Custo: 0</html>");
        bntP2Mao_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP2MaoActionPerformed(evt, 1);
            }
        });
        bntP2Mao_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP2Mao_1.setBounds(250, 0, 80, 90);
        panelMaoP2.add(bntP2Mao_1);
        
        bntP2Mao_2 = new JButton();
        bntP2Mao_2.setText("<html>Campeao 2<br /><br />Vida: 10<br />Ataque: 5<br />Custo: 0</html>");
        bntP2Mao_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP2MaoActionPerformed(evt, 2);
            }
        });
        bntP2Mao_2.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP2Mao_2.setBounds(340, 0, 80, 90);
        panelMaoP2.add(bntP2Mao_2);
        
        bntP2Mao_3 = new JButton();
        bntP2Mao_3.setText("<html>Campeao 3<br /><br />Vida: 10<br />Ataque: 5<br />Custo: 0</html>");
        bntP2Mao_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP2MaoActionPerformed(evt, 3);
            }
        });
        bntP2Mao_3.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP2Mao_3.setBounds(430, 0, 80, 90);
        panelMaoP2.add(bntP2Mao_3);
        
        bntP2Mao_4 = new JButton();
        bntP2Mao_4.setText("<html>Campeao 4<br /><br />Vida: 10<br />Ataque: 5<br />Custo: 0</html>");
        bntP2Mao_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP2MaoActionPerformed(evt, 4);
            }
        });
        bntP2Mao_4.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP2Mao_4.setBounds(70, 0, 80, 90);
        panelMaoP2.add(bntP2Mao_4);
        
        bntP2Mao_5 = new JButton();
        bntP2Mao_5.setText("<html>Campeao 5<br /><br />Vida: 10<br />Ataque: 5<br />Custo: 0</html>");
        bntP2Mao_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP2MaoActionPerformed(evt, 5);
            }
        });
        bntP2Mao_5.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP2Mao_5.setBounds(520, 0, 80, 90);
        panelMaoP2.add(bntP2Mao_5);
        
        panelNexus = new JLayeredPane();
        
        panelInformacoes = new JLayeredPane();
        
        jLabel7 = new JLabel();
        jLabel7.setText("Mana de Feiti\u00E7o");
        jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel7.setForeground(new Color(255, 153, 153));
        jLabel7.setFont(new Font("Tahoma", Font.BOLD, 14));
        jLabel7.setBounds(0, 238, 124, 17);
        panelInformacoes.add(jLabel7);
        
        bntAvancarTurno = new JButton();
        bntAvancarTurno.setText("Finalizar Turno");
        bntAvancarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntAvancarTurnoActionPerformed(evt);
            }
        });
        bntAvancarTurno.setBounds(0, 330, 124, 70);
        panelInformacoes.add(bntAvancarTurno);
        
        jLabel6 = new JLabel();
        jLabel6.setText("Mana");
        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setForeground(new Color(153, 153, 255));
        jLabel6.setFont(new Font("Tahoma", Font.BOLD, 14));
        jLabel6.setBounds(0, 278, 124, 17);
        panelInformacoes.add(jLabel6);
        
        jLabel5 = new JLabel();
        jLabel5.setText("Mana");
        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel5.setForeground(new Color(153, 153, 255));
        jLabel5.setFont(new Font("Tahoma", Font.BOLD, 14));
        jLabel5.setBounds(0, 428, 124, 17);
        panelInformacoes.add(jLabel5);
        
        jblP1Mana = new JLabel();
        jblP1Mana.setText("5");
        jblP1Mana.setHorizontalAlignment(SwingConstants.CENTER);
        jblP1Mana.setForeground(new Color(153, 153, 255));
        jblP1Mana.setFont(new Font("Tahoma", Font.BOLD, 14));
        jblP1Mana.setBounds(45, 410, 27, 17);
        panelInformacoes.add(jblP1Mana);
        
        jblP1ManaFeitico = new JLabel();
        jblP1ManaFeitico.setText("0");
        jblP1ManaFeitico.setHorizontalAlignment(SwingConstants.CENTER);
        jblP1ManaFeitico.setForeground(new Color(255, 153, 153));
        jblP1ManaFeitico.setFont(new Font("Tahoma", Font.BOLD, 14));
        jblP1ManaFeitico.setBounds(45, 455, 27, 17);
        panelInformacoes.add(jblP1ManaFeitico);
        
        jLabel4 = new JLabel();
        jLabel4.setText("Mana de Feiti\u00E7o");
        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel4.setForeground(new Color(255, 153, 153));
        jLabel4.setFont(new Font("Tahoma", Font.BOLD, 14));
        jLabel4.setBounds(0, 476, 124, 17);
        panelInformacoes.add(jLabel4);
        
        jblTipoDeTurnoPlayer1 = new JLabel();
        jblTipoDeTurnoPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
        jblTipoDeTurnoPlayer1.setText("Defesa");
        jblTipoDeTurnoPlayer1.setFont(new Font("Tahoma", Font.PLAIN, 24));
        jblTipoDeTurnoPlayer1.setBounds(0, 541, 124, 29);
        panelInformacoes.add(jblTipoDeTurnoPlayer1);
        
        jblP2Mana = new JLabel();
        jblP2Mana.setText("5");
        jblP2Mana.setHorizontalAlignment(SwingConstants.CENTER);
        jblP2Mana.setForeground(new Color(153, 153, 255));
        jblP2Mana.setFont(new Font("Tahoma", Font.BOLD, 14));
        jblP2Mana.setBounds(45, 301, 27, 17);
        panelInformacoes.add(jblP2Mana);
        
        jblP2ManaFeitico = new JLabel();
        jblP2ManaFeitico.setText("0");
        jblP2ManaFeitico.setHorizontalAlignment(SwingConstants.CENTER);
        jblP2ManaFeitico.setForeground(new Color(255, 153, 153));
        jblP2ManaFeitico.setFont(new Font("Tahoma", Font.BOLD, 14));
        jblP2ManaFeitico.setBounds(45, 258, 27, 17);
        panelInformacoes.add(jblP2ManaFeitico);
        
        jblTipoDeTurnoPlayer2 = new JLabel();
        jblTipoDeTurnoPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
        jblTipoDeTurnoPlayer2.setText("Ataque");
        jblTipoDeTurnoPlayer2.setFont(new Font("Tahoma", Font.PLAIN, 24));
        jblTipoDeTurnoPlayer2.setBounds(0, 147, 124, 29);
        panelInformacoes.add(jblTipoDeTurnoPlayer2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(panelNexus, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(panelEvocadasP2, GroupLayout.PREFERRED_SIZE, 674, GroupLayout.PREFERRED_SIZE)
        				.addComponent(panelCampo, GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
        				.addComponent(panelMaoP2, GroupLayout.PREFERRED_SIZE, 673, GroupLayout.PREFERRED_SIZE)
        				.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        					.addComponent(panelEvocadasP1, Alignment.LEADING)
        					.addComponent(panelMaoP1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(panelInformacoes, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
        			.addGap(25))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(panelInformacoes, GroupLayout.PREFERRED_SIZE, 737, GroupLayout.PREFERRED_SIZE)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addContainerGap()
        							.addComponent(panelMaoP2, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(panelEvocadasP2, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
        							.addGap(15)
        							.addComponent(panelCampo, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(panelEvocadasP1, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
        						.addGroup(layout.createSequentialGroup()
        							.addGap(237)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(panelNexus, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
        								.addGroup(layout.createSequentialGroup()
        									.addGap(285)
        									.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
        					.addGap(18)
        					.addComponent(panelMaoP1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        jLabel1 = new JLabel();
        jLabel1.setText("Jogador 2");
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setForeground(Color.RED);
        jLabel1.setFont(new Font("Tahoma", Font.BOLD, 14));
        jLabel1.setBounds(0, 75, 90, 17);
        panelNexus.add(jLabel1);
        
        jLabel0 = new JLabel();
        jLabel0.setText("Nexus");
        jLabel0.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel0.setForeground(Color.RED);
        jLabel0.setFont(new Font("Tahoma", Font.BOLD, 14));
        jLabel0.setBounds(0, 99, 90, 17);
        panelNexus.add(jLabel0);
        
        jLabel2 = new JLabel();
        jLabel2.setText("Nexus");
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setForeground(Color.BLUE);
        jLabel2.setFont(new Font("Tahoma", Font.BOLD, 14));
        jLabel2.setBounds(0, 139, 90, 17);
        panelNexus.add(jLabel2);
        
        jLabel3 = new JLabel();
        jLabel3.setText("Jogador 1");
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setForeground(Color.BLUE);
        jLabel3.setFont(new Font("Tahoma", Font.BOLD, 14));
        jLabel3.setBounds(0, 162, 90, 17);
        panelNexus.add(jLabel3);
        
        jblP1VidaNexus = new JLabel();
        jblP1VidaNexus.setText("20");
        jblP1VidaNexus.setFont(new Font("Tahoma", Font.BOLD, 15));
        jblP1VidaNexus.setBounds(35, 189, 19, 19);
        panelNexus.add(jblP1VidaNexus);
        
        jblP2VidaNexus = new JLabel();
        jblP2VidaNexus.setText("20");
        jblP2VidaNexus.setFont(new Font("Tahoma", Font.BOLD, 15));
        jblP2VidaNexus.setBounds(35, 46, 19, 19);
        panelNexus.add(jblP2VidaNexus);
        
        bntP1Evocada_0 = new JButton();
        bntP1Evocada_0.setText("<html>Campeao 0<br /><br />Vida: 10<br />Ataque: 5<br />Nivel: 1</html>");
        bntP1Evocada_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP1EvocadaActionPerformed(evt, 0);
            }
        });
        bntP1Evocada_0.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP1Evocada_0.setBounds(160, 0, 80, 90);
        panelEvocadasP1.add(bntP1Evocada_0);
        
        bntP1Evocada_1 = new JButton();
        panelEvocadasP1.setLayer(bntP1Evocada_1, 0);
        bntP1Evocada_1.setText("<html>Campeao 1<br /><br />Vida: 10<br />Ataque: 5<br />Nivel: 1</html>");
        bntP1Evocada_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP1EvocadaActionPerformed(evt, 1);
            }
        });
        bntP1Evocada_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP1Evocada_1.setBounds(250, 0, 80, 90);
        panelEvocadasP1.add(bntP1Evocada_1);
        
        bntP1Evocada_2 = new JButton();
        bntP1Evocada_2.setText("<html>Campeao 2<br /><br />Vida: 10<br />Ataque: 5<br />Nivel: 1</html>");
        bntP1Evocada_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP1EvocadaActionPerformed(evt, 2);
            }
        });
        bntP1Evocada_2.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP1Evocada_2.setBounds(340, 0, 80, 90);
        panelEvocadasP1.add(bntP1Evocada_2);
        
        bntP1Evocada_3 = new JButton();
        bntP1Evocada_3.setText("<html>Campeao 3<br /><br />Vida: 10<br />Ataque: 5<br />Nivel: 1</html>");
        bntP1Evocada_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP1EvocadaActionPerformed(evt, 3);
            }
        });
        bntP1Evocada_3.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP1Evocada_3.setBounds(430, 0, 80, 90);
        panelEvocadasP1.add(bntP1Evocada_3);
        
        bntP1Evocada_4 = new JButton();
        panelEvocadasP1.setLayer(bntP1Evocada_4, 0);
        bntP1Evocada_4.setText("<html>Campeao 4<br /><br />Vida: 10<br />Ataque: 5<br />Nivel: 1</html>");
        bntP1Evocada_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP1EvocadaActionPerformed(evt, 4);
            }
        });
        bntP1Evocada_4.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP1Evocada_4.setBounds(70, 0, 80, 90);
        panelEvocadasP1.add(bntP1Evocada_4);
        
        bntP1Evocada_5 = new JButton();
        bntP1Evocada_5.setText("<html>Campeao 5<br /><br />Vida: 10<br />Ataque: 5<br />Nivel: 1</html>");
        bntP1Evocada_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP1EvocadaActionPerformed(evt, 5);
            }
        });
        bntP1Evocada_5.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP1Evocada_5.setBounds(520, 0, 80, 90);
        panelEvocadasP1.add(bntP1Evocada_5);
        
        bntP1Mao_0 = new JButton();
        bntP1Mao_0.setText("<html>Campeao 0<br /><br />Vida: 10<br />Ataque: 5<br />Custo: 0</html>");
        bntP1Mao_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP1MaoActionPerformed(evt, 0);
            }
        });
        bntP1Mao_0.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP1Mao_0.setBounds(160, 0, 80, 90);
        panelMaoP1.add(bntP1Mao_0);
        
        bntP1Mao_1 = new JButton();
        bntP1Mao_1.setText("<html>Campeao 1<br /><br />Vida: 10<br />Ataque: 5<br />Custo: 0</html>");
        bntP1Mao_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP1MaoActionPerformed(evt, 1);
            }
        });
        bntP1Mao_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP1Mao_1.setBounds(250, 0, 80, 90);
        panelMaoP1.add(bntP1Mao_1);
        
        bntP1Mao_2 = new JButton();
        bntP1Mao_2.setText("<html>Campeao 2<br /><br />Vida: 10<br />Ataque: 5<br />Custo: 0</html>");
        bntP1Mao_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP1MaoActionPerformed(evt, 2);
            }
        });
        bntP1Mao_2.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP1Mao_2.setBounds(340, 0, 80, 90);
        panelMaoP1.add(bntP1Mao_2);
        
        bntP1Mao_3 = new JButton();
        bntP1Mao_3.setText("<html>Campeao 3<br /><br />Vida: 10<br />Ataque: 5<br />Custo: 0</html>");
        bntP1Mao_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP1MaoActionPerformed(evt, 3);
            }
        });
        bntP1Mao_3.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP1Mao_3.setBounds(430, 0, 80, 90);
        panelMaoP1.add(bntP1Mao_3);
        
        bntP1Mao_4 = new JButton();
        panelMaoP1.setLayer(bntP1Mao_4, 0);
        bntP1Mao_4.setText("<html>Campeao 4<br /><br />Vida: 10<br />Ataque: 5<br />Custo: 0</html>");
        bntP1Mao_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP1MaoActionPerformed(evt, 4);
            }
        });
        bntP1Mao_4.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP1Mao_4.setBounds(70, 0, 80, 90);
        panelMaoP1.add(bntP1Mao_4);
        
        bntP1Mao_5 = new JButton();
        bntP1Mao_5.setText("<html>Campeao 5<br /><br />Vida: 10<br />Ataque: 5<br />Custo: 0</html>");
        bntP1Mao_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bntP1MaoActionPerformed(evt, 5);
            }
        });
        bntP1Mao_5.setFont(new Font("Tahoma", Font.PLAIN, 9));
        bntP1Mao_5.setBounds(520, 0, 80, 90);
        panelMaoP1.add(bntP1Mao_5);
        getContentPane().setLayout(layout);
        
       
        // ---------------------------------------------------------------------
        // ---------------------- Botoes de Informacao -------------------------
        // ---------------------------------------------------------------------
        
        btnInfoP1Mao_0 = new JButton("Info");
        btnInfoP1Mao_0.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnInfoP1MaoActionPerformed(e, 0);
        	}
        });
        panelMaoP1.setLayer(btnInfoP1Mao_0, 1);
        btnInfoP1Mao_0.setForeground(Color.BLACK);
        btnInfoP1Mao_0.setBackground(Color.ORANGE);
        btnInfoP1Mao_0.setBounds(160, 0, 80, 12);
        panelMaoP1.add(btnInfoP1Mao_0);
        
        btnInfoP1Mao_1 = new JButton("Info");
        btnInfoP1Mao_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnInfoP1MaoActionPerformed(e, 1);
        	}
        });
        panelMaoP1.setLayer(btnInfoP1Mao_1, 1);
        btnInfoP1Mao_1.setForeground(Color.BLACK);
        btnInfoP1Mao_1.setBackground(Color.ORANGE);
        btnInfoP1Mao_1.setBounds(250, 0, 80, 12);
        panelMaoP1.add(btnInfoP1Mao_1);
        
        btnInfoP1Mao_2 = new JButton("Info");
        btnInfoP1Mao_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnInfoP1MaoActionPerformed(e, 2);
        	}
        });
        panelMaoP1.setLayer(btnInfoP1Mao_2, 1);
        btnInfoP1Mao_2.setForeground(Color.BLACK);
        btnInfoP1Mao_2.setBackground(Color.ORANGE);
        btnInfoP1Mao_2.setBounds(340, 0, 80, 12);
        panelMaoP1.add(btnInfoP1Mao_2);
        
        btnInfoP1Mao_3 = new JButton("Info");
        btnInfoP1Mao_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnInfoP1MaoActionPerformed(e, 3);
        	}
        });
        panelMaoP1.add(btnInfoP1Mao_3);
        panelMaoP1.setLayer(btnInfoP1Mao_3, 1);
        btnInfoP1Mao_3.setForeground(Color.BLACK);
        btnInfoP1Mao_3.setBackground(Color.ORANGE);
        btnInfoP1Mao_3.setBounds(430, 0, 80, 12);
        
        btnInfoP1Mao_4 = new JButton("Info");
        btnInfoP1Mao_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnInfoP1MaoActionPerformed(e, 4);
        	}
        });
        panelMaoP1.setLayer(btnInfoP1Mao_4, 1);
        btnInfoP1Mao_4.setForeground(Color.BLACK);
        btnInfoP1Mao_4.setBackground(Color.ORANGE);
        btnInfoP1Mao_4.setBounds(70, 0, 80, 12);
        panelMaoP1.add(btnInfoP1Mao_4);
        
        btnInfoP1Mao_5 = new JButton("Info");
        btnInfoP1Mao_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnInfoP1MaoActionPerformed(e, 5);
        	}
        });
        panelMaoP1.setLayer(btnInfoP1Mao_5, 1);
        btnInfoP1Mao_5.setForeground(Color.BLACK);
        btnInfoP1Mao_5.setBackground(Color.ORANGE);
        btnInfoP1Mao_5.setBounds(520, 0, 80, 12);
        panelMaoP1.add(btnInfoP1Mao_5);
        
        
        btnInfoP2Mao_0 = new JButton("Info");
        btnInfoP2Mao_0.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnInfoP2MaoActionPerformed(e, 0);
        	}
        });
        panelMaoP2.setLayer(btnInfoP2Mao_0, 1);
        btnInfoP2Mao_0.setForeground(Color.BLACK);
        btnInfoP2Mao_0.setBackground(Color.ORANGE);
        btnInfoP2Mao_0.setBounds(160, 0, 80, 12);
        panelMaoP2.add(btnInfoP2Mao_0);
        
        btnInfoP2Mao_1 = new JButton("Info");
        btnInfoP2Mao_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnInfoP2MaoActionPerformed(e, 1);
        	}
        });
        panelMaoP2.setLayer(btnInfoP2Mao_1, 1);
        btnInfoP2Mao_1.setForeground(Color.BLACK);
        btnInfoP2Mao_1.setBackground(Color.ORANGE);
        btnInfoP2Mao_1.setBounds(250, 0, 80, 12);
        panelMaoP2.add(btnInfoP2Mao_1);
        
        btnInfoP2Mao_2 = new JButton("Info");
        btnInfoP2Mao_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnInfoP2MaoActionPerformed(e, 2);
        	}
        });
        panelMaoP2.setLayer(btnInfoP2Mao_2, 1);
        btnInfoP2Mao_2.setForeground(Color.BLACK);
        btnInfoP2Mao_2.setBackground(Color.ORANGE);
        btnInfoP2Mao_2.setBounds(340, 0, 80, 12);
        panelMaoP2.add(btnInfoP2Mao_2);
        
        btnInfoP2Mao_3 = new JButton("Info");
        btnInfoP2Mao_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnInfoP2MaoActionPerformed(e, 3);
        	}
        });
        panelMaoP2.setLayer(btnInfoP2Mao_3, 1);
        btnInfoP2Mao_3.setForeground(Color.BLACK);
        btnInfoP2Mao_3.setBackground(Color.ORANGE);
        btnInfoP2Mao_3.setBounds(430, 0, 80, 12);
        panelMaoP2.add(btnInfoP2Mao_3);
        
        btnInfoP2Mao_4 = new JButton("Info");
        btnInfoP2Mao_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnInfoP2MaoActionPerformed(e, 4);
        	}
        });
        panelMaoP2.setLayer(btnInfoP2Mao_4, 1);
        btnInfoP2Mao_4.setForeground(Color.BLACK);
        btnInfoP2Mao_4.setBackground(Color.ORANGE);
        btnInfoP2Mao_4.setBounds(70, 0, 80, 12);
        panelMaoP2.add(btnInfoP2Mao_4);
        
        btnInfoP2Mao_5 = new JButton("Info");
        panelMaoP2.setLayer(btnInfoP2Mao_5, 1);
        btnInfoP2Mao_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnInfoP2MaoActionPerformed(e, 5);
        	}
        });
        btnInfoP2Mao_5.setForeground(Color.BLACK);
        btnInfoP2Mao_5.setBackground(Color.ORANGE);
        btnInfoP2Mao_5.setBounds(520, 0, 80, 12);
        panelMaoP2.add(btnInfoP2Mao_5);
        

        // -------------------------------------------------------------------
        // --------------------- Adicionar aos ArrayLists --------------------  
        pack();
          
        maoP1.add(bntP1Mao_0);
        maoP1.add(bntP1Mao_1);
        maoP1.add(bntP1Mao_2);
        maoP1.add(bntP1Mao_3);
        maoP1.add(bntP1Mao_4);
        maoP1.add(bntP1Mao_5);

        maoP2.add(bntP2Mao_0);
        maoP2.add(bntP2Mao_1);
        maoP2.add(bntP2Mao_2);
        maoP2.add(bntP2Mao_3);
        maoP2.add(bntP2Mao_4);
        maoP2.add(bntP2Mao_5);
        
        infoP1Mao.add(btnInfoP1Mao_0);
        infoP1Mao.add(btnInfoP1Mao_1);
        infoP1Mao.add(btnInfoP1Mao_2);
        infoP1Mao.add(btnInfoP1Mao_3);
        infoP1Mao.add(btnInfoP1Mao_4);
        infoP1Mao.add(btnInfoP1Mao_5);
        
        infoP2Mao.add(btnInfoP2Mao_0);
        infoP2Mao.add(btnInfoP2Mao_1);
        infoP2Mao.add(btnInfoP2Mao_2);
        infoP2Mao.add(btnInfoP2Mao_3);
        infoP2Mao.add(btnInfoP2Mao_4);
        infoP2Mao.add(btnInfoP2Mao_5);
 
        evocadasP1.add(bntP1Evocada_0);
        evocadasP1.add(bntP1Evocada_1);
        evocadasP1.add(bntP1Evocada_2);
        evocadasP1.add(bntP1Evocada_3);
        evocadasP1.add(bntP1Evocada_4);
        evocadasP1.add(bntP1Evocada_5);
        
        evocadasP2.add(bntP2Evocada_0);
        evocadasP2.add(bntP2Evocada_1);
        evocadasP2.add(bntP2Evocada_2);
        evocadasP2.add(bntP2Evocada_3);
        evocadasP2.add(bntP2Evocada_4);
        evocadasP2.add(bntP2Evocada_5);
        
        emCampoP1.add(bntP1EmCampo_0);
        emCampoP1.add(bntP1EmCampo_1);
        emCampoP1.add(bntP1EmCampo_2);
        emCampoP1.add(bntP1EmCampo_3);
        emCampoP1.add(bntP1EmCampo_4);
        emCampoP1.add(bntP1EmCampo_5);
        
        emCampoP2.add(bntP2EmCampo_0);
        emCampoP2.add(bntP2EmCampo_1);
        emCampoP2.add(bntP2EmCampo_2);
        emCampoP2.add(bntP2EmCampo_3);
        emCampoP2.add(bntP2EmCampo_4);
        emCampoP2.add(bntP2EmCampo_5);
    }// </editor-fold>                        
    
//=========================================================================================================================
/*												Clique de Botoes	
 * 						  							 */
    private void bntAvancarTurnoActionPerformed(java.awt.event.ActionEvent evt) {
    	if(realizouAcao == true || rodada.getTipo() == TipoRodada.ESCOLHA_DEFENSORES) {
    		rodada.mudarTipo();
    	}
    	rodada.finalizarTurno(this.realizouAcao);
    	iniciarTurno();
    }
    
    private void bntP1MaoActionPerformed(java.awt.event.ActionEvent evt, int posicao) {
        if(campo.getP1().comprarCarta(posicao, campo)) {
        	atualizarMao();
        	atualizarEvocadas();
        	realizouAcao = true;
        	rodada.finalizarTurno(this.realizouAcao);
        	iniciarTurno();
        }
        else {
        	System.out.println("Nao foi possivel comprar essa carta! e"
        			+ "scolha outra ou finalize o turno!");
        }
    }
    
    private void bntP2MaoActionPerformed(java.awt.event.ActionEvent evt, int posicao) { 
        if(campo.getP2().comprarCarta(posicao, campo)) {
        	atualizarMao();
        	atualizarEvocadas();
        	realizouAcao = true;
        	rodada.finalizarTurno(this.realizouAcao);
        	iniciarTurno();
        }
        else {
        	System.out.println("Nao foi possivel comprar essa carta! escolha outra ou finalize o turno!");
        }
    }
    
    private void bntP1EvocadaActionPerformed(java.awt.event.ActionEvent evt, int posicao) {
    	Runnable aguardarP1Evocada = new Runnable() {
        	public void run() {
        		try {
        			bntAvancarTurno.setEnabled(false);
        			desativarEvocadasP1();
        			desativarMaoP1();
            		ativarCampoSelecionavelP1();
            		while(aguardandoIndex) {
            			Thread.sleep(100);
            		}
            		campo.getP1().colocarEmCampo(posicao, indexEscolhido);
            		atualizarEmCampo();
            		atualizarEvocadas();
            		desativarEmCampoP1();
            		ativarEvocadasP1();
            		bntAvancarTurno.setEnabled(true);
            		if(campo.getP1().getTurno() == Turno.ATAQUE) {
            			bntAvancarTurno.setText("<html>Atacar</html>");
            		}
            		else {
            			bntAvancarTurno.setText("<html>Defender</html>");
            		}
        		} catch (Exception e) {}
        	}
        };
    	if(aguardandoCarta == false) {
    		System.out.println("Selecione o local do campo para a carta!");
    		aguardandoIndex = true;
    		Thread tarefa = new Thread(aguardarP1Evocada);
    		tarefa.start();
    	}
    	else {
    		cartaEscolhida = campo.getP1().getEvocadas().get(posicao);
    		aguardandoCarta = false;
    	}
    	realizouAcao = true;
    }
    
    private void bntP2EvocadaActionPerformed(java.awt.event.ActionEvent evt, int posicao) {
    	Runnable aguardarP2Evocada = new Runnable() {
        	public void run() {
        		try {
        			bntAvancarTurno.setEnabled(false);
        			desativarEvocadasP2();
        			desativarMaoP2();
            		ativarCampoSelecionavelP2();
            		while(aguardandoIndex) {  
            			Thread.sleep(100);    
            		}
            		campo.getP2().colocarEmCampo(posicao, indexEscolhido);
            		atualizarEmCampo();
            		atualizarEvocadas();
            		desativarEmCampoP2();
            		ativarEvocadasP2();
            		bntAvancarTurno.setEnabled(true);
            		if(campo.getP2().getTurno() == Turno.ATAQUE) {
            			bntAvancarTurno.setText("<html>Atacar</html>");
            		}
            		else {
            			bntAvancarTurno.setText("<html>Defender</html>");
            		}
        		} catch (Exception e) {}
        	}
        };
    	if(aguardandoCarta == false) {
    		System.out.println("Selecione o local do campo para a carta!");
    		aguardandoIndex = true;
    		Thread tarefa = new Thread(aguardarP2Evocada);
    		tarefa.start();
    	}
    	else {
    		cartaEscolhida = campo.getP2().getEvocadas().get(posicao);
    		aguardandoCarta = false;
    	}
    	realizouAcao = true;
    }
    
    private void bntP1EmCampoActionPerformed(java.awt.event.ActionEvent evt, int posicao) {
    	if(aguardandoCarta == true) {
    		cartaEscolhida = campo.getP1().getEmCampo().get(posicao);
    		aguardandoCarta = false;
    	}
    	else if(aguardandoIndex == true) {
    		indexEscolhido = posicao;
    		aguardandoIndex = false;
    	}
    	realizouAcao = true;
    }
    
    private void bntP2EmCampoActionPerformed(java.awt.event.ActionEvent evt, int posicao) {
    	if(aguardandoCarta == true) {
    		cartaEscolhida = campo.getP2().getEmCampo().get(posicao);
    		aguardandoCarta = false;
    	}
    	else if(aguardandoIndex == true) {
    		indexEscolhido = posicao;
    		aguardandoIndex = false;
    	}
    	realizouAcao = true;
    }
    
    
    private void btnInfoP2MaoActionPerformed(ActionEvent evt, int posicao){
    	Carta carta = campo.getP2().getMao().get(posicao);
    	System.out.println(carta.toStringDetalhes());
    }    
    
    private void btnInfoP1MaoActionPerformed(ActionEvent evt, int posicao){
    	Carta carta = campo.getP1().getMao().get(posicao);
    	System.out.println(carta.toStringDetalhes());
    }  
    
    
   
    
//=========================================================================================================================    
    
    private javax.swing.JLayeredPane panelCampo;
    private JLayeredPane panelEvocadasP1;
    private JLayeredPane panelMaoP1;
    private JButton bntP1Mao_0;
    private JButton bntP1Mao_1;
    private JButton bntP1Mao_2;
    private JButton bntP1Mao_3;
    private JButton bntP1Mao_4;
    private JButton bntP1Mao_5;
    private JButton bntP1Evocada_0;
    private JButton bntP1Evocada_1;
    private JButton bntP1Evocada_2;
    private JButton bntP1Evocada_3;
    private JButton bntP1Evocada_4;
    private JButton bntP1Evocada_5;
    private JLayeredPane panelEvocadasP2;
    private JButton bntP2Evocada_0;
    private JButton bntP2Evocada_1;
    private JButton bntP2Evocada_2;
    private JButton bntP2Evocada_3;
    private JButton bntP2Evocada_4;
    private JButton bntP2Evocada_5;
    private JLayeredPane panelMaoP2;
    private JButton bntP2Mao_0;
    private JButton bntP2Mao_1;
    private JButton bntP2Mao_2;
    private JButton bntP2Mao_3;
    private JButton bntP2Mao_4;
    private JButton bntP2Mao_5;
    private JLayeredPane panelNexus;
    private JLabel jLabel1;
    private JLabel jLabel0;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jblP1VidaNexus;
    private JLabel jblP2VidaNexus;
    private JLayeredPane panelInformacoes;
    private JLabel jLabel7;
    private JButton bntAvancarTurno;
    private JLabel jLabel6;
    private JLabel jLabel5;
    private JLabel jblP1Mana;
    private JLabel jblP1ManaFeitico;
    private JLabel jLabel4;
    private JLabel jblTipoDeTurnoPlayer1;
    private JLabel jblP2Mana;
    private JLabel jblP2ManaFeitico;
    private JLabel jblTipoDeTurnoPlayer2;
    private JLayeredPane panelCampoP1;
    private JButton bntP1EmCampo_1;
    private JButton bntP1EmCampo_2;
    private JButton bntP1EmCampo_3;
    private JButton bntP1EmCampo_0;
    private JButton bntP1EmCampo_4;
    private JButton bntP1EmCampo_5;
    private JLayeredPane panelCampoP2;
    private JButton bntP2EmCampo_1;
    private JButton bntP2EmCampo_2;
    private JButton bntP2EmCampo_3;
    private JButton bntP2EmCampo_0;
    private JButton bntP2EmCampo_4;
    private JButton bntP2EmCampo_5;
    private JButton btnInfoP1Mao_3;
    private JButton btnInfoP1Mao_5;
    private JButton btnInfoP2Mao_0;
    private JButton btnInfoP2Mao_4;
    private JButton btnInfoP2Mao_1;
    private JButton btnInfoP2Mao_2;
    private JButton btnInfoP2Mao_5;
    private JButton btnInfoP2Mao_3;
    private JButton btnInfoP1Mao_4;
    private JButton btnInfoP1Mao_0;
    private JButton btnInfoP1Mao_1;
    private JButton btnInfoP1Mao_2;
}
