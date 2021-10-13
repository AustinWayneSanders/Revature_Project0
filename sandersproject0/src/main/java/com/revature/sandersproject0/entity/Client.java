package com.revature.sandersproject0.entity;

//Entity class for the client table

import java.sql.Date;

public class Client {

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
	private String state;
	private int postalCode;
	private Date dateJoined; 

	public Client() {
		super();
	}

	public Client(int clientId, String firstName, String lastName, String userName, String password, String email,
			int age, String gender, String race, String street, String city, String state, int postalCode, Date dateJoined) {
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
		this.state = state;
		this.postalCode = postalCode;
		this.dateJoined = dateJoined;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public Date getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(Date dateJoined) {
		this.dateJoined = dateJoined;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", password=" + password + ", email=" + email + ", age=" + age + ", gender=" + gender
				+ ", race=" + race + ", street=" + street + ", city=" + city + ", state=" + state + ", postalCode=" + postalCode
				+ ", dateJoined=" + dateJoined + "]";
	}

	
}