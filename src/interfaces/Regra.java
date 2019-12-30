package interfaces;

import regras.RegraJogo.EstadoDeCelula;

public interface Regra {

	//TODO: Remover a primeira instancia
	public EstadoDeCelula[][] getTabuleiro();	
	public EstadoDeCelula[][] getTabuleiro(int idx);
	
	//TODO: Passar a usar o idx e remover
	public EstadoDeCelula[][] getPecas();			
	
	public String getVez();	
	public Boolean podeFinalizar();
		
	//TODO: Remover a primeira instancia
	public void onLeftClickTabuleiro(int x, int y);
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
