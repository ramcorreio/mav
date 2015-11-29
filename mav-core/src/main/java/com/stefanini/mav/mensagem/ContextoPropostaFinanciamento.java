package com.stefanini.mav.mensagem;

import java.text.ParseException;
import java.util.LinkedList;

import com.stefanini.mav.mensagem.Cabecalho.Fluxo;

public class ContextoPropostaFinanciamento extends ContextoMensagem<PropostaFinanciamento> {

	public ContextoPropostaFinanciamento() {
		super(CodigoMensagem.C0100, PropostaFinanciamento.class);
	}
	
	private void lerDadosClientes(String input, PropostaFinanciamento m) throws ParseException {
		
		/*private DadoComplementar complemento;
		
		private Boolean cobraTac;
		
		private Boolean elegibilidadeSeguro;
		
		private String codigoProdutoLosango;
		
		private Integer qtdNumeroSorte;*/
		
		
		m.setDadosPessoais(new DadoPessoalDetalhado());
		//0084 a 0084	Tipo de personalidade do CPF	1	A	T = Tipo de Pessoa (F – Fisica).	“F”	X
		m.getDadosPessoais().setTipoPersonalidade(lerString(input, 83, 1));
		
		//0085 a 0098	CPF 	14	N	CPF / CGC do cliente  		X
		m.getDadosPessoais().setCpf(lerString(input, 84, 11));
		
		//0099 a 0099	Usuário do CPF	1	A	Tipo de CPF ( T - Titular  ;   D  -  Dependente  )	“T”   “D”	X
		m.getDadosPessoais().setUsuarioCpf(lerString(input, 98, 1));
		
		//0100 a 0100	Correspondência	1	N	1 – Residencial      2 - comercial (Identificação da Localização do recebimento do documento	“1”  “ 2”	X
		m.getDadosPessoais().setUsuarioCpf(lerString(input, 98, 1));
		
		
		m.getDadosPessoais().setDocumentoIdentificacao(new Documento());
		//0101 a 0115	Numero do Documento de identificação do Cliente	15	A	Numero do documento de identificação do cliente		X
		m.getDadosPessoais().getDocumentoIdentificacao().setNuDocIdentificacao(lerString(input, 100, 15));
		
		//0116 a 0117	Tipo de Documento	2	A	Tipo do Documento do Cliente	Ver tabela de dominio TP  DOCUMENTO IDENTIDADE	X
		m.getDadosPessoais().getDocumentoIdentificacao().setTpDocIdentificacao(lerString(input, 115, 2));
		
		//0118 a 0122	Órgão Emissor	5	A	Órgão emissor do documento de identidade do cliente	Ver tabela de dominio Orgão Emissor	X
		m.getDadosPessoais().getDocumentoIdentificacao().setOrgaoEmissor(lerString(input, 117, 5));
		
		//0123 a 0124	UF Órgão Emissor	2	A		Ver tabela de dominio UF	X
		m.getDadosPessoais().getDocumentoIdentificacao().setUfOrgaoEmissor(lerString(input, 122, 2));
		
		//0125 a 0132	Data_Emissão	8	N	Data de emissão do documento de identidade do cliente		X
		m.getDadosPessoais().getDocumentoIdentificacao().setDataEmissao(lerData(input, 124));
		
		//0133 a 0133	Conjuge Compõe Renda	1	N	 0 - Não     1 - Sim   (Flag que indica que conjuge compõe renda)	“0”    “1”	X
		m.getDadosPessoais().setConjugeCompoeRenda(lerBoolean(input, 132));
		
		//0134 a 0163	Nome	30	A	Nome do cliente		X
		m.getDadosPessoais().setNome(lerString(input, 133, 30));
		
		//0164 a 0203	Local de Nascimento	40	A	Informar o Municipio de Nascimento do Cliente	Tabela de Dominio Local de Nascimento	X, se a nacionalidade igual a Brasileiro
		m.getDadosPessoais().setLocalNascimento(lerString(input, 163, 40));
		
		//0204 a 0211	Data_Nascimento	8	N	Data de nascimento do cliente		X
		m.getDadosPessoais().setDataNascimento(lerData(input, 203));
		
		//0212 a 0212	Sexo	1	A	M – Masculino     F – Feminino         	“M”   ”F”	X
		m.getDadosPessoais().setSexo(lerString(input, 211, 1));
		
		//0213 a 0213	Nacionalidade	1	N	"Nacionalidade do Cliente
		//0-Brasileiro
		//1-Estrangeiro        "	“0”   “1”	X
		m.getDadosPessoais().setNacionalidade(lerString(input, 212, 1));
		
		//0214 a 0228	Naturalidade	15	A	Naturalidade do cliente
		m.getDadosPessoais().setNaturalidade(lerString(input, 213, 14));
		
		//0229 a 0258	Nome da Mãe	30	A	Nome da Mãe do Cliente 		X
		m.getDadosPessoais().setNomeMae(lerString(input, 228, 30));
		
		//0259 a 0288	Nome do Pai	30	A	Nome do Pai do Cliente 		
		m.getDadosPessoais().setNomePai(lerString(input, 258, 30));
		
		//0289 a 0293	Carteira Profissional	5	N	Número da Carteira Profissional do Cliente (PC)
		m.getDadosPessoais().setCarteiraProfissional(lerInt(input, 288, 5));
		
		//0294 a 0298	Série	5	A	Número de Série da Carteira Profissional do cliente (PC)
		m.getDadosPessoais().setSerieCarteiraProfissional(lerString(input, 293, 5));
		
		//0299 a 0299	Estado Civil	1	N	Informar o Estado Civil	Ver Tabela Dominio Estado Civil	X
		m.getDadosPessoais().setEstadoCivil(lerInt(input, 298, 1));
		
		m.getDadosPessoais().setEndereco(new Endereco());
		//0300 a 0339	Logradouro	40	A	Logradouro da residência do cliente		X
		m.getDadosPessoais().getEndereco().setLogradouro(lerString(input, 299, 30));
		
		//0340 a 0344	Numero	5	A	Numero do logradouro		X
		m.getDadosPessoais().getEndereco().setNumero(lerString(input, 339, 5));
		
		//0345 a 0359	Complemento	15	A	Complemento do logradouro
		m.getDadosPessoais().getEndereco().setComplemento(lerString(input, 344, 15));
		
		//0360 a 0379	Bairro	20	A	Bairro endereço residencial		X
		m.getDadosPessoais().getEndereco().setBairro(lerString(input, 359, 20));
		
		//0380 a 0399	Cidade	20	A	Cidade endereço residencial		X
		m.getDadosPessoais().getEndereco().setCidade(lerString(input, 379, 20));
		
		//0400 a 0401	UF	2	A	Abreviação do Estado onde o cliente reside		X
		m.getDadosPessoais().getEndereco().setUf(lerString(input, 399, 2));
		
		//0402 a 0409	CEP	8	N	CEP endereço residencial		X
		m.getDadosPessoais().getEndereco().setCep(lerInt(input, 401, 8));
		
		m.getDadosPessoais().setTelefone(new Telefone());
		//0410 a 0412	DDD	3	N	DDD telefone residencial		X
		m.getDadosPessoais().getTelefone().setDdd(lerInt(input, 409, 3));
		
		//0413 a 0421	Telefone	9	N	"Se o campo DDD estiver preenchido com 011 e o numero do telefone não iniciar 70, 75, 78 e 79 e for um numero de celular, o telefone deve ser iniciado com o numero ""9"", caso contrário deverá ser iniciado com o numero ""0"".
		m.getDadosPessoais().getTelefone().setNumero(lerInt(input, 412, 9));
		
		//0422 a 0425	Ramal	4	N	Ramal do telefone residencial do cliente
		m.getDadosPessoais().getTelefone().setRamal(lerInt(input, 421, 4));
		
		//0426 a 0426	Tipo Telefone	1	N	Informar o Tipo de telefone	Ver tabela de dominio Tipo de Telefone	X
		m.getDadosPessoais().setTipoTelefone(lerInt(input, 425, 1));
		
		//0427 a 0427	Tipo Residencia	1	N	Informar o Tipo de Residencia	Ver tabela de dominio Tip de Residencia	X
		m.getDadosPessoais().setTipoResidencia(lerInt(input, 426, 1));
		
		//0428 a 0433	Reside desde	6	N	MMAAAA
		m.getDadosPessoais().setResideDesde(lerDataCurta(input, 427));
		
		m.getDadosPessoais().setCelular(new Telefone());
		//0434 a 0436	DDD Celular	3	N			X.
		m.getDadosPessoais().getCelular().setDdd(lerInt(input, 433, 3));
		
		//0437 a 0445	Telefone Celular	9	N	"Se o campo DDD estiver preenchido com 011 e o numero do telefone não iniciar 70, 75, 78 e 79 e for um numero de celular, o telefone deve ser iniciado com o numero ""9"", caso contrário deverá ser iniciado com o numero ""0"".
		m.getDadosPessoais().getCelular().setNumero(lerInt(input, 436, 9));
		
		//0446 a 0505	Email	60	A
		m.getDadosPessoais().setEmail(lerString(input, 445, 60));
		
		//0506 a 0506	Cliente Possui Patrimonio?	1	N	Campo que o Clinte indica se possui Patrimonios, caso a opção escolhida seja “Sim”, deverão ser informados pelo menos um Patrimonio, composto por : “Tipo de Patrimônio”, “Patrimônio” e “Valor do Patrimônio”.	 0 - Não     1 - Sim
		m.getDadosPessoais().setPossuiPatrimonio(lerBoolean(input, 505));
		
		m.getDadosPessoais().setPatrimonio(new LinkedList<Patrimonio>());
		if(m.getDadosPessoais().isPossuiPatrimonio()) {
			
			//0507 a 0507	Tipo de Patrimonio 1	1	A	Deverá ser informado o Tipo de Patrimônio	"“1” - “Bens Móveis” e/ou
			//“2” - “Bens Imóveis”"	Enviar em branco se o "cliente possue patrimonio = 0"
			//0508 a 0512	Patrimonio 1	5	A	Dever ser informado a característica do patrimônio. 	Tabela de Dominio Patrimonio	Enviar em branco se o "cliente possue patrimonio = 0"
			//0513 a 0523	Valor do Patrimonio 1	11	N	Dever informado o valor do Bem	9 inteiros e 2 decimais	Enviar em zerado se o "cliente possue patrimonio = 0"
			m.getDadosPessoais().getPatrimonio().add(new Patrimonio());
			m.getDadosPessoais().getPatrimonio().get(0).setTipo(lerString(input, 506, 1));
			m.getDadosPessoais().getPatrimonio().get(0).setNome(lerString(input, 507, 5));
			m.getDadosPessoais().getPatrimonio().get(0).setValor(lerDouble(input, 512, 11));
			
			//0524 a 0524	Tipo de Patrimonio 2	1	A	Deverá ser informado o Tipo de Patrimônio	"“1” - “Bens Móveis” e/ou “2” - “Bens Imóveis”"	Enviar em branco se o "cliente possue patrimonio = 0"
			//0525 a 0529	Patrimonio 2	5	A	Dever ser informado a característica do patrimônio. 	Tabela de Dominio Patrimonio	Enviar em branco se o "cliente possue patrimonio = 0"
			//0530 a 0540	Valor do Patrimonio 2	11	N	Dever informado o valor do Bem	9 inteiros e 2 decimais	Enviar em zerado se o "cliente possue patrimonio = 0"
			m.getDadosPessoais().getPatrimonio().add(new Patrimonio());
			m.getDadosPessoais().getPatrimonio().get(1).setTipo(lerString(input, 523, 1));
			m.getDadosPessoais().getPatrimonio().get(1).setNome(lerString(input, 524, 5));
			m.getDadosPessoais().getPatrimonio().get(1).setValor(lerDouble(input, 529, 11));
			
			//0541 a 0541	Tipo de Patrimonio 3	1	A	Deverá ser informado o Tipo de Patrimônio	"“1” - “Bens Móveis” e/ou “2” - “Bens Imóveis”"	Enviar em branco se o "cliente possue patrimonio = 0"
			//0542 a 0546	Patrimonio 3	5	A	Dever ser informado a característica do patrimônio. 	Tabela de Dominio Patrimonio	Enviar em branco se o "cliente possue patrimonio = 0"
			//0547 a 0557	Valor do Patrimonio 3	11	N	Dever informado o valor do Bem	9 inteiros e 2 decimais	Enviar em zerado se o "cliente possue patrimonio = 0"
			m.getDadosPessoais().getPatrimonio().add(new Patrimonio());
			m.getDadosPessoais().getPatrimonio().get(2).setTipo(lerString(input, 540, 1));
			m.getDadosPessoais().getPatrimonio().get(2).setNome(lerString(input, 541, 5));
			m.getDadosPessoais().getPatrimonio().get(2).setValor(lerDouble(input, 546, 11));
			
			//0558 a 0558	Tipo de Patrimonio 4	1	A	Deverá ser informado o Tipo de Patrimônio	"“1” - “Bens Móveis” e/ou “2” - “Bens Imóveis”"	Enviar em branco se o "cliente possue patrimonio = 0"
			//0559 a 0563	Patrimonio 4	5	A	Dever ser informado a característica do patrimônio. 	Tabela de Dominio Patrimonio	Enviar em branco se o "cliente possue patrimonio = 0"
			//0564 a 0574	Valor do Patrimonio 4	11	N	Dever informado o valor do Bem	9 inteiros e 2 decimais	Enviar em zerado se o "cliente possue patrimonio = 0"
			m.getDadosPessoais().getPatrimonio().add(new Patrimonio());
			m.getDadosPessoais().getPatrimonio().get(3).setTipo(lerString(input, 557, 1));
			m.getDadosPessoais().getPatrimonio().get(3).setNome(lerString(input, 558, 5));
			m.getDadosPessoais().getPatrimonio().get(3).setValor(lerDouble(input, 563, 11));
		}
		
		//0575 a 0575	Filler	1	A
		m.getDadosPessoais().setFiller(lerString(input, 574, 1));
		
		//0576 a 0577	Código do País (informar se nacionalidade = estrangeiro)	2	A	Tabela de Paises	Ver tabela de Dominio Paises	X. Se Nacionalidade = Estrangeiro
		m.getDadosPessoais().setCodigoPais(lerString(input, 575, 2));
		
		//0578 a 0579	Código da UF da Naturalidade (informar se nacionalidade = brasileiro)	2	A	Tabela de UF	Ver tabela de dominio UF	X. Se Nacionalidade = Brasileiro
		m.getDadosPessoais().setUFNaturalidade(lerString(input, 577, 2));
		
		//0580 a 0587	Data de Vencimento do Documento de identificação	8	A			X, se o tipo do documento for: 02, 03 e 09
		try {
			m.getDadosPessoais().getDocumentoIdentificacao().setDataVancimento(lerData(input, 579));	
		}
		catch(ParseException e) {
			m.getDadosPessoais().getDocumentoIdentificacao().setDataVancimento(null);
		}
		
		//0588 a 0588	Flag Emancipado	1	A		0 - Nao 1 - Sim	X
		m.getDadosPessoais().setEmancipado(lerBoolean(input, 587));
		
		if(m.getDadosPessoais().isPossuiPatrimonio()) {
			
			//0589 a 0589	Origem do patrimonio 1	1	A
			m.getDadosPessoais().getPatrimonio().get(0).setOrigem(lerString(input, 588, 1));
			
			//0590 a 0590	Origem do patrimonio 2	1	A
			m.getDadosPessoais().getPatrimonio().get(1).setOrigem(lerString(input, 589, 1));
			
			//0591 a 0591	Origem do patrimonio 3	1	A
			m.getDadosPessoais().getPatrimonio().get(2).setOrigem(lerString(input, 590, 1));
			
			//0592 a 0592	Origem do patrimonio 4	1	A
			m.getDadosPessoais().getPatrimonio().get(3).setOrigem(lerString(input, 591, 1));
		}
		
		//0593 a 0625	Filler	33	A
		m.getDadosPessoais().setFiller2(lerString(input, 592, 33));

	}

