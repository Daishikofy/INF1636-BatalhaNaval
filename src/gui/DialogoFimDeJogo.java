package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import regras.RegraJogo;

@SuppressWarnings("serial")
public class DialogoFimDeJogo extends JDialog implements ActionListener {
	
	final int LARG_DEFAULT=200;
	final int ALT_DEFAULT=150;
	final int x_almejado = 75, y_almejado = 4;
	
	String jogador1;
	String jogador2;
	JTextField textoJogador1;
	JTextField textoJogador2;
	JButton botaoComecar;
	
	Frame frame;
	
	public DialogoFimDeJogo(Frame fr) {
		super(fr, "Fim de jogo!");
		frame = fr;
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int sl = screenSize.width;
		int sa = screenSize.height;
		int x = sl/2 - LARG_DEFAULT/2;
		int y = sa/2 - ALT_DEFAULT/2;
		setBounds(x, y, LARG_DEFAULT, ALT_DEFAULT);
		
		JPanel mainPane = (JPanel) this.getContentPane();
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));
		

		
		JLabel l1 = new JLabel("Jogador vencedor: "+ RegraJogo.Instance().getVencedor());
		mainPane.add(l1);
		
		
		botaoComecar = new JButton("OK");
		botaoComecar.addActionListener(this);
		mainPane.add(botaoComecar);
	}

	public void paint(Graphics g) {
		super.paint(g);
	}
	
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
        if (action.equals("OK")) {
    		RegraJogo.Instance().jogoFinalizado();
    		this.dispose();
        }
	}
}
