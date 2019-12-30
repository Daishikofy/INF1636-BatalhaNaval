package regras;

import java.io.*;
import java.util.*;

public class TabuleiroData {
	int armasDisponiveis;
	private char[][] grid;
	private Peca[][] pecas;
	private int xDim, yDim;
	private char defaultChar;
	
	public TabuleiroData(int x, int y)
	{	
		armasDisponiveis = 0;
		defaultChar = 'a';
		grid = new char[x][y];	
		pecas = new Peca[x][y];
		this.xDim = x;
		this.yDim = y;
		
		for(int i = 0; i < y; i++)
			for(int j = 0; j < x; j++)
			{
				grid[j][i] = defaultChar;
				pecas[i][j] = null;
			}
	}
	
	public TabuleiroData(Scanner sc) {
		sc.skip("TABULEIRODATA");
		xDim = sc.nextInt();
		yDim = sc.nextInt();
		sc.skip("\n");
		grid = new char[xDim][yDim];
		pecas = new Peca[xDim][yDim];
		Hashtable<Integer, Peca> pecasUnicas = new Hashtable<Integer, Peca>();
		armasDisponiveis = 0;
		while(sc.hasNext("PECA:")) {
			sc.skip("PECA:");
			int hash = sc.nextInt();
			sc.skip("\n");
			Peca p = new Peca(sc);
			pecasUnicas.put(hash, p);
			if(!p.estaAfundada()) {
				armasDisponiveis++;
			}
			sc.skip("\n");
		}
		for(int i = 0; i < xDim; i++) {
			for(int j = 0; j < yDim; j++) {
				int hash = sc.nextInt();
				System.out.println("["+i+"] ["+j+"] "+hash);
				if(hash != 0) {
					pecas[i][j] = pecasUnicas.get(hash);	
				}
			}
		}
		for(int i = 0; i < xDim; i++) {
			String linha = sc.next();
			for(int j = 0; j < yDim; j++) {
				grid[i][j] = linha.charAt(j);
			}
		}
	}
	
