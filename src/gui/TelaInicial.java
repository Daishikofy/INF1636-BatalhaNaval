package gui;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import regras.RegraJogo;

public class TelaInicial extends JPanel implements ActionListener{
	
	JButton novoJogo;
	JButton carregarJogo;
	
	public TelaInicial() {
		
		setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        
        JLabel titulo = new JLabel("Batalha Naval");
        titulo.setFont (titulo.getFont ().deriveFont (64.0f));
        titulo.setForeground(Color.GRAY.darker());
        
        add(titulo, gbc);
        
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());
        
        novoJogo = new JButton("Novo Jogo");
        novoJogo.addActionListener(this);
        
		carregarJogo = new JButton("Carregar ultimo jogo");		
		carregarJogo.addActionListener(this);		
        
        buttons.add(novoJogo, gbc);
        buttons.add(new JLabel(" "),gbc);
        buttons.add(carregarJogo, gbc);

        gbc.weighty = 1;
        add(buttons, gbc);
		
		/*
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		*/
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action == "Novo Jogo")
		{
			RegraJogo.Instance().escolherJogadores();
		}
		else if (action == "Carregar ultimo jogo")
		{
			JFileChooser fc = new JFileChooser(); 
			int rv = fc.showOpenDialog(this);
			if(rv == JFileChooser.APPROVE_OPTION) {
				RegraJogo.Instance().carregarJogo(fc.getSelectedFile());	
			}
		}
	}
}