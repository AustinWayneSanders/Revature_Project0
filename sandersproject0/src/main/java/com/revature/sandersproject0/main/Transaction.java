package com.revature.sandersproject0.main;

//This class provides methods for manipulating financial data. 

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

import com.revature.sandersBankingExceptions.entity.Account;
import com.revature.sandersBankingExceptions.entity.Transactions;
import com.revature.sandersproject0.App;

public class Transaction {
	private static Transactions transactions = new Transactions();

	static int updateTransactions(PreparedStatement preparedStmt, Connection connection) throws Exception {

		int insertStatus = 0;
		String insertQuery = "INSERT INTO transactions (`AccountNumber`,`TransType`, `Amount`, `TransTimeStamp`) VALUES (?,?,?,?);";
	
		preparedStmt = connection.prepareStatement(insertQuery);

		preparedStmt.setInt(1, transactions.getAccountNumber());
		preparedStmt.setString(2, transactions.getTransType());
		preparedStmt.setFloat(3, transactions.getAmount());
		preparedStmt.setDate(4, transactions.getTransTimeStamp());
		insertStatus = preparedStmt.executeUpdate();
		return insertStatus;
	}

	static void applyTransaction(String userNameValidation, int accountNumber, float amount, String accountName,
			String transType, Statement statement, ResultSet result, Connection connection, Scanner input)
			throws Exception {

		String accountNumberQuery = "SELECT AccountNumber FROM bankingapplication.account "
				+ "WHERE AccountTypeId = (SELECT AccountTypeId FROM accounttype WHERE AccountName = '" + accountName
				+ "') " + "AND ClientID = (SELECT ClientID FROM client WHERE UserName = '" + userNameValidation + "');";
		
		statement = connection.createStatement();
		result = statement.executeQuery(accountNumberQuery);
	

		if (result.next() == false) {
			System.out.println("You do not have an existing " + accountName.toLowerCase() + " account.");
		} else {
			accountNumber = result.getInt("AccountNumber");
			transactions.setAccountNumber(accountNumber);
			transactions.setTransType(transType);
			transactions.setTransTimeStamp(Date.valueOf(LocalDate.now()));

			while (amount == 0.00) {
				System.out.println("Enter the amount to " + transType + " : ");
				amount = input.nextFloat();
				input.nextLine();
				if (amount == 0.00) {
					System.out.println("This is a required field\n");
				}
				transactions.setAmount(amount);
				updateTransactions(App.preparedStmt, App.connection);
				Account account = new Account();

				int status = App.updateBalance(account, amount, accountNumber, transType);

				if (status == 1) {
					System.out.print("Transaction Successful!\n");
				} else if (status == 0) {
					System.out.print("We were unable to process this transaction. Insufficent funds.\n");
				}

			}

		}

	}

	//Gives the user choices of the accounts to which he/she could make a deposit. Transactions will only be applied
	//to account that the user currently has opened. 
	static void transactionalChoices(String userNameValidation, int accountNumber, float amount,
			String transType, Scanner input) throws Exception {
		System.out.println("Select the account where you would like to make the " + transType.toLowerCase() + ":");
		System.out.println("\t1) Checking");
		System.out.println("\t2) Savings");
		System.out.println("\t3) Loan");
		System.out.println("\t4) Credit");
		System.out.println("\t5) Exit");

		System.out.println("Make your choice [1-5]");
		int choice2 = input.nextInt();

		switch (choice2) {
		case 1:
			applyTransaction(userNameValidation, accountNumber, amount, "Checking", transType, App.statement,
					App.result, App.connection, input);
			break;
		case 2:
			applyTransaction(userNameValidation, accountNumber, amount, "Savings", transType, App.statement,
					App.result, App.connection, input);
			break;
		case 3:
			applyTransaction(userNameValidation, accountNumber, amount, "Loan", transType, App.statement,
					App.result, App.connection, input);
			break;
		case 4:
			applyTransaction(userNameValidation, accountNumber, amount, "Credit", transType, App.statement,
					App.result, App.connection, input);
			break;
		case 5:
			App.closeResource();
			System.exit(0);
			break;
		}

	}

