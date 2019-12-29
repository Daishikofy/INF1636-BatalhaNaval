package gui;
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
		System.out.println("Update" + regra.getVez());
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
