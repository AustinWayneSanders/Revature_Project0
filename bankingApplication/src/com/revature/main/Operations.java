package com.revature.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.revature.display.DisplayAccounts;
import com.revature.entity.Transactions;

public class Operations {
	Transactions transaction = new Transactions();
	private static int accountNumber = 0;
	private static String transType = null;
	private static float amount = 0.00f;
	
	public static void operations(String userNameValidation, String passwordValidation, Scanner input) throws Exception {
		int choice = 1;
		int choice1;
		while (choice == 1) {
		System.out.println("Welcome " + Starter.userName(userNameValidation)
				+ ". What would you like to do?\n");
		System.out.println("\t1) Make a deposit");
		System.out.println("\t2) Make a withdrawl");
		System.out.println("\t3) Transfer funds");
		System.out.println("\t4) Display account balances");
		System.out.println("\t5) Open a new account");
		System.out.println("\t6) Log out\n");

		System.out.println("Enter your choice [1-5]:");

		choice1 = input.nextInt();
		input.nextLine();
		
		switch (choice1) {
		case 1:
			transType = "Deposit";
			Transaction.transactionalChoices(userNameValidation, accountNumber, amount, transType, input);
			System.out.print("Would you like to make another transaction?");
			System.out.print("\t1) Yes");
			System.out.print("\t2) No");
			choice = input.nextInt();
			choice1 = 0;
			break;
		case 2: 
			transType = "Withdraw";
			Transaction.transactionalChoices(userNameValidation, accountNumber, amount, transType, input);
			System.out.print("Would you like to make another transaction?");
			System.out.print("\t1) Yes");
			System.out.print("\t2) No");
			choice = input.nextInt();
			input.nextLine();
			choice1 = 0;
			break;
		
		case 3:
			
			transType = "Transfer";
			System.out.print("Select the account from which you would like to make the transfer: \n");
			System.out.println("\t1) Checking");
			System.out.println("\t2) Savings");
			System.out.println("\t3) Loan");
			System.out.println("\t4) Credit");
			
			String accountNameFrom = new String();
			int accountSelection1 = input.nextInt();
			
			switch (accountSelection1) {
			case 1: 
				accountNameFrom = "Checking";
				break;
			case 2: 
				accountNameFrom = "Savings";
				break;
			case 3:
				accountNameFrom = "Loan";
				break;
			case 4: 
				accountNameFrom = "Security Deposit";
				break;
			case 5: 
				accountNameFrom = "Credit";
				break;
			}
			
			System.out.print("Select the account to which you would like to make the transfer: \n");
			System.out.println("\t1) Checking");
			System.out.println("\t2) Savings");
			System.out.println("\t3) Loan");
			System.out.println("\t4) Credit");
			
			String accountNameTo = new String();
			int accountSelection2 = input.nextInt();
			
			switch (accountSelection2) {
			case 1: 
				accountNameTo = "Checking";
				break;
			case 2: 
				accountNameTo = "Savings";
				break;
			case 3:
				accountNameTo = "Loan";
				break;
			case 4: 
				accountNameTo = "Security Deposit";
				break;
			case 5: 
				accountNameTo = "Credit";
				break;
			}
			Transaction.transferFunds(userNameValidation, accountNumber, amount, accountNameFrom, accountNameTo, transType, Starter.statement, Starter.result, Starter.connection, input);
		break;	
		
		case 4: 
			DisplayAccounts displayAccounts = new DisplayAccounts();
			displayAccounts.displayAccountByClient("client", userNameValidation, Starter.statement, Starter.connection, Starter.result, Starter.rsmd);
			break;
		
		case 5:
			System.out.print("What type of account would you like to open?\n");
			System.out.print("\t1) Checking\n");
			System.out.print("\t2) Savings\n");
			System.out.print("\t3) Loan\n");
			System.out.print("\t4) Security Deposit\n");
			System.out.print("\t5) Credit\n");
			System.out.print("\t6) Return to previous menu\n");
			System.out.print("Enter your choice [1-6]");
			
			String accountName = new String();
			int accountSelection = input.nextInt();
			switch (accountSelection) {
			case 1: 
				accountName = "Checking";
				break;
			case 2: 
				accountName = "Savings";
				break;
			case 3:
				accountName = "Loan";
				break;
			case 4: 
				accountName = "Security Deposit";
				break;
			case 5: 
				accountName = "Credit";
				break;
			case 6: 
				operations(userNameValidation, passwordValidation, input);
				break;
			}
			
			CreateAccount.insertNewAccount(userNameValidation, accountName);
			
		case 6:
			Main.start();
			break;
		}
		if (choice ==2) {
		Main.start();
		}
		}
	}


}
