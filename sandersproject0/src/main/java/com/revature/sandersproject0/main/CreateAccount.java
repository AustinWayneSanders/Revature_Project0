package com.revature.sandersproject0.main;

import java.sql.SQLException;

import com.revature.sandersproject0.App;
import com.revature.sandersproject0.entity.Account;

public class CreateAccount {

	private static Account account = new Account();
	
	// This method is used to add a account requested from the user logged in. It has a default access modifier since it is only accessed 
	// my classes within this package. 
		static void insertNewAccount(String userName, String accountName) throws SQLException {
		String findClientID = "(SELECT ClientID FROM client WHERE UserName = '" + userName + "');";

		App.statement = App.connection.createStatement();
		App.result = App.statement.executeQuery(findClientID);
		App.result.next();
		
		int clientId = App.result.getInt("ClientID");
		
		
		String findAccountTypeId = "(SELECT AccountTypeId FROM accounttype WHERE AccountName = '" + accountName + "');";
		
		App.statement = App.connection.createStatement();
		App.result = App.statement.executeQuery(findAccountTypeId);
		
		App.result.next();
		int accountTypeId = App.result.getInt("AccountTypeId");
		
		
		//This query is executed to ensure that the account that the user requested to be created does not already exist. 
		String findExistingAccount = "Select COUNT(*) as cnt FROM account WHERE clientId = "+ clientId + " AND accountTypeId = "+ accountTypeId+";";
		
		App.statement = App.connection.createStatement();
		App.result = App.statement.executeQuery(findExistingAccount);
		App.result.next();
		
		int count = App.result.getInt("cnt");
		
		//If the account already exists, the count of records with the "findExistingAccount" query would be greater than or equal to 1. 
		if (count == 0) {//Having a count equal to 0 implies that the an existing account does not already exist. If count equals 0, the 
						//program will proceed to creating a new account. 
		
		account.setBalance(0);
		account.setAccountType(accountTypeId);
		account.setClientID(clientId);
		
		String insertQuery = "INSERT INTO account (`Balance`, `ClientID`, `AccountTypeId`) VALUES (?,?,?);";
		App.preparedStmt = App.connection.prepareStatement(insertQuery);

		App.preparedStmt.setFloat(1, account.getBalance());
		App.preparedStmt.setInt(2, account.getClientID());
		App.preparedStmt.setInt(3, account.getAccountType());
		
		App.preparedStmt.executeUpdate();
		
		}else {
			System.out.print("We are unable to process this request. You already have an exsiting " + accountName.toLowerCase() + " account.\n\n\n" );
		}
		
		
		
	}
}
