package regras;

import interfaces.IObservador;
import interfaces.Regra;
import regras.RegraJogo.EstadoDeCelula;
import utils.Evento;
import utils.TransformacaoTabuleiro;

public class RegraGeral implements Regra{	
	public Evento tabuleiroAlterado;
	public Evento updateUI;
	
	protected TabuleiroData tabuleiro = new TabuleiroData(15,15);
	protected TabuleiroData pecas = new TabuleiroData (15,15);
	protected String vez = "";
	protected Boolean finalizar = false;
	
	public RegraGeral() {
		tabuleiroAlterado = new Evento();
		updateUI = new Evento();
		System.out.println("Regra geral");
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				tabuleiro.setCell('0', i, j);
			}
		}
	}
	
	@Override
	public EstadoDeCelula[][] getTabuleiro() {
		return TransformacaoTabuleiro.getMatriz(tabuleiro);
	}

	@Override
	public EstadoDeCelula[][] getPecas() {
		return TransformacaoTabuleiro.getMatriz(pecas);
	}
	
	public String getVez() {
		return vez;
	}	
	
	public Boolean podeFinalizar()
	{		
		return finalizar;
	}
	
		
	public void onLeftClickTabuleiro(int x, int y) {}
	public void onRightClick() {}

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
	public void onLeftClickTabuleiro(int idx, int x, int y) {
		if(idx == -1) {
			onLeftClickTabuleiro(x, y);
		}
	}

	@Override
	public void mouseMovimento(int x, int y) {
		// Empty.
	}

	@Override
	public boolean jogoAcabou() {
		return false;
	}
}