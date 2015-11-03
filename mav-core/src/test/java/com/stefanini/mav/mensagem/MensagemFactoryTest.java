package com.stefanini.mav.mensagem;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import com.stefanini.mav.util.MensagemHelper;

/**
 * 
 * Layout das mensagens está mav/mav-core/docs/TRS_CDC-EP_V9-Layout_Completo_082015.xls
 * 
 * @author Rodrigo
 *
 */
public class MensagemFactoryTest {
	
	
	
	@Test
	public void getNumTransacao() {
		
		Assert.assertEquals(6, MensagemHelper.getNumTransacao().length());
	}
	
	@Test(expected = MensagemNaoEncontradaException.class)
	public void criarMensagemVazia() throws MensagemNaoEncontradaException {
		
		MensagemFactory.parse("");
	}
	
	@Test(expected = MensagemNaoEncontradaException.class)
	public void criarMensagemNula() throws MensagemNaoEncontradaException {
		
		MensagemFactory.parse(null);
	}
	
	@Test
	public void criarCapturaSimplicada() throws IOException, URISyntaxException, MensagemNaoEncontradaException{
		
		String messagem = MensagemHelper.lerMensagem(186, 450, "criarCapturaSimplicada.1");
		
		MensagemBasica m = MensagemFactory.parse(messagem);
		Assert.assertEquals(TipoMensagem.C0450, m.getCabecalho().getTipo());
		Assert.assertEquals(Integer.valueOf(116), m.getCabecalho().getTamanho());
		
	}
	
	@Test
	public void criarRespostaCapturaSimplicada() {
		
		Assert.fail("Não implementado.");
	}
	
	@Test
	public void criarGeracaoToken() {
		
		Assert.fail("Não implementado.");
	}
	
	@Test
	public void criarRespostaGeracaoToken() {
		
		Assert.fail("Não implementado.");
	}
	
	@Test
	public void criarPropostaFinanciamento() {
		
		Assert.fail("Não implementado.");
	}
	
	@Test
	public void criarRespostaPropostaFinanciamento() {
		
		Assert.fail("Não implementado.");
	}
}
