package gui;

import regras.CtrlRegras;
import javax.swing.*;

public class Tabuleiro extends JPanel {
	static private Tabuleiro instance = null;
	private CtrlRegras regras1, regras2;
	
	private Tabuleiro() {
		regras1 = new CtrlRegras();
		regras2 = new CtrlRegras();
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
