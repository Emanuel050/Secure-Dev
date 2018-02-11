package forms;

import java.io.Serializable;
import java.util.Date;

import exceptions.AeException;

public class ForumForm extends AeForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int msgLength = 500;
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	@Override
	public <T> FormValidator validateForm(Class<T> clazz, T form) throws AeException {
		FormValidator formValidator = FormValidator.startFieldsBasicValidation(clazz, form);
		formValidator.validateStringLength(this.msg, msgLength);
		return formValidator;
	}

}