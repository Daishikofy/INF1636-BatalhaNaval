package regras;

import utils.Evento;
import utils.TransformacaoTabuleiro;

import java.io.*;
import java.util.Scanner;

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
	String report;
	
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
	
	public RegraEmbate(FileReader fonte) {
		tabuleiroAlterado = new Evento();
		updateUI = new Evento();
		jogoFinalizado = new Evento();
		
		Scanner sc = new Scanner(fonte);
		
		vez = sc.nextInt();
		jogadasSobrando = sc.nextInt();
		sc.skip("\n");
		
		int numJogadores = 2;
		jogadores = new String[numJogadores];
		for(int i = 0; i < numJogadores; i++) {
			jogadores[i] = sc.nextLine();	
		}
		
		tabuleiros = new TabuleiroData[numJogadores];
		for(int i = 0; i < numJogadores; i++) {
			tabuleiros[i] = new TabuleiroData(sc);
			sc.skip("\n");
		}
		sc.close();
	}
	
	public void escrever(FileWriter arquivo) {
		// TODO Auto-generated method stub
		try {
			arquivo.write(Integer.toString(vez)+" "+Integer.toString(jogadasSobrando)+"\n");
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
			
	@Override
	public void onLeftClickTabuleiro(int idx, int x, int y) {
		if (idx == vez) return;
		// Jogador clicou no próprio tabuleiro
		
		char a = tabuleiros[1 - vez].getCell(x, y);
		
		if (a == '0' || a == 'x' || a == 'X') {
			return; // A celula ja foi clicada nesses casos
		} 
		
		if(a == 'A') // Agua sem arma
		{
			tabuleiros[1 - vez].setCell('0', x, y);
			report = "Atingiu a água.";
		} 
		else // Arma
		{
			Peca pecaAtingida = tabuleiros[1 - vez].getPeca(x, y);
			pecaAtingida.atingida();
			tabuleiros[1 - vez].setCell('x', x, y);
			
			report = "Atingiu uma arma. ";
			
			// Notificar qual arma foi atingida
			if(pecaAtingida.estaAfundada()) 
			{
				tabuleiros[1 - vez].marcarComoAfundada(x, y);
				report += "E a afundou!";
				// Notificar que afundou
				if(!tabuleiros[1-vez].temArmasDisponiveis()) {
					vencedor = vez;
				}
			}
		}
		tabuleiroAlterado.notificar(this);
		updateUI.notificar(this);
		
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
		tabuleiros[vez].ocultar();
		vez = 1 - vez;
		jogadasSobrando = 3;
		updateUI.notificar(this);
	}

	@Override
	public void onRightClick() {
		// TODO Auto-generated method stub
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
	public String getVez() {
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
	public void mouseMovimento(int x, int y) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onEscPressed() {
	}
	
	public String getVencedor() {
		return jogadores[vencedor];
	}

	public String getReport() {
		return report;
	}
	
	@Override
	public boolean jogoAcabou() {
		return vencedor != -1;
	}
}
