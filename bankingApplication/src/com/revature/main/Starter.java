package com.revature.main;

import java.util.Scanner;

import com.revature.entity.Client;
import com.revature.sandersBankingExceptions.UserCredentialsNotValid;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Starter {

	private static Connection connection = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStmt = null;
	private static ResultSet result = null;

	public static void connect() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingapplication", "root",
				"SEG987undo65431@*$^");
	}

	public static int createClient(Client client) throws Exception {
		int insertStatus = 0;
		String insertQuery = "INSERT INTO account (`FirstName, `LastName`, `UserName`,`Password`,"
				+ "`Email`, `Age`, `Gender`, `Race`, `Street`, `City`, `State`, "
				+ "`PostalCode`, `DateJoined`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
		preparedStmt = connection.prepareStatement(insertQuery);

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
		preparedStmt.setDate(13, (java.sql.Date) client.getDateJoined());
		insertStatus = preparedStmt.executeUpdate();

		System.out.println("Account created.");

		return insertStatus;
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
		// TODO Auto-generated method stub
//			String name = "Austin";
//			String userName = "austin";
//			String password = "password";
		Scanner input = new Scanner(System.in);
		Starter.connect();
		int choice = 0;
		// while (choice >=0) {
		System.out.println("\t\t *************Sanders Banking Application***************");
		System.out.println("\t1) Login");
		System.out.println("\t2) Sign Up");
		System.out.println("\t3) Exit");

		System.out.println("Enter you choice [1-3]:");

		// }
		choice = input.nextInt();
		input.nextLine();

		// input.close();
		switch (choice) {
		case 1:

			// Scanner input2 = new Scanner(System.in);

//				System.out.println("Enter your username:");
//				String userNameValidation = input.next();
//				System.out.println("Enter your password:");
//				String passwordValidation = input.next();
//
//				if (userNameValidation.equals(userName) && passwordValidation.equals(password)) {
//					System.out.println("Welcome " + name + ". What would you like to do?\n");
//					System.out.println("\t1) Make a deposit");
//					System.out.println("\t2) Make a withdrawl");
//					System.out.println("\t3) Exit\n");
//
//					System.out.println("Enter you choice [1-3]:");
//
//					choice = input.nextInt();
//				} else {
//					input.close();
//					throw new UserCredentialsNotValid("User credentials entered are not valid. /n Please try again.");
//				}

			break;
		case 2:

			// Scanner input3 = new Scanner(System.in);

			String firstName = "";
			String lastName = "";
			String userName = "";
			String password = "";
			String passwordReEnter = "";
			String email = "";
			int age = 0;
			String gender = "";
			String race = "";
			String street = "";
			String city = "";
			String state = "";
			int postalCode = 0;
			Date dateJoined = new Date();
			
			

			Client client = new Client();

			client.setDateJoined(dateJoined);
			
			while (firstName.isEmpty()) {
				System.out.print("Enter your first name: ");
				firstName = input.nextLine();
				if (firstName.isEmpty()) {
					System.out.print("This is a required field.\n");
				}
			}
			
			client.setFirstName(firstName);

			while (lastName.isEmpty()) {
				System.out.println("Enter your last name: ");
				lastName = input.nextLine();
				if (lastName.isEmpty()) {
					System.out.print("This is a required field.\n");
				}
			}
			
			client.setLastName(lastName);

			while (email.isEmpty()) {
				System.out.print("Enter your email: ");
				email = input.nextLine();
				if (email.isEmpty()) {
					System.out.print("This is a required field\n");
				}
			}
			
			client.setEmail(email);

			while (age == 0) {
				System.out.print("Enter your age:");
				age = input.nextInt();
				if (age >= 0 && age < 18) {
					System.out.print("You need to be at least 18 to sign up.\n");
				} else if (age < 0) {
					System.out.print("This entry is not a valid age.");
				} else if (age == 0) {
					System.out.print("This is a required field.");
				}
			}
			
			client.setAge(age);
			
			System.out.print("What is your gender? Leave this field blank if you wish not to answer.");
			userName = input.nextLine();
			client.setGender(gender);
			
			System.out.print("What is your race? Leave this field blank if you wish not to answer.");
			race = input.nextLine();
			client.setUserName(race);
			
			while (street.isEmpty()) {
				System.out.print("Enter your street address: ");
				street = input.nextLine();
				if (street.isEmpty()) {
					System.out.print("This is a required field\n");
				}
			}
			
			client.setStreet(street);
			
			while (city.isEmpty()) {
				System.out.print("Enter your city: ");
				city = input.nextLine();
				if (city.isEmpty()) {
					System.out.print("This is a required field\n");
				}
			}
			
			client.setCity(city);

			while (state.isEmpty()) {
				System.out.print("Enter your state: ");
				state = input.nextLine();
				if (state.isEmpty()) {
					System.out.print("This is a required field\n");
				}
			}
			
			client.setState(state);
			
			while (postalCode == 0) {
				System.out.print("Enter your postal code: ");
				postalCode = input.nextInt();
				if (postalCode == 0) {
					System.out.print("This is a required field\n");
				} else if (postalCode < 0) {
					System.out.print("This is not a valid postal code");
				}
			}
			
			client.setPostalCode(postalCode);

			while (userName.isEmpty()) {
				System.out.print("Enter your username: ");
				userName = input.nextLine();
				if (userName.isEmpty()) {
					System.out.print("This is a required field\n");
				}
			}
			
			client.setUserName(userName);
			
			while (password.isEmpty()) {

				System.out.println("Enter a password: \n");
				password = input.nextLine();
				System.out.println("Re-enter password: \n");
				passwordReEnter = input.nextLine();

				if (password != "" && password == passwordReEnter) {

					System.out.println("Passwords are not matching. Please try again.");
				} 
			}
			
			client.setPassword(password);
			
			
			Starter.createClient(client);
			break;
		case 3:
			System.out.println("Thank you for using our application.");
			System.exit(0);

		}
	}
}
