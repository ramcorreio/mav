package com.stefanini.mav.mensagem;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.stefanini.mav.util.MensagemHelper;
import com.stefanini.mav.util.UtilsDate;

public class MensagemSerializeTest {
	
	@Test
	public void serialize450() throws IOException, URISyntaxException, ParseException, MensagemNaoEncontradaException {
		
		Cabecalho c = new Cabecalho();
		c.setTamanho(116);
		c.setCodigo(CodigoMensagem.C0450);
		c.setNumeroTransacao(980008);
		c.setNumeroProposta("");
		c.setCodigoUsuario("UILSON");
		c.setCodigoRetorno("");
		c.setCodigoLojista(170894002);
		c.setVersao("9");
		c.setCampoLojista("");
		
		SolicitacaoCapturaSimplificada scs = new SolicitacaoCapturaSimplificada(Long.toString(System.currentTimeMillis()), c);
		
		scs.setDadosPessoais(new DadoClienteBasico());
		scs.getDadosPessoais().setCpf("00000000191");
		scs.getDadosPessoais().setDataNascimento(UtilsDate.parse("01011960"));
		scs.getDadosPessoais().setFiller("");
		
		//validação de dados operação cartão
		scs.setDadosOperacaoCartao(new DadoOperacaoCartao());
		scs.getDadosOperacaoCartao().setCodigoOrg("");
		scs.getDadosOperacaoCartao().setCodigoLogo("");
		scs.getDadosOperacaoCartao().setCodigoCampanha("");
		scs.getDadosOperacaoCartao().setCodigoModalidade("");
		scs.getDadosOperacaoCartao().setFiller("");
		
		//validação de Dados Complementares
		scs.setComplemento(new DadoComplementar());
		scs.getComplemento().setEmancipado(false);
		scs.getComplemento().setCodigoProduto("01");
		
		//validação outros indicadores
		scs.setIndicadores(new Indicador());
		scs.getIndicadores().setIdentificadorCanal("T");
		scs.getIndicadores().setVersaoCanal("");
		scs.getIndicadores().setPolitica("");
		scs.getIndicadores().setAmbiente("");
		

		String expected = MensagemHelper.lerMensagem(CodigoMensagem.C0450, "criarCapturaSimplicada.1");
		ContextoMensagem<SolicitacaoCapturaSimplificada> cscs = MensagemFactory.loadContexto(scs.getCabecalho().getCodigo());
		String mSerialized = cscs.escrever(scs);
		MatcherAssert.assertThat(mSerialized.substring(0, 5), Matchers.is(expected.substring(0, 5)));
		MatcherAssert.assertThat(mSerialized, Matchers.is(Matchers.equalTo(expected)));
	}
	
	@Test
	public void serialize460() throws IOException, URISyntaxException, ParseException, MensagemNaoEncontradaException {
		
		Cabecalho atual = new Cabecalho();
		atual.setTamanho(863);
		atual.setCodigo(CodigoMensagem.C0460);
		atual.setNumeroTransacao(980008);
		atual.setNumeroProposta("P4201170358");
		atual.setCodigoUsuario("UILSON");
		atual.setCodigoRetorno("A0062");
		atual.setCodigoLojista(170894002);
		atual.setVersao("9");
		atual.setCampoLojista("");
		
		RespostaCapturaSimplificada rcs = new RespostaCapturaSimplificada(Long.toString(System.currentTimeMillis()), atual);
		
		rcs.setFiller("");
		rcs.setMensagemAutorizador("Xx");
		rcs.setData(UtilsDate.parseDateHora("25082015180815"));
		rcs.setCodigoStatusProposta("02");
		rcs.setParecer("");
		rcs.setProduto("01");
		

		//Dados de cliente
		rcs.setDadosPessoais(new DadoCliente());
		rcs.getDadosPessoais().setCpf("00000000191");
		rcs.getDadosPessoais().setDataNascimento(UtilsDate.parse("20101944"));
		rcs.getDadosPessoais().setComplemento(new DadoComplementar());
		rcs.getDadosPessoais().getComplemento().setEmancipado(false);
		rcs.getDadosPessoais().getComplemento().setCodigoProduto("01");
		rcs.getDadosPessoais().setCobraTac(false);
		rcs.getDadosPessoais().setElegibilidadeSeguro(true);
		rcs.getDadosPessoais().setCodigoProdutoLosango("HSSOR002");
		rcs.getDadosPessoais().setQtdNumeroSorte(0);
		rcs.getDadosPessoais().setFiller("");
		
		//validação de dados operação cartão
		rcs.setDadosOperacaoCartao(new DadoOperacaoCartao());
		rcs.getDadosOperacaoCartao().setCodigoOrg("");
		rcs.getDadosOperacaoCartao().setCodigoLogo("");
		rcs.getDadosOperacaoCartao().setCodigoCampanha("");
		rcs.getDadosOperacaoCartao().setCodigoModalidade("");
		rcs.getDadosOperacaoCartao().setFiller("0000 0000 0000 0000           000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000   ".trim());
		
		//validação outros indicadores
		rcs.setIndicadores(new Indicador());
		rcs.getIndicadores().setIdentificadorCanal("T");
		rcs.getIndicadores().setVersaoCanal("1");
		rcs.getIndicadores().setPolitica("2");
		rcs.getIndicadores().setAmbiente("HO");
		
		
		String expected = MensagemHelper.lerMensagem(946, 460, "criarRespostaCapturaSimplicada.1");
		ContextoMensagem<RespostaCapturaSimplificada> crcs = MensagemFactory.loadContexto(rcs.getCabecalho().getCodigo());
		String mSerialized = crcs.escrever(rcs);
		MatcherAssert.assertThat(mSerialized.substring(0, 5), Matchers.is(expected.substring(0, 5)));
		MatcherAssert.assertThat(mSerialized, Matchers.is(Matchers.equalTo(expected)));
	}
}
