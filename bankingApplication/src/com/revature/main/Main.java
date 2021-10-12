package com.revature.main;

import java.io.Console;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

import com.revature.entity.Client;

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
			console.printf("Password entered was: %s%n", new String(pwdArray));
			System.out.println("Enter your password:");
//			password = input.next();
//			char[] pwdArray = new char[password.length()];
//			  
//	        // Copy character by character into array
//	        for (int i = 0; i < password.length(); i++) {
//	            pwdArray[i] = password.charAt(i);
//	        }
			boolean validUser = Login.loginUser(Starter.statement, Starter.result, Starter.connection, userName, pwdArray, input);

			if (validUser) {
				Operations.operations(userName, password, input);	
			}
			break;
		case 2:

			// Scanner input3 = new Scanner(System.in);

			String firstName = "";
			String lastName = "";
			userName = "";
			password = "";
			//String passwordReEnter = "";
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
				firstName = input.nextLine();
				if (firstName.isEmpty()) {
					System.out.println("This is a required field.\n");
				}
			}

			client.setFirstName(firstName);

			while (lastName.isEmpty()) {
				System.out.println("Enter your last name: ");
				lastName = input.nextLine();
				if (lastName.isEmpty()) {
					System.out.println("This is a required field.\n");
				}
			}

			client.setLastName(lastName);

			while (email.isEmpty()) {
				System.out.println("Enter your email: ");
				email = input.nextLine();
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
				gender = input.nextLine();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			client.setGender(gender);

			System.out.println("What is your race? Leave this field blank if you wish not to answer.");
			race = input.nextLine();
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
				userName = input.nextLine();
				if (userName.isEmpty()) {
					System.out.println("This is a required field\n");
				}
			}

			client.setUserName(userName);
			char[] pwdArray1 = null;
			char[] pwdArrayReEnter = null;
			while (password.isEmpty()) {

				pwdArray1 = console.readPassword("Enter your password: ");
				console.printf("Password entered was: %s%n", new String(pwdArray1));
				pwdArrayReEnter = console.readPassword("Enter your password: ");
				console.printf("Password entered was: %s%n", new String(pwdArrayReEnter));
//				System.out.println("Enter a password: \n");
//				password = input.nextLine();
//				System.out.println("Re-enter password: \n");
//				passwordReEnter = input.nextLine();

				if (!pwdArray1.toString().isEmpty() && pwdArray1.toString() != pwdArrayReEnter.toString()) {

					System.out.println("Passwords are not matching. Please try again.");
				}
			}
			
//			// Creating array of string length
//	        char[] pwdArray2 = new char[password.length()];
//	  
//	        // Copy character by character into array
//	        for (int i = 0; i < password.length(); i++) {
//	            pwdArray2[i] = password.charAt(i);
//	        }
			
			
			client.setPassword(pa.hash(pwdArray1));
			System.out.println(client.toString());

			Starter.createClient(client);
			Starter.closeResource();
//			Starter obj = new Starter();
//			obj.insert("Client");
			
			break;
		case 3:
			System.out.println("Thank you for using our application.");
			System.exit(0);

		}
	}
//	
//	public static void main(String[] args) throws Exception {
//		new Starter().main(args);
//	}
}
