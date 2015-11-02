package com.stefanini.mav.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.mina.core.buffer.IoBuffer;

public class MessageRead {
	
	public static final int BUFFER_SIZE = 1024; 

	private MessageRead() {
	}
	
	public static String readMessage(int code, String name) throws IOException, URISyntaxException {
		
		URL path = MessageRead.class.getClassLoader().getResource(name + "-" + code + ".input");
		byte[] readed = Files.readAllBytes(Paths.get(path.toURI()));
		
		return new String(readed);
	}
	
	public static String readBuffer(IoBuffer buffer) throws IOException, URISyntaxException {
		
		StringBuilder b = new StringBuilder();
		b.append(new String(buffer.array()), 0, buffer.remaining());
		return b.toString();
	}
}
