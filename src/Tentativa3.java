import java.util.ArrayList;
import java.util.Random;



/**
 * Classe que representa uma forma de tentiva de salvr o mundo com a utilização
 * de algoritimo genético
 */
public class Tentativa3 extends AlgoritimoGenetico {

	public Tentativa3(SaveWorld sw) 
	{
		super(sw);
	}

	@Override
	protected void atualizaPopulacao() {
		Solucao[] atualizada = new Solucao[INDIVIDUOS_POP_INICIAL];
		ArrayList<Solucao> popP = new ArrayList<Solucao>();
		ArrayList<Solucao> popPLinha = new ArrayList<Solucao>();
		
		for (int i = 0; i < populacaoP.length; i++) {
			popP.add(populacaoPLinha[i]);
		}
		for (int i = 0; i < populacaoPLinha.length; i++) {
			popPLinha.add(populacaoPLinha[i]);
		}
		
		for (int i = 0; i < atualizada.length / 2; i++) {
			atualizada[i] = getMelhorSolucao(popP);
			if(atualizada[i] == null){
				atualizada[i] = getSolucaoIncompleta(popP);
			}
			popP.remove(atualizada[i]);
		}
		for (int i = atualizada.length / 2 ; i < atualizada.length ; i++){
			atualizada[i] = getMelhorSolucao(popPLinha);
			if(atualizada[i] == null){
				atualizada[i] = getSolucaoIncompleta(popPLinha);
			}
			popPLinha.remove(atualizada[i]);
		}
		
		this.populacaoP = atualizada;	
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
	protected void geraPopulacaoInicial(){
		Estacao[] EstacoesOrd = ordenaEstacoesRelacaoPontosCusto(sw.getEstacoes());
		int numEstacoes = sw.getNumEstacoes();
		int[] representacao = new int[sw.getNumEstacoes()];
		int numPontos = sw.getNumPontos();
		Random generator = new Random();
		int k;
		
		sw.setEstacoes(EstacoesOrd);
		
		for(int i = 0; i < INDIVIDUOS_POP_INICIAL; i++) {
			for(int j = 0; j < numEstacoes/FATOR_PASSOS; j++) {
				k = generator.nextInt(numEstacoes/FATOR_ESCOLHA);
				representacao[k] = 1;
				populacaoP[i] = new Solucao(representacao,sw);
				if(populacaoP[i].getNumPontosCobertos() == numPontos) break;
				
			}
			
			for(int j = 0; j < numEstacoes/FATOR_PASSOS; j++){
				k = generator.nextInt(numEstacoes);
				representacao[k] = 1;
				populacaoP[i] = new Solucao(representacao,sw);
				if(populacaoP[i].getNumPontosCobertos() == numPontos) break;

			}
			
			if(populacaoP[i].getNumPontosCobertos() < numPontos){
				for(int j = 0; j < numEstacoes; j++){
					representacao[j] = 1;
					populacaoP[i] = new Solucao(representacao,sw);
					if(populacaoP[i].getNumPontosCobertos() == numPontos) break;
				}
			}
		}
		
		for(int i = 0; i < INDIVIDUOS_POP_INICIAL; i++) {
			for(int j = 0; j < numEstacoes/FATOR_PASSOS; j++) {
				k = generator.nextInt(numEstacoes/FATOR_ESCOLHA);
				representacao[k] = 1;
				populacaoPLinha[i] = new Solucao(representacao,sw);
				if(populacaoPLinha[i].getNumPontosCobertos() == numPontos) break;
				
			}
			
			for(int j = 0; j < numEstacoes/FATOR_PASSOS; j++){
				k = generator.nextInt(numEstacoes);
				representacao[k] = 1;
				populacaoPLinha[i] = new Solucao(representacao,sw);
				if(populacaoPLinha[i].getNumPontosCobertos() == numPontos) break;

			}
			
			if(populacaoP[i].getNumPontosCobertos() < numPontos){
				for(int j = 0; j < numEstacoes; j++){
					representacao[j] = 1;
					populacaoP[i] = new Solucao(representacao,sw);
					if(populacaoP[i].getNumPontosCobertos() == numPontos) break;
				}
			}
			
			for(int j = 0; j < numEstacoes; j++) representacao[j] = 0;
		}
		
	}

	
	/**
	 * Ordena estacoes utilizando a relação pontos cobertos sobre o custo.
	 * Em ordem decerscente.
	 *
	 * @param estacoes
	 * @return Uma LinkedList ordenada
	 */
	private Estacao[] ordenaEstacoesRelacaoPontosCusto(Estacao[] estacoes) {		
		Estacao estacao;
		double relacaoPontosCusto;
		
		
		for (int i = 1 ; i < estacoes.length; i++){
			estacao = estacoes[i];
			relacaoPontosCusto = estacao.getPontosCobertos().length/estacao.getCustoReal();
			for (int j = i - 1; j >= 0; j--) {
				if(relacaoPontosCusto > (estacoes[j].getPontosCobertos().length/estacoes[j].getCustoReal())){
					break;
				} else {
					estacoes[j + 1] = estacoes[j];
					estacoes[j] = estacao;
				}
			}
		}
		
		return estacoes;
	}

}
