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

import org.exparity.hamcrest.BeanMatchers;
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
		//TODO: montar massa de testes
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
		
		
		esperado.setDadosProfissionais(new DadoProfissional());
		//0626 a 0633	Data de Admissão 	8	N	Data de Admissão na Empresa.                  		X
		esperado.getDadosProfissionais().setDataAdmissao(null);
		
		//0634 a 0663	Empresa	30	A	Empresa Em Que Trabalha o Cliente                              		X
		esperado.getDadosProfissionais().setEmpresa("JV TUBOS E ACABAMENTOS");
		
		esperado.getDadosProfissionais().setEndereco(new Endereco());
		//0664 a 0703	Logradouro	40	A	Logradouro onde Trabalha o Cliente		X
		esperado.getDadosProfissionais().getEndereco().setLogradouro("RUA JOAQUIM MURTINHO");
		
		//0704 a 0708	Numero	5	A	Numero do Logradouro		X
		esperado.getDadosProfissionais().getEndereco().setNumero("04665");
		
		//0709 a 0723	Complemento	15	A	Complemento do logradouro
		esperado.getDadosProfissionais().getEndereco().setComplemento("");
		
		//0724 a 0743	Bairro	20	A	Bairro onde Trabalha o Cliente		X
		esperado.getDadosProfissionais().getEndereco().setBairro("CIDADE MORENA");
		
		//0744 a 0763	Cidade	20	A	Cidade onde Trabalha o Cliente		X
		esperado.getDadosProfissionais().getEndereco().setCidade("CAMPO GRANDE");
		
		//0764 a 0765	UF	2	A	Unidade Federativa onde Trabalha o Cliente		X
		esperado.getDadosProfissionais().getEndereco().setUf("MS");
		
		//0766 a 0773	CEP	8	N	CEP onde trabalha o cliente		X
		esperado.getDadosProfissionais().getEndereco().setCep(79041904);
		
		esperado.getDadosProfissionais().setTelefone(new Telefone());
		//0774 a 0776	DDD	3	N	DDD da Cidade Onde Trabalha o Cliente		X
		esperado.getDadosProfissionais().getTelefone().setDdd(6);
		
		//0777 a 0785	Telefone	9	N	"Se o campo DDD estiver preenchido com 011 e o numero do telefone não iniciar 70, 75, 78 e 79 e for um numero de celular, o telefone deve ser iniciado com o numero ""9"", caso contrário deverá ser iniciado com o numero ""0"".
		esperado.getDadosProfissionais().getTelefone().setNumero(733481881);
		
		//0786 a 0789	Ramal	4	N	Ramal do Trabalho do Cliente
		esperado.getDadosProfissionais().getTelefone().setRamal(0000);
		
		//0790 a 0800	Valor Renda Líquida 	11	N	Renda Líquida do Cliente (em R$)                                              		X
		esperado.getDadosProfissionais().setRendaLiquida(130000);
		
		//0801 a 0820	Cargo	20	A	Cargo do Cliente	Ver tabela de Dominio Cargo	X
		esperado.getDadosProfissionais().setCargoCliente("ADVOGADA");
		
		//0821 a 0840	Profissão	20	A	Profissão do Cliente	Ver tabela de dominio Profissão	X
		esperado.getDadosProfissionais().setProfissaoCliente("ASSALARIADO COM CART");
		
		//0841 a 0841	Aposentado	1	A	"Aponta se o cliente é aposentado:
		//S - Sim; N - Não"	"S"  "N"	X
		esperado.getDadosProfissionais().setAposentado(false);
		
		//0842 a 0842	Pensionista	1	A	"Aponta se o cliente é Pensionista:
		//S - Sim; N - Não"	"S"  "N"	X
		esperado.getDadosProfissionais().setPensionista(false);
		
		//0843 a 0843	Uso exclusivo da Losango	1	A	Uso exclusivo da Losango
		esperado.getDadosProfissionais().setLosango("");
		
		//0844 a 0845	Orgão Beneficio	2	A		Ver tabela de dominio Orgao Beneficio	X. Se Aposentado ou Pensionista = SIM
		esperado.getDadosProfissionais().setOpcaoBeneficio("");
		
		//0846 a 0865	Número do benefício	20	A			X. Se Aposentado ou Pensionista = SIM
		esperado.getDadosProfissionais().setNumeroBeneficio("");
		
		//0866 a 0871	Data do Comprovante de Renda	6	A	Mes/Ano do comprovante de Renda apresentado pelo Cliente	MMAAAA	X, salvo se o Tipo de Comprovante de Renda = "N"
		esperado.getDadosProfissionais().setDataComprovanteRenda(UtilsDate.parse("062015", UtilsDate.FORMATADOR_DATA_CURTA));
		
		//0872 a 0873	Tipo Comprovante de Renda	2	A		Ver tabela Dominio tipo C Renda	X
		esperado.getDadosProfissionais().setTipoComprovanteRenda("H");
		
		//0874 a 0875	Ocupação nova	2	A	Código da Profissão	Ver tabela Dominio Código da Profissão	X
		esperado.getDadosProfissionais().setOcupacaoNova("03");
		
		//0876 a 0889	Cnpj Cliente	14	A			X, se Empresario ou Proprietario
		esperado.getDadosProfissionais().setCnpjCliente("");
		
		//0890 a 0915	Filler	26	A
		esperado.getDadosProfissionais().setFiller(ContextoMensagem.escreverString(26, " "));

		
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
		assertThat(esperado.getDadosPessoais(), BeanMatchers.theSameAs(m.getDadosPessoais()).excludeProperty("telefone"));
		
		
		assertThat(esperado.getDadosProfissionais().getEndereco(), Matchers.samePropertyValuesAs(m.getDadosProfissionais().getEndereco()));
		assertThat(esperado.getDadosProfissionais().getTelefone(), Matchers.samePropertyValuesAs(m.getDadosProfissionais().getTelefone()));
		assertThat(esperado.getDadosProfissionais(), BeanMatchers.theSameAs(m.getDadosProfissionais()));
		
		assertThat(esperado, BeanMatchers.theSameAs(m));
	}
	
	@Test
	@Ignore
	public void criarRespostaPropostaFinanciamento() {
		
		Assert.fail("Não implementado.");
	}
}
