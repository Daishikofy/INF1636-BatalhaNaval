package gui;

import regras.*;
import regras.RegraJogo.EstadoDoJogo;

import java.awt.*;
import javax.swing.*;

import interfaces.IObservador;

public class FrameJogo extends JFrame implements IObservador{
	final int LARG_DEFAULT=1200;
	final int ALT_DEFAULT=600;
	
	FrameJogo instance;
	JPanel currentPanel;
	
	public FrameJogo() {
		instance = this;
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		setTitle("Batalha Naval");
		TelaInicial telaIncial = new TelaInicial();
		currentPanel = telaIncial;
		this.getContentPane().add(telaIncial);
	}

	public static void main(String args[]) {
		RegraJogo regraJogo = RegraJogo.Instance();		
		FrameJogo frameJogo = new FrameJogo(); 
		regraJogo.trocaPanel.cadastrar(frameJogo);
		frameJogo.setVisible(true);
	}

	@Override
	public void update() {
		System.out.println("Trocar panel");
		JPanel newPanel = null;
		EstadoDoJogo panel = RegraJogo.Instance().getEstado();	
		switch (panel) {
		
		case EMBATE:
			
			break;
		case TELAINICIAL:
			newPanel = new TelaInicial();
					
			break;
		case POSICIONAMENTO:
			newPanel = new PanelPreenchimento();
			System.out.println("panel de preenchimento");
			
			break;
		case ESCOLHAJOGADORES:
			DialogoJogadores dialogo = new DialogoJogadores(instance);
			dialogo.setVisible(true);
			
			break;
		}
		
		if (newPanel != null)
		{
			System.out.println("HEYO");
			currentPanel.removeAll();
			currentPanel.setVisible(false);
			currentPanel = newPanel;
			currentPanel.setVisible(true);
			instance.add(newPanel);
		}
	}
	
}
