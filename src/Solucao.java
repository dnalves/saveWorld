import java.util.ArrayList;


public class Solucao {

	private double valor;//custo total da solu��o
	private int total;//n�mero de esta��es utilizadas na solu��o
	private ArrayList<String> estacoesSolucao;//estacoes escolhida como solucao
	private int[] representacao;//representa��o da solu��o
	private ArrayList<Integer> pontosCobertos;
	private double numPontosCobertos;
	
	/**
	 * Instancia uma nova solu��o.
	 * J� calcula o valor objetivo e o n�mero de esta��es e quais foram utilizadas.
	 *
	 * @param representacao um int[] com a representa��o da solu��o contendo 0s e 1s
	 * @param sw TODO
	 */
	public Solucao(int[] representacao, SaveWorld sw){
		
		Estacao[] estacao = sw.getEstacoes();
		
		this.estacoesSolucao = new ArrayList<String>();
		this.pontosCobertos = new ArrayList<Integer>();
		
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
				this.estacoesSolucao.add(new String(estacao[i].getNome()));
				
				// Passa pelos pontos cobertos pela esta��o e testa
				// se j� est� no conjunto de pontos cobertos pela solu��o
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
	
	//produz s�da no formato especificado 
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

	public void setPontosCobertos(ArrayList<Integer> pontosCobertos) {
		this.pontosCobertos = pontosCobertos;
	}

	public ArrayList<Integer> getPontosCobertos() {
		return pontosCobertos;
	}

	public void setNumPontosCobertos(double numPontosCobertos) {
		this.numPontosCobertos = numPontosCobertos;
	}

	public double getNumPontosCobertos() {
		return numPontosCobertos;
	}

	
}
