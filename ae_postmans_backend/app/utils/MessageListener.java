package utils;

import java.util.ArrayList;
import java.util.List;

import exceptions.AeMessage;
import exceptions.AeMessages;
import exceptions.MessageType;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class MessageListener {
	private List<AeMessage> messages = new ArrayList<>();
	private boolean isFailed = false;
	public MessageListener() {
	}
	
	public void addMessage(MessageType messageType, AeMessages aeMessage, Object ... args) {
		updateIsFailed(messageType);
		messages.add(new AeMessage(messageType, String.format(aeMessage.getMessage(), args), aeMessage.getErrorCode()));
	}

	private void updateIsFailed(MessageType messageType) {
		if(MessageType.FAILED_STATUSES.contains(messageType)){
			setFailed(true);
		}
	}
	
	public Result getResult(Object returnOnSuccess) {
		if (messages.size() == 1) {
			AeMessage message = messages.get(0);
			return Controller.status(message.getErrorCode(), message.getFormatedMessage());
		}
		
		if (messages.size() > 0) {
			return Controller.badRequest(Json.toJson(messages));
		}
		
		return Controller.ok(Json.toJson(returnOnSuccess));
	}
	
	public Result getResult() {
		return getResult("");
	}

	public boolean isFailed() {
		return isFailed;
	}

	public void setFailed(boolean isFailed) {
		this.isFailed = isFailed;
	}
	
	
}
