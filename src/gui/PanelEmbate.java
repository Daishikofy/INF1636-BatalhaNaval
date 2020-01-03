package gui;

import regras.RegraEmbate;
import regras.RegraJogo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import interfaces.IObservador;

// Definir o número de linhas na classe tabuleiro

@SuppressWarnings("serial")
public class PanelEmbate extends JPanel implements IObservador, ActionListener {

	RegraEmbate regra;
	JLabel jogadorCorrente, reportUltimaJogada;
	JButton comeco;
	
	public PanelEmbate() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		jogadorCorrente = new JLabel("Nome do jogador");
		jogadorCorrente.setFont (jogadorCorrente.getFont ().deriveFont (24.0f));
		add(jogadorCorrente);
		
		JPanel tabuleiros = new JPanel();
		tabuleiros.setLayout(new BoxLayout(tabuleiros, BoxLayout.X_AXIS));
		
		tabuleiros.add(new GridTabuleiro(0));
		tabuleiros.add(new GridTabuleiro(1));
		add(tabuleiros);
		
		JPanel buttoes = new JPanel();
		buttoes.setLayout(new BoxLayout(buttoes, BoxLayout.X_AXIS));
		comeco = new JButton("Ver tabuleiro");
		comeco.addActionListener(this);
		comeco.setEnabled(true);
		
		reportUltimaJogada = new JLabel("");
		buttoes.add(comeco);	
		buttoes.add(new JLabel("             . . .             "));
		buttoes.add(reportUltimaJogada);
		
		add(buttoes);
		regra = (RegraEmbate) RegraJogo.Instance().getRegra();
		regra.ouvirAlteracoesUI(this);
		update();
	}

	private void atualizaJogadorCorrente (String nome)
	{
		jogadorCorrente.setText(nome);
	}
	
	private void atualizaReport (String report)
	{
		if (report == null)
			report = "Esperando primeira jogada";
		reportUltimaJogada.setText(report);
	}
	@Override
	public void update() {
		String jogador = regra.getVez();
		atualizaJogadorCorrente(jogador);
		atualizaReport(regra.getReport());
		comeco.setEnabled(regra.podeFinalizar());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == comeco.getActionCommand()) {
			((RegraEmbate)regra).mostrarTabuleiro();
			comeco.setEnabled(false);
		}
	}
}
