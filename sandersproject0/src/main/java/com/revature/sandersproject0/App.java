
/*
 * This file is the entry point for project0. It includes a main method which calls the connections() method which establishes the 
 * connection with MySQL database used in the back end for this application. The main method also calls start() which is 
 * responsible for the "Start Up" menu that includes a login, sign up, and exit option.   
 */





package com.revature.sandersproject0;


import com.revature.sandersBankingExceptions.entity.Account;
import com.revature.sandersBankingExceptions.entity.Client;
import com.revature.sandersproject0.main.Main;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App 
{

	/*
	 * The following App instances are relating to MySQL driver methods. 
	 * They are all set to public static to allow communication with other 
	 * Classes outside of this package. 
	 * */
	public static Connection connection = null;
	public static Statement statement = null;
	public static PreparedStatement preparedStmt = null;
	public static ResultSet result = null;
	public static ResultSetMetaData rsmd = null;
	public static void connect() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingapplication", "root",
				"SEG987undo65431@*$^");
	}

	
	//This method is called when a user would like to Sign up or register for this application
	public static int createClient(Client client) throws Exception {
		int insertStatus = 0;
		String insertQuery = "INSERT INTO client (`FirstName`, `LastName`, `UserName`,`Password`,"
				+ "`Email`, `Age`, `Gender`, `Race`, `Street`, `City`, `State`, "
				+ "`PostalCode`, `DateJoined`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";

		preparedStmt = connection.prepareStatement(insertQuery.toString());

		//The getter setter methods below follow the standard OOP procedure for passing data to 
		//the Client entity or table in MySQL. 
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
	
	// The follow method is called for applying a deposit or withdraw changes to logged in user's specified account. 
	// It is public to allow classes outside of this package to communicate with this method. 
	// This is used for transactions. 
	public static int updateBalance(Account account, float amount, int accountNumber, String transType)
			throws Exception {
		String balanceQuery = "SELECT `Balance` FROM `account` WHERE `AccountNumber` = " + accountNumber + ";";
		
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
				+ ";"; 
		
		preparedStmt = connection.prepareStatement(updateQuery); 

		
		int updateStatus = 0;
		
		//This prevents the user from making an overdraft to their account. The different from a 
		//withdraw must be greater than 0. 
		if (balance >= 0) {
			updateStatus = preparedStmt.executeUpdate();
		}
		
		return updateStatus;
	}

	
	// This method is used to ensure transactions are applied to the logged in user. It is public as it is 
	// used with serveral methods outside of this package. 
	public static String userName(String userName) throws SQLException {
		String readName = "SELECT `firstName` FROM `client` WHERE `userName` = '" + userName+ "'";
		statement = connection.createStatement();
		result = statement.executeQuery(readName);
		result.next();

		return result.getString("firstName");
	}

	//Closes are resources relating to MySQL connections. 
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
		
		App.connect();
		Main.start();
	}
}
