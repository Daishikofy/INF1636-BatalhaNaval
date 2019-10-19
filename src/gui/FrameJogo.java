package gui;

import regras.*;

import java.awt.*;
import javax.swing.*;

public class FrameJogo extends JFrame {
	final int LARG_DEFAULT=1200;
	final int ALT_DEFAULT=600;
	
	public FrameJogo(CtrlRegras c) {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		mainPanel.add(new PanelMatriz(c));
		mainPanel.add(new PanelMatriz(c));
		
		getContentPane().add(mainPanel);
		
		setTitle("Jogo da Velha");
	}
	
	public static void main(String args[]) {
		
		(new FrameJogo(new CtrlRegras())).setVisible(true);
	}
}
