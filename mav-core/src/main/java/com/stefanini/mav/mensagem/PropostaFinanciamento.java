package com.stefanini.mav.mensagem;

import java.util.Date;

import com.stefanini.mav.es.MapAtributo;
import com.stefanini.mav.es.MapBean;
import com.stefanini.mav.es.PosicaoInicio;

@PosicaoInicio(posicao = 83)
public class PropostaFinanciamento extends MensagemBasica {

	private static final long serialVersionUID = 2703729128402664733L;

	@MapBean
	private DadoPessoal dadosPessoais;
	
	@MapBean
	private DadoProfissional dadosProfissionais;

/*	private Indicador indicadores;

	private DadoProfissional dadosProfissionais;

	private DadoConjuge dadosConjuge;

	private Integer escolaridade;

	private String formacao;

	private Boolean possuiCartao;

	private Boolean possuiVeiculoProprio;

	private String placa;

	private String renavam;

	private Boolean veiculoQuitado;

	private Boolean possuiExperienciaCredito;

	private String localExperienciaCredito;

	private Integer planoExperienciaCredito;

	private Integer valorPrestacaoExperienciaCredito;

	private Date inicioExperienciaCredito;

	private String classificacaoCliente;

	private Boolean possuiCartaoFinanceira;

	private Boolean possuiContaCorrente;

	private Boolean possuiDependente;

	private Integer quantidadeDependente;

	private String nomeCartao;

	private String indicadorCapturarFoto;

	private String indicadorCapturarDocumento;

	private String indicadorCapturarBiometria;

	private String fillerDadosComplementares;

	private List<Referencia> referenciasPessoais;

	private List<Referencia> referenciasComerciais;

	private Banco referenciaBancaria;

	private Integer tabelaFinanciamento;

	private String sinalCarencia;

	private Integer carencia;

	private Integer formaPagamento;

	private Date dataOperacao;

	private Integer produto;

	private Integer prestacoes;

	private Double taxaMensal;

	private Double taxaAnual;

	private Integer valorEntrada;

	private Integer tipoPagamento;

	private Integer top;

	private Integer valorTac;

	private Integer pagTac;

	private Integer valorOperacaoSolicitado;

	private Integer valorTotalFinanciado;

	private Integer valorPrestacao;

	private Date vencimentoPrestacao;

	private String descricaoDoBem;

	private Boolean impressaoCarne;

	private String nuPedido;

	private String nuCd;

	private String cpfVendedor;

	private String telefoneVendedor;

	private Boolean prePago;

	private Boolean levaNaHora;

	private Integer betaGama;

	private Integer promotor;

	private Boolean aceitaConsulta;

	private Double cetMensal;

	private Double cetAnual;

	private Double iof;

	private Date dataEvento;

	private Integer valorEntradaLojista;

	private String fillerDadosOperacao;

	private String codigoOfertaAderidaContaCorrente;

	private String codigoPerfilOfertaAderidaCdc;

	private String fillerPreScreening;*/

	public PropostaFinanciamento(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}

	public DadoPessoal getDadosPessoais() {
		return dadosPessoais;
	}
	
	public void setDadosPessoais(DadoPessoal dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}
	
	public DadoProfissional getDadosProfissionais() {
		return dadosProfissionais;
	}
	
	public void setDadosProfissionais(DadoProfissional dadosProfissionais) {
		this.dadosProfissionais = dadosProfissionais;
	}
	
