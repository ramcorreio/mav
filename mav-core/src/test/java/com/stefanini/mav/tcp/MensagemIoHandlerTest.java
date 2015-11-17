package com.stefanini.mav.tcp;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.mina.core.session.IoSession;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.stefanini.mav.mensagem.CodigoMensagem;
import com.stefanini.mav.mensagem.ContextoMensagem;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;
import com.stefanini.mav.util.MensagemHelper;

public class MensagemIoHandlerTest {
	
	private MensagemIoHandler handler;
	
	@Before
	public void setUp() {
		
		handler = new MensagemIoHandler();
	}
	

	@Test 
	public void receberMensagem() throws Exception {
		
		IoSession session = EasyMock.mock(IoSession.class);
		
		ContextoMensagem<MensagemBasica> ctx = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica mensagemBasica = ctx.ler(MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1"));
		
		handler.messageReceived(session, mensagemBasica);
		
	}
	
}
