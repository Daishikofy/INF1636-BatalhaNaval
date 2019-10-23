package regras;

public class RegraGeral {

	public enum EstadoDeCelula {
		AGUA,				// N�o cont�m arma
		OCUPADO,			// Cont�m arma intacta
		ATINGIDO,			// Cont�m parte de arma atingida
		AFUNDADO,			// Toda a arma foi atingida
		INVALIDO,			// Posicionamento de arma encosta em outra arma
		OCULTO				// Seu conte�do n�o pode ser visto atualmente
	}
	
	int tabuleiro [][] = new int[15][15];
	int vez=5;
	
	public RegraGeral() {
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