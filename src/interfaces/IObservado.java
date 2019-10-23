package interfaces;

public interface IObservado {
	
	public void cadastrar(IObservador novoObservador);
	
	public void remover(IObservador observadorRetirado);
	
	public Object get(int index);
}
