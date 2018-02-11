package exceptions;

import java.util.EnumSet;

public enum MessageType {
	INFO,
	ERROR,
	WARNING;
	
	public static EnumSet<MessageType> FAILED_STATUSES = EnumSet.of(ERROR, WARNING);
}

