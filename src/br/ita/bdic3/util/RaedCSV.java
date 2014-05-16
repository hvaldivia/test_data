package br.ita.bdic3.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RaedCSV {

	public static void main(String[] args) {

		//RaedCSV obj = new RaedCSV();
		//obj.generateInsertLocalidade();
		System.out.println("fdfsdf");

	}

	public void generateInsertLocalidade() {

		String csvFile = "/home/cloudera/Desktop/ce240-2/Localidade.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";

		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] tmpLoc = line.split(cvsSplitBy);
				System.out.println("INSERT INTO BDIC3.LOCALIDADE (loc_id, loc_latitude, loc_longitude, loc_endereco, loc_cidade, loc_estado, loc_pais, loc_cep) VALUES ("
						+ tmpLoc[0] + ", " + tmpLoc[1].replaceAll(",", ".") + ", " + tmpLoc[2].replaceAll(",", ".")
						+ ", '" + tmpLoc[3] + "', '" + tmpLoc[4].replaceAll("'", "`") + "', '"
						+ tmpLoc[5] + "', '" + tmpLoc[6] + "', '" + tmpLoc[7]
						+ "');");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}