package gui;
import javax.swing.*;

import interfaces.IObservador;
import regras.RegraGeral.EstadoDeCelula;
import regras.RegraPreenchimento;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GridPecas extends JPanel implements MouseListener, IObservador {

		double xIni=25.0, yIni=25.0, larg=30.0, alt=30.0, espLinha=2.0;
		int nLinhas = 15;
		Celula tab[][]=new Celula[nLinhas][nLinhas];
		RegraPreenchimento regra = new RegraPreenchimento(true);
		
		public GridPecas() {	
			
			addMouseListener(this);
			regra.cadastrar((IObservador)this);
			
			double x = xIni ,y = yIni;
			for(int i=0; i < nLinhas; i++) {
				x = xIni;
				for(int j=0; j < nLinhas; j++) {
					tab[i][j] = new Celula(x,y);
					x += larg + espLinha;
				}
				y += alt + espLinha;
			}
			int largura = (int) (2 * xIni + nLinhas * (larg + espLinha) + espLinha);
			int altura = (int) (2 * yIni + nLinhas * (alt + espLinha) + espLinha);
			this.setSize(largura, altura);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			
			g2d.setColor(Color.black);

			g2d.setStroke(new BasicStroke((float) espLinha,
	                BasicStroke.CAP_BUTT,
	                BasicStroke.JOIN_MITER,
	                10.0f));
			
			//TODO: Usar os parametros de observador para passar a matriz
			EstadoDeCelula mat[][] = regra.getMatriz();
			
			for (int i = 0; i < nLinhas; i++) 
			{	
				for(int j = 0; j < nLinhas; j++) 
				{		
					//Draw boats
					if(mat[i][j] != EstadoDeCelula.AGUA) 
					{					
						Rectangle r = new Rectangle();
						r.height = (int)alt - 15;
						r.width = (int)larg - 15;
						r.x = (int)(tab[i][j].x + espLinha/2 + 7);
						r.y = (int)(tab[i][j].y + espLinha/2 + 7);						
						
						if(mat[i][j] == EstadoDeCelula.OCUPADO)
							g2d.setColor(Color.black);
						else
							g2d.setColor(Color.gray);
						g2d.fill(r);
					}
				}
			}
		}
		
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == e.BUTTON1)//Left click
			{
				double x = e.getX(), y = e.getY();
				x -= xIni;
				y -= yIni;
				if(x > 0 && y > 0) 
				{
					int xCel = (int) (x/(larg + espLinha));
					int yCel = (int) (y/(alt + espLinha));
					
					regra.onLeftClick(xCel, yCel);		
				}
			}
			else if (e.getButton() == e.BUTTON3)//Right click
			{
				regra.onRightClick();	
			}
			
			//DEBUG : Usar o notify, nao deixar o repaint aqui	
			//repaint();
		}
		
		public void mouseEntered(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}

		@Override
		public void update() {
			repaint();	
		}
	}
