package regras;

public class TabuleiroData {
	private char[][] grid;
	private int xDim, yDim;
	private char defaultChar;
	public TabuleiroData(int x, int y)
	{	
		defaultChar = '0';
		grid = new char[x][y];	
		this.xDim = x;
		this.yDim = y;
		
		for(int i = 0; i < y; i++)
			for(int j = 0; j < x; j++)
				grid[j][i] = defaultChar;
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
			return '0';
		if (y < 0 || y >= yDim)
			return '0';
		return grid[x][y];
	}
	
	public boolean setCell(char element, int x, int y)
	{/*
		if (x < 0 || x >= xDim)
			return false;
		if (y < 0 || y >= yDim)
			return false;*/
		grid[x][y] = element;
		return true;
	}
}
