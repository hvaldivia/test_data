package br.com.bdic3.util;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class HiveJdbcClient {
	private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		
		//Captura Entrada
		String cli_id = JOptionPane.showInputDialog("Qual é o seu nome?");
		String valor = JOptionPane.showInputDialog("Qual é o valor da compra?");
		
		
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}

		Connection con = DriverManager.getConnection(
				"jdbc:hive://localhost:10000/default", "", "");
		Statement stmt = con.createStatement();
		stmt.execute("use bdic3");
		
		float valor_max = 0;
		ResultSet res = stmt
				.executeQuery("select max(transacao.tra_total) from transacao where transacao.cli_id = "+ cli_id);
		
		while (res.next()) {
			valor_max = res.getFloat(1);
			System.out.println("valor_max = " + valor_max);
		}

		float first_quartile = valor_max / 4;
		System.out.println("first_quartile = " + first_quartile);
		
		float third_quartile = first_quartile * 3;
		System.out.println("third_quartile = " + third_quartile);
		
		float upper_limit = (float) (third_quartile + 1.5 * (third_quartile - first_quartile));
		System.out.println("upper_limit = " + upper_limit);
		
		if (Float.parseFloat(valor) >  upper_limit){
			JOptionPane.showConfirmDialog(null, "ATENÇÃO TRANSAÇÃO INCOMUN", " Você realizou esta compra " + cli_id + "?", 0);
		}
	}
}