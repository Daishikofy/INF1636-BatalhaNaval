package regras;

public class Peca {
	
	private String nome;
	private char[] componentes;
	
	public static Peca cria(String nome)
	{
		Peca peca = new Peca();
		if (nome == "hidravioes")
		{
			peca.nome = nome;
			char[] aux = {'a','h','a', 'h', 'a','h'};
			peca.componentes = aux;
		}
		else if (nome == "submarinos")
		{
			peca.nome = nome;
			char[] aux = {'s'};
			peca.componentes = aux;
		}
		else if (nome == "destroyers")
		{
			peca.nome = nome;
			char[] aux = {'d','d'};
			peca.componentes = aux;
		}
		else if (nome == "cruzadores")
		{	
			peca.nome = nome;
			char[] aux = {'c','c','c','c'};
			peca.componentes = aux;
		}
		else if (nome == "couracado")
		{
			peca.nome = nome;
			char[] aux = {'r','r','r','r','r'};
			peca.componentes = aux;
		}
		
		return peca;
	}

	public String getNome() {
		return nome;
	}

	public char[] getComponentes() {
		return componentes;
	}
}
