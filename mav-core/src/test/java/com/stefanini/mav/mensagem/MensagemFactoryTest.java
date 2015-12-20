package com.stefanini.mav.mensagem;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.LinkedList;

import org.exparity.hamcrest.BeanMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.stefanini.mav.es.AdaptadorTipo;
import com.stefanini.mav.es.MapeamentoNaoEncontrado;
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
	
	public static SolicitacaoCapturaSimplificada criarSolicitacaoCapturaSimplificadaMensagem(String mensagem) throws MensagemNaoEncontradaException, ParseException {
		
		Cabecalho expected = new Cabecalho();
		expected.setSentidoFluxo(Fluxo.ENTRADA);
		expected.setTamanho(116);
		expected.setCodigo(CodigoMensagem.C0450);
		expected.setNumeroTransacao(980008);
		expected.setNumeroProposta("");
		expected.setCodigoUsuario("UILSON");
		expected.setCodigoRetorno("");
		expected.setCodigoLojista(170894002);
		expected.setVersao("9");
		expected.setCampoLojista(AdaptadorTipo.escreverString(30, " "));
		
		SolicitacaoCapturaSimplificada esperado = new SolicitacaoCapturaSimplificada(ContextoMensagem.md5(mensagem), expected);

		
		//validação de dados pessoais
		esperado.setDadosPessoais(new SolicitacaoCapturaSimplificada.DadoPessoal());
		esperado.getDadosPessoais().setCpf("00000000191");
		esperado.getDadosPessoais().setDataNascimento(UtilsDate.parse("01011960", UtilsDate.FormatadorData.DATA));
		esperado.getDadosPessoais().setFiller(AdaptadorTipo.escreverString(40, " "));
		
		//validação de dados operação cartão
		esperado.setDadosOperacaoCartao(new SolicitacaoCapturaSimplificada.DadoOperacaoCartao());
		esperado.getDadosOperacaoCartao().setCodigoOrg("");
		esperado.getDadosOperacaoCartao().setCodigoLogo("");
		esperado.getDadosOperacaoCartao().setCodigoCampanha("");
		esperado.getDadosOperacaoCartao().setCodigoModalidade("");
		esperado.getDadosOperacaoCartao().setFiller(AdaptadorTipo.escreverString(28, " "));
		
		//validação de Dados Complementares
		esperado.setComplemento(new SolicitacaoCapturaSimplificada.DadoComplementar());
		esperado.getComplemento().setEmancipado(false);
		esperado.getComplemento().setCodigoProduto("01");
		
		//validação outros indicadores
		esperado.setIndicadores(new Indicador());
		esperado.getIndicadores().setIdentificadorCanal("T");
		esperado.getIndicadores().setVersaoCanal("");
		esperado.getIndicadores().setPolitica("");
		esperado.getIndicadores().setAmbiente("");
		
		return esperado;
	}
	
	public SolicitacaoCapturaSimplificada criarSolicitacaoCapturaSimplificada() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException {
		
		String mensagem = MensagemHelper.lerMensagem(199, 450, "criarCapturaSimplicada.1");
		
		SolicitacaoCapturaSimplificada m = (SolicitacaoCapturaSimplificada) MensagemFactory.parse(mensagem);
		SolicitacaoCapturaSimplificada esperado = criarSolicitacaoCapturaSimplificadaMensagem(mensagem);
		
		assertThat(m, BeanMatchers.theSameAs(esperado));
		
		return m;
	}
	
	
	@Test
	public void gerarErroCapturaSimplicada() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException, MapeamentoNaoEncontrado{
	
		SolicitacaoCapturaSimplificada m = criarSolicitacaoCapturaSimplificada();
		assertThat(m, notNullValue());
		
		String descricao = "Erro de solicitação";
		RespostaErro erro = (RespostaErro) MensagemFactory.gerarErro(m, descricao);
		MatcherAssert.assertThat(CodigoMensagem.C9450, Matchers.is(Matchers.equalTo(erro.getCabecalho().getCodigo())));
		MatcherAssert.assertThat(descricao, Matchers.is(Matchers.equalTo(erro.getDescricao())));
	}
	
	@Test
	public void criarCapturaSimplicada() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException{
		
		criarSolicitacaoCapturaSimplificada();
	}
	
	public static RespostaCapturaSimplificada criarRespostaCapturaSimplicadaMensagem(String mensagem) throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException {
		
		Cabecalho cabecalho = new Cabecalho();
		cabecalho.setSentidoFluxo(Fluxo.SAIDA);
		cabecalho.setTamanho(863);
		cabecalho.setCodigo(CodigoMensagem.C0460);
		cabecalho.setNumeroTransacao(980008);
		cabecalho.setNumeroProposta("P4201170358");
		cabecalho.setCodigoUsuario("UILSON");
		cabecalho.setCodigoRetorno("A0062");
		cabecalho.setCodigoLojista(170894002);
		cabecalho.setVersao("9");
		cabecalho.setCampoLojista(AdaptadorTipo.escreverString(30, " "));
		
		RespostaCapturaSimplificada esperado = new RespostaCapturaSimplificada(ContextoGeracaoToken.md5(mensagem), cabecalho);
		
		//DADOS DA CONSULTA
		esperado.setDadosConsulta(new RespostaCapturaSimplificada.DadoConsulta());
		esperado.getDadosConsulta().setFiller(AdaptadorTipo.escreverString(83, " "));
		esperado.getDadosConsulta().setMensagemAutorizador("Xx");		  
		esperado.getDadosConsulta().setData(UtilsDate.parse("25082015180815", UtilsDate.FormatadorData.DATA_TEMPO));
		esperado.getDadosConsulta().setCodigoStatusProposta(StatusProposta.ELEGIVEL);
		esperado.getDadosConsulta().setParecer("");
		esperado.getDadosConsulta().setProduto("01");
		
		//validação de dados cliente
		esperado.setDadosCliente(new RespostaCapturaSimplificada.DadoCliente());
		esperado.getDadosCliente().setCpf("00000000191");
		esperado.getDadosCliente().setDataNascimento(UtilsDate.parse("20101944", UtilsDate.FormatadorData.DATA));
		esperado.getDadosCliente().setEmancipado(false);
		esperado.getDadosCliente().setCodigoProduto("01");
		esperado.getDadosCliente().setCobraTac(false);
		esperado.getDadosCliente().setElegibilidadeSeguro(true);
		esperado.getDadosCliente().setCodigoProdutoLosango("HSSOR002");
		esperado.getDadosCliente().setQtdNumeroSorte(0);
		esperado.getDadosCliente().setFiller(AdaptadorTipo.escreverString(47, " "));
		
		//validação de dados operação cartão
		esperado.setDadosOperacaoCartao(new RespostaCapturaSimplificada.DadoOperacaoCartao());
		esperado.getDadosOperacaoCartao().setCodigoOrg("");
		esperado.getDadosOperacaoCartao().setCodigoLogo("");
		esperado.getDadosOperacaoCartao().setCodigoCampanha("");
		esperado.getDadosOperacaoCartao().setCodigoModalidade("");
		
		//validação do filler
		esperado.setFiller("0000 0000 0000 0000           000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000             000000000000000000000000000000000000000000   ");
		
		//validação outros indicadores
		esperado.setIndicadores(new Indicador());
		esperado.getIndicadores().setIdentificadorCanal("T");
		esperado.getIndicadores().setVersaoCanal("1");
		esperado.getIndicadores().setPolitica("2");
		esperado.getIndicadores().setAmbiente("HO");
		
		return esperado;
	}
	
	@Test
	public void criarRespostaCapturaSimplicada() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException {
		
		String mensagem = MensagemHelper.lerMensagem(946, 460, "criarRespostaCapturaSimplicada.1");
		
		RespostaCapturaSimplificada m = (RespostaCapturaSimplificada) MensagemFactory.parse(mensagem);
		RespostaCapturaSimplificada esperado = criarRespostaCapturaSimplicadaMensagem(mensagem);		

		assertThat(m, BeanMatchers.theSameAs(esperado));
	}
	
	@Test
	public void criarGeracaoToken() throws NumberFormatException, IOException, URISyntaxException, MensagemNaoEncontradaException {

		String mensagem = MensagemHelper.lerMensagem(157, CodigoMensagem.C0670.toInt(), "criarGeracaoToken.1");
		
		GeracaoToken m = (GeracaoToken) MensagemFactory.parse(mensagem);
		GeracaoToken esperado = criarGeracaoTokenMensagem(mensagem);

		assertThat(m, BeanMatchers.theSameAs(esperado));
	}

	public static GeracaoToken criarGeracaoTokenMensagem(String mensagem) throws MensagemNaoEncontradaException {
		
		Cabecalho cabecalhoEsperado = new Cabecalho();
		cabecalhoEsperado.setSentidoFluxo(Fluxo.ENTRADA);
		cabecalhoEsperado.setTamanho(74);
		cabecalhoEsperado.setCodigo(CodigoMensagem.C0670);
		cabecalhoEsperado.setNumeroTransacao(980010);
		cabecalhoEsperado.setNumeroProposta("P4201170358");
		cabecalhoEsperado.setCodigoUsuario("UILSON");
		cabecalhoEsperado.setCodigoRetorno("");
		cabecalhoEsperado.setCodigoLojista(170894002);
		cabecalhoEsperado.setVersao("9");
		cabecalhoEsperado.setCampoLojista(AdaptadorTipo.escreverString(30, "TOP"));
		
		GeracaoToken esperado = new GeracaoToken(ContextoMensagem.md5(mensagem), cabecalhoEsperado);

		//Tipos de Serviços
		esperado.setTipoServico(new LinkedList<String>());
		//0084 a 0089	Tipo de serviço 1	6	A	Indica o tipo de serviço	Ver tabela de dominio Tipo de Serviço	X
		//0090 a 0095	Tipo de serviço 2	6	A	Indica o tipo de serviço	Ver tabela de dominio Tipo de Serviço	
		//0096 a 0101	Tipo de serviço 3	6	A	Indica o tipo de serviço	Ver tabela de dominio Tipo de Serviço	
		//0102 a 0107	Tipo de serviço 4	6	A	Indica o tipo de serviço	Ver tabela de dominio Tipo de Serviço	
		//0108 a 0113	Tipo de serviço 5	6	A	Indica o tipo de serviço	Ver tabela de dominio Tipo de Serviço	
		//0114 a 0119	Tipo de serviço 6	6	A	Indica o tipo de serviço	Ver tabela de dominio Tipo de Serviço	
		//0120 a 0125	Tipo de serviço 7	6	A	Indica o tipo de serviço	Ver tabela de dominio Tipo de Serviço	
		//0126 a 0131	Tipo de serviço 8	6	A	Indica o tipo de serviço	Ver tabela de dominio Tipo de Serviço	
		//0132 a 0137	Tipo de serviço 9	6	A	Indica o tipo de serviço	Ver tabela de dominio Tipo de Serviço	
		//0138 a 0143	Tipo de serviço 10	6	A	Indica o tipo de serviço	Ver tabela de dominio Tipo de Serviço	
		esperado.getTipoServico().add("BOLETO");

		//Outros Indicadores
		esperado.setIndicadores(new Indicador());
		//0144 a 0144	Identificador do canal	1	A	Identifica que a proposta é de procedência do TRS	
		//0145 a 0154	Versão do Canal	10	A	Uso exclusivo da Losango	
		//0155 a 0155	Política	1	A	Uso exclusivo da Losango	
		//0156 a 0157	Ambiente	2	A	Uso exclusivo da Losango
		esperado.getIndicadores().setIdentificadorCanal("T");
		esperado.getIndicadores().setVersaoCanal("");
		esperado.getIndicadores().setPolitica("");
		esperado.getIndicadores().setAmbiente("");
		return esperado;
	}
	
	public static RespostaGeracaoToken criarRespostaGeracaoTokenMensagem(String mensagem) throws MensagemNaoEncontradaException {
		
		Cabecalho cabecalhoEsperado = new Cabecalho();
		cabecalhoEsperado.setSentidoFluxo(Fluxo.ENTRADA);
		cabecalhoEsperado.setTamanho(669);
		cabecalhoEsperado.setCodigo(CodigoMensagem.C0680);
		cabecalhoEsperado.setNumeroTransacao(980010);
		cabecalhoEsperado.setNumeroProposta("P4201170358");
		cabecalhoEsperado.setCodigoUsuario("UILSON");
		cabecalhoEsperado.setCodigoRetorno("A0000");
		cabecalhoEsperado.setCodigoLojista(170894002);
		cabecalhoEsperado.setVersao("9");
		cabecalhoEsperado.setCampoLojista(AdaptadorTipo.escreverString(30, "TOP"));
		
		RespostaGeracaoToken esperado = new RespostaGeracaoToken(ContextoMensagem.md5(mensagem), cabecalhoEsperado);
		
		//Dados do Token				
		//0084 a 0595	token	512	A	Valor do token
		esperado.setToken("BA4CCCA99E8CDF8E4FC96C9DB538F8C8BB23AB7864B9EFB319AE8714A48C659D");

		
		//Tipos de Serviços
		esperado.setTipoServico(new LinkedList<String>());
		//0596 a 0601	Tipo de Impressão 1	6	A	Indica o tipo de Impressão (ver tabela tipo Impressão)
		//0602 a 0607	Tipo de Impressão 2	6	A	Indica o tipo de Impressão (ver tabela tipo Impressão)
		//0608 a 0613	Tipo de Impressão 3	6	A	Indica o tipo de Impressão (ver tabela tipo Impressão)
		//0614 a 0619	Tipo de Impressão 4	6	A	Indica o tipo de Impressão (ver tabela tipo Impressão)
		//0620 a 0625	Tipo de Impressão 5	6	A	Indica o tipo de Impressão (ver tabela tipo Impressão)
		//0626 a 0631	Tipo de Impressão 6	6	A	Indica o tipo de Impressão (ver tabela tipo Impressão)
		//0632 a 0637	Tipo de Impressão 7	6	A	Indica o tipo de Impressão (ver tabela tipo Impressão)
		//0638 a 0643	Tipo de Impressão 8	6	A	Indica o tipo de Impressão (ver tabela tipo Impressão)
		//0644 a 0649	Tipo de Impressão 9	6	A	Indica o tipo de Impressão (ver tabela tipo Impressão)
		//0650 a 0655	Tipo de Impressão 10	6	A	Indica o tipo de Impressão (ver tabela tipo Impressão)
		esperado.getTipoServico().add("BOLETO");

		//Outros Indicadores
		esperado.setIndicadores(new Indicador());
		//0656 a 0656	Identificador do canal	1	A	Identifica que a proposta é de procedência do TRS
		//0657 a 0666	Versão do Canal	10	A	Uso exclusivo da Losango
		//0667 a 0667	Política	1	A	Uso exclusivo da Losango
		//0668 a 0669	Ambiente	2	A	Uso exclusivo da Losango
		esperado.getIndicadores().setIdentificadorCanal("T");
		esperado.getIndicadores().setVersaoCanal("9");
		esperado.getIndicadores().setPolitica("4");
		esperado.getIndicadores().setAmbiente("HO");
		return esperado;
	}
	
	@Test
	public void criarRespostaGeracaoToken() throws NumberFormatException, IOException, URISyntaxException, MensagemNaoEncontradaException {
		
		
		String mensagem = MensagemHelper.lerMensagem(669, CodigoMensagem.C0680.toInt(), "criarRespostaGeracaoToken.1");
		
		RespostaGeracaoToken m = (RespostaGeracaoToken) MensagemFactory.parse(mensagem);
		RespostaGeracaoToken esperado = criarRespostaGeracaoTokenMensagem(mensagem);

		assertThat(m, BeanMatchers.theSameAs(esperado));
	}
	
	public static PropostaFinanciamento criarPropostaFinanciamentoMensagem(String mensagem) throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException {
		
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
		esperado.setDadosPessoais(new PropostaFinanciamento.DadoPessoal());
		esperado.getDadosPessoais().setTipoPersonalidadede("F");
		esperado.getDadosPessoais().setCpf("00000000000191");
		esperado.getDadosPessoais().setUsuarioCpf("T");
		esperado.getDadosPessoais().setCorrespondencia("1");
		esperado.getDadosPessoais().setIdentificacao(new DocumentoTp1());
		esperado.getDadosPessoais().getIdentificacao().setAttr(new DocumentoAttr());
		esperado.getDadosPessoais().getIdentificacao().setNumero("201570496  0");
		esperado.getDadosPessoais().getIdentificacao().getAttr().setTipo("01");
		esperado.getDadosPessoais().getIdentificacao().getAttr().setOrgaoEmissor("SSP");
		esperado.getDadosPessoais().getIdentificacao().getAttr().setUfOrgaoEmissor("MS");
		esperado.getDadosPessoais().getIdentificacao().getAttr().setDataEmissao(UtilsDate.parse("06072001", UtilsDate.FormatadorData.DATA));
		
		esperado.getDadosPessoais().setConjugeCompoeRenda(false);
		esperado.getDadosPessoais().setNome("PROPOSTA TESTE");
		esperado.getDadosPessoais().setLocalNascimento("CAMPO GRANDE");
		esperado.getDadosPessoais().setDataNascimento(UtilsDate.parse("20101944", UtilsDate.FormatadorData.DATA));
		esperado.getDadosPessoais().setSexo("F");
		esperado.getDadosPessoais().setNacionalidade(0);
		esperado.getDadosPessoais().setNaturalidade("CAMPO GRANDE");
		esperado.getDadosPessoais().setNomeMae("MARIA DO CARMO PINHEIRO NE");
		esperado.getDadosPessoais().setNomePai("LEANDRO NE");
		esperado.getDadosPessoais().setCtps(0);
		esperado.getDadosPessoais().setCtpsSerie("00000");
		esperado.getDadosPessoais().setEstadoCivil(1);
		
		esperado.getDadosPessoais().setEnderecoResidencial(new EnderecoTp1());
		esperado.getDadosPessoais().getEnderecoResidencial().setLogradouro("RUA ITAQUERA");
		esperado.getDadosPessoais().getEnderecoResidencial().setNumero("68");
		esperado.getDadosPessoais().getEnderecoResidencial().setComplemento("");
		esperado.getDadosPessoais().getEnderecoResidencial().setBairro("FLAMBOYANT");
		esperado.getDadosPessoais().getEnderecoResidencial().setCidade("CAMPO GRANDE");
		esperado.getDadosPessoais().getEnderecoResidencial().setUf("MS");
		esperado.getDadosPessoais().getEnderecoResidencial().setCep(23585361);
		
		esperado.getDadosPessoais().setTelefoneResidencial(new TelefoneRamal());
		esperado.getDadosPessoais().getTelefoneResidencial().setDdd(6);
		esperado.getDadosPessoais().getTelefoneResidencial().setNumero(730264981);
		esperado.getDadosPessoais().getTelefoneResidencial().setRamal(0);
		
		esperado.getDadosPessoais().setTipoTelefone(1);
		esperado.getDadosPessoais().setTipoResidencia(1);
		esperado.getDadosPessoais().setResideDesde(UtilsDate.parse("01052008", UtilsDate.FormatadorData.DATA));
		
		esperado.getDadosPessoais().setTelefoneCelular(new Telefone());
		esperado.getDadosPessoais().getTelefoneCelular().setDdd(6);
		esperado.getDadosPessoais().getTelefoneCelular().setNumero(792169260);
		
		esperado.getDadosPessoais().setEmail("tpne@hotmail.com");
		esperado.getDadosPessoais().setPossuiPatrimonio(false);
		esperado.getDadosPessoais().setPatrimonio(new LinkedList<Patrimonio>());
		
		esperado.getDadosPessoais().setFillerPatrimonio("");
		esperado.getDadosPessoais().setCodigoPais("");
		
		//0578 a 0579	Código da UF da Naturalidade (informar se nacionalidade = brasileiro)	2	A	Tabela de UF	Ver tabela de dominio UF	X. Se Nacionalidade = Brasileiro
		esperado.getDadosPessoais().setCodigoUfNaturalidade("MS");
		
		//0580 a 0587	Data de Vencimento do Documento de identificação	8	A			X, se o tipo do documento for: 02, 03 e 09
		esperado.getDadosPessoais().setDataVencimentoIdentificacao(null);

		//0588 a 0588	Flag Emancipado	1	A		0 - Nao 1 - Sim
		//aceita null
		//esperado.getDadosPessoais().setEmancipado(false);	
		
		//0589 a 0589	Origem do patrimonio 1	1	A		
		//0590 a 0590	Origem do patrimonio 2	1	A		
		//0591 a 0591	Origem do patrimonio 3	1	A		
		//0592 a 0592	Origem do patrimonio 4	1	A
		esperado.getDadosPessoais().setOrigemPatrimonio(new LinkedList<String>());
		esperado.getDadosPessoais().setFiller(AdaptadorTipo.escreverString(33, " "));
		
		
		esperado.setDadosProfissionais(new PropostaFinanciamento.DadoProfissional());
		//0626 a 0633	Data de Admissão 	8	N	Data de Admissão na Empresa.                  		X
		esperado.getDadosProfissionais().setDataAdmissao(null);
		
		//0634 a 0663	Empresa	30	A	Empresa Em Que Trabalha o Cliente                              		X
		esperado.getDadosProfissionais().setEmpresa("JV TUBOS E ACABAMENTOS");
		
		esperado.getDadosProfissionais().setEndereco(new EnderecoTp1());
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
		
		esperado.getDadosProfissionais().setTelefone(new TelefoneRamal());
		//0774 a 0776	DDD	3	N	DDD da Cidade Onde Trabalha o Cliente		X
		esperado.getDadosProfissionais().getTelefone().setDdd(6);
		
		//0777 a 0785	Telefone	9	N	"Se o campo DDD estiver preenchido com 011 e o numero do telefone não iniciar 70, 75, 78 e 79 e for um numero de celular, o telefone deve ser iniciado com o numero ""9"", caso contrário deverá ser iniciado com o numero ""0"".
		esperado.getDadosProfissionais().getTelefone().setNumero(733481881);
		
		//0786 a 0789	Ramal	4	N	Ramal do Trabalho do Cliente
		esperado.getDadosProfissionais().getTelefone().setRamal(0000);
		
		//0790 a 0800	Valor Renda Líquida 	11	N	Renda Líquida do Cliente (em R$)                                              		X
		esperado.getDadosProfissionais().setValorRendaLiquida(1300.00);
		
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
		esperado.getDadosProfissionais().setUsoExclusivoLosango("");
		
		//0844 a 0845	Orgão Beneficio	2	A		Ver tabela de dominio Orgao Beneficio	X. Se Aposentado ou Pensionista = SIM
		esperado.getDadosProfissionais().setOrgaoBeneficio("");
		
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
		esperado.getDadosProfissionais().setFiller(AdaptadorTipo.escreverString(26, " "));
		
		
		esperado.setDadosConjuge(new PropostaFinanciamento.DadoConjuge());
		//0916 a 0945	Nome Do Cônjuge	30	A	Nome do Cônjuge do Cliente		X. Se conjuge compoe renda = 1 ou Estado Civil = 2
		esperado.getDadosConjuge().setNome("");
		
		//0946 a 0985	Local de Nascimento	40	A
		esperado.getDadosConjuge().setLocalNascimento("");
		
		//0986 a 0993	Data de Nascimento	8	N	Data nascimento do cônjuge.		X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().setDataNascimento(null);
		
		//0994 a 1004	CPF	11	N	CPF do Cônjuge		X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().setCpf("00000000000");
		
		esperado.getDadosConjuge().setIdentidade(new DocumentoTp2());
		esperado.getDadosConjuge().getIdentidade().setAttr(new DocumentoAttr());
		//1005 a 1014	Identidade / RG	10	A	Número da Identidade do Cônjuge		X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getIdentidade().setNumero("");
		
		//1015 a 1016	Tipo de Documento	2	A		Ver tabela de dominio TP  DOCUMENTO IDENTIDADE	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getIdentidade().getAttr().setTipo("");
		
		//1017 a 1021	Órgão Emissor	5	A	Órgão Emissor do Documento de Identidade do Cônjuge	Ver tabela de dominio Orgão Emissor	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getIdentidade().getAttr().setOrgaoEmissor("");
		
		//1022 a 1023	UF Órgão Emissor	2	A		Ver tabela de dominio UF	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getIdentidade().getAttr().setUfOrgaoEmissor("");
		
		//1024 a 1031	Data Emissão	8	N	Data de emissão do documento do Cônjuge		X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getIdentidade().getAttr().setDataEmissao(null);
		
		//1032 a 1056	Empresa 	25	A	Empresa Em Que o Cônjuge Trabalha		X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().setEmpresa("");
		
		//1057 a 1064	Data  Admissão	8	N	Data da Admissão na Empresa		X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().setDataAdmissao(null);
		
		esperado.getDadosConjuge().setEndereco(new EnderecoTp1());
		//1065 a 1104	Logradouro	40	A	Logradouro do trabalho do conjuge		X. Se conjuge compoe renda = 1
		//1105 a 1109	Numero	5	A	Numero do Logradouro		X. Se conjuge compoe renda = 1
		//1110 a 1124	Complemento	15	A	Complemento do Logradouro
		//1125 a 1144	Bairro	20	A	Bairro onde trabalha o cônjuge		X. Se conjuge compoe renda = 1 
		//1145 a 1164	Cidade	20	A	Cidade Onde Trabalha o cônjuge		X. Se conjuge compoe renda = 1 
		//1165 a 1166	UF	2	A	Abreviatura da Unidade Federativa 		X. Se conjuge compoe renda = 1 
		//1167 a 1174	CEP	8	N	CEP do endereço comercial do cônjuge		X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().getEndereco().setLogradouro("");
		esperado.getDadosConjuge().getEndereco().setNumero("");
		esperado.getDadosConjuge().getEndereco().setComplemento("");
		esperado.getDadosConjuge().getEndereco().setBairro("");
		esperado.getDadosConjuge().getEndereco().setCidade("");
		esperado.getDadosConjuge().getEndereco().setUf("");
		esperado.getDadosConjuge().getEndereco().setCep(null);
		
		 
		esperado.getDadosConjuge().setTelefone(new TelefoneRamal());
		//1175 a 1177	DDD	3	N	DDD da Cidade Onde Trabalha o cônjuge		X. Se conjuge compoe renda = 1 
		//1178 a 1186	Telefone	9	N	"Se o campo DDD estiver preenchido com 011 e o numero do telefone não iniciar 70, 75, 78 e 79 e for um numero de celular, o telefone deve ser iniciado com o numero ""9"", caso contrário deverá ser iniciado com o numero ""0"".
		//1187 a 1190	Ramal	4	N	Ramal do Trabalho do cônjuge
		esperado.getDadosConjuge().getTelefone().setDdd(null);
		esperado.getDadosConjuge().getTelefone().setNumero(null);
		esperado.getDadosConjuge().getTelefone().setRamal(0);
				
		//1191 a 1210	Cargo	20	A	Cargo do Cônjuge	Ver tabela de Dominio Cargo	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().setCargo("");
		
		//1211 a 1230	Profissão	20	A	Profissão do Conjuge	Ver tabela de dominio Profissão	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().setProfissao("");
		
		//1231 a 1231	Aposentado	1	A	"Aponta se o cliente é aposentado:
		//S - Sim; N - Não"	"S"  "N"	X. Se conjuge compoe renda = 1
		//aceita null
		//esperado.getDadosConjuge().setAposentado(false);
		
		//1232 a 1232	Pensionista	1	A	"Aponta se o cliente é Pensionista:
		//S - Sim; N - Não"	"S"  "N"	X. Se conjuge compoe renda = 1
		//aceita null
		//esperado.getDadosConjuge().setPensionista(false);
		
		//1233 a 1233	Uso exclusivo da Losango	1	A	Uso exclusivo da Losango
		esperado.getDadosConjuge().setUsoExclusivoLosango("");
		
		//1234 a 1244	Valor Renda Líquida 	11	N	Renda Líquida do Cônjuge (em R$)                                                  		X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().setValorRendaLiquida(null);

		//1245 a 1314	Patrimônio	70	A			
		//esperado.getDadosConjuge().setPatrimonio(new LinkedList<Patrimonio>());
		esperado.getDadosConjuge().setPatrimonio("");

		//1315 a 1315	Nacionalidade	1	N	Nacionalidade do Conjuge	"0-Brasileiro
		//1-Estrangeiro        "	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().setNacionalidade(null);
		
		//1316 a 1317	Código do País (informar se nacionalidade = estrangeiro)	2	A	Tabela de Paises	Ver tabela de Dominio Paises	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().setCodigoPais("");
		
		//1318 a 1319	Código da UF da Naturalidade (informar se nacionalidade = brasileiro)	2	A	Tabela de UF	Ver tabela de dominio UF	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().setCodigoUfNaturalidade("");
		
		//1320 a 1325	Data do Comprovante de Renda	6	A	Mes/Ano do comprovante de Renda apresentado pelo Conjuge	MMAAAA	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().setDataComprovanteRenda(null);
		
		//1326 a 1327	Tipo Comprovante de Renda	2	A		Ver tabela Dominio tipo C Renda	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().setTipoComprovanteRenda("");
		
		//1328 a 1329	Ocupação nova	2	A	Código da Profissão	Ver tabela Dominio Código da Profissão	X. Se conjuge compoe renda = 1
		esperado.getDadosConjuge().setOcupacaoNova("");
		
		//1330 a 1330	Sexo do Conjuge	1	A	M – Masculino     F – Feminino         	“M”   ”F”	X. Se conjuge compoe renda = 1 ou Estado Civil = 2
		esperado.getDadosConjuge().setSexo("");
		
		//1331 a 1344	CNPJ Conjuge	14	A
		esperado.getDadosConjuge().setCnpj("");
		
		//1331 a 1364	Filler	20	A			X, se Empresario ou Proprietario
		esperado.getDadosConjuge().setFiller(AdaptadorTipo.escreverString(20, " "));
		
		//DADOS COMPLEMENTARES
		esperado.setDadoComplementar(new PropostaFinanciamento.DadoComplementar());
		//1365 a 1366	Escolaridade	2	A	Codigo da Escolaridade	Ver tabela de dominio Escolaridade
		esperado.getDadoComplementar().setEscolaridade("11");
		
		//1367 a 1386	Formação	20	A	Formação
		esperado.getDadoComplementar().setFormacao("OK");
		
		//1387 a 1387	Indicador Possui cartão	1	N	Indicador se possui cartão	0 - Não 1 - Sim	X
		esperado.getDadoComplementar().setPossuiCartao(false);
		
		//1388 a 1388	Indicador Possui veículo próprio	1	N	Indicador Possui veículo próprio	0 - Não 1 - Sim	X
		esperado.getDadoComplementar().setPossuiVeiculoProprio(false);
		
		//1389 a 1398	Placa	10	A
		esperado.getDadoComplementar().setPlaca("");
		
		//1399 a 1458	Renavam	60	A
		esperado.getDadoComplementar().setRenavam("");
		
		//1459 a 1459	Indicador Possui veículo quitado	1	N	Indicador Possui veículo quitado	0 - Não 1 - Sim	X
		esperado.getDadoComplementar().setPossuiVeiculoQuitado(false);
		
		//1460 a 1460	Possui experiencia de crédito	1	N	Indicador Possui experiência	0 - Não 1 - Sim	X
		esperado.getDadoComplementar().setPossuiExperienciaCredito(false);
		
		//1461 a 1480	Local da Experiência	20	A			X. Se Possui experiencia de crédito = 1
		esperado.getDadoComplementar().setLocalExperiencia("");
		
		//1481 a 1482	Plano da Experiência	2	N			X. Se Possui experiencia de crédito = 1
		esperado.getDadoComplementar().setPlanoExperiencia(null);
		
		//1483 a 1497	Valor da Prestação da Experiência	15	N			X. Se Possui experiencia de crédito = 1
		esperado.getDadoComplementar().setValorPrestacaoExperiencia(0);
		
		//1498 a 1503	Inicio da Experiência de Crédito	6	N	Inicio da Experiência MMAAAA		X. Se Possui experiencia de crédito = 1
		esperado.getDadoComplementar().setInicioExperienciaCredito(null);
		
		//1504 a 1543	Classificação do Cliente	40	A
		esperado.getDadoComplementar().setClassificao("");
		
		//1544 a 1544	Indicador Possui Cartão Financeira	1	N	Indicador Possui Cartão Financeira	0 - Não 1 - Sim	X
		esperado.getDadoComplementar().setPossuiCartaoFinanceira(false);
		
		//1545 a 1545	Indicador Possui Conta Corrente	1	N	Indicador Possui Conta Corrente	0 - Não 1 - Sim	X
		esperado.getDadoComplementar().setPossuiContaCorrente(false);
		
		//1546 a 1546	Indicador Possui dependente	1	N		0 - Não 1 - Sim	X
		esperado.getDadoComplementar().setPossuiDependente(false);
		
		//1547 a 1548	Quantidade de dependentes	2	N			X. Se Indicador Possui dependente = 1
		esperado.getDadoComplementar().setQuantidadeDependentes(0);
		
		//1549 a 1563	Nome do cartão	15	A
		esperado.getDadoComplementar().setNomeCartao("");
		
		//1564 a 1564	indicadorCapturarFoto	1	A	Indicador da captura da Foto do Cliente.	""" "" Documentos capturados com sucesso ou Lojista sem captura digital com a Losango
		//1 - Problemas Técnicos
		//2 - Cliente não autoriza
		//3 - Outros"	X
		esperado.getDadoComplementar().setCapturarFoto("");
		
		//1565 a 1565	indicadorCapturarDocumento	1	A	Indicador da captura do Documento.	""" "" Documentos capturados com sucesso ou Lojista sem captura digital com a Losango
		//1 - Problemas Técnicos
		//2 - Cliente não autoriza
		//3 - Outros"	X
		esperado.getDadoComplementar().setCapturarDocumento("");
		
		//1566 a 1566	indicadorCapturarBiometria	1	A	Indicador da captura da Biometria	""" "" Documentos capturados com sucesso ou Lojista sem captura digital com a Losango
		//1 - Problemas Técnicos
		//2 - Cliente não autoriza
		//3 - Outros"	X
		esperado.getDadoComplementar().setCapturarBiometria("");
		
		//1567 a 1613	Filler	47	A
		esperado.getDadoComplementar().setFiller(AdaptadorTipo.escreverString(47, " "));
		
		
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
		esperado.getReferenciasPessoais().get(0).setTelefone(new TelefoneRamal());
		esperado.getReferenciasPessoais().get(0).setNome("THIAGO IRMAO");
		esperado.getReferenciasPessoais().get(0).getTelefone().setDdd(6);
		esperado.getReferenciasPessoais().get(0).getTelefone().setNumero(730222758);
		esperado.getReferenciasPessoais().get(0).getTelefone().setRamal(0);
				
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
		esperado.getReferenciasPessoais().get(1).setTelefone(new TelefoneRamal());
		esperado.getReferenciasPessoais().get(1).setNome("MARIA DO CARMO MAE");
		esperado.getReferenciasPessoais().get(1).getTelefone().setDdd(6);
		esperado.getReferenciasPessoais().get(1).getTelefone().setNumero(792818571);
		esperado.getReferenciasPessoais().get(1).getTelefone().setRamal(0);
		
		
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
		//esperado.getReferenciasPessoais().get(0).setTelefone(new TelefoneRamal());
		//esperado.getReferenciasPessoais().get(0).setNome("");
		//esperado.getReferenciasPessoais().get(0).getTelefone().setDdd(null);
		//esperado.getReferenciasPessoais().get(0).getTelefone().setNumero(null);
		//esperado.getReferenciasPessoais().get(0).getTelefone().setRamal(null);
		
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
		//esperado.getReferenciasPessoais().get(1).setTelefone(new Telefone());
		//esperado.getReferenciasPessoais().get(1).setNome("");
		//esperado.getReferenciasPessoais().get(1).getTelefone().setDdd(null);
		//esperado.getReferenciasPessoais().get(1).getTelefone().setNumero(null);
		//esperado.getReferenciasPessoais().get(1).getTelefone().setRamal(null);

		
		//REFERÊNCIAS BANCARIAS						
		//1798 a 1801	Banco	4	N		Ver tabela de dominio Banco	X. Se algum campo da referencia bancaria for preenchuda 
		//1802 a 1805	Agência	4	N			X. Se algum campo da referencia bancaria for preenchuda 
		//1806 a 1806	DV Agência	1	A			
		//1807 a 1819	Conta Corrente	13	N			X. Se algum campo da referencia bancaria for preenchuda 
		//1820 a 1821	DV Conta Corrente	2	A			X. Se algum campo da referencia bancaria for preenchuda 
		//1822 a 1823	Tipo da Conta	2	A		Ver tabela de dominio Tipo de Conta Corrente	X. Se algum campo da referencia bancaria for preenchuda 
		//1824 a 1831	Data Abertura	8	N	DDMMAAAA		X. Se algum campo da referencia bancaria for preenchuda
		esperado.setReferenciaBancaria(new Banco());
		esperado.getReferenciaBancaria().setInfo(new InfoBanco());
		esperado.getReferenciaBancaria().getInfo().setNumero(341);
		esperado.getReferenciaBancaria().getInfo().setAgencia(1585);
		esperado.getReferenciaBancaria().getInfo().setDvAgencia("0");
		esperado.getReferenciaBancaria().getInfo().setContaCorrente(6446);
		esperado.getReferenciaBancaria().getInfo().setDvContaCorrente("0");
		esperado.getReferenciaBancaria().setTipoConta("01");
		esperado.getReferenciaBancaria().setDataAbertura(UtilsDate.parse("13082001", UtilsDate.FormatadorData.DATA));
		

		//DADOS DA OPERAÇÃO
		esperado.setDadosOperacao(new PropostaFinanciamento.DadoOperacao());
		//1832 a 1839	Tabela de Financiamento	8	N	Identificação da tabela de financiamento  (COP’s) referente ao crédito solicitado, específica para o lojista ou Crédito Pessoal 		X
		esperado.getDadosOperacao().setTabelaFinanciamento(288799);
		
		//1840 a 1840	Sinal da Carência 	1	A	Sinal da carência  (+) - Positiva  (-) - Negativa	"+"  -  "-" 	X
		esperado.getDadosOperacao().setSinalCarencia("+");
		
		//1841 a 1842	Carência 	2	N	Quantidade de Dias Para Ajuste do Vencimento da Prestação		X
		esperado.getDadosOperacao().setCarencia(0);
		
		//1843 a 1843	Forma de pagamento	1	N	"Indicação da Forma de Cobrança
		//0) Carnë
		//1) averbação em folha
		//2) Cheque Pré
		//3) Extrato Rotativo
		//4) Extrato Parcelado
		//5) Debito em Conta"	“0”  “1”  “2”   “3”  ”4”, "5"	X
		esperado.getDadosOperacao().setFormaPagamento(FormaPagamento.CARNE);
		
		
		//1844 a 1851	Data da  Operação	8	N	Data da Realização da Operação		X
		esperado.getDadosOperacao().setDataOperacao(UtilsDate.parse("25082015", UtilsDate.FormatadorData.DATA));
		
		//1852 a 1853	Produto (Tabela de Produto)	2	N	Informar o Produto		X
		esperado.getDadosOperacao().setProduto(1);
		
		//1854 a 1855	Prestações	2	N	Indicar O Nº de Parcelas do contrato		X
		esperado.getDadosOperacao().setPrestacoes(10);
		
		//1856 a 1862	Taxa Mensal	7	N	Taxa de Juros Aplicada Ao Mês (2 inteiras e 5 decimais)		X
		esperado.getDadosOperacao().setTaxaMensal(6.49000);
		
		//1863 a 1869	Taxa Anual	7	N	Taxa de Juros Aplicada Ao Ano (3 inteiras 4 decimais)		
		esperado.getDadosOperacao().setTaxaAnual(0.0);
		
		//1870 a 1884	Valor da Entrada (não é mais utilizado)	15	N	Valor da Entrada (não é mais utilizado)		
		esperado.getDadosOperacao().setValorEntrada(0.00);
		
		//1885 a 1885	Tipo de Pagamento	1	N	0-Pré 1-Pós (Flag que indica se a negociação será efetuada com Pré fixado ou Pos fixado)	“0”  “1”	X
		esperado.getDadosOperacao().setTipoPagamento(0);
		
		//1886 a 1887	Top	2	N	Tipo de Operação		X
		esperado.getDadosOperacao().setTop(1);
		
		//1888 a 1902	Valor Tac	15	N	Valor da TAC (em R$)		X
		esperado.getDadosOperacao().setValorTac(0);
		
		//1903 a 1903	Pag_Tac	1	N	Flag que indica a forma de pagamento da TAC ( 0 -Financiada   1- A vista 2 - Descontada em (RO))	“0”   “1”    “2”	X
		esperado.getDadosOperacao().setPagTac(0);
		
		//1904 a 1918	Valor  da Operação/Solicitado	15	N	Valor solicitado pelo cliente (em R$)		X
		esperado.getDadosOperacao().setValorOperacaoSolicitado(70000);
		
		//1919 a 1933	Valor Total do Financiamento	15	N	Valor Total do Financiamento (em R$).		X
		esperado.getDadosOperacao().setValorTotalFinanciado(70000);
		
		//1934 a 1948	Valor da Prestação 	15	N	Valor A Ser Pago Mensalmente Já Com Taxa de Juros (em R$).		X
		esperado.getDadosOperacao().setValorPrestacao(9920);
		
		//1949 a 1956	Vencimento 1ª prestação	8	N	Data do primeiro  vencimento		X
		esperado.getDadosOperacao().setVencimentoPrimeiraPrestacao(UtilsDate.parse("25092015", UtilsDate.FormatadorData.DATA));
		
		//1957 a 1981	Descrição do bem	25	A	Identificação da mercadoria financiada (obrigatório para TOP 31 e 34)
		esperado.getDadosOperacao().setDescricaoDoBem("");
		
		//1982 a 1982	Imp_Carne	1	A	Flag que indica que o lojista vai imprimir carnê na loja (0-Não   e     1-Sim)	“0”     “1”	X
		esperado.getDadosOperacao().setImprimeCarne(true);
		
		//1983 a 1997	Nº Pedido	15	A	Campo para o lojista associar o número de pedido, nota fiscal, etc.
		esperado.getDadosOperacao().setNumeroPedido("");
		
		//1998 a 2008	Nº do CD	11	A	Numeração gráfica pré impressa do comprovante de débito ( não é obrigatorio)
		esperado.getDadosOperacao().setNumeroCd("");
		
		//2009 a 2019	CPF do Vendedor	11	A	Identificação do vendedor/atendente  responsável pela operação		X
		esperado.getDadosOperacao().setCpfVendedor("01234567890");
		
		//2020 a 2033	Telefone	14	A	Telefone do Vendedor		X
		esperado.getDadosOperacao().setTelefoneVendedor("");
		
		//2034 a 2034	Pre-Pago	1	A	"Indica a compra de telefone celular Pre-Pago
		//  0 - Default - Não
		//  1 - Pre     - Sim"	 '0' '1'	X
		esperado.getDadosOperacao().setPrePago(false);
		
		//2035 a 2035	Leva na Hora	1	A	"Indica se o cliente levara a mercadoria na hora
		//  0 - Default - Não
		//  1 - leva    - Sim"	 '0' '1'	X
		esperado.getDadosOperacao().setLevaNaHora(true);
		
		//2036 a 2036	Beta-Gama	1	A	"Indicadore de Fraude
		//  0 - Default - Sem fraude
		//  2 - Beta    - Susp fraude
		//  1 - Gama    - Confirm fraude"	 '0' '1' '2'	X
		esperado.getDadosOperacao().setBetaGama(0);
		
		//2037 a 2046	Promotor	10	N	Código do Promotor
		esperado.getDadosOperacao().setPromtor("");
		
		//2047 a 2047	Indicador aceita consulta ao sysbacen	1	A	Indica se o cliente permitiu a consulta ao sysbacen 0- Não(Default), 1 - Sim	 '0' '1'	X
		esperado.getDadosOperacao().setAceitaConsultaSysBacen(false);
		
		//2048 a 2054	CET Mensal (%)	7	N	Taxa Mensal do Custo efetivo Total (2 decimais)	"Preencher com o valor 
		//informado pelo Simulador ou zero caso o simulador não foi utilizado"	
		esperado.getDadosOperacao().setCetMensal(0.00);
		
		//2055 a 2061	CET Anual  (%)	7	N	Taxa Anual   do Custo efetivo Total (2 decimais)	"Preencher com o valor 
		//informado pelo Simulador ou zero caso o simulador não foi utilizado"
		esperado.getDadosOperacao().setCetAnual(0.00);
		
		//2062 a 2068	IOF	7	N	Valor do IOF (2 casas decimais)	"Preencher com o valor 
		//informado pelo Simulador ou zero caso o simulador não foi utilizado"
		esperado.getDadosOperacao().setValorIof(0.00);
		
		//2069 a 2076	Data do Evento	8	N	Data da Entrega do Bem/Serviço		X. Se o produto for cessão
		esperado.getDadosOperacao().setDataEvento(null);
		
		//2077 a 2091	Valor da Entrada ao Lojista	15	N	Valor dado de entrada ao Lojista
		esperado.getDadosOperacao().setValorEntradaLojista(0);
		
		//2092 a 2141	Filler	50	A
		esperado.getDadosOperacao().setFiller(AdaptadorTipo.escreverString(50, " "));
		
		//Dados do Pre Screening						
		//2142 a 2143	Código da Oferta Aderida de Conta Corrente	2	A	Código da Oferta recuperada na mensagem 0460.
		esperado.setCodigoOfertaAderidaContaCorrente("");
		
		//2144 a 2145	Código da Oferta Aderida de CDC/EP.	2	A	Código da Oferta recuperada na mensagem 0460.		
		esperado.setCodigoOfertaAderidaCdc("");
		
		//2146 a 2147	Código do Perfil da Oferta Aderida de CDC/EP.	2	A	Código do Perfil recuperado na mensagem 0460.		
		esperado.setCodigoPerfilOfertaAderidaCdc("");
		
		//2148 a 2160	Filler	13	A
		esperado.setFillerPreScreening(AdaptadorTipo.escreverString(13, " "));
		
		
		//Atendimento ao Cliente						
		//2161 a 2195	Nome do Vendedor	35	A			X
		esperado.setNomeVendedor("VENDEDOR TESTE");
		
		//2196 a 2230	Nome do Agente Correspondente	35	A
		esperado.setNomeAgenteCorrespondente("CERTIFICADOR TESTE");
		
		//2231 a 2241	CPF do Agente Correspondente	11	A			X
		esperado.setCpfAgenteCorrespondente("01115303180");
		
		//Dados de Operações para Emprestimo Pessoal (EP) e averbação em folha.
		esperado.setBancoEp(new BancoEp());
		esperado.getBancoEp().setInfo(new InfoBanco());
		//2242 a 2242	Ind. Pagto DOC - EP	1	A	Indicador de pagamento em DOC	"S = Sim com Doc
		esperado.getBancoEp().setPagtoDoc(false);

		//2243 a 2244	Tipo de Conta (spb) - EP	2	A	Ver tabela de dominio		X. Se Ind. Pagto DOC - EP = "S"
		esperado.getBancoEp().setTipoConta("");
		
		//2245 a 2248	Banco - EP	4	N	Número do Banco para Depósito do Emprestimo pessoal		X. Se Ind. Pagto DOC - EP = "S"
		esperado.getBancoEp().getInfo().setNumero(0);
		
		//2249 a 2252	Agencia - EP	4	N	Número da Agencia para Depósito do Emprestimo pessoal		X. Se Ind. Pagto DOC - EP = "S"
		esperado.getBancoEp().getInfo().setAgencia(0);
		
		//2253 a 2253	DV Agencia  - EP	1	A	DV da Agencia para Depósito do Emprestimo pessoal		X. Se Ind. Pagto DOC - EP = "S"
		esperado.getBancoEp().getInfo().setDvAgencia("");
		
		//2254 a 2266	Nº da conta - EP	13	N	Número da Conta para Depósito do Emprestimo pessoal		X. Se Ind. Pagto DOC - EP = "S"
		esperado.getBancoEp().getInfo().setContaCorrente(0);
		
		//2267 a 2268	Dv da conta  - EP	2	A	DV da Conta para deposito do Emprestimo pessoal		X. Se Ind. Pagto DOC - EP = "S"
		esperado.getBancoEp().getInfo().setDvContaCorrente("");
		
		//2269 a 2269	C2 - EP	1	N			X. Se Ind. Pagto DOC - EP = "S"
		esperado.getBancoEp().setC2(0);
		
		//2270 a 2270	C3 - EP	1	N			X. Se Ind. Pagto DOC - EP = "S"
		esperado.getBancoEp().setC3(0);		
		
		
		//Dados referente ao Seguro						
		//2271 a 2271	Adesao_Seguro	1	N	Flag que indica se o cliente optou por fazer seguro (0 - NÃO  ;  1 - SIM)	“0”   “1”	X
		esperado.setAdesaoSeguro(false);
		
		//2272 a 2272	Forma pagamento acessório	1	A	Forma de pagamento do produto acessório (V – a vista ; F – Financiado)	“V”  “F”	X.  Se o Campo Adesao_Seguro estiver como 1.
		esperado.setFormaPagamentoAcessorio("");
		
		//2273 a 2273	Quantidade de Seguro	1	N	Indica se o Cliente vai aderir a um Seguro ou aos dois Seguros.	"0 - Não vai aderir;
		esperado.setQuantidadeSeguro(null);
		
		
		//Dados referente ao Seguro Prestamista
		esperado.setSeguroPremista(new InfoSeguro());
		//2274 a 2275	tipo do seguro	2	A	Tipo de produto acessório	"02"	X. Se algum campo do seguro prestamista for preenchido
		esperado.getSeguroPremista().setTipo("");
		
		//2276 a 2279	código do seguro	4	A	Codigo do produto acessório	De acordo com a Matriz de Seguros	X. Se algum campo do seguro prestamista for preenchido
		esperado.getSeguroPremista().setCodigo("");
		
		//2280 a 2294	valor do seguro	15	N	Valor do produto acessório (em R$)	De acordo com a Matriz de Seguros	X. Se algum campo do seguro prestamista for preenchido
		esperado.getSeguroPremista().setValor(0);

		//Dados referente ao Seguro da Sorte		
		esperado.setSeguroSorte(new InfoSeguro());
		//2295 a 2296	tipo do seguro	2	A	Tipo de produto acessório	"02"	X. Se algum campo do seguro da sorte for preenchido
		esperado.getSeguroSorte().setTipo("");
		
		//2297 a 2300	código do seguro	4	A	Codigo do produto acessório	De acordo com a Matriz de Seguros	X. Se algum campo do seguro da sorte for preenchido
		esperado.getSeguroSorte().setCodigo("");
		
		//2301 a 2315	valor do seguro	15	N	Valor do produto acessório (em R$)	De acordo com a Matriz de Seguros	X. Se algum campo do seguro da sorte for preenchido
		esperado.getSeguroSorte().setValor(0);
		
		//2316 a 2317	Quantidade Numero da Sorte	2	N	Usar o valor da matriz de Seguros	De acordo com a Matriz de Seguros	X. Se algum campo do seguro da sorte for preenchido
		esperado.setQuantidadeNumeroSorte(0);


		//DEBITO EM CONTA						
		esperado.setDebitoConta(new Banco());
		esperado.getDebitoConta().setInfo(new InfoBanco());
		//2318 a 2321	Banco	4	N		Ver tabela de dominio Banco	X. Se Forma de pagamento = "5"
		esperado.getDebitoConta().getInfo().setNumero(0);
		
		//2322 a 2325	Agencia	4	N			X. Se Forma de pagamento = "5"
		esperado.getDebitoConta().getInfo().setAgencia(0);
		
		//2326 a 2326	DV Agência	1	A
		esperado.getDebitoConta().getInfo().setDvAgencia("");
		
		//2327 a 2339	Conta Corrente	13	N			X. Se Forma de pagamento = "5"
		esperado.getDebitoConta().getInfo().setContaCorrente(0);
		
		//2340 a 2341	DV Conta Corrente	2	A			X. Se Forma de pagamento = "5"
		esperado.getDebitoConta().getInfo().setDvContaCorrente("");
		
		//2342 a 2343	Tipo da Conta	2	A		Ver tabela de dominio Tipo de Conta Corrente	X. Se Forma de pagamento = "5"
		esperado.getDebitoConta().setTipoConta("");
		
		//2344 a 2351	Data de Abertura	8	N	DDMMAAAA		X. Se Forma de pagamento = "5"
		esperado.getDebitoConta().setDataAbertura(null);
		
		
		//Dados de Cheques
		esperado.setDadosCheque(new PropostaFinanciamento.DadoCheque());
		//2352 a 2354	CÓDIGO DO BANCO dos cheques	3	N	Código do Banco da primeira faixa de cheques para as operações de cheque-pré	Se produto igual a "2"	X
		esperado.getDadosCheque().setCodigoBanco(0);
		
		//2355 a 2358	AGÊNCIA  DE DESTINO dos cheques	4	N	Código da Agência Bancária da primeira faixa de cheques para as operações de cheque-pré	Se produto igual a "2"	X
		esperado.getDadosCheque().setAgenciaDestino(0);
		
		//2359 a 2359	DV Agencia dos cheques	1	A
		esperado.getDadosCheque().setDvAgenciaDestino("");
		
		//2360 a 2372	Codigo da Conta	13	N			X
		esperado.getDadosCheque().setCodigoConta(0);
		
		//2373 a 2374	DV da Conta	2	A			X
		esperado.getDadosCheque().setDvConta("");
		
		//2375 a 2380	NÚMERO DO CHEQUE do Primeiro Cheque da 1a. Faixa de Cheques	6	N	Número do primeiro cheque da primeira faixa de cheques para as operações de cheque-pré	Se produto igual a "2"	X
		esperado.getDadosCheque().setNumeroPrimeiroChequeFaixa1(0);
		
		//2381 a 2386	NÚMERO DO CHEQUE do Último Cheque da 1a. Faixa de Cheques	6	N	Número do último cheque da primeira faixa de cheques para as operações de cheque-pré	Se produto igual a "2"	X
		esperado.getDadosCheque().setNumeroUltimoChequeFaixa1(0);
		
		//2387 a 2392	NÚMERO DO CHEQUE do Primeiro Cheque da 2a. Faixa de Cheques	6	N	Número do primeiro cheque da segunda faixa de cheques para as operações de cheque-pré		
		esperado.getDadosCheque().setNumeroPrimeiroChequeFaixa2(0);
		
		//2393 a 2398	NÚMERO DO CHEQUE do Último Cheque da 2a. Faixa de Cheques	6	N	Número do último cheque da segunda faixa de cheques para as operações de cheque-pré		
		esperado.getDadosCheque().setNumeroUltimoChequeFaixa2(0);
		
		//2399 a 2406	Data da abertura da conta corrente	8	N	Data da abertura da conta corrente	Se produto igual a "2"	X
		esperado.getDadosCheque().setDataAberturaConta(null);
		
		//2407 a 2454	Filler	48	A			
		esperado.getDadosCheque().setFiller(AdaptadorTipo.escreverString(48, " "));
		
		//Circular 3641 Banco Central						
		//2455 a 2456	Flag Circular 3461 Banco Central	2	A	informar sempre o valor X2 nesse campo	X2	X
		esperado.setCircular3461BancoCentral("X2");
		
		//Observação						
		//2457 a 2711	Observação	255	A	Campo observação 	 	
		esperado.setObservacao("PROPOSTA TESTE MATRIZ FAVOR NAO ANALISAR NA MESA");
		
		//validação outros indicadores
		esperado.setIndicadores(new Indicador());
		esperado.getIndicadores().setIdentificadorCanal("T");
		esperado.getIndicadores().setVersaoCanal("");
		esperado.getIndicadores().setPolitica("");
		esperado.getIndicadores().setAmbiente("");
		
		return esperado;
	}
	
	@Test
	public void criarPropostaFinanciamento() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException {
		
		String mensagem = MensagemHelper.lerMensagem(2725, 100, "criarPropostaFinanciamento.1");
		
		PropostaFinanciamento m = (PropostaFinanciamento) MensagemFactory.parse(mensagem);
		PropostaFinanciamento esperado = criarPropostaFinanciamentoMensagem(mensagem);
		
		assertThat(m, BeanMatchers.theSameAs(esperado));
	}
	
	@Test
	public void criarRespostaPropostaFinanciamento() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		String mensagem = MensagemHelper.lerMensagem(5102, CodigoMensagem.C0110.toInt(), "criarRespostaPropostaFinanciamento.1");
		
		RespostaPropostaFinanciamento m = (RespostaPropostaFinanciamento) MensagemFactory.parse(mensagem);
		RespostaPropostaFinanciamento esperado = criarRespostaPropostaMensagem(mensagem, RespostaPropostaFinanciamento.class);
		
		assertThat(m, BeanMatchers.theSameAs(esperado));

	}
	
	public static RespostaPropostaFinanciamento criarRespostaPropostaFinanciamentoMensagem(String mensagem) throws MensagemNaoEncontradaException, ParseException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		return criarRespostaPropostaMensagem(mensagem, RespostaPropostaFinanciamento.class);
	}
	

	private static <T extends RespostaPropostaPadrao> T criarRespostaPropostaMensagem(String mensagem, Class<T> clazz) throws MensagemNaoEncontradaException, ParseException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Cabecalho cabecalhoEsperado = new Cabecalho();
		cabecalhoEsperado.setSentidoFluxo(Fluxo.SAIDA);
		cabecalhoEsperado.setTamanho(5019);
		cabecalhoEsperado.setCodigo(CodigoMensagem.parse(mensagem.substring(5, 9)));
		cabecalhoEsperado.setNumeroTransacao(980009);
		cabecalhoEsperado.setNumeroProposta("P4201170358");
		cabecalhoEsperado.setCodigoUsuario("UILSON");
		cabecalhoEsperado.setCodigoRetorno("A0050");
		cabecalhoEsperado.setCodigoLojista(170894002);
		cabecalhoEsperado.setVersao("9");
		cabecalhoEsperado.setCampoLojista("TOP 01       6  06            ");
		
		Constructor<T> constructor = clazz.getConstructor(String.class, Cabecalho.class);
		T esperado = constructor.newInstance(ContextoMensagem.md5(mensagem), cabecalhoEsperado);
		
		//DADOS RESPOSTA DA PROPOSTA					
		//0084 a 0084	Indicador Liberação Cessão	1	A	Indica se a cessão foi realizada pelo Lojista	"""0"" - Não cedido
		//""1"" - Cedido
		//"" "" - Produto não possui cessão"
		esperado.setLiberacaoCessao("0");
		
		//0085 a 0339	Restrição	255	A	Motivo da restrição
		esperado.setRestricao("Aprovada CPF de Teste");
		
		//0340 a 0346	Cod_autorizador	7	N	Código de autorização
		esperado.setCodAutorizador(11621);
		
		//0347 a 0354	Data_Autorização	8	N	Data da resposta do Autorizador
		esperado.setDataAutorizacao(UtilsDate.parse("25082015", UtilsDate.FormatadorData.DATA));
		
		//0355 a 0404	Filler	50	A
		esperado.setFillerRespostaProposta(AdaptadorTipo.escreverString(50, " "));
		
		//DADOS DA OPERAÇÃO					
		//0405 a 0406	Prestações	2	N	Indicar O Nº de Parcelas do contrato
		esperado.setNumeroPrestacoes(10);
		
		//0407 a 0421	Valor da Prestação 	15	N	Valor A Ser Pago Mensalmente Já Com Taxa de Juros (em R$)
		esperado.setValorPrestacao(9920);
		
		
		//DADOS PARA IMPRESSÃO DE CARNÊ
		esperado.setDadosImpressao(new RespostaPropostaFinanciamento.DadoCarne());
		
		//0422 a 0451	Nome_Cedente	30	A	Nome do Cedente
		esperado.getDadosImpressao().setNomeCedente("HSBC Finance Brasil S.A.");
		
		//0452 a 0453	Especie_Doc	2	A	Especie do Documento
		esperado.getDadosImpressao().setEspecieDocumento("CS");
		
		//0454 a 0454	Aceite	1	A	Identificação do aceite Default=N
		esperado.getDadosImpressao().setAceite(false);
		
		//0455 a 0457	CIP	3	A	CIP
		esperado.getDadosImpressao().setCip("775");
		
		//0458 a 0460	Carteira	3	A	Tipo de cobrança
		esperado.getDadosImpressao().setCarteira("006");
		
		//0461 a 0465	Especie	5	A	Tipo de Moeda
		esperado.getDadosImpressao().setEspecie("REAL");
		
		//0466 a 0480	Quantidade	15	N	Quantidade relacionada a especie de moeda (cinco (5) decimais)
		esperado.getDadosImpressao().setQuantidade(0.0);
		
		//0481 a 0495	Valor_Mora_Dia	15	N	Valor do juros de mora expresso em quantidade de moeda (2) decimais)
		esperado.getDadosImpressao().setValorMoraDia(0.21);
		
		//0496 a 0510	Val_A_Pagar	15	N	Valor da parcela a pagar com os encargos
		esperado.getDadosImpressao().setValorPagar(9920);
		
		//0511 a 0513	Banco	3	N	Codigo do Banco
		esperado.getDadosImpressao().setBanco(237);
		
		//0514 a 0517	Agencia_Cedente	4	N	Codigo da Agencia
		esperado.getDadosImpressao().setAgenciaCedente(2372);
		
		//0518 a 0518	Agencia_Digito_Cedente	1	A	Digito verificador da Agencia
		esperado.getDadosImpressao().setDvAgenciaCedente("8");
		
		//0519 a 0525	Codigo_Cedente	7	N	Número da conta do cedente
		esperado.getDadosImpressao().setCodigoCedente(886);
		
		//0526 a 0526	Digito_Cedente	1	A	Digito verificador da C.C. do cedente
		esperado.getDadosImpressao().setDigitoCedente("9");
		
		esperado.getDadosImpressao().setMensagens(new LinkedList<String>());
		//0527 a 0606	Msg_01	80	A	Campo de Instrução
		esperado.getDadosImpressao().getMensagens().add("APOS VENCIMENTO, PAGUE NO BRADESCO");
		
		//0607 a 0686	Msg_02	80	A	Campo de Instrução
		esperado.getDadosImpressao().getMensagens().add("INCIDIRA MULTA DE 0.00 % SOBRE VALOR TOTAL");
		
		//0687 a 0766	Msg_03	80	A	Campo de Instrução
		esperado.getDadosImpressao().getMensagens().add("TOP 01 PARCELA ?/10MAGAZINE LUIZA SA / Cod Lojista: 170894002");
		
		//0767 a 0846	Msg_04	0080	A	Campo de Instrução
		esperado.getDadosImpressao().getMensagens().add("END: R JOSE LOUREIRO, 711 - LJ 1/2 CENTRO CURITIBA PR 80010000");
		
		//0847 a 0926	Msg_05	0080	A	Campo de Instrução
		//sem mensagens
		
		//0927 a 1006	Msg_06	0080	A	Campo de Instrução
		//sem mensagens
		
		//1007 a 1086	Filler	80	A
		esperado.getDadosImpressao().setFiller(AdaptadorTipo.escreverString(80, " "));
		
		
		//validação de dados pessoais
		esperado.setDadosCliente(new RespostaPropostaFinanciamento.DadoCliente());
		esperado.getDadosCliente().setNome("PROPOSTA TESTE");
		esperado.getDadosCliente().setTipoPersonalidadeCpf("F");
		esperado.getDadosCliente().setCpf("00000000000191");
		esperado.getDadosCliente().setCorrespondencia("1");
		esperado.getDadosCliente().setIdentidade(new DocumentoTp1());
		esperado.getDadosCliente().getIdentidade().setAttr(new DocumentoAttr());
		esperado.getDadosCliente().getIdentidade().setNumero("201570496  0");
		esperado.getDadosCliente().getIdentidade().getAttr().setTipo("01");
		esperado.getDadosCliente().getIdentidade().getAttr().setOrgaoEmissor("SSP");
		esperado.getDadosCliente().getIdentidade().getAttr().setUfOrgaoEmissor("MS");
		esperado.getDadosCliente().getIdentidade().getAttr().setDataEmissao(UtilsDate.parse("06072001", UtilsDate.FormatadorData.DATA));
		
		esperado.getDadosCliente().setConjugeCompoeRenda(false);
		esperado.getDadosCliente().setLocalNascimento("CAMPO GRANDE");
		esperado.getDadosCliente().setDataNascimento(UtilsDate.parse("20101944", UtilsDate.FormatadorData.DATA));
		esperado.getDadosCliente().setSexo("F");
		esperado.getDadosCliente().setNacionalidade(0);
		esperado.getDadosCliente().setNaturalidade("MATO GROSSO DO");
		esperado.getDadosCliente().setNomeDaMae("MARIA DO CARMO PINHEIRO NE");
		esperado.getDadosCliente().setNomeDoPai("LEANDRO NE");
		esperado.getDadosCliente().setCtps(0);
		esperado.getDadosCliente().setSerieCtps("00000");
		esperado.getDadosCliente().setEstadoCivil(1);
		
		esperado.getDadosCliente().setEnderecoResidencia(new EnderecoTp2());
		esperado.getDadosCliente().getEnderecoResidencia().setLogradouro("RUA ITAQUERA");
		esperado.getDadosCliente().getEnderecoResidencia().setNumero("68");
		esperado.getDadosCliente().getEnderecoResidencia().setComplemento("");
		esperado.getDadosCliente().getEnderecoResidencia().setBairro("FLAMBOYANT");
		esperado.getDadosCliente().getEnderecoResidencia().setCidade("CAMPO GRANDE");
		esperado.getDadosCliente().getEnderecoResidencia().setUf("RJ");
		esperado.getDadosCliente().getEnderecoResidencia().setCep(20765070);
		
		esperado.getDadosCliente().setTelefoneResidencia(new TelefoneRamal());
		esperado.getDadosCliente().getTelefoneResidencia().setDdd(67);
		esperado.getDadosCliente().getTelefoneResidencia().setNumero(30264981);
		esperado.getDadosCliente().getTelefoneResidencia().setRamal(0);
		
		esperado.getDadosCliente().setTipoTelefone(1);
		esperado.getDadosCliente().setTipoResidencia(1);
		
		esperado.getDadosCliente().setDataAdmissao(null);
		esperado.getDadosCliente().setEmpresa("JV TUBOS E ACABAMENTOS");
		esperado.getDadosCliente().setEnderecoTrabalho(new EnderecoTp2());
		esperado.getDadosCliente().getEnderecoTrabalho().setLogradouro("RUA JOAQUIM MURTINHO");
		esperado.getDadosCliente().getEnderecoTrabalho().setNumero("04665");
		esperado.getDadosCliente().getEnderecoTrabalho().setComplemento("");
		esperado.getDadosCliente().getEnderecoTrabalho().setBairro("CIDADE MORENA");
		esperado.getDadosCliente().getEnderecoTrabalho().setCidade("CAMPO GRANDE");
		esperado.getDadosCliente().getEnderecoTrabalho().setUf("MS");
		esperado.getDadosCliente().getEnderecoTrabalho().setCep(79041904);
		
		esperado.getDadosCliente().setTelefoneTrabalho(new TelefoneRamal());
		esperado.getDadosCliente().getTelefoneTrabalho().setDdd(67);
		esperado.getDadosCliente().getTelefoneTrabalho().setNumero(33481881);
		esperado.getDadosCliente().getTelefoneTrabalho().setRamal(0);
		
		esperado.getDadosCliente().setValorRendaLiquida(130000);
		esperado.getDadosCliente().setCargo("ADVOGADA");
		esperado.getDadosCliente().setProfissao("ASSALARIADO COM CART");
		esperado.getDadosCliente().setAposentado(false);
		esperado.getDadosCliente().setPensionista(false);
		esperado.getDadosCliente().setUsoExclusivoLosango("3");
		esperado.getDadosCliente().setResideDesde(null);
		
		esperado.getDadosCliente().setCelular(new Telefone());
		esperado.getDadosCliente().getCelular().setDdd(67);
		esperado.getDadosCliente().getCelular().setNumero(92169260);
		
		
		esperado.getDadosCliente().setEmail("TPNE@HOTMAIL.COM");
		esperado.getDadosCliente().setOrgaoBeneficio("");
		esperado.getDadosCliente().setNumeroBeneficio("");
		esperado.getDadosCliente().setPossuiPatrimonio(false);
		esperado.getDadosCliente().setPatrimonio("      00000000000      00000000000      00000000000      00000000000");
		esperado.getDadosCliente().setFillerPatrimonio(" ");
		
		esperado.getDadosCliente().setCodigoPais("");
		esperado.getDadosCliente().setCodigoUfNaturalidade("MS");
		esperado.getDadosCliente().setDataComprovanteRenda(UtilsDate.parse("062015", UtilsDate.FormatadorData.DATA_CURTA));
		esperado.getDadosCliente().setTipoComprovante("H");
		esperado.getDadosCliente().setOcupacaoNova("03");
		esperado.getDadosCliente().setDataVencimentoDocumentoIdentificacao(null);
		
		esperado.getDadosCliente().setCnpj("00000000000000");
		esperado.getDadosCliente().setEmancipado(false);
		esperado.getDadosCliente().setOrigemPatrimonio(new LinkedList<String>());
		esperado.getDadosCliente().setFiller(AdaptadorTipo.escreverString(9, " "));
		
		
		//DADOS DO CÔNJUGE
		esperado.setDadosConjuge(new RespostaPropostaFinanciamento.DadoConjuge());
		//1848 a 1877	Nome Do Cônjuge	30	A	Nome do Cônjuge do Cliente
		esperado.getDadosConjuge().setNome("");
		
		//1878 a 1917	Local de Nascimento	40	A
		esperado.getDadosConjuge().setLocalNascimento("");
		
		//1918 a 1925	Data de Nascimento	8	N	Data nascimento do cônjuge
		esperado.getDadosConjuge().setDataNascimento(null);
		
		//1926 a 1936	CPF	11	N	CPF do Cônjuge	
		esperado.getDadosConjuge().setCpf("00000000000");
		
		esperado.getDadosConjuge().setIdentidade(new DocumentoTp3());;
		esperado.getDadosConjuge().getIdentidade().setAttr(new DocumentoAttr2());
		//1937 a 1946	Identidade	10	A	Número da Identidade do Cônjuge
		esperado.getDadosConjuge().getIdentidade().setNumero("0000000000");
		
		//1947 a 1948	Tipo de Documento	2	A
		esperado.getDadosConjuge().getIdentidade().getAttr().setTipo("");
		
		//1949 a 1953	Órgão Emissor	5	A	Órgão Emissor do Documento de Identidade do Cônjuge	Ver tabela de dominio
		esperado.getDadosConjuge().getIdentidade().getAttr().setOrgaoEmissor("");
		
		//1954 a 1955	UF Órgão Emissor	2	A
		esperado.getDadosConjuge().getIdentidade().getAttr().setUfOrgaoEmissor("");
		
		//1956 a 1963	Data Emissão	8	N	Data de emissão da identidade
		esperado.getDadosConjuge().getIdentidade().getAttr().setDataEmissao(null);
		
		//1964 a 1988	Empresa 	25	A	Empresa Em Que o Cônjuge Trabalha	
		esperado.getDadosConjuge().setEmpresa("");
		
		//1989 a 1996	Data  Admissão	8	N	Data da Admissão Na Empresa
		esperado.getDadosConjuge().setDataAdmissao(null);
		
		esperado.getDadosConjuge().setEnderecoComercial(new EnderecoTp2());
		//1997 a 2036	Logradouro	40	A	Endereço Comercial do Cônjuge
		esperado.getDadosConjuge().getEnderecoComercial().setLogradouro("");
		
		//2037 a 2041	Numero	5	A
		esperado.getDadosConjuge().getEnderecoComercial().setNumero("");
		
		//2042 a 2056	Complemento	15	A		
		esperado.getDadosConjuge().getEnderecoComercial().setComplemento("");
		
		//2057 a 2071	Bairro	15	A	Bairro onde trabalha o cônjuge
		esperado.getDadosConjuge().getEnderecoComercial().setBairro("");
		
		//2072 a 2086	Cidade	15	A	Cidade Onde Trabalha o cônjuge
		esperado.getDadosConjuge().getEnderecoComercial().setCidade("");
		
		//2087 a 2088	UF	2	A	Abreviatura da Unidade Federativa
		esperado.getDadosConjuge().getEnderecoComercial().setUf("");
		
		//2089 a 2096	CEP	8	N	CEP do endereço comercial do cônjuge
		esperado.getDadosConjuge().getEnderecoComercial().setCep(0);
		
		esperado.getDadosConjuge().setTelefoneTrabalho(new TelefoneRamal());
		//2097 a 2099	DDD	3	N	DDD da Cidade Onde Trabalha o cônjuge
		esperado.getDadosConjuge().getTelefoneTrabalho().setDdd(0);
		
		//2100 a 2108	Telefone	9	N	Telefone do Trabalho do cônjuge
		esperado.getDadosConjuge().getTelefoneTrabalho().setNumero(0);
		
		//2109 a 2112	Ramal	4	N	Ramal do Trabalho do cônjuge	
		esperado.getDadosConjuge().getTelefoneTrabalho().setRamal(0);
		
		//2113 a 2132	Cargo	20	A	Cargo do Cônjuge
		esperado.getDadosConjuge().setCargo("");
		
		//2133 a 2152	Profissão	20	A	Profissão do Conjuge
		esperado.getDadosConjuge().setProfissao("OUTROS");
		
		//2153 a 2153	Aposentado	1	A	"Aponta se o cliente é aposentado:
		//S - Sim; N - Não"	"S"  "N"
		//aceita null
		//esperado.getDadosConjuge().setAposentado(false);
		
		//2154 a 2154	Pensionista	1	A	"Aponta se o cliente é Pensionista:
		//S - Sim; N - Não"	"S"  "N"
		//aceita null
		//esperado.getDadosConjuge().setPensionista(false);
		
		//2155 a 2155	Uso exclusivo da Losango	1	A	Uso exclusivo da Losango
		esperado.getDadosConjuge().setUsoExclusivoLosango("1");
		
		//2156 a 2166	Valor Renda Líquida 	11	N	Renda Líquida do Cônjuge (em R$)
		esperado.getDadosConjuge().setValorRendaLiquida(0);
		
		//2167 a 2236	Patrimônio	70	A		
		esperado.getDadosConjuge().setPatrimonio(AdaptadorTipo.escreverString(70, " "));
		
		//2237 a 2237	Nacionalidade	1	N	"Nacionalidade do Conjuge
		//0-Brasileiro
		//1-Estrangeiro        "
		esperado.getDadosConjuge().setNacionalidade("");
		
		//2238 a 2239	Código do País (informar se nacionalidade = estrangeiro)	2	A	Tabela de Paises
		esperado.getDadosConjuge().setCodigoPais("");
		
		//2240 a 2241	Código da UF da Naturalidade (informar se nacionalidade = brasileiro)	2	A	Tabela de UF
		esperado.getDadosConjuge().setCodigoUfNaturalidade("");
		
		//2242 a 2247	Mes/Ano Comprovante de Renda	6	A		
		esperado.getDadosConjuge().setDataComprovanteRenda(null);
		
		//2248 a 2249	Tipo Comprovante de Renda	2	A
		esperado.getDadosConjuge().setTipoComprovanteRenda("");
		
		//2250 a 2251	Ocupação nova	2	A	Código da Profissão
		esperado.getDadosConjuge().setOcupacaoNova("11");
		
		//2252 a 2252	Sexo do Conjuge	1	A
		esperado.getDadosConjuge().setSexo("");
		
		//2253 a 2266	CNPJ Conjuge	14	A
		esperado.getDadosConjuge().setCnpj("");
		
		//2267 a 2286	Filler	20	A		
		esperado.getDadosConjuge().setFiller(AdaptadorTipo.escreverString(20, " "));
		
		//REFERÊNCIAS PESSOAIS
		esperado.setReferenciasPessoais(new LinkedList<Referencia>());
		
		esperado.getReferenciasPessoais().add(new Referencia());
		esperado.getReferenciasPessoais().get(0).setTelefone(new TelefoneRamal());
		//2287 a 2316	Nome	30	A	Nome da Pessoa de Referência
		esperado.getReferenciasPessoais().get(0).setNome("THIAGO IRMAO");
		
		//2317 a 2319	DDD	3	N	DDD da Pessoa de Referência
		esperado.getReferenciasPessoais().get(0).getTelefone().setDdd(67);
		
		//2320 a 2328	Telefone	9	N	Telefone da Pessoa de Referência
		esperado.getReferenciasPessoais().get(0).getTelefone().setNumero(30222758);
		
		//2329 a 2332	Ramal	4	N	Ramal da Pessoa de Referência
		esperado.getReferenciasPessoais().get(0).getTelefone().setRamal(0);
		
		esperado.getReferenciasPessoais().add(new Referencia());
		esperado.getReferenciasPessoais().get(1).setTelefone(new TelefoneRamal());
		//2333 a 2362	Nome	30	A	Nome da Pessoa de Referência
		esperado.getReferenciasPessoais().get(1).setNome("MARIA DO CARMO MAE");
		
		//2363 a 2365	DDD	3	N	DDD da Pessoa de Referência
		esperado.getReferenciasPessoais().get(1).getTelefone().setDdd(67);
		
		//2366 a 2374	Telefone	9	N	Telefone da Pessoa de Referência
		esperado.getReferenciasPessoais().get(1).getTelefone().setNumero(92818571);
		
		//2375 a 2378	Ramal	4	N	Ramal da Pessoa de Referência
		esperado.getReferenciasPessoais().get(1).getTelefone().setRamal(0);
		
		
		
		//REFERÊNCIAS COMERCIAIS
		esperado.setReferenciasComerciais(new LinkedList<Referencia>());
		//esperado.getReferenciasComerciais().add(new Referencia());
		//esperado.getReferenciasComerciais().get(0).setTelefone(new TelefoneRamal());
		//2379 a 2408	Nome 1	30	A	Nome da Pessoa de Referência  (PC)
		//esperado.getReferenciasComerciais().get(0).setNome("");
		
		//2409 a 2411	DDD 1	3	N	DDD da referencia comercial
		//esperado.getReferenciasComerciais().get(0).getTelefone().setDdd(0);
		
		//2412 a 2420	Telefone 1	9	N	Telefone da referencia Comercial
		//esperado.getReferenciasComerciais().get(0).getTelefone().setNumero(0);
		
		//2421 a 2424	Ramal 1	4	N	Ramal da referencia comercial	
		//esperado.getReferenciasComerciais().get(0).getTelefone().setRamal(0);
		
		//esperado.getReferenciasComerciais().add(new Referencia());
		//esperado.getReferenciasComerciais().get(1).setTelefone(new TelefoneRamal());
		//2425 a 2454	Nome 2	30	A	Nome da Pessoa de Referência  (PC)
		//esperado.getReferenciasComerciais().get(1).setNome("");
		
		//2455 a 2457	DDD 2	3	N	DDD da referencia comercial
		//esperado.getReferenciasComerciais().get(1).getTelefone().setDdd(0);
		
		//2458 a 2466	Telefone 2	9	N	Telefone da referencia Comercial
		//esperado.getReferenciasComerciais().get(1).getTelefone().setNumero(0);
		
		//2467 a 2470	Ramal 2	4	N	Ramal da referencia comercial	
		//esperado.getReferenciasComerciais().get(1).getTelefone().setRamal(0);
		
		
		
		//REFERÊNCIA BANCÁRIA
		esperado.setReferenciaBancaria(new Banco());
		esperado.getReferenciaBancaria().setInfo(new InfoBanco());
		//2471 a 2474	Banco	4	N
		esperado.getReferenciaBancaria().getInfo().setNumero(341);
		
		//2475 a 2478	Agência	4	N
		esperado.getReferenciaBancaria().getInfo().setAgencia(1585);
		
		//2479 a 2479	DV Agência	1	A
		esperado.getReferenciaBancaria().getInfo().setDvAgencia("0");
		
		//2480 a 2492	Conta Corrente	13	N
		esperado.getReferenciaBancaria().getInfo().setContaCorrente(6446);
		
		//2493 a 2494	DV Conta Corrente	2	A
		esperado.getReferenciaBancaria().getInfo().setDvContaCorrente("0");
		
		//2495 a 2496	Tipo da Conta	2	A
		esperado.getReferenciaBancaria().setTipoConta("01");
		
		//2497 a 2504	Data Abertura	8	N	DDMMAAAA
		esperado.getReferenciaBancaria().setDataAbertura(UtilsDate.parse("13082001", UtilsDate.FormatadorData.DATA));
		
		
		//DADOS COMPLEMENTARES
		esperado.setDadosComplementares(new RespostaPropostaFinanciamento.DadoComplementar());
		//2505 a 2506	Escolaridade	2	A	Codigo da Escolaridade
		esperado.getDadosComplementares().setEscolaridade("11");
		
		//2507 a 2526	Formação	20	A	Formação
		esperado.getDadosComplementares().setFormacao("OK");
		
		//2527 a 2527	Indicador Possui cartão	1	A	Indicador se possui cartão	0 - Não 1 - Sim
		esperado.getDadosComplementares().setPossuiCartao(false);
		
		//2528 a 2528	Indicador Possui veículo próprio	1	A	Indicador Possui veículo próprio	0 - Não 1 - Sim
		esperado.getDadosComplementares().setPossuiVeiculoProprio(false);
		
		//2529 a 2538	Placa	10	A
		esperado.getDadosComplementares().setPlaca("");
		
		//2539 a 2598	Renavam	60	A
		esperado.getDadosComplementares().setRenavam("");
		
		//2599 a 2599	Indicador Possui veículo quitado	1	A	Indicador Possui veículo quitado	0 - Não 1 - Sim
		esperado.getDadosComplementares().setPossuiVeiculoQuitado(false);
		
		//2600 a 2600	Possui experiencia de crédito	1	A	Indicador Possui experiência	0 - Não 1 - Sim
		esperado.getDadosComplementares().setPossuiExperienciaCredito(false);
		
		//2601 a 2620	Local da Experiência	20	A
		esperado.getDadosComplementares().setLocalExperiencia("");
		
		//2621 a 2622	Plano da Experiência	2	N
		esperado.getDadosComplementares().setPlanoExperiencia(0);
		
		//2623 a 2637	Valor da Prestação da Experiência	15	N
		esperado.getDadosComplementares().setValorPrestacaoExperiencia(0);
		
		//2638 a 2643	Inicio da Experiência de Crédito	6	N	Inicio da Experiência MMAAAA
		esperado.getDadosComplementares().setDataInicioExperienciaCredito(null);
		
		//2644 a 2683	Classificação do Cliente no Lojista	40	A
		esperado.getDadosComplementares().setClassificacaoClienteLojista("");
		
		//2684 a 2684	Indicador Possui Cartão Financeira	1	A	Indicador Possui Cartão Financeira	0 - Não 1 - Sim
		esperado.getDadosComplementares().setPossuiCartaoFinanceira(false);
		
		//2685 a 2685	Indicador Possui Conta Corrente	1	A	Indicador Possui Conta Corrente	0 - Não 1 - Sim
		esperado.getDadosComplementares().setPossuiContaCorrente(false);
		
		//2686 a 2686	Indicador Possui dependente	1	A		0 - Não 1 - Sim
		esperado.getDadosComplementares().setPossuiDependente(false);
		
		//2687 a 2688	Quantidade de dependentes	2	N		
		esperado.getDadosComplementares().setQuantidadeDependentes(0);
		
		//2689 a 2703	Nome do cartão	15	A		
		esperado.getDadosComplementares().setNomeCartao("");
		
		//2704 a 2704	Indicador DDA	1	A	Indicador se o cliente é DDA	0 - Não 1 - Sim
		esperado.getDadosComplementares().setDda(false);
		
		//2705 a 2754	Filler	50	A		
		esperado.getDadosComplementares().setFiller(AdaptadorTipo.escreverString(50, " "));

		
		//DADOS DA OPERAÇÃO
		esperado.setDadosOperacao(new RespostaPropostaFinanciamento.DadoOperacao());
		//2755 a 2755	Operação	1	A	Flag operação (1 -EP , 2- CDC)	“1”   “2”
		esperado.getDadosOperacao().setOperacao(2);
		
		//2756 a 2756	Primeira Compra	1	A	"Flag de Primeira Compra
		//0 - Não
		//1 - Sim"	“0”   “1”
		esperado.getDadosOperacao().setPrimeiraCompra(true);
		
		//2757 a 2771	Pedido	15	A	Campo do número de pedido, nota fiscal, etc. do lojista
		esperado.getDadosOperacao().setPedido("");
		
		//2772 a 2801	Nome Lojista	30	A	Nome do Lojista
		esperado.getDadosOperacao().setNomeLojista("MAGAZINE LUIZA");
		
		//2802 a 2805	Conveniada	4	N	Código da Empresa Conveniada
		esperado.getDadosOperacao().setConveniada(0);
		
		//2806 a 2806	Forma Pgto	1	N	"Indicação da Forma de Cobrança
		//0) Carnë
		//1) averbação em folha
		//2) Cheque Pré
		//3) Extrato Rotativo
		//4) Extrato Parcelado
		//5) Debito em Conta"	"0"  “1”   “2”  “3”  “4”, "5"
		esperado.getDadosOperacao().setFormaPagamento(0);
		
		//2807 a 2808	Carência	2	N	Quantidade de Dias Para Ajuste do Vencimento da Prestação
		esperado.getDadosOperacao().setCarencia(0);
		
		//2809 a 2810	Top	2	N	Tipo de Operação
		esperado.getDadosOperacao().setTop(1);
		
		//2811 a 2818	Tab Financiamento	8	N	Identificação da tabela de financiamento  (COP’s) referente ao crédito solicitado, específica para o lojista ou Crédito Pessoal
		esperado.getDadosOperacao().setTabelaFinanciamento(288799);
		
		//2819 a 2819	Tipo de Pagamento	1	N	0-Pré 1-Pós (Flag que indica se a negociação será efetuada com Pré fixado ou Pos fixado)	“0”   “1”
		esperado.getDadosOperacao().setTipoPagamento(0);
		
		//2820 a 2826	Taxa Anual	7	N	Taxa de Juros Aplicada Ao Ano
		esperado.getDadosOperacao().setTaxaAnual(30000);
		
		//2827 a 2833	Taxa Mensal	7	N	Taxa de Juros Aplicada Ao Mês
		esperado.getDadosOperacao().setTaxaMensal(649000);
		
		//2834 a 2841	Data da Operação	8	N	Data da Operação
		esperado.getDadosOperacao().setDataOperacao(UtilsDate.parse("25082015", UtilsDate.FormatadorData.DATA));
		
		//2842 a 2856	Valor  da Operação/Solicitado	15	N	Valor solicitado pelo cliente (em R$)
		esperado.getDadosOperacao().setValorOperacao(70000);
		
		//2857 a 2857	Pag_Tac	1	N	Flag que indica a forma de pagamento da TAC ( 0 -Financiada   1- A vista 2 - Descontada em (RO))	“0”   “1”    “2”
		esperado.getDadosOperacao().setPagTac(0);
		
		//2858 a 2872	Valor Tac	15	N	Valor da TAC (em R$)
		esperado.getDadosOperacao().setValorTac(0);
		
		//2873 a 2887	Valor da Entrada	15	N	Valor cobrado no ato da venda (em R$).
		esperado.getDadosOperacao().setValorEntrada(0);
		
		//2888 a 2902	Valor Total do Financiamento	15	N	Valor Total do Financiamento (em R$).
		esperado.getDadosOperacao().setValorTotalFinanciamento(70000);
		
		//2903 a 2907	Valor Tarifa Bancaria	5	N	Tarifa Bancária (2 casas decimais) (em R$)
		esperado.getDadosOperacao().setValorTarifaBancaria(0.0);
		
		//2908 a 2909	Produto	2	N	Produto (Top + Forma Pgto)
		esperado.getDadosOperacao().setProduto(1);
		
		//2910 a 2916	CET Mensal (%)	7	A	Taxa Mensal do Custo efetivo Total (2 decimais)
		esperado.getDadosOperacao().setCetMensal(6.77);
		
		//2917 a 2923	CET Anual  (%)	7	A	Taxa Anual   do Custo efetivo Total (2 decimais)
		esperado.getDadosOperacao().setCetAnual(121.89);
		
		//2924 a 2930	IOF	7	N	Valor do IOF (2 casas decimais)
		esperado.getDadosOperacao().setIof(13.45);
		
		//2931 a 2931	Indicador se existe RPS	1	N	Mostra se os campos do RPS estão preenchidos	0 - Não, 1 - Sim, 2 - Erro
		esperado.getDadosOperacao().setExisteRps(0);
		
		//2932 a 2949	Número do RPS	18	A	Campo de Instrução para o RPS
		esperado.getDadosOperacao().setNumeroRps("");
		
		//2950 a 2954	Série do RPS	5	A	Campo de Instrução para o RPS
		esperado.getDadosOperacao().setSerieRps("");
		
		//2955 a 2962	Data de Emissão do RPS	8	A	Campo de Instrução para o RPS
		esperado.getDadosOperacao().setDataEmissaoRps(null);
		
		//2963 a 2967	VLR Alíquota	5	A	Campo de Instrução para o RPS
		esperado.getDadosOperacao().setValorAliquota(0);
		
		//2968 a 2971	Filial Losango	4	A	Campo de Instrução para o RPS
		esperado.getDadosOperacao().setFilialLosango("0094");
		
		//2972 a 3021	Nome da Filial Losango	0050	A	Usado no RPS
		esperado.getDadosOperacao().setNomeFilialLosango("UBERLANDIA");                                        
		
		//3022 a 3035	CNPJ Filial Losango	0014	A	Usado no RPS
		esperado.getDadosOperacao().setCnpjFilialLosango("33550104000129");
		
		//3036 a 3055	Inscrição da Filial Losango	0020	A	Usado no RPS
		esperado.getDadosOperacao().setInscricaoFilialLosango("");
		
		esperado.getDadosOperacao().setEndereoFilialLosango(new EnderecoTp3());
		//3056 a 3120	Logradouro da Filial Losango	0065	A	Usado no RPS
		esperado.getDadosOperacao().getEndereoFilialLosango().setLogradouro("RUA OLEGARIO MACIEL 533");
		
		//3121 a 3125	Numero (Endereço) da Filial Losango	0005	A	Usado no RPS
		esperado.getDadosOperacao().getEndereoFilialLosango().setNumero("");
		
		//3126 a 3140	Complemento da Filial Losango	0015	A	Usado no RPS
		esperado.getDadosOperacao().getEndereoFilialLosango().setComplemento("");
		
		//3141 a 3175	Bairro da Filial Losango	0035	A	Usado no RPS
		esperado.getDadosOperacao().getEndereoFilialLosango().setBairro("CENTRO");
		
		//3176 a 3210	Cidade da Filial Losango	0035	A	Usado no RPS
		esperado.getDadosOperacao().getEndereoFilialLosango().setCidade("UBERLANDIA");
		
		//3211 a 3212	UF da Filial Losango	0002	A	Usado no RPS
		esperado.getDadosOperacao().getEndereoFilialLosango().setUf("MG");
		
		//3213 a 3220	Cep da Filial Losango	0008	A	Usado no RPS
		esperado.getDadosOperacao().getEndereoFilialLosango().setCep(38400000);
		
		//3221 a 3221	Pre-Pago	1	A	Indica a compra de telefone celular Pre-Pago: 0 -Default ; 1 - Pre	 '0' '1'
		esperado.getDadosOperacao().setPrePago("0");
		
		//3222 a 3222	Leva na Hora	1	A	 Indica se o cliente levara a mercadoria na hora:   0 - Default ; 1 - leva	 '0' '1'
		esperado.getDadosOperacao().setLevaNaHora(true);
		
		//3223 a 3223	Beta-Gama	1	A	"Indicadore de Fraude
		//  0 - Default - Sem fraude
		//  2 - Beta    - Susp fraude
		//  1 - Gama    - Confirm fraude"	 '0' '1' '2'
		esperado.getDadosOperacao().setBetaGama(0);
		
		//3224 a 3233	Promotor	10	N
		esperado.getDadosOperacao().setPromotor(0);
		
		//3234 a 3234	Indicador aceita consulta ao sysbacen	1	A		 '0' '1'
		esperado.getDadosOperacao().setAceitaConsultaSysBacen(false);
		
		//3235 a 3242	Data do evento (entrega do bem)	8	N	Data do Bem
		esperado.getDadosOperacao().setDataEvento(null);
		
		//3243 a 3257	Valor da Entrada Lojista	15	N
		esperado.getDadosOperacao().setValorEntradaLojista(0);
		
		//3258 a 3258	Quantidade máxima de reanálise	1	N
		esperado.getDadosOperacao().setQuantidadeMaxReanalise(0);
		
		//3259 a 3259	Produto Cessão	1	N	0 - Não, 1 - Sim
		esperado.getDadosOperacao().setProdutoCessao(false);
		
		//3260 a 3309	Filler	50	A		
		esperado.getDadosOperacao().setFiller(AdaptadorTipo.escreverString(50, " "));
		
		//Dados do Perfil/Oferta
		//3310 a 3389	Filler	80	A
		esperado.setFillerPerfilOferta(AdaptadorTipo.escreverString(80, " "));
		
		
		//Dados Captura Digital
		//3390 a 3390	indicadorCapturarFoto	1	A	Indicador da captura da Foto do Cliente.	"1 - Problemas Técnicos
		//2 - Cliente não autoriza
		//3 - Outros"
		esperado.setIndicadorCapturarFoto("");
		
		//3390 a 3391	indicadorCapturarDocumento	1	A	Indicador da captura do Documento.	"1 - Problemas Técnicos
		//2 - Cliente não autoriza
		//3 - Outros"
		esperado.setIndicadorCapturarDocumento("");
		
		//3391 a 3392	indicadorCapturarBiometria	1	A	Indicador da captura da Biometria	"1 - Problemas Técnicos
		//2 - Cliente não autoriza
		//3 - Outros"
		esperado.setIndicadorCapturarBiometria("");
		
		//3393 a 3409	Filler	17	A		
		esperado.setFillerCapturaDigital(AdaptadorTipo.escreverString(17, " "));
		
		
		//Dados de Seguro
		//3410 a 3410	Adesao_Seguro	1	N	Flag que indica se o cliente optou por fazer seguro (0 - NÃO  ;  1 - SIM)	“0”   “1”
		esperado.setAdesaoSeguro(false);
		
		//3411 a 3411	Forma pagamento acessório	1	A	Forma de pagamento do produto acessório (V – a vista ; F – Financiado)	“V”  “F”
		esperado.setFormaPagamentoAcessorio("");
		
		//3412 a 3412	Quantidade_Seguro	1	N	Quantidade de Seguros na proposta	
		esperado.setQuantidadeSeguro(0);
		
		
		//Dados de Seguro Prestamista
		esperado.setDadosSeguroPremista(new RespostaPropostaFinanciamento.DadoSeguroPremista());
		//3413 a 3414	tipo do seguro	2	A	Tipo de produto acessório	
		esperado.getDadosSeguroPremista().setTipoSeguro("");
		
		//3415 a 3418	código do seguro	4	A	Codigo do produto acessório
		esperado.getDadosSeguroPremista().setCodigoSeguro("");
		
		//3419 a 3433	valor do seguro	15	N	Valor do produto acessório (em R$)
		esperado.getDadosSeguroPremista().setValorSeguro(0);
		
		//3434 a 3441	Valor da Cobertura do seguro	8	N
		esperado.getDadosSeguroPremista().setValorCoberturaSeguro(0);
		
		//3442 a 3443	Plano Maximo do Seguro	2	N
		esperado.getDadosSeguroPremista().setPlanoMaximoSeguro(0);
		
		//3444 a 3451	Codigo do produto do Seguro	8	N		
		esperado.getDadosSeguroPremista().setCodigoProdutoSeguro(0);
		
		
		//Dados de Seguro da Sorte/Vida
		esperado.setDadoSeguroSorteVida(new RespostaPropostaFinanciamento.DadoSeguroSorteVida());
		
		//3452 a 3453	tipo do seguro	2	A	Tipo de produto acessório
		esperado.getDadoSeguroSorteVida().setTipoSeguro("");
		
		//3454 a 3457	código do seguro	4	A	Codigo do produto acessório
		esperado.getDadoSeguroSorteVida().setCodigoSeguro("");
		
		//3458 a 3472	valor do seguro	15	N	Valor do produto acessório (em R$)
		esperado.getDadoSeguroSorteVida().setValorSeguro(0);
		
		//3473 a 3480	Valor da Cobertura do seguro	8	N
		esperado.getDadoSeguroSorteVida().setValorCobertura(0);
		
		//3481 a 3482	Plano Maximo do Seguro	2	N
		esperado.getDadoSeguroSorteVida().setPlanoMaximo(0);
		
		//3483 a 3490	Codigo do produto do Seguro	8	A
		esperado.getDadoSeguroSorteVida().setCodigoProduto(0);
		
		//3491 a 3498	Data do Sorteio	8	N	data do Sorteio do Numero da Sorte
		esperado.getDadoSeguroSorteVida().setDateSorteio(null);
		
		//3499 a 3500	Quantidade de Numero da Sorte	2	N	Valor do produto acessório (em R$)
		esperado.getDadoSeguroSorteVida().setQuantidadeNumeroSorte(0);
		esperado.getDadoSeguroSorteVida().setNumerosSorte(new LinkedList<NumeroSorte>());
		
		//3501 a 3505	Série do Número da Sorte 1	5	N		
		//3506 a 3512	Número da Sorte 1	7	N		
		//3513 a 3517	Série do Número da Sorte 2	5	N		
		//3518 a 3524	Número da Sorte2	7	N		
		//3525 a 3529	Série do Número da Sorte 3	5	N		
		//3530 a 3536	Número da Sorte3	7	N		
		//3537 a 3541	Série do Número da Sorte 4	5	N		
		//3542 a 3548	Número da Sorte4	7	N		
		//3549 a 3553	Série do Número da Sorte 5	5	N		
		//3554 a 3560	Número da Sorte5	7	N		
		//3561 a 3565	Série do Número da Sorte 6	5	N		
		//3566 a 3572	Número da Sorte6	7	N		
		//3573 a 3577	Série do Número da Sorte 7	5	N		
		//3578 a 3584	Número da Sorte7	7	N		
		//3585 a 3589	Série do Número da Sorte 8	5	N		
		//3590 a 3596	Número da Sorte8	7	N
		
		
		//Dados do SUSEP para Seguro Prestamista
		esperado.setSusepSeguroPremista(new RespostaPropostaFinanciamento.SusepSeguroPremista());
		//3597 a 3604	Data do Sorteio	8	N	
		esperado.getSusepSeguroPremista().setDataSorteio(null);
		
		//3605 a 3617	Bilhete Seguro Prestamista	13	N
		esperado.getSusepSeguroPremista().setBilhete(0);
		
		//3618 a 3622	Série do Número da Sorte 1	5	N
		esperado.getSusepSeguroPremista().setSerieNumeroSorte(0);
		
		//3623 a 3629	Número da Sorte 1	7	N	
		esperado.getSusepSeguroPremista().setNumeroDaSorte(0);
		
		esperado.getSusepSeguroPremista().setPercentualGarantia(new LinkedList<Integer>());
		//3630 a 3634	Percentual Garantia 1	5	N	
		//3635 a 3639	Percentual Garantia 2	5	N	
		//3640 a 3644	Percentual Garantia 3	5	N	
		//3645 a 3649	Percentual Garantia 4	5	N	
		//3650 a 3654	Percentual Garantia 5	5	N	
		//3655 a 3659	Percentual Garantia 6	5	N	
		//3660 a 3664	Percentual Garantia 7	5	N	
		
		//3665 a 3670	Filler	6	A
		esperado.getSusepSeguroPremista().setFiller(AdaptadorTipo.escreverString(6, " "));
		
		
		//DÉBITO EM CONTA
		esperado.setDebitoConta(new Banco());
		esperado.getDebitoConta().setInfo(new InfoBanco());
		//3671 a 3674	Banco	4	N
		esperado.getDebitoConta().getInfo().setNumero(0);
		
		//3675 a 3678	Agencia	4	N
		esperado.getDebitoConta().getInfo().setAgencia(0);
		
		//3679 a 3679	DV Agência	1	A	
		esperado.getDebitoConta().getInfo().setDvAgencia("");
		
		//3680 a 3692	Conta Corrente	13	N	
		esperado.getDebitoConta().getInfo().setContaCorrente(0);
		
		//3693 a 3694	DV Conta Corrente	2	A
		esperado.getDebitoConta().getInfo().setDvContaCorrente("");
		
		//3695 a 3696	Tipo da Conta	2	A
		esperado.getDebitoConta().setTipoConta("");
		
		//3697 a 3704	Data de Abertura	8	N	DDMMAAAA
		esperado.getDebitoConta().setDataAbertura(null);

		//Dados de Cheques
		esperado.setDadosCheque(new RespostaPropostaFinanciamento.DadoCheque());
		//3705 a 3707	CÓDIGO DO BANCO dos cheques	3	N	Código do Banco da primeira faixa de cheques para as operações de cheque-pré	Se produto igual a "2"
		esperado.getDadosCheque().setCodigoBanco(0);
		
		//3708 a 3711	AGÊNCIA  DE DESTINO dos cheques	4	N	Código da Agência Bancária da primeira faixa de cheques para as operações de cheque-pré	Se produto igual a "2"
		esperado.getDadosCheque().setAgenciaDestino(0);
		
		//3712 a 3712	DV Agencia dos cheques	1	A	
		esperado.getDadosCheque().setDvAgenciaDestino("");
		
		//3713 a 3725	Codigo da Conta	13	N		
		esperado.getDadosCheque().setCodigoConta(0);
		
		//3726 a 3727	DV da Conta	2	A
		esperado.getDadosCheque().setDvConta("");
		
		//3728 a 3733	NÚMERO DO CHEQUE do Primeiro Cheque da 1a. Faixa de Cheques	6	N	Número do primeiro cheque da primeira faixa de cheques para as operações de cheque-pré	Se produto igual a "2"
		esperado.getDadosCheque().setNumeroPrimeiroChequeFaixa1(0);
		
		//3734 a 3739	NÚMERO DO CHEQUE do Último Cheque da 1a. Faixa de Cheques	6	N	Número do último cheque da primeira faixa de cheques para as operações de cheque-pré	Se produto igual a "2"
		esperado.getDadosCheque().setNumeroUltimoChequeFaixa1(0);
		
		//3740 a 3745	NÚMERO DO CHEQUE do Primeiro Cheque da 2a. Faixa de Cheques	6	N	Número do primeiro cheque da segunda faixa de cheques para as operações de cheque-pré
		esperado.getDadosCheque().setNumeroPrimeiroChequeFaixa2(0);
		
		//3746 a 3751	NÚMERO DO CHEQUE do Último Cheque da 2a. Faixa de Cheques	6	N	Número do último cheque da segunda faixa de cheques para as operações de cheque-pré
		esperado.getDadosCheque().setNumeroUltimoChequeFaixa2(0);
		
		//3752 a 3759	Data da abertura da conta corrente	8	N	Data da abertura da conta corrente	Se produto igual a "2"
		esperado.getDadosCheque().setDataAberturaConta(null);
		
		//3760 a 3809	Filler	50	A
		esperado.getDadosCheque().setFiller(AdaptadorTipo.escreverString(50, " "));

		
		//Dados de operação para Emprestimo Pessoal (EP)
		esperado.setBancoEp(new BancoEp());
		esperado.getBancoEp().setInfo(new InfoBanco());
		//3810 a 3810	Ind. Pagto DOC - Credito Pessoal	1	A	Indicador de pagamento em DOC
		esperado.getBancoEp().setPagtoDoc(false);
		
		//3811 a 3812	Tipo de Conta (spb) - EP	2	A	ver tabela de dominio
		esperado.getBancoEp().setTipoConta("");
		
		//3813 a 3816	Banco - Credito Pessoal	4	N	Número do Banco para Depósito do Credito pessoal
		esperado.getBancoEp().getInfo().setNumero(0);
		
		//3817 a 3820	Agencia - Credito Pessoal	4	N	Número da Agencia para Depósito do Credito pessoal
		esperado.getBancoEp().getInfo().setAgencia(0);
		
		//3821 a 3821	DV Agencia  - Credito Pessoal	1	A	DV da Agencia para Depósito do Emprestimo pessoal
		esperado.getBancoEp().getInfo().setDvAgencia("");
		
		//3822 a 3834	Nº da conta - Credito Pessoal	13	N	Número da Agencia para Depósito do crédito pessoal
		esperado.getBancoEp().getInfo().setContaCorrente(0);
		
		//3835 a 3836	Dv da conta  - Credito Pessoal	2	A	Número da Conta para débito referente ao pagamento das prestações
		esperado.getBancoEp().getInfo().setDvContaCorrente("");
		
		//3837 a 3837	C2 - Credito Pessoal	1	N
		esperado.getBancoEp().setC2(0);
		
		//3838 a 3838	C3 - Credito Pessoal	1	N
		esperado.getBancoEp().setC3(0);
		
		
		//Outros Indicadores
		//3839 a 3888	Filler	50	A
		esperado.setFillerOutrosIndicadores(AdaptadorTipo.escreverString(50, " "));
		
		//Prestações
		esperado.setPrestacoes(new LinkedList<Prestacao>());
		//3889 a 3896	Data de Vencimento da 1ª prestação	8	N	Data do Vencimento de cada prestação
		//3897 a 3908	NOSNUMERO da 1ª prestação	12	A	Nosso Numero (Losango)
		//3909 a 3928	Prestação 2	20	A	Data do Vencimento + Nosso Numero (Losango)
		//3929 a 3948	Prestação 3	20	A	Data do Vencimento + Nosso Numero (Losango)
		//4769 a 4788	Prestação 45	20	A	Data do Vencimento + Nosso Numero (Losango)
		esperado.getPrestacoes().add(addPrestacao("25092015861026964730"));
		esperado.getPrestacoes().add(addPrestacao("25102015861026964749"));
		esperado.getPrestacoes().add(addPrestacao("25112015861026964757"));
		esperado.getPrestacoes().add(addPrestacao("25122015861026964765"));
		esperado.getPrestacoes().add(addPrestacao("25012016861026964773"));
		esperado.getPrestacoes().add(addPrestacao("25022016861026964781"));
		esperado.getPrestacoes().add(addPrestacao("2503201686102696479P"));
		esperado.getPrestacoes().add(addPrestacao("25042016861026964803"));
		esperado.getPrestacoes().add(addPrestacao("25052016861026964811"));
		esperado.getPrestacoes().add(addPrestacao("2506201686102696482P"));
		
		//4789 a 4842	Filler	54	A
		esperado.setFillerPrestacoes(AdaptadorTipo.escreverString(54, " "));
		
		
		//Dados CD					
		//4843 a 4877	Registro Oficio Contrato	35	A	Numero do Contrado Registrado em Cartório
		esperado.setRegistroOficioContrato("4o Of. RTD-RJ, n. 951628/19-12-13");
		
		//4878 a 4927	Filler	50	A
		esperado.setFillerDadosCd(AdaptadorTipo.escreverString(50, " "));
		
		
		//Atendimento ao Cliente					
		//4928 a 4938	CPF do Vendedor	11	A
		esperado.setCpfVendedor("01234567890");
		
		//4939 a 4973	Nome do Vendedor	35	A
		esperado.setNomeVendedor("VENDEDOR TESTE");
		
		//4974 a 5008	Nome do Certificado	35	A
		esperado.setNomeCertificador("JUCIELY FRANSIANE AMARIO");
		
		//5009 a 5019	CPF do Certificado	11	A
		esperado.setCpfCertificador("01115303180");
		
		//5020 a 5088	Filler	69	A
		esperado.setFillerAtendimentoCliente(AdaptadorTipo.escreverString(69, " "));		
		
		//Outros Indicadores
		esperado.setIndicadores(new Indicador());
		//5089 a 5089	Identificador do canal	1	A	Identifica que a proposta é de procedência do TRS
		esperado.getIndicadores().setIdentificadorCanal("");
		
		//5090 a 5099	Versão do Canal	10	A	Uso exclusivo da Losango
		esperado.getIndicadores().setVersaoCanal("7");
		
		//5100 a 5100	Política	1	A	Uso exclusivo da Losango
		esperado.getIndicadores().setPolitica("2");
		
		//5101 a 5102	Ambiente	2	A	Uso exclusivo da Losango
		esperado.getIndicadores().setAmbiente("HO");
		return esperado;
	}
	
	private static Prestacao addPrestacao(String prestacao) throws ParseException {
		
		Prestacao p = new Prestacao();
		p.setNossoNumero(prestacao.substring(8));
		p.setVencimento(UtilsDate.parse(prestacao.substring(0,8), UtilsDate.FormatadorData.DATA));
		return p;
	}
	
	public static ConsultaProposta criarConsultaProposta(String mensagem) throws MensagemNaoEncontradaException, ParseException {
		
		Cabecalho expected = new Cabecalho();
		expected.setSentidoFluxo(Fluxo.ENTRADA);
		expected.setTamanho(14);
		expected.setCodigo(CodigoMensagem.C0200);
		expected.setNumeroTransacao(999004);
		expected.setNumeroProposta("P4201348790");
		expected.setCodigoUsuario("UILSON");
		expected.setCodigoRetorno("");
		expected.setCodigoLojista(170894001);
		expected.setVersao("9");
		expected.setCampoLojista(AdaptadorTipo.escreverString(30, " "));
		
		ConsultaProposta esperado = new ConsultaProposta(ContextoMensagem.md5(mensagem), expected);

		//validação outros indicadores
		esperado.setIndicadores(new Indicador());
		esperado.getIndicadores().setIdentificadorCanal("T");
		esperado.getIndicadores().setVersaoCanal("");
		esperado.getIndicadores().setPolitica("");
		esperado.getIndicadores().setAmbiente("");
		
		return esperado;
	}
	
	
	
	
	@Test
	public void criarConsultaProposta() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException, MapeamentoNaoEncontrado{
	
		String mensagem = MensagemHelper.lerMensagem(97, CodigoMensagem.C0200.toInt(), "criarConsultaProposta.1");
		
		ConsultaProposta m = (ConsultaProposta) MensagemFactory.parse(mensagem);
		ConsultaProposta esperado = criarConsultaProposta(mensagem);
		
		assertThat(m, BeanMatchers.theSameAs(esperado));
	}
	
	
	public static RespostaConsultaProposta criarRespostaConsultaProposta(String mensagem) throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		return criarRespostaPropostaMensagem(mensagem, RespostaConsultaProposta.class);
	}
	
	@Test
	public void criarRespostaConsultaProposta() throws IOException, URISyntaxException, MensagemNaoEncontradaException, ParseException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		String mensagem = MensagemHelper.lerMensagem(5102, CodigoMensagem.C0210.toInt(), "criarRespostaConsultaProposta.1");
		
		RespostaConsultaProposta m = (RespostaConsultaProposta) MensagemFactory.parse(mensagem);
		RespostaConsultaProposta esperado = criarRespostaConsultaProposta(mensagem);
		
		assertThat(m, BeanMatchers.theSameAs(esperado));
	}
}
