package gui;

import javax.swing.*;
import javax.swing.Box.Filler;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import regras.*;
 
public class PanelTabuleiro extends JPanel implements MouseListener {
	double xIni=50.0,yIni=50.0,larg=50.0,alt=50.0,espLinha=3.0;
	int iClick,jClick;
	int nLinhas = 15;
	Celula tab[][]=new Celula[15][15];
	Line2D.Double ln[]=new Line2D.Double[32];
	CtrlRegras ctrl;
	
	public PanelTabuleiro(CtrlRegras c) {
		double x = xIni ,y = yIni;
		ctrl = c;
		
		//Linhas horizontais
		for(int i=0; i <= nLinhas; i++) {
			ln[i]=new Line2D.Double(alt
					, larg * (i + 1)
					, alt * nLinhas + alt
					, larg * (i + 1));
		}
		
		//Linhas verticais
		for(int i = nLinhas + 1 ; i < ln.length; i++) {
			int index = i  -  nLinhas;
			ln[i]=new Line2D.Double(larg * index
					, alt - espLinha/2
					, larg * index
					, alt * nLinhas + alt + espLinha);
		}
		
		addMouseListener(this);
		
		for(int i=0; i < 15; i++) {
			x = xIni;
			for(int j=0; j < 15; j++) {
				tab[i][j] = new Celula(x,y);
				x += larg;
			}
			y += alt;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int mat[][] = ctrl.getMatriz();
		int vez = ctrl.getVez();
		
		if(vez == -1)
			g2d.setColor(Color.black);
		else
			g2d.setColor(Color.gray);
		
		g2d.setStroke(new BasicStroke(5.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f));
		
		for (int i = 0; i < ln.length; i++)
			g2d.draw(ln[i]);
		
		for (int i = 0; i < 15; i++) 
		{	
			
			//Draw letters
			char[] let = {(char)(i + 'A')};				
			g2d.drawChars(let, 0, 1, (int)((i + 1) * larg + larg/2), (int)(alt/2));
			
			for(int j = 0; j < 15; j++) 
			{	
				//Draw numbers
				char[] num = {'0', '0'};
				if ((j + 1) > 9)
				{
					num[0] = '1';
					num[1] = (char)( (j - 9) + '0');	
				}
				else
				{
					num[0] = '0';
					num[1] = (char)( ( j + 1) + '0');
				}
				g2d.drawChars(num, 0, 2, (int)(larg/2), (int)((j + 1) * alt + alt/2));
	
				//Draw boats
				if(mat[i][j]!=0) 
				{					
					Rectangle r = new Rectangle();
					r.height = (int)alt - 15;
					r.width = (int)larg - 15;
					r.x = (int)(tab[i][j].x + espLinha/2 + 7);
					r.y = (int)(tab[i][j].y + espLinha/2 + 7);						
					
					if(mat[i][j] == -1)
						g2d.setColor(Color.black);
					else
						g2d.setColor(Color.gray);
					g2d.fill(r);
				}
			}
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		int x = e.getX(), y = e.getY();
		x -= larg;
		y -= alt;
		int xCel = (int) (x/larg);
		int yCel = (int) (y/alt);
		if(ctrl.onClick(xCel, yCel))
			repaint();
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
