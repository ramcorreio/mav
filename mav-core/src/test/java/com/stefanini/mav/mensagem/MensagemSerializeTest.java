package com.stefanini.mav.mensagem;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.stefanini.mav.es.MapeamentoNaoEncontrado;
import com.stefanini.mav.util.MensagemHelper;
import com.stefanini.mav.util.UtilsDate;

public class MensagemSerializeTest {
	
	@Test
	public void serialize450() throws IOException, URISyntaxException, ParseException, MensagemNaoEncontradaException, MapeamentoNaoEncontrado {
		
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
		
		//Dados Pessoais
		scs.setDadosPessoais(new SolicitacaoCapturaSimplificada.DadoPessoal());
		scs.getDadosPessoais().setCpf("00000000191");
		scs.getDadosPessoais().setDataNascimento(UtilsDate.parse("01011960", UtilsDate.FormatadorData.DATA));
		scs.getDadosPessoais().setFiller("");
		
		//validação de dados operação cartão
		scs.setDadosOperacaoCartao(new SolicitacaoCapturaSimplificada.DadoOperacaoCartao());
		scs.getDadosOperacaoCartao().setCodigoOrg("");
		scs.getDadosOperacaoCartao().setCodigoLogo("");
		scs.getDadosOperacaoCartao().setCodigoCampanha("");
		scs.getDadosOperacaoCartao().setCodigoModalidade("");
		scs.getDadosOperacaoCartao().setFiller("");
		
		//validação de Dados Complementares
		//TODO: rever
		scs.setComplemento(new SolicitacaoCapturaSimplificada.DadoComplementar());
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
	public void serialize460() throws IOException, URISyntaxException, ParseException, MensagemNaoEncontradaException, MapeamentoNaoEncontrado {
		
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
		
		rcs.setDadosConsulta(new RespostaCapturaSimplificada.DadoConsulta());
		rcs.getDadosConsulta().setFiller("");
		rcs.getDadosConsulta().setMensagemAutorizador("Xx");
		rcs.getDadosConsulta().setData(UtilsDate.parse("25082015180815", UtilsDate.FormatadorData.DATA_TEMPO));
		rcs.getDadosConsulta().setCodigoStatusProposta(StatusProposta.ELEGIVEL);
		rcs.getDadosConsulta().setParecer("");
		rcs.getDadosConsulta().setProduto("01");
		

		//Dados de cliente
		rcs.setDadosCliente(new RespostaCapturaSimplificada.DadoCliente());
		rcs.getDadosCliente().setCpf("00000000191");
		rcs.getDadosCliente().setDataNascimento(UtilsDate.parse("20101944", UtilsDate.FormatadorData.DATA));
		rcs.getDadosCliente().setEmancipado(false);
		rcs.getDadosCliente().setCodigoProduto("01");
		rcs.getDadosCliente().setCobraTac(false);
		rcs.getDadosCliente().setElegibilidadeSeguro(true);
		rcs.getDadosCliente().setCodigoProdutoLosango("HSSOR002");
		rcs.getDadosCliente().setQtdNumeroSorte(0);
		rcs.getDadosCliente().setFiller("");
		
		//validação de dados operação cartão
		rcs.setDadosOperacaoCartao(new RespostaCapturaSimplificada.DadoOperacaoCartao());
		rcs.getDadosOperacaoCartao().setCodigoOrg("");
		rcs.getDadosOperacaoCartao().setCodigoLogo("");
		rcs.getDadosOperacaoCartao().setCodigoCampanha("");
		rcs.getDadosOperacaoCartao().setCodigoModalidade("");
		
		//validação de filler
		rcs.setFiller("0000 0000 0000 0000           000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000   ".trim());
		
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