	@Override
	void ler(String input, PropostaFinanciamento mensagem) throws MensagemNaoEncontradaException {

		mensagem.getCabecalho().setSentidoFluxo(Fluxo.ENTRADA);
		
		try {
			//dados pessoais
			lerDadosClientes(input, mensagem);
			
			//dados profissionais
			lerDadosProfissionais(input, mensagem);
			
			//dados do cônjuge
			lerDadosConjuge(input, mensagem);
			
			//DADOS COMPLEMENTARES
			//1365 a 1366	Escolaridade	2	A	Codigo da Escolaridade	Ver tabela de dominio Escolaridade
			mensagem.setEscolaridade(lerInt(input, 1364, 2));
			
			//1367 a 1386	Formação	20	A	Formação
			mensagem.setFormacao(lerString(input, 1366, 20));
			
			//1387 a 1387	Indicador Possui cartão	1	N	Indicador se possui cartão	0 - Não 1 - Sim	X
			mensagem.setPossuiCartao(lerBoolean(input, 1386));
			
			//1388 a 1388	Indicador Possui veículo próprio	1	N	Indicador Possui veículo próprio	0 - Não 1 - Sim	X
			mensagem.setPossuiVeiculoProprio(lerBoolean(input, 1387));
			
			//1389 a 1398	Placa	10	A
			mensagem.setPlaca(lerString(input, 1388, 10));
			
			//1399 a 1458	Renavam	60	A
			mensagem.setRenavam(lerString(input, 1398, 60));
			
			//1459 a 1459	Indicador Possui veículo quitado	1	N	Indicador Possui veículo quitado	0 - Não 1 - Sim	X
			mensagem.setVeiculoQuitado(lerBoolean(input, 1458));
			

			//1460 a 1460	Possui experiencia de crédito	1	N	Indicador Possui experiência	0 - Não 1 - Sim	X
			mensagem.setPossuiExperienciaCredito(lerBoolean(input, 1459));

			if(mensagem.isPossuiExperienciaCredito()) {
				
				//1461 a 1480	Local da Experiência	20	A			X. Se Possui experiencia de crédito = 1
				mensagem.setLocalExperienciaCredito(lerString(input, 1460, 20));
				
				//1481 a 1482	Plano da Experiência	2	N			X. Se Possui experiencia de crédito = 1
				mensagem.setPlanoExperienciaCredito(lerInt(input, 1480, 2));
				
				//1483 a 1497	Valor da Prestação da Experiência	15	N			X. Se Possui experiencia de crédito = 1
				mensagem.setValorPrestacaoExperienciaCredito(lerInt(input, 1482, 15));
				
				//1498 a 1503	Inicio da Experiência de Crédito	6	N	Inicio da Experiência MMAAAA		X. Se Possui experiencia de crédito = 1
				mensagem.setInicioExperienciaCredito(lerDataCurta(input, 1497));
			}
			
			//1504 a 1543	Classificação do Cliente	40	A
			mensagem.setClassificacaoCliente(lerString(input, 1503, 40));
			
			//1544 a 1544	Indicador Possui Cartão Financeira	1	N	Indicador Possui Cartão Financeira	0 - Não 1 - Sim	X
			mensagem.setPossuiCartaoFinanceira(lerBoolean(input, 1543));
			
			//1545 a 1545	Indicador Possui Conta Corrente	1	N	Indicador Possui Conta Corrente	0 - Não 1 - Sim	X
			mensagem.setPossuiContaCorrente(lerBoolean(input, 1544));
			
			//1546 a 1546	Indicador Possui dependente	1	N		0 - Não 1 - Sim	X
			mensagem.setPossuiDependente(lerBoolean(input, 1545));
			
			//1547 a 1548	Quantidade de dependentes	2	N			X. Se Indicador Possui dependente = 1
			mensagem.setQuantidadeDependente(lerInt(input, 1546, 2));
			
			//1549 a 1563	Nome do cartão	15	A
			mensagem.setNomeCartao(lerString(input, 1548, 15));
			
			//1564 a 1564	indicadorCapturarFoto	1	A	Indicador da captura da Foto do Cliente.	""" "" Documentos capturados com sucesso ou Lojista sem captura digital com a Losango
			//1 - Problemas Técnicos
			//2 - Cliente não autoriza
			//3 - Outros"	X
			mensagem.setIndicadorCapturarFoto(lerString(input, 1563, 1));
			
			//1565 a 1565	indicadorCapturarDocumento	1	A	Indicador da captura do Documento.	""" "" Documentos capturados com sucesso ou Lojista sem captura digital com a Losango
			//1 - Problemas Técnicos
			//2 - Cliente não autoriza
			//3 - Outros"	X
			mensagem.setIndicadorCapturarDocumento(lerString(input, 1564, 1));
			
			//1566 a 1566	indicadorCapturarBiometria	1	A	Indicador da captura da Biometria	""" "" Documentos capturados com sucesso ou Lojista sem captura digital com a Losango
			//1 - Problemas Técnicos
			//2 - Cliente não autoriza
			//3 - Outros"	X
			mensagem.setIndicadorCapturarBiometria(lerString(input, 1565, 1));
			
			//1567 a 1613	Filler	47	A
			mensagem.setFillerDadosComplementares(lerStringCheia(input, 1566, 47));
			
			//REFERÊNCIAS PESSOAIS
			mensagem.setReferenciasPessoais(new LinkedList<Referencia>());
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
			mensagem.getReferenciasPessoais().add(new Referencia());
			mensagem.getReferenciasPessoais().get(0).setTelefone(new Telefone());
			mensagem.getReferenciasPessoais().get(0).setNome(lerString(input, 1613, 30));
			mensagem.getReferenciasPessoais().get(0).getTelefone().setDdd(lerInt(input, 1643, 3));
			mensagem.getReferenciasPessoais().get(0).getTelefone().setNumero(lerInt(input, 1646, 9));
			mensagem.getReferenciasPessoais().get(0).getTelefone().setRamal(lerInt(input, 1655, 4));
			
					
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
			mensagem.getReferenciasPessoais().add(new Referencia());
			mensagem.getReferenciasPessoais().get(1).setTelefone(new Telefone());
			mensagem.getReferenciasPessoais().get(1).setNome(lerString(input, 1659, 30));
			mensagem.getReferenciasPessoais().get(1).getTelefone().setDdd(lerInt(input, 1689, 3));
			mensagem.getReferenciasPessoais().get(1).getTelefone().setNumero(lerInt(input, 1692, 9));
			mensagem.getReferenciasPessoais().get(1).getTelefone().setRamal(lerInt(input, 1701, 4));
			
			//REFERÊNCIAS COMERCIAIS
			mensagem.setReferenciasComerciais(new LinkedList<Referencia>());
			
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
			mensagem.getReferenciasPessoais().get(0).setTelefone(new Telefone());
			mensagem.getReferenciasPessoais().get(0).setNome(lerString(input, 1705, 30));
			try {
				mensagem.getReferenciasPessoais().get(0).getTelefone().setDdd(lerInt(input, 1735, 3));	
			}
			catch(NumberFormatException e) {
				mensagem.getReferenciasPessoais().get(0).getTelefone().setDdd(null);
			}
			try {
				mensagem.getReferenciasPessoais().get(0).getTelefone().setNumero(lerInt(input, 1738, 9));	
			}
			catch(NumberFormatException e) {
				mensagem.getReferenciasPessoais().get(0).getTelefone().setNumero(null);
			}
			try {
				mensagem.getReferenciasPessoais().get(0).getTelefone().setRamal(lerInt(input, 1747, 4));	
			}
			catch(NumberFormatException e) {
				mensagem.getReferenciasPessoais().get(0).getTelefone().setRamal(null);
			}
			
			
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
			mensagem.getReferenciasPessoais().get(1).setTelefone(new Telefone());
			mensagem.getReferenciasPessoais().get(1).setNome(lerString(input, 1751, 30));
			try {
				mensagem.getReferenciasPessoais().get(1).getTelefone().setDdd(lerInt(input, 1781, 3));	
			}
			catch(NumberFormatException e) {
				mensagem.getReferenciasPessoais().get(1).getTelefone().setDdd(null);
			}
			
			try {
				mensagem.getReferenciasPessoais().get(1).getTelefone().setNumero(lerInt(input, 1784, 9));	
			}
			catch(NumberFormatException e) {
				mensagem.getReferenciasPessoais().get(1).getTelefone().setNumero(null);
			}
			
			try {
				mensagem.getReferenciasPessoais().get(1).getTelefone().setRamal(lerInt(input, 1793, 4));	
			}
			catch(NumberFormatException e) {
				mensagem.getReferenciasPessoais().get(1).getTelefone().setRamal(null);
			}
			
			//REFERÊNCIAS BANCARIAS						
			//1798 a 1801	Banco	4	N		Ver tabela de dominio Banco	X. Se algum campo da referencia bancaria for preenchuda 
			//1802 a 1805	Agência	4	N			X. Se algum campo da referencia bancaria for preenchuda 
			//1806 a 1806	DV Agência	1	A			
			//1807 a 1819	Conta Corrente	13	N			X. Se algum campo da referencia bancaria for preenchuda 
			//1820 a 1821	DV Conta Corrente	2	A			X. Se algum campo da referencia bancaria for preenchuda 
			//1822 a 1823	Tipo da Conta	2	A		Ver tabela de dominio Tipo de Conta Corrente	X. Se algum campo da referencia bancaria for preenchuda 
			//1824 a 1831	Data Abertura	8	N	DDMMAAAA		X. Se algum campo da referencia bancaria for preenchuda
			mensagem.setReferenciaBancaria(new Banco());
			mensagem.getReferenciaBancaria().setBanco(lerString(input, 1797, 4));
			mensagem.getReferenciaBancaria().setAgencia(lerString(input, 1801, 4));
			mensagem.getReferenciaBancaria().setDvAgencia(lerString(input, 1805, 1));
			mensagem.getReferenciaBancaria().setDvContaCorrente(lerString(input, 1806, 13));
			mensagem.getReferenciaBancaria().setDvContaCorrente(lerString(input, 1820, 2));
			mensagem.getReferenciaBancaria().setTipoConta(lerString(input, 1821, 2));
			try {
				mensagem.getReferenciaBancaria().setDataAbertura(lerData(input, 1823));
			}
			catch(ParseException e) {
				mensagem.getReferenciaBancaria().setDataAbertura(null);
			}
			
			//DADOS DA OPERAÇÃO						
			//1832 a 1839	Tabela de Financiamento	8	N	Identificação da tabela de financiamento  (COP’s) referente ao crédito solicitado, específica para o lojista ou Crédito Pessoal 		X
			mensagem.setTabelaFinanciamento(lerInt(input, 1831, 8));
			
			//1840 a 1840	Sinal da Carência 	1	A	Sinal da carência  (+) - Positiva  (-) - Negativa	"+"  -  "-" 	X
			mensagem.setSinalCarencia(lerString(input, 1839, 1));
			
			//1841 a 1842	Carência 	2	N	Quantidade de Dias Para Ajuste do Vencimento da Prestação		X
			mensagem.setCarencia(lerInt(input, 1840, 2));
			
			//1843 a 1843	Forma de pagamento	1	N	"Indicação da Forma de Cobrança
			//0) Carnë
			//1) averbação em folha
			//2) Cheque Pré
			//3) Extrato Rotativo
			//4) Extrato Parcelado
			//5) Debito em Conta"	“0”  “1”  “2”   “3”  ”4”, "5"	X
			mensagem.setFormaPagamento(lerInt(input, 1842, 1));
			
			
			//1844 a 1851	Data da  Operação	8	N	Data da Realização da Operação		X
			mensagem.setDataOperacao(lerData(input, 1843));
			
			//1852 a 1853	Produto (Tabela de Produto)	2	N	Informar o Produto		X
			mensagem.setProduto(lerInt(input, 1851, 2));
			
			//1854 a 1855	Prestações	2	N	Indicar O Nº de Parcelas do contrato		X
			mensagem.setPrestacoes(lerInt(input, 1853, 2));
			
			//1856 a 1862	Taxa Mensal	7	N	Taxa de Juros Aplicada Ao Mês (2 inteiras e 5 decimais)		X
			mensagem.setTaxaMensal(lerDouble(input, 1855, 7, 5));
			
			//1863 a 1869	Taxa Anual	7	N	Taxa de Juros Aplicada Ao Ano (3 inteiras 4 decimais)		
			mensagem.setTaxaAnual(lerDouble(input, 1862, 7, 4));
			
			//1870 a 1884	Valor da Entrada (não é mais utilizado)	15	N	Valor da Entrada (não é mais utilizado)		
			mensagem.setValorEntrada(lerInt(input, 1869, 15));
			
			//1885 a 1885	Tipo de Pagamento	1	N	0-Pré 1-Pós (Flag que indica se a negociação será efetuada com Pré fixado ou Pos fixado)	“0”  “1”	X
			mensagem.setTipoPagamento(lerInt(input, 1884, 1));
			
			//1886 a 1887	Top	2	N	Tipo de Operação		X
			mensagem.setTop(lerInt(input, 1885, 2));
			
			//1888 a 1902	Valor Tac	15	N	Valor da TAC (em R$)		X
			mensagem.setValorTac(lerInt(input, 1887, 15));
			
			//1903 a 1903	Pag_Tac	1	N	Flag que indica a forma de pagamento da TAC ( 0 -Financiada   1- A vista 2 - Descontada em (RO))	“0”   “1”    “2”	X
			mensagem.setPagTac(lerInt(input, 1902, 1));
			
			//1904 a 1918	Valor  da Operação/Solicitado	15	N	Valor solicitado pelo cliente (em R$)		X
			mensagem.setValorOperacaoSolicitado(lerInt(input, 1903, 15));
			
			//1919 a 1933	Valor Total do Financiamento	15	N	Valor Total do Financiamento (em R$).		X
			mensagem.setValorTotalFinanciado(lerInt(input, 1918, 15));
			
			//1934 a 1948	Valor da Prestação 	15	N	Valor A Ser Pago Mensalmente Já Com Taxa de Juros (em R$).		X
			mensagem.setValorPrestacao(lerInt(input, 1933, 15));
			
			//1949 a 1956	Vencimento 1ª prestação	8	N	Data do primeiro  vencimento		X
			mensagem.setVencimentoPrestacao(lerData(input, 1948));
			
			//1957 a 1981	Descrição do bem	25	A	Identificação da mercadoria financiada (obrigatório para TOP 31 e 34)
			mensagem.setDescricaoDoBem(lerString(input, 1956, 25));
			
			//1982 a 1982	Imp_Carne	1	A	Flag que indica que o lojista vai imprimir carnê na loja (0-Não   e     1-Sim)	“0”     “1”	X
			mensagem.setImpressaoCarne(lerBoolean(input, 1981));
			
			//1983 a 1997	Nº Pedido	15	A	Campo para o lojista associar o número de pedido, nota fiscal, etc.
			mensagem.setNuPedido(lerString(input, 1982, 15));
			
			//1998 a 2008	Nº do CD	11	A	Numeração gráfica pré impressa do comprovante de débito ( não é obrigatorio)
			mensagem.setNuCd(lerString(input, 1997, 11));
			
			//2009 a 2019	CPF do Vendedor	11	A	Identificação do vendedor/atendente  responsável pela operação		X
			mensagem.setCpfVendedor(lerString(input, 2008, 11));
			
			//2020 a 2033	Telefone	14	A	Telefone do Vendedor		X
			mensagem.setTelefoneVendedor(lerString(input, 2019, 14));
			
			//2034 a 2034	Pre-Pago	1	A	"Indica a compra de telefone celular Pre-Pago
			//  0 - Default - Não
			//  1 - Pre     - Sim"	 '0' '1'	X
			mensagem.setPrePago(lerBoolean(input, 2033));
			
			//2035 a 2035	Leva na Hora	1	A	"Indica se o cliente levara a mercadoria na hora
			//  0 - Default - Não
			//  1 - leva    - Sim"	 '0' '1'	X
			mensagem.setLevaNaHora(lerBoolean(input, 2034));
			
			//2036 a 2036	Beta-Gama	1	A	"Indicadore de Fraude
			//  0 - Default - Sem fraude
			//  2 - Beta    - Susp fraude
			//  1 - Gama    - Confirm fraude"	 '0' '1' '2'	X
			mensagem.setBetaGama(lerInt(input, 2035, 1));
			
			//2037 a 2046	Promotor	10	N	Código do Promotor
			try {
				mensagem.setPromotor(lerInt(input, 2036, 10));	
			}
			catch(NumberFormatException e) {
				mensagem.setPromotor(null);
			}
			
			//2047 a 2047	Indicador aceita consulta ao sysbacen	1	A	Indica se o cliente permitiu a consulta ao sysbacen 0- Não(Default), 1 - Sim	 '0' '1'	X
			mensagem.setAceitaConsulta(lerBoolean(input, 2046));
			
			//2048 a 2054	CET Mensal (%)	7	N	Taxa Mensal do Custo efetivo Total (2 decimais)	"Preencher com o valor 
			//informado pelo Simulador ou zero caso o simulador não foi utilizado"	
			mensagem.setCetMensal(lerDouble(input, 2047, 7, 2));
			
			//2055 a 2061	CET Anual  (%)	7	N	Taxa Anual   do Custo efetivo Total (2 decimais)	"Preencher com o valor 
			//informado pelo Simulador ou zero caso o simulador não foi utilizado"
			mensagem.setCetAnual(lerDouble(input, 2054, 7, 2));
			
			//2062 a 2068	IOF	7	N	Valor do IOF (2 casas decimais)	"Preencher com o valor 
			//informado pelo Simulador ou zero caso o simulador não foi utilizado"
			mensagem.setIof(lerDouble(input, 2061, 7, 2));
			
			//2069 a 2076	Data do Evento	8	N	Data da Entrega do Bem/Serviço		X. Se o produto for cessão
			mensagem.setDataEvento(lerData(input, 2068));
			
			//2077 a 2091	Valor da Entrada ao Lojista	15	N	Valor dado de entrada ao Lojista
			mensagem.setValorEntradaLojista(lerInt(input, 2076, 15));
			
			//2092 a 2141	Filler	50	A
			mensagem.setFillerDadosOperacao(lerStringCheia(input, 2091, 50));
			
			//Dados do Pre Screening						
			//2142 a 2143	Código da Oferta Aderida de Conta Corrente	2	A	Código da Oferta recuperada na mensagem 0460.
			mensagem.setCodigoOfertaAderidaContaCorrente(lerString(input, 2141, 2));
			
			//2144 a 2145	Código da Oferta Aderida de CDC/EP.	2	A	Código da Oferta recuperada na mensagem 0460.		
			mensagem.setCodigoOfertaAderidaContaCorrente(lerString(input, 2143, 2));
			
			//2146 a 2147	Código do Perfil da Oferta Aderida de CDC/EP.	2	A	Código do Perfil recuperado na mensagem 0460.		
			mensagem.setCodigoPerfilOfertaAderidaCdc(lerString(input, 2145, 2));
			
			//2148 a 2160	Filler	13	A
			mensagem.setFillerPreScreening(lerStringCheia(input, 2147, 13));
			
			//TODO: falta o parse do itens abaixo
			//Atendimento ao Cliente						
			//2161 a 2195	Nome do Vendedor	35	A			X
			//2196 a 2230	Nome do Agente Correspondente	35	A			
			//2231 a 2241	CPF do Agente Correspondente	11	A			X
			//Dados de Operações para Emprestimo Pessoal (EP) e averbação em folha.						
			//2242 a 2242	Ind. Pagto DOC - EP	1	A	Indicador de pagamento em DOC	"S = Sim com Doc
			// N =Não opera com EP"	X
			//2243 a 2244	Tipo de Conta (spb) - EP	2	A	Ver tabela de dominio		X. Se Ind. Pagto DOC - EP = "S"
			//2245 a 2248	Banco - EP	4	N	Número do Banco para Depósito do Emprestimo pessoal		X. Se Ind. Pagto DOC - EP = "S"
			//2249 a 2252	Agencia - EP	4	N	Número da Agencia para Depósito do Emprestimo pessoal		X. Se Ind. Pagto DOC - EP = "S"
			//2253 a 2253	DV Agencia  - EP	1	A	DV da Agencia para Depósito do Emprestimo pessoal		X. Se Ind. Pagto DOC - EP = "S"
			//2254 a 2266	Nº da conta - EP	13	N	Número da Conta para Depósito do Emprestimo pessoal		X. Se Ind. Pagto DOC - EP = "S"
			//2267 a 2268	Dv da conta  - EP	2	A	DV da Conta para deposito do Emprestimo pessoal		X. Se Ind. Pagto DOC - EP = "S"
			//2269 a 2269	C2 - EP	1	N			X. Se Ind. Pagto DOC - EP = "S"
			//2270 a 2270	C3 - EP	1	N			X. Se Ind. Pagto DOC - EP = "S"
			//Dados referente ao Seguro						
			//2271 a 2271	Adesao_Seguro	1	N	Flag que indica se o cliente optou por fazer seguro (0 - NÃO  ;  1 - SIM)	“0”   “1”	X
			//2272 a 2272	Forma pagamento acessório	1	A	Forma de pagamento do produto acessório (V – a vista ; F – Financiado)	“V”  “F”	X.  Se o Campo Adesao_Seguro estiver como 1.
			//2273 a 2273	Quantidade de Seguro	1	N	Indica se o Cliente vai aderir a um Seguro ou aos dois Seguros.	"0 - Não vai aderir;
			//1- Vai aderi a um Seguro;
			//2 - Vai aderir a dois Seguros;"	X.  Se o Campo Adesao_Seguro estiver como 1.
			//Dados referente ao Seguro Prestamista						
			//2274 a 2275	tipo do seguro	2	A	Tipo de produto acessório	"02"	X. Se algum campo do seguro prestamista for preenchido
			//2276 a 2279	código do seguro	4	A	Codigo do produto acessório	De acordo com a Matriz de Seguros	X. Se algum campo do seguro prestamista for preenchido
			//2280 a 2294	valor do seguro	15	N	Valor do produto acessório (em R$)	De acordo com a Matriz de Seguros	X. Se algum campo do seguro prestamista for preenchido
			//Dados referente ao Seguro da Sorte						
			//2295 a 2296	tipo do seguro	2	A	Tipo de produto acessório	"02"	X. Se algum campo do seguro da sorte for preenchido
			//2297 a 2300	código do seguro	4	A	Codigo do produto acessório	De acordo com a Matriz de Seguros	X. Se algum campo do seguro da sorte for preenchido
			//2301 a 2315	valor do seguro	15	N	Valor do produto acessório (em R$)	De acordo com a Matriz de Seguros	X. Se algum campo do seguro da sorte for preenchido
			//2316 a 2317	Quantidade Numero da Sorte	2	N	Usar o valor da matriz de Seguros	De acordo com a Matriz de Seguros	X. Se algum campo do seguro da sorte for preenchido
			//DEBITO EM CONTA						
			//2318 a 2321	Banco	4	N		Ver tabela de dominio Banco	X. Se Forma de pagamento = "5" 
			//2322 a 2325	Agencia	4	N			X. Se Forma de pagamento = "5" 
			//2326 a 2326	DV Agência	1	A			
			//2327 a 2339	Conta Corrente	13	N			X. Se Forma de pagamento = "5" 
			//2340 a 2341	DV Conta Corrente	2	A			X. Se Forma de pagamento = "5" 
			//2342 a 2343	Tipo da Conta	2	A		Ver tabela de dominio Tipo de Conta Corrente	X. Se Forma de pagamento = "5" 
			//2344 a 2351	Data de Abertura	8	N	DDMMAAAA		X. Se Forma de pagamento = "5" 
			//Dados de Cheques						
			//2352 a 2354	CÓDIGO DO BANCO dos cheques	3	N	Código do Banco da primeira faixa de cheques para as operações de cheque-pré	Se produto igual a "2"	X
			//2355 a 2358	AGÊNCIA  DE DESTINO dos cheques	4	N	Código da Agência Bancária da primeira faixa de cheques para as operações de cheque-pré	Se produto igual a "2"	X
			//2359 a 2359	DV Agencia dos cheques	1	A			
			//2360 a 2372	Codigo da Conta	13	N			X
			//2373 a 2374	DV da Conta	2	A			X
			//2375 a 2380	NÚMERO DO CHEQUE do Primeiro Cheque da 1a. Faixa de Cheques	6	N	Número do primeiro cheque da primeira faixa de cheques para as operações de cheque-pré	Se produto igual a "2"	X
			//2381 a 2386	NÚMERO DO CHEQUE do Último Cheque da 1a. Faixa de Cheques	6	N	Número do último cheque da primeira faixa de cheques para as operações de cheque-pré	Se produto igual a "2"	X
			//2387 a 2392	NÚMERO DO CHEQUE do Primeiro Cheque da 2a. Faixa de Cheques	6	N	Número do primeiro cheque da segunda faixa de cheques para as operações de cheque-pré		
			//2393 a 2398	NÚMERO DO CHEQUE do Último Cheque da 2a. Faixa de Cheques	6	N	Número do último cheque da segunda faixa de cheques para as operações de cheque-pré		
			//2399 a 2406	Data da abertura da conta corrente	8	N	Data da abertura da conta corrente	Se produto igual a "2"	X
			//2407 a 2454	Filler	48	A			
			//Circular 3641 Banco Central						
			//2455 a 2456	Flag Circular 3461 Banco Central	2	A	informar sempre o valor X2 nesse campo	X2	X
			//Observação						
			//2457 a 2711	Observação	255	A	Campo observação 	 	

			
		} catch (ParseException e) {
			
			throw new MensagemNaoEncontradaException(e);
		}
		
		
		
		//indicadores
		//2712 a 2712	Identificador do canal	1	A	Identifica que a proposta é de procedência do TRS
		//2713 a 2722	Versão do Canal	10	A	Uso exclusivo da Losango
		//2723 a 2723	Política	1	A	Uso exclusivo da Losango
		//2724 a 2725	Ambiente	2	A	Uso exclusivo da Losango
		mensagem.setIndicadores(new Indicador());
		mensagem.getIndicadores().setIdentificadorCanal(lerString(input, 2711, 1));
		mensagem.getIndicadores().setVersaoCanal(lerString(input, 2712, 10));
		mensagem.getIndicadores().setPolitica(lerString(input, 2722, 1));
		mensagem.getIndicadores().setAmbiente(lerString(input, 2723, 2));
		
	}

