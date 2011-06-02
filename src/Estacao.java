
/**
 * Classe responsavel por armzaenar informa��es da esta��o  
 */
public class Estacao 
{
	
	private String nome;
	private double custoReal; 
	private int[] pontosCobertos;
	
	public Estacao(String nome, double custoReal, int[] pontosCobertos) {
		super();
		this.nome = nome;
		this.custoReal = custoReal;
		this.pontosCobertos = pontosCobertos;
	}
	
	@Override
	public Estacao clone(){
		Estacao estacao = new Estacao(this.nome, this.custoReal, this.pontosCobertos);
		return estacao;
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getCustoReal() {
		return custoReal;
	}

	public void setCustoReal(double custoReal) {
		this.custoReal = custoReal;
	}

	public int[] getPontosCobertos() {
		return pontosCobertos;
	}

	public void setPontosCobertos(int[] pontosCobertos) {
		this.pontosCobertos = pontosCobertos;
	}
		
}