	//This methods transfers funds between two specified accounts: accountFrom and accountTo
	static void transferFunds(String userNameValidation, int accountNumber, float amount, String accountNameFrom,
			String accountNameTo, String transType, Statement statement, ResultSet result, Connection connection,
			Scanner input) throws Exception {
		int status = 0;
		//This query is used to verify that both the accounts exists before preceeding forward with the transaction. 
		String accountNumberQuery1 = "SELECT COUNT(AccountNumber) AS NumAccounts FROM bankingapplication.account "
				+ "WHERE AccountTypeId in (SELECT AccountTypeId FROM accounttype WHERE AccountName = '"
				+ accountNameFrom + "' OR AccountName = '" + accountNameTo + "') "
				+ "AND ClientID = (SELECT ClientID FROM client WHERE UserName = '" + userNameValidation + "');";
	
		statement = connection.createStatement();
		result = statement.executeQuery(accountNumberQuery1);
		result.next();

		if (result.getInt("NumAccounts") != 2) { //If the number of accounts does not equal two, then transfer between two accounts is not possible,
												// therefore, the logic is caught here to avoid roll backs.    
			System.out.println("At least one of the accounts you selected does not exist.");
		} else if (result.getInt("NumAccounts") == 2) {
			String accountNumberQuery2 = "SELECT AccountNumber FROM bankingapplication.account "
					+ "WHERE AccountTypeId = (SELECT AccountTypeId FROM accounttype WHERE AccountName = '"
					+ accountNameFrom + "') " + "AND ClientID = (SELECT ClientID FROM client WHERE UserName = '"
					+ userNameValidation + "');";
			
			statement = connection.createStatement();
			result = statement.executeQuery(accountNumberQuery2);
			result.next();
			accountNumber = result.getInt("AccountNumber");
			transactions.setAccountNumber(accountNumber);
			transactions.setTransType(transType);
			transactions.setTransTimeStamp(Date.valueOf(LocalDate.now()));

			while (amount == 0.00) {
				System.out.println("Enter the amount to " + transType + ": ");
				amount = input.nextFloat();
				input.nextLine();
				if (amount == 0.00) {
					System.out.println("This is a required field\n");
				}
				transactions.setAmount(amount);
				updateTransactions(App.preparedStmt, App.connection);
				Account account = new Account();

				
				//Once the two accounts have been verified and sufficient funds exists with the withdraw, the application will 
				//update the accountFrom balance with a withdraw. 
				status = App.updateBalance(account, amount, accountNumber, "Withdraw");

				
				//The status variable is a flag to determine if the withdraw with accountFrom is processed successfully, else the transfer will not be committed. 
				if (status == 1) {
					String accountNumberQuery3 = "SELECT AccountNumber FROM bankingapplication.account "
							+ "WHERE AccountTypeId = (SELECT AccountTypeId FROM accounttype WHERE AccountName = '"
							+ accountNameTo + "') " + "AND ClientID = (SELECT ClientID FROM client WHERE UserName = '"
							+ userNameValidation + "');";
					
					statement = connection.createStatement();
					result = statement.executeQuery(accountNumberQuery3);
					result.next();
					accountNumber = result.getInt("AccountNumber");
					transactions.setAccountNumber(accountNumber);
					transactions.setTransType(transType);
					transactions.setTransTimeStamp(Date.valueOf(LocalDate.now()));
					transactions.setAmount(amount);
					updateTransactions(App.preparedStmt, App.connection);
					App.updateBalance(account, amount, accountNumber, "Deposit");
					System.out.print("Transaction Successful!\n");
				} else if (status == 0) {
					System.out.print("We were unable to process this transaction. Insufficient funds.\n");
				}

			}

		}
	}

}
