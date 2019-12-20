package regras;
import interfaces.*;

public class RegraPreenchimento  extends RegraGeral implements IObservado{


	TabuleiroData tabuleiroPrenchendo; //Contem as pe�as atualmente fixas no tabuleiro
	TabuleiroData tabuleiroDesenhado; //Contem o tabuleiro como ele deve ser desenhado
	TabuleiroData pecasPreenchidas;
	Peca[] allPecas = new Peca[15]; //Possui as pe�as a serem posicionadas
	
	Peca pecaSelecionada = null;
	
	public RegraPreenchimento() {
		System.out.println("Regra preenchimento");
		tabuleiroPrenchendo = new TabuleiroData(15, 15);
		pecasPreenchidas = new TabuleiroData(15, 15);
		
		geraPecas();		
		
		pecasPreenchidas.inserePeca(allPecas[0], 0, 13);
		pecasPreenchidas.inserePeca(allPecas[1], 0, 10);
		pecasPreenchidas.inserePeca(allPecas[2], 5, 10);
		pecasPreenchidas.inserePeca(allPecas[3], 0, 7);
		pecasPreenchidas.inserePeca(allPecas[4], 4, 7);
		pecasPreenchidas.inserePeca(allPecas[5], 8, 7);
		pecasPreenchidas.inserePeca(allPecas[6], 0, 4);
		pecasPreenchidas.inserePeca(allPecas[7], 2, 4);
		pecasPreenchidas.inserePeca(allPecas[8], 4, 4);
		pecasPreenchidas.inserePeca(allPecas[9], 6, 4);
		pecasPreenchidas.inserePeca(allPecas[10], 0, 0);
		pecasPreenchidas.inserePeca(allPecas[11], 4, 0);
		pecasPreenchidas.inserePeca(allPecas[12], 8, 0);
		pecasPreenchidas.inserePeca(allPecas[13], 12, 0);
		pecasPreenchidas.inserePeca(allPecas[14], 12, 3);
		tabuleiro = tabuleiroPrenchendo;
		pecas = pecasPreenchidas;
		
		updateUI.notificar(this);
	}
	

	public void transferir(RegraPreenchimento r) {
		this.pecaSelecionada = r.pecaSelecionada;
		r.pecaSelecionada = null;
	}
	
	public boolean validaInsercao(Peca peca, int x, int y)
	{
		int xIni, xFim, yIni, yFim;
		xIni = x - 1;
		xFim = x + peca.largura + 1;
		yIni = y - 1;
		yFim = y + peca.altura + 1;
		if (peca.largura + x > 15 || peca.altura + y > 15)
			return false;
		
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
	
	private void geraPecas()
	{
		int i = 0;
		allPecas[i] = Peca.cria("couracado");
		for (i = 1; i < 3; i++)
			allPecas[i] = Peca.cria("cruzadores");
		for (; i < 6; i++)
			allPecas[i] = Peca.cria("destroyers");
		for (; i < 10; i++)
			allPecas[i] = Peca.cria("submarinos");
		for (; i < 15; i++)
			allPecas[i] = Peca.cria("hidravioes");
	}
	
	public void onLeftClickTabuleiro(int x, int y)
	{
		System.out.println("ClickTabuleiro");
		printTabuleiro();
		//System.out.println("Peca selecionada: " + tabuleiroPrenchendo.getCell(x, y));
		if ( pecaSelecionada == null )
		{			
			pecaSelecionada = tabuleiroPrenchendo.getPeca(x, y);
			if (pecaSelecionada != null)
			{
				tabuleiroPrenchendo.removePeca(x, y);
				System.out.println("Peca selecionada: " + pecaSelecionada.getNome());
				System.out.println("Largura: " + pecaSelecionada.largura + " - Altura: " + pecaSelecionada.altura);
			}
		}
		else
		{
			if (validaInsercao(pecaSelecionada, x, y))
			{
				tabuleiroPrenchendo.inserePeca(pecaSelecionada, x, y);
				pecaSelecionada = null;
				//Tabuleiro sendo o da regra geral
			}
		}
		tabuleiro = tabuleiroPrenchendo;
		tabuleiroAlterado.notificar(this);
	}

	public void onLeftClickPecas(int x, int y)
	{
		System.out.println("ClickPeca");
		if ( pecaSelecionada == null )
		{			
			pecaSelecionada = pecasPreenchidas.getPeca(x, y);
			if (pecaSelecionada != null)
			{
				pecasPreenchidas.removePeca(x, y);
				System.out.println("Peca selecionada: " + pecaSelecionada.getNome());
				System.out.println("Largura: " + pecaSelecionada.largura + " - Altura: " + pecaSelecionada.altura);
			}
		}
		else
		{
			if (validaInsercao(pecaSelecionada, x, y))
			{
				pecasPreenchidas.inserePeca(pecaSelecionada, x, y);
				pecaSelecionada = null;
				//pecas sendo as da regra geral		
			}
		}
		pecas = pecasPreenchidas;
		tabuleiroAlterado.notificar(this);
	}
	
	public void onRightClick() 
	{
		if (pecaSelecionada != null)
			pecaSelecionada.girar90Graus();			
	}
	
	private void printTabuleiro ()
	{
		for (int i = 0; i < 15; i++)
		{
			for (int j = 0; j < 15; j++)
			{
				char cell = tabuleiroPrenchendo.getCell(j, i);
				if (cell != '0')
					System.out.print(cell + " ");
				else
					System.out.print(". ");
			}
			System.out.println("");
		}
	}
}
