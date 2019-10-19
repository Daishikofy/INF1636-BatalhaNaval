package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class DialogoJogadores extends JDialog implements ActionListener {
	
	String jogador1;
	String jogador2;
	JTextField textoJogador1;
	JTextField textoJogador2;
	JButton botaoComecar;
	
	public DialogoJogadores(Frame fr) {
		super(fr, "Jogadores");
		
		JPanel p = new JPanel();
		
		p.add(new JLabel("Jogador 1"));
		textoJogador1 = new JTextField("Jodador 1"); 
		p.add(textoJogador1);
		p.add(new JLabel("Jogador 2"));
		textoJogador2 = new JTextField("Jodador 2");
		p.add(textoJogador2);
		botaoComecar = new JButton("Começar");
		botaoComecar.addActionListener(this);
		p.add(botaoComecar);
		
		add(p);
	}

	public void paint(Graphics g) {
		super.paint(g);
	}
	
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
        if (s.equals("Começar")) {
        	this.dispose();
        	// Notificar quem ouve evento de mudança do nome dos jogadores
        }
	}
}
