package regras;

public class Peca {
	
	private String nome;
	private char[][] componentes;
	
	public static Peca cria(String nome)
	{
		Peca peca = new Peca();
		if (nome == "hidroavioes")
		{
			peca.nome = nome;
			char[][] aux = {{'x','h','x'},{'h','x','h'}};
			peca.componentes = aux;
		}
		else if (nome == "submarinos")
		{
			peca.nome = nome;
			char[][] aux = {{'s'},{'x'}};
			peca.componentes = aux;
		}
		else if (nome == "destroyers")
		{
			peca.nome = nome;
			char[][] aux = {{'d','d'},{'x'}};
			peca.componentes = aux;
		}
		else if (nome == "cruzadores")
		{
			peca.nome = nome;
			char[][] aux = {{'c','c','c','c'},{'x'}};
			peca.componentes = aux;
		}
		else if (nome == "couracado")
		{
			peca.nome = nome;
			char[][] aux = {{'r','r','r','r','r'},{'x'}};
			peca.componentes = aux;
		}
		else
		{
			peca.nome = "NULL";
			char[][] aux = {{'x'},{'x'}};
			peca.componentes = aux;
		}
		
		return peca;
	}

	public String getNome() {
		return nome;
	}

	public char[][] getComponentes() {
		return componentes;
	}
}
