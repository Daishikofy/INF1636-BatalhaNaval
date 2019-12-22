package regras;

import utils.Evento;

public class RegraEmbate extends RegraGeral {
	
	public Evento tabuleiroAlterado;
	TabuleiroData[] tabuleiros;
	
	public RegraEmbate (TabuleiroData[] tabuleiros)
	{
		tabuleiroAlterado = new Evento();
		this.tabuleiros = tabuleiros;
	}
	
	/*
	 * Marca a casa atingida
	 */
	public void onClick(int x, int y)
	{
		
	}
	/*
	 * Mostra o tabuleiro do jogador corrente
	 */
	public void mostrarTabuleiro()
	{
		
	}
	
	/*
	 * A cada jogada, verifica se o adversario perdeu
	 */
	public void verificarJogo()
	{
		
	}
}
