package com.stefanini.mav.util;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;
import com.stefanini.mav.mensagem.CodigoMensagem;

public class EchoConexaoParceira extends IoHandlerAdapter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void messageReceived(IoSession session, Object mensagem) throws MensagemNaoEncontradaException, IOException, URISyntaxException {

		logger.info("recebendo mensagem: " + mensagem);
		//String m = String.class.cast(mensagem);
		MensagemBasica m = MensagemFactory.parse(String.class.cast(mensagem));
		logger.info("mensagem recebida: " + m);
		session.write(MensagemHelper.lerMensagem(CodigoMensagem.C0460, "EchoConexaoParceira.1"));
	}
	
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		
		logger.info("enviando mensagem: " + message);
	}
}
