package com.stefanini.mav.simulador;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.stefanini.mav.mensagem.CodigoMensagem;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;
import com.stefanini.mav.util.MensagemHelper;

/**
 * Criada para simular o comportamento do ambiente losando para validação das mensagens
 *
 */
public class Losango extends IoHandlerAdapter {

	private static Logger _LOGGER = LoggerFactory.getLogger(Losango.class);
	
	@Override
	public void messageReceived(IoSession session, Object mensagem) throws MensagemNaoEncontradaException, IOException, URISyntaxException {

		_LOGGER.info("recebendo mensagem: " + mensagem);
		//String m = String.class.cast(mensagem);
		MensagemBasica m = MensagemFactory.parse(String.class.cast(mensagem));
		_LOGGER.info("mensagem recebida: " + m);
		session.write(MensagemHelper.lerMensagem(CodigoMensagem.C0460, "Losango.1"));
	}
	
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		
		_LOGGER.info("enviando mensagem: " + message);
	}
	
	private static final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("mav-init-context.xml");

	public static void main(String[] args) {

		_LOGGER.info("Simulador " + ctx.getDisplayName() + " iniciado.");
	}
}