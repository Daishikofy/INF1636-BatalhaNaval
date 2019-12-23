package gui;

import regras.RegraEmbate;
import regras.RegraJogo;
import javax.swing.*;

import interfaces.IObservador;

// Definir o número de linhas na classe tabuleiro

public class PanelEmbate extends JPanel implements IObservador{
	static PanelEmbate instance = null;

	JLabel jogadorCorrente;
	
	private PanelEmbate() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		jogadorCorrente = new JLabel("Jogador 1");
		add(new GridTabuleiro());
		add(new GridTabuleiro());
		add(jogadorCorrente);
		
		update();
	}

	private void atualizaJogadorCorrente (String nome)
	{
		jogadorCorrente.setText(nome);
	}
	@Override
	public void update() {
		RegraEmbate regra = RegraJogo.Instance().getRegra();
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
