package regras;

import gui.DialogoJogadores;
import gui.FrameJogo;

public class RegraPreenchimento  extends RegraGeral {
	
	TabuleiroData tabuleiroPrenchendo; //Contem as peças atualmente fixas no tabuleiro
	TabuleiroData tabuleiroDesenhado; //Contem o tabuleiro como ele deve ser desenhado
	Peca[] pecas = new Peca[15]; //Possui as peças a serem posicionadas
	
	public RegraPreenchimento()
	{
		geraPecas();
		tabuleiroPrenchendo = new TabuleiroData(15,15);
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
		peca.x = x;
		peca.y = y;
		for (int i = x; i < x + peca.largura; i++)
			for (int j = y, c = 0; j < y + peca.altura; j++, c++)			
			{
				currentComp = (i - x) + c * peca.largura;
				tabuleiroPrenchendo.setPeca(peca, components[currentComp], i, j);	
			}
		
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
public static void main(String args[]) {
	RegraPreenchimento regra = new RegraPreenchimento();
	regra.inserePeca(regra.pecas[0], 5, 5);
	if (regra.validaInsercao(regra.pecas[0], 5, 5))
		regra.inserePeca(regra.pecas[0], 5, 5);
	//regra.RemovePeca(6, 5);
	regra.inserePeca(regra.pecas[9], 7, 3);
	Peca peca = regra.pecas[14];
	regra.inserePeca(peca, 8, 10);
	peca.girar90Graus();
	regra.inserePeca(peca, 3, 10);
	
	regra.printTabuleiro();
	
}
}
