package regras;

public class Peca {
	
	private String nome;
	private char[] componentes;
	int x = -1, y = -1;
	int altura, largura;
	
	public static Peca cria(String nome)
	{
		Peca peca = new Peca();
		if (nome == "hidravioes")
		{
			peca.nome = nome;
			char[] aux = {'0','h','0', 'h', '0','h'};
			peca.componentes = aux;
			peca.largura = 3;
			peca.altura = 2;
		}
		else if (nome == "submarinos")
		{
			peca.nome = nome;
			char[] aux = {'s'};
			peca.componentes = aux;
			peca.largura = 1;
			peca.altura = 1;
		}
		else if (nome == "destroyers")
		{
			peca.nome = nome;
			char[] aux = {'d','d'};
			peca.componentes = aux;
			peca.largura = 2;
			peca.altura = 1;
		}
		else if (nome == "cruzadores")
		{	
			peca.nome = nome;
			char[] aux = {'c','c','c','c'};
			peca.componentes = aux;
			peca.largura = 4;
			peca.altura = 1;
		}
		else if (nome == "couracado")
		{
			peca.nome = nome;
			char[] aux = {'r','r','r','r','r'};
			peca.componentes = aux;
			peca.largura = 5;
			peca.altura = 1;
		}
		
		return peca;
	}
	
	public void setPosicao(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String getNome() {
		return nome;
	}

	public char[] getComponentes() {
		return componentes;
	}
	
	public void girar90Graus() {
		int auxInt = altura;
		altura = largura;
		largura = auxInt;
		if(largura != 1 && altura != 1) { // Hidroavião
			char aux;
			if(altura > largura) {
				aux = componentes[2];
				componentes[2] = componentes[3];
				componentes[3] = aux;
			}
			else {
				aux = componentes[0];
				componentes[0] = componentes[1];
				componentes[1] = aux;
				aux = componentes[4];
				componentes[4] = componentes[5];
				componentes[5] = aux;
			}
		}
	}
	
	public boolean colide(Peca outraPeca) {
		if(x < 0 || y < 0) {
			return false;
		}
		if( Peca.intersecta(this.x, this.largura, outraPeca.x, outraPeca.largura) && 
				Peca.intersecta(this.y, this.altura, outraPeca.y, outraPeca.altura)) {
			return true;
		}
		for(int idx = 0; idx < this.componentes.length; idx++) {
			if(this.componentes[idx] != 'a') {
				int xRef = idx/largura + this.x;
				int yRef = idx%largura + this.y;
				for(int i = xRef - 1; i <= xRef + 1; i ++ ) {
					for(int j = yRef - 1; j <= yRef + 1; j++) {
						if(outraPeca.estaOcupado(i, j)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	static private boolean intersecta(int c1, int l1, int c2, int l2) {
		return Integer.min(c1, c2) + l1 + l2 > 
				Integer.max(c1 + l1, c2 + l2);
	}
	
	private boolean estaOcupado (int x, int y) {
		int idxX = x - this.x;
		int idxY = y - this.y;
		
		int idx = idxX * largura + idxY;
		if(idx > 0 && idx < componentes.length) {
			return componentes[idx] != 'a';
		}
		return false;
	}
}
