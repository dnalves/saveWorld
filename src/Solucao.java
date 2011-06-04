import java.util.ArrayList;


public class Solucao {

	private double valor;//custo total da solução
	private int total;//número de estações utilizadas na solução
	private ArrayList<String> estacoesSolucao;//estacoes escolhidas como solucao
	private int[] representacao;//representação da solução (vetor de 0s e 1s)
	private int numPontosCobertos;//numero de pontos cobertos pela solução
	
	/**
	 * Instancia uma nova solução.
	 * Já calcula o valor objetivo,o número de estações e quais foram utilizadas.
	 * Calcula também o número de pontos cobertos pela solução.
	 *
	 * @param representacao um int[] com a representação da solução contendo 0s e 1s
	 * @param sw
	 */
	public Solucao(int[] representacao, SaveWorld sw){
		
		Estacao[] estacao = sw.getEstacoes();
		
		ArrayList<Integer> pontosCobertos;
		
		this.estacoesSolucao = new ArrayList<String>();
		pontosCobertos = new ArrayList<Integer>();
		
		valor = 0;
		total = 0;
		numPontosCobertos = 0;
		
		int[] pontos;
		Integer ponto;
		
		this.setRepresentacao(new int[representacao.length]);
		this.setRepresentacao(representacao.clone());
		
		for (int i = 0; i < representacao.length; i++) {
			if(representacao[i] == 1) {
				total++;
				valor += estacao[i].getCustoReal();
				//Adiciona estação coberta ao conjunto de estações da solução
				this.estacoesSolucao.add(new String(estacao[i].getNome()));
				
				// Passa pelos pontos cobertos pela estação e testa
				// se já está no conjunto de pontos cobertos pela solução
				pontos = estacao[i].getPontosCobertos();
				for (int pt : pontos) {
					ponto = Integer.valueOf(pt);
					if(!pontosCobertos.contains(ponto)){
						pontosCobertos.add(new Integer(pt));
						this.numPontosCobertos++;
						
					}
				}
				
			}
		}
		
	}
	
	//produz sída no formato especificado 
	public void showResult() 
	{
		System.out.println("Valor: " + this.valor);
		System.out.println("Total: " + this.total);
		
		for (String string : estacoesSolucao) {
			System.out.println(string);
		}

	}
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public ArrayList<String> getEstacoesSolucao() {
		return estacoesSolucao;
	}
	public void setEstacoesSolucao(ArrayList<String> estacoesSolucao) {
		this.estacoesSolucao = estacoesSolucao;
	}

	public void setRepresentacao(int[] representacao) {
		this.representacao = representacao;
	}

	public int[] getRepresentacao() {
		return representacao;
	}

	public void setNumPontosCobertos(int numPontosCobertos) {
		this.numPontosCobertos = numPontosCobertos;
	}

	public int getNumPontosCobertos() {
		return numPontosCobertos;
	}

	
}
