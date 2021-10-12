package com.revature.main;


import com.revature.entity.Client;
import com.revature.entity.Account;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public class Starter {
	
	private static final SecureRandom random = new SecureRandom();
	static Connection connection = null;
	static Statement statement = null;
	static PreparedStatement preparedStmt = null;
	static ResultSet result = null;
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

		int updateStatus = 0;
		if (balance >= 0) {
			updateStatus = preparedStmt.executeUpdate();
		}

		return updateStatus;
	}

	public static String userName(String userName) throws SQLException {
		String readName = "SELECT `firstName` FROM `client` WHERE `userName` = '" + userName+ "'";
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
		
//		byte[] salt = new byte[16];
//		random.nextBytes(salt);
//		KeySpec spec = new PBEKeySpec("password".toCharArray(), salt, 65536, 128);
//		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//		byte[] hash = f.generateSecret(spec).getEncoded();
//		Base64.Encoder enc = Base64.getEncoder();
//		System.out.printf("salt: %s%n", enc.encodeToString(salt));
//		System.out.printf("hash: %s%n", enc.encodeToString(hash));
		
		
		Starter.connect();
		MainMenu.start();
	}
}
