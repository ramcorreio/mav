package com.stefanini.mav.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.mina.core.session.IoSession;
import org.easymock.EasyMock;
import org.junit.Before;

import com.stefanini.mav.mensagem.CodigoMensagem;
import com.stefanini.mav.mensagem.ContextoMensagem;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;
import com.stefanini.mav.util.MensagemHelper;

/**
 * 
 * Classe respponsável para controlar o fluxo de mensagens entre a losango e as lojas
 * 
 * @author Rodrigo
 */
public class MensagemBrokerTest {
	
	/*private MensagemBroker broker;
	
	@Before
	public void setUp() {
		
		Men 
	}*/
	
	public void enviarParceira() throws MensagemNaoEncontradaException, IOException, URISyntaxException, BrokerException {
		
		IoSession session = EasyMock.mock(IoSession.class);
		
		ContextoMensagem<MensagemBasica> ctx = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica mensagemBasica = ctx.ler(MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1"));
		
		MensagemBroker.getInstance().enviarParceira(mensagemBasica);
		
	}

}