	// 0084 a 0084	Tipo de personalidade do CPF	1	A	T = Tipo de Pessoa (F – Fisica).	“F”	X
	// 0085 a 0098	CPF 	14	N	CPF / CGC do cliente  		X
	// 0099 a 0099	Usuário do CPF	1	A	Tipo de CPF ( T - Titular  ;   D  -  Dependente  )	“T”   “D”	X
	// 0100 a 0100	Correspondência	1	N	1 – Residencial      2 - comercial (Identificação da Localização do recebimento do documento	“1”  “ 2”	X
	// 0101 a 0115	Numero do Documento de identificação do Cliente	15	A	Numero do documento de identificação do cliente		X
	// 0116 a 0117	Tipo de Documento	2	A	Tipo do Documento do Cliente	Ver tabela de dominio TP  DOCUMENTO IDENTIDADE	X
	// 0118 a 0122	Órgão Emissor	5	A	Órgão emissor do documento de identidade do cliente	Ver tabela de dominio Orgão Emissor	X
	// 0123 a 0124	UF Órgão Emissor	2	A		Ver tabela de dominio UF	X
	// 	0125 a 0132	Data_Emissão	8	N	Data de emissão do documento de identidade do cliente		X
	// 	0133 a 0133	Conjuge Compõe Renda	1	N	 0 - Não     1 - Sim   (Flag que indica que conjuge compõe renda)	“0”    “1”	X
	// 	0134 a 0163	Nome	30	A	Nome do cliente		X
	// 	0164 a 0203	Local de Nascimento	40	A	Informar o Municipio de Nascimento do Cliente	Tabela de Dominio Local de Nascimento	X, se a nacionalidade igual a Brasileiro
	// 	0204 a 0211	Data_Nascimento	8	N	Data de nascimento do cliente		X
	// 	0212 a 0212	Sexo	1	A	M – Masculino     F – Feminino         	“M”   ”F”	X
	// 	0213 a 0213	Nacionalidade	1	N	"Nacionalidade do Cliente
	// 	0-Brasileiro
	// 	1-Estrangeiro        "	“0”   “1”	X
	// 	0214 a 0228	Naturalidade	15	A	Naturalidade do cliente		
	// 	0229 a 0258	Nome da Mãe	30	A	Nome da Mãe do Cliente 		X
	// 	0259 a 0288	Nome do Pai	30	A	Nome do Pai do Cliente 		
	// 	0289 a 0293	Carteira Profissional	5	N	Número da Carteira Profissional do Cliente (PC)		
	// 	0294 a 0298	Série	5	A	Número de Série da Carteira Profissional do cliente (PC)		
	// 	0299 a 0299	Estado Civil	1	N	Informar o Estado Civil	Ver Tabela Dominio Estado Civil	X
	// 	0300 a 0339	Logradouro	40	A	Logradouro da residência do cliente		X
	// 	0340 a 0344	Numero	5	A	Numero do logradouro		X
	// 	0345 a 0359	Complemento	15	A	Complemento do logradouro		
	// 	0360 a 0379	Bairro	20	A	Bairro endereço residencial		X
	// 	0380 a 0399	Cidade	20	A	Cidade endereço residencial		X
	// 	0400 a 0401	UF	2	A	Abreviação do Estado onde o cliente reside		X
	// 	0402 a 0409	CEP	8	N	CEP endereço residencial		X
	// 	0410 a 0412	DDD	3	N	DDD telefone residencial		X
	// 	0413 a 0421	Telefone	9	N	"Se o campo DDD estiver preenchido com 011 e o numero do telefone não iniciar 70, 75, 78 e 79 e for um numero de celular, o telefone deve ser iniciado com o numero ""9"", caso contrário deverá ser iniciado com o numero ""0"".
	// 	Exemplos:
	// 	1º) DDD=011 e numero = 8240-3043 ==> 98240-3043
	// 	2º) DDD=011 e numero = 7040-3043 ==> 07040-3043
	// 	3º) DDD=011 e numero = 3043-5322 ==> 03043-5322
	// 	4º) DDD=021 e numero = 8243-5322 ==> 08243-5322
	// 	5º) DDD=021 e numero = 3043-5322 ==> 03043-5322"		X
	// 	0422 a 0425	Ramal	4	N	Ramal do telefone residencial do cliente		
	// 	0426 a 0426	Tipo Telefone	1	N	Informar o Tipo de telefone	Ver tabela de dominio Tipo de Telefone	X
	// 	0427 a 0427	Tipo Residencia	1	N	Informar o Tipo de Residencia	Ver tabela de dominio Tip de Residencia	X
	// 	0428 a 0433	Reside desde	6	N	MMAAAA		
	// 	0434 a 0436	DDD Celular	3	N			X.
	// 	0437 a 0445	Telefone Celular	9	N	"Se o campo DDD estiver preenchido com 011 e o numero do telefone não iniciar 70, 75, 78 e 79 e for um numero de celular, o telefone deve ser iniciado com o numero ""9"", caso contrário deverá ser iniciado com o numero ""0"".
	// 	Exemplos:
	// 	1º) DDD=011 e numero = 8240-3043 ==> 98240-3043
	// 	2º) DDD=011 e numero = 7040-3043 ==> 07040-3043
	// 	3º) DDD=011 e numero = 3043-5322 ==> 03043-5322
	// 	4º) DDD=021 e numero = 8243-5322 ==> 08243-5322
	// 	5º) DDD=021 e numero = 3043-5322 ==> 03043-5322"		X.
	// 	0446 a 0505	Email	60	A			
	// 	0506 a 0506	Cliente Possui Patrimonio?	1	N	Campo que o Clinte indica se possui Patrimonios, caso a opção escolhida seja “Sim”, deverão ser informados pelo menos um Patrimonio, composto por : “Tipo de Patrimônio”, “Patrimônio” e “Valor do Patrimônio”.	 0 - Não     1 - Sim 	
	// 	0507 a 0507	Tipo de Patrimonio 1	1	A	Deverá ser informado o Tipo de Patrimônio	"“1” - “Bens Móveis” e/ou
	// 	“2” - “Bens Imóveis”"	Enviar em branco se o "cliente possue patrimonio = 0"
	// 	0508 a 0512	Patrimonio 1	5	A	Dever ser informado a característica do patrimônio. 	Tabela de Dominio Patrimonio	Enviar em branco se o "cliente possue patrimonio = 0"
	// 	0513 a 0523	Valor do Patrimonio 1	11	N	Dever informado o valor do Bem	9 inteiros e 2 decimais	Enviar em zerado se o "cliente possue patrimonio = 0"
	// 	0524 a 0524	Tipo de Patrimonio 2	1	A	Deverá ser informado o Tipo de Patrimônio	"“1” - “Bens Móveis” e/ou
	// 	“2” - “Bens Imóveis”"	Enviar em branco se o "cliente possue patrimonio = 0"
	// 	0525 a 0529	Patrimonio 2	5	A	Dever ser informado a característica do patrimônio. 	Tabela de Dominio Patrimonio	Enviar em branco se o "cliente possue patrimonio = 0"
	// 	0530 a 0540	Valor do Patrimonio 2	11	N	Dever informado o valor do Bem	9 inteiros e 2 decimais	Enviar em zerado se o "cliente possue patrimonio = 0"
	// 	0541 a 0541	Tipo de Patrimonio 3	1	A	Deverá ser informado o Tipo de Patrimônio	"“1” - “Bens Móveis” e/ou
	// 	“2” - “Bens Imóveis”"	Enviar em branco se o "cliente possue patrimonio = 0"
	// 	0542 a 0546	Patrimonio 3	5	A	Dever ser informado a característica do patrimônio. 	Tabela de Dominio Patrimonio	Enviar em branco se o "cliente possue patrimonio = 0"
	// 	0547 a 0557	Valor do Patrimonio 3	11	N	Dever informado o valor do Bem	9 inteiros e 2 decimais	Enviar em zerado se o "cliente possue patrimonio = 0"
	// 	0558 a 0558	Tipo de Patrimonio 4	1	A	Deverá ser informado o Tipo de Patrimônio	"“1” - “Bens Móveis” e/ou
	// 	“2” - “Bens Imóveis”"	Enviar em branco se o "cliente possue patrimonio = 0"
	// 	0559 a 0563	Patrimonio 4	5	A	Dever ser informado a característica do patrimônio. 	Tabela de Dominio Patrimonio	Enviar em branco se o "cliente possue patrimonio = 0"
	// 	0564 a 0574	Valor do Patrimonio 4	11	N	Dever informado o valor do Bem	9 inteiros e 2 decimais	Enviar em zerado se o "cliente possue patrimonio = 0"
	// 	0575 a 0575	Filler	1	A			
	// 	0576 a 0577	Código do País (informar se nacionalidade = estrangeiro)	2	A	Tabela de Paises	Ver tabela de Dominio Paises	X. Se Nacionalidade = Estrangeiro
	// 	0578 a 0579	Código da UF da Naturalidade (informar se nacionalidade = brasileiro)	2	A	Tabela de UF	Ver tabela de dominio UF	X. Se Nacionalidade = Brasileiro
	// 	0580 a 0587	Data de Vencimento do Documento de identificação	8	A			X, se o tipo do documento for: 02, 03 e 09
	// 	0588 a 0588	Flag Emancipado	1	A		0 - Nao 1 - Sim	X
	// 	0589 a 0589	Origem do patrimonio 1	1	A			
	// 	0590 a 0590	Origem do patrimonio 2	1	A			
	// 	0591 a 0591	Origem do patrimonio 3	1	A			
	// 	0592 a 0592	Origem do patrimonio 4	1	A			
	// 	0593 a 0625	Filler	33	A
	public static class DadoPessoal {
		
