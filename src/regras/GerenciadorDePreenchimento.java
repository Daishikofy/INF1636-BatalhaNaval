package regras;

public class GerenciadorDePreenchimento {
	static GerenciadorDePreenchimento gerenciador = null;
	RegraPreenchimento regra ;
	
	private GerenciadorDePreenchimento()
	{
		regra = RegraJogo.Instance().regraPreenchimento;
	}
	
	public static GerenciadorDePreenchimento getManager()
	{
		if (gerenciador == null)
		{
			gerenciador = new GerenciadorDePreenchimento();
		}
		return gerenciador;
	}
	//TODO: remover este getRegra quando observador for implementado
	public RegraPreenchimento getRegra()
	{
		return regra;
	}
	
	public void onLeftClickTabuleiro(int x, int y)
	{
		regra.onLeftClickTabuleiro(x,y);
	}
	
	public void onLeftClickPecas(int x, int y)
	{
		regra.onLeftClickPecas(x,y);
	}
	
	public void onRightClick()
	{
		regra.onRightClick();
	}
}
