package com.revature.main;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.entity.Transactions;

public class Operations {
	Transactions transaction = new Transactions();
	private static int accountNumber = 0;
	private static String transType = null;
	private static float amount = 0.00f;
	private static Date TransTimeStamp = null;
	
	public static void operations(String userNameValidation, String passwordValidation, Scanner input) throws Exception {
		int choice = 1;
		int choice1;
		while (choice == 1) {
		System.out.println("Welcome " + Starter.userName(userNameValidation, passwordValidation)
				+ ". What would you like to do?\n");
		System.out.println("\t1) Make a deposit");
		System.out.println("\t2) Make a withdrawl");
		System.out.println("\t3) Open a new account");
		System.out.println("\t4) Log out\n");

		System.out.println("Enter your choice [1-4]:");

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
//			input.nextLine();
//			switch (choice2) {
//			case 1:
//				Transaction.transactionalChoices(userNameValidation, accountNumber, amount, transType, input);
//				break;
//			case 2:
//				MainMenu.mainMenu();
//				break;
//			}
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
//			switch (choice2) {
//			case 1:
//				Transaction.transactionalChoices(userNameValidation, accountNumber, amount, transType, input);
//				break;
//			case 2:
//				MainMenu.mainMenu();
//				break;
//			}
			break;
		case 4:
			MainMenu.start();
			break;
		}
		if (choice ==2) {
		MainMenu.start();
		}
		}
	}


}