		@MapAtributo
		private String tipoPersonalidadede;
		
		@MapAtributo(tamanho = 14)
		private String cpf;
		
		@MapAtributo
		private String usuarioCpf;
		
		@MapAtributo(tamanho = 1)
		private String correspondencia;	
		
		@MapBean
		private Documento identificacao;
		
		@MapAtributo
		private Boolean conjugeCompoeRenda;
		
		@MapAtributo(tamanho = 30)
		private String nome;
		
		@MapAtributo(tamanho = 40)
		private String localNascimento;
		
		@MapAtributo(tamanho = 8)
		private Date dataNascimento;
		
		@MapAtributo
		private String sexo;
		
		@MapAtributo
		private Integer nacionalidade;
		
		@MapAtributo(tamanho = 15)
		private String naturalidade;
		
		@MapAtributo(tamanho = 30)
		private String nomeMae;
		
		@MapAtributo(tamanho = 30)
		private String nomePai;
		
		@MapAtributo(tamanho = 5)
		private Integer ctps;
		
		@MapAtributo(tamanho = 5)
		private String ctpsSerie;
		
		@MapAtributo
		private Integer estadoCivil;
		
		@MapBean
		private Endereco enderecoResidencial;
		
		@MapBean
		private TelefoneRamal telefoneResidencial;
		
