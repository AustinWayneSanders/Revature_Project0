package com.revature.sandersproject0.main;

import java.io.Console;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

import com.revature.sandersproject0.App;
import com.revature.sandersproject0.entity.Client;


//This class acts as the "main menu" for the application to allow the user to Login, Sign up, or Exit the program. 

public class Main {
	
	
	public static void start() throws Exception {
		
		Console console = System.console();
		if (console == null) {
			System.out.println("Couldn't get Console instance");
			System.exit(0);
		}
		
		PasswordAuthentication pa = new PasswordAuthentication();
		String password = new String();
		Scanner input = new Scanner(System.in);
		System.out.println(" *************Sanders Banking Application***************");
		System.out.println("\t1) Login");
		System.out.println("\t2) Sign Up");
		System.out.println("\t3) Exit");
		System.out.println(" ********************************************************");
		
		System.out.println("Enter you choice [1-3]:");

		int choice = input.nextInt();
		//input.nextLine();
		switch(choice) 
		{

		case 1:
			System.out.println("Enter your username:");
			String userName = input.next();
			char[] pwdArray = console.readPassword("Enter your password: ");

			boolean validUser = Login.loginUser(App.statement, App.result, App.connection, userName, pwdArray, input);
			if (validUser) {
				Operations.operations(userName, password, input);	
			}
			break;
		case 2:

			
			String firstName = "";
			String lastName = "";
			userName = "";
			password = "";
			String email = "";
			int age = 0;
			String gender = "";
			String race = "";
			String street = "";
			String city = "";
			String state = "";
			int postalCode = 0;
			Date dateJoined = Date.valueOf(LocalDate.now());

			Client client = new Client();

			client.setDateJoined(dateJoined);

			while (firstName.isEmpty()) {
				System.out.println("Enter your first name: ");
				firstName = input.next();
				if (firstName.isEmpty()) {
					System.out.println("This is a required field.\n");
				}
			}

			client.setFirstName(firstName);

			while (lastName.isEmpty()) {
				System.out.println("Enter your last name: ");
				lastName = input.next();
				if (lastName.isEmpty()) {
					System.out.println("This is a required field.\n");
				}
			}

			client.setLastName(lastName);

			while (email.isEmpty()) {
				System.out.println("Enter your email: ");
				email = input.next();
				if (email.isEmpty()) {
					System.out.println("This is a required field\n");
				}
			}

			client.setEmail(email);

			while (age == 0) {
				System.out.println("Enter your age:");
				age = input.nextInt();
				input.nextLine();
				if (age >= 0 && age < 18) {
					age = 0;
					System.out.println("You need to be at least 18 to sign up.\n");
				} else if (age < 0) {
					age = 0;
					System.out.println("This entry is not a valid age.");
				} else if (age == 0) {
					System.out.println("This is a required field.");
				}
			}

			client.setAge(age);

			System.out.println("What is your gender? Leave this field blank if you wish not to answer.");
			try {
				gender = input.next();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			client.setGender(gender);

			System.out.println("What is your race? Leave this field blank if you wish not to answer.");
			race = input.next();
			client.setRace(race);

			while (street.isEmpty()) {
				System.out.println("Enter your street address: ");
				street = input.nextLine();
				if (street.isEmpty()) {
					System.out.println("This is a required field\n");
				}
			}

			client.setStreet(street);

			while (city.isEmpty()) {
				System.out.println("Enter your city: ");
				city = input.nextLine();
				if (city.isEmpty()) {
					System.out.println("This is a required field\n");
				}
			}

			client.setCity(city);

			while (state.isEmpty()) {
				System.out.println("Enter your state: ");
				state = input.nextLine();
				if (state.isEmpty()) {
					System.out.println("This is a required field\n");
				}
			}

			client.setState(state);

			while (postalCode == 0) {
				System.out.println("Enter your postal code: ");
				postalCode = input.nextInt();
				input.nextLine();
				if (postalCode == 0) {
					System.out.println("This is a required field\n");
				} else if (postalCode < 0) {
					System.out.println("This is not a valid postal code");
				}
			}

			client.setPostalCode(postalCode);

			while (userName.isEmpty()) {
				System.out.println("Enter your username: ");
				userName = input.next();
				if (userName.isEmpty()) {
					System.out.println("This is a required field\n");
				}
			}

			client.setUserName(userName);
			char[] pwdArray1 =  console.readPassword("Enter your password: ");
			char[] pwdArrayReEnter = console.readPassword("Re-Enter your password: ");
			while (!Arrays.equals(pwdArray1, pwdArrayReEnter)) {

				pwdArray1 = console.readPassword("Enter your password: ");
				pwdArrayReEnter = console.readPassword("Re-Enter your password: ");
				
			}
					
			client.setPassword(pa.hash(pwdArray1));
			App.createClient(client);
			App.closeResource();
			
			break;
		case 3:
			System.out.println("Thank you for using our application.");
			System.exit(0);

		}
	}

}
