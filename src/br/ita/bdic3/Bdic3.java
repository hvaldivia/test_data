package br.ita.bdic3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Humberto
 *
 */

public class Bdic3 {


	
	/**
	 * Este e´ um stub apenas para suportar as classes de teste do JUnit
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
    
		BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(System.in));
		String sCPF = "";
		String sDateStart = "";
		String sDateEnd = "";
		
		System.out.print("Digite o CPF do cliente:");
		try {
			sCPF = inputBuffer.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("Digite a data de inicio da busca (DD/MM/YYYY):");
		try {
			sDateStart = inputBuffer.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("Digite a data de fim da busca (DD/MM/YYYY):");
		try {
			sDateEnd = inputBuffer.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String Output = GetUserData(sCPF, sDateStart, sDateEnd);
 
		System.out.println("Processando... \n\rFim");
	    
		System.out.println("ID    | Data  |  CPF  | Valor");
        System.out.println(Output);
        
	}
	
	/**
	 * Este metodo é um stub para o método que fará a pesquisa no BD.
	 * 
	 * @param sCPF
	 * @param sDateStart
	 * @param sDateEnd
	 * @return Retorna uma string com o resultado da busca, no formato " ID | Data | CPF | Valor " 
	 */
	public static String GetUserData(String sCPF, String sDateStart, String sDateEnd) {
		
		int i;
		String sOutput;
			
		sOutput=""; // Valor inicial
		for(i=0;i<20;i++) {
			sOutput=sOutput + String.valueOf(i);
			sOutput=sOutput + " | " + sDateStart + " | " + sCPF + " | ";
			sOutput=sOutput + String.valueOf(i*123.45) + "\r";
		}
		
		return sOutput;
		
		
	}

}

