package utils;

import org.mindrot.jbcrypt.BCrypt;

import db_repositories.RepositoryUtils;
import models.User;

public class AppUtils {
	
	public static <T> boolean hasCause(Throwable throwable, Class<T> clazz) {
		Throwable cause = throwable.getCause();
		while (cause != null) {
			if (cause.getCause().getClass().equals(clazz)){
				return true;
			}
			
			cause = cause.getCause();
		}
		
		return false;
	}
	
	public static <T> T updateIfChanged(T oldValue, T newValue) {
		if (oldValue == null) {
			if (newValue != null) {
				return newValue;
			}
		} else if (!oldValue.equals(newValue)) {
			return newValue;
		}
		
		return oldValue;
	}
	
	public static boolean checkIfValidUser(String mail, String password) {
		User user = RepositoryUtils.getEntityByUniqueFieldValue(User.class, "mail", mail);
		if (user == null || !BCrypt.checkpw(password,  user.getPassword())) {
			return false;
		}
		
		return true;
	}
}
