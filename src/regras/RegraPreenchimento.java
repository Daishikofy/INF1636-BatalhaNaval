package regras;

public class RegraPreenchimento  extends RegraGeral {
	
	TabuleiroData tabuleiroPrenchendo; //Contem as peças atualmente fixas no tabuleiro
	TabuleiroData tabuleiroDesenhado; //Contem o tabuleiro como ele deve ser desenhado
	Peca[] pecas = new Peca[15]; //Possui as peças a serem posicionadas
	
	public RegraPreenchimento()
	{
		geraPecas();
	}
	
	public void transferir(RegraEmbate r) {
		
	}
	
	public boolean validaInsercao(Peca peca, int x, int y)
	{
		int xIni, xFim, yIni, yFim;
		xIni = x - 1;
		xFim = x + peca.largura + 1;
		yIni = y - 1;
		yFim = y + peca.altura + 1;
		if (peca.getNome() != "submarinos")
		{			
			for (int i = xIni; i <= xFim; i++)
			{
				for (int j = yIni; j <= yFim; j++)
				{
					if(!tabuleiroPrenchendo.isEmpty(i, j))
						return false;
				}
			}
			return true;
		}
		else
		{		
			int xIgnore = -1, yIgnore = -1;
			
			if(peca.largura > peca.altura)
			{
				if (peca.getComponentes()[0] == '0')	// 0 X 0
					yIgnore = yIni;						// X 0 X
				else
					yIgnore = yFim;     				// X 0 X
			}											// 0 X 0
			else
			{
				if (peca.getComponentes()[0] == '0')	// 0 X 
					xIgnore = xIni;						// X 0
														// 0 X
				else	
					xIgnore = xFim;    					// X 0 
														// 0 X 
			}											// X 0
			
			if (xIgnore > 0)
				for (int i = xIni; i <= xFim; i++)
				{
					for (int j = yIni; j <= yFim; j++)
					{
						if (i != xIgnore || (j != yFim && j != yIni))
							if(!tabuleiroPrenchendo.isEmpty(i, j))
								return false;
					}
				}
			
			else if (yIgnore > 0)
				for (int i = xIni; i <= xFim; i++)
				{
					for (int j = yIni; j <= yFim; j++)
					{
						if (j != yIgnore || (i != xFim && i != xIni))
						if(!tabuleiroPrenchendo.isEmpty(i, j))
							return false;
					}
				}
				
			return true;
		}
	}
	
	public void inserePeca (Peca peca, int x, int y)
	{
		char[] components = peca.getComponentes();
		int currentComp = 0;
		for (int i = x, cX = 0; i < x + peca.largura; i++)
			for (int j = y, cY = 0; j < y + peca.altura; j++)			
			{
				currentComp = x + j * peca.largura;
				tabuleiroPrenchendo.setCell(components[currentComp], i, j);				
			}
		
	}
	
	private void geraPecas()
	{
		int i = 0;
		pecas[i] = Peca.cria("couracado");
		for (i = 1; i < 3; i++)
			pecas[i] = Peca.cria("cruzadores");
		for (; i < 6; i++)
			pecas[i] = Peca.cria("destroyers");
		for (; i < 10; i++)
			pecas[i] = Peca.cria("submarinos");
		for (; i < 15; i++)
			pecas[i] = Peca.cria("hidravioes");
	}
}
