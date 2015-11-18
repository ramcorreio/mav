package com.stefanini.mav.mensagem;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.stefanini.mav.util.MensagemHelper;
import com.stefanini.mav.util.UtilsDate;

/**
 * 
 * Layout das mensagens está mav/mav-core/docs/TRS_CDC-EP_V9-Layout_Completo_082015.xls
 * 
 * @author Rodrigo
 *
 */
public class MensagemFactoryTest {
	
	@Test(expected = MensagemNaoEncontradaException.class)
	public void criarMensagemVazia() throws MensagemNaoEncontradaException {
		
		MensagemFactory.parse("");
	}
	
	@Test(expected = MensagemNaoEncontradaException.class)
	public void criarMensagemNula() throws MensagemNaoEncontradaException {
		
		MensagemFactory.parse(null);
	}
	
	@Test
	public void criarCapturaSimplicada() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException{
		
		String messagem = MensagemHelper.lerMensagem(199, 450, "criarCapturaSimplicada.1");
		
		SolicitacaoCapturaSimplificada m = (SolicitacaoCapturaSimplificada) MensagemFactory.parse(messagem);
		Cabecalho expected = new Cabecalho();
		expected.setTamanho(116);
		expected.setCodigo(CodigoMensagem.C0450);
		expected.setNumeroTransacao(980008);
		expected.setNumeroProposta("");
		expected.setCodigoUsuario("UILSON");
		expected.setCodigoRetorno("");
		expected.setCodigoLojista(170894002);
		expected.setVersao("9");
		expected.setCampoLojista("");
		
		MensagemHelper.verificarCabecalho(expected, m.getCabecalho());
		
		//validação de dados pessoais
		assertThat(m.getDadosPessoais(), notNullValue());
		assertThat(m.getDadosPessoais().getCpf(), is(equalTo("00000000191")));
		assertThat(m.getDadosPessoais().getDataNascimento(), is(equalTo(UtilsDate.parse("01011960"))));
		assertThat(m.getDadosPessoais().getFiller(), is(""));
		
		//validação de dados operação cartão
		assertThat(m.getDadosOperacaoCartao(), notNullValue());
		assertThat(m.getDadosOperacaoCartao().getCodigoOrg(), is(""));
		assertThat(m.getDadosOperacaoCartao().getCodigoLogo(), is(""));
		assertThat(m.getDadosOperacaoCartao().getCodigoCampanha(), is(""));
		assertThat(m.getDadosOperacaoCartao().getCodigoModalidade(), is(""));
		assertThat(m.getDadosOperacaoCartao().getFiller(), is(""));
		
		//validação de Dados Complementares
		assertThat(m.getComplemento(), notNullValue());
		assertThat(m.getComplemento().isClienteEmancipado(), is(false));
		assertThat(m.getComplemento().getCodigoProduto(), is("01"));
		
		//validação outros indicadores
		assertThat(m.getIndicadores(), notNullValue());
		assertThat(m.getIndicadores().getIdentificadorCanal(), is(equalTo("T")));
		assertThat(m.getIndicadores().getVersaoCanal(), is(""));
		assertThat(m.getIndicadores().getPolitica(), is(""));
		assertThat(m.getIndicadores().getAmbiente(), is(""));
	}
	
	@Test
	public void criarRespostaCapturaSimplicada() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException {
		
		String messagem = MensagemHelper.lerMensagem(946, 460, "criarRespostaCapturaSimplicada.1");
		assertThat(messagem, notNullValue());
		
		RespostaCapturaSimplificada m = (RespostaCapturaSimplificada) MensagemFactory.parse(messagem);
		assertThat(m, notNullValue());
		
		Cabecalho expected = new Cabecalho();
		expected.setTamanho(863);
		expected.setCodigo(CodigoMensagem.C0460);
		expected.setNumeroTransacao(980008);
		expected.setNumeroProposta("P4201170358");
		expected.setCodigoUsuario("UILSON");
		expected.setCodigoRetorno("A0062");
		expected.setCodigoLojista(170894002);
		expected.setVersao("9");
		expected.setCampoLojista("");
		
		MensagemHelper.verificarCabecalho(expected, m.getCabecalho());
		
		//DADOS DA CONSULTA					
		assertThat(m.getFiller(), is(""));
		assertThat(m.getMensagemAutorizador(), is("Xx"));
		assertThat(m.getData(), is(equalTo(UtilsDate.parseDateHora("25082015180815"))));
		assertThat(m.getCodigoStatusProposta(), is("02"));
		assertThat(m.getParecer(), is(""));
		assertThat(m.getProduto(), is("01"));
		
		//validação de dados cliente
		assertThat(m.getDadosPessoais(), notNullValue());
		assertThat(m.getDadosPessoais().getCpf(), is(equalTo("00000000191")));
		assertThat(m.getDadosPessoais().getDataNascimento(), is(equalTo(UtilsDate.parse("20101944"))));
		assertThat(m.getDadosPessoais().getComplemento(), notNullValue());
		assertThat(m.getDadosPessoais().getComplemento().isClienteEmancipado(), is(false));
		assertThat(m.getDadosPessoais().getComplemento().getCodigoProduto(), is("01"));
		assertThat(m.getDadosPessoais().isCobraTac(), is(equalTo(false)));
		assertThat(m.getDadosPessoais().isElegibilidadeSeguro(), is(equalTo(true)));
		assertThat(m.getDadosPessoais().getCodigoProdutoLosango(), is(equalTo("HSSOR002")));
		assertThat(m.getDadosPessoais().getQtdNumeroSorte(), is(equalTo(0)));
		assertThat(m.getDadosPessoais().getFiller(), is(""));
		
		//validação de dados operação cartão
		assertThat(m.getDadosOperacaoCartao(), notNullValue());
		assertThat(m.getDadosOperacaoCartao().getCodigoOrg(), is(""));
		assertThat(m.getDadosOperacaoCartao().getCodigoLogo(), is(""));
		assertThat(m.getDadosOperacaoCartao().getCodigoCampanha(), is(""));
		assertThat(m.getDadosOperacaoCartao().getCodigoModalidade(), is(""));
		assertThat(m.getDadosOperacaoCartao().getFiller(), is(equalTo("0000 0000 0000 0000           000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000   ".trim())));
		
		//validação outros indicadores
		assertThat(m.getIndicadores(), notNullValue());
		assertThat(m.getIndicadores().getIdentificadorCanal(), is(equalTo("T")));
		assertThat(m.getIndicadores().getVersaoCanal(), is("1"));
		assertThat(m.getIndicadores().getPolitica(), is("2"));
		assertThat(m.getIndicadores().getAmbiente(), is("HO"));
	}
	
	@Test
	@Ignore
	public void criarGeracaoToken() {
		
		Assert.fail("Não implementado.");
	}
	
	@Test
	@Ignore
	public void criarRespostaGeracaoToken() {
		
		Assert.fail("Não implementado.");
	}
	
	@Test
	@Ignore
	public void criarPropostaFinanciamento() {
		
		Assert.fail("Não implementado.");
	}
	
	@Test
	@Ignore
	public void criarRespostaPropostaFinanciamento() {
		
		Assert.fail("Não implementado.");
	}
}
