package regras;

public class CtrlRegras {
	int tabuleiro [][] = new int[15][15];
	int vez=5;
	
	public CtrlRegras() {
	}
	
	public int[][] getMatriz() {
		return tabuleiro;
	}
	
	public int getVez() {
		return vez;
	}	
	
	public boolean onClick(int x, int y)
	{
		if (x < 0 || x > 14 || y < 0 || y > 14)
			return false;
		if (tabuleiro[y][x] != 0)
			return false;
		
		tabuleiro[y][x] = vez;
		if(vez == -1)
			vez = 5;
		else
			vez = -1;
		return true;		
	}
}