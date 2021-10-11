package com.revature.main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

import com.revature.entity.Account;
import com.revature.entity.Transactions;

public class Transaction {
	private static Transactions transactions = new Transactions();
	public static int updateTransactions(PreparedStatement preparedStmt, Connection connection) throws Exception {
		
		int insertStatus = 0;
		String insertQuery = "INSERT INTO transactions (`AccountNumber`,`TransType`, `Amount`, `TransTimeStamp`) VALUES (?,?,?,?);";
		System.out.print(insertQuery);
		preparedStmt = connection.prepareStatement(insertQuery);

		preparedStmt.setInt(1, transactions.getAccountNumber());
		preparedStmt.setString(2, transactions.getTransType());
		preparedStmt.setFloat(3, transactions.getAmount());
		preparedStmt.setDate(4, transactions.getTransTimeStamp());
		insertStatus = preparedStmt.executeUpdate();
		return insertStatus;
	}
	
	public static void applyTransaction(String userNameValidation, int accountNumber,
			float amount, String accountName, String transType, Statement statement, ResultSet result, Connection connection, Scanner input) throws Exception {
		String accountNumberQuery = "SELECT AccountNumber FROM bankingapplication.account "
				+ "WHERE AccountTypeId = (SELECT AccountTypeId FROM accounttype WHERE AccountName = '" + accountName
				+ "') " + "AND ClientID = (SELECT ClientID FROM client WHERE UserName = '" + userNameValidation + "');";
		System.out.print(accountNumberQuery);
		statement = connection.createStatement();
		result = statement.executeQuery(accountNumberQuery);
		// System.out.print(accountNumber);

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
				updateTransactions(Starter.preparedStmt, Starter.connection);
				Account account = new Account();

				int status = Starter.updateBalance(account, amount, accountNumber, transType);

				if (status == 1) {
					System.out.print("Transaction Successful!\n");
				} else if (status == 0) {
					System.out.print("We were unable to process this transaction. Insufficent funds.\n");
				}
			}
		}
	}

	public static void transactionalChoices(String userNameValidation, int accountNumber,
			float amount, String transType, Scanner input) throws Exception {

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
			applyTransaction(userNameValidation, accountNumber, amount, "Checking", transType, Starter.statement, Starter.result, Starter.connection, input);
			break;
		case 2:
			applyTransaction(userNameValidation, accountNumber, amount, "Savings", transType, Starter.statement, Starter.result, Starter.connection, input);
			break;
		case 3:
			applyTransaction(userNameValidation, accountNumber, amount, "Loan", transType, Starter.statement, Starter.result, Starter.connection, input);
			break;
		case 4:
			applyTransaction(userNameValidation, accountNumber, amount, "Credit", transType, Starter.statement, Starter.result, Starter.connection, input);
			break;
		case 5:
			Starter.closeResource();
			System.exit(0);
			break;
		}

	}
	
}
