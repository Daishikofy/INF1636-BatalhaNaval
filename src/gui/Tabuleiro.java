package gui;

import regras.RegraGeral;
import javax.swing.*;

// Definir o n�mero de linhas na classe tabuleiro

public class Tabuleiro extends JPanel {
	static private Tabuleiro instance = null;
	private RegraGeral regras1, regras2;
	
	private Tabuleiro() {
		regras1 = new RegraGeral();
		regras2 = new RegraGeral();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new PanelMatriz(regras1));
		add(new PanelMatriz(regras2));
	}
	
	static Tabuleiro getTabuleiro() {
		if(instance == null) {
			instance = new Tabuleiro();
		}
		return instance;
	}
}
