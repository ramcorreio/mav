package com.stefanini.mav.message;

public abstract class Message {
	
	private MessageType type;
	
	protected Message(MessageType type) {
		
		this.type = type;
	}

}
