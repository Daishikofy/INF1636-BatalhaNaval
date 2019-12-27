package regras;

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
		//A peca selecionada tem coordenadas zeradas, nao sei se daria 
		//para se aproveitar dessas coordenadas em vez de deixar elas zeradas.
		//Talvez possam ser uteis na hora de fazer o desenho ou de arastar a 
		//peca pelo tabuleiro
		peca.x = 0;
		peca.y = 0;

		for (int i = xPeca; i < xPeca + peca.largura; i++)
			for (int j = yPeca; j < yPeca + peca.altura; j++)			
			{
				setCell('a', i, j);	
			}
		armasDisponiveis --;
	}
	
	public boolean estaAfundada(int x, int y) {
		Peca peca = getPeca(x, y);
		
		boolean todasX = true;
		
		for(int i = 0; i < peca.largura; i++) {
			for(int j = 0; j < peca.altura; j++) {
				x = peca.x + i;
				y = peca.y + j;
				char a = grid[i][j];
				System.out.println(x);
				System.out.println(y);
				System.out.println(a);
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
		
		for(int i = 0; i < peca.largura; i++) {
			for(int j = 0; j < peca.altura; j++) {
				x = peca.x + i;
				y = peca.y + j;
				char a = grid[i][j];
				if( a == 'x' ) {
					grid[i][j] = 'X';
				}
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
		System.out.print("Armas disponiveis: ");
		System.out.println( armasDisponiveis);
		return armasDisponiveis > 0;
	}
}
