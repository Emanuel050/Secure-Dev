package forms;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import constants.Gender;
import exceptions.AeException;
import models.User;


public class SignupForm extends AeForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private String firstName;
	private String lastName;
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

	public SignupForm() {
	}
	
	public SignupForm(User user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getMail();
		this.phoneNumber = user.getPhoneNumber();
		this.gender = user.getGender();
		this.birthDate = user.getBirthDate();
		this.passportPicture = user.getPassportPicture();
		this.type = User.getUser();
	}
	
	@Override
	public <T> FormValidator validateForm(Class<T> clazz, T form) throws AeException {
		FormValidator formValidator = FormValidator.startFieldsBasicValidation(clazz, form);
		formValidator.validateMailField(getEmail());
		formValidator.CheckUserNotExists(getEmail());
		formValidator.checkPasswords(getPassword(), getPasswordConfirm());
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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
}