package regras;

import utils.Evento;
import utils.TransformacaoTabuleiro;
import interfaces.IObservador;
import interfaces.Regra;
import regras.RegraJogo.EstadoDeCelula;

public class RegraEmbate implements Regra {
	
	public Evento tabuleiroAlterado;
	TabuleiroData[] tabuleiros;
	
	public RegraEmbate (TabuleiroData[] tabuleiros)
	{
		tabuleiroAlterado = new Evento();
		for(TabuleiroData tab: tabuleiros) {
			tab.ocultar();
		}
		this.tabuleiros = tabuleiros;
	}
	
	/*
	 * Marca a casa atingida
	 */	
	@Override
	public void onLeftClickTabuleiro(int x, int y) {
		// TODO Auto-generated method stub
		System.out.println("Click embate");
	}

	@Override
	public void onRightClick() {
		// TODO Auto-generated method stub
		System.out.println("Click embate");
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

	@Override
	public EstadoDeCelula[][] getTabuleiro(int idx) {
		// TODO Auto-generated method stub
		return TransformacaoTabuleiro.getMatriz(tabuleiros[idx]);
	}

	@Override
	public EstadoDeCelula[][] getPecas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVez() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean podeFinalizar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ouvirAlteracoes(IObservador observador) {
		tabuleiroAlterado.cadastrar(observador);		
	}

	@Override
	public EstadoDeCelula[][] getTabuleiro() {
		// TODO Auto-generated method stub
		return null;
	}
}
