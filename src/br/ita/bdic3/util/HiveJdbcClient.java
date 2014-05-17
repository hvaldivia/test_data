package br.ita.bdic3.util;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class HiveJdbcClient {

	private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
	private String sqlClienteTransacao = "SELECT cliente.cli_nome, cliente.cli_cpf, transacao.tra_data, transacao.tra_total FROM cliente JOIN transacao WHERE transacao.cli_id = cliente.cli_id";
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

	public void consultaClienteCpf(String cpf) throws SQLException {
		System.out.println("PROCESSANDO");
		try {
			ResultSet res = stmt.executeQuery(sqlClienteTransacao
					+ " AND cliente.cli_cpf = " + cpf);
			System.out.println("CLIENTE NOME|    CPF   | DATA |VALOR   ");
			while (res.next()) {
				System.out.println(res.getString(1) + "  |  "
						+ res.getString(2) + "  |  " + res.getString(3)
						+ "  |  " + res.getFloat(4));
			}
		} catch (Exception e) {
			System.out.println("ERRO consulta hive: " + e);
			e.printStackTrace();
			System.exit(1);
		}

	}

	public void consultaClienteData(String cli_cpf, String data)
			throws SQLException {
		System.out.println("PROCESSANDO");
		try {
			ResultSet res = stmt.executeQuery(sqlClienteTransacao
					+ " AND transacao.tra_data = '" + data + "'"
					+ " AND cliente.cli_cpf = "+ cli_cpf);
			System.out.println("CLIENTE NOME|    CPF   | DATA |VALOR   ");
			while (res.next()) {
				System.out.println(res.getString(1) + "  |  "
						+ res.getString(2) + "  |  " + res.getString(3)
						+ "  |  " + res.getFloat(4));
			}
		} catch (Exception e) {
			System.out.println("ERRO consulta hive: " + e);
			e.printStackTrace();
			System.exit(1);
		}

	}

}