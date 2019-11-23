package regras;

public class ManagerDeEventos {
	static ManagerDeEventos manager = null;
	RegraGeral regras ;
	
	private ManagerDeEventos()
	{
		regras = new RegraPreenchimento();
	}
	
	public static ManagerDeEventos getManager()
	{
		if (manager == null)
			manager = new ManagerDeEventos();
		return manager;
	}
	//TODO: remover este getRegra quando observador for implementado
	public RegraGeral getRegra()
	{
		return regras;
	}
	
	public void onClick(int x, int y)
	{
		regras.onClick(x,y);
	}
}
