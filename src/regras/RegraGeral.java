package regras;

public class RegraGeral {

	public enum EstadoDeCelula {
		AGUA,				// Não contém arma 'a'
		OCUPADO,			// Contém arma intacta 'o' (ou uma letra pra cada arma)
		ATINGIDO,			// Contém parte de arma atingida 'x'
		AFUNDADO,			// Toda a arma foi atingida 'X'
		INVALIDO,			// Posicionamento de arma encosta em outra arma 'i'
		OCULTO				// Seu conteúdo não pode ser visto atualmente <upper case - {'X'}>
	}
	
	char tabuleiro [][] = new char[15][15];
	int vez=5;
	
	public RegraGeral() {
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				tabuleiro[i][j] = 'a';
			}
		}
	}
	
	public EstadoDeCelula[][] getMatriz() {
		EstadoDeCelula[][] matrizDeCelulas = new EstadoDeCelula[15][15];
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				EstadoDeCelula estado = EstadoDeCelula.OCUPADO;
				char conteudo = tabuleiro[i][j];
				if(conteudo == 'X') {
					estado = EstadoDeCelula.AFUNDADO;
				} 
				else if(Character.isUpperCase(conteudo)) {
					estado = EstadoDeCelula.OCULTO;
				}
				else if(conteudo == 'i') {
					estado = EstadoDeCelula.INVALIDO;
				}
				else if(conteudo == 'a') {
					estado = EstadoDeCelula.AGUA;
				}
				else if(conteudo == 'x') {
					estado = EstadoDeCelula.ATINGIDO;
				}
				
				matrizDeCelulas[i][j] = estado;
			}
		}
		return matrizDeCelulas;
	}
	
	public int getVez() {
		return vez;
	}	
	
	public boolean onClick(int x, int y)
	{
		if (x < 0 || x > 14 || y < 0 || y > 14)
			return false;
		if (tabuleiro[y][x] != 'a')
			return false;
		
		tabuleiro[y][x] = 'o';
		if(vez == -1)
			vez = 5;
		else
			vez = -1;
		return true;		
	}
}