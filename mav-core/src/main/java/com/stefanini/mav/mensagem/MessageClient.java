package com.stefanini.mav.mensagem;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class MessageClient {

	//TODO: verificar como configurar timeout
	//private static int TIMEOUT = 1000;

	private static int PORT = 8889;
	
	private static Logger logger = LoggerFactory.getLogger(MessageClient.class);

	private NioSocketAcceptor acceptor;

	private void init() throws IOException {

		acceptor = new NioSocketAcceptor();
		
		acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
		acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "UTF-8" ))));
		
		acceptor.setHandler(new MessageIoHandler());
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

		acceptor.setDefaultLocalAddress(new InetSocketAddress(PORT));
		logger.info("server configured");
	}
	
	public void start() throws IOException {

		init();
		acceptor.bind();
		logger.info("server is listenig at port " + PORT);
	}

	public void stop() {

		logger.info("server is stoping");
		acceptor.setCloseOnDeactivation(true);
		acceptor.unbind();
		logger.info("server stoped");
	}
}
