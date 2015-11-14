package com.stefanini.mav.tcp;

import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MensagemIoHandler extends IoHandlerAdapter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
     * {@inheritDoc}
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
    	
    	logger.error("session: " + session.getId(), cause);
        session.close(true);
    }
		
	@Override
    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception
    {
		logger.info( "IDLE " + session.getIdleCount( status ));
    }
	
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {

		System.out.println("Message class: " + message.getClass());
		String str = message.toString();
		System.out.println("Message received: " + str);
		if (str.trim().equalsIgnoreCase("quit")) {
			session.close(true);
			return;
		}
		
		if (str.trim().equals("data")) {
			Date date = new Date();
			session.write(date.toString()); /* Retornando dado ao cliente */
		} else {
			session.write("echo " + str); /* Retornando dado ao cliente */
		}

	}
	
	
	
	

}