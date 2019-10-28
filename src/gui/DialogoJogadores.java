package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class DialogoJogadores extends JDialog implements ActionListener {
	
	final int LARG_DEFAULT=200;
	final int ALT_DEFAULT=150;
	final int x_almejado = 75, y_almejado = 4;
	
	String jogador1;
	String jogador2;
	JTextField textoJogador1;
	JTextField textoJogador2;
	JButton botaoComecar;
	
	public DialogoJogadores(Frame fr) {
		super(fr, "Jogadores");
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int sl = screenSize.width;
		int sa = screenSize.height;
		int x = sl/2 - LARG_DEFAULT/2;
		int y = sa/2 - ALT_DEFAULT/2;
		setBounds(x, y, LARG_DEFAULT, ALT_DEFAULT);
		
		JPanel mainPane = (JPanel) this.getContentPane();
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		mainPane.add(p1);
		mainPane.add(p2);
		
		
		
		p1.setLayout(new BoxLayout(p1, BoxLayout.LINE_AXIS));
		JLabel l1 = new JLabel("        Jogador 1:    ");
		l1.setSize(LARG_DEFAULT/2, y_almejado);
		p1.add(l1);
		textoJogador1 = new JTextField("Jogador 1"); 
		textoJogador1.setSize(x_almejado, y_almejado);
		p1.add(textoJogador1);
		p1.setVisible(true);
		
		p2.setLayout(new BoxLayout(p2, BoxLayout.LINE_AXIS));
		JLabel l2 = new JLabel("        Jogador 2:    ");
		l2.setSize(LARG_DEFAULT/2, y_almejado);
		p2.add(l2);
		textoJogador2 = new JTextField("Jogador 2");
		textoJogador2.setSize(x_almejado, y_almejado);
		p2.add(textoJogador2);
		p2.setVisible(true);
		
		botaoComecar = new JButton("Começar");
		botaoComecar.addActionListener(this);
		mainPane.add(botaoComecar);
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
