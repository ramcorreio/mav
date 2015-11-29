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
