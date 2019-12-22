package regras;
import java.util.ArrayList;
import java.util.List;

import interfaces.*;
import utils.Evento;

public class RegraGeral{

	public enum EstadoDeCelula {
		AGUA,				// Não contém arma '0'
		OCUPADO,			// Contém arma intacta 'o' (ou uma letra pra cada arma)
		ATINGIDO,			// Contém parte de arma atingida 'x'
		AFUNDADO,			// Toda a arma foi atingida 'X'
		INVALIDO,			// Posicionamento de arma encosta em outra arma 'i'
		OCULTO				// Seu conteúdo não pode ser visto atualmente <upper case - {'X'}>
	}
	
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
	
	public EstadoDeCelula[][] getTabuleiro()
	{		
		return getMatriz(tabuleiro);
	}
	
	public EstadoDeCelula[][] getPecas()
	{		
		return getMatriz(pecas);
	}
		
	private EstadoDeCelula[][] getMatriz(TabuleiroData matriz) {
		EstadoDeCelula[][] matrizDeCelulas = new EstadoDeCelula[15][15];
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				EstadoDeCelula estado = EstadoDeCelula.OCUPADO;
				char conteudo = matriz.getCell(j, i);
				if(conteudo == 'X') {
					estado = EstadoDeCelula.AFUNDADO;
				} 
				else if(Character.isUpperCase(conteudo)) {
					estado = EstadoDeCelula.OCULTO;
				}
				else if(conteudo == 'i') {
					estado = EstadoDeCelula.INVALIDO;
				}
				else if(conteudo == '0') {
					estado = EstadoDeCelula.AGUA;
				}
				else if(conteudo == 'x') {
					estado = EstadoDeCelula.ATINGIDO;
				}
				
				matrizDeCelulas[i][j] = estado;
			}
		}
		return matrizDeCelulas;
	}
	
	public String getVez() {
		return vez;
	}	
	
	public Boolean podeFinalizar()
	{		
		return finalizar;
	}
	
//TODO		
	public void onLeftClickTabuleiro(int x, int y) {}
	public void onRightClick() {}
}