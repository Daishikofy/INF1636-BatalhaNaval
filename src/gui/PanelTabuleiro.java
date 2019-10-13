package gui;

import javax.swing.*;
import javax.swing.Box.Filler;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import regras.*;
 
public class PanelTabuleiro extends JPanel implements MouseListener {
	double xIni=75.0,yIni=75.0,larg=80.0,alt=80.0,espLinha=5.0;
	int iClick,jClick;
	Celula tab[][]=new Celula[3][3];
	Line2D.Double ln[]=new Line2D.Double[4];
	CtrlRegras ctrl;
	
	public PanelTabuleiro(CtrlRegras c) {
		double x=xIni,y=yIni;
		ctrl=c;
		
		for(int i=0;i<3;i++) {
			x=xIni;
			for(int j=0;j<3;j++) {
				tab[i][j]=new Celula(x,y);
				x+=larg+espLinha;
			}
			y+=alt+espLinha;
		}
		
		addMouseListener(this);
		
		ln[0]=new Line2D.Double(155.0,75.0,155.0,325.0);
		ln[1]=new Line2D.Double(240.0,75.0,240.0,325.0);
		ln[2]=new Line2D.Double(75.0,155.0,325.0,155.0);
		ln[3]=new Line2D.Double(75.0,240.0,325.0,240.0);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		int mat[][]=ctrl.getMatriz();
		int vez=ctrl.getVez();
		
		g2d.setStroke(new BasicStroke(5.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f));
		for(int i = 0; i<ln.length; i++)
			g2d.draw(ln[i]);
		
		for(int i=0;i<3;i++) {			
			for(int j=0;j<3;j++) {	
				if(mat[i][j]!=0) {					
					Rectangle r = new Rectangle();
					r.height = (int)alt - 15;
					r.width = (int)larg - 15;
					r.x = ((int)tab[i][j].x + 5);
					r.y = (int)tab[i][j].y + 5;						
					
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
		int x=e.getX(),y=e.getY();
		x-=75;
		y-=75;
		int xCel = (int) (x/(larg + espLinha));
		int yCel = (int) (y/(alt + espLinha));
		if(ctrl.onClick(xCel, yCel))
			repaint();
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
