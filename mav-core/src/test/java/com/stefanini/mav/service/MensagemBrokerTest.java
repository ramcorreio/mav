package com.stefanini.mav.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.exparity.hamcrest.BeanMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.stefanini.mav.core.Mensagem;
import com.stefanini.mav.es.MapeamentoNaoEncontrado;
import com.stefanini.mav.mensagem.CodigoMensagem;
import com.stefanini.mav.mensagem.ContextoMensagem;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;
import com.stefanini.mav.mensagem.RespostaErro;
import com.stefanini.mav.service.MensagemBroker.MensagemErroBroker;
import com.stefanini.mav.service.ServiceLocator.Service;
import com.stefanini.mav.tcp.ConexaoParceira;
import com.stefanini.mav.util.MensagemHelper;

/**
 * 
 * Classe respponsável para controlar o fluxo de mensagens entre a losango e as lojas
 * 
 * @author Rodrigo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:mav-test-context.xml", "classpath:mav-daemon-fake-broker.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class MensagemBrokerTest {
	
	@Autowired
	private IGerenciaMensagem manager;
	
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
		expected.getCabecalho().setNumeroProposta(retorno.getCabecalho().getNumeroProposta());
		
		MatcherAssert.assertThat(retorno, BeanMatchers.theSameAs(expected));
	}
	
	@Test
	public void conexaoParceiraOK() throws MensagemNaoEncontradaException, IOException, URISyntaxException, BrokerException, MapeamentoNaoEncontrado {
		
		//IMocksControl mocker = EasyMock.createStrictControl();
		
		ContextoMensagem<MensagemBasica> ctxEntrada = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica entrada = ctxEntrada.ler(MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1"));
		
		ContextoMensagem<MensagemBasica> ctxExpected = MensagemFactory.loadContexto(CodigoMensagem.C0460);
		MensagemBasica expected = ctxExpected.ler(MensagemHelper.lerMensagem(CodigoMensagem.C0460, "criarRespostaCapturaSimplicada.1"));
		
		ConexaoParceira c = new ConexaoParceira("localhost", 8891);
		Parceira p = new Parceira("parceira-fake", "Teste Mocked", c);
		MensagemBroker.getInstance().setParceira(p);
		
		//mocker.replay();
		MensagemBasica retorno = MensagemBroker.getInstance().enviarParceira(entrada);
		//mocker.verify();
		MatcherAssert.assertThat(expected, Matchers.equalTo(retorno));
	}
	
	@Test
	public void conexaoDuasParceiras() throws MensagemNaoEncontradaException, IOException, URISyntaxException, BrokerException, MapeamentoNaoEncontrado {
		
		IMocksControl mocker = EasyMock.createStrictControl();
		
		ContextoMensagem<MensagemBasica> ctxEntrada = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica entrada = ctxEntrada.ler(MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1"));
		
		ContextoMensagem<MensagemBasica> ctxExpected = MensagemFactory.loadContexto(CodigoMensagem.C0460);
		MensagemBasica expected = ctxExpected.ler(MensagemHelper.lerMensagem(CodigoMensagem.C0460, "criarRespostaCapturaSimplicada.1"));
		
		//configurando parceira 1
		ConexaoParceira c1 = mocker.createMock(ConexaoParceira.class);
		Parceira p1 = new Parceira("parceira-fake1", "Teste Mocked", c1);
		MensagemBroker.getInstance().setParceira(p1);

		//configurando retorno
		c1.conectar();
		c1.enviar(entrada);
		
		RespostaErro erro = (RespostaErro) MensagemErroBroker.MSG_ERRO_RETORNO.wrap(entrada, null, new Object[]{"localhost", 8891});
		EasyMock.expect(c1.receber()).andReturn(erro);
		c1.fechar();
		
		//configurando parceira 2
		ConexaoParceira c2 = mocker.createMock(ConexaoParceira.class);
		Parceira p2 = new Parceira("parceira-fake2", "Teste Mocked", c2);
		MensagemBroker.getInstance().setParceira(p2);
		
		//configurando retorno
		c2.conectar();
		c2.enviar(entrada);
		
		EasyMock.expect(c2.receber()).andReturn(expected);
		c2.fechar();
		
		mocker.replay();
		MensagemBasica retorno = MensagemBroker.getInstance().enviarParceira(entrada);
		mocker.verify();
		MatcherAssert.assertThat(expected, Matchers.equalTo(retorno));
		
		//verificando dados do teste
		IGerenciaMensagem gerente = ServiceLocator.getInstance().getService(Service.GERENTE_MENSAGEM, IGerenciaMensagem.class);
		Mensagem rs = gerente.recuperarMensagem(entrada);
		MatcherAssert.assertThat(rs.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(rs.getParceiras().size(), Matchers.greaterThan(0));
		MatcherAssert.assertThat(rs.getParceiras().size(), Matchers.equalTo(2));
	}
	
	@Test
	public void conexaoDuasParceirasCom100() throws MensagemNaoEncontradaException, IOException, URISyntaxException, BrokerException, MapeamentoNaoEncontrado {
		
		IMocksControl mocker = EasyMock.createStrictControl();
		
		ContextoMensagem<MensagemBasica> ctxEntrada = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica entrada = ctxEntrada.ler(MensagemHelper.mudarTransacao(MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1")));
		
		ContextoMensagem<MensagemBasica> ctxExpected = MensagemFactory.loadContexto(CodigoMensagem.C0460);
		MensagemBasica expected = ctxExpected.ler(MensagemHelper.mudarTransacao(MensagemHelper.lerMensagem(CodigoMensagem.C0460, "criarRespostaCapturaSimplicada.1"), entrada.getCabecalho().getNumeroTransacao()));
		
		//configurando parceira 1
		ConexaoParceira c1 = mocker.createMock(ConexaoParceira.class);
		Parceira p1 = new Parceira("parceira-fake1", "Teste Mocked", c1);
		MensagemBroker.getInstance().setParceira(p1);

		//configurando retorno
		c1.conectar();
		c1.enviar(entrada);
		
		RespostaErro erro = (RespostaErro) MensagemErroBroker.MSG_ERRO_RETORNO.wrap(entrada, null, new Object[]{"localhost", 8991});
		EasyMock.expect(c1.receber()).andReturn(erro);
		c1.fechar();
		
		//configurando parceira 2
		ConexaoParceira c2 = mocker.createMock(ConexaoParceira.class);
		Parceira p2 = new Parceira("parceira-fake2", "Teste Mocked", c2);
		MensagemBroker.getInstance().setParceira(p2);
			
		//configurando retorno 450
		c2.conectar();
		c2.enviar(entrada);
		
		EasyMock.expect(c2.receber()).andReturn(expected);
		c2.fechar();
		
		//configurando entrega de mensagem 100
		ContextoMensagem<MensagemBasica> ctxEntrada100 = MensagemFactory.loadContexto(CodigoMensagem.C0100);
		MensagemBasica entrada100 = ctxEntrada100.ler(MensagemHelper.mudarTransacao(MensagemHelper.lerMensagem(CodigoMensagem.C0100, "criarPropostaFinanciamento.1")));
		MatcherAssert.assertThat(entrada100, Matchers.notNullValue());
		
		ContextoMensagem<MensagemBasica> ctxExpected110 = MensagemFactory.loadContexto(CodigoMensagem.C0110);
		MensagemBasica expected110 = ctxExpected110.ler(MensagemHelper.mudarTransacao(MensagemHelper.lerMensagem(CodigoMensagem.C0110, "criarRespostaPropostaFinanciamento.1"), entrada.getCabecalho().getNumeroTransacao()));
		MatcherAssert.assertThat(expected110, Matchers.notNullValue());
		
		//configurando retorno 100
		c2.conectar();
		c2.enviar(entrada100);
		
		EasyMock.expect(c2.receber()).andReturn(expected110);
		c2.fechar();
		
		mocker.replay();
		MensagemBasica retornoEntrada = MensagemBroker.getInstance().enviarParceira(entrada);
		MatcherAssert.assertThat(retornoEntrada, Matchers.equalTo(expected));
		
		//verificando dados do teste
		IGerenciaMensagem gerente = ServiceLocator.getInstance().getService(Service.GERENTE_MENSAGEM, IGerenciaMensagem.class);
		Mensagem rs = gerente.recuperarMensagem(entrada);
		MatcherAssert.assertThat(rs.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(rs.getParceiras().size(), Matchers.greaterThan(0));
		MatcherAssert.assertThat(rs.getParceiras().size(), Matchers.equalTo(2));
		
		MensagemBasica retorno100 = MensagemBroker.getInstance().enviarParceira(entrada100);
		MatcherAssert.assertThat(retorno100, Matchers.equalTo(expected110));
		
		Mensagem mensagemDb110 = manager.recuperarMensagem(retorno100);
		
		MatcherAssert.assertThat(mensagemDb110.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(mensagemDb110.getParceiras().size(), Matchers.greaterThan(0));
		MatcherAssert.assertThat(mensagemDb110.getParceiras().size(), Matchers.equalTo(1));
		MatcherAssert.assertThat(mensagemDb110.getParceiras().get(0).getChaveParceira(), Matchers.equalTo(p2.getId()));
		
		mocker.verify();
	}

}
