package com.revature.main;

import java.util.Scanner;

public class MainMenu {
	static int mainMenu() {
		Scanner input = new Scanner(System.in);
		System.out.println("\t\t *************Sanders Banking Application***************");
		System.out.println("\t1) Login");
		System.out.println("\t2) Sign Up");
		System.out.println("\t3) Exit");

		System.out.println("Enter you choice [1-3]:");

		int choice = input.nextInt();
		input.nextLine();
		return choice;
	}
}
