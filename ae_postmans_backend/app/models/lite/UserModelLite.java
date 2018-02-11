package models.lite;

import java.io.File;
import java.io.Serializable;

import models.User;

public class UserModelLite implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String address;
	private String mail;
	private String phoneNumber;
	private byte[] passportPicture;
	
	public UserModelLite(User cusomter) {
		this.firstName = cusomter.getFirstName();
		this.lastName = cusomter.getLastName();
		this.mail = cusomter.getMail();
		this.phoneNumber = cusomter.getPhoneNumber();
		this.passportPicture = cusomter.getPassportPicture();
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public byte[] getPassportPicture() {
		return passportPicture;
	}

	public void setPassportPicture(byte[] passportPicture) {
		this.passportPicture = passportPicture;
	}

}
