package com.revature.display;

import com.revature.main.Starter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import com.revature.main.Starter;

public class DisplayAccounts {

	

	public static void getById(String tableName, int id, Connection conn, Statement stmt, ResultSet rs, ResultSetMetaData rsmd) {
		try {
			stmt = conn.createStatement();
			String query1 = "select * from " + tableName + " where ClientID= " + id;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query1);
			
			rsmd = (ResultSetMetaData) rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				System.out.print(rsmd.getColumnName(i) + " \t\t");
			}
			System.out.print("\n");
			if (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(rs.getString(i) + "\t\t\t");
				}
				System.out.print("\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public void displayAccountByClient(String tableName, String userName, Statement stmt, Connection conn, ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
		
		String query1 = "select ClientID from " +tableName + " where userName = '"+ userName + "';";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query1);
		rs.next();
		int id = rs.getInt("ClientID");

		getById("account", id, conn, stmt, rs, rsmd);
		
	}
}
