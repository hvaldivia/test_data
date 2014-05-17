package br.ita.bdic3;

import static org.junit.Assert.*;
import org.junit.Test;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bdic3Test{

	static Bdic3 bdi;
	
	@Test
	public void testGetUserData() {

		// Troque os valores abaixo por valores presentes no banco de dados
		String CLIENTE = "123.456.789-00";
		String DATA_INICIO = "01/04/2014";
		String DATA_FIM = "30/04/2014";
		
		String registros[];
		String campos[];
		Bdic3 bdi = new Bdic3(); 
		
		Date StartDate = new Date();
		Date EndDate = new Date();
		Date ThisDate = new Date();

		int i;
		
		// Converte as datas de inicio e fim para o formato nativo do Java
		try {
			StartDate = formataData(DATA_INICIO);
			EndDate = formataData(DATA_FIM);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// Busca por CPF em um período bem específico
		// Le os dados e separa cada linha em um vetor diferente
		registros=bdi.GetUserData(CLIENTE, DATA_INICIO, DATA_FIM).split("\r");	
		
		
		// Critérios para aceitação
		for(i=0;i<registros.length;i++) {
			
			// Separa os campos (0 = ID, 2 = Data, 4 = CPF, 6 = Valor)
			campos=registros[i].split(" | ");

			// Apenas os dados do cliente 123.456.789-00 são aceitos
			assertEquals("Cliente não confere com a solicitação",CLIENTE,campos[4]);
			
			// Apenas datas no intervalo são aceitas
			try {
				ThisDate = formataData(campos[2]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(ThisDate.compareTo(StartDate)<0 || ThisDate.compareTo(EndDate)>0) {
				fail("Data fora do intervalo");
			}
		}
		
	}
	
	/** 
     * Converte uma String para um objeto Date. Caso a String seja vazia ou nula,  
     * retorna null - para facilitar em casos onde formulários podem ter campos 
     * de datas vazios. 
     * @param data String no formato dd/MM/yyyy a ser formatada 
     * @return Date Objeto Date ou null caso receba uma String vazia ou nula 
     * @throws Exception Caso a String esteja no formato errado 
     */  
    public static Date formataData(String data) throws Exception {   
        if (data == null || data.equals(""))  
            return null;  
          
        Date date = null;  
        try {  
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
            date = (java.util.Date)formatter.parse(data);  
        } catch (ParseException e) {              
            throw e;
        }  
        return date;  
    }

}

