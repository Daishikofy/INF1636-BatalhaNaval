package gui;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import interfaces.IObservador;
import interfaces.Regra;
import regras.RegraJogo;

@SuppressWarnings("serial")
public class PanelPreenchimento extends JPanel implements ActionListener, IObservador {
	
	JButton botaoContinuar;
	JLabel jogadorCorrente;
	Regra regra;
	
	public PanelPreenchimento() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				
		jogadorCorrente = new JLabel("NOME DO JOGADOR");
		jogadorCorrente.setFont (jogadorCorrente.getFont ().deriveFont (24.0f));
		add(jogadorCorrente);
		
		
		JPanel tabuleiros = new JPanel();
		tabuleiros.setLayout(new BoxLayout(tabuleiros, BoxLayout.X_AXIS));
		
		GridTabuleiro gridTabuleiro = new GridTabuleiro(1);
		gridTabuleiro.addKeyListener(gridTabuleiro);
		gridTabuleiro.setFocusable(true);	
		
		tabuleiros.add(new GridPecas(0));
		tabuleiros.add(gridTabuleiro);	
		
		add(tabuleiros);
		
		botaoContinuar = new JButton();
		botaoContinuar.setText("Finalizar");
		botaoContinuar.setEnabled(false);
		botaoContinuar.addActionListener(this);
		add(botaoContinuar);
		
		regra = RegraJogo.Instance().getRegra();
		regra.ouvirAlteracoesUI(this);
		
		update();
	}
	
	private void atualizaJogadorCorrente (String nome)
	{
		jogadorCorrente.setText(nome);
	}

	@Override
	public void update() {				
		String jogador = regra.getVez();
		atualizaJogadorCorrente(jogador);		
		botaoContinuar.setEnabled(regra.podeFinalizar());			
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
