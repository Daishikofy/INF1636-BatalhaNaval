package regras;

import utils.Evento;
import utils.TransformacaoTabuleiro;
import interfaces.IObservador;
import interfaces.Regra;
import regras.RegraJogo.EstadoDeCelula;

public class RegraEmbate implements Regra {
	
	public Evento tabuleiroAlterado;
	TabuleiroData[] tabuleiros;
	int vez = 0;
	
	public RegraEmbate (TabuleiroData[] tabuleiros)
	{
		tabuleiroAlterado = new Evento();
		for(TabuleiroData tab: tabuleiros) {
			tab.ocultar();
		}
		this.tabuleiros = tabuleiros;
	}
	
	public void comecar() {
		tabuleiros[vez].mostrar();
		tabuleiroAlterado.notificar(this);
	}
	
	
	
	@Override
	public void onLeftClickTabuleiro(int idx, int x, int y) {
		if(idx != vez) { // Jogador clicou num 
			System.out.println("Click embate");
			char a = tabuleiros[1 - vez].getCell(x, y);
			if (a == '0' || a == 'x' || a == 'X') {
				return; // A celula ja foi clicada nesses casos
			} else if(a == 'A') {
				tabuleiros[1 - vez].setCell('0', x, y);
			} else {
				tabuleiros[1 - vez].setCell('x', x, y);
				// Notificar qual arma foi atingida
				if(tabuleiros[1 - vez].estaAfundada(x, y)) {
					System.out.println("AFUNDOU");
					tabuleiros[1 - vez].marcarComoAfundada(x, y);
					// Notificar que afundou
					if(!tabuleiros[1-vez].temArmasDisponiveis()) {
						// Notificar que acabou
						System.out.println("ACABOU");
					}
				}
			}
			tabuleiros[vez].ocultar();
			vez = 1 - vez;
			tabuleiroAlterado.notificar(this);
		}
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

	@Override
	public void onLeftClickTabuleiro(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
