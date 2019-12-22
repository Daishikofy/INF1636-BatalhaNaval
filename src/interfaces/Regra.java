package interfaces;

import regras.RegraJogo.EstadoDeCelula;
import regras.TabuleiroData;

public interface Regra {
	//Botar em utils
	public EstadoDeCelula[][] getTabuleiro();	
	public EstadoDeCelula[][] getPecas();			
	
	public String getVez();	
	public Boolean podeFinalizar();
			
	public void onLeftClickTabuleiro(int x, int y);
	public void onRightClick();
}
