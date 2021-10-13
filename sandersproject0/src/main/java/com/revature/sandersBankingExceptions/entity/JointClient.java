package com.revature.sandersBankingExceptions.entity;

// Entity class for the joint client. 

import java.sql.Date;

public class JointClient {

	private int clientId;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;	
	private String email;
	private int age;
	private String gender;
	private String race;
	private String street;
	private String city;
	private String postalCode;
	private Date dateJoined; 
	private int primaryClientId;
	
	public JointClient() {
		super();
	}

	public JointClient(int clientId, String firstName, String lastName, String userName, String password, String email,
			int age, String gender, String race, String street, String city, String postalCode, Date dateJoined,
			int primaryClientId) {
		super();
		this.clientId = clientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.age = age;
		this.gender = gender;
		this.race = race;
		this.street = street;
		this.city = city;
		this.postalCode = postalCode;
		this.dateJoined = dateJoined;
		this.primaryClientId = primaryClientId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Date getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(Date dateJoined) {
		this.dateJoined = dateJoined;
	}

	public int getPrimaryClientId() {
		return primaryClientId;
	}

	public void setPrimaryClientId(int primaryClientId) {
		this.primaryClientId = primaryClientId;
	}

	@Override
	public String toString() {
		return "JointClient [clientId=" + clientId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", userName=" + userName + ", password=" + password + ", email=" + email + ", age=" + age
				+ ", gender=" + gender + ", race=" + race + ", street=" + street + ", city=" + city + ", postalCode="
				+ postalCode + ", dateJoined=" + dateJoined + ", primaryClientId=" + primaryClientId + "]";
	}
	
	
}
