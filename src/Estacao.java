
/**
 * Classe responsavel por armzaenar informações da estação  
 */
public class Estacao 
{
	
	private String nome;
	private float custoReal; 
	private int[] pontosCobertos;
	
	public Estacao(String nome, float custoReal, int[] pontosCobertos) {
		super();
		this.nome = nome;
		this.custoReal = custoReal;
		this.pontosCobertos = pontosCobertos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getCustoReal() {
		return custoReal;
	}

	public void setCustoReal(float custoReal) {
		this.custoReal = custoReal;
	}

	public int[] getPontosCobertos() {
		return pontosCobertos;
	}

	public void setPontosCobertos(int[] pontosCobertos) {
		this.pontosCobertos = pontosCobertos;
	}
		
}
