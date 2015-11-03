package com.stefanini.mav.mensagem;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import com.stefanini.mav.util.MensagemHelper;

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
	public void criarMensagemSimplificada450() throws IOException, URISyntaxException, MensagemNaoEncontradaException{
		
		String messagem = MensagemHelper.lerMensagem(186, 450, "criarMensagemSimplificada450.1");
		
		MensagemBasica m = MensagemFactory.parse(messagem);
		Assert.assertEquals(TipoMensagem.C0450, m.getCabecalho().getTipo());
		Assert.assertEquals(Integer.valueOf(116), m.getCabecalho().getTamanho());
		
	}

}
