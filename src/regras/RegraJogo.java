package regras;

import utils.Evento;
import interfaces.Regra;

public class RegraJogo {
	public enum EstadoDeCelula {
		AGUA,				// Não contém arma '0'
		OCUPADO,			// Contém arma intacta 'o' (ou uma letra pra cada arma)
		ATINGIDO,			// Contém parte de arma atingida 'x'
		AFUNDADO,			// Toda a arma foi atingida 'X'
		INVALIDO,			// Posicionamento de arma encosta em outra arma 'i'
		OCULTO				// Seu conteúdo não pode ser visto atualmente <upper case - {'X'}>
	}
	public enum EstadoDoJogo {
		TELAINICIAL,
		ESCOLHAJOGADORES,
		POSICIONAMENTO,	
		EMBATE	
	}
	static RegraJogo instance = null;
	
	public Evento trocaPanel;
	EstadoDoJogo estadoAtual;
	
	int jogadorAtual = -1;
	String[] nomesJogadores;
	
	RegraPreenchimento regraPreenchimento;
	TabuleiroData[] tabuleiros;
	
	
	RegraEmbate regraEmbate;
	
	public static RegraJogo Instance()
	{
		if (instance == null)
		{
			instance = new RegraJogo();
		}
		return instance;
	}
	
	private RegraJogo()
	{	
		estadoAtual = EstadoDoJogo.TELAINICIAL;
		trocaPanel = new Evento();
		
	}
	
	public EstadoDoJogo getEstado()
	{
		return estadoAtual;
	}
	
	public void escolherJogadores()
	{
		estadoAtual = EstadoDoJogo.ESCOLHAJOGADORES;
		jogadorAtual = 0;
		trocaPanel.notificar(this);
	}
	public void inciciarNovoJogo()
	{
		estadoAtual = EstadoDoJogo.POSICIONAMENTO;
		regraPreenchimento = new RegraPreenchimento(nomesJogadores[jogadorAtual]);
		tabuleiros = new TabuleiroData[nomesJogadores.length];
		trocaPanel.notificar(this);
	}
	
	public void trocarJogadorPreenchimento()
	{
		tabuleiros[jogadorAtual] = regraPreenchimento.finalizaPosicionamento();
		jogadorAtual ++;
		if (jogadorAtual < nomesJogadores.length)
		{
			regraPreenchimento = new RegraPreenchimento(nomesJogadores[jogadorAtual]);
			trocaPanel.notificar(this);	
		}
		else
		{
			System.out.println("Começa embate");
			iniciarEmbate();
		}
	}
	
	private void iniciarEmbate()
	{
		estadoAtual = EstadoDoJogo.EMBATE;
		jogadorAtual = 0;
		
		regraEmbate = new RegraEmbate(tabuleiros);
		trocaPanel.notificar(this);	
	}
	
	private void trocarJogadorEmbate()
	{
		jogadorAtual = (jogadorAtual + 1) % 2;
	}
	
	private void finalizarPreenchimento()
	{
		
	}
	
	private void salvarJogo()
	{
		
	}
	
	private void carregarJogo()
	{
		estadoAtual = EstadoDoJogo.EMBATE;
	}
	
	public void setJogadores(String[] jogadores)
	{
		nomesJogadores = jogadores;
	}
	
	public Regra getRegra()
	{
		if (estadoAtual == EstadoDoJogo.EMBATE)
		{
			System.out.println("Regra embate");
			return regraEmbate;
		}
		else if (estadoAtual == EstadoDoJogo.POSICIONAMENTO)
		{
			System.out.println("Regra posicionamento");
			return regraPreenchimento;
		}		
		System.out.println("Erro: Foi pedido uma regra fora de uma fase de jogo");
		return null;
	}
	
	
}
