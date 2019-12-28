package regras;

public class Peca {
	
	private String nome;
	private char[] componentes;
	int x = -1, y = -1;
	int altura, largura;
	private int componentesIntactos;
	private Boolean afundou;
	
	public static Peca cria(String nome)
	{
		Peca peca = new Peca();
		if (nome == "hidravioes")
		{
			peca.nome = nome;
			char[] aux = {'a','h','a', 'h', 'a','h'};
			peca.componentes = aux;
			peca.largura = 3;
			peca.altura = 2;
			peca.componentesIntactos = 3;
		}
		else if (nome == "submarinos")
		{
			peca.nome = nome;
			char[] aux = {'s'};
			peca.componentes = aux;
			peca.largura = 1;
			peca.altura = 1;
			peca.componentesIntactos = 1;
		}
		else if (nome == "destroyers")
		{
			peca.nome = nome;
			char[] aux = {'d','d'};
			peca.componentes = aux;
			peca.largura = 2;
			peca.altura = 1;
			peca.componentesIntactos = 2;
		}
		else if (nome == "cruzadores")
		{	
			peca.nome = nome;
			char[] aux = {'c','c','c','c'};
			peca.componentes = aux;
			peca.largura = 4;
			peca.altura = 1;
			peca.componentesIntactos = 4;
		}
		else if (nome == "couracado")
		{
			peca.nome = nome;
			char[] aux = {'r','r','r','r','r'};
			peca.componentes = aux;
			peca.largura = 5;
			peca.altura = 1;
			peca.componentesIntactos = 5;
		}
		peca.afundou = false;
		
		return peca;
	}
	
	public void setPosicao(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String getNome() {
		return nome;
	}

	public char[] getComponentes() {
		return componentes;
	}
	
	public void atingida()
	{	
		componentesIntactos --;
		if (componentesIntactos <= 0)
			afundou = true;
	}
	
	public Boolean estaAfundada()
	{
		return afundou;
	}
	
	public void girar90Graus() {
		int auxInt = altura;
		altura = largura;
		largura = auxInt;
		if(largura != 1 && altura != 1) { // Hidroavião
			char aux;
			if(altura > largura) {
				aux = componentes[2];
				componentes[2] = componentes[3];
				componentes[3] = aux;
			}
			else {
				aux = componentes[0];
				componentes[0] = componentes[1];
				componentes[1] = aux;
				aux = componentes[4];
				componentes[4] = componentes[5];
				componentes[5] = aux;
			}
		}
	}
}
