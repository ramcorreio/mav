package com.stefanini.mav.util;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoProtocolHandler extends IoHandlerAdapter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {

		logger.info("recebendo mensagem: " + message);
		String m = String.class.cast(message);
		session.write(m);
	}
}
