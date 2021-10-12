package com.revature.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.revature.entity.Client;
import com.revature.entity.Transactions;
import com.revature.entity.Account;
import com.revature.entity.AccountType;
import com.revature.sandersBankingExceptions.UserCredentialsNotValid;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Starter {

	static Connection connection = null;
	static Statement statement = null;
	static PreparedStatement preparedStmt = null;
	static ResultSet result = null;
	private static java.sql.ResultSetMetaData rsmd = null;
	private static Scanner input = new Scanner(System.in);

	public static void connect() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingapplication", "root",
				"SEG987undo65431@*$^");
	}

	public static int createClient(Client client) throws Exception {
		int insertStatus = 0;
		String insertQuery = "INSERT INTO client (`FirstName`, `LastName`, `UserName`,`Password`,"
				+ "`Email`, `Age`, `Gender`, `Race`, `Street`, `City`, `State`, "
				+ "`PostalCode`, `DateJoined`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";

		preparedStmt = connection.prepareStatement(insertQuery.toString());

		preparedStmt.setString(1, client.getFirstName());
		preparedStmt.setString(2, client.getLastName());
		preparedStmt.setString(3, client.getUserName());
		preparedStmt.setString(4, client.getPassword());
		preparedStmt.setString(5, client.getEmail());
		preparedStmt.setInt(6, client.getAge());
		preparedStmt.setString(7, client.getGender());
		preparedStmt.setString(8, client.getRace());
		preparedStmt.setString(9, client.getStreet());
		preparedStmt.setString(10, client.getCity());
		preparedStmt.setString(11, client.getState());
		preparedStmt.setInt(12, client.getPostalCode());
		preparedStmt.setDate(13, client.getDateJoined());
		insertStatus = preparedStmt.executeUpdate();

		System.out.println("Account created.");

		return insertStatus;
	}

	public static void insert(String tableName) {
		List<String> columnNames = new ArrayList<String>();
		List<String> columnValues = new ArrayList<String>();
		StringBuffer insertQuery = new StringBuffer("insert into ");
		insertQuery.append(tableName).append(" (");
		try {
			statement = connection.createStatement();
			String query = "select * from " + tableName;
			result = statement.executeQuery(query);
			rsmd = result.getMetaData();
			int columnCount = rsmd.getColumnCount();
			StringBuffer columns = new StringBuffer("");
			int i = 0;
			String columnName = null;
			String value = null;
			for (i = 1; i < columnCount; i++) {
				columnName = rsmd.getColumnName(i);
				columnNames.add(columnName);
				columns.append(columnName).append(",");
				System.out.print("Enter for " + columnName + ":");
				value = input.next();
				columnValues.add(value);
			}
			columnName = rsmd.getColumnName(i);
			columnNames.add(columnName);
			columns.append(columnName).append(") values (");
			System.out.print("Enter for " + columnName + ":");
			value = input.next();
			columnValues.add(value);
			for (i = 1; i < columnCount; i++) {
				if (i == 1)
					columns.append(columnValues.get(0) + ",");
				else
					columns.append("'" + columnValues.get(i - 1) + "',");
			}
			columns.append("'" + columnValues.get(i - 1) + "');");
			insertQuery.append(columns);

			System.out.println("insertQuery =" + insertQuery);
			preparedStmt = connection.prepareStatement(insertQuery.toString());
//			pstmt.setString(1, tableName);
			System.out.println("column values :" + columnValues);
//			for (i = 2; i <= columnCount+1; i++) {
//				pstmt.setString(i, columnValues.get(i-2));
//			}
			preparedStmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}


	public static int updateBalance(Account account, float amount, int accountNumber, String transType)
			throws Exception {
		String balanceQuery = "SELECT `Balance` FROM `account` WHERE `AccountNumber` = " + accountNumber + ";";
		System.out.print(balanceQuery);
		statement = connection.createStatement();
		result = statement.executeQuery(balanceQuery);
		result.next();
		float balance = result.getInt("Balance");

		if (transType == "Deposit") {
			balance = balance + amount;
		} else if (transType == "Withdraw") {
			balance = balance - amount;
		}

		String updateQuery = "UPDATE `account` SET `Balance` = " + balance + " WHERE `AccountNumber` = " + accountNumber
				+ ";"; // creating a query
		System.out.print(updateQuery);
		preparedStmt = connection.prepareStatement(updateQuery); // creating prepared Statement
//		preparedStmt.setFloat(1, account.getBalance());
//		preparedStmt.setInt(2, account.getAccountNumber());

		int updateStatus = 0;
		if (balance >= 0) {
			updateStatus = preparedStmt.executeUpdate();
		}

		return updateStatus;
	}

	public static String userName(String userName, String password) throws SQLException {
		String readName = "SELECT `firstName` FROM `client` WHERE `userName` = '" + userName + "' AND password = '"
				+ password + "';";
		statement = connection.createStatement();
		result = statement.executeQuery(readName);
		result.next();

		return result.getString("firstName");
	}

	public static void closeResource() throws Exception {
		if (result != null) {
			result.close();
		}
		if (preparedStmt != null) {
			preparedStmt.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (connection != null) {
			connection.close();
		}

	}

	public static void main(String[] args) throws Exception {

		Starter.connect();
		MainMenu.start();
	}
}
