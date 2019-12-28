package gui;

import regras.RegraEmbate;
import regras.RegraJogo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import interfaces.IObservador;
import interfaces.Regra;

// Definir o número de linhas na classe tabuleiro

@SuppressWarnings("serial")
public class PanelEmbate extends JPanel implements IObservador, ActionListener {
	
	static PanelEmbate instance = null;
	RegraEmbate regra;
	JLabel jogadorCorrente;
	JButton comeco;
	
	private PanelEmbate() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel tabs = new JPanel();
		tabs.setLayout(new BoxLayout(tabs, BoxLayout.X_AXIS));
		jogadorCorrente = new JLabel("Jogador 1");
		tabs.add(new GridTabuleiro(0));
		tabs.add(new GridTabuleiro(1));
		add(tabs);
		add(jogadorCorrente);
		
		comeco = new JButton("Ver tabuleiro");
		comeco.addActionListener(this);
		add(comeco);
		regra = (RegraEmbate) RegraJogo.Instance().getRegra();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == comeco.getActionCommand()) {
			System.out.println("MostrarTabuleiro");
			regra.mostrarTabuleiro();
		}
	}
}
