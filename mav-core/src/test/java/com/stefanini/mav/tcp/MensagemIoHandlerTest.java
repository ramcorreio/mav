package com.stefanini.mav.tcp;

import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.stefanini.mav.mensagem.CodigoMensagem;
import com.stefanini.mav.mensagem.ContextoMensagem;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.service.MensagemBroker.MensagemErroBroker;
import com.stefanini.mav.util.MensagemHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:mav-test-context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class MensagemIoHandlerTest {
	
	private MensagemIoHandler handler;
	
	private IMocksControl mocker;
	
	@Before
	public void setUp() {
		
		handler = new MensagemIoHandler();
		mocker = EasyMock.createStrictControl();
	}

	@Test 
	public void receberMensagem() throws Exception {
		
		IoSession session = mocker.createMock(IoSession.class);
		WriteFuture writeFuture = mocker.createMock(WriteFuture.class);
		CloseFuture closeFuture = mocker.createMock(CloseFuture.class);
		
		ContextoMensagem<MensagemBasica> ctxEntrada = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica entrada = ctxEntrada.ler(MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1"));
		
		MensagemBasica expected = MensagemErroBroker.MSG_ERRO_AUSENCIA_PARCEIRA.wrap(entrada);
		
		EasyMock.expect(session.write(expected)).andReturn(writeFuture);
		EasyMock.expect(session.close(true)).andReturn(closeFuture);
		
		mocker.replay();
		handler.messageReceived(session, entrada);
		mocker.verify();
		
	}
	
}
