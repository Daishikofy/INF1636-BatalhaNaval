package regras;

import interfaces.IObservador;
import interfaces.Regra;
import regras.RegraJogo.EstadoDeCelula;
import utils.Evento;
import utils.TransformacaoTabuleiro;

public class RegraPreenchimento implements Regra {

	public Evento tabuleiroAlterado;
	public Evento updateUI;

	TabuleiroData tabuleiroPrenchendo; //Contem as peças atualmente fixas no tabuleiro
	TabuleiroData tabuleiroDesenhado; //Contem o tabuleiro como ele deve ser desenhado
	TabuleiroData tabuleiro = new TabuleiroData(15,15);
	
	TabuleiroData pecasPreenchidas;
	TabuleiroData pecas = new TabuleiroData (15,15);
	
	Peca[] allPecas = new Peca[15]; //Possui as peças a serem posicionadas
	
	Peca pecaSelecionada = null;
	int pecasPosicionadas = 0;	
	int pecaPosx = -1, pecaPosy = -1;
	
	String vez = "";
	Boolean finalizar = false;
	
	public RegraPreenchimento(String jogador) {
		tabuleiroAlterado = new Evento();
		updateUI = new Evento();
		/*
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				tabuleiro.setCell('0', i, j);
			}
		}*/
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
		
		tabuleiro.copiar(tabuleiroPrenchendo);
		pecas = pecasPreenchidas;		
	}
	

	public TabuleiroData finalizaPosicionamento() {
		return tabuleiro;
	}
	
	public boolean validaInsercao(Peca peca, int x, int y)
	{
		if (peca.largura + x > 15 || peca.altura + y > 15) {
			return false;
		}
		char[] conteudo = peca.getComponentes();
		
		for(int i = 0; i < peca.altura; i++) {
			for(int j = 0; j < peca.largura; j++) {
				if(conteudo[i*peca.largura+j] != 'a') {
					if(tabuleiroPrenchendo.temVizinhos(x+j, y+i)) {
						return false;
					}
				}
			}
		}
		return true;
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
				pecaPosx = x;
				pecaPosy = y;
				tabuleiroPrenchendo.removePeca(x, y);
				pecasPosicionadas -= 1;
				//System.out.println("Peca selecionada: " + pecaSelecionada.getNome());
				//System.out.println("Largura: " + pecaSelecionada.largura + " - Altura: " + pecaSelecionada.altura);
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
		tabuleiro.copiar(tabuleiroPrenchendo);
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
		tabuleiro.copiar(tabuleiroDesenhado);
		tabuleiroAlterado.notificar(this);
	}
	
	private void printTabuleiro ()
	{
		for (int i = 0; i < 15; i++)
		{
			for (int j = 0; j < 15; j++)
			{
				char cell = tabuleiro.getCell(j, i);
				if (cell != '0')
					System.out.print(cell + " ");
				else
					System.out.print(". ");
			}
			System.out.println("");
		}
	}
	
	
	
	public String getVez() {
		return vez;
	}	
	
	public Boolean podeFinalizar()
	{		
		return finalizar;
	}

	@Override
	public void ouvirAlteracoes(IObservador observador) {
		tabuleiroAlterado.cadastrar(observador);	
	}
	public void ouvirAlteracoesUI(IObservador observador) {
		updateUI.cadastrar(observador);	
	}

	@Override
	public EstadoDeCelula[][] getTabuleiro(int idx) {
		if (idx != -1) {
			return null;
		}	
		return getTabuleiro();
	}
	
	@Override
	public EstadoDeCelula[][] getTabuleiro() {
		return TransformacaoTabuleiro.getMatriz(tabuleiro);
	}

	@Override
	public EstadoDeCelula[][] getPecas() {
		return TransformacaoTabuleiro.getMatriz(pecas);
	}

	@Override
	public void onLeftClickTabuleiro(int idx, int x, int y) {
		if(idx == -1) {
			onLeftClickTabuleiro(x, y);
		}
	}
	
	@Override
	public void onEscPressed() {
		System.out.println("Volte a peça para sua posicao original");
		if (pecaSelecionada == null) return;
		
		if (pecaPosx == -1)
			//TODO: Botar de volta nas pecas
			return;
		tabuleiroPrenchendo.inserePeca(pecaSelecionada, pecaPosx, pecaPosy);
		pecaSelecionada = null;
		pecasPosicionadas += 1;
		
		pecaPosx = -1;
		pecaPosy = -1;
		
		tabuleiro.copiar(tabuleiroPrenchendo);
		tabuleiroAlterado.notificar(this);
	}
}
