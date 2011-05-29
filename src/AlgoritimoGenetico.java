
public abstract class AlgoritimoGenetico {

	protected byte[][] populacaoP;
	protected byte[][] populacaoPLinha;
	
	                 
	/**
	 * Gera a popul��o inicial (P) para o problema.
	 * @return Uma matriz onde cada uma das linhas representa um individuo (solu��o candidata) 
	 */
	protected abstract byte[][] geraPopulacaoInicial(saveWorld sw, int tamanho);
	
	/**
	 * Gera uma nova popula��o (P') a partir de P.
	 * @return Uma matriz onde cada uma das linhas representa um individuo (solu��o candidata)
	 */
	protected abstract byte[][] evolua();
	
	/**
	 * Reconstroi a popula��o P utilizando-se de elementos de P e P' 
	 * (Selecionar como novo P as melhores solu�c�oes em P').
	 * @return Nova populacao P.
	 */
	protected abstract byte[][] atualizaPopulacao();
	
	/**
	 * Percorre o vetor solu��o olhando para os ddos da inst�ncia do problema para montar uma
	 * solu��o no formato esperado.
	 * @param solucao Vetor indicando quais esta��es est�o presentes na solu��o. 
	 * @param estcoes Dados das esta��es dessa inst�ncia do problema.
	 * @return Solu��o no formto esperado.
	 */
	private Solucao calculaCustoSolucao(byte[] solucao, Estacao[] estacoes)
	{
		double custo = 0;
		int numEstacoes = 0;
		String nomeEstacoesSolucao = "";
		
		for (int i = 0; i < solucao.length; i++) 
		{
			if(solucao[i] == 1)
			{
				numEstacoes ++;
				custo = custo + estacoes[i].getCustoReal();
				nomeEstacoesSolucao = nomeEstacoesSolucao + ";" + estacoes[i].getNome();
			}
		}
		
		nomeEstacoesSolucao = nomeEstacoesSolucao.substring(1);//retira primeiro ;
		
		return new Solucao(custo, numEstacoes, nomeEstacoesSolucao.split(";"));
	}
	
	/**
	 * Seleciona a melhor solu��o global encontrada (presente em P).
	 * @param estacoes Dados das esta��es da inst�ncia do problema
	 * @return Solu��o de menor custo em P
	 */
	private Solucao getMelhorSolucao(Estacao[] estacoes)
	{		
		Solucao s = null;
		Solucao melhorSolucao = null;
		double menorCusto = Double.MAX_VALUE;
		
		for (int i = 0; i < populacaoP.length; i++) 
		{
			s = this.calculaCustoSolucao(populacaoP[i], estacoes);
			if(s.getValor() < menorCusto){							
				menorCusto = s.getValor();
				melhorSolucao = s;
			}
		}
		
		return melhorSolucao;
	}
	
	/**
	 * Algoritmo genetico basico
	 * @param sw objeto com dados da inst�ncia do  problema
	 * @return Solu��o do problema
	 */
	public final Solucao geraSolucao(saveWorld sw)
	{
		populacaoP = this.geraPopulacaoInicial(sw, 30);
		int i = 0;
		while(i < 1998)//TODO limitar por tempo: 1 minuto
		{
			populacaoPLinha = evolua();
			populacaoP = atualizaPopulacao();
			i++;
		}
		
		return this.getMelhorSolucao(sw.getEstacoes()); 
	}
}
