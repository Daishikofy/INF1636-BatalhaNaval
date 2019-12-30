package gui;

import regras.*;
import regras.RegraJogo.EstadoDoJogo;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import interfaces.IObservador;

public class FrameJogo extends JFrame implements IObservador, ActionListener {
	final int LARG_DEFAULT=1200;
	final int ALT_DEFAULT=620;
	
	FrameJogo instance;
	
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuSalvar;
	
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
		
		menuBar = new JMenuBar();
		menu = new JMenu("Arquivo");
		menuSalvar = new JMenuItem("Salvar jogo");
		
		menuBar.add(menu);
		menu.add(menuSalvar);
		
		menuSalvar.setEnabled(false);
		menuSalvar.addActionListener(this);
		
		this.setJMenuBar(menuBar);
		menuBar.setVisible(true);
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
		menuSalvar.setEnabled(false);
		JPanel newPanel = null;
		EstadoDoJogo panel = RegraJogo.Instance().getEstado();	
		switch (panel) {
		
		case EMBATE:
			newPanel = new PanelEmbate();
			menuSalvar.setEnabled(true);
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
			
		case FINALIZADO:
			DialogoFimDeJogo fim = new DialogoFimDeJogo(instance);
			fim.setVisible(true);
			
			break;
			
		}
		
		if (newPanel != null)
		{			
			currentPanel.removeAll();
			currentPanel.setVisible(false);
			currentPanel = newPanel;
			System.out.println("HEYO");
			currentPanel.setVisible(true);
			System.out.println("HEYO");
			instance.add(newPanel);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Salvar jogo")) {
			JFileChooser fc = new JFileChooser();
			int ret = fc.showSaveDialog(this);
			if(ret == JFileChooser.APPROVE_OPTION) {
				RegraJogo.Instance().salvarJogo(fc.getSelectedFile());	
			}
		}
	}
	
}
