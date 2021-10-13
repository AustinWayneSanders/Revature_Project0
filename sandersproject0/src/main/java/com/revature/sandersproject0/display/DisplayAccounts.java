package com.revature.sandersproject0.display;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.result.ResultSetMetaData;


public class DisplayAccounts {

	public static void getById(String tableName, int id, Connection conn, Statement stmt, ResultSet rs,
			ResultSetMetaData rsmd) {
		try {
			stmt = conn.createStatement();
			String query1 = "select * from " + tableName + " where ClientID= " + id;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query1);
			String columnName = new String();
			rsmd = (ResultSetMetaData) rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				columnName = rsmd.getColumnName(i);
				System.out.printf("%s %" + (columnName.length() - 25) + "s", columnName, " ");
			}
			System.out.print("\n");
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					String value = rs.getString(i);
					if (value.contains(".00")) {
						System.out.printf("$" + "%s %" + (value.length() - 25) + "s", value, " ");
					} else {
						System.out.printf("%s %" + (value.length() - 25) + "s", value, " ");
					}

				}
				System.out.print("\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	public void displayAccountByClient(String tableName, String userName, Statement stmt, Connection conn, ResultSet rs,
			ResultSetMetaData rsmd) throws SQLException {

		String query1 = "select ClientID from " + tableName + " where userName = '" + userName + "';";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query1);
		rs.next();
		int id = rs.getInt("ClientID");

		getById("account", id, conn, stmt, rs, rsmd);

	}

	public static void displayTransactionsbyClientId(String userName, Statement stmt, Connection conn, ResultSet rs,
			ResultSetMetaData rsmd) throws SQLException {

		String query1 = "Select * from transactions where AccountNumber in (select AccountNumber from account where ClientID in (Select ClientID From client where userName = '"
				+ userName + "')) ORDER BY TransactionID;";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query1);
			String columnName = new String();
			rsmd = (ResultSetMetaData) rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				columnName = rsmd.getColumnName(i);
				System.out.printf("%s %" + (columnName.length() - 20) + "s", columnName, " ");
			}
			System.out.print("\n");
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					String value = rs.getString(i);
					if (value.contains(".00")) {
						System.out.printf("$" + "%s %" + (value.length() - 20) + "s", value, " ");
					} else {
						System.out.printf("%s %" + (value.length() - 20) + "s", value, " ");
					}

				}
				System.out.print("\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
