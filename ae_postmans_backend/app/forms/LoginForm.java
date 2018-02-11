package forms;

import java.io.Serializable;

import exceptions.AeException;

public class LoginForm extends AeForm implements Serializable{
	private static final long serialVersionUID = 1L;
	  private String email =""; 
	  private String password ="";
	
	  /** Required for form instantiation. */
	  public LoginForm() {
	
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

	@Override
	public <T> FormValidator validateForm(Class<T> clazz, T form) throws AeException {
		//get instance of form validation
		FormValidator formValidator = FormValidator.startFieldsBasicValidation(clazz, form);
		
		//validate fields
		formValidator.validateMailField(getEmail());
		if (formValidator.getMessages().isFailed()){
			return formValidator;
		}
		
		formValidator.CheckUserExists(getEmail(), getPassword());
		
		return formValidator;		
	}


	
	  
}

