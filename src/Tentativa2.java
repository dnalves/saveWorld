import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
	protected void geraPopulacaoInicial() {
		
		int numEstacoes = sw.getNumEstacoes();		
		int[] representacao = new int[sw.getNumEstacoes()];
		Random generator = new Random();
		
		for (int i = 0; i < INDIVIDUOS_POP_INICIAL; i++) {
			for (int j = 0; j < numEstacoes; j++) {
				//Gerando populacã‹o P inicial rando™mica
				representacao[j] = generator.nextInt(2);
			}
			populacaoP[i] = new Solucao(representacao, sw);
		}
		
		for (int i = 0; i < INDIVIDUOS_POP_INICIAL; i++) {
			for (int j = 0; j < numEstacoes; j++) {
				//Gerando populacã‹o P inicial rando™mica
				representacao[j] = generator.nextInt(2);
			}
			populacaoPLinha[i] = new Solucao(representacao, sw);
		}
		
		
	}
	
	protected void geraPopulacaoInicialHeuristica(){
		LinkedList<Estacao> ordenada = ordenaEstacoesRelacaoPontosCusto(sw.getEstacoes());
		
		
	}

	
	/**
	 * Ordena estacoes utilizando a relação pontos cobertos sobre o custo.
	 * Em ordem decerscente.
	 *
	 * @param estacoes
	 * @return Uma LinkedList ordenada
	 */
	private LinkedList<Estacao> ordenaEstacoesRelacaoPontosCusto(Estacao[] estacoes) {
		
		LinkedList<Estacao> list = new LinkedList<Estacao>();
		list.add(estacoes[0].clone());
		
		Estacao estacao, estacaoLista;
		double relacaoPontosCusto;
		int j;
		
		for (int i = 1 ; i < estacoes.length ; i++){
			estacao = estacoes[i];
			relacaoPontosCusto = estacao.getPontosCobertos().length/estacao.getCustoReal();
			for (j = 0 ; j < list.size() ; j++) {
				estacaoLista = list.get(j);
				if(relacaoPontosCusto < (estacaoLista.getPontosCobertos().length/estacao.getCustoReal())){
					j++;
				} else {
					break;
				}
			}
			list.add(j, estacao);
		}
		
		return list;
	}

}
