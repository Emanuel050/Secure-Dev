package forms;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import play.mvc.Controller;
import db_repositories.RepositoryUtils;
import exceptions.AeException;
import exceptions.AeMessage;
import exceptions.AeMessages;
import exceptions.MessageType;
import models.User;
import play.mvc.Result;
import utils.AppUtils;
import utils.FormUtils;
import utils.MessageListener;
import views.html.helper.input;

public class FormValidator {
	private MessageListener messageListener = new MessageListener();
	
	  protected FormValidator validateField(String input){
		  if (!FormUtils.validateSqlInjection(input)) {
			  messageListener.addMessage(MessageType.ERROR, AeMessages.INVALID_CHARACTERS);
		  }
		  
		  return this;
	  }
	  
	protected FormValidator validateMailField(String mailInput) {
		  if (!FormUtils.validateMail(mailInput)) {
			  messageListener.addMessage(MessageType.ERROR, AeMessages.INVALID_MAIL_ENTERED);
		  }
		  return this;
	 }
	
	protected FormValidator validateStringLength(String inpunt,int length) {
		  if (inpunt.length()> length) 
		  {
			  messageListener.addMessage(MessageType.ERROR, AeMessages.STR_TO_LONGG);
		  }
		  return this;
	 }
	
	  public FormValidator validateCsrfAttack(AeForm form) {
		  if (!form.getCsrfToken().equals(Controller.session("csrfToken"))) {
			  messageListener.addMessage(MessageType.ERROR, AeMessages.CSRF_ATTACK);
		  }
		  return this;
	  }
	
	public static <T> FormValidator startFieldsBasicValidation(Class<T> clazz, T object) throws AeException {
		FormValidator formValidator = new FormValidator();
		
		Field [] formFields = object.getClass().getDeclaredFields();
		//For each field in form
		for (Field field :formFields) {
			try {
				field.setAccessible(true);
				//read field value
				String fieldValue = String.valueOf(field.get(object));
				if (StringUtils.isNotBlank(fieldValue)) {
					//run validation field method
					formValidator.validateField(fieldValue);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new AeException(new AeMessage(MessageType.ERROR, AeMessages.INVALID_VALUE_IN_FORM, field.getName(), field.getType()));
			}
		}
		
		return  formValidator;
	}
	
	
	public MessageListener getMessages() {
		return messageListener;
	}
	
	public Result getResult(Object returnOnSuccess) {
		return messageListener.getResult(returnOnSuccess);
	}
	
	public FormValidator CheckUserExists(String mail, String password) {
		if (!AppUtils.checkIfValidUser(mail, password)) {
			messageListener.addMessage(MessageType.ERROR, AeMessages.USER_OR_PASSWORD_INVALID);
		}

		return this;
	}
	
	protected FormValidator CheckUserNotExists(String mail) {
		User user = RepositoryUtils.getEntityByUniqueFieldValue(User.class, "mail", mail);
		
		if (user != null) {
			messageListener.addMessage(MessageType.ERROR, AeMessages.USER_MAIL_ALREADY_EXIST, mail);
		}

		return this;
	}

	public void checkPasswords(String password, String passwordConfirm) {
	   	if (!password.matches("(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%&*()_+=|<>?{}\\[\\]~-]).{8,12}")) {
			messageListener.addMessage(MessageType.ERROR, AeMessages.PASSWORD_TOO_SHORT, "8-12 legnth, 1 Letter, 1 Number, 1 Special char");
	   	}
	
		if (!password.equals(passwordConfirm)) {
			messageListener.addMessage(MessageType.ERROR, AeMessages.PASSWORDS_NOT_EQUALS);
		}
	}

}
