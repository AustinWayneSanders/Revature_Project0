package com.revature.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.revature.sandersBankingExceptions.UserCredentialsNotValid;

public class Login {
	
	private static int counter = 1;
	
	public static boolean loginUser(Statement statement, ResultSet result, Connection connection, String userName, String password, Scanner input) throws SQLException {
		boolean validCredentials = false;
		String readCredentialsQuery = "SELECT COUNT(*) AS Count FROM `client` WHERE `userName` = '" + userName
				+ "' AND `password` = '" + password + "';";
		statement = connection.createStatement();
		result = statement.executeQuery(readCredentialsQuery);
		result.next();

		while (counter < 4) {
			if (result.getInt("Count") == 1) {
				counter = 4;
				validCredentials = true;
			} else if (counter < 3 && counter > 0) {
				System.out.print("\nInvalid User.\n\nTry again.\n\n");
				System.out.println("Enter your username:");
				userName = input.next();
				System.out.println("Enter your password:");
				password = input.next();
				counter++;
				loginUser(statement, result, connection, userName, password, input);

				validCredentials = false;
			} else {
				System.out.print("Maximum Attempts Exceeded.\n");
				throw new UserCredentialsNotValid("Exceded maximum attempts.");
			}
		}

		return validCredentials;
	}
}
