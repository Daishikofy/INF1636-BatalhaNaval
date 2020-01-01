package interfaces;

import regras.RegraJogo.EstadoDeCelula;

public interface Regra {


	public EstadoDeCelula[][] getTabuleiro(int idx);		
	
	public String getVez();	
	public Boolean podeFinalizar();
	public boolean jogoAcabou();
		
	public void onLeftClickTabuleiro(int idx, int x, int y);
	
	public void onRightClick();
	public void onEscPressed();
	
	/* Cadastra uma grid de tabuleiro para 
	 * ser redesenhado quando houver modificaçao 
	 * nas pecas
	 */
	public void ouvirAlteracoes(IObservador observador);

	public void ouvirAlteracoesUI(IObservador observador);
	
	void mouseMovimento(int x, int y);
}