	private void lerDadosConjuge(String input, PropostaFinanciamento m) throws ParseException {
		
		m.setDadosConjuge(new DadoConjuge());
		m.getDadosConjuge().setDadoProfissional(new DadoProfissionalBasico());
		//0916 a 0945	Nome Do Cônjuge	30	A	Nome do Cônjuge do Cliente		X. Se conjuge compoe renda = 1 ou Estado Civil = 2
		m.getDadosConjuge().setNome(lerString(input, 915, 30));
		
		//0946 a 0985	Local de Nascimento	40	A
		m.getDadosConjuge().setLocalNascimento(lerString(input, 945, 40));
		
		//0986 a 0993	Data de Nascimento	8	N	Data nascimento do cônjuge.		X. Se conjuge compoe renda = 1
		try {
			m.getDadosConjuge().setDataNascimento(lerData(input, 985));
		} catch (ParseException e) {
			m.getDadosConjuge().setDataNascimento(null);
		}
		
		//0994 a 1004	CPF	11	N	CPF do Cônjuge		X. Se conjuge compoe renda = 1
		m.getDadosConjuge().setCpf(lerString(input, 993, 11));
		
		m.getDadosConjuge().setDocumentoIdentificacao(new Documento());
		//1005 a 1014	Identidade / RG	10	A	Número da Identidade do Cônjuge		X. Se conjuge compoe renda = 1
		m.getDadosConjuge().getDocumentoIdentificacao().setNuDocIdentificacao(lerString(input, 1004, 10));
		
		//1015 a 1016	Tipo de Documento	2	A		Ver tabela de dominio TP  DOCUMENTO IDENTIDADE	X. Se conjuge compoe renda = 1
		m.getDadosConjuge().getDocumentoIdentificacao().setTpDocIdentificacao(lerString(input, 1014, 2));
		
		//1017 a 1021	Órgão Emissor	5	A	Órgão Emissor do Documento de Identidade do Cônjuge	Ver tabela de dominio Orgão Emissor	X. Se conjuge compoe renda = 1
		m.getDadosConjuge().getDocumentoIdentificacao().setOrgaoEmissor(lerString(input, 1016, 5));
		
		//1022 a 1023	UF Órgão Emissor	2	A		Ver tabela de dominio UF	X. Se conjuge compoe renda = 1
		m.getDadosConjuge().getDocumentoIdentificacao().setUfOrgaoEmissor(lerString(input, 1021, 2));
		
		//1024 a 1031	Data Emissão	8	N	Data de emissão do documento do Cônjuge		X. Se conjuge compoe renda = 1
		try {
			m.getDadosConjuge().getDocumentoIdentificacao().setDataEmissao(lerData(input, 1023));
		} catch (ParseException e) {
			m.getDadosConjuge().getDocumentoIdentificacao().setDataEmissao(null);
		}
		
		//1032 a 1056	Empresa 	25	A	Empresa Em Que o Cônjuge Trabalha		X. Se conjuge compoe renda = 1
		m.getDadosConjuge().getDadoProfissional().setEmpresa(lerString(input, 1031, 25));
		
		//1057 a 1064	Data  Admissão	8	N	Data da Admissão na Empresa		X. Se conjuge compoe renda = 1
		try {
			m.getDadosConjuge().getDadoProfissional().setDataAdmissao(lerData(input, 1056));
		} catch (ParseException e) {
			m.getDadosConjuge().getDadoProfissional().setDataAdmissao(null);
		}
		
		m.getDadosConjuge().getDadoProfissional().setEndereco(new Endereco());
		//1065 a 1104	Logradouro	40	A	Logradouro do trabalho do conjuge		X. Se conjuge compoe renda = 1
		//1105 a 1109	Numero	5	A	Numero do Logradouro		X. Se conjuge compoe renda = 1 
		//1110 a 1124	Complemento	15	A	Complemento do Logradouro		
		//1125 a 1144	Bairro	20	A	Bairro onde trabalha o cônjuge		X. Se conjuge compoe renda = 1 
		//1145 a 1164	Cidade	20	A	Cidade Onde Trabalha o cônjuge		X. Se conjuge compoe renda = 1 
		//1165 a 1166	UF	2	A	Abreviatura da Unidade Federativa 		X. Se conjuge compoe renda = 1 
		//1167 a 1174	CEP	8	N	CEP do endereço comercial do cônjuge		X. Se conjuge compoe renda = 1
		m.getDadosConjuge().getDadoProfissional().getEndereco().setLogradouro(lerString(input, 1064, 40));
		m.getDadosConjuge().getDadoProfissional().getEndereco().setNumero(lerString(input, 1104, 5));
		m.getDadosConjuge().getDadoProfissional().getEndereco().setComplemento(lerString(input, 1109, 15));
		m.getDadosConjuge().getDadoProfissional().getEndereco().setBairro(lerString(input, 1124, 20));
		m.getDadosConjuge().getDadoProfissional().getEndereco().setCidade(lerString(input, 1144, 20));
		m.getDadosConjuge().getDadoProfissional().getEndereco().setUf(lerString(input, 1164, 2));
		try {
			m.getDadosConjuge().getDadoProfissional().getEndereco().setCep(lerInt(input, 1166, 8));	
		}
		catch(NumberFormatException e) {
			m.getDadosConjuge().getDadoProfissional().getEndereco().setCep(null);
		}
		
		m.getDadosConjuge().getDadoProfissional().setTelefone(new Telefone());
		//1175 a 1177	DDD	3	N	DDD da Cidade Onde Trabalha o cônjuge		X. Se conjuge compoe renda = 1
		//1178 a 1186	Telefone	9	N	"Se o campo DDD estiver preenchido com 011 e o numero do telefone não iniciar 70, 75, 78 e 79 e for um numero de celular, o telefone deve ser iniciado com o numero ""9"", caso contrário deverá ser iniciado com o numero ""0"".
		//1187 a 1190	Ramal	4	N	Ramal do Trabalho do cônjuge
		try {
			m.getDadosConjuge().getDadoProfissional().getTelefone().setDdd(lerInt(input, 1174, 3));	
		}
		catch(NumberFormatException e) {
			m.getDadosConjuge().getDadoProfissional().getTelefone().setDdd(0);
		}
		
		try {
			m.getDadosConjuge().getDadoProfissional().getTelefone().setNumero(lerInt(input, 1177, 9));	
		}
		catch(NumberFormatException e) {
			m.getDadosConjuge().getDadoProfissional().getTelefone().setNumero(0);
		}
		
		m.getDadosConjuge().getDadoProfissional().getTelefone().setRamal(lerInt(input, 1186, 4));
		
		
		//1191 a 1210	Cargo	20	A	Cargo do Cônjuge	Ver tabela de Dominio Cargo	X. Se conjuge compoe renda = 1
		m.getDadosConjuge().getDadoProfissional().setCargo(lerString(input, 1190, 20));
		
		//1211 a 1230	Profissão	20	A	Profissão do Conjuge	Ver tabela de dominio Profissão	X. Se conjuge compoe renda = 1
		m.getDadosConjuge().getDadoProfissional().setProfissao(lerString(input, 1210, 20));
		
		//1231 a 1231	Aposentado	1	A	"Aponta se o cliente é aposentado:
		//S - Sim; N - Não"	"S"  "N"	X. Se conjuge compoe renda = 1 
		m.getDadosConjuge().getDadoProfissional().setAposentado(lerBoolean(input, 1230));
		
		//1232 a 1232	Pensionista	1	A	"Aponta se o cliente é Pensionista:
		//S - Sim; N - Não"	"S"  "N"	X. Se conjuge compoe renda = 1
		m.getDadosConjuge().getDadoProfissional().setPensionista(lerBoolean(input, 1231));
		
		//1233 a 1233	Uso exclusivo da Losango	1	A	Uso exclusivo da Losango
		m.getDadosConjuge().getDadoProfissional().setLosango(lerString(input, 1232, 1));
		
		//1234 a 1244	Valor Renda Líquida 	11	N	Renda Líquida do Cônjuge (em R$)                                                  		X. Se conjuge compoe renda = 1
		try {
			m.getDadosConjuge().getDadoProfissional().setRendaLiquida(lerInt(input, 1233, 11));	
		}
		catch(NumberFormatException e) {
			m.getDadosConjuge().getDadoProfissional().setRendaLiquida(0);
		}
		
		//1245 a 1314	Patrimônio	70	A
		m.getDadosConjuge().setPatrimonio(new LinkedList<Patrimonio>());
		if(m.getDadosPessoais().isPossuiPatrimonio()) {
			
			m.getDadosConjuge().getPatrimonio().add(new Patrimonio());
			m.getDadosConjuge().getPatrimonio().get(0).setTipo(lerString(input, 1244, 1));
			m.getDadosConjuge().getPatrimonio().get(0).setNome(lerString(input, 1245, 5));
			m.getDadosConjuge().getPatrimonio().get(0).setValor(lerDouble(input, 1250, 11));
			
			m.getDadosConjuge().getPatrimonio().add(new Patrimonio());
			m.getDadosConjuge().getPatrimonio().get(1).setTipo(lerString(input, 1261, 1));
			m.getDadosConjuge().getPatrimonio().get(1).setNome(lerString(input, 1262, 5));
			m.getDadosConjuge().getPatrimonio().get(1).setValor(lerDouble(input, 1267, 11));
			
			m.getDadosConjuge().getPatrimonio().add(new Patrimonio());
			m.getDadosConjuge().getPatrimonio().get(2).setTipo(lerString(input, 1278, 1));
			m.getDadosConjuge().getPatrimonio().get(2).setNome(lerString(input, 1279, 5));
			m.getDadosConjuge().getPatrimonio().get(2).setValor(lerDouble(input, 1284, 11));
			
			m.getDadosConjuge().getPatrimonio().add(new Patrimonio());
			m.getDadosConjuge().getPatrimonio().get(3).setTipo(lerString(input, 1295, 1));
			m.getDadosConjuge().getPatrimonio().get(3).setNome(lerString(input, 1296, 5));
			m.getDadosConjuge().getPatrimonio().get(3).setValor(lerDouble(input, 1301, 11));
		}
		
		
		//1315 a 1315	Nacionalidade	1	N	Nacionalidade do Conjuge	"0-Brasileiro
		//1-Estrangeiro        "	X. Se conjuge compoe renda = 1
		m.getDadosConjuge().setNacionalidade(lerString(input, 1314, 1));
		
		//1316 a 1317	Código do País (informar se nacionalidade = estrangeiro)	2	A	Tabela de Paises	Ver tabela de Dominio Paises	X. Se conjuge compoe renda = 1
		m.getDadosConjuge().setCodigoPais(lerString(input, 1315, 2));
		
		//1318 a 1319	Código da UF da Naturalidade (informar se nacionalidade = brasileiro)	2	A	Tabela de UF	Ver tabela de dominio UF	X. Se conjuge compoe renda = 1
		m.getDadosConjuge().setUFNaturalidade(lerString(input, 1317, 2));
		
		//1320 a 1325	Data do Comprovante de Renda	6	A	Mes/Ano do comprovante de Renda apresentado pelo Conjuge	MMAAAA	X. Se conjuge compoe renda = 1
		try {
			m.getDadosConjuge().getDadoProfissional().setDataComprovanteRenda(lerDataCurta(input, 1320));	
		}
		catch(ParseException e) {
			m.getDadosConjuge().getDadoProfissional().setDataComprovanteRenda(null);
		}
		
		//1326 a 1327	Tipo Comprovante de Renda	2	A		Ver tabela Dominio tipo C Renda	X. Se conjuge compoe renda = 1
		m.getDadosConjuge().getDadoProfissional().setTipoComprovanteRenda(lerString(input, 1325, 2));
		
		//1328 a 1329	Ocupação nova	2	A	Código da Profissão	Ver tabela Dominio Código da Profissão	X. Se conjuge compoe renda = 1
		m.getDadosConjuge().getDadoProfissional().setOcupacaoNova(lerString(input, 1327, 2));
		
		//1330 a 1330	Sexo do Conjuge	1	A	M – Masculino     F – Feminino         	“M”   ”F”	X. Se conjuge compoe renda = 1 ou Estado Civil = 2
		m.getDadosConjuge().setSexo(lerString(input, 1329, 1));
		
		//1331 a 1344	CNPJ Conjuge	14	A			
		m.getDadosConjuge().getDadoProfissional().setCnpj(lerString(input, 1330, 14));
		
		//1331 a 1364	Filler	20	A			X, se Empresario ou Proprietario
		m.getDadosConjuge().setFiller(lerString(input, 1330, 20));
	}

