package exceptions;

public enum AeMessages {
	INVALID_MAIL_ENTERED(400, "Invalid mail entered."),
	INVALID_VALUE_IN_FORM(400, "Invalid value entered on ['%s'] the required value is ['%s']."),
	//SQL_INJECTION_ATTACK(400, "Sql injection attack detected, your details monitored."),
	INVALID_CHARACTERS(400, "Invalid charcters entered!."),
	USER_OR_PASSWORD_INVALID(400, "User or password is invalid."),
	USER_MAIL_ALREADY_EXIST(400, "User with mail ['%s'] already exist."),
	SESSION_TIMEOUT(401, "No active session, please login or signup."),
	DUPLICATE_ENTRY_EXISTS(400, "Duplicate entry exists."),
	FILE_TYPE_IS_NOT_ALLOWED(400, "File type ['%s'] is not allowed ['s'] required."),
	PASSWORD_TOO_SHORT(401, "Invalid password, required password with at least %s."),
	PASSWORDS_NOT_EQUALS(400, "Passwords not equals, enter again."),
	USER_NOT_ADMIN(400, "The user is not defined as Admin."),
	USER_ALREADY_ADMIN(400, "The user is already set as Admin."),
	STR_TO_LONGG(400, "Invalid input! your input is to long"),
	CSRF_ATTACK(400, "CSRF attack detected, your details monitored.");

	
	int errorCode;
	String message;
	
	private AeMessages(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
