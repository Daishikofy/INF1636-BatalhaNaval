package gui;

import regras.RegraGeral;
import regras.RegraPreenchimento;

import javax.swing.*;

// Definir o número de linhas na classe tabuleiro

public class Tabuleiro extends JPanel {
	static private Tabuleiro instance = null;
	//private RegraGeral regras1, regras2;
	
	private Tabuleiro() {
		//regras1 = new RegraPreenchimento();
		//regras2 = new RegraPreenchimento();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new PanelMatriz());
		add(new PanelMatriz());
	}
	
	static Tabuleiro getTabuleiro() {
		if(instance == null) {
			instance = new Tabuleiro();
		}
		return instance;
	}
}