	private void lerDadosProfissionais(String input, PropostaFinanciamento m) throws ParseException {
		
		m.setDadosProfissionais(new DadoProfissional());
		//0626 a 0633	Data de Admissão 	8	N	Data de Admissão na Empresa.                  		X
		try {
			m.getDadosProfissionais().setDataAdmissao(lerData(input, 625));	
		}
		catch(ParseException e) {
			m.getDadosProfissionais().setDataAdmissao(null);
		}
		
		
		//0634 a 0663	Empresa	30	A	Empresa Em Que Trabalha o Cliente                              		X
		m.getDadosProfissionais().setEmpresa(lerString(input, 633, 30));
		
		m.getDadosProfissionais().setEndereco(new Endereco());
		//0664 a 0703	Logradouro	40	A	Logradouro onde Trabalha o Cliente		X
		m.getDadosProfissionais().getEndereco().setLogradouro(lerString(input, 663, 40));
		
		//0704 a 0708	Numero	5	A	Numero do Logradouro		X
		m.getDadosProfissionais().getEndereco().setNumero(lerString(input, 703, 5));
		
		//0709 a 0723	Complemento	15	A	Complemento do logradouro
		m.getDadosProfissionais().getEndereco().setComplemento(lerString(input, 708, 15));
		
		//0724 a 0743	Bairro	20	A	Bairro onde Trabalha o Cliente		X
		m.getDadosProfissionais().getEndereco().setBairro(lerString(input, 723, 20));
		
		//0744 a 0763	Cidade	20	A	Cidade onde Trabalha o Cliente		X
		m.getDadosProfissionais().getEndereco().setCidade(lerString(input, 743, 20));
		
		//0764 a 0765	UF	2	A	Unidade Federativa onde Trabalha o Cliente		X
		m.getDadosProfissionais().getEndereco().setUf(lerString(input, 763, 2));
		
		//0766 a 0773	CEP	8	N	CEP onde trabalha o cliente		X
		m.getDadosProfissionais().getEndereco().setCep(lerInt(input, 765, 8));
		
		m.getDadosProfissionais().setTelefone(new Telefone());
		//0774 a 0776	DDD	3	N	DDD da Cidade Onde Trabalha o Cliente		X
		//0777 a 0785	Telefone	9	N	"Se o campo DDD estiver preenchido com 011 e o numero do telefone não iniciar 70, 75, 78 e 79 e for um numero de celular, o telefone deve ser iniciado com o numero ""9"", caso contrário deverá ser iniciado com o numero ""0"".
		//0786 a 0789	Ramal	4	N	Ramal do Trabalho do Cliente
		m.getDadosProfissionais().getTelefone().setDdd(lerInt(input, 773, 3));
		m.getDadosProfissionais().getTelefone().setNumero(lerInt(input, 776, 9));
		m.getDadosProfissionais().getTelefone().setRamal(lerInt(input, 785, 4));
		
		//0790 a 0800	Valor Renda Líquida 	11	N	Renda Líquida do Cliente (em R$)                                              		X
		m.getDadosProfissionais().setRendaLiquida(lerInt(input, 789, 11));
		
		//0801 a 0820	Cargo	20	A	Cargo do Cliente	Ver tabela de Dominio Cargo	X
		m.getDadosProfissionais().setCargo(lerString(input, 800, 20));
		
		//0821 a 0840	Profissão	20	A	Profissão do Cliente	Ver tabela de dominio Profissão	X
		m.getDadosProfissionais().setProfissao(lerString(input, 820, 20));
		
		//0841 a 0841	Aposentado	1	A	"Aponta se o cliente é aposentado:
		//S - Sim; N - Não"	"S"  "N"	X
		m.getDadosProfissionais().setAposentado(lerBooleanString(input, 840));
		
		//0842 a 0842	Pensionista	1	A	"Aponta se o cliente é Pensionista:
		//S - Sim; N - Não"	"S"  "N"	X
		m.getDadosProfissionais().setPensionista(lerBooleanString(input, 841));
		
		//0843 a 0843	Uso exclusivo da Losango	1	A	Uso exclusivo da Losango
		m.getDadosProfissionais().setLosango(lerString(input, 842, 1));
		
		//0844 a 0845	Orgão Beneficio	2	A		Ver tabela de dominio Orgao Beneficio	X. Se Aposentado ou Pensionista = SIM
		m.getDadosProfissionais().setOpcaoBeneficio(lerString(input, 843, 2));
		
		//0846 a 0865	Número do benefício	20	A			X. Se Aposentado ou Pensionista = SIM
		m.getDadosProfissionais().setNumeroBeneficio(lerString(input, 845, 20));
		
		//0866 a 0871	Data do Comprovante de Renda	6	A	Mes/Ano do comprovante de Renda apresentado pelo Cliente	MMAAAA	X, salvo se o Tipo de Comprovante de Renda = "N"
		m.getDadosProfissionais().setDataComprovanteRenda(lerDataCurta(input, 865));
		
		//0872 a 0873	Tipo Comprovante de Renda	2	A		Ver tabela Dominio tipo C Renda	X
		m.getDadosProfissionais().setTipoComprovanteRenda(lerString(input, 871, 2));
		
		//0874 a 0875	Ocupação nova	2	A	Código da Profissão	Ver tabela Dominio Código da Profissão	X
		m.getDadosProfissionais().setOcupacaoNova(lerString(input, 873, 2));
		
		//0876 a 0889	Cnpj Cliente	14	A			X, se Empresario ou Proprietario
		m.getDadosProfissionais().setCnpj(lerString(input, 875, 14));
		
		//0890 a 0915	Filler	26	A
		m.getDadosProfissionais().setFiller(lerStringCheia(input, 889, 26));
	}

	@Override
	void escrever(StringBuilder b, PropostaFinanciamento mensagem) throws MensagemNaoEncontradaException {

	}
}
