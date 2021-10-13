package com.revature.sandersproject0.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class Login {

	//Password Authentication is a class used to authenticate the password the user submitted. 
	static PasswordAuthentication pa = new PasswordAuthentication();
	static String token = new String();

		//If the returned boolean value from this method is true, then the user is verified as a valid user.
		static boolean loginUser(Statement statement, ResultSet result, Connection connection, String userName,
			char[] pwdArray, Scanner input) throws SQLException {
		boolean validCredentials = false;
		
//		
//		String cntUser = "SELECT COUNT(*) AS Cnt FROM `client` WHERE `userName` = '" + userName + "';";
//		
//		statement = connection.createStatement();
//		result = statement.executeQuery(cntUser);
//		//result.next();
//		int cnt = result.getInt("Cnt");
//		if (cnt == 1 ) {
		//The query readCredentials return the hashed password associated with the userName provided. 
		String readCredentialsQuery = "SELECT `Password` AS pwd FROM `client` WHERE `userName` = '" + userName + "';";

		statement = connection.createStatement();
		result = statement.executeQuery(readCredentialsQuery);
		
		if (result.next()) {
			token = result.getString("pwd");
			
			//The returned hashed password from the readCredentials is passed to the authenticate method from 
			// the PasswordAuthentication class to verify the password. 
			validCredentials = pa.authenticate(pwdArray, token);

		}else {
			System.out.print("Invalid User");
		}
			return validCredentials;
	}
}