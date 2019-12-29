package regras;

import utils.Evento;
import utils.TransformacaoTabuleiro;

import java.io.*;

import interfaces.IObservador;
import interfaces.Regra;
import regras.RegraJogo.EstadoDeCelula;

public class RegraEmbate implements Regra {
	
	public Evento tabuleiroAlterado;
	public Evento updateUI;
	public Evento jogoFinalizado;
	
	TabuleiroData[] tabuleiros;
	
	int jogadasSobrando = 3;
	
	int vez = 0;
	int vencedor = -1;
	
	String[] jogadores;
	
	public RegraEmbate (TabuleiroData[] tabuleiros, String[] jogadores)
	{
		tabuleiroAlterado = new Evento();
		updateUI = new Evento();
		jogoFinalizado = new Evento();
		
		for(TabuleiroData tab: tabuleiros) {
			tab.ocultar();
		}
		this.tabuleiros = tabuleiros;
		this.jogadores = jogadores;
	}
			
	@Override
	public void onLeftClickTabuleiro(int idx, int x, int y) {
		if (idx == vez) return;
		// Jogador clicou no pr�prio tabuleiro
		
		char a = tabuleiros[1 - vez].getCell(x, y);
		
		if (a == '0' || a == 'x' || a == 'X') {
			return; // A celula ja foi clicada nesses casos
		} 
		
		if(a == 'A') // Agua sem arma
		{
			tabuleiros[1 - vez].setCell('0', x, y);
		} 
		else // Arma
		{
			Peca pecaAtingida = tabuleiros[1 - vez].getPeca(x, y);
			pecaAtingida.atingida();
			tabuleiros[1 - vez].setCell('x', x, y);
			
			// Notificar qual arma foi atingida
			if(pecaAtingida.estaAfundada()) 
			{
				System.out.println(pecaAtingida.getNome() + " (" + x + "," + y + ") afundou");
				tabuleiros[1 - vez].marcarComoAfundada(x, y);
				// Notificar que afundou
				if(!tabuleiros[1-vez].temArmasDisponiveis()) {
					vencedor = vez;
				}
			}
		}
		tabuleiroAlterado.notificar(this);
		
		if(!jogoAcabou()) {
			jogadasSobrando --;
			if (jogadasSobrando <= 0) {
				finalizarTurno();
			}	
		} else {
			jogoFinalizado.notificar(this);
		}
	}
	
	private void finalizarTurno()
	{
		System.out.println("--- Fim do turno ---");
		tabuleiros[vez].ocultar();
		vez = 1 - vez;
		jogadasSobrando = 3;
		updateUI.notificar(this);
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
		tabuleiros[vez].mostrar();
		tabuleiroAlterado.notificar(this);	
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
		System.out.println("E a vez de " + jogadores[vez]);
		return jogadores[vez];
	}

	@Override
	public Boolean podeFinalizar() {
		return jogadasSobrando == 3;
	}

	@Override
	public void ouvirAlteracoes(IObservador observador) {
		tabuleiroAlterado.cadastrar(observador);	
	}
	@Override
	public void ouvirAlteracoesUI(IObservador observador) {
		updateUI.cadastrar(observador);	
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

	@Override
	public void mouseMovimento(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	public String getVencedor() {
		return jogadores[vencedor];
	}

	@Override
	public boolean jogoAcabou() {
		return vencedor != -1;
	}

	public void escrever(FileWriter arquivo) {
		// TODO Auto-generated method stub
		try {
			arquivo.write(Integer.toString(vez)+"-"+Integer.toString(jogadasSobrando)+"\n");
			for(String jogador:jogadores) {
				arquivo.write(jogador + "\n");
			}
			for(TabuleiroData tab: tabuleiros) {
				tab.escrever(arquivo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
