package gui;

import regras.RegraEmbate;
import regras.RegraJogo;
import javax.swing.*;

import interfaces.IObservador;
import interfaces.Regra;

// Definir o número de linhas na classe tabuleiro

public class PanelEmbate extends JPanel implements IObservador{
	
	static PanelEmbate instance = null;
	Regra regra;
	JLabel jogadorCorrente;
	
	private PanelEmbate() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		jogadorCorrente = new JLabel("Jogador 1");
		add(new GridTabuleiro(0));
		add(new GridTabuleiro(1));
		add(jogadorCorrente);
		
		regra = RegraJogo.Instance().getRegra();
		update();
	}

	private void atualizaJogadorCorrente (String nome)
	{
		jogadorCorrente.setText(nome);
	}
	@Override
	public void update() {
		System.out.println("Update " + regra.getVez());
		String jogador = regra.getVez();
		atualizaJogadorCorrente(jogador);
	}
	
	static PanelEmbate getPanel() {
		if (instance == null) {
			instance = new PanelEmbate();
		}
		return instance;
	}
}