		@MapAtributo(tamanho = 1)
		private Integer tipoTelefone;
		
		@MapAtributo(tamanho = 1)
		private Integer tipoResidencia;
		
		@MapAtributo(tamanho = 6, formato = "MMyyyy")
		private Date resideDesde;
		
		@MapBean
		private Telefone telefoneCelular;
		
		@MapAtributo(tamanho = 60)
		private String email;
		
		@MapAtributo
		private Boolean possuiPatrimonio;
		
		@MapAtributo(tamanho = 68)
		private String patrimonio;
		
		@MapAtributo(tamanho = 1)
		private String fillerPatrimonio;
		
		@MapAtributo(tamanho = 2)
		private String codigoPais;
		
		@MapAtributo(tamanho = 2)
		private String codigoUfNaturalidade;
		
		@MapAtributo(tamanho = 8)
		private Date dataVencimentoIdentificacao;
		
		@MapAtributo
		private Boolean emancipado;
						
		@MapAtributo(tamanho = 4)
		private String origemPatrimonio;
		
		@MapAtributo(tamanho = 33, trim = false)
		private String fillerDados;

		public String getTipoPersonalidadede() {
			return tipoPersonalidadede;
		}

		public void setTipoPersonalidadede(String tipoPersonalidadede) {
			this.tipoPersonalidadede = tipoPersonalidadede;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public String getUsuarioCpf() {
			return usuarioCpf;
		}

		public void setUsuarioCpf(String usuarioCpf) {
			this.usuarioCpf = usuarioCpf;
		}

		public String getCorrespondencia() {
			return correspondencia;
		}

		public void setCorrespondencia(String correspondencia) {
			this.correspondencia = correspondencia;
		}

		public Documento getIdentificacao() {
			return identificacao;
		}

		public void setIdentificacao(Documento identificacao) {
			this.identificacao = identificacao;
		}

		public Boolean isConjugeCompoeRenda() {
			return conjugeCompoeRenda;
		}

		public void setConjugeCompoeRenda(Boolean conjugeCompoeRenda) {
			this.conjugeCompoeRenda = conjugeCompoeRenda;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getLocalNascimento() {
			return localNascimento;
		}

		public void setLocalNascimento(String localNascimento) {
			this.localNascimento = localNascimento;
		}

		public Date getDataNascimento() {
			return dataNascimento;
		}

		public void setDataNascimento(Date dataNascimento) {
			this.dataNascimento = dataNascimento;
		}

		public String getSexo() {
			return sexo;
		}

		public void setSexo(String sexo) {
			this.sexo = sexo;
		}

		public Integer getNacionalidade() {
			return nacionalidade;
		}

		public void setNacionalidade(Integer nacionalidade) {
			this.nacionalidade = nacionalidade;
		}

		public String getNaturalidade() {
			return naturalidade;
		}

		public void setNaturalidade(String naturalidade) {
			this.naturalidade = naturalidade;
		}

		public String getNomeMae() {
			return nomeMae;
		}

		public void setNomeMae(String nomeMae) {
			this.nomeMae = nomeMae;
		}

		public String getNomePai() {
			return nomePai;
		}

		public void setNomePai(String nomePai) {
			this.nomePai = nomePai;
		}

		public Integer getCtps() {
			return ctps;
		}

		public void setCtps(Integer ctps) {
			this.ctps = ctps;
		}

		public String getCtpsSerie() {
			return ctpsSerie;
		}

		public void setCtpsSerie(String ctpsSerie) {
			this.ctpsSerie = ctpsSerie;
		}

		public Integer getEstadoCivil() {
			return estadoCivil;
		}

		public void setEstadoCivil(Integer estadoCivil) {
			this.estadoCivil = estadoCivil;
		}

		public Endereco getEnderecoResidencial() {
			return enderecoResidencial;
		}

		public void setEnderecoResidencial(Endereco enderecoResidencial) {
			this.enderecoResidencial = enderecoResidencial;
		}

		public TelefoneRamal getTelefoneResidencial() {
			return telefoneResidencial;
		}

		public void setTelefoneResidencial(TelefoneRamal telefoneResidencial) {
			this.telefoneResidencial = telefoneResidencial;
		}

		public Integer getTipoTelefone() {
			return tipoTelefone;
		}

		public void setTipoTelefone(Integer tipoTelefone) {
			this.tipoTelefone = tipoTelefone;
		}

		public Integer getTipoResidencia() {
			return tipoResidencia;
		}

		public void setTipoResidencia(Integer tipoResidencia) {
			this.tipoResidencia = tipoResidencia;
		}

		public Date getResideDesde() {
			return resideDesde;
		}

		public void setResideDesde(Date resideDesde) {
			this.resideDesde = resideDesde;
		}

		public Telefone getTelefoneCelular() {
			return telefoneCelular;
		}

		public void setTelefoneCelular(Telefone telefoneCelular) {
			this.telefoneCelular = telefoneCelular;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Boolean isPossuiPatrimonio() {
			return possuiPatrimonio;
		}

		public void setPossuiPatrimonio(Boolean possuiPatrimonio) {
			this.possuiPatrimonio = possuiPatrimonio;
		}

		public String getPatrimonio() {
			return patrimonio;
		}

		public void setPatrimonio(String patrimonio) {
			this.patrimonio = patrimonio;
		}

		public String getFillerPatrimonio() {
			return fillerPatrimonio;
		}

		public void setFillerPatrimonio(String fillerPatrimonio) {
			this.fillerPatrimonio = fillerPatrimonio;
		}

		public String getCodigoPais() {
			return codigoPais;
		}

		public void setCodigoPais(String codigoPais) {
			this.codigoPais = codigoPais;
		}

		public String getCodigoUfNaturalidade() {
			return codigoUfNaturalidade;
		}

		public void setCodigoUfNaturalidade(String codigoUfNaturalidade) {
			this.codigoUfNaturalidade = codigoUfNaturalidade;
		}

		public Date getDataVencimentoIdentificacao() {
			return dataVencimentoIdentificacao;
		}

		public void setDataVencimentoIdentificacao(Date dataVencimentoIdentificacao) {
			this.dataVencimentoIdentificacao = dataVencimentoIdentificacao;
		}

		public Boolean isEmancipado() {
			return emancipado;
		}

		public void setEmancipado(Boolean emancipado) {
			this.emancipado = emancipado;
		}

		public String getOrigemPatrimonio() {
			return origemPatrimonio;
		}

		public void setOrigemPatrimonio(String origemPatrimonio) {
			this.origemPatrimonio = origemPatrimonio;
		}

		public String getFillerDados() {
			return fillerDados;
		}

		public void setFillerDados(String fillerDados) {
			this.fillerDados = fillerDados;
		}
	}
	
	
	//0626 a 0633	Data de Admissão 	8	N	Data de Admissão na Empresa.                  		X
	//0634 a 0663	Empresa	30	A	Empresa Em Que Trabalha o Cliente                              		X
	//0664 a 0703	Logradouro	40	A	Logradouro onde Trabalha o Cliente		X
	//0704 a 0708	Numero	5	A	Numero do Logradouro		X
	//0709 a 0723	Complemento	15	A	Complemento do logradouro		
	//0724 a 0743	Bairro	20	A	Bairro onde Trabalha o Cliente		X
	//0744 a 0763	Cidade	20	A	Cidade onde Trabalha o Cliente		X
	//0764 a 0765	UF	2	A	Unidade Federativa onde Trabalha o Cliente		X
	//0766 a 0773	CEP	8	N	CEP onde trabalha o cliente		X
	//0774 a 0776	DDD	3	N	DDD da Cidade Onde Trabalha o Cliente		X
	//0777 a 0785	Telefone	9	N	"Se o campo DDD estiver preenchido com 011 e o numero do telefone não iniciar 70, 75, 78 e 79 e for um numero de celular, o telefone deve ser iniciado com o numero ""9"", caso contrário deverá ser iniciado com o numero ""0"".
	//Exemplos:
	//1º) DDD=011 e numero = 8240-3043 ==> 98240-3043
	//2º) DDD=011 e numero = 7040-3043 ==> 07040-3043
	//3º) DDD=011 e numero = 3043-5322 ==> 03043-5322
	//4º) DDD=021 e numero = 8243-5322 ==> 08243-5322
	//5º) DDD=021 e numero = 3043-5322 ==> 03043-5322"		X
	//0786 a 0789	Ramal	4	N	Ramal do Trabalho do Cliente		
	//0790 a 0800	Valor Renda Líquida 	11	N	Renda Líquida do Cliente (em R$)                                              		X
	//0801 a 0820	Cargo	20	A	Cargo do Cliente	Ver tabela de Dominio Cargo	X
	//0821 a 0840	Profissão	20	A	Profissão do Cliente	Ver tabela de dominio Profissão	X
	//0841 a 0841	Aposentado	1	A	"Aponta se o cliente é aposentado:
	//S - Sim; N - Não"	"S"  "N"	X
	//0842 a 0842	Pensionista	1	A	"Aponta se o cliente é Pensionista:
	//S - Sim; N - Não"	"S"  "N"	X
	//0843 a 0843	Uso exclusivo da Losango	1	A	Uso exclusivo da Losango		
	//0844 a 0845	Orgão Beneficio	2	A		Ver tabela de dominio Orgao Beneficio	X. Se Aposentado ou Pensionista = SIM
	//0846 a 0865	Número do benefício	20	A			X. Se Aposentado ou Pensionista = SIM
	//0866 a 0871	Data do Comprovante de Renda	6	A	Mes/Ano do comprovante de Renda apresentado pelo Cliente	MMAAAA	X, salvo se o Tipo de Comprovante de Renda = "N"
	//0872 a 0873	Tipo Comprovante de Renda	2	A		Ver tabela Dominio tipo C Renda	X
	//0874 a 0875	Ocupação nova	2	A	Código da Profissão	Ver tabela Dominio Código da Profissão	X
	//0876 a 0889	Cnpj Cliente	14	A			X, se Empresario ou Proprietario
	//0890 a 0915	Filler	26	A			
	public static class DadoProfissional {

		@MapAtributo(tamanho = 8)
		private Date dataAdmissao;
		
		@MapAtributo(tamanho = 30)
		private String empresa;
		
		@MapBean
		private Endereco endereco;
		
		@MapBean
		private TelefoneRamal telefone;

		@MapAtributo(tamanho = 11, scale = 2)
		private Double valorRendaLiquida;

		@MapAtributo(tamanho = 20)
		private String cargo;

		@MapAtributo(tamanho = 20)
		private String profissao;

		@MapAtributo
		private Boolean aposentado;

		@MapAtributo
		private Boolean pensionista;

		@MapAtributo
		private String usoExclusivoLosango;

		
		@MapAtributo(tamanho = 2)
		private String orgaoBeneficio;

		@MapAtributo(tamanho = 20)
		private String numeroBeneficio;
		
		
		@MapAtributo(tamanho = 6, formato = "MMyyyy")
		private Date dataComprovanteRenda;

		@MapAtributo(tamanho = 2)
		private String tipoComprovanteRenda;

		@MapAtributo(tamanho = 2)
		private String ocupacaoNova;

		@MapAtributo(tamanho = 14)
		private String cnpj;

		@MapAtributo(tamanho = 26, trim = false)
		private String filler;

		public Date getDataAdmissao() {
			return dataAdmissao;
		}

		public void setDataAdmissao(Date dataAdmissao) {
			this.dataAdmissao = dataAdmissao;
		}

		public String getEmpresa() {
			return empresa;
		}

		public void setEmpresa(String empresa) {
			this.empresa = empresa;
		}

		public Endereco getEndereco() {
			return endereco;
		}

		public void setEndereco(Endereco endereco) {
			this.endereco = endereco;
		}

		public TelefoneRamal getTelefone() {
			return telefone;
		}

		public void setTelefone(TelefoneRamal telefone) {
			this.telefone = telefone;
		}

		public Double getValorRendaLiquida() {
			return valorRendaLiquida;
		}
		
		public void setValorRendaLiquida(Double valorRendaLiquida) {
			this.valorRendaLiquida = valorRendaLiquida;
		}

		public String getCargo() {
			return cargo;
		}

		public void setCargo(String cargo) {
			this.cargo = cargo;
		}

		public String getProfissao() {
			return profissao;
		}

		public void setProfissao(String profissao) {
			this.profissao = profissao;
		}

		public Boolean isAposentado() {
			return aposentado;
		}

		public void setAposentado(Boolean aposentado) {
			this.aposentado = aposentado;
		}

		public Boolean isPensionista() {
			return pensionista;
		}

		public void setPensionista(Boolean pensionista) {
			this.pensionista = pensionista;
		}

		public String getUsoExclusivoLosango() {
			return usoExclusivoLosango;
		}

		public void setUsoExclusivoLosango(String usoExclusivoLosango) {
			this.usoExclusivoLosango = usoExclusivoLosango;
		}

		public String getOrgaoBeneficio() {
			return orgaoBeneficio;
		}

		public void setOrgaoBeneficio(String orgaoBeneficio) {
			this.orgaoBeneficio = orgaoBeneficio;
		}

		public String getNumeroBeneficio() {
			return numeroBeneficio;
		}

		public void setNumeroBeneficio(String numeroBeneficio) {
			this.numeroBeneficio = numeroBeneficio;
		}

		public Date getDataComprovanteRenda() {
			return dataComprovanteRenda;
		}

		public void setDataComprovanteRenda(Date dataComprovanteRenda) {
			this.dataComprovanteRenda = dataComprovanteRenda;
		}

		public String getTipoComprovanteRenda() {
			return tipoComprovanteRenda;
		}

		public void setTipoComprovanteRenda(String tipoComprovanteRenda) {
			this.tipoComprovanteRenda = tipoComprovanteRenda;
		}

		public String getOcupacaoNova() {
			return ocupacaoNova;
		}

		public void setOcupacaoNova(String ocupacaoNova) {
			this.ocupacaoNova = ocupacaoNova;
		}

		public String getCnpj() {
			return cnpj;
		}

		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}

		public String getFiller() {
			return filler;
		}

		public void setFiller(String filler) {
			this.filler = filler;
		}
	}
}
