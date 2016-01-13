package com.stefanini.mav.simulador;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.CharBuffer;
import java.util.Properties;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;
import com.stefanini.mav.util.Utils;

/**
 * Criada para simular o comportamento do ambiente losando para validação das mensagens
 *
 */
public class Losango extends IoHandlerAdapter {

	private static Logger _LOGGER = LoggerFactory.getLogger(Losango.class);
	
	private File mensagens = new File("mensagens.io");
	
	protected CharBuffer carregarArquivoMensagens() throws IOException {
		
		_LOGGER.info("recebendo mensagem: " + mensagens);
		return Utils.carrgarArquivo(mensagens.getName());
	}
	
	protected Properties carregarPropriedades() throws IOException {
		
		return Utils.carregarPropriedades(carregarArquivoMensagens());
	}
	
	@Override
	public void messageReceived(IoSession session, Object mensagem) throws MensagemNaoEncontradaException, IOException, URISyntaxException {
		
		Properties p = carregarPropriedades();
		
		_LOGGER.info("recebendo mensagem: " + mensagem);
		MensagemBasica mb = MensagemFactory.parse(mensagem.toString());
		_LOGGER.info("mensagem recebida: " + mb);
		_LOGGER.info("mensagem recebida id: " + mb.getId());
		session.write(p.getProperty("losango.".concat(mb.getCabecalho().getNumeroTransacao().toString()).concat(".out")));
		session.close(true);
	}
	
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		
		_LOGGER.info("enviando mensagem: " + message);
	}
}