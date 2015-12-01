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
	

	@Test
	public void lerDouble() throws ParseException {
		
		String input = "0649000";
		Double expected = 6.49;
		Double value = ContextoMensagem.lerDouble(input, 0,	7, 5);
		
		MatcherAssert.assertThat(value, Matchers.equalTo(expected));
	}
	
	
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
		assertThat(m.getDadosPessoais().getFiller(), is(ContextoMensagem.escreverString(40, " ")));
		
		//validação de dados operação cartão
		assertThat(m.getDadosOperacaoCartao(), notNullValue());
		assertThat(m.getDadosOperacaoCartao().getCodigoOrg(), is(""));
		assertThat(m.getDadosOperacaoCartao().getCodigoLogo(), is(""));
		assertThat(m.getDadosOperacaoCartao().getCodigoCampanha(), is(""));
		assertThat(m.getDadosOperacaoCartao().getCodigoModalidade(), is(""));
		assertThat(m.getDadosOperacaoCartao().getFiller(), is(ContextoMensagem.escreverString(28, " ")));
		
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
	@Ignore
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
	@Ignore
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
		esperado.setDadosPessoais(new DadoPessoalDetalhado());
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
		esperado.getDadosProfissionais().setCargo("ADVOGADA");
		
		//0821 a 0840	Profissão	20	A	Profissão do Cliente	Ver tabela de dominio Profissão	X
		esperado.getDadosProfissionais().setProfissao("ASSALARIADO COM CART");
		
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
		esperado.getDadosProfissionais().setDataComprovanteRenda(UtilsDate.parse("062015", UtilsDate.FormatadorData.DATA_CURTA));
		
		//0872 a 0873	Tipo Comprovante de Renda	2	A		Ver tabela Dominio tipo C Renda	X
		esperado.getDadosProfissionais().setTipoComprovanteRenda("H");
		
		//0874 a 0875	Ocupação nova	2	A	Código da Profissão	Ver tabela Dominio Código da Profissão	X
		esperado.getDadosProfissionais().setOcupacaoNova("03");
		
		//0876 a 0889	Cnpj Cliente	14	A			X, se Empresario ou Proprietario
		esperado.getDadosProfissionais().setCnpj("");
		
		//0890 a 0915	Filler	26	A
		esperado.getDadosProfissionais().setFiller(ContextoMensagem.escreverString(26, " "));
		
		
		esperado.setDadosConjuge(new DadoConjuge());
		esperado.getDadosConjuge().setDadoProfissional(new DadoProfissionalBasico());
		//0916 a 0945	Nome Do Cônjuge	30	A	Nome do Cônjuge do Cliente		X. Se conjuge compoe renda = 1 ou Estado Civil = 2
		esperado.getDadosConjuge().setNome("");
		
		//0946 a 0985	Local de Nascimento	40	A
		esperado.getDadosConjuge().setLocalNascimento("");
		
		//0986 a 0993	Data de Nascimento	8	N	Data nascimento do cônjuge.		X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().setDataNascimento(null);
		
		//0994 a 1004	CPF	11	N	CPF do Cônjuge		X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().setCpf("00000000000");
		
		esperado.getDadosConjuge().setDocumentoIdentificacao(new Documento());
		//1005 a 1014	Identidade / RG	10	A	Número da Identidade do Cônjuge		X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getDocumentoIdentificacao().setNuDocIdentificacao("");
		
		//1015 a 1016	Tipo de Documento	2	A		Ver tabela de dominio TP  DOCUMENTO IDENTIDADE	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getDocumentoIdentificacao().setTpDocIdentificacao("");
		
		//1017 a 1021	Órgão Emissor	5	A	Órgão Emissor do Documento de Identidade do Cônjuge	Ver tabela de dominio Orgão Emissor	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getDocumentoIdentificacao().setOrgaoEmissor("");
		
		//1022 a 1023	UF Órgão Emissor	2	A		Ver tabela de dominio UF	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getDocumentoIdentificacao().setUfOrgaoEmissor("");
		
		//1024 a 1031	Data Emissão	8	N	Data de emissão do documento do Cônjuge		X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getDocumentoIdentificacao().setDataEmissao(null);
		
		//1032 a 1056	Empresa 	25	A	Empresa Em Que o Cônjuge Trabalha		X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getDadoProfissional().setEmpresa("");
		
		//1057 a 1064	Data  Admissão	8	N	Data da Admissão na Empresa		X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getDadoProfissional().setDataAdmissao(null);
		
		esperado.getDadosConjuge().getDadoProfissional().setEndereco(new Endereco());
		//1065 a 1104	Logradouro	40	A	Logradouro do trabalho do conjuge		X. Se conjuge compoe renda = 1
		//1105 a 1109	Numero	5	A	Numero do Logradouro		X. Se conjuge compoe renda = 1
		//1110 a 1124	Complemento	15	A	Complemento do Logradouro
		//1125 a 1144	Bairro	20	A	Bairro onde trabalha o cônjuge		X. Se conjuge compoe renda = 1 
		//1145 a 1164	Cidade	20	A	Cidade Onde Trabalha o cônjuge		X. Se conjuge compoe renda = 1 
		//1165 a 1166	UF	2	A	Abreviatura da Unidade Federativa 		X. Se conjuge compoe renda = 1 
		//1167 a 1174	CEP	8	N	CEP do endereço comercial do cônjuge		X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getDadoProfissional().getEndereco().setLogradouro("");
		esperado.getDadosConjuge().getDadoProfissional().getEndereco().setNumero("");
		esperado.getDadosConjuge().getDadoProfissional().getEndereco().setComplemento("");
		esperado.getDadosConjuge().getDadoProfissional().getEndereco().setBairro("");
		esperado.getDadosConjuge().getDadoProfissional().getEndereco().setCidade("");
		esperado.getDadosConjuge().getDadoProfissional().getEndereco().setUf("");
		esperado.getDadosConjuge().getDadoProfissional().getEndereco().setCep(null);
		
		 
		esperado.getDadosConjuge().getDadoProfissional().setTelefone(new Telefone());
		//1175 a 1177	DDD	3	N	DDD da Cidade Onde Trabalha o cônjuge		X. Se conjuge compoe renda = 1 
		//1178 a 1186	Telefone	9	N	"Se o campo DDD estiver preenchido com 011 e o numero do telefone não iniciar 70, 75, 78 e 79 e for um numero de celular, o telefone deve ser iniciado com o numero ""9"", caso contrário deverá ser iniciado com o numero ""0"".
		//1187 a 1190	Ramal	4	N	Ramal do Trabalho do cônjuge
		esperado.getDadosConjuge().getDadoProfissional().getTelefone().setDdd(0);
		esperado.getDadosConjuge().getDadoProfissional().getTelefone().setNumero(0);
		esperado.getDadosConjuge().getDadoProfissional().getTelefone().setRamal(0);
				
		//1191 a 1210	Cargo	20	A	Cargo do Cônjuge	Ver tabela de Dominio Cargo	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getDadoProfissional().setCargo("");
		
		//1211 a 1230	Profissão	20	A	Profissão do Conjuge	Ver tabela de dominio Profissão	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getDadoProfissional().setProfissao("");
		
		//1231 a 1231	Aposentado	1	A	"Aponta se o cliente é aposentado:
		//S - Sim; N - Não"	"S"  "N"	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getDadoProfissional().setAposentado(false);
		
		//1232 a 1232	Pensionista	1	A	"Aponta se o cliente é Pensionista:
		//S - Sim; N - Não"	"S"  "N"	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getDadoProfissional().setPensionista(false);
		
		//1233 a 1233	Uso exclusivo da Losango	1	A	Uso exclusivo da Losango
		esperado.getDadosConjuge().getDadoProfissional().setLosango("");
		
		//1234 a 1244	Valor Renda Líquida 	11	N	Renda Líquida do Cônjuge (em R$)                                                  		X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getDadoProfissional().setRendaLiquida(0);

		//1245 a 1314	Patrimônio	70	A			
		esperado.getDadosConjuge().setPatrimonio(new LinkedList<Patrimonio>());

		//1315 a 1315	Nacionalidade	1	N	Nacionalidade do Conjuge	"0-Brasileiro
		//1-Estrangeiro        "	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().setNacionalidade("");
		
		//1316 a 1317	Código do País (informar se nacionalidade = estrangeiro)	2	A	Tabela de Paises	Ver tabela de Dominio Paises	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().setCodigoPais("");
		
		//1318 a 1319	Código da UF da Naturalidade (informar se nacionalidade = brasileiro)	2	A	Tabela de UF	Ver tabela de dominio UF	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().setUFNaturalidade("");
		
		//1320 a 1325	Data do Comprovante de Renda	6	A	Mes/Ano do comprovante de Renda apresentado pelo Conjuge	MMAAAA	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getDadoProfissional().setDataComprovanteRenda(null);
		
		//1326 a 1327	Tipo Comprovante de Renda	2	A		Ver tabela Dominio tipo C Renda	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getDadoProfissional().setTipoComprovanteRenda("");
		
		//1328 a 1329	Ocupação nova	2	A	Código da Profissão	Ver tabela Dominio Código da Profissão	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getDadoProfissional().setOcupacaoNova("");
		
		//1330 a 1330	Sexo do Conjuge	1	A	M – Masculino     F – Feminino         	“M”   ”F”	X. Se conjuge compoe renda = 1 ou Estado Civil = 2
		esperado.getDadosConjuge().setSexo("");
		
		//1331 a 1344	CNPJ Conjuge	14	A
		esperado.getDadosConjuge().getDadoProfissional().setCnpj("");
		
		//1331 a 1364	Filler	20	A			X, se Empresario ou Proprietario
		esperado.getDadosConjuge().setFiller("");
		
		//DADOS COMPLEMENTARES
		//1365 a 1366	Escolaridade	2	A	Codigo da Escolaridade	Ver tabela de dominio Escolaridade
		esperado.setEscolaridade(11);
		
		//1367 a 1386	Formação	20	A	Formação
		esperado.setFormacao("OK");
		
		//1387 a 1387	Indicador Possui cartão	1	N	Indicador se possui cartão	0 - Não 1 - Sim	X
		esperado.setPossuiCartao(false);
		
		//1388 a 1388	Indicador Possui veículo próprio	1	N	Indicador Possui veículo próprio	0 - Não 1 - Sim	X
		esperado.setPossuiVeiculoProprio(false);
		
		//1389 a 1398	Placa	10	A
		esperado.setPlaca("");
		
		//1399 a 1458	Renavam	60	A
		esperado.setRenavam("");
		
		//1459 a 1459	Indicador Possui veículo quitado	1	N	Indicador Possui veículo quitado	0 - Não 1 - Sim	X
		esperado.setVeiculoQuitado(false);
		
		//1460 a 1460	Possui experiencia de crédito	1	N	Indicador Possui experiência	0 - Não 1 - Sim	X
		esperado.setPossuiExperienciaCredito(false);
		
		//1461 a 1480	Local da Experiência	20	A			X. Se Possui experiencia de crédito = 1
		esperado.setLocalExperienciaCredito(null);
		
		//1481 a 1482	Plano da Experiência	2	N			X. Se Possui experiencia de crédito = 1
		esperado.setPlanoExperienciaCredito(null);
		
		//1483 a 1497	Valor da Prestação da Experiência	15	N			X. Se Possui experiencia de crédito = 1
		esperado.setValorPrestacao(null);
		
		//1498 a 1503	Inicio da Experiência de Crédito	6	N	Inicio da Experiência MMAAAA		X. Se Possui experiencia de crédito = 1
		esperado.setInicioExperienciaCredito(null);
		
		//1504 a 1543	Classificação do Cliente	40	A
		esperado.setClassificacaoCliente("");
		
		//1544 a 1544	Indicador Possui Cartão Financeira	1	N	Indicador Possui Cartão Financeira	0 - Não 1 - Sim	X
		esperado.setPossuiCartaoFinanceira(false);
		
		//1545 a 1545	Indicador Possui Conta Corrente	1	N	Indicador Possui Conta Corrente	0 - Não 1 - Sim	X
		esperado.setPossuiContaCorrente(false);
		
		//1546 a 1546	Indicador Possui dependente	1	N		0 - Não 1 - Sim	X
		esperado.setPossuiDependente(false);
		
		//1547 a 1548	Quantidade de dependentes	2	N			X. Se Indicador Possui dependente = 1
		esperado.setQuantidadeDependente(0);
		
		//1549 a 1563	Nome do cartão	15	A
		esperado.setNomeCartao("");
		
		//1564 a 1564	indicadorCapturarFoto	1	A	Indicador da captura da Foto do Cliente.	""" "" Documentos capturados com sucesso ou Lojista sem captura digital com a Losango
		//1 - Problemas Técnicos
		//2 - Cliente não autoriza
		//3 - Outros"	X
		esperado.setIndicadorCapturarFoto("");
		
		//1565 a 1565	indicadorCapturarDocumento	1	A	Indicador da captura do Documento.	""" "" Documentos capturados com sucesso ou Lojista sem captura digital com a Losango
		//1 - Problemas Técnicos
		//2 - Cliente não autoriza
		//3 - Outros"	X
		esperado.setIndicadorCapturarDocumento("");
		
		//1566 a 1566	indicadorCapturarBiometria	1	A	Indicador da captura da Biometria	""" "" Documentos capturados com sucesso ou Lojista sem captura digital com a Losango
		//1 - Problemas Técnicos
		//2 - Cliente não autoriza
		//3 - Outros"	X
		esperado.setIndicadorCapturarBiometria("");
		
		//1567 a 1613	Filler	47	A
		esperado.setFillerDadosComplementares(ContextoMensagem.escreverString(47, " "));
		
		//REFERÊNCIAS PESSOAIS
		esperado.setReferenciasPessoais(new LinkedList<Referencia>());
		//1614 a 1643	Nome	30	A	Nome da Pessoa de Referência		X
		//1644 a 1646	DDD	3	N	DDD da Pessoa de Referência		X
		//1647 a 1655	Telefone	9	N	"Se o campo DDD estiver preenchido com 011 e o numero do telefone não iniciar 70, 75, 78 e 79 e for um numero de celular, o telefone deve ser iniciado com o numero ""9"", caso contrário deverá ser iniciado com o numero ""0"".
		//Exemplos:
		//1º) DDD=011 e numero = 8240-3043 ==> 98240-3043
		//2º) DDD=011 e numero = 7040-3043 ==> 07040-3043
		//3º) DDD=011 e numero = 3043-5322 ==> 03043-5322
		//4º) DDD=021 e numero = 8243-5322 ==> 08243-5322
		//5º) DDD=021 e numero = 3043-5322 ==> 03043-5322"		X
		//1656 a 1659	Ramal	4	N	Ramal da Pessoa de Referência
		esperado.getReferenciasPessoais().add(new Referencia());
		esperado.getReferenciasPessoais().get(0).setTelefone(new Telefone());
		esperado.getReferenciasPessoais().get(0).setNome("");
		//esperado.getReferenciasPessoais().get(0).getTelefone().setDdd();
		//esperado.getReferenciasPessoais().get(0).getTelefone().setNumero(lerInt(input, 1646, 9));
		//esperado.getReferenciasPessoais().get(0).getTelefone().setRamal(lerInt(input, 1655, 4));
		
				
		//1660 a 1689	Nome	30	A	Nome da Pessoa de Referência		X
		//1690 a 1692	DDD	3	N	DDD da Pessoa de Referência		X
		//1693 a 1701	Telefone	9	N	"Se o campo DDD estiver preenchido com 011 e o numero do telefone não iniciar 70, 75, 78 e 79 e for um numero de celular, o telefone deve ser iniciado com o numero ""9"", caso contrário deverá ser iniciado com o numero ""0"".
		//Exemplos:
		//1º) DDD=011 e numero = 8240-3043 ==> 98240-3043
		//2º) DDD=011 e numero = 7040-3043 ==> 07040-3043
		//3º) DDD=011 e numero = 3043-5322 ==> 03043-5322
		//4º) DDD=021 e numero = 8243-5322 ==> 08243-5322
		//5º) DDD=021 e numero = 3043-5322 ==> 03043-5322"		X
		//1702 a 1705	Ramal	4	N	Ramal da Pessoa de Referência		
		//	As referencias tem que ter nome duplo, ou seja, nome e sobre nome. Porém para facilitar a analise da proposta aconcelhamos que seja colocado o primeiro nome e grau de parentesco. Por exemplo: Maria Amiga. Rafael Tio.
		esperado.getReferenciasPessoais().add(new Referencia());
		esperado.getReferenciasPessoais().get(1).setTelefone(new Telefone());
		esperado.getReferenciasPessoais().get(1).setNome("");
		//mensagem.getReferenciasPessoais().get(1).getTelefone().setDdd(lerInt(input, 1689, 3));
		//mensagem.getReferenciasPessoais().get(1).getTelefone().setNumero(lerInt(input, 1692, 9));
		//mensagem.getReferenciasPessoais().get(1).getTelefone().setRamal(lerInt(input, 1701, 4));
		
		//REFERÊNCIAS COMERCIAIS
		esperado.setReferenciasComerciais(new LinkedList<Referencia>());
		
		//1706 a 1735	Nome 1	30	A	Nome da Pessoa de Referência  (PC)		
		//1736 a 1738	DDD 1	3	N	DDD da referencia comercial		
		//1739 a 1747	Telefone 1	9	N	"Se o campo DDD estiver preenchido com 011 e o numero do telefone não iniciar 70, 75, 78 e 79 e for um numero de celular, o telefone deve ser iniciado com o numero ""9"", caso contrário deverá ser iniciado com o numero ""0"".
		//Exemplos:
		//1º) DDD=011 e numero = 8240-3043 ==> 98240-3043
		//2º) DDD=011 e numero = 7040-3043 ==> 07040-3043
		//3º) DDD=011 e numero = 3043-5322 ==> 03043-5322
		//4º) DDD=021 e numero = 8243-5322 ==> 08243-5322
		//5º) DDD=021 e numero = 3043-5322 ==> 03043-5322"		
		//1748 a 1751	Ramal 1	4	N	Ramal da referencia Comercial
		esperado.getReferenciasPessoais().get(0).setTelefone(new Telefone());
		esperado.getReferenciasPessoais().get(0).setNome("");
		esperado.getReferenciasPessoais().get(0).getTelefone().setDdd(null);
		esperado.getReferenciasPessoais().get(0).getTelefone().setNumero(null);
		esperado.getReferenciasPessoais().get(0).getTelefone().setRamal(null);
		
		//1752 a 1781	Nome 2	30	A	Nome da Pessoa de Referência  (PC)		
		//1782 a 1784	DDD 2	3	N	DDD da referencia comercial		
		//1785 a 1793	Telefone 2	9	N	"Se o campo DDD estiver preenchido com 011 e o numero do telefone não iniciar 70, 75, 78 e 79 e for um numero de celular, o telefone deve ser iniciado com o numero ""9"", caso contrário deverá ser iniciado com o numero ""0"".
		//Exemplos:
		//1º) DDD=011 e numero = 8240-3043 ==> 98240-3043
		//2º) DDD=011 e numero = 7040-3043 ==> 07040-3043
		//3º) DDD=011 e numero = 3043-5322 ==> 03043-5322
		//4º) DDD=021 e numero = 8243-5322 ==> 08243-5322
		//5º) DDD=021 e numero = 3043-5322 ==> 03043-5322"		
		//1794 a 1797	Ramal 2	4	N	Ramal da referencia Comercial
		esperado.getReferenciasPessoais().get(1).setTelefone(new Telefone());
		esperado.getReferenciasPessoais().get(1).setNome("");
		esperado.getReferenciasPessoais().get(1).getTelefone().setDdd(null);
		esperado.getReferenciasPessoais().get(1).getTelefone().setNumero(null);
		esperado.getReferenciasPessoais().get(1).getTelefone().setRamal(null);

		
		//REFERÊNCIAS BANCARIAS						
		//1798 a 1801	Banco	4	N		Ver tabela de dominio Banco	X. Se algum campo da referencia bancaria for preenchuda 
		//1802 a 1805	Agência	4	N			X. Se algum campo da referencia bancaria for preenchuda 
		//1806 a 1806	DV Agência	1	A			
		//1807 a 1819	Conta Corrente	13	N			X. Se algum campo da referencia bancaria for preenchuda 
		//1820 a 1821	DV Conta Corrente	2	A			X. Se algum campo da referencia bancaria for preenchuda 
		//1822 a 1823	Tipo da Conta	2	A		Ver tabela de dominio Tipo de Conta Corrente	X. Se algum campo da referencia bancaria for preenchuda 
		//1824 a 1831	Data Abertura	8	N	DDMMAAAA		X. Se algum campo da referencia bancaria for preenchuda
		esperado.setReferenciaBancaria(new Banco());
		esperado.getReferenciaBancaria().setBanco("0341");
		esperado.getReferenciaBancaria().setAgencia("1585");
		esperado.getReferenciaBancaria().setDvAgencia("0");
		esperado.getReferenciaBancaria().setDvContaCorrente("0000000006446");
		esperado.getReferenciaBancaria().setDvContaCorrente("0");
		esperado.getReferenciaBancaria().setTipoConta("01");
		esperado.getReferenciaBancaria().setDataAbertura(UtilsDate.parse("13082001"));
		

		//DADOS DA OPERAÇÃO						
		//1832 a 1839	Tabela de Financiamento	8	N	Identificação da tabela de financiamento  (COP’s) referente ao crédito solicitado, específica para o lojista ou Crédito Pessoal 		X
		esperado.setTabelaFinanciamento(288799);
		
		//1840 a 1840	Sinal da Carência 	1	A	Sinal da carência  (+) - Positiva  (-) - Negativa	"+"  -  "-" 	X
		esperado.setSinalCarencia("+");
		
		//1841 a 1842	Carência 	2	N	Quantidade de Dias Para Ajuste do Vencimento da Prestação		X
		esperado.setCarencia(0);
		
		//1843 a 1843	Forma de pagamento	1	N	"Indicação da Forma de Cobrança
		//0) Carnë
		//1) averbação em folha
		//2) Cheque Pré
		//3) Extrato Rotativo
		//4) Extrato Parcelado
		//5) Debito em Conta"	“0”  “1”  “2”   “3”  ”4”, "5"	X
		esperado.setFormaPagamento(0);
		
		
		//1844 a 1851	Data da  Operação	8	N	Data da Realização da Operação		X
		esperado.setDataOperacao(UtilsDate.parse("25082015"));
		
		//1852 a 1853	Produto (Tabela de Produto)	2	N	Informar o Produto		X
		esperado.setProduto(1);
		
		//1854 a 1855	Prestações	2	N	Indicar O Nº de Parcelas do contrato		X
		esperado.setPrestacoes(10);
		
		//1856 a 1862	Taxa Mensal	7	N	Taxa de Juros Aplicada Ao Mês (2 inteiras e 5 decimais)		X
		esperado.setTaxaMensal(6.49000);
		
		//1863 a 1869	Taxa Anual	7	N	Taxa de Juros Aplicada Ao Ano (3 inteiras 4 decimais)		
		esperado.setTaxaAnual(0.0);
		
		//1870 a 1884	Valor da Entrada (não é mais utilizado)	15	N	Valor da Entrada (não é mais utilizado)		
		esperado.setValorEntrada(0);
		
		//1885 a 1885	Tipo de Pagamento	1	N	0-Pré 1-Pós (Flag que indica se a negociação será efetuada com Pré fixado ou Pos fixado)	“0”  “1”	X
		esperado.setTipoPagamento(0);
		
		//1886 a 1887	Top	2	N	Tipo de Operação		X
		esperado.setTop(1);
		
		//1888 a 1902	Valor Tac	15	N	Valor da TAC (em R$)		X
		esperado.setValorTac(0);
		
		//1903 a 1903	Pag_Tac	1	N	Flag que indica a forma de pagamento da TAC ( 0 -Financiada   1- A vista 2 - Descontada em (RO))	“0”   “1”    “2”	X
		esperado.setPagTac(0);
		
		//1904 a 1918	Valor  da Operação/Solicitado	15	N	Valor solicitado pelo cliente (em R$)		X
		esperado.setValorOperacaoSolicitado(70000);
		
		//1919 a 1933	Valor Total do Financiamento	15	N	Valor Total do Financiamento (em R$).		X
		esperado.setValorTotalFinanciado(70000);
		
		//1934 a 1948	Valor da Prestação 	15	N	Valor A Ser Pago Mensalmente Já Com Taxa de Juros (em R$).		X
		esperado.setValorPrestacao(9920);
		
		//1949 a 1956	Vencimento 1ª prestação	8	N	Data do primeiro  vencimento		X
		esperado.setVencimentoPrestacao(UtilsDate.parse("25092015"));
		
		//1957 a 1981	Descrição do bem	25	A	Identificação da mercadoria financiada (obrigatório para TOP 31 e 34)
		esperado.setDescricaoDoBem("");
		
		//1982 a 1982	Imp_Carne	1	A	Flag que indica que o lojista vai imprimir carnê na loja (0-Não   e     1-Sim)	“0”     “1”	X
		esperado.setImpressaoCarne(true);
		
		//1983 a 1997	Nº Pedido	15	A	Campo para o lojista associar o número de pedido, nota fiscal, etc.
		esperado.setNuPedido("");
		
		//1998 a 2008	Nº do CD	11	A	Numeração gráfica pré impressa do comprovante de débito ( não é obrigatorio)
		esperado.setNuCd("");
		
		//2009 a 2019	CPF do Vendedor	11	A	Identificação do vendedor/atendente  responsável pela operação		X
		esperado.setCpfVendedor("01234567890");
		
		//2020 a 2033	Telefone	14	A	Telefone do Vendedor		X
		esperado.setTelefoneVendedor("");
		
		//2034 a 2034	Pre-Pago	1	A	"Indica a compra de telefone celular Pre-Pago
		//  0 - Default - Não
		//  1 - Pre     - Sim"	 '0' '1'	X
		esperado.setPrePago(false);
		
		//2035 a 2035	Leva na Hora	1	A	"Indica se o cliente levara a mercadoria na hora
		//  0 - Default - Não
		//  1 - leva    - Sim"	 '0' '1'	X
		esperado.setLevaNaHora(true);
		
		//2036 a 2036	Beta-Gama	1	A	"Indicadore de Fraude
		//  0 - Default - Sem fraude
		//  2 - Beta    - Susp fraude
		//  1 - Gama    - Confirm fraude"	 '0' '1' '2'	X
		esperado.setBetaGama(0);
		
		//2037 a 2046	Promotor	10	N	Código do Promotor
		esperado.setPromotor(null);
		
		//2047 a 2047	Indicador aceita consulta ao sysbacen	1	A	Indica se o cliente permitiu a consulta ao sysbacen 0- Não(Default), 1 - Sim	 '0' '1'	X
		esperado.setAceitaConsulta(false);
		
		//2048 a 2054	CET Mensal (%)	7	N	Taxa Mensal do Custo efetivo Total (2 decimais)	"Preencher com o valor 
		//informado pelo Simulador ou zero caso o simulador não foi utilizado"	
		esperado.setCetMensal(0.00);
		
		//2055 a 2061	CET Anual  (%)	7	N	Taxa Anual   do Custo efetivo Total (2 decimais)	"Preencher com o valor 
		//informado pelo Simulador ou zero caso o simulador não foi utilizado"
		esperado.setCetAnual(0.00);
		
		//2062 a 2068	IOF	7	N	Valor do IOF (2 casas decimais)	"Preencher com o valor 
		//informado pelo Simulador ou zero caso o simulador não foi utilizado"
		esperado.setIof(0.00);
		
		//2069 a 2076	Data do Evento	8	N	Data da Entrega do Bem/Serviço		X. Se o produto for cessão
		esperado.setDataEvento(UtilsDate.parse("00000000"));
		
		//2077 a 2091	Valor da Entrada ao Lojista	15	N	Valor dado de entrada ao Lojista
		esperado.setValorEntradaLojista(0);
		
		//2092 a 2141	Filler	50	A
		esperado.setFillerDadosOperacao(ContextoMensagem.escreverString(50, " "));
		
		//Dados do Pre Screening						
		//2142 a 2143	Código da Oferta Aderida de Conta Corrente	2	A	Código da Oferta recuperada na mensagem 0460.
		esperado.setCodigoOfertaAderidaContaCorrente("");
		
		//2144 a 2145	Código da Oferta Aderida de CDC/EP.	2	A	Código da Oferta recuperada na mensagem 0460.		
		esperado.setCodigoOfertaAderidaContaCorrente("");
		
		//2146 a 2147	Código do Perfil da Oferta Aderida de CDC/EP.	2	A	Código do Perfil recuperado na mensagem 0460.		
		esperado.setCodigoPerfilOfertaAderidaCdc("");
		
		//2148 a 2160	Filler	13	A
		esperado.setFillerPreScreening(ContextoMensagem.escreverString(13, " "));
		
		
		//validação outros indicadores
		esperado.setIndicadores(new Indicador());
		esperado.getIndicadores().setIdentificadorCanal("T");
		esperado.getIndicadores().setVersaoCanal("");
		esperado.getIndicadores().setPolitica("");
		esperado.getIndicadores().setAmbiente("");
		
		
		assertThat(m.getCabecalho(), Matchers.samePropertyValuesAs(esperado.getCabecalho()));
		assertThat(m.getDadosPessoais().getDocumentoIdentificacao(), Matchers.samePropertyValuesAs(esperado.getDadosPessoais().getDocumentoIdentificacao()));
		assertThat(m.getDadosPessoais().getEndereco(), Matchers.samePropertyValuesAs(esperado.getDadosPessoais().getEndereco()));
		assertThat(m.getDadosPessoais().getTelefone(), Matchers.samePropertyValuesAs(esperado.getDadosPessoais().getTelefone()));
		assertThat(m.getDadosPessoais().getCelular(), Matchers.samePropertyValuesAs(esperado.getDadosPessoais().getCelular()));
		assertThat(m.getDadosPessoais(), BeanMatchers.theSameAs(esperado.getDadosPessoais()));
		
		assertThat(m.getDadosProfissionais().getEndereco(), Matchers.samePropertyValuesAs(esperado.getDadosProfissionais().getEndereco()));
		assertThat(m.getDadosProfissionais().getTelefone(), Matchers.samePropertyValuesAs(esperado.getDadosProfissionais().getTelefone()));
		assertThat(m.getDadosProfissionais(), BeanMatchers.theSameAs(esperado.getDadosProfissionais()));
		assertThat(m.getDadosConjuge(), BeanMatchers.theSameAs(esperado.getDadosConjuge()));
		
		assertThat(m, BeanMatchers.theSameAs(esperado));
	}
	
	@Test
	@Ignore
	public void criarRespostaPropostaFinanciamento() {
		
		Assert.fail("Não implementado.");
	}
}
