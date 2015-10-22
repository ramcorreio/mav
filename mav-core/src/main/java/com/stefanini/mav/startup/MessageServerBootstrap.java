package com.stefanini.mav.startup;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stefanini.mav.message.MessageIoHandler;

public class MessageServerBootstrap {
	
	private static int PORT = 8889;
	
	private static MessageServerBootstrap server;
	
	private Logger logger = LoggerFactory.getLogger(MessageServerBootstrap.class);
	
	private NioSocketAcceptor acceptor;
	
	public void init() throws IOException {
		
		MessageIoHandler handler = new MessageIoHandler();
        acceptor = new NioSocketAcceptor();
        //TODO: verificar a necessidade da linha abaixo
        //acceptor.getFilterChain().addLast("protocol", new ProtocolCodecFilter(new ImageCodecFactory(false)));
        acceptor.setDefaultLocalAddress(new InetSocketAddress(PORT));
        acceptor.setHandler(handler);
        acceptor.bind();
        logger.info("server is listenig at port " + PORT);
	}
	
	private void stop() {
		
		acceptor.setCloseOnDeactivation(true);
		acceptor.unbind();
	}
	
	public static void main(String[] args) throws IOException {
		
		
		if(server == null) {
		
			server = new MessageServerBootstrap();
			server.init();
		}
		else if(args.length > 0) {
			
			server.stop();
		}
		
		server.logger.info("server already listenig at port " + PORT);
	}
}
