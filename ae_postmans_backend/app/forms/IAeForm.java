package forms;

import exceptions.AeException;

public interface IAeForm {

	public <T> FormValidator validateForm(Class<T> clazz, T form) throws AeException;
}
