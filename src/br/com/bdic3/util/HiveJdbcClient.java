package br.com.bdic3.util;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class HiveJdbcClient {
	private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
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
		ResultSet res = stmt.executeQuery("select * from localidade");
		while (res.next()) {
			System.out.println(res.getString(1));
		}

	}
}