package forms;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import constants.Gender;
import exceptions.AeException;
import models.User;


public class UpdateUserForm extends AeForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final String USER = "User";
	private String firstName;
	private String lastName;
	private String currentEmail;
	private String email;
	private String oldPassword;
	private String password;
	private String passwordConfirm;
	private String phoneNumber;
	private Gender gender;
	private Date birthDate;
	private String type;
	
	@JsonIgnore
	private byte[] passportPicture ;

	public UpdateUserForm() {
	}
	
	public UpdateUserForm(User user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getMail();
		this.phoneNumber = user.getPhoneNumber();
		this.gender = user.getGender();
		this.birthDate = user.getBirthDate();
		this.passportPicture = user.getPassportPicture();
		this.type =  this.USER;
	}
	
	@Override
	public <T> FormValidator validateForm(Class<T> clazz, T form) throws AeException {
		FormValidator formValidator = FormValidator.startFieldsBasicValidation(clazz, form);
		if(!this.currentEmail.equals(this.email))
		{
			formValidator.validateMailField(getNewEmail());
			formValidator.CheckUserNotExists(getNewEmail());
		}
		
		if(!this.oldPassword.isEmpty() && !this.password.isEmpty() && !this.passwordConfirm.isEmpty())
		{
			formValidator.checkPasswords(getPassword(), getPasswordConfirm());
		}	
		return formValidator;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}


	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}


	public byte[]  getPassportPicture() {
		return passportPicture;
	}


	public void setPassportPicture(byte[]  passportPicture) {
		this.passportPicture = passportPicture;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCurrentEmail() {
		return currentEmail;
	}

	public void setCurrentEmail(String currentEmail) {
		this.currentEmail = currentEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNewEmail() {
		return email;
	}

	public void setNewEmail(String newEmail) {
		this.email = newEmail;
	}

}