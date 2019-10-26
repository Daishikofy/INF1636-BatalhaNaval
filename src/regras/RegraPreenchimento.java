package regras;

public class RegraPreenchimento  extends RegraGeral {
	int a[][];
	Peca[] pecas = new Peca[15];
	
	public RegraPreenchimento()
	{
		geraPecas();
	}
	
	public void transferir(RegraEmbate r) {
		
	}
	
	public void validaInsercao(Peca peca)
	{}
	
	private void geraPecas()
	{
		int i = 0;
		pecas[i] = Peca.cria("couracado");
		for (i = 1; i < 3; i++)
			pecas[i] = Peca.cria("cruzadores");
		for (; i < 6; i++)
			pecas[i] = Peca.cria("destroyers");
		for (; i < 10; i++)
			pecas[i] = Peca.cria("submarinos");
		for (; i < 15; i++)
			pecas[i] = Peca.cria("hidravioes");
	}
}
