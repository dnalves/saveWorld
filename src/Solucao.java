import java.util.ArrayList;


public class Solucao {

	private double valor;//custo total da solução
	private int total;//número de estações utilizadas na solução
	private ArrayList<String> estacoesSolucao;//estacoes escolhida como solucao
	private int[] representacao;//representação da solução
	
	/**
	 * Instancia uma nova solução.
	 * Já calcula o valor objetivo e o número de estações utilizadas.
	 *
	 * @param representacao um int[] com a representação da solução contendo 0s e 1s
	 */
	public Solucao(int[] representacao){
		
		Estacao[] estacao = new Estacao[representacao.length];
		
		valor = 0;
		total = 0;
		
		this.setRepresentacao(new int[representacao.length]);
		this.setRepresentacao(representacao.clone());
		
		for (int i = 0; i < representacao.length; i++) {
			if(representacao[i] == 1) {
				total++;
				valor += estacao[i].getCustoReal();
				estacoesSolucao.add(new String(estacao[i].getNome()));
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

	
}
