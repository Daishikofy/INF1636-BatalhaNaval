package utils;

import regras.RegraJogo.EstadoDeCelula;
import regras.TabuleiroData;

public class TransformacaoTabuleiro {	
	
	public static EstadoDeCelula[][] getMatriz(TabuleiroData matriz) {
		EstadoDeCelula[][] matrizDeCelulas = new EstadoDeCelula[15][15];
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				EstadoDeCelula estado;
				
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
				else if(conteudo == 'h') {
					estado = EstadoDeCelula.HIDROAVIAO;
				}
				else if(conteudo == 's') {
					estado = EstadoDeCelula.SUBMARINO;
				}
				else if(conteudo == 'd') {
					estado = EstadoDeCelula.DESTROYER;
				}
				else if(conteudo == 'r') {
					estado = EstadoDeCelula.COURACADO;
				}
				else if(conteudo == 'c') {
					estado = EstadoDeCelula.CRUZADOR;
				}
				else {
					estado = EstadoDeCelula.ATINGIDO;
				}
				
				matrizDeCelulas[i][j] = estado;
			}
		}
		return matrizDeCelulas;
	}
}
