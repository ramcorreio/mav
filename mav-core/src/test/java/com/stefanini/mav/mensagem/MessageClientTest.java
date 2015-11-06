package com.stefanini.mav.mensagem;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
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
public class MessageClientTest {
	
	private static final int TIMEOUT = 10000; // 10 seconds
	
	// 30 seconds
	private static final long CONNECT_TIMEOUT = 30*1000L;
	
	private ClientEchoConnectorHandler handler;
	
	@Before
    public void setUp() throws Exception {
        handler = new ClientEchoConnectorHandler();
	}
	
	private void waitForResponse(ClientEchoConnectorHandler handler, int bytes) throws InterruptedException {
        for (int j = 0; j < TIMEOUT / 10; j++) {
            if (handler.readBuf.position() >= bytes) {
                break;
            }
            Thread.sleep(10);
        }

        Assert.assertEquals(bytes, handler.readBuf.position());
    }
	
	// Set this to false to use object serialization instead of custom codec.
	@Test
	public void sendMessage() throws IOException, URISyntaxException, InterruptedException {
		
		NioSocketConnector connector = new NioSocketConnector();
		connector.setHandler(handler);
	    connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);
	    connector.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "UTF-8" ))));
	    ConnectFuture future = connector.connect(new InetSocketAddress(8889));
	    future.awaitUninterruptibly();
	    
	    IoSession session = future.getSession();
	    String message = MensagemHelper.lerMensagem(450, "sendMessage.1");
	    Assert.assertEquals(199, message.length());
	    session.write(message).awaitUninterruptibly();
	    waitForResponse(handler, 199);
	    
        handler.readBuf.flip();
        Assert.assertEquals(186, handler.readBuf.remaining());
	    Assert.assertEquals(message, MensagemHelper.readBuffer(handler.readBuf));
	}
	
	private static class ClientEchoConnectorHandler extends IoHandlerAdapter {
		
        private final IoBuffer readBuf = IoBuffer.allocate(MensagemHelper.BUFFER_SIZE);

        private ClientEchoConnectorHandler() {
            readBuf.setAutoExpand(true);
            readBuf.setAutoShrink(true);
        }

        @Override
        public void messageReceived(IoSession session, Object message) {
        	System.out.println("==============================================");
        	System.out.println(message);
        	System.out.println("==============================================");
        	readBuf.put(message.toString().getBytes());
        }        
    }

}
