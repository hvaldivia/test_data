package br.com.bdic3.util;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

/*
 * @author
 * Amal G Jose
 * 
 */

public class HiveJdbc {
	private static String driver = "org.apache.hadoop.hive.jdbc.HiveDriver";

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}

		Connection connect = DriverManager.getConnection(
				"jdbc:hive://:10000/default", "", "");
		Statement state = connect.createStatement();
		String tableName = "test";
		state.executeQuery("drop table " + tableName);
		ResultSet res = state.executeQuery("create table " + tableName
				+ " (key int, value string)");

		// Query to show tables
		String show = "show tables";
		System.out.println("Running: " + show);
		res = state.executeQuery(show);
		if (res.next()) {
			System.out.println(res.getString(1));
		}

		// Query to describe table
		String describe = "describe " + tableName;
		System.out.println("Running: " + describe);
		res = state.executeQuery(describe);
		while (res.next()) {
			System.out.println(res.getString(1) + "\t" + res.getString(2));
		}

	}
}
