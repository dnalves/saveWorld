import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public abstract class AlgoritimoGenetico {

	//numero de individuos n popula��o inicial. 
	//por default s�o 30, mas nada impede que uma 
	//classe que implemente use outro valor
	protected static final int INDIVIDUOS_POP_INICIAL = 30; 
	
	protected static final byte ESTACAO_AUSENTE = 0;
	protected static final byte ESTACAO_PRESENTE = 1;
	
	protected SaveWorld sw;
	protected Solucao[] populacaoP;
	protected Solucao[] populacaoPLinha;

	
	public AlgoritimoGenetico(SaveWorld sw)
	{
		super();
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
//	
//	/**
//	 * Percorre o vetor solu��o olhando para os dados da inst�ncia do problema para montar uma
//	 * solu��o no formato esperado.
//	 * @param solucao Vetor indicando quais esta��es est�o presentes na solu��o. 
//	 * @return Solu��o no formto esperado.
//	 */
//	private Solucao calculaCustoSolucao(byte[] solucao)
//	{
//		double custo = 0;
//		int numEstacoes = 0;
//		String nomeEstacoesSolucao = "";
//		
//		for (int i = 0; i < solucao.length; i++) 
//		{
//			if(solucao[i] == 1)
//			{
//				numEstacoes ++;
//				custo = custo + sw.getEstacoes()[i].getCustoReal();
//				nomeEstacoesSolucao = nomeEstacoesSolucao + ";" + sw.getEstacoes()[i].getNome();
//			}
//		}
//		
//		nomeEstacoesSolucao = nomeEstacoesSolucao.substring(1);//retira primeiro ;
//		
//		return new Solucao(custo, numEstacoes, nomeEstacoesSolucao.split(";"));
//	}
	
	/**
	 * Seleciona a melhor solu��o global encontrada (presente em P).
	 * @return Solu��o de menor custo em P
	 */
	private Solucao getMelhorSolucao()
	{		
		Solucao melhorSolucao = null;
		double menorCusto = Double.MAX_VALUE;
		double valorSol;
		
		for (Solucao solucao : populacaoP) {
			valorSol = solucao.getValor();
			if(valorSol < menorCusto){
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
	 * Algoritmo genetico basico
	 * @param duracao Quanto tempo que o algoritimo deve executar (em segundos).
	 * @return Solu��o do problema
	 */
	public final Solucao geraSolucao(double duracao)
	{
		Date ini = new Date();
		
		this.geraPopulacaoInicial();
		this.geraPopulacaoInicial();
		
		while(this.secondsBetween(ini, new Date()) < duracao)
		{
			evolua();
			atualizaPopulacao();			
		}
		
		return this.getMelhorSolucao(); 
	}
	
	/**
	 * Ordena solucoes candidatas em ordem decrescente de valor objetivo.
	 *
	 * @param solucoes as solu��es
	 * @return the byte[][]
	 */
	protected byte[][] ordenaSolucoes(byte[][] solucoes){
		 return null;
	}
}
