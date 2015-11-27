package com.stefanini.mav.mensagem;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.stefanini.mav.mensagem.Cabecalho.Fluxo;
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
	public void lerStringCheia() {
		
		String entrada = "008630460980008P4201170358    UILSON  A00621708940029";
		String saida = ContextoMensagem.lerStringCheia(entrada, 30, 8);
		MatcherAssert.assertThat("UILSON  ", Matchers.equalTo(saida));
	}
	
	@Test
	public void lerString() {
		
		String entrada = "008630460980008P4201170358    UILSON  A00621708940029";
		String saida = ContextoMensagem.lerString(entrada, 30, 8);
		MatcherAssert.assertThat("UILSON", Matchers.equalTo(saida));
	}
	
	@Test
	public void lerInt() {
		
		String entrada = "008630460980008P4201170358    UILSON  A00621708940029";
		Integer saida = ContextoMensagem.lerInt(entrada, 5, 7);
		MatcherAssert.assertThat(460980, Matchers.equalTo(saida));
	}
	
	@Test
	public void lerBooleanTrue() {
		
		String entrada = "008630460980008P4201170358    UILSON  A00621708940029";
		Boolean saida = ContextoMensagem.lerBoolean(entrada, 19);
		MatcherAssert.assertThat(true, Matchers.equalTo(saida));
	}
	
	@Test
	public void lerBooleanFalse() {
		
		String entrada = "008630460980008P4201170358    UILSON  A00621708940029";
		Boolean saida = ContextoMensagem.lerBoolean(entrada, 1);
		MatcherAssert.assertThat(false, Matchers.equalTo(saida));
	}
	
	@Test
	public void lerData() throws ParseException {
		
		Date expected = UtilsDate.parse("20151808");
		
		String entrada = "Xx   2508201518081502                    ";
		Date saida = ContextoMensagem.lerData(entrada, 9);
		MatcherAssert.assertThat(expected, Matchers.equalTo(saida));
	}
	
	//m.getDadosPessoais().setDataNascimento(UtilsDate.parse(input.substring(94, 102)));
	
	@Test
	public void escreverString() {
		
		String entrada = "MAV";
		String expected = entrada.concat("  ");
		String saida = ContextoMensagem.escreverString(5, entrada);
		
		MatcherAssert.assertThat(expected, Matchers.equalTo(saida));
	}
	
	
	
	
	
	private SolicitacaoCapturaSimplificada montarSolicitacaoCapturaSimplificada() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException {
		
		String mensagem = MensagemHelper.lerMensagem(199, 450, "criarCapturaSimplicada.1");
		
		SolicitacaoCapturaSimplificada m = (SolicitacaoCapturaSimplificada) MensagemFactory.parse(mensagem);
		MensagemHelper.verificarTamanho(mensagem, m);
		
		Cabecalho expected = new Cabecalho();
		expected.setTamanho(116);
		expected.setCodigo(CodigoMensagem.C0450);
		expected.setNumeroTransacao(980008);
		expected.setNumeroProposta("");
		expected.setCodigoUsuario("UILSON");
		expected.setCodigoRetorno("");
		expected.setCodigoLojista(170894002);
		expected.setVersao("9");
		expected.setCampoLojista(ContextoMensagem.escreverString(30, " "));
		
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
		assertThat(m.getComplemento().isEmancipado(), is(false));
		assertThat(m.getComplemento().getCodigoProduto(), is("01"));
		
		//validação outros indicadores
		assertThat(m.getIndicadores(), notNullValue());
		assertThat(m.getIndicadores().getIdentificadorCanal(), is(equalTo("T")));
		assertThat(m.getIndicadores().getVersaoCanal(), is(""));
		assertThat(m.getIndicadores().getPolitica(), is(""));
		assertThat(m.getIndicadores().getAmbiente(), is(""));
		return m;
	}
	
	
	@Test
	public void gerarErroCapturaSimplicada() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException{
	
		SolicitacaoCapturaSimplificada m = montarSolicitacaoCapturaSimplificada();
		assertThat(m, notNullValue());
		
		String descricao = "Erro de solicitação";
		RespostaErro erro = (RespostaErro) MensagemFactory.gerarErro(m, descricao);
		MatcherAssert.assertThat(CodigoMensagem.C9450, Matchers.is(Matchers.equalTo(erro.getCabecalho().getCodigo())));
		MatcherAssert.assertThat(descricao, Matchers.is(Matchers.equalTo(erro.getDescricao())));
	}
	
	@Test
	public void criarCapturaSimplicada() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException{
		
		montarSolicitacaoCapturaSimplificada();
	}
	
	@Test
	public void criarRespostaCapturaSimplicada() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException {
		
		String mensagem = MensagemHelper.lerMensagem(946, 460, "criarRespostaCapturaSimplicada.1");
		assertThat(mensagem, notNullValue());
		
		RespostaCapturaSimplificada m = (RespostaCapturaSimplificada) MensagemFactory.parse(mensagem);
		assertThat(m, notNullValue());
		
		MensagemHelper.verificarTamanho(mensagem, m);
		Cabecalho expected = new Cabecalho();
		expected.setTamanho(863);
		expected.setCodigo(CodigoMensagem.C0460);
		expected.setNumeroTransacao(980008);
		expected.setNumeroProposta("P4201170358");
		expected.setCodigoUsuario("UILSON");
		expected.setCodigoRetorno("A0062");
		expected.setCodigoLojista(170894002);
		expected.setVersao("9");
		expected.setCampoLojista(ContextoMensagem.escreverString(30, " "));
		
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
		assertThat(m.getDadosPessoais().getComplemento().isEmancipado(), is(false));
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
		assertThat(m.getIndicadores().getVersaoCanal(), is(ContextoMensagem.escreverString(10, "1")));
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
	public void criarPropostaFinanciamento() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException {
		
		String mensagem = MensagemHelper.lerMensagem(2725, 100, "criarPropostaFinanciamento.1");
		assertThat(mensagem, notNullValue());
		
		PropostaFinanciamento m = (PropostaFinanciamento) MensagemFactory.parse(mensagem);
		assertThat(m, notNullValue());
		
		Cabecalho cabecalhoEsperado = new Cabecalho();
		cabecalhoEsperado.setSentidoFluxo(Fluxo.ENTRADA);
		cabecalhoEsperado.setTamanho(2642);
		cabecalhoEsperado.setCodigo(CodigoMensagem.C0100);
		cabecalhoEsperado.setNumeroTransacao(980009);
		cabecalhoEsperado.setNumeroProposta("P4201170358");
		cabecalhoEsperado.setCodigoUsuario("UILSON");
		cabecalhoEsperado.setCodigoRetorno("");
		cabecalhoEsperado.setCodigoLojista(170894002);
		cabecalhoEsperado.setVersao("9");
		cabecalhoEsperado.setCampoLojista("     TOP 01       6  06       ");
		
		PropostaFinanciamento esperado = new PropostaFinanciamento(ContextoMensagem.md5(mensagem), cabecalhoEsperado);
		
		//validação de dados pessoais
		esperado.setDadosPessoais(new DadoClienteDetalhado());
		esperado.getDadosPessoais().setTipoPersonalidade("F");
		esperado.getDadosPessoais().setCpf("00000000000");
		esperado.getDadosPessoais().setUsuarioCpf("T");
		esperado.getDadosPessoais().setDocumentoIdentificacao(new Documento());
		esperado.getDadosPessoais().getDocumentoIdentificacao().setNuDocIdentificacao("201570496  0");
		esperado.getDadosPessoais().getDocumentoIdentificacao().setTpDocIdentificacao("01");
		esperado.getDadosPessoais().getDocumentoIdentificacao().setOrgaoEmissor("SSP");
		esperado.getDadosPessoais().getDocumentoIdentificacao().setUfOrgaoEmissor("MS");
		esperado.getDadosPessoais().getDocumentoIdentificacao().setDataEmissao(UtilsDate.parse("06072001"));
		
		esperado.getDadosPessoais().setConjugeCompoeRenda(false);
		esperado.getDadosPessoais().setNome("PROPOSTA TESTE");
		esperado.getDadosPessoais().setLocalNascimento("CAMPO GRANDE");
		esperado.getDadosPessoais().setDataNascimento(UtilsDate.parse("20101944"));
		esperado.getDadosPessoais().setSexo("F");
		esperado.getDadosPessoais().setNacionalidade("0");
		esperado.getDadosPessoais().setNaturalidade("CAMPO GRANDE");
		esperado.getDadosPessoais().setNomeMae("MARIA DO CARMO PINHEIRO NE");
		esperado.getDadosPessoais().setNomePai("LEANDRO NE");
		esperado.getDadosPessoais().setCarteiraProfissional(ContextoMensagem.lerInt("00000", 0, 5));
		esperado.getDadosPessoais().setSerieCarteiraProfissional("00000");
		esperado.getDadosPessoais().setEstadoCivil(1);
		
		esperado.getDadosPessoais().setEndereco(new Endereco());
		esperado.getDadosPessoais().getEndereco().setLogradouro("RUA ITAQUERA");
		esperado.getDadosPessoais().getEndereco().setNumero("68");
		esperado.getDadosPessoais().getEndereco().setComplemento("");
		esperado.getDadosPessoais().getEndereco().setBairro("FLAMBOYANT");
		esperado.getDadosPessoais().getEndereco().setCidade("CAMPO GRANDE");
		esperado.getDadosPessoais().getEndereco().setUf("MS");
		esperado.getDadosPessoais().getEndereco().setCep(23585361);
		
		esperado.getDadosPessoais().setTelefone(new Telefone());
		esperado.getDadosPessoais().getTelefone().setDdd(6);
		esperado.getDadosPessoais().getTelefone().setNumero(730264981);
		esperado.getDadosPessoais().getTelefone().setRamal(0);
		
		esperado.getDadosPessoais().setTipoTelefone(1);
		esperado.getDadosPessoais().setTipoResidencia(1);
		esperado.getDadosPessoais().setResideDesde(UtilsDate.parse("01052008"));
		
		esperado.getDadosPessoais().setCelular(new Telefone());
		esperado.getDadosPessoais().getCelular().setDdd(6);
		esperado.getDadosPessoais().getCelular().setNumero(792169260);
		
		esperado.getDadosPessoais().setEmail("tpne@hotmail.com");
		esperado.getDadosPessoais().setPossuiPatrimonio(false);
		esperado.getDadosPessoais().setPatrimonio(new LinkedList<Patrimonio>());
		/*esperado.getDadosPessoais().getPatrimonio().add(new Patrimonio());
		esperado.getDadosPessoais().getPatrimonio().get(0).setNome("Meu");
		esperado.getDadosPessoais().getPatrimonio().get(0).setTipo("Opa");
		esperado.getDadosPessoais().getPatrimonio().get(0).setValor(100.00);
		esperado.getDadosPessoais().getPatrimonio().get(0).setOrigem("1");*/
		
		esperado.getDadosPessoais().setFiller("");
		esperado.getDadosPessoais().setCodigoPais("");
		esperado.getDadosPessoais().setUFNaturalidade("MS");
		esperado.getDadosPessoais().getDocumentoIdentificacao().setDataVancimento(null);
		esperado.getDadosPessoais().setEmancipado(false);
		esperado.getDadosPessoais().setFiller2("");
		


		
		//0300 a 0339	Logradouro	40	A	Logradouro da residência do cliente		X
		//0340 a 0344	Numero	5	A	Numero do logradouro		X
		//0345 a 0359	Complemento	15	A	Complemento do logradouro		
		//0360 a 0379	Bairro	20	A	Bairro endereço residencial		X
		//0380 a 0399	Cidade	20	A	Cidade endereço residencial		X
		//0400 a 0401	UF	2	A	Abreviação do Estado onde o cliente reside		X
		//0402 a 0409	CEP	8	N	CEP endereço residencial		X
		//0410 a 0412	DDD	3	N	DDD telefone residencial		X
		//0413 a 0421	Telefone	9	N	"Se o campo DDD estiver preenchido com 011 e o numero do telefone não iniciar 70, 75, 78 e 79 e for um numero de celular, o telefone deve ser iniciado com o numero ""9"", caso contrário deverá ser iniciado com o numero ""0"".
		//Exemplos:
		//1º) DDD=011 e numero = 8240-3043 ==> 98240-3043
		//2º) DDD=011 e numero = 7040-3043 ==> 07040-3043
		//3º) DDD=011 e numero = 3043-5322 ==> 03043-5322
		//4º) DDD=021 e numero = 8243-5322 ==> 08243-5322
		//5º) DDD=021 e numero = 3043-5322 ==> 03043-5322"		X
		//0422 a 0425	Ramal	4	N	Ramal do telefone residencial do cliente		
		//0426 a 0426	Tipo Telefone	1	N	Informar o Tipo de telefone	Ver tabela de dominio Tipo de Telefone	X
		//0427 a 0427	Tipo Residencia	1	N	Informar o Tipo de Residencia	Ver tabela de dominio Tip de Residencia	X
		//0428 a 0433	Reside desde	6	N	MMAAAA		
		//0434 a 0436	DDD Celular	3	N			X.
		//0437 a 0445	Telefone Celular	9	N	"Se o campo DDD estiver preenchido com 011 e o numero do telefone não iniciar 70, 75, 78 e 79 e for um numero de celular, o telefone deve ser iniciado com o numero ""9"", caso contrário deverá ser iniciado com o numero ""0"".
		//Exemplos:
		//1º) DDD=011 e numero = 8240-3043 ==> 98240-3043
		//2º) DDD=011 e numero = 7040-3043 ==> 07040-3043
		//3º) DDD=011 e numero = 3043-5322 ==> 03043-5322
		//4º) DDD=021 e numero = 8243-5322 ==> 08243-5322
		//5º) DDD=021 e numero = 3043-5322 ==> 03043-5322"		X.
		//0446 a 0505	Email	60	A			
		//0506 a 0506	Cliente Possui Patrimonio?	1	N	Campo que o Clinte indica se possui Patrimonios, caso a opção escolhida seja “Sim”, deverão ser informados pelo menos um Patrimonio, composto por : “Tipo de Patrimônio”, “Patrimônio” e “Valor do Patrimônio”.	 0 - Não     1 - Sim 	
		//0507 a 0507	Tipo de Patrimonio 1	1	A	Deverá ser informado o Tipo de Patrimônio	"“1” - “Bens Móveis” e/ou
		//“2” - “Bens Imóveis”"	Enviar em branco se o "cliente possue patrimonio = 0"
		//0508 a 0512	Patrimonio 1	5	A	Dever ser informado a característica do patrimônio. 	Tabela de Dominio Patrimonio	Enviar em branco se o "cliente possue patrimonio = 0"
		//0513 a 0523	Valor do Patrimonio 1	11	N	Dever informado o valor do Bem	9 inteiros e 2 decimais	Enviar em zerado se o "cliente possue patrimonio = 0"
		//0524 a 0524	Tipo de Patrimonio 2	1	A	Deverá ser informado o Tipo de Patrimônio	"“1” - “Bens Móveis” e/ou
		//“2” - “Bens Imóveis”"	Enviar em branco se o "cliente possue patrimonio = 0"
		//0525 a 0529	Patrimonio 2	5	A	Dever ser informado a característica do patrimônio. 	Tabela de Dominio Patrimonio	Enviar em branco se o "cliente possue patrimonio = 0"
		//0530 a 0540	Valor do Patrimonio 2	11	N	Dever informado o valor do Bem	9 inteiros e 2 decimais	Enviar em zerado se o "cliente possue patrimonio = 0"
		//0541 a 0541	Tipo de Patrimonio 3	1	A	Deverá ser informado o Tipo de Patrimônio	"“1” - “Bens Móveis” e/ou
		//“2” - “Bens Imóveis”"	Enviar em branco se o "cliente possue patrimonio = 0"
		//0542 a 0546	Patrimonio 3	5	A	Dever ser informado a característica do patrimônio. 	Tabela de Dominio Patrimonio	Enviar em branco se o "cliente possue patrimonio = 0"
		//0547 a 0557	Valor do Patrimonio 3	11	N	Dever informado o valor do Bem	9 inteiros e 2 decimais	Enviar em zerado se o "cliente possue patrimonio = 0"
		//0558 a 0558	Tipo de Patrimonio 4	1	A	Deverá ser informado o Tipo de Patrimônio	"“1” - “Bens Móveis” e/ou
		//“2” - “Bens Imóveis”"	Enviar em branco se o "cliente possue patrimonio = 0"
		//0559 a 0563	Patrimonio 4	5	A	Dever ser informado a característica do patrimônio. 	Tabela de Dominio Patrimonio	Enviar em branco se o "cliente possue patrimonio = 0"
		//0564 a 0574	Valor do Patrimonio 4	11	N	Dever informado o valor do Bem	9 inteiros e 2 decimais	Enviar em zerado se o "cliente possue patrimonio = 0"
		//0575 a 0575	Filler	1	A			
		//0576 a 0577	Código do País (informar se nacionalidade = estrangeiro)	2	A	Tabela de Paises	Ver tabela de Dominio Paises	X. Se Nacionalidade = Estrangeiro
		//0578 a 0579	Código da UF da Naturalidade (informar se nacionalidade = brasileiro)	2	A	Tabela de UF	Ver tabela de dominio UF	X. Se Nacionalidade = Brasileiro
		//0580 a 0587	Data de Vencimento do Documento de identificação	8	A			X, se o tipo do documento for: 02, 03 e 09
		//0588 a 0588	Flag Emancipado	1	A		0 - Nao 1 - Sim	X
		//0589 a 0589	Origem do patrimonio 1	1	A			
		//0590 a 0590	Origem do patrimonio 2	1	A			
		//0591 a 0591	Origem do patrimonio 3	1	A			
		//0592 a 0592	Origem do patrimonio 4	1	A
		
		//assertThat(m.getDadosPessoais().getComplemento(), notNullValue());
		//assertThat(m.getDadosPessoais().getComplemento().isClienteEmancipado(), is(false));
		//assertThat(m.getDadosPessoais().getComplemento().getCodigoProduto(), is("01"));
		//assertThat(m.getDadosPessoais().isCobraTac(), is(equalTo(false)));
		//assertThat(m.getDadosPessoais().isElegibilidadeSeguro(), is(equalTo(true)));
		//assertThat(m.getDadosPessoais().getCodigoProdutoLosango(), is(equalTo("HSSOR002")));
		//assertThat(m.getDadosPessoais().getQtdNumeroSorte(), is(equalTo(0)));
		//assertThat(m.getDadosPessoais().getFiller(), is(""));
		
		//validação de dados operação cartão
		/*assertThat(m.getDadosOperacaoCartao(), notNullValue());
		assertThat(m.getDadosOperacaoCartao().getCodigoOrg(), is(""));
		assertThat(m.getDadosOperacaoCartao().getCodigoLogo(), is(""));
		assertThat(m.getDadosOperacaoCartao().getCodigoCampanha(), is(""));
		assertThat(m.getDadosOperacaoCartao().getCodigoModalidade(), is(""));
		assertThat(m.getDadosOperacaoCartao().getFiller(), is(equalTo("0000 0000 0000 0000           000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000   ".trim())));*/
		
		//validação outros indicadores
		esperado.setIndicadores(new Indicador());
		esperado.getIndicadores().setIdentificadorCanal("T");
		esperado.getIndicadores().setVersaoCanal("");
		esperado.getIndicadores().setPolitica("");
		esperado.getIndicadores().setAmbiente("");
		
		assertThat(esperado.getCabecalho(), Matchers.samePropertyValuesAs(m.getCabecalho()));
		assertThat(esperado.getDadosPessoais().getDocumentoIdentificacao(), Matchers.samePropertyValuesAs(m.getDadosPessoais().getDocumentoIdentificacao()));
		assertThat(esperado.getDadosPessoais().getEndereco(), Matchers.samePropertyValuesAs(m.getDadosPessoais().getEndereco()));
		assertThat(esperado.getDadosPessoais().getTelefone(), Matchers.samePropertyValuesAs(m.getDadosPessoais().getTelefone()));
		assertThat(esperado.getDadosPessoais().getCelular(), Matchers.samePropertyValuesAs(m.getDadosPessoais().getCelular()));
		assertThat(esperado.getDadosPessoais(), Matchers.samePropertyValuesAs(m.getDadosPessoais()));
		assertThat(esperado, Matchers.samePropertyValuesAs(m));
	}
	
	@Test
	@Ignore
	public void criarRespostaPropostaFinanciamento() {
		
		Assert.fail("Não implementado.");
	}
}
