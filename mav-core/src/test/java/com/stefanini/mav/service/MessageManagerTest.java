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

import com.stefanini.mav.core.Mensagem;
import com.stefanini.mav.mensagem.CodigoMensagem;
import com.stefanini.mav.mensagem.ContextoMensagem;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;
import com.stefanini.mav.util.MensagemHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:mav-test-context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class MessageManagerTest {
	
	@Autowired
	private IGerenciaMensagem manager;	
	
	@Test
	public void verificarNotNull() {
		
		MatcherAssert.assertThat(manager, Matchers.notNullValue());
	}
	
	@Test
	public void salvarMensagem() throws IOException, URISyntaxException, MensagemNaoEncontradaException, BrokerException {
		
		String input = MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1");
		ContextoMensagem<MensagemBasica> ctx = MensagemFactory.loadContexto(CodigoMensagem.C0450);
		MensagemBasica m = ctx.ler(input);  
		Mensagem rm = manager.salvar(m);
		MatcherAssert.assertThat(rm.getId(), Matchers.notNullValue());
	}

}
