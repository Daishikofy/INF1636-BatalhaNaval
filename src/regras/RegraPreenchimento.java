package regras;

public class RegraPreenchimento  extends RegraGeral {


	TabuleiroData tabuleiroPrenchendo; //Contem as peças atualmente fixas no tabuleiro
	TabuleiroData tabuleiroDesenhado; //Contem o tabuleiro como ele deve ser desenhado
	TabuleiroData pecasPreenchidas;
	Peca[] allPecas = new Peca[15]; //Possui as peças a serem posicionadas
	
	Peca pecaSelecionada = null;
	int pecasPosicionadas = 0;
	
	public RegraPreenchimento(String jogador) {
		System.out.println("Regra preenchimento " + jogador);
		vez = jogador;
		tabuleiroPrenchendo = new TabuleiroData(15, 15);
		tabuleiroDesenhado = new TabuleiroData(15, 15);
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
	}
	

	public TabuleiroData finalizaPosicionamento() {
		return tabuleiro;
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
		System.out.println("ClickTabuleiro preenchimento");
		printTabuleiro();
		//System.out.println("Peca selecionada: " + tabuleiroPrenchendo.getCell(x, y));
		if ( pecaSelecionada == null )
		{			
			pecaSelecionada = tabuleiroPrenchendo.getPeca(x, y);
			if (pecaSelecionada != null)
			{
				tabuleiroPrenchendo.removePeca(x, y);
				pecasPosicionadas -= 1;
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
				pecasPosicionadas += 1;
				//Tabuleiro sendo o da regra geral				
			}
		}
		tabuleiro = tabuleiroPrenchendo;
		tabuleiroAlterado.notificar(this);
		//TODO : Botar 15 em vez de 3
		if (finalizar && pecasPosicionadas < 3)
		{
			finalizar = false;
			updateUI.notificar(this);
		}
		if(!finalizar && pecasPosicionadas == 3)
		{
			finalizar = true;
			updateUI.notificar(this);
		}
			
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
			pecas = pecasPreenchidas;
			tabuleiroAlterado.notificar(this);
		}	
	}
	
	public void onRightClick() 
	{
		if (pecaSelecionada != null) {
			pecaSelecionada.girar90Graus();
			atualizarDesenho();
		}
	}
	
	@Override
	public void mouseMovimento(int x, int y) {
		if(pecaSelecionada != null) {
			pecaSelecionada.x = x;
			pecaSelecionada.y = y;
			atualizarDesenho();
		}
	}
	
	public void atualizarDesenho() {
		tabuleiroDesenhado.copiar(tabuleiroPrenchendo);
		if(validaInsercao(pecaSelecionada, pecaSelecionada.x, pecaSelecionada.y)) {
			tabuleiroDesenhado.inserePeca(pecaSelecionada, pecaSelecionada.x, pecaSelecionada.y);
		} else {
			// Tratar casos inválidos
			tabuleiroDesenhado.insereInvalida(pecaSelecionada);
		}
		tabuleiro = tabuleiroDesenhado;
		tabuleiroAlterado.notificar(this);
		updateUI.notificar(this);
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
