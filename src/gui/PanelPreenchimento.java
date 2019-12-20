package gui;
import javax.swing.*;

import interfaces.IObservador;
import regras.GerenciadorDePreenchimento;

public class PanelPreenchimento extends JPanel implements IObservador{
	
	JButton botaoContinuar;
	JLabel jogadorCorrente;
	
	public PanelPreenchimento() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		botaoContinuar = new JButton();
		jogadorCorrente = new JLabel("NOME DO JOGADOR");
		botaoContinuar.setText("Finalizar");
		botaoContinuar.setEnabled(false);
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
		System.out.println("Update");
		var regra = GerenciadorDePreenchimento.getManager().getRegra();
		int vez = regra.getVez();
		//TODO : Implementar o nome para a vez
		//DEBUG : Abaixo so pra teste
		if (vez == 1)
			atualizaJogadorCorrente("Julieta");
		else
			atualizaJogadorCorrente("Marcela");
		
		podeFinalizar(regra.podeFinalizar());
	}
	
}
