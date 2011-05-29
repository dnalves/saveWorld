
public class Solucao {

	private double valor;//custo total da solução
	private int total;//número de estações utilizadas na solução
	private String[] estacoesSolucao;//estacoes escolhida como solucao
	
	public Solucao(double valor, int total, String[] estacoesSolucao) {
		super();
		this.valor = valor;
		this.total = total;
		this.estacoesSolucao = estacoesSolucao;
	}
	
	//produz sída no formato especificado 
	public void showResult() 
	{
		System.out.println("Valor: " + this.valor);
		System.out.println("Total: " + this.total);
		for (int i = 0; i < this.estacoesSolucao.length; i++) 
		{
			System.out.println(this.estacoesSolucao[i]);
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
	public String[] getEstacoesSolucao() {
		return estacoesSolucao;
	}
	public void setEstacoesSolucao(String[] estacoesSolucao) {
		this.estacoesSolucao = estacoesSolucao;
	}

	
}
