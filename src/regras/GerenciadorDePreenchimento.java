package regras;

public class GerenciadorDePreenchimento {
	static GerenciadorDePreenchimento gerenciador = null;
	RegraPreenchimento regras ;
	
	private GerenciadorDePreenchimento()
	{
		System.out.println("oi");
		regras = new RegraPreenchimento();
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
		return regras;
	}
	
	public void onLeftClickTabuleiro(int x, int y)
	{
		regras.onLeftClickTabuleiro(x,y);
	}
	
	public void onLeftClickPecas(int x, int y)
	{
		regras.onLeftClickPecas(x,y);
	}
	
	public void onRightClick()
	{
		regras.onRightClick();
	}
}
