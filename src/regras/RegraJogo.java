package regras;

import utils.Evento;

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
		System.out.println("oi");
		jogadorAtual ++;
		if (jogadorAtual < nomesJogadores.length)
		{
			regraPreenchimento = new RegraPreenchimento(nomesJogadores[jogadorAtual]);
			trocaPanel.notificar(this);	
		}
		else
		{
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
	
	public RegraEmbate getRegra()
	{
		return regraEmbate;
	}
	
	// Iniciar um novo jogo
	// Continuar um jogo
	
	// identificar turno do jogador atual
	// preechimento ou embate ou fim
	// observador do embate para saber se acabou (passar informação adiante)
	
	// 
	
}
