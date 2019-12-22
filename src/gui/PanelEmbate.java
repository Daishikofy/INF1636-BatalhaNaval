package gui;

import regras.RegraGeral;
import regras.RegraJogo;
import regras.RegraPreenchimento;

import javax.swing.*;

import interfaces.IObservador;

// Definir o número de linhas na classe tabuleiro

public class PanelEmbate extends JPanel implements IObservador{

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
		var regra = RegraJogo.Instance().getRegra();
		System.out.println("Update" + regra.getVez());
		String jogador = regra.getVez();
		atualizaJogadorCorrente(jogador);
	}
	
}
