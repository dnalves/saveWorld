import java.util.ArrayList;
import java.util.Date;


public abstract class AlgoritimoGenetico {

	//numero de individuos n popula��o inicial. 
	//por default s�o 30, mas nada impede que uma 
	//classe que implemente use outro valor
	protected static final int INDIVIDUOS_POP_INICIAL = 30;
	
	//fator de divisao na utilizacao da heuristica
	//construtiva de geracao de populacao inicial
	protected static final int FATOR_PASSOS = 4;
	
	//fator de divisao para decisao de qual a parcela
	//do numero de estacoes "melhores" sera escolhida
	protected static final int FATOR_ESCOLHA = 2;
	
	protected static final byte ESTACAO_AUSENTE = 0;
	protected static final byte ESTACAO_PRESENTE = 1;
	
	protected SaveWorld sw;
	protected Solucao[] populacaoP;
	protected Solucao[] populacaoPLinha;

	
	public AlgoritimoGenetico(SaveWorld sw)
	{
		super();
		populacaoP = new Solucao[INDIVIDUOS_POP_INICIAL];
		populacaoPLinha = new Solucao[INDIVIDUOS_POP_INICIAL];
		this.sw = sw;
	}
	                 
	/**
	 * Gera a popul��o inicial (P) para o problema.
	 * @return Uma matriz onde cada uma das linhas representa um individuo (solu��o candidata) 
	 */
	protected abstract void geraPopulacaoInicial();
	
	/**
	 * Gera uma nova popula��o (P') a partir de P.
	 * @return Uma matriz onde cada uma das linhas representa um individuo (solu��o candidata)
	 */
	protected abstract void evolua();
	
	/**
	 * Reconstroi a popula��o P utilizando-se de elementos de P e P' 
	 * (Selecionar como novo P as melhores solucoes em P').
	 * @return Nova populacao P.
	 */
	protected abstract void atualizaPopulacao();

	
	/**
 * Seleciona a melhor solu��o global encontrada (presente em popula��o).
 *
 * @param populacao a popula��o onde deve ser realizada a busca
 * @return Solu��o de menor custo em popula��o
 */
	protected Solucao getMelhorSolucao(ArrayList<Solucao> populacao)
	{		
		Solucao melhorSolucao = null;
		double menorCusto = Double.POSITIVE_INFINITY;
		double valorSol, numPontosCobertos;
		
		for (Solucao solucao : populacao) {
			valorSol = solucao.getValor();
			numPontosCobertos = solucao.getNumPontosCobertos();
			//Garante que cobre todos os pontos e que o custo � o menor
			if(valorSol < menorCusto && numPontosCobertos == sw.getNumPontos()){
				menorCusto = valorSol;
				melhorSolucao = solucao;
			}
		}
		
		return melhorSolucao;
	}
	
	/**
	 * Retorna a diferen�a, em segundos, entre duas datas 
	 * @param d1
	 * @param d2
	 * @return Diferen�a em segundos entre d1 e d2
	 */
	private double secondsBetween(Date d1, Date d2)
	{
		long l1 = d1.getTime();
		long l2 = d2.getTime();
		return (l2 - l1)/1000;
	}
	
	/**
	 * Devolve uma solu��o que n�o cobre todos os pontos mas � uma das melhores entre as incompletas
	 *
	 * @param populacao A popula��o onde ser� realizada a busca
	 * @return Uma solu��o que n�o cobre todos os pontos
	 */
	protected Solucao getSolucaoIncompleta(ArrayList<Solucao> populacao){
		Solucao melhorSolucao = null;
		double menorCusto = Double.POSITIVE_INFINITY;
		int pontosCobertos = Integer.MIN_VALUE;
		double valorSol, numPontosCobertos;
		
		for (Solucao solucao : populacao) {
			valorSol = solucao.getValor();
			numPontosCobertos = solucao.getNumPontosCobertos();
			//Pega a solu��o de menor custo que cobre mais pontos
			if(valorSol < menorCusto && numPontosCobertos > pontosCobertos){
				menorCusto = valorSol;
				numPontosCobertos = pontosCobertos;
				melhorSolucao = solucao;
			}
		}
		
		return melhorSolucao;
	}
	
	/**
	 * Algoritmo genetico basico
	 * @param duracao Quanto tempo que o algoritimo deve executar (em segundos).
	 * @return Solu��o do problema
	 */
	public final Solucao geraSolucao(double duracao)
	{
		Date ini = new Date();
		
		this.geraPopulacaoInicial();
		
		while(this.secondsBetween(ini, new Date()) < duracao)
		{
			evolua();
			atualizaPopulacao();			
		}
		
		// Transforma o vetor de solu��es em uma lista
		ArrayList<Solucao> solucoes = new ArrayList<Solucao>();
		for (Solucao solucao : populacaoP) {
			solucoes.add(solucao);
		}
		
		return this.getMelhorSolucao(solucoes); 
	}
}
