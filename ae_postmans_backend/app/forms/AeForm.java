package forms;

import java.io.Serializable;

import exceptions.AeException;

public abstract class AeForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String csrfToken;
	public abstract <T> FormValidator validateForm(Class<T> clazz, T form) throws AeException;
	
	
	public String getCsrfToken() {
		return csrfToken;
	}
	public void setCsrfToken(String csrfToken) {
		this.csrfToken = csrfToken;
	}
}
