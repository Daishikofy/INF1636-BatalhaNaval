package regras;

import utils.Evento;

public class RegraJogo {
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
	
	
	RegraEmbate regrasEmbate[];
	
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
		trocaPanel.notificar(this);
	}
	
	private void trocarJogadorPreenchimento()
	{
		
	}
	
	private void iniciarEmbate()
	{
		estadoAtual = EstadoDoJogo.EMBATE;
	}
	
	private void trocarJogadorEmbate()
	{
		
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
	
	// Iniciar um novo jogo
	// Continuar um jogo
	
	// identificar turno do jogador atual
	// preechimento ou embate ou fim
	// observador do embate para saber se acabou (passar informação adiante)
	
	// 
	
}
