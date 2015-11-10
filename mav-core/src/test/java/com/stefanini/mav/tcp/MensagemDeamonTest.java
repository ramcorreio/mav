package com.stefanini.mav.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.stefanini.mav.util.MensagemHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:mav-test-context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class MensagemDeamonTest {
	
	
	// 30 seconds
	private static final long CONNECT_TIMEOUT = 10*1000L;
	
	private LojistaEchoHandler handler;
	
	@Before
    public void setUp() throws Exception {
        handler = new LojistaEchoHandler();
	}
	
	@Test
	public void sendMessage() throws IOException, URISyntaxException, InterruptedException {
		
		NioSocketConnector connector = new NioSocketConnector();
		connector.setHandler(handler);
	    connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);
	    connector.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory() ));
	    ConnectFuture future = connector.connect(new InetSocketAddress(8889));
	    future.awaitUninterruptibly();
	    
	    String message = MensagemHelper.lerMensagem(450, "sendMessage.1");
	    Assert.assertEquals(199, message.length());
	    future.getSession().write(message).awaitUninterruptibly();
	    future.getSession().getConfig().setUseReadOperation(true);
	    ReadFuture toRead = future.getSession().read();
	    toRead.awaitUninterruptibly(CONNECT_TIMEOUT);
	    String recebida = (String) toRead.getMessage();
	    
	    MatcherAssert.assertThat(message, Matchers.is(Matchers.equalTo(recebida)));
	}
	
	private static class LojistaEchoHandler extends IoHandlerAdapter {
		
		private LojistaEchoHandler() {
        }

        @Override
        public void messageReceived(IoSession session, Object message) {
        	System.out.println("==============================================");
        	System.out.println(message);
        	System.out.println("==============================================");
        }        
    }

}
