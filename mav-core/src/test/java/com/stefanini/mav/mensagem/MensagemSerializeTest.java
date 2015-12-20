package com.stefanini.mav.mensagem;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.text.ParseException;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.stefanini.mav.es.MapeamentoNaoEncontrado;
import com.stefanini.mav.util.MensagemHelper;

public class MensagemSerializeTest {
	
	@Test
	public void serialize450() throws IOException, URISyntaxException, ParseException, MensagemNaoEncontradaException, MapeamentoNaoEncontrado {
		
		String expected = MensagemHelper.lerMensagem(199, CodigoMensagem.C0450.toInt(), "criarCapturaSimplicada.1");
		SolicitacaoCapturaSimplificada scs = MensagemFactoryTest.criarSolicitacaoCapturaSimplificadaMensagem(expected);
		
		ContextoMensagem<SolicitacaoCapturaSimplificada> cscs = MensagemFactory.loadContexto(scs.getCabecalho().getCodigo());
		String mSerialized = cscs.escrever(scs);
		assertThat(mSerialized, expected);
	}
	
	@Test
	public void serialize460() throws IOException, URISyntaxException, ParseException, MensagemNaoEncontradaException, MapeamentoNaoEncontrado {
		
		String expected = MensagemHelper.lerMensagem(946, 460, "criarRespostaCapturaSimplicada.1");
		RespostaCapturaSimplificada rcs = MensagemFactoryTest.criarRespostaCapturaSimplicadaMensagem(expected);
		
		ContextoMensagem<RespostaCapturaSimplificada> crcs = MensagemFactory.loadContexto(rcs.getCabecalho().getCodigo());
		String mSerialized = crcs.escrever(rcs);
		assertThat(mSerialized, expected);
	}
	
	@Test
	public void serialize100() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException, MapeamentoNaoEncontrado {
		
		String expected = MensagemHelper.lerMensagem(2725, 100, "criarPropostaFinanciamento.1");
		PropostaFinanciamento pf = MensagemFactoryTest.criarPropostaFinanciamentoMensagem(expected);
		
		ContextoMensagem<PropostaFinanciamento> cpf = MensagemFactory.loadContexto(CodigoMensagem.C0100);
		String mSerialized = cpf.escrever(pf);
		assertThat(mSerialized, expected);
	}
	
	@Test
	public void serialize110() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException, MapeamentoNaoEncontrado, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		String expected = MensagemHelper.lerMensagem(5102, 110, "criarRespostaPropostaFinanciamento.1");
		RespostaPropostaFinanciamento rpf = MensagemFactoryTest.criarRespostaPropostaFinanciamentoMensagem(expected);
		
		ContextoMensagem<RespostaPropostaFinanciamento> crpf = MensagemFactory.loadContexto(CodigoMensagem.C0110);
		String mSerialized = crpf.escrever(rpf);
		assertThat(mSerialized, expected);
	}
	
	@Test
	public void serialize670() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException, MapeamentoNaoEncontrado {
		
		String expected = MensagemHelper.lerMensagem(157, CodigoMensagem.C0670.toInt(), "criarGeracaoToken.1");
		
		GeracaoToken gt = MensagemFactoryTest.criarGeracaoTokenMensagem(expected);
		ContextoMensagem<GeracaoToken> cgt = MensagemFactory.loadContexto(CodigoMensagem.C0670);
		String mSerialized = cgt.escrever(gt);
		assertThat(mSerialized, expected);
	}
	
	@Test
	public void serialize680() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException, MapeamentoNaoEncontrado {
		
		String expected = MensagemHelper.lerMensagem(669, CodigoMensagem.C0680.toInt(), "criarRespostaGeracaoToken.1");
		
		RespostaGeracaoToken rgt = MensagemFactoryTest.criarRespostaGeracaoTokenMensagem(expected);
		ContextoMensagem<RespostaGeracaoToken> crgt = MensagemFactory.loadContexto(CodigoMensagem.C0680);
		String mSerialized = crgt.escrever(rgt);
		assertThat(mSerialized, expected);
	}
	
	@Test
	public void serialize200() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException, MapeamentoNaoEncontrado {
		
		String expected = MensagemHelper.lerMensagem(97, CodigoMensagem.C0200.toInt(), "criarConsultaProposta.1");
		
		ConsultaProposta cp = MensagemFactoryTest.criarConsultaProposta(expected);
		ContextoMensagem<ConsultaProposta> ccp = MensagemFactory.loadContexto(CodigoMensagem.C0200);
		String mSerialized = ccp.escrever(cp);
		assertThat(mSerialized, expected);
	}
	
	@Test
	public void serialize210() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException, MapeamentoNaoEncontrado, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		String expected = MensagemHelper.lerMensagem(5102, CodigoMensagem.C0210.toInt(), "criarRespostaConsultaProposta.1");
		
		RespostaConsultaProposta rcp = MensagemFactoryTest.criarRespostaConsultaProposta(expected);
		ContextoMensagem<RespostaConsultaProposta> crcp = MensagemFactory.loadContexto(CodigoMensagem.C0210);
		String mSerialized = crcp.escrever(rcp);
		assertThat(mSerialized, expected);
	}
	
	private void assertThat(String actual, String expected) {
		
		try {
			MatcherAssert.assertThat(actual.length(), Matchers.is(Matchers.equalTo(expected.length())));
			MatcherAssert.assertThat(actual.substring(0, 5), Matchers.is(expected.substring(0, 5)));
			MatcherAssert.assertThat(actual, Matchers.is(Matchers.equalTo(expected)));
		}
		catch(AssertionError e) {
			
			for (int i = 0; i < actual.length(); i++) {
				if(actual.charAt(i) == expected.charAt(i)){
					continue;
				}
				
				throw new AssertionError("Posição " + i, e);
			}
		}
		
	}
}
