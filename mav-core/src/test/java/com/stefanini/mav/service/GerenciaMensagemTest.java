package com.stefanini.mav.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.TransactionSystemException;

import com.stefanini.mav.core.Mensagem;
import com.stefanini.mav.core.MensagemParceira;
import com.stefanini.mav.es.MapeamentoNaoEncontrado;
import com.stefanini.mav.mensagem.CodigoMensagem;
import com.stefanini.mav.mensagem.ContextoMensagem;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;
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
		MatcherAssert.assertThat(rm.getNumeroProposta(), Matchers.equalTo("G" + input.substring(9, 15)));
	}
	
	@Test(expected = TransactionSystemException.class)
	public void salvarMensagemDuplicada() throws IOException, URISyntaxException, MensagemNaoEncontradaException, BrokerException, MapeamentoNaoEncontrado {
		
		String input = MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1");
		input = MensagemHelper.mudarTransacao(input);
		ContextoMensagem<MensagemBasica> ctx = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica m = ctx.ler(input);
		
		Mensagem rm = manager.salvar(m);
		MatcherAssert.assertThat(rm.getId(), Matchers.notNullValue());
		
		rm = manager.salvar(m);
	}
	
	@Test(expected = TransactionSystemException.class)
	public void salvarPropostaDuplicada() throws IOException, URISyntaxException, MensagemNaoEncontradaException, BrokerException, MapeamentoNaoEncontrado {
		
		String input = MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1");
		input = MensagemHelper.mudarTransacao(input);
		ContextoMensagem<MensagemBasica> ctx = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica m = ctx.ler(input);
		
		m.getCabecalho().setNumeroProposta("TESTE");
		Mensagem rm = manager.salvar(m);
		MatcherAssert.assertThat(rm.getId(), Matchers.notNullValue());
		MatcherAssert.assertThat(rm.getId(), Matchers.is(Matchers.greaterThan(0l)));
		
		rm = manager.salvar(m);
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
}
