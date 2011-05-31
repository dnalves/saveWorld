import java.util.Date;


public abstract class AlgoritimoGenetico {

	//numero de individuos n população inicial. 
	//por default são 30, mas nada impede que uma 
	//classe que implemente use outro valor
	protected static final int INDIVIDUOS_POP_INICIAL = 30; 
	
	protected static final byte ESTACAO_AUSENTE = 0;
	protected static final byte ESTACAO_PRESENTE = 1;
	
	protected SaveWorld sw;
	protected byte[][] populacaoP;
	protected byte[][] populacaoPLinha;

	
	public AlgoritimoGenetico(SaveWorld sw)
	{
		super();
		this.sw = sw;
	}
	                 
	/**
	 * Gera a populção inicial (P) para o problema.
	 * @return Uma matriz onde cada uma das linhas representa um individuo (solução candidata) 
	 */
	protected abstract byte[][] geraPopulacaoInicial();
	
	/**
	 * Gera uma nova população (P') a partir de P.
	 * @return Uma matriz onde cada uma das linhas representa um individuo (solução candidata)
	 */
	protected abstract byte[][] evolua();
	
	/**
	 * Reconstroi a população P utilizando-se de elementos de P e P' 
	 * (Selecionar como novo P as melhores solucoes em P').
	 * @return Nova populacao P.
	 */
	protected abstract byte[][] atualizaPopulacao();
	
	/**
	 * Percorre o vetor solução olhando para os dados da instância do problema para montar uma
	 * solução no formato esperado.
	 * @param solucao Vetor indicando quais estações estão presentes na solução. 
	 * @return Solução no formto esperado.
	 */
	private Solucao calculaCustoSolucao(byte[] solucao)
	{
		double custo = 0;
		int numEstacoes = 0;
		String nomeEstacoesSolucao = "";
		
		for (int i = 0; i < solucao.length; i++) 
		{
			if(solucao[i] == 1)
			{
				numEstacoes ++;
				custo = custo + sw.getEstacoes()[i].getCustoReal();
				nomeEstacoesSolucao = nomeEstacoesSolucao + ";" + sw.getEstacoes()[i].getNome();
			}
		}
		
		nomeEstacoesSolucao = nomeEstacoesSolucao.substring(1);//retira primeiro ;
		
		return new Solucao(custo, numEstacoes, nomeEstacoesSolucao.split(";"));
	}
	
	/**
	 * Seleciona a melhor solução global encontrada (presente em P).
	 * @return Solução de menor custo em P
	 */
	private Solucao getMelhorSolucao()
	{		
		Solucao s = null;
		Solucao melhorSolucao = null;
		double menorCusto = Double.MAX_VALUE;
		
		for (int i = 0; i < populacaoP.length; i++) 
		{
			s = this.calculaCustoSolucao(populacaoP[i]);
			if(s.getValor() < menorCusto){							
				menorCusto = s.getValor();
				melhorSolucao = s;
			}
		}
		
		return melhorSolucao;
	}
	
	/**
	 * Retorna a diferença, em segundos, entre duas datas 
	 * @param d1
	 * @param d2
	 * @return Diferença em segundos entre d1 e d2
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
	 * @return Solução do problema
	 */
	public final Solucao geraSolucao(double duracao)
	{
		Date ini = new Date();
		
		populacaoP = this.geraPopulacaoInicial();
		
		while(this.secondsBetween(ini, new Date()) < duracao)
		{
			populacaoPLinha = evolua();
			populacaoP = atualizaPopulacao();			
		}
		
		return this.getMelhorSolucao(); 
	}
	
	/**
	 * Ordena solucoes candidatas em ordem decrescente de valor objetivo.
	 *
	 * @param solucoes as soluções
	 * @return the byte[][]
	 */
	protected byte[][] ordenaSolucoes(byte[][] solucoes){
		//TODO fazer o metodo 
		return null;
	}
}
