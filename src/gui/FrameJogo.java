package gui;

import regras.*;

import java.awt.*;
import javax.swing.*;

public class FrameJogo extends JFrame {
	final int LARG_DEFAULT=1200;
	final int ALT_DEFAULT=600;
	
	public FrameJogo(/*RegraGeral c*/) {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Tabuleiro tab = Tabuleiro.getTabuleiro();
		PanelPreenchimento tab = new PanelPreenchimento();
		tab.setVisible(true);
		getContentPane().add(tab);
		
		setTitle("Batalha Naval");
	}

	public static void main(String args[]) {
		FrameJogo frameJogo = new FrameJogo(/*new RegraPreenchimento()*/); 
		DialogoJogadores dialogo = new DialogoJogadores(frameJogo);
		dialogo.setVisible(true);
		frameJogo.setVisible(true);
	}
	
}
