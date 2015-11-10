package com.stefanini.mav.mensagem;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.filter.codec.textline.LineDelimiter;

public class Util {
	
	public Util() {
	}
	
	public static IoBuffer writeBuffer(String input) throws CharacterCodingException {
		
		IoBuffer buf = IoBuffer.allocate(input.length()).setAutoExpand(true);
		CharsetEncoder encoder = Charset.defaultCharset().newEncoder();
        buf.putString(input, encoder);
        buf.putString(LineDelimiter.UNIX.getValue(), encoder);
        buf.flip();
        
        return buf;
	}

}
