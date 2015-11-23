package com.stefanini.mav.tcp;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stefanini.mav.mensagem.MensagemBasica;

public class ConexaoParceira {

	private static final int FATOR_TIMEOUT = 10;

	private static final int TIMEOUT = FATOR_TIMEOUT * 1000; // 10 seconds

	private static Logger logger = LoggerFactory.getLogger(ConexaoParceira.class);

	private String servidor;

	private int porta;

	private SocketConnector connector;

	private ConnectFuture future;

	private ControladorParceira controlador;

	private class ControladorParceira extends IoHandlerAdapter {

		@Override
		public void messageReceived(IoSession session, Object message) throws Exception {
			super.messageReceived(session, message);
		}
	}

	public ConexaoParceira(String servidor, int porta) {
		this.servidor = servidor;
		this.porta = porta;
		this.connector = new NioSocketConnector();
	}

	public String getServidor() {
		return servidor;
	}

	public int getPorta() {
		return porta;
	}

	public void conectar() {

		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MensagemCodecFactory()));

		controlador = new ControladorParceira();
		connector.setHandler(controlador);
		connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

		logger.info("conexão configurada");
		connector.setConnectTimeoutMillis(TIMEOUT);
		future = connector.connect(new InetSocketAddress(servidor, porta)).awaitUninterruptibly();
	}

	public void fechar() {

		future.getSession().close(true);
		future.cancel();
		connector.dispose();
		logger.info("conexão encerrada");
	}

	public void enviar(MensagemBasica mensagem) {

		future.getSession().write(mensagem);
	}

	public MensagemBasica receber() {

		future.getSession().getConfig().setUseReadOperation(true);
		ReadFuture read = future.getSession().read();
		read.awaitUninterruptibly(TIMEOUT);
		return (MensagemBasica) read.getMessage();
	}
}
