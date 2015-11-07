package com.stefanini.mav.mensagem;

import org.apache.mina.core.buffer.IoBuffer;

public class Util {
	
	public Util() {
	}
	
	public static IoBuffer writeBuffer(Object input) {
		
		IoBuffer buf = IoBuffer.allocate(64);
        buf.setAutoExpand(true);
        buf.putObject(input);
        buf.flip();
        
        return buf;
	}

}
