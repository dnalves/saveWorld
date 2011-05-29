import java.util.Random;



/**
 * Classe que representa uma forma de tentiva de salvr o mundo com a utilização
 * de algoritimo genético
 */
public class Tentativa1 extends AlgoritimoGenetico {

	@Override
	protected byte[][] atualizaPopulacao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected byte[][] evolua() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected byte[][] geraPopulacaoInicial(saveWorld sw, int tamanho) {
		
		int numEstacoes = sw.getNumEstacoes();
		byte[][] retorno = new byte[tamanho][numEstacoes];
		Random generator = new Random();
		
		for (int i = 0; i < tamanho; i++) {
			for (int j = 0; j < numEstacoes; j++) {
				
				//Gerando popula‹o inicial rand™mica
				retorno[i][j] = (byte) generator.nextInt(2);
				System.out.println(retorno[i][j]);
			}
		}
		
		return retorno;
	}

}
