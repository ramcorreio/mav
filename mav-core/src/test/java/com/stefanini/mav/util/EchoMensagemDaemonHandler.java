package com.stefanini.mav.util;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stefanini.mav.mensagem.MensagemBasica;

public class EchoMensagemDaemonHandler extends IoHandlerAdapter {

	private static Logger logger = LoggerFactory.getLogger(EchoMensagemDaemonHandler.class);
	
	/**
     * {@inheritDoc}
     */
    @Override
	public void sessionOpened(IoSession session) throws Exception {
		
		logger.info("session open: " + session.getId());
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		super.sessionOpened(session);
	}
	
    /**
     * {@inheritDoc}
     */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		
		logger.info("session closed: " + session.getId());
	}
	
	/**
     * {@inheritDoc}
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
    	
    	logger.error("session: " + session.getId(), cause);
        session.close(true);
    }
    
    /**
     * {@inheritDoc}
     */
    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception
    {
		logger.info( "IDLE " + session.getIdleCount( status ));
    }
	
	@Override
	public void messageReceived(IoSession session, Object message) {

		logger.info("recebendo mensagem: " + message);
		MensagemBasica m = MensagemBasica.class.cast(message);
		session.write(m);
		session.close(true);
	}
	
	@Override
	public void messageSent(IoSession session, Object message) {
		
		logger.info("enviando mensagem: " + message);
	}
	
}
