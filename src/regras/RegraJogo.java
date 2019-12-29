package regras;

import utils.Evento;

import java.io.*;

import interfaces.IObservador;
import interfaces.Regra;

public class RegraJogo implements IObservador {
	public enum EstadoDeCelula {
		AGUA,				// Não contém arma '0'
		HIDROAVIAO,			// Contêm arma intacta
		SUBMARINO,
		DESTROYER,
		CRUZADOR,
		COURACADO,
		ATINGIDO,			// Contém parte de arma atingida 'x'
		AFUNDADO,			// Toda a arma foi atingida 'X'
		INVALIDO,			// Posicionamento de arma encosta em outra arma 'i'
		OCULTO				// Seu conteúdo não pode ser visto atualmente <upper case - {'X'}>
	}
	
	public enum EstadoDoJogo {
		TELAINICIAL,
		ESCOLHAJOGADORES,
		POSICIONAMENTO,	
		EMBATE,
		FINALIZADO
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
	
	public void jogoFinalizado() {
		estadoAtual = EstadoDoJogo.TELAINICIAL;
		trocaPanel.notificar(this);
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
		
		regraEmbate = new RegraEmbate(tabuleiros, nomesJogadores);
		regraEmbate.jogoFinalizado.cadastrar(this);
		trocaPanel.notificar(this);	
	}
	
	public void salvarJogo(File arquivo) {
		assert estadoAtual == EstadoDoJogo.EMBATE;
		try {
			FileWriter escrita;
			escrita = new FileWriter(arquivo);
			regraEmbate.escrever(escrita);
			escrita.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void carregarJogo(File f)
	{
		try {
			FileReader fr;
			fr = new FileReader(f);
			regraEmbate = new RegraEmbate(fr);
			fr.close();
			regraEmbate.jogoFinalizado.cadastrar(this);
			estadoAtual = EstadoDoJogo.EMBATE;
			trocaPanel.notificar(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public String getVencedor() {
		return regraEmbate.getVencedor();
	}

	
	
	@Override
	public void update() {
		if(regraEmbate.jogoAcabou()) {
			estadoAtual = EstadoDoJogo.FINALIZADO;
			trocaPanel.notificar(this);
		}
	}
	
	
}
