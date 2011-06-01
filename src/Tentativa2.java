import java.util.ArrayList;
import java.util.Random;



/**
 * Classe que representa uma forma de tentiva de salvr o mundo com a utilização
 * de algoritimo genético
 */
public class Tentativa2 extends AlgoritimoGenetico {

	public Tentativa2(SaveWorld sw) 
	{
		super(sw);
	}

	@Override
	protected void atualizaPopulacao() {
		
	}

	@Override
	protected void evolua() {
		
		int metade = sw.getNumEstacoes() / 2;
		int[] repP = new int[sw.getNumEstacoes()];
		int[] repPLinha = new int[sw.getNumEstacoes()];
		
		for (int i = 0; i < populacaoP.length; i++) {
			repP = populacaoP[i].getRepresentacao();
			repPLinha = populacaoPLinha[i].getRepresentacao();
			
			// copia a 1a metade de P para PLinha
			// a 2a metade é a de PLinha
			for (int j = 0; j < metade; j++) {
				repPLinha[j] = repP[j];
			}
			
			populacaoPLinha[i].setRepresentacao(repPLinha);
			
		}

	}

	@Override
	protected void geraPopulacaoInicial() {
		
		int numEstacoes = sw.getNumEstacoes();
		populacaoP = new Solucao[INDIVIDUOS_POP_INICIAL];
		populacaoPLinha = new Solucao[INDIVIDUOS_POP_INICIAL];
		int[] representacao = new int[sw.getNumEstacoes()];
		Random generator = new Random();
		
		for (int i = 0; i < INDIVIDUOS_POP_INICIAL; i++) {
			for (int j = 0; j < numEstacoes; j++) {
				//Gerando populacã‹o P inicial rando™mica
				representacao[j] = generator.nextInt(2);
			}
			populacaoP[i] = new Solucao(representacao);
		}
		
		for (int i = 0; i < INDIVIDUOS_POP_INICIAL; i++) {
			for (int j = 0; j < numEstacoes; j++) {
				//Gerando populacã‹o P inicial rando™mica
				representacao[j] = generator.nextInt(2);
			}
			populacaoPLinha[i] = new Solucao(representacao);
		}
		
	}

}
