package utils;

import java.awt.Color;

import regras.RegraJogo.EstadoDeCelula;

public class CoresJogo {
	
	public static Color branco = new Color(240,240,240);
	public static Color	amarelo = new Color(252, 186, 3);	
	public static Color	rosa = new Color(237, 19, 117);
	public static Color	vermelho = new Color(222, 13, 52);
	public static Color	laranja = new Color(255, 111, 8);
	public static Color	azul = new Color(38, 99, 191);
	public static Color	azulClaro = new Color(148, 218, 255);
	public static Color	verde  = new Color(127, 227, 34);
	public static Color	verdeEscuro = new Color(70, 156, 76);
	public static Color	cinza = new Color(150,150,150);
	public static Color	preto = new Color(70,70,70);
	
	public static Color selectColor(EstadoDeCelula estado)
	{
		if(estado == EstadoDeCelula.OCULTO) {
			return azulClaro;
		}	
		else if(estado == EstadoDeCelula.AGUA) {
			return branco;
		}
		else if(estado == EstadoDeCelula.HIDROAVIAO) {
			return verdeEscuro;
		}
		else if(estado == EstadoDeCelula.SUBMARINO) {
			return verde;
		}
		else if(estado == EstadoDeCelula.CRUZADOR) {
			return rosa;
		}
		else if(estado == EstadoDeCelula.COURACADO) {
			return laranja;
		}
		else if(estado == EstadoDeCelula.DESTROYER) {
			return amarelo;
		}
		else if(estado == EstadoDeCelula.ATINGIDO) {
			return cinza;
		}
		else if(estado == EstadoDeCelula.AFUNDADO) {
			return preto;
		}
		else if(estado == EstadoDeCelula.INVALIDO) {
			return vermelho;
		}
		return branco;
	}
}
