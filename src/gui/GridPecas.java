package gui;
import javax.swing.*;

import interfaces.IObservador;
import interfaces.Regra;
import regras.RegraJogo.EstadoDeCelula;
import utils.CoresJogo;
import regras.RegraJogo;
import regras.RegraPreenchimento;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GridPecas extends JPanel implements MouseListener, IObservador {

		double xIni=25.0, yIni=25.0, larg=30.0, alt=30.0;
		int nLinhas = 15;
		Celula tab[][]=new Celula[nLinhas][nLinhas];
		Regra regra = RegraJogo.Instance().getRegra();
		int idx = -1;
		
		public GridPecas(int idx) {			
			this.idx = idx;
			addMouseListener(this);
			regra.ouvirAlteracoes((IObservador)this);
			
			double x = xIni ,y = yIni;
			for(int i=0; i < nLinhas; i++) {
				x = xIni;
				for(int j=0; j < nLinhas; j++) {
					tab[i][j] = new Celula(x,y);
					x += larg;
				}
				y += alt;
			}
			int largura = (int) (2 * xIni + nLinhas * larg);
			int altura = (int) (2 * yIni + nLinhas * alt);
			this.setSize(largura, altura);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			
			g2d.setColor(Color.black);
			g2d.setStroke(new BasicStroke((float) 0.0,
	                BasicStroke.CAP_BUTT,
	                BasicStroke.JOIN_MITER,
	                10.0f));
			
			//TODO: Usar os parametros de observador para passar a matriz
			EstadoDeCelula mat[][] = regra.getTabuleiro(idx);
			
			for (int i = 0; i < nLinhas; i++) 
			{	
				for(int j = 0; j < nLinhas; j++) 
				{	
					if(mat[i][j] != EstadoDeCelula.AGUA)
					{
						Color color = CoresJogo.selectColor(mat[i][j]);
						g2d.setColor(color);
						
						Rectangle r = new Rectangle();
						
						r.height = (int)alt;
						r.width = (int)larg;
						r.x = (int)(tab[i][j].x);
						r.y = (int)(tab[i][j].y);						

						g2d.fill(r);
					}
				}
			}
			g2d.setColor(Color.gray);
		}
		
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1)//Left click
			{
				double x = e.getX(), y = e.getY();
				x -= xIni;
				y -= yIni;
				if(x > 0 && y > 0) 
				{
					int xCel = (int) (x/(larg));
					int yCel = (int) (y/(alt));
					
					((RegraPreenchimento)regra).onLeftClickTabuleiro(idx, xCel, yCel);		
				}
			}
			else if (e.getButton() == MouseEvent.BUTTON3)//Right click
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
