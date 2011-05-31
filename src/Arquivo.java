import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 *Classe responsvel por carregar as inform��es d inst�ncia do problema
 */
public class Arquivo 
{

	private String nomeArquivo;

	public Arquivo(String nomeArquivo) {
		super();
		this.nomeArquivo = nomeArquivo;
	}
	
	/**
	 * Cria o vetor de int com os pontos cobertos de uma estacao
	 * @param linha linha de descricao de est��o do arquivo de entrada
	 * @return vetor com os pontos cobertos pela estacao
	 */
	private int[] getPontosCobertos(String[] linha)
	{
		int[] retorno = new int[linha.length-2];
		
		for (int i = 2; i < linha.length; i++)//2 � onde come�am os pontos cobertos 
		{
			retorno[i - 2] = Integer.parseInt(linha[i]);
		}
		
		return retorno;
	}
	
	/**
	 * Carrega os dados de uma inst�ncia do problema de um arquivo
	 * @return Objeto com os dados.
	 */
	public SaveWorld loadDados()
	{
	    SaveWorld sw = new SaveWorld();
	    
	    try 
	    {	          	    	 
	   	  String linha;
	      String[] aux;
	      BufferedReader in = new BufferedReader(new FileReader(this.nomeArquivo));
	      
	      //l� numero de pontos
	      if((linha = in.readLine()) != null)
	      {
	        aux = linha.split(" ");  	       
	        sw.setNumPontos(Integer.parseInt(aux[1]));
	      }
	      
	      //l� numero de estacoes
	      if((linha = in.readLine()) != null)
	      {
	        aux = linha.split(" ");  	       
	        sw.setNumEstacoes(Integer.parseInt(aux[1]));
	      }
	      
	      //l� as estcoes
	      Estacao[] estacoes = new Estacao[sw.getNumEstacoes()];
	      for (int i = 0; i < sw.getNumEstacoes(); i++) 
	      {
	    	  linha = in.readLine();		      
	    	  aux = linha.split(" ");  	       
	    	  int[] pontosCobertos = this.getPontosCobertos(aux);	    	  
	    	  estacoes[i] = new Estacao(aux[0], Float.parseFloat(aux[1]), pontosCobertos); 
	      }	      
	      sw.setEstacoes(estacoes);
	     
	      in.close();
	    }
	    catch (IOException e) 
	    {
			// TODO: handle exception
		}

	    return sw;	    	
	}
	
}
