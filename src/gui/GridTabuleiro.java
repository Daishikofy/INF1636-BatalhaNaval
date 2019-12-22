package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import regras.*;
import regras.RegraJogo.EstadoDeCelula;

import interfaces.*;
 
public class GridTabuleiro extends JPanel implements MouseListener, IObservador {
	double xIni=25.0, yIni=25.0, larg=30.0, alt=30.0, espLinha=2.0;
	int nLinhas = 15;
	Celula tab[][]=new Celula[nLinhas][nLinhas];
	Line2D.Double ln[]=new Line2D.Double[(nLinhas + 1) * 2];
	RegraPreenchimento regra = GerenciadorDePreenchimento.getManager().getRegra();
	
	public GridTabuleiro() {		
		// Linhas horizontais
		for(int i=0; i <= nLinhas + 1; i++) 
		{
			ln[i]=new Line2D.Double(xIni
					, yIni + (alt + espLinha) * i
					, xIni + (larg + espLinha) * nLinhas
					, yIni + (alt + espLinha) * i );
		}
		
		// Linhas verticais
		for(int i = nLinhas + 1 ; i < ln.length; i++) 
		{
			int index = i  -  nLinhas - 1;
			ln[i]=new Line2D.Double(xIni + (larg + espLinha) * index
					, yIni
					, xIni + (larg + espLinha) * index
					, yIni + (alt+ espLinha) * nLinhas);
		}
		
		addMouseListener(this);
		regra.tabuleiroAlterado.cadastrar((IObservador)this);
		
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
		
		for (int i = 0; i < ln.length; i++)
			g2d.draw(ln[i]);
		
		// Draw letters
		for(int i = 0; i < nLinhas; i ++) {
			char[] let = {(char)(i + 'A')};				
			g2d.drawChars(let, 0, 1, (int)(xIni / 2), (int)(yIni + (alt + espLinha)*(i + 0.5)));
		}
		
		// Draw numbers
		for(int i = 0; i < nLinhas; i ++) {
			char[] num = {'0', '0'};
			if ((i + 1) > 9)
			{
				num[0] = '1';
				num[1] = (char)( (i - 9) + '0');	
			}
			else
			{
				num[0] = '0';
				num[1] = (char)( ( i + 1) + '0');
			}
			g2d.drawChars(num, 0, 2, (int)( xIni + (larg + espLinha)*(i + 0.25) ), (int)(yIni / 2));
		}
//TODO: Usar os parametros de observador para passar a matriz
		EstadoDeCelula mat[][] = regra.getTabuleiro();
		
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
				
				GerenciadorDePreenchimento.getManager().onLeftClickTabuleiro(xCel, yCel);		
			}
		}
		else if (e.getButton() == e.BUTTON3)//Right click
		{
			GerenciadorDePreenchimento.getManager().onRightClick();	
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
