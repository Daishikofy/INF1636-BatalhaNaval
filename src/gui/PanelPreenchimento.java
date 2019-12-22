package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import interfaces.IObservador;
import regras.GerenciadorDePreenchimento;
import regras.RegraJogo;

public class PanelPreenchimento extends JPanel implements ActionListener,IObservador{
	
	JButton botaoContinuar;
	JLabel jogadorCorrente;
	
	public PanelPreenchimento() {
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		botaoContinuar = new JButton();
		jogadorCorrente = new JLabel("NOME DO JOGADOR");
		botaoContinuar.setText("Finalizar");
		botaoContinuar.setEnabled(false);
		botaoContinuar.addActionListener(this);
		add(new GridPecas());
		add(new GridTabuleiro());
		add(botaoContinuar);
		add(jogadorCorrente);
		
		GerenciadorDePreenchimento.getManager().getRegra().updateUI.cadastrar((IObservador)this);
		update();
	}

	private void podeFinalizar (Boolean value)
	{		
		botaoContinuar.setEnabled(value);
	}
	
	private void atualizaJogadorCorrente (String nome)
	{
		jogadorCorrente.setText(nome);
	}

	@Override
	public void update() {
		
		var regra = GerenciadorDePreenchimento.getManager().getRegra();
		System.out.println("Update" + regra.getVez());
		String jogador = regra.getVez();
		atualizaJogadorCorrente(jogador);		
		podeFinalizar(regra.podeFinalizar());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action == "Finalizar")
		{			
			RegraJogo.Instance().trocarJogadorPreenchimento();
		}
	}
	
}
