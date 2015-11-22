package com.stefanini.mav.tcp;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.service.MensagemBroker;

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
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		logger.info("IDLE " + session.getIdleCount(status));
	}

	@Override
	public void messageReceived(IoSession session, Object mensagem) throws Exception {

		session.write(MensagemBroker.getInstance().enviarParceira(MensagemBasica.class.cast(mensagem)));
		session.close(true);

	}

}