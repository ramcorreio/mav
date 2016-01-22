package com.stefanini.mav.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.IoFilterChainBuilder;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MensagemDeamon {

	private static int DEFAULT_PORT = 9089;
	
	private static Logger logger = LoggerFactory.getLogger(MensagemDeamon.class);

	private NioSocketAcceptor acceptor;
	
	private int port = DEFAULT_PORT; 
	
	public MensagemDeamon() {
		
		acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors());
		acceptor.getSessionConfig().setReadBufferSize(8192);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 2);
	}
	
	public void setPort(int port) {
		
		this.port = port;
	}
	
	public void setFilterChainBuilder(IoFilterChainBuilder builder) {
		
		acceptor.setFilterChainBuilder(builder);
	}
	
	public void setHandler(IoHandler handler) {
		
		acceptor.setHandler(handler);
	}
	
	public void addListener(IoServiceListener listener) {
		
		acceptor.addListener(listener);
	}
	
	public void start() throws IOException {

		acceptor.setDefaultLocalAddress(new InetSocketAddress(port));
		logger.info("server configured");
		acceptor.bind();
		logger.info("server is listenig at port " + port);
	}

	public void stop() {

		logger.info("server is stoping");
		acceptor.setCloseOnDeactivation(true);
		acceptor.unbind();
		logger.info("server stoped");
	}
}
