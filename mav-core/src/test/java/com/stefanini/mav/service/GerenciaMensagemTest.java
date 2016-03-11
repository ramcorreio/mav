package com.stefanini.mav.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.stefanini.mav.core.Mensagem;
import com.stefanini.mav.core.MensagemParceira;
import com.stefanini.mav.es.MapeamentoNaoEncontrado;
import com.stefanini.mav.mensagem.CodigoMensagem;
import com.stefanini.mav.mensagem.ContextoMensagem;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;
import com.stefanini.mav.mensagem.RespostaErro;
import com.stefanini.mav.service.MensagemBroker.MensagemErroBroker;
import com.stefanini.mav.tcp.ConexaoParceira;
import com.stefanini.mav.util.MensagemHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:mav-test-context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class GerenciaMensagemTest {
	
	@Autowired
	private IGerenciaMensagem manager;	
	
	@Test
	public void verificarNotNull() {
		
		MatcherAssert.assertThat(manager, Matchers.notNullValue());
	}
	
	@Test
	public void salvarMensagem() throws IOException, URISyntaxException, MensagemNaoEncontradaException, BrokerException, MapeamentoNaoEncontrado {
		
		String input = MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1");
		input = MensagemHelper.mudarTransacao(input);
		ContextoMensagem<MensagemBasica> ctx = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica m = ctx.ler(input);
		
		Mensagem rm = manager.salvar(m);
		MatcherAssert.assertThat(rm.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(rm.getNumeroProposta(), Matchers.is(Matchers.isEmptyString()));
	}
	
	@Test
	public void existeMensagem() throws IOException, URISyntaxException, MensagemNaoEncontradaException, BrokerException, MapeamentoNaoEncontrado {
		
		String input = MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1");
		input = MensagemHelper.mudarTransacao(input);
		ContextoMensagem<MensagemBasica> ctx = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica m = ctx.ler(input);
		
		Mensagem rm = manager.salvar(m);
		MatcherAssert.assertThat(rm.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(rm.getNumeroProposta(), Matchers.is(Matchers.isEmptyString()));
		
		if(!manager.existe(m)) {
			Assert.fail("Mensagem não existe");
		}
	}
	
	@Test
	public void salvarMensagemDuplicada() throws IOException, URISyntaxException, MensagemNaoEncontradaException, BrokerException, MapeamentoNaoEncontrado {
		
		String input = MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1");
		input = MensagemHelper.mudarTransacao(input);
		ContextoMensagem<MensagemBasica> ctx = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica m = ctx.ler(input);
		
		int qtd = manager.contarMensagens();
		
		Mensagem rm = manager.salvar(m);
		MatcherAssert.assertThat(rm.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(qtd + 1, Matchers.equalTo(manager.contarMensagens()));
		
		qtd = manager.contarMensagens();
		rm = manager.salvar(m);
		MatcherAssert.assertThat(rm.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(qtd, Matchers.equalTo(manager.contarMensagens()));
	}
	
	@Test
	public void salvarPropostaDuplicada() throws IOException, URISyntaxException, MensagemNaoEncontradaException, BrokerException, MapeamentoNaoEncontrado {
		
		String input = MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1");
		input = MensagemHelper.mudarTransacao(input);
		ContextoMensagem<MensagemBasica> ctx = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica m = ctx.ler(input);
		
		int qtd = manager.contarMensagens();
		m.getCabecalho().setNumeroProposta("TESTE");
		Mensagem rm = manager.salvar(m);
		MatcherAssert.assertThat(rm.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(rm.getId(), Matchers.is(Matchers.greaterThan(0l)));
		MatcherAssert.assertThat(qtd + 1, Matchers.is(Matchers.equalTo(manager.contarMensagens())));
		
		qtd = manager.contarMensagens();
		rm = manager.salvar(m);
		MatcherAssert.assertThat(rm.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(rm.getId(), Matchers.is(Matchers.greaterThan(0l)));
		MatcherAssert.assertThat(qtd, Matchers.is(Matchers.equalTo(manager.contarMensagens())));
	}
	
	@Test
	public void gravarMensagemParceira() throws IOException, URISyntaxException, MensagemNaoEncontradaException, BrokerException, MapeamentoNaoEncontrado {
		
		String input = MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1");
		input = MensagemHelper.mudarTransacao(input);
		ContextoMensagem<MensagemBasica> ctx = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica m = ctx.ler(input);
		
		Parceira parceira = new Parceira("testeid", "teste", new ConexaoParceira("local", 10000)) ;
		Mensagem rm = manager.salvar(m);
		MatcherAssert.assertThat(rm.getId(), Matchers.notNullValue());
		
		MensagemParceira mp = manager.gravarMensagemParceira(rm, parceira);
		MatcherAssert.assertThat(mp.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(mp.getChaveParceira(), Matchers.equalTo("testeid"));
		MatcherAssert.assertThat(mp.getMensagens().size(), Matchers.greaterThan(0));
	}
	
	@Test
	public void gravarMensagemDuasParceira() throws IOException, URISyntaxException, MensagemNaoEncontradaException, BrokerException, MapeamentoNaoEncontrado {
		
		String input = MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1");
		input = MensagemHelper.mudarTransacao(input);
		ContextoMensagem<MensagemBasica> ctx = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica m = ctx.ler(input);
		
		Mensagem rm = manager.salvar(m);
		MatcherAssert.assertThat(rm.getId(), Matchers.notNullValue());
		
		Parceira parceira1 = new Parceira("testeid1", "teste 2", new ConexaoParceira("local", 10000)) ;
		MensagemParceira mp1 = manager.gravarMensagemParceira(rm, parceira1);
		MatcherAssert.assertThat(mp1.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(mp1.getChaveParceira(), Matchers.equalTo("testeid1"));
		MatcherAssert.assertThat(mp1.getMensagens().size(), Matchers.greaterThan(0));
		MatcherAssert.assertThat(mp1.getMensagens().size(), Matchers.equalTo(1));
		rm = manager.recuperarMensagem(m);
		MatcherAssert.assertThat(rm.getParceiras().size(), Matchers.greaterThan(0));
		MatcherAssert.assertThat(rm.getParceiras().size(), Matchers.equalTo(1));
		
		Parceira parceira2 = new Parceira("testeid2", "teste 2", new ConexaoParceira("local", 10001)) ;
		MensagemParceira mp2 = manager.gravarMensagemParceira(rm, parceira2);
		MatcherAssert.assertThat(mp2.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(mp2.getChaveParceira(), Matchers.equalTo("testeid2"));
		MatcherAssert.assertThat(mp2.getMensagens().size(), Matchers.greaterThan(0));
		MatcherAssert.assertThat(mp2.getMensagens().size(), Matchers.equalTo(1));
		rm = manager.recuperarMensagem(m);
		MatcherAssert.assertThat(rm.getParceiras().size(), Matchers.greaterThan(0));
		MatcherAssert.assertThat(rm.getParceiras().size(), Matchers.equalTo(2));
	}
	
	@Test
	public void gravarMensagem100() throws IOException, URISyntaxException, MensagemNaoEncontradaException, BrokerException, MapeamentoNaoEncontrado {
		
		String input = MensagemHelper.lerMensagem(CodigoMensagem.C0100, "criarPropostaFinanciamento.2");
		input = MensagemHelper.mudarTransacao(input);
		ContextoMensagem<MensagemBasica> ctx = MensagemFactory.loadContexto(CodigoMensagem.C0100);
		MensagemBasica m = ctx.ler(input);
		
		Parceira parceira = new Parceira("testeid", "teste", new ConexaoParceira("local", 10000)) ;
		Mensagem rm = manager.salvar(m);
		MatcherAssert.assertThat(rm.getId(), Matchers.notNullValue());
		
		MensagemParceira mp = manager.gravarMensagemParceira(rm, parceira);
		MatcherAssert.assertThat(mp.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(mp.getChaveParceira(), Matchers.equalTo("testeid"));
		MatcherAssert.assertThat(mp.getMensagens().size(), Matchers.greaterThan(0));
	}
	
	/**
	 * verificar a quantas parceiras a mensagem foi associada
	 */
	@Test
	public void verificarProcessadas() throws IOException, URISyntaxException, MensagemNaoEncontradaException, BrokerException, MapeamentoNaoEncontrado {
		
		String input = MensagemHelper.lerMensagem(CodigoMensagem.C0100, "criarPropostaFinanciamento.2");
		input = MensagemHelper.mudarTransacao(input);
		ContextoMensagem<MensagemBasica> ctx = MensagemFactory.loadContexto(CodigoMensagem.C0100);
		MensagemBasica m = ctx.ler(input);
		
		int qtd = manager.contarProcessadas();
		
		//primeira parceira
		Parceira parceira1 = new Parceira("testeid", "teste", new ConexaoParceira("local", 10000)) ;
		Mensagem rm = manager.salvar(m);
		MensagemParceira mp1 = manager.gravarMensagemParceira(rm, parceira1);
		MatcherAssert.assertThat(rm.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(mp1.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(qtd, Matchers.is(Matchers.greaterThan(0)));
		MatcherAssert.assertThat(qtd + 1, Matchers.is(Matchers.equalTo(manager.contarProcessadas())));
		
		//segunda parceira
		Parceira parceira2 = new Parceira("testeid", "teste", new ConexaoParceira("local", 10001)) ;
		MensagemParceira mp2 = manager.gravarMensagemParceira(rm, parceira2);
		MatcherAssert.assertThat(mp2.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(qtd + 2, Matchers.is(Matchers.equalTo(manager.contarProcessadas())));
	}
	
	/**
	 * verificar a quantas parceiras a mensagem foi associada
	 */
	@Test
	public void recuperarParceira() throws IOException, URISyntaxException, MensagemNaoEncontradaException, BrokerException, MapeamentoNaoEncontrado {
		
		List<Parceira> parceiras = new LinkedList<Parceira>();
		
		//configurando parceira 1
		ConexaoParceira c1 = new ConexaoParceira("local", 9990);
		Parceira p1 = new Parceira("parceira-fake1", "Teste Mocked", c1);
		parceiras.add(p1);
		
		ContextoMensagem<MensagemBasica> ctxEntrada = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica entrada = ctxEntrada.ler(MensagemHelper.mudarTransacao(MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1")));
		RespostaErro entradaError = (RespostaErro) MensagemErroBroker.MSG_ERRO_RETORNO.wrap(entrada, null, p1);
		
		Mensagem rm = manager.salvar(entrada);
		MensagemParceira mp1 = manager.gravarMensagemParceira(rm, p1);
		Mensagem expectedrm = manager.salvar(entradaError);
		MensagemParceira expectedmp1 = manager.gravarMensagemParceira(expectedrm, p1);
		expectedrm = manager.recuperarMensagem(entradaError);
		
		MatcherAssert.assertThat(rm.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(mp1.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(mp1.getMensagens().size(), Matchers.is(Matchers.greaterThan(0)));
		
		MatcherAssert.assertThat(expectedrm.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(expectedmp1.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(expectedrm.getParceiras().size(), Matchers.is(Matchers.greaterThan(0)));
		
		//configurando parceira 2
		ConexaoParceira c2 = new ConexaoParceira("local", 9991);
		Parceira p2 = new Parceira("parceira-fake2", "Teste Mocked 2", c2);
		parceiras.add(p2);
		
		ContextoMensagem<MensagemBasica> ctxExpected = MensagemFactory.loadContexto(CodigoMensagem.C0460);
		MensagemBasica expected = ctxExpected.ler(MensagemHelper.mudarTransacao(MensagemHelper.lerMensagem(CodigoMensagem.C0460, "criarRespostaCapturaSimplicada.1"), entrada.getCabecalho().getNumeroTransacao()));
		
		Mensagem rm2 = manager.salvar(entrada);
		MensagemParceira mp2 = manager.gravarMensagemParceira(rm2, p2);
		Mensagem expectedrm2 = manager.salvar(expected);
		MensagemParceira expectedmp2 = manager.gravarMensagemParceira(expectedrm2, p2);
		expectedrm2 = manager.recuperarMensagem(expected);
		
		
		MatcherAssert.assertThat(rm2.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(mp2.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(mp2.getMensagens().size(), Matchers.is(Matchers.greaterThan(0)));
		
		MatcherAssert.assertThat(expectedrm2.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(expectedmp2.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(expectedrm2.getParceiras().size(), Matchers.is(Matchers.greaterThan(0)));
		
		//configurando parceira 3
		ConexaoParceira c3 = new ConexaoParceira("local", 9992);
		Parceira p3 = new Parceira("parceira-fake3", "Teste Mocked 3", c3);
		parceiras.add(p3);
		
		ContextoMensagem<MensagemBasica> ctx100 = MensagemFactory.loadContexto(CodigoMensagem.C0100);
		MensagemBasica entrada100 = ctx100.ler(MensagemHelper.mudarTransacao(MensagemHelper.lerMensagem(CodigoMensagem.C0100, "criarPropostaFinanciamento.2")));
		//igualando número de proposta para fazer sentido a localização da 460
		entrada100.getCabecalho().setNumeroProposta(expected.getCabecalho().getNumeroProposta());
		
		//verificando lozalização da parceira
		String id = manager.recuperarParceira(entrada100);
		MatcherAssert.assertThat(p2.getId(), Matchers.is(Matchers.equalTo(id)));
		Parceira target = MensagemBroker.localizarParceira(id, parceiras);
		MatcherAssert.assertThat(p2.getId(), Matchers.is(Matchers.equalTo(target.getId())));
		
		/*Mensagem rm100 = manager.salvar(entrada);
		MensagemParceira mp100 = manager.gravarMensagemParceira(rm100, p2);
		Mensagem expectedrm100 = manager.salvar(expected);
		MensagemParceira expectedmp100 = manager.gravarMensagemParceira(rm100, p2);*/
		
		
		
		
		
		
		/*int qtd = manager.contarProcessadas();
		
		//primeira parceira
		Parceira parceira1 = new Parceira("testeid", "teste", new ConexaoParceira("local", 10000)) ;
		Mensagem rm = manager.salvar(m);
		MensagemParceira mp1 = manager.gravarMensagemParceira(rm, parceira1);
		MatcherAssert.assertThat(rm.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(mp1.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(qtd, Matchers.is(Matchers.greaterThan(0)));
		MatcherAssert.assertThat(qtd + 1, Matchers.is(Matchers.equalTo(manager.contarProcessadas())));
		
		//segunda parceira
		Parceira parceira2 = new Parceira("testeid", "teste", new ConexaoParceira("local", 10001)) ;
		MensagemParceira mp2 = manager.gravarMensagemParceira(rm, parceira2);
		MatcherAssert.assertThat(mp2.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(qtd + 2, Matchers.is(Matchers.equalTo(manager.contarProcessadas())));*/
	}
}
