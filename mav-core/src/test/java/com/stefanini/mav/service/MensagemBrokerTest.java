package com.stefanini.mav.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
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
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;
import com.stefanini.mav.service.MensagemBroker.MensagemErroBroker;
import com.stefanini.mav.tcp.ConexaoParceira;
import com.stefanini.mav.util.MensagemHelper;

/**
 * 
 * Classe respponsável para controlar o fluxo de mensagens entre a losango e as lojas
 * 
 * @author Rodrigo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:mav-test-context.xml", "classpath:mav-deamon-fake-broker.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class MensagemBrokerTest {
	
	@Test
	public void verificarParceira() {
		
		MatcherAssert.assertThat(MensagemBroker.getInstance(), Matchers.notNullValue());
		MatcherAssert.assertThat(MensagemBroker.getInstance().getParceiras().size(), Matchers.equalTo(0));
	}
	
	@After
	public void tearDown() {
		
		MensagemBroker.getInstance().getParceiras().clear();
	}
	
	
	@Test
	public void erroAusenciaParceira() throws MensagemNaoEncontradaException, IOException, URISyntaxException, BrokerException {
		
		ContextoMensagem<MensagemBasica> ctxEntrada = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica entrada = ctxEntrada.ler(MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1"));
		
		MensagemBasica expected = MensagemErroBroker.MSG_ERRO_AUSENCIA_PARCEIRA.wrap(entrada);
		
		MensagemBasica retorno = MensagemBroker.getInstance().enviarParceira(entrada);
		MatcherAssert.assertThat(expected, Matchers.equalTo(retorno));
	}
	
	@Test
	public void erroConexaoParceira() throws MensagemNaoEncontradaException, IOException, URISyntaxException, BrokerException {
		
		ContextoMensagem<MensagemBasica> ctxEntrada = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica entrada = ctxEntrada.ler(MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1"));
		
		Parceira p = new Parceira("Teste Fake", "localhost", 9090);
		MensagemBroker.getInstance().getParceiras().add(p);
		
		MensagemBasica expected = MensagemErroBroker.MSG_ERRO_CONEXAO.wrap(entrada, null, p);
		
		MensagemBasica retorno = MensagemBroker.getInstance().enviarParceira(entrada);
		MatcherAssert.assertThat(expected, Matchers.equalTo(retorno));
	}
	
	@Test
	public void conexaoParceiraOK() throws MensagemNaoEncontradaException, IOException, URISyntaxException, BrokerException {
		
		IMocksControl mocker = EasyMock.createStrictControl();
		
		ContextoMensagem<MensagemBasica> ctxEntrada = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica entrada = ctxEntrada.ler(MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1"));
		
		ContextoMensagem<MensagemBasica> ctxExpected = MensagemFactory.loadContexto(CodigoMensagem.C0460);
		MensagemBasica expected = ctxExpected.ler(MensagemHelper.lerMensagem(CodigoMensagem.C0460, "criarRespostaCapturaSimplicada.1"));
		
		//mocking parceira
		//SocketConnector nsc = mocker.createMock(SocketConnector.class);
		//DefaultIoFilterChainBuilder chain = new DefaultIoFilterChainBuilder(); //mocker.createMock(DefaultIoFilterChainBuilder.class);
		//nsc.setHandler(new ControladorParceira());
		//chain.addLast("logger", new LoggingFilter());
		//chain.addLast("codec", new ProtocolCodecFilter(new MensagemCodecFactory()));
		//ConnectFuture connectFuture = mocker.createMock(ConnectFuture.class);
		//IoSession session = mocker.createMock(IoSession.class);
		//CloseFuture closeFuture = mocker.createMock(CloseFuture.class);
		
		
		//EasyMock.expect(nsc.getFilterChain()).andReturn(chain).times(2);
		//chain.addLast("logger", new LoggingFilter());
		//EasyMock.expect(nsc.getFilterChain()).andReturn(chain);
		//chain.addLast("codec", new ProtocolCodecFilter(new MensagemCodecFactory()));
		
		//EasyMock.expect(nsc.connect()).andStubReturn(connectFuture);
		//EasyMock.expect(connectFuture.getSession()).andStubReturn(session);
		//EasyMock.expect(session.close(true)).andStubReturn(closeFuture);
		//EasyMock.expect(nsc.getFilterChain()).andReturn(difc);
		//difc.addaddLast( "logger", new LoggingFilter() )).;
		//EasyMock.expect(difc.addLast( "codec", new ProtocolCodecFilter( new MensagemCodecFactory() )));
		
		
		
		
		//EasyMock.expect(c.getPorta()).andStubReturn(9090);
		
		//ConnectFuture cf = mocker.createMock(ConnectFuture.class);
		//EasyMock.expect(nsc.connect()).andReturn(cf);
		
		
		ConexaoParceira c = new ConexaoParceira("localhost", 8891);
		/*ConexaoParceira c = mocker.createMock(ConexaoParceira.class);
		EasyMock.expect(c.getServidor()).andStubReturn("localhost");
		EasyMock.expect(c.getPorta()).andStubReturn(9090);
		c.conectar();
		c.enviar(entrada);
		EasyMock.expect(c.receber()).andStubReturn(expected);
		c.fechar();*/
		
		Parceira p = new Parceira("Teste Mocked", c);
		MensagemBroker.getInstance().getParceiras().add(p);
		
		mocker.replay();
		MensagemBasica retorno = MensagemBroker.getInstance().enviarParceira(entrada);
		mocker.verify();
		MatcherAssert.assertThat(expected, Matchers.equalTo(retorno));
	}

}
