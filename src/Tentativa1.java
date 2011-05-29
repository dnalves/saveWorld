import java.util.Random;



/**
 * Classe que representa uma forma de tentiva de salvr o mundo com a utilização
 * de algoritimo genético
 */
public class Tentativa1 extends AlgoritimoGenetico {

	public Tentativa1(SaveWorld sw, int tamanhoPop) {
		super(sw, tamanhoPop);
	}

	@Override
	protected byte[][] atualizaPopulacao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected byte[][] evolua() {
		
		int metade = sw.getNumEstacoes() / 2;
		
		// Coloca a 1a metade da instancia vinda de P, a 2a metade é de PLinha
		for (int i = 0; i < populacaoP.length; i++) {
			for (int j = 0; j < metade; j++) {
				populacaoPLinha[i][j] = populacaoP[i][j];
			}
		}
		
		return null;
	}

	@Override
	protected byte[][] geraPopulacaoInicial() {
		
		int numEstacoes = sw.getNumEstacoes();
		byte[][] retorno = new byte[tamanhoPop][numEstacoes];
		Random generator = new Random();
		
		for (int i = 0; i < tamanhoPop; i++) {
			for (int j = 0; j < numEstacoes; j++) {
				
				//Gerando populaca‹o inicial rando™mica
				retorno[i][j] = (byte) generator.nextInt(2);
				
			}
		}
		
		return retorno;
	}

}
