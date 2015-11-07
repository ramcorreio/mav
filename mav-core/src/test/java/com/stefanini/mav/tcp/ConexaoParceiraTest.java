package com.stefanini.mav.tcp;

import java.io.IOException;
import java.net.URISyntaxException;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.stefanini.mav.mensagem.CodigoMensagem;
import com.stefanini.mav.mensagem.MensagemBasica;
import com.stefanini.mav.mensagem.MensagemFactory;
import com.stefanini.mav.mensagem.MensagemNaoEncontradaException;
import com.stefanini.mav.util.MensagemHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:mav-test-context-fake.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class ConexaoParceiraTest {
	
	private ConexaoParceira conexao;
	
	@Before
	public void setUp() throws IOException {
		
		conexao = new ConexaoParceira("localhost", 8890);
		conexao.conectar();
	}
	
	@After
	public void tearDown() throws IOException {
		
		conexao.fechar();
		
	}
	
	@Test
	public void enviarMensagem() throws MensagemNaoEncontradaException, IOException, URISyntaxException {
		
		MensagemBasica paraEnviar = MensagemFactory.parse(MensagemHelper.lerMensagem(CodigoMensagem.C0450, "EchoConexaoParceira.1"));
		conexao.envar(paraEnviar);
		MensagemBasica recebida = conexao.receber();
		MatcherAssert.assertThat(recebida, Matchers.notNullValue());
		MatcherAssert.assertThat(recebida.getCabecalho().getCodigo(), Matchers.is(Matchers.equalTo(CodigoMensagem.C0460)));
	}

}