	public void escrever(FileWriter arquivo) {
		try {
			arquivo.write("TABULEIRODATA "+ xDim + " " + yDim + "\n");
			Hashtable<Integer, Peca> pecasUnicas = new Hashtable<Integer, Peca>();
			for(int i = 0; i < xDim; i ++) {
				for (int j = 0; j < yDim; j++) {
					Peca peca =  pecas[i][j];
					if(peca!=null) {
						pecasUnicas.put(peca.hashCode(), peca);	
					}
				}
			}
			for(Peca pecaUnica: pecasUnicas.values()) {
				arquivo.write("PECA: "+ pecaUnica.hashCode()+ "\n");
				pecaUnica.escrever(arquivo);
			}
			for(int i = 0; i < xDim; i ++) {
				for (int j = 0; j < yDim; j++) {
					Peca peca =  pecas[i][j];
					if(peca != null) {
						arquivo.write(peca.hashCode()+" ");
					} else {
						arquivo.write(0 + " ");
					}
				}
				arquivo.write("\n");
			}
			for(int i = 0; i < xDim; i ++) {
				for (int j = 0; j < yDim; j++) {
					arquivo.write(grid[i][j]);
				}
				arquivo.write("\n");
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean isEmpty(int x, int y)
	{	
		if (x < 0 || x >= xDim)
			return true;
		if (y < 0 || y >= yDim)
			return true;
		if (Character.compare(grid[x][y], defaultChar) == 0)
		{
			return true;
		}
		else
			return false;
		
	}
	
	public char getCell(int x, int y)
	{
		if (x < 0 || x >= xDim)
			return 'a';
		if (y < 0 || y >= yDim)
			return 'a';
		return grid[x][y];
	}
	
	public Peca getPeca(int x, int y)
	{
		if (x < 0 || x >= xDim)
			return null;
		if (y < 0 || y >= yDim)
			return null;
		return pecas[x][y];
	}
	
	public boolean setCell(char element, int x, int y)
	{
		if (x < 0 || x >= xDim)
			return false;
		if (y < 0 || y >= yDim)
			return false;
		grid[x][y] = element;
		return true;
	}
	
	public boolean setPeca(Peca peca, char element, int x, int y)
	{
		if (x < 0 || x >= xDim)
			return false;
		if (y < 0 || y >= yDim)
			return false;
		grid[x][y] = element;
		pecas[x][y] = peca;
		return true;		
	}
	
	public void inserePeca (Peca peca, int x, int y)
	{
		char[] components = peca.getComponentes();
		int currentComp = 0;	
		peca.x = x;
		peca.y = y;
		for (int i = x; i < x + peca.largura; i++)
			for (int j = y, c = 0; j < y + peca.altura; j++, c++)			
			{
				currentComp = (i - x) + c * peca.largura;
				setPeca(peca, components[currentComp], i, j);	
			}		
		armasDisponiveis ++;
	}
	
	public void removePeca (int x, int y)
	{
		Peca peca = getPeca(x, y);
		if (peca == null)
			return;
		int xPeca = peca.x, yPeca = peca.y;
		
		for (int i = xPeca; i < xPeca + peca.largura; i++)
			for (int j = yPeca; j < yPeca + peca.altura; j++)			
			{
				setCell('a', i, j);	
			}
		armasDisponiveis --;
	}
	
	public boolean estaAfundada(int x, int y) {
		Peca peca = getPeca(x, y);
		System.out.println("Coordenadas da peca: " + peca.x + ", " + peca.y);
		boolean todasX = true;
		
		for(int i = 0; i < peca.largura; i++) {
			for(int j = 0; j < peca.altura; j++) {
				x = peca.x + i;
				y = peca.y + j;
				char a = grid[i][j];
				System.out.println("X: " + x + " - Y: " + y + " - char: " + a);
				if( a != '0' && a != 'x' && a!= 'X' && a != 'A' && a != 'a' ) {
					todasX = false;
					break;
				}
			}
		}
		
		return todasX;
	}
	
	public void marcarComoAfundada(int x, int y) {
		Peca peca = getPeca(x, y);
		System.out.println("Coordenadas a afundar: " + peca.x + ", " + peca.y);
				
		for (int i = peca.x; i < peca.x + peca.largura; i++)
			for (int j = peca.y; j < peca.y + peca.altura; j++)			
			{
				char a = grid[i][j];
				if( a == 'x' ) {
					setCell('X', i, j);
				}			
			}
		armasDisponiveis --;
	}
	
	public void ocultar() {
		for(int i=0;i< xDim;i++) {
			for(int j = 0; j < yDim; j++) {
				char a = grid[i][j];
				if(a != 'x' && a != 'X' && a != '0') {
					grid[i][j] = Character.toUpperCase(a);
				}
			}
		}
	}
	
	public void mostrar() {
		for(int i=0;i< xDim;i++) {
			for(int j = 0; j < yDim; j++) {
				char a = grid[i][j];
				if(a != 'x' && a != 'X' && a != '0') {
					grid[i][j] = Character.toLowerCase(a);
				}
			}
		}
	}
	
	public boolean temArmasDisponiveis() {
		System.out.print("Armas disponiveis: " + armasDisponiveis);
		return armasDisponiveis > 0;
	}
	
	public void copiar(TabuleiroData fonte) {
		for(int x = 0; x < xDim; x++) {
			for(int y = 0; y < yDim; y++) {
				grid[x][y] = fonte.grid[x][y];
			}
		}
	}
	
	public boolean temVizinhos(int x, int y) {
		for(int dx =-1; dx <=1; dx++ ) {
			for(int dy = -1; dy<=1; dy++) {
				if(!isEmpty(x+dx, y+dy)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void insereInvalida(Peca peca) {
		System.out.println("Peça: ");
		char[] a = peca.getComponentes();
		for(char l: a) {
			System.out.print(l+ " ");
		}
		for(int i = 0; i < peca.altura; i++) {
			for(int j = 0; j < peca.largura; j++) {
				System.out.println("IDX: "+(i*peca.largura+j));
				if(peca.getComponentes()[i*peca.largura+j] != 'a') {
					int x, y;
					x = peca.x + j;
					y = peca.y + i;
					setCell('i', x, y);
				}
			}
		}
	}
	
}
