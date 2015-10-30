package com.stefanini.mav.startup;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.security.AccessController;
import java.security.PrivilegedAction;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stefanini.mav.message.MessageIoHandler;



public class MessageServerBootstrap {

	//TODO: verificar como configurar timeout
	//private static int TIMEOUT = 1000;

	private static int PORT = 8889;
	
	protected ClassLoader loader;

	private static MessageServerBootstrap deamon = null;

	private static Logger logger = LoggerFactory.getLogger(MessageServerBootstrap.class);

	private NioSocketAcceptor acceptor;

	public void init() throws IOException {

		acceptor = new NioSocketAcceptor();
		
		acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
		acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "UTF-8" ))));
		
		acceptor.setHandler(new MessageIoHandler());
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

		acceptor.setDefaultLocalAddress(new InetSocketAddress(PORT));
		acceptor.bind();
		logger.info("server is listenig at port " + PORT);
	}

	private void stop() {

		acceptor.setCloseOnDeactivation(true);
		acceptor.unbind();
	}

	public static void main(String[] args) throws IOException, URISyntaxException {

		if (deamon == null) {

			MessageServerBootstrap bootstrap = new MessageServerBootstrap();
			bootstrap.init();
			logger.info("loader: " + bootstrap.loader);
			
			deamon = bootstrap;
			
		} else if (args.length > 0) {

			deamon.stop();
		}

		//server.logger.info("server already listenig at port " + PORT);
	}
}
