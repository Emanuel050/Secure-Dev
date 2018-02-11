package utils;

import java.util.regex.Matcher;
import org.apache.commons.*;
import java.util.regex.Pattern;

public class FormUtils {

	public static boolean validateMail(String input) {
		String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern p = Pattern.compile(emailPattern);
		Matcher m = p.matcher(input);
		return m.matches();
	}

	public static boolean validateSqlInjection(String input) {
		boolean goodInput = true;

		for (int i = 0; i < input.length(); ++i) {
			char c = input.charAt(i);
			
			switch (c) {

			case '\0':
				goodInput = false;
				break;

			case '\'':
				goodInput = false;
				break;

			case '\"':
				goodInput = false;
				break;

			case '\b':
				goodInput = false;
				break;

			case '\n':
				goodInput = false;

				break;

			case '\r':
				goodInput = false;

				break;

			case '\t':
				goodInput = false;
				break;

			case '\\':
				goodInput = false;
				break;

			case '%':
				goodInput = false;
				break;

			case '_':
				goodInput = false;
				break;
			}

		}
		return goodInput;
	}
}
