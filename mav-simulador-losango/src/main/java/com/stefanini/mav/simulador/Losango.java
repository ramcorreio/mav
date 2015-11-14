package com.stefanini.mav.simulador;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.CharBuffer;
import java.util.Properties;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;

/**
 * Criada para simular o comportamento do ambiente losando para validação das mensagens
 *
 */
public class Losango extends IoHandlerAdapter {

	private static Logger _LOGGER = LoggerFactory.getLogger(Losango.class);
	
	private File mensagens = new File("mensagens.io");
	
	protected CharBuffer carregarArquivoMensagens() throws IOException {
		
		_LOGGER.info("Lendo: " + mensagens.getAbsolutePath());
		FileReader fr;
		try {
			fr = new FileReader(mensagens);
		} catch (FileNotFoundException e) {
			URL file = getClass().getClassLoader().getResource(mensagens.getName());
			_LOGGER.info("Lendo: " + file.getPath());
			fr = new FileReader(file.getPath());
		}
		
		StringBuilder sb = new StringBuilder();
		CharBuffer cb = CharBuffer.allocate(1024);
		while (fr.read(cb) > 0) {
			cb.flip();
			sb.append(cb);
			cb.clear();
		}
		fr.close();
		
		CharBuffer b = CharBuffer.allocate(sb.length());
		b.append(sb);
		b.flip();
		
		return b;
	}
	
	protected Properties carregarPropriedades() throws IOException {
		
		CharBuffer b = carregarArquivoMensagens();
		StringBuffer sb = new StringBuffer();
		sb.append(b);
		
		Properties p = new Properties();
		p.load(new ByteArrayInputStream(sb.toString().getBytes()));
		return p;
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