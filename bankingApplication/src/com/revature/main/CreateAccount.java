package com.revature.main;

import java.sql.SQLException;

import com.revature.entity.Account;

public class CreateAccount {

	private static Account account = new Account();
	public static void insertNewAccount(String userName, String accountName) throws SQLException {
		String findClientID = "(SELECT ClientID FROM client WHERE UserName = '" + userName + "');";
		System.out.print(findClientID);
		Starter.statement = Starter.connection.createStatement();
		Starter.result = Starter.statement.executeQuery(findClientID);
		Starter.result.next();
		
		int clientId = Starter.result.getInt("ClientID");
		
		
		String findAccountTypeId = "(SELECT AccountTypeId FROM accounttype WHERE AccountName = '" + accountName + "');";
		System.out.print(findAccountTypeId);
		Starter.statement = Starter.connection.createStatement();
		Starter.result = Starter.statement.executeQuery(findAccountTypeId);
		
		Starter.result.next();
		int accountTypeId = Starter.result.getInt("AccountTypeId");
		
		account.setBalance(0);
		account.setAccountType(accountTypeId);
		account.setClientID(clientId);
		
		String insertQuery = "INSERT INTO account (`Balance`, `ClientID`, `AccountTypeId`) VALUES (?,?,?);";
		System.out.print(insertQuery);
		Starter.preparedStmt = Starter.connection.prepareStatement(insertQuery);

		Starter.preparedStmt.setFloat(1, account.getBalance());
		Starter.preparedStmt.setInt(2, account.getClientID());
		Starter.preparedStmt.setInt(3, account.getAccountType());
		
		Starter.preparedStmt.executeUpdate();
	}
}
