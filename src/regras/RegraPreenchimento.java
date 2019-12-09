package regras;
import interfaces.*;

public class RegraPreenchimento  extends RegraGeral implements IObservado{


	TabuleiroData tabuleiroPrenchendo; //Contem as peças atualmente fixas no tabuleiro
	TabuleiroData tabuleiroDesenhado; //Contem o tabuleiro como ele deve ser desenhado
	Peca[] pecas = new Peca[15]; //Possui as peças a serem posicionadas
	
	Peca pecaSelecionada = null;
	
	public RegraPreenchimento() {
		this(false);
	}
	
	public RegraPreenchimento(boolean visible)
	{
		System.out.println("Regra preenchimento");
		tabuleiroPrenchendo = new TabuleiroData(15, 15);
		
		if (visible) {
			geraPecas();
			
			inserePeca(pecas[0], 0, 13);
			inserePeca(pecas[1], 0, 10);
			inserePeca(pecas[2], 5, 10);
			inserePeca(pecas[3], 0, 7);
			inserePeca(pecas[4], 4, 7);
			inserePeca(pecas[5], 8, 7);
			inserePeca(pecas[6], 0, 4);
			inserePeca(pecas[7], 2, 4);
			inserePeca(pecas[8], 4, 4);
			inserePeca(pecas[9], 6, 4);
			inserePeca(pecas[10], 0, 0);
			inserePeca(pecas[11], 4, 0);
			inserePeca(pecas[12], 8, 0);
			inserePeca(pecas[13], 12, 0);
			inserePeca(pecas[14], 12, 3);
		}
		
		tabuleiro = tabuleiroPrenchendo;
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
				tabuleiroPrenchendo.setPeca(peca, components[currentComp], i, j);	
			}
		
		notificar(this);
	}
	
	public void RemovePeca (int x, int y)
	{
		Peca peca = tabuleiroPrenchendo.getPeca(x, y);
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
				tabuleiroPrenchendo.setCell('0', i, j);	
			}
		
		notificar(this);
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
	
	public void onLeftClick(int x, int y)
	{
		printTabuleiro();
		//System.out.println("Peca selecionada: " + tabuleiroPrenchendo.getCell(x, y));
		if ( pecaSelecionada == null )
		{			
			pecaSelecionada = tabuleiroPrenchendo.getPeca(x, y);
			if (pecaSelecionada != null)
			{
				RemovePeca(x, y);
				System.out.println("Peca selecionada: " + pecaSelecionada.getNome());
				System.out.println("Largura: " + pecaSelecionada.largura + " - Altura: " + pecaSelecionada.altura);
			}
		}
		else
		{
			if (validaInsercao(pecaSelecionada, x, y))
			{
				inserePeca(pecaSelecionada, x, y);
				pecaSelecionada = null;
				//Tabuleiro sendo o da regra geral
				tabuleiro = tabuleiroPrenchendo;
			}
		}
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
