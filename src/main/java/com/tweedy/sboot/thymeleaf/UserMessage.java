package com.tweedy.sboot.thymeleaf;

public class UserMessage {

	private String message;
	private Boolean isErrorMessage = false;

	public Boolean getIsErrorMessage() {
		return isErrorMessage;
	}

	public void setIsErrorMessage(Boolean isErrorMessage) {
		this.isErrorMessage = isErrorMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserMessage(String message) {
		
		this.message = message;
	}
	
	public UserMessage() {	}
	
}
