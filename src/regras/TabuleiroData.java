package regras;

public class TabuleiroData {
	private char[][] grid;
	private int xDim, yDim;
	
	public TabuleiroData(int xDim, int yDim)
	{
		grid = new char[xDim][yDim];
	}
	
	public Boolean isEmpty(int x, int y)
	{
		if (x < 0 || x >= xDim)
			return true;
		if (y < 0 || y >= yDim)
			return true;
		if (grid[x][y] != '0')
			return true;
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
	{
		if (x < 0 || x >= xDim)
			return false;
		if (y < 0 || y >= yDim)
			return false;
		grid[x][y] = element;
		return true;
	}
}
