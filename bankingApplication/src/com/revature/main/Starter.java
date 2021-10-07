package com.revature.main;

import java.util.Scanner;

import com.revature.sandersBankingExceptions.UserCredentialsNotValid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Starter {

		private static Connection connection = null;
		private static Statement statement = null;
		private static PreparedStatement preparedStmt = null;
		private static ResultSet result = null;

		public static void connect() throws Exception {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingapplication", "root","SEG987undo65431@*$^");
		}

		public static int createAccount(String userName, String password) throws Exception {
			int insertStatus = 0;
			String insertQuery = "INSERT INTO account (`UserName`,`Password`) VALUES (?,?);";
			preparedStmt = connection.prepareStatement(insertQuery);

			preparedStmt.setString(1, userName);
			preparedStmt.setString(2, password);
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
			System.out.println("\t2) Sign in");
			System.out.println("\t3) Exit");

			System.out.println("Enter you choice [1-3]:");

			// }
			choice = input.nextInt();
			input.nextLine();

			 //input.close();
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

				//Scanner input3 = new Scanner(System.in);
				String userName = "";
				String password = "";
				String passwordReEnter = "";

				while (userName.isEmpty()) {
					System.out.println("Enter a username:");
					userName = input.nextLine();
					if (userName.isEmpty()) {
						System.out.println("This field may not be left empty\n");
					}
				}

				
				while (password.isEmpty()) {

					System.out.println("Enter a password:");
					password = input.nextLine();
					System.out.println("Re-enter password:");
					passwordReEnter = input.nextLine();

					if (password != "" && password == passwordReEnter) {

						System.out.println("Passwords are not matching. Please try again.");
					} else {
						Starter.createAccount(userName, password);
					}
				}
				break;
			case 3:
				System.out.println("Good Bye.");
				System.exit(0);
			
		}
	}
}
