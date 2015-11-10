package com.stefanini.mav.util;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stefanini.mav.mensagem.MensagemBasica;

public class EchoMensagemDeamonHandler extends IoHandlerAdapter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void messageReceived(IoSession session, Object message) {

		logger.info("recebendo mensagem: " + message);
		MensagemBasica m = MensagemBasica.class.cast(message);
		session.write(m);
	}
	
	@Override
	public void messageSent(IoSession session, Object message) {
		
		logger.info("enviando mensagem: " + message);
	}
	
}
