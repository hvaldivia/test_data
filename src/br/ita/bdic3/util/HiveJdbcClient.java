package br.ita.bdic3.util;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class HiveJdbcClient {

	private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
	private Connection con;
	private Statement stmt;

	public HiveJdbcClient() {
		con = null;
		stmt = null;
	}

	public boolean connectionHive() throws SQLException {

		// Connection with HIVE
		try {
			// ABIR UM TERMINAL E EXECUTAR
			// # hive --service hiveserver
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}

		con = DriverManager.getConnection(
				"jdbc:hive://localhost:10000/default", "", "");
		stmt = con.createStatement();
		return stmt.execute("use bdic3");

	}

	public float consultaHive(int cli_id) throws SQLException {
		float valor_max = 0;

		try {
			ResultSet res = stmt
					.executeQuery("select max(transacao.tra_total) from transacao where transacao.cli_id = "
							+ cli_id);
			while (res.next()) {
				valor_max = res.getFloat(1);
				System.out.println("valor_max = " + valor_max);
			}
		} catch (Exception e) {
			System.out.println("ERRO consulta hive: " + e);
			e.printStackTrace();
			System.exit(1);
		}

		return valor_max;
	}

}