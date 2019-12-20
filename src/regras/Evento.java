package regras;

import java.util.ArrayList;
import java.util.List;

import interfaces.IObservado;
import interfaces.IObservador;

public class Evento implements IObservado{

	private List<IObservador> observadores;

	public Evento()
	{
		observadores = new ArrayList<>();		
	}
	
	@Override
	public void cadastrar(IObservador novoObservador) {
		observadores.add(novoObservador);		
	}

	@Override
	public void remover(IObservador observadorRetirado) {
		observadores.remove(observadorRetirado);		
	}

	@Override
	public void notificar(IObservado fonte) {
		observadores.forEach(observador -> observador.update());	
	}

}
