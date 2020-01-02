package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import regras.RegraJogo;

@SuppressWarnings("serial")
public class DialogoFimDeJogo extends JDialog implements ActionListener {
	
	final int LARG_DEFAULT=250;
	final int ALT_DEFAULT=200;
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
		setLayout(new GridBagLayout());
		
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        
		JLabel l1 = new JLabel("Jogador vencedor:");
		JLabel l2 = new JLabel(RegraJogo.Instance().getVencedor());
        l1.setFont(l1.getFont().deriveFont (14.0f));
        l2.setFont(l2.getFont().deriveFont(22.0f));
        l1.setForeground(Color.GRAY.darker());
        l2.setForeground(Color.green.darker().darker());
        
        add(l1, gbc);
        add(l2, gbc);
        
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;		
		
		botaoComecar = new JButton("OK");
		botaoComecar.addActionListener(this);
		mainPane.add(botaoComecar, gbc);
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
