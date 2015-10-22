package com.stefanini.mav.message;


public class MessageFactory {
	
	public static Message create() {
		
		return new RequestMessage450();
	}

}
