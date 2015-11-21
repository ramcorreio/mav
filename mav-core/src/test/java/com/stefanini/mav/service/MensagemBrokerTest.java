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
import com.stefanini.mav.util.MensagemHelper;

/**
 * 
 * Classe respponsável para controlar o fluxo de mensagens entre a losango e as lojas
 * 
 * @author Rodrigo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:mav-test-context.xml"/*, "classpath:mav-parceira-loader-context.xml"*/})
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
		Parceira pMock = mocker.createMock(Parceira.class);
		EasyMock.expect(pMock.getServidor()).andStubReturn("localhost");
		EasyMock.expect(pMock.getPorta()).andStubReturn(9090);
		EasyMock.expect(pMock.getNome()).andStubReturn("Teste Mock");
		
		//mocking session
		//Parceira pMock = mocker.createMock(Parceira.class);
		
		
		
		MensagemBroker.getInstance().getParceiras().add(pMock);
		
		mocker.replay();
		MensagemBasica retorno = MensagemBroker.getInstance().enviarParceira(entrada);
		mocker.verify();
		MatcherAssert.assertThat(expected, Matchers.equalTo(retorno));
	}

}
