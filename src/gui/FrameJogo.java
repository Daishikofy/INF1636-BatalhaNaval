package gui;

import regras.*;

import java.awt.*;
import javax.swing.*;

public class FrameJogo extends JFrame {
	final int LARG_DEFAULT=410;
	final int ALT_DEFAULT=450;
	
	public FrameJogo(CtrlRegras c) {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(new PanelTabuleiro(c));
		setTitle("Jogo da Velha");
	}
	
	public static void main(String args[]) {
		
		(new FrameJogo(new CtrlRegras())).setVisible(true);
	}
}
