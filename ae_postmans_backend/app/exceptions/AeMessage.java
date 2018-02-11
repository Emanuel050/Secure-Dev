package exceptions;

import java.io.Serializable;

public class AeMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MessageType messageType;
	private String formatedMessage;
	private int errorCode;
	
	public AeMessage(MessageType messageType, String formatedMessage, int errorCode) {
		super();
		this.messageType = messageType;
		this.formatedMessage = formatedMessage;
		this.errorCode = errorCode;
	}
	
	public AeMessage(MessageType messageType, AeMessages aeMessages, Object ... args) {
		super();
		this.messageType = messageType;
		this.formatedMessage = String.format(aeMessages.getMessage(), args);
		this.errorCode = aeMessages.getErrorCode();
	}
	
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	public String getFormatedMessage() {
		return formatedMessage;
	}
	public void setFormatedMessage(String formatedMessage) {
		this.formatedMessage = formatedMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return "AeMessage [messageType=" + messageType + ", formatedMessage=" + formatedMessage + ", errorCode="
				+ errorCode + "]";
	}
	
	
	
}
