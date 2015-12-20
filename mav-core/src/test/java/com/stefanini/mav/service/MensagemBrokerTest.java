package com.stefanini.mav.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.exparity.hamcrest.BeanMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.stefanini.mav.es.MapeamentoNaoEncontrado;
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
		
		MensagemBroker.getInstance().clear();
	}
	
	
	@Test
	public void erroAusenciaParceira() throws MensagemNaoEncontradaException, IOException, URISyntaxException, BrokerException, MapeamentoNaoEncontrado {
		
		ContextoMensagem<MensagemBasica> ctxEntrada = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica entrada = ctxEntrada.ler(MensagemHelper.lerMensagemMudaTransacao(CodigoMensagem.C0450, "criarCapturaSimplicada.1"));
		
		MensagemBasica expected = MensagemErroBroker.MSG_ERRO_AUSENCIA_PARCEIRA.wrap(entrada);
		
		MensagemBasica retorno = MensagemBroker.getInstance().enviarParceira(entrada);
		MatcherAssert.assertThat(retorno, BeanMatchers.theSameAs(expected));
	}
	
	@Test
	public void erroConexaoParceira() throws MensagemNaoEncontradaException, IOException, URISyntaxException, BrokerException, MapeamentoNaoEncontrado {
		
		ContextoMensagem<MensagemBasica> ctxEntrada = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica entrada = ctxEntrada.ler(MensagemHelper.lerMensagemMudaTransacao(CodigoMensagem.C0450, "criarCapturaSimplicada.1"));
		
		Parceira p = new Parceira("parceira-fake", "Teste Fake", "localhost", 9090);
		MensagemBroker.getInstance().setParceira(p);
		
		MensagemBasica expected = MensagemErroBroker.MSG_ERRO_CONEXAO.wrap(entrada, null, p);
		
		MensagemBasica retorno = MensagemBroker.getInstance().enviarParceira(entrada);
		MatcherAssert.assertThat(retorno, BeanMatchers.theSameAs(expected));
	}
	
	@Test
	public void conexaoParceiraOK() throws MensagemNaoEncontradaException, IOException, URISyntaxException, BrokerException, MapeamentoNaoEncontrado {
		
		IMocksControl mocker = EasyMock.createStrictControl();
		
		ContextoMensagem<MensagemBasica> ctxEntrada = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica entrada = ctxEntrada.ler(MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1"));
		
		ContextoMensagem<MensagemBasica> ctxExpected = MensagemFactory.loadContexto(CodigoMensagem.C0460);
		MensagemBasica expected = ctxExpected.ler(MensagemHelper.lerMensagem(CodigoMensagem.C0460, "criarRespostaCapturaSimplicada.1"));
		
		ConexaoParceira c = new ConexaoParceira("localhost", 8891);
		Parceira p = new Parceira("parceira-fake", "Teste Mocked", c);
		MensagemBroker.getInstance().setParceira(p);
		
		mocker.replay();
		MensagemBasica retorno = MensagemBroker.getInstance().enviarParceira(entrada);
		mocker.verify();
		MatcherAssert.assertThat(expected, Matchers.equalTo(retorno));
	}
	
	@Test
	@Ignore
	public void conexaoDuasParceiras() throws MensagemNaoEncontradaException, IOException, URISyntaxException, BrokerException, MapeamentoNaoEncontrado {
		
		IMocksControl mocker = EasyMock.createStrictControl();
		
		ContextoMensagem<MensagemBasica> ctxEntrada = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica entrada = ctxEntrada.ler(MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1"));
		
		ContextoMensagem<MensagemBasica> ctxExpected = MensagemFactory.loadContexto(CodigoMensagem.C0460);
		MensagemBasica expected = ctxExpected.ler(MensagemHelper.lerMensagem(CodigoMensagem.C0460, "criarRespostaCapturaSimplicada.1"));
		
		ConexaoParceira c1 = new ConexaoParceira("localhost", 8891);
		Parceira p1 = new Parceira("parceira-fake1", "Teste Mocked", c1);
		MensagemBroker.getInstance().getParceiras().add(p1);
		
		ConexaoParceira c2 = new ConexaoParceira("localhost", 8892);
		Parceira p2 = new Parceira("parceira-fake2", "Teste Mocked", c2);
		MensagemBroker.getInstance().getParceiras().add(p2);
		
		mocker.replay();
		MensagemBasica retorno = MensagemBroker.getInstance().enviarParceira(entrada);
		mocker.verify();
		MatcherAssert.assertThat(expected, Matchers.equalTo(retorno));
	}

}
