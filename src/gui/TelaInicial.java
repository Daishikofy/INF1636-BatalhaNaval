package gui;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import regras.RegraJogo;

public class TelaInicial extends JPanel implements ActionListener{
	
	JButton novoJogo;
	JButton carregarJogo;
	
	public TelaInicial() {
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		novoJogo = new JButton("Novo Jogo");
		carregarJogo = new JButton("Carregar ultimo jogo");
		novoJogo.addActionListener(this);
		carregarJogo.addActionListener(this);
		
		add(novoJogo);
		add(carregarJogo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action == "Novo Jogo")
		{
			System.out.println("Novo Jogo");
			RegraJogo.Instance().escolherJogadores();
		}
		else if (action == "Carregar ultimo jogo")
		{
			System.out.println("Carregar ultimo jogo");
			JFileChooser fc = new JFileChooser(); 
			int rv = fc.showOpenDialog(this);
			if(rv == JFileChooser.APPROVE_OPTION) {
				RegraJogo.Instance().carregarJogo(fc.getSelectedFile());	
			}
		}
	}
}