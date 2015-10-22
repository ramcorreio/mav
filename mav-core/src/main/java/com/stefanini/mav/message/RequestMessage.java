package com.stefanini.mav.message;

public abstract class RequestMessage extends Message {

	private ResponseMessage response;
	
	protected RequestMessage(MessageType type) {
		super(type);
	}
	
	public ResponseMessage getResponse() {
		return response;
	}
}
