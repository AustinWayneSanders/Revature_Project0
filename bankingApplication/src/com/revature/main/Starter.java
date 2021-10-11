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
		//int choice = 0;
		MainMenu.start();
//		switch (choice) {
//
//		case 1:
//			System.out.println("Enter your username:");
//			String userName = input.nextLine();
//			System.out.println("Enter your password:");
//			String password = input.nextLine();
//			Boolean validUser = Login.loginUser(statement, result, connection, userName, password, input);
//
//			if (validUser) {
//				Operations.operations(userName, password, input);	
//			}
//
//		case 2:
//
//			// Scanner input3 = new Scanner(System.in);
//
//			String firstName = "";
//			String lastName = "";
//			userName = "";
//			password = "";
//			String passwordReEnter = "";
//			String email = "";
//			int age = 0;
//			String gender = "";
//			String race = "";
//			String street = "";
//			String city = "";
//			String state = "";
//			int postalCode = 0;
//			Date dateJoined = Date.valueOf(LocalDate.now());
//
//			Client client = new Client();
//
//			client.setDateJoined(dateJoined);
//
//			while (firstName.isEmpty()) {
//				System.out.println("Enter your first name: ");
//				firstName = input.nextLine();
//				if (firstName.isEmpty()) {
//					System.out.println("This is a required field.\n");
//				}
//			}
//
//			client.setFirstName(firstName);
//
//			while (lastName.isEmpty()) {
//				System.out.println("Enter your last name: ");
//				lastName = input.nextLine();
//				if (lastName.isEmpty()) {
//					System.out.println("This is a required field.\n");
//				}
//			}
//
//			client.setLastName(lastName);
//
//			while (email.isEmpty()) {
//				System.out.println("Enter your email: ");
//				email = input.nextLine();
//				if (email.isEmpty()) {
//					System.out.println("This is a required field\n");
//				}
//			}
//
//			client.setEmail(email);
//
//			while (age == 0) {
//				System.out.println("Enter your age:");
//				age = input.nextInt();
//				input.nextLine();
//				if (age >= 0 && age < 18) {
//					age = 0;
//					System.out.println("You need to be at least 18 to sign up.\n");
//				} else if (age < 0) {
//					age = 0;
//					System.out.println("This entry is not a valid age.");
//				} else if (age == 0) {
//					System.out.println("This is a required field.");
//				}
//			}
//
//			client.setAge(age);
//
//			System.out.println("What is your gender? Leave this field blank if you wish not to answer.");
//			try {
//				gender = input.nextLine();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			client.setGender(gender);
//
//			System.out.println("What is your race? Leave this field blank if you wish not to answer.");
//			race = input.nextLine();
//			client.setRace(race);
//
//			while (street.isEmpty()) {
//				System.out.println("Enter your street address: ");
//				street = input.nextLine();
//				if (street.isEmpty()) {
//					System.out.println("This is a required field\n");
//				}
//			}
//
//			client.setStreet(street);
//
//			while (city.isEmpty()) {
//				System.out.println("Enter your city: ");
//				city = input.nextLine();
//				if (city.isEmpty()) {
//					System.out.println("This is a required field\n");
//				}
//			}
//
//			client.setCity(city);
//
//			while (state.isEmpty()) {
//				System.out.println("Enter your state: ");
//				state = input.nextLine();
//				if (state.isEmpty()) {
//					System.out.println("This is a required field\n");
//				}
//			}
//
//			client.setState(state);
//
//			while (postalCode == 0) {
//				System.out.println("Enter your postal code: ");
//				postalCode = input.nextInt();
//				input.nextLine();
//				if (postalCode == 0) {
//					System.out.println("This is a required field\n");
//				} else if (postalCode < 0) {
//					System.out.println("This is not a valid postal code");
//				}
//			}
//
//			client.setPostalCode(postalCode);
//
//			while (userName.isEmpty()) {
//				System.out.println("Enter your username: ");
//				userName = input.nextLine();
//				if (userName.isEmpty()) {
//					System.out.println("This is a required field\n");
//				}
//			}
//
//			client.setUserName(userName);
//
//			while (password.isEmpty()) {
//
//				System.out.println("Enter a password: \n");
//				password = input.nextLine();
//				System.out.println("Re-enter password: \n");
//				passwordReEnter = input.nextLine();
//
//				if (password != "" && password == passwordReEnter) {
//
//					System.out.println("Passwords are not matching. Please try again.");
//				}
//			}
//
//			client.setPassword(password);
//			System.out.println(client.toString());
//
//			Starter.createClient(client);
//			Starter.closeResource();
////			Starter obj = new Starter();
////			obj.insert("Client");
//			break;
//		case 3:
//			System.out.println("Thank you for using our application.");
//			System.exit(0);
//
//		}
	}
}
