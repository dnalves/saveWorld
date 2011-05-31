
/**
 * Classe que representa uma forma de tentiva de salvr o mundo com a utilização
 * de algoritimo genético
 */
public class Tentativa1 extends AlgoritimoGenetico 
{
	
	public Tentativa1(SaveWorld sw) 
	{
		super(sw);
	}

	@Override
	/**
	 * Monta P com os melhores(com menor valor) individuos detre os de P e P'
	 */
	protected byte[][] atualizaPopulacao() 
	{
		byte[][] pop = populacaoP;
				
		int i = 0;
		boolean sair = false;
		Solucao piorP = null;
		Solucao melhorPLinha = null;		
		while((! sair) && (i < populacaoPLinha.length))	
		{
			melhorPLinha = ;
			piorP = ;
			if(melhorPLinha.getValor() < piorP.getValor())
				populacaoP[] = populacaoPLinha[];				
			else
			  sair = true;
			
			i++;	
		}
		
		return pop;
	}

	@Override
	/**
	 * Para cada individuo de P gera dois novos individuos. 
	 * um com a metade inicial da solucao original e com a segunda metade com valores invertidos
	 * o outro com a metade inicial invertida e a solucao original copiada.
	 */
	protected byte[][] evolua() 
	{
		byte[][] pop = new byte[INDIVIDUOS_POP_INICIAL * 2][sw.getNumEstacoes()];
		
		int k = 0;
		for (int i = 0; i < pop.length; i = i + 2) 
		{
			for (int j = 0; j < pop[i].length; j++) 
			{
				pop[i][j] = (j < sw.getNumEstacoes() / 2) ? populacaoP[k][j] : (populacaoP[k][j] == ESTACAO_AUSENTE ? ESTACAO_PRESENTE : ESTACAO_AUSENTE); 
				pop[i + 1][j] = (j > sw.getNumEstacoes() / 2) ? populacaoP[k][j] : (populacaoP[k][j] == ESTACAO_AUSENTE ? ESTACAO_PRESENTE : ESTACAO_AUSENTE);
			}			
		}
		
		return pop;
	}

	@Override
	/**
	 * Gera uma população inicial com tamanho default e com valores aletorios com chance de 50% 
	 */
	protected byte[][] geraPopulacaoInicial() 
	{
		byte[][] pop = new byte[INDIVIDUOS_POP_INICIAL][sw.getNumEstacoes()];
		
		for (int i = 0; i < pop.length; i++) 
		{
			for (int j = 0; j < pop[i].length; j++) 
			{
				pop[i][j] = Math.random() < 5 ? ESTACAO_AUSENTE : ESTACAO_PRESENTE;
			}
		}
		
		return pop;
	}

}
