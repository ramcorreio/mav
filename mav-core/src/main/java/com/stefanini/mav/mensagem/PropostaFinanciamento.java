package com.stefanini.mav.mensagem;

import java.util.Date;

import com.stefanini.mav.es.MapAtributo;
import com.stefanini.mav.es.MapBean;
import com.stefanini.mav.mensagem.Cabecalho.Fluxo;
import com.stefanini.mav.es.ConfiguracaoMensagem;

@ConfiguracaoMensagem(inicio = 83, sentido = Fluxo.ENTRADA)
public class PropostaFinanciamento extends MensagemBasica {

	private static final long serialVersionUID = 2703729128402664733L;

	@MapBean
	private DadoPessoal dadosPessoais;
	
	@MapBean
	private DadoProfissional dadosProfissionais;
	
	@MapBean
	private DadoConjuge dadosConjuge;
	
	@MapBean
	private DadoComplementar dadoComplementar;
	
	@MapAtributo(tamanho = 92)
	private String referenciasPessoais;
	
	@MapAtributo(tamanho = 92)
	private String referenciasComerciais;
	
	@MapBean
	private Banco referenciaBancaria;
	
	@MapBean
	private DadoOperacao dadosOperacao;

	@MapAtributo(tamanho = 2)
	private String codigoOfertaAderidaContaCorrente;
	
	@MapAtributo(tamanho = 2)
	private String codigoOfertaAderidaCdc;
	
	@MapAtributo(tamanho = 2)
	private String codigoPerfilOfertaAderidaCdc;
	
	@MapAtributo(tamanho = 13, trim = false)
	private String fillerPreScreening;
	
	@MapAtributo(tamanho = 35)
	private String nomeVendedor;
	
	@MapAtributo(tamanho = 35)
	private String nomeAgenteCorrespondente;
	
	@MapAtributo(tamanho = 11)
	private String cpfAgenteCorrespondente;
	
	@MapBean
	private BancoEp bancoEp;
	
	@MapAtributo
	private Boolean adesaoSeguro;
	
	@MapAtributo
	private String formaPagamentoAcessorio;
	
	@MapAtributo
	private Integer quantidadeSeguro;
	
	@MapBean
	private InfoSeguro seguroPremista;
	
	@MapBean
	private InfoSeguro seguroSorte;

	@MapAtributo(tamanho = 2)
	private Integer quantidadeNumeroSorte;
	
	@MapBean
	private Banco debitoConta;
	
	@MapBean
	private DadoCheque dadosCheque;
	
	@MapAtributo(tamanho = 2)
	private String circular3461BancoCentral;
	
	@MapAtributo(tamanho = 255)
	private String observacao;
	
	@MapBean
	private Indicador indicadores;

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
	
	public DadoConjuge getDadosConjuge() {
		return dadosConjuge;
	}
	
	public void setDadosConjuge(DadoConjuge dadosConjuge) {
		this.dadosConjuge = dadosConjuge;
	}
	
	public DadoComplementar getDadoComplementar() {
		return dadoComplementar;
	}
	
	public void setDadoComplementar(DadoComplementar dadoComplementar) {
		this.dadoComplementar = dadoComplementar;
	}
	
	public String getReferenciasPessoais() {
		return referenciasPessoais;
	}
	
	public void setReferenciasPessoais(String referenciasPessoais) {
		this.referenciasPessoais = referenciasPessoais;
	}
	
	public String getReferenciasComerciais() {
		return referenciasComerciais;
	}
	
	public void setReferenciasComerciais(String referenciasComerciais) {
		this.referenciasComerciais = referenciasComerciais;
	}
	
	public Banco getReferenciaBancaria() {
		return referenciaBancaria;
	}
	
	public void setReferenciaBancaria(Banco referenciaBancaria) {
		this.referenciaBancaria = referenciaBancaria;
	}
	
	public DadoOperacao getDadosOperacao() {
		return dadosOperacao;
	}
	
	public void setDadosOperacao(DadoOperacao dadosOperacao) {
		this.dadosOperacao = dadosOperacao;
	}
	
	public String getCodigoOfertaAderidaContaCorrente() {
		return codigoOfertaAderidaContaCorrente;
	}

	public void setCodigoOfertaAderidaContaCorrente(String codigoOfertaAderidaContaCorrente) {
		this.codigoOfertaAderidaContaCorrente = codigoOfertaAderidaContaCorrente;
	}

	public String getCodigoOfertaAderidaCdc() {
		return codigoOfertaAderidaCdc;
	}

	public void setCodigoOfertaAderidaCdc(String codigoOfertaAderidaCdc) {
		this.codigoOfertaAderidaCdc = codigoOfertaAderidaCdc;
	}

	public String getCodigoPerfilOfertaAderidaCdc() {
		return codigoPerfilOfertaAderidaCdc;
	}

	public void setCodigoPerfilOfertaAderidaCdc(String codigoPerfilOfertaAderidaCdc) {
		this.codigoPerfilOfertaAderidaCdc = codigoPerfilOfertaAderidaCdc;
	}

	public String getFillerPreScreening() {
		return fillerPreScreening;
	}

	public void setFillerPreScreening(String fillerPreScreening) {
		this.fillerPreScreening = fillerPreScreening;
	}
	
	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public String getNomeAgenteCorrespondente() {
		return nomeAgenteCorrespondente;
	}

	public void setNomeAgenteCorrespondente(String nomeAgenteCorrespondente) {
		this.nomeAgenteCorrespondente = nomeAgenteCorrespondente;
	}

	public String getCpfAgenteCorrespondente() {
		return cpfAgenteCorrespondente;
	}

	public void setCpfAgenteCorrespondente(String cpfAgenteCorrespondente) {
		this.cpfAgenteCorrespondente = cpfAgenteCorrespondente;
	}

	public BancoEp getBancoEp() {
		return bancoEp;
	}

	public void setBancoEp(BancoEp bancoEp) {
		this.bancoEp = bancoEp;
	}
	
	public Boolean isAdesaoSeguro() {
		return adesaoSeguro;
	}
	
	public void setAdesaoSeguro(Boolean adesaoSeguro) {
		this.adesaoSeguro = adesaoSeguro;
	}
	
	public String getFormaPagamentoAcessorio() {
		return formaPagamentoAcessorio;
	}
	
	public void setFormaPagamentoAcessorio(String formaPagamentoAcessorio) {
		this.formaPagamentoAcessorio = formaPagamentoAcessorio;
	}
	
	public Integer getQuantidadeSeguro() {
		return quantidadeSeguro;
	}
	
	public void setQuantidadeSeguro(Integer quantidadeSeguro) {
		this.quantidadeSeguro = quantidadeSeguro;
	}
	
	public InfoSeguro getSeguroPremista() {
		return seguroPremista;
	}

	public void setSeguroPremista(InfoSeguro seguroPremista) {
		this.seguroPremista = seguroPremista;
	}

	public InfoSeguro getSeguroSorte() {
		return seguroSorte;
	}

	public void setSeguroSorte(InfoSeguro seguroSorte) {
		this.seguroSorte = seguroSorte;
	}

	public Integer getQuantidadeNumeroSorte() {
		return quantidadeNumeroSorte;
	}

	public void setQuantidadeNumeroSorte(Integer quantidadeNumeroSorte) {
		this.quantidadeNumeroSorte = quantidadeNumeroSorte;
	}
	
	public Banco getDebitoConta() {
		return debitoConta;
	}
	
	public void setDebitoConta(Banco debitoConta) {
		this.debitoConta = debitoConta;
	}
	
	public DadoCheque getDadosCheque() {
		return dadosCheque;
	}
	
	public void setDadosCheque(DadoCheque dadosCheque) {
		this.dadosCheque = dadosCheque;
	}
	
	public String getCircular3461BancoCentral() {
		return circular3461BancoCentral;
	}
	
	public void setCircular3461BancoCentral(String circular3461BancoCentral) {
		this.circular3461BancoCentral = circular3461BancoCentral;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public Indicador getIndicadores() {
		return indicadores;
	}
	
	public void setIndicadores(Indicador indicadores) {
		this.indicadores = indicadores;
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
		
		@MapAtributo
		private String correspondencia;	
		
		@MapBean
		private DocumentoTp1 identificacao;
		
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
		private EnderecoTp1 enderecoResidencial;
		
		@MapBean
		private TelefoneRamal telefoneResidencial;
		
		@MapAtributo
		private Integer tipoTelefone;
		
		@MapAtributo
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
		private String filler;

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

		public DocumentoTp1 getIdentificacao() {
			return identificacao;
		}

		public void setIdentificacao(DocumentoTp1 identificacao) {
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

		public EnderecoTp1 getEnderecoResidencial() {
			return enderecoResidencial;
		}

		public void setEnderecoResidencial(EnderecoTp1 enderecoResidencial) {
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

		public String getFiller() {
			return filler;
		}
		
		public void setFiller(String filler) {
			this.filler = filler;
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
		private EnderecoTp1 endereco;
		
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

		public EnderecoTp1 getEndereco() {
			return endereco;
		}

		public void setEndereco(EnderecoTp1 endereco) {
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
	
	//0916 a 0945	Nome Do Cônjuge	30	A	Nome do Cônjuge do Cliente		X. Se conjuge compoe renda = 1 ou Estado Civil = 2 
	//0946 a 0985	Local de Nascimento	40	A			
	//0986 a 0993	Data de Nascimento	8	N	Data nascimento do cônjuge.		X. Se conjuge compoe renda = 1 
	//0994 a 1004	CPF	11	N	CPF do Cônjuge		X. Se conjuge compoe renda = 1 
	//1005 a 1014	Identidade / RG	10	A	Número da Identidade do Cônjuge		X. Se conjuge compoe renda = 1 
	//1015 a 1016	Tipo de Documento	2	A		Ver tabela de dominio TP  DOCUMENTO IDENTIDADE	X. Se conjuge compoe renda = 1 
	//1017 a 1021	Órgão Emissor	5	A	Órgão Emissor do Documento de Identidade do Cônjuge	Ver tabela de dominio Orgão Emissor	X. Se conjuge compoe renda = 1 
	//1022 a 1023	UF Órgão Emissor	2	A		Ver tabela de dominio UF	X. Se conjuge compoe renda = 1 
	//1024 a 1031	Data Emissão	8	N	Data de emissão do documento do Cônjuge		X. Se conjuge compoe renda = 1 
	//1032 a 1056	Empresa 	25	A	Empresa Em Que o Cônjuge Trabalha		X. Se conjuge compoe renda = 1 
	//1057 a 1064	Data  Admissão	8	N	Data da Admissão na Empresa		X. Se conjuge compoe renda = 1 
	//1065 a 1104	Logradouro	40	A	Logradouro do trabalho do conjuge		X. Se conjuge compoe renda = 1 
	//1105 a 1109	Numero	5	A	Numero do Logradouro		X. Se conjuge compoe renda = 1 
	//1110 a 1124	Complemento	15	A	Complemento do Logradouro		
	//1125 a 1144	Bairro	20	A	Bairro onde trabalha o cônjuge		X. Se conjuge compoe renda = 1 
	//1145 a 1164	Cidade	20	A	Cidade Onde Trabalha o cônjuge		X. Se conjuge compoe renda = 1 
	//1165 a 1166	UF	2	A	Abreviatura da Unidade Federativa 		X. Se conjuge compoe renda = 1 
	//1167 a 1174	CEP	8	N	CEP do endereço comercial do cônjuge		X. Se conjuge compoe renda = 1 
	//1175 a 1177	DDD	3	N	DDD da Cidade Onde Trabalha o cônjuge		X. Se conjuge compoe renda = 1 
	//1178 a 1186	Telefone	9	N	"Se o campo DDD estiver preenchido com 011 e o numero do telefone não iniciar 70, 75, 78 e 79 e for um numero de celular, o telefone deve ser iniciado com o numero ""9"", caso contrário deverá ser iniciado com o numero ""0"".
	//Exemplos:
	//1º) DDD=011 e numero = 8240-3043 ==> 98240-3043
	//2º) DDD=011 e numero = 7040-3043 ==> 07040-3043
	//3º) DDD=011 e numero = 3043-5322 ==> 03043-5322
	//4º) DDD=021 e numero = 8243-5322 ==> 08243-5322
	//5º) DDD=021 e numero = 3043-5322 ==> 03043-5322"		X. Se conjuge compoe renda = 1 
	//1187 a 1190	Ramal	4	N	Ramal do Trabalho do cônjuge		
	//1191 a 1210	Cargo	20	A	Cargo do Cônjuge	Ver tabela de Dominio Cargo	X. Se conjuge compoe renda = 1
	//1211 a 1230	Profissão	20	A	Profissão do Conjuge	Ver tabela de dominio Profissão	X. Se conjuge compoe renda = 1
	//1231 a 1231	Aposentado	1	A	"Aponta se o cliente é aposentado:
	//S - Sim; N - Não"	"S"  "N"	X. Se conjuge compoe renda = 1 
	//1232 a 1232	Pensionista	1	A	"Aponta se o cliente é Pensionista:
	//S - Sim; N - Não"	"S"  "N"	X. Se conjuge compoe renda = 1 
	//1233 a 1233	Uso exclusivo da Losango	1	A	Uso exclusivo da Losango		
	//1234 a 1244	Valor Renda Líquida 	11	N	Renda Líquida do Cônjuge (em R$)                                                  		X. Se conjuge compoe renda = 1 
	//1245 a 1314	Patrimônio	70	A			
	//1315 a 1315	Nacionalidade	1	N	Nacionalidade do Conjuge	"0-Brasileiro
	//1-Estrangeiro        "	X. Se conjuge compoe renda = 1
	//1316 a 1317	Código do País (informar se nacionalidade = estrangeiro)	2	A	Tabela de Paises	Ver tabela de Dominio Paises	X. Se conjuge compoe renda = 1
	//1318 a 1319	Código da UF da Naturalidade (informar se nacionalidade = brasileiro)	2	A	Tabela de UF	Ver tabela de dominio UF	X. Se conjuge compoe renda = 1
	//1320 a 1325	Data do Comprovante de Renda	6	A	Mes/Ano do comprovante de Renda apresentado pelo Conjuge	MMAAAA	X. Se conjuge compoe renda = 1
	//1326 a 1327	Tipo Comprovante de Renda	2	A		Ver tabela Dominio tipo C Renda	X. Se conjuge compoe renda = 1
	//1328 a 1329	Ocupação nova	2	A	Código da Profissão	Ver tabela Dominio Código da Profissão	X. Se conjuge compoe renda = 1
	//1330 a 1330	Sexo do Conjuge	1	A	M – Masculino     F – Feminino         	“M”   ”F”	X. Se conjuge compoe renda = 1 ou Estado Civil = 2 
	//1331 a 1344	CNPJ Conjuge	14	A			
	//1331 a 1364	Filler	20	A			X, se Empresario ou Proprietario
	public static class DadoConjuge {
		
		@MapAtributo(tamanho = 30) 
		private String nome;
		
		@MapAtributo(tamanho = 40) 
		private String localNascimento;
		
		@MapAtributo(tamanho = 8) 
		private Date dataNascimento;
		
		@MapAtributo(tamanho = 11) 
		private String cpf;
		
		@MapBean 
		private DocumentoTp2 identidade;
		
		@MapAtributo(tamanho = 25)
		private String empresa;
		
		@MapAtributo(tamanho = 8)
		private Date dataAdmissao;
		
		@MapBean
		private EnderecoTp1 endereco;
		
		@MapBean
		private TelefoneRamal telefone;

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
		
		@MapAtributo(tamanho = 11, scale = 2)
		private Double valorRendaLiquida;
		
		@MapAtributo(tamanho = 70)
		//TODO: rever a carga desse item
		private String patrimonio;
		
		@MapAtributo
		private Integer nacionalidade;
		
		@MapAtributo(tamanho = 2)
		private String codigoPais;
		
		@MapAtributo(tamanho = 2)
		private String codigoUfNaturalidade;
		
		@MapAtributo(tamanho = 6, formato = "MMyyyy")
		private Date dataComprovanteRenda;

		@MapAtributo(tamanho = 2)
		private String tipoComprovanteRenda;

		@MapAtributo(tamanho = 2)
		private String ocupacaoNova;
		
		@MapAtributo
		private String sexo;

		@MapAtributo(tamanho = 14)
		private String cnpj;

		@MapAtributo(tamanho = 20, trim = false)
		private String filler;

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

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public DocumentoTp2 getIdentidade() {
			return identidade;
		}

		public void setIdentidade(DocumentoTp2 identidade) {
			this.identidade = identidade;
		}

		public String getEmpresa() {
			return empresa;
		}

		public void setEmpresa(String empresa) {
			this.empresa = empresa;
		}

		public Date getDataAdmissao() {
			return dataAdmissao;
		}

		public void setDataAdmissao(Date dataAdmissao) {
			this.dataAdmissao = dataAdmissao;
		}

		public EnderecoTp1 getEndereco() {
			return endereco;
		}

		public void setEndereco(EnderecoTp1 endereco) {
			this.endereco = endereco;
		}

		public TelefoneRamal getTelefone() {
			return telefone;
		}

		public void setTelefone(TelefoneRamal telefone) {
			this.telefone = telefone;
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

		public Double getValorRendaLiquida() {
			return valorRendaLiquida;
		}

		public void setValorRendaLiquida(Double valorRendaLiquida) {
			this.valorRendaLiquida = valorRendaLiquida;
		}

		public String getPatrimonio() {
			return patrimonio;
		}

		public void setPatrimonio(String patrimonio) {
			this.patrimonio = patrimonio;
		}

		public Integer getNacionalidade() {
			return nacionalidade;
		}

		public void setNacionalidade(Integer nacionalidade) {
			this.nacionalidade = nacionalidade;
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
		
		public String getSexo() {
			return sexo;
		}
		
		public void setSexo(String sexo) {
			this.sexo = sexo;
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
	
	//1365 a 1366	Escolaridade	2	A	Codigo da Escolaridade	Ver tabela de dominio Escolaridade	
	//1367 a 1386	Formação	20	A	Formação		
	//1387 a 1387	Indicador Possui cartão	1	N	Indicador se possui cartão	0 - Não 1 - Sim	X
	//1388 a 1388	Indicador Possui veículo próprio	1	N	Indicador Possui veículo próprio	0 - Não 1 - Sim	X
	//1389 a 1398	Placa	10	A			
	//1399 a 1458	Renavam	60	A			
	//1459 a 1459	Indicador Possui veículo quitado	1	N	Indicador Possui veículo quitado	0 - Não 1 - Sim	X
	//1460 a 1460	Possui experiencia de crédito	1	N	Indicador Possui experiência	0 - Não 1 - Sim	X
	//1461 a 1480	Local da Experiência	20	A			X. Se Possui experiencia de crédito = 1
	//1481 a 1482	Plano da Experiência	2	N			X. Se Possui experiencia de crédito = 1
	//1483 a 1497	Valor da Prestação da Experiência	15	N			X. Se Possui experiencia de crédito = 1
	//1498 a 1503	Inicio da Experiência de Crédito	6	N	Inicio da Experiência MMAAAA		X. Se Possui experiencia de crédito = 1
	//1504 a 1543	Classificação do Cliente	40	A			
	//1544 a 1544	Indicador Possui Cartão Financeira	1	N	Indicador Possui Cartão Financeira	0 - Não 1 - Sim	X
	//1545 a 1545	Indicador Possui Conta Corrente	1	N	Indicador Possui Conta Corrente	0 - Não 1 - Sim	X
	//1546 a 1546	Indicador Possui dependente	1	N		0 - Não 1 - Sim	X
	//1547 a 1548	Quantidade de dependentes	2	N			X. Se Indicador Possui dependente = 1
	//1549 a 1563	Nome do cartão	15	A			
	//1564 a 1564	indicadorCapturarFoto	1	A	Indicador da captura da Foto do Cliente.	""" "" Documentos capturados com sucesso ou Lojista sem captura digital com a Losango
	//1 - Problemas Técnicos
	//2 - Cliente não autoriza
	//3 - Outros"	X
	//1565 a 1565	indicadorCapturarDocumento	1	A	Indicador da captura do Documento.	""" "" Documentos capturados com sucesso ou Lojista sem captura digital com a Losango
	//1 - Problemas Técnicos
	//2 - Cliente não autoriza
	//3 - Outros"	X
	//1566 a 1566	indicadorCapturarBiometria	1	A	Indicador da captura da Biometria	""" "" Documentos capturados com sucesso ou Lojista sem captura digital com a Losango
	//1 - Problemas Técnicos
	//2 - Cliente não autoriza
	//3 - Outros"	X
	//1567 a 1613	Filler	47	A
	public static class DadoComplementar {
		
		@MapAtributo(tamanho = 2)
		private String escolaridade;
		
		@MapAtributo(tamanho = 20)
		private String formacao;
		
		@MapAtributo
		private Boolean possuiCartao;
		
		@MapAtributo
		private Boolean possuiVeiculoProprio;
		
		@MapAtributo(tamanho = 10)
		private String placa;

		@MapAtributo(tamanho = 60)
		private String renavam;
		
		@MapAtributo
		private Boolean possuiVeiculoQuitado;
		
		@MapAtributo
		private Boolean possuiExperienciaCredito;
		
		@MapAtributo(tamanho = 20)
		private String localExperiencia;
		
		@MapAtributo(tamanho = 2)
		private Integer planoExperiencia;
		
		@MapAtributo(tamanho = 15, scale = 2)
		private Integer valorPrestacaoExperiencia;
		
		@MapAtributo(tamanho = 6, formato = "MMyyyy")
		private Date inicioExperienciaCredito;
		
		@MapAtributo(tamanho = 40)
		private String classificao;
		
		@MapAtributo
		private Boolean possuiCartaoFinanceira;
		
		@MapAtributo
		private Boolean possuiContaCorrente;
		
		@MapAtributo
		private Boolean possuiDependente;
		
		@MapAtributo(tamanho = 2)
		private Integer quantidadeDependentes;
		
		@MapAtributo(tamanho = 15)
		private String nomeCartao;
		
		@MapAtributo
		private Boolean capturarFoto;
		
		@MapAtributo
		private Boolean capturarDocumento;
		
		@MapAtributo
		private Boolean capturarBiometria;
		
		@MapAtributo(tamanho = 47, trim = false)
		private String filler;

		/**
		 * @return the escolaridade
		 */
		public String getEscolaridade() {
			return escolaridade;
		}

		/**
		 * @param escolaridade the escolaridade to set
		 */
		public void setEscolaridade(String escolaridade) {
			this.escolaridade = escolaridade;
		}

		/**
		 * @return the formacao
		 */
		public String getFormacao() {
			return formacao;
		}

		/**
		 * @param formacao the formacao to set
		 */
		public void setFormacao(String formacao) {
			this.formacao = formacao;
		}

		/**
		 * @return the possuiCartao
		 */
		public Boolean isPossuiCartao() {
			return possuiCartao;
		}

		/**
		 * @param possuiCartao the possuiCartao to set
		 */
		public void setPossuiCartao(Boolean possuiCartao) {
			this.possuiCartao = possuiCartao;
		}

		/**
		 * @return the possuiVeiculoProprio
		 */
		public Boolean isPossuiVeiculoProprio() {
			return possuiVeiculoProprio;
		}

		/**
		 * @param possuiVeiculoProprio the possuiVeiculoProprio to set
		 */
		public void setPossuiVeiculoProprio(Boolean possuiVeiculoProprio) {
			this.possuiVeiculoProprio = possuiVeiculoProprio;
		}

		/**
		 * @return the placa
		 */
		public String getPlaca() {
			return placa;
		}

		/**
		 * @param placa the placa to set
		 */
		public void setPlaca(String placa) {
			this.placa = placa;
		}

		/**
		 * @return the renavam
		 */
		public String getRenavam() {
			return renavam;
		}

		/**
		 * @param renavam the renavam to set
		 */
		public void setRenavam(String renavam) {
			this.renavam = renavam;
		}

		/**
		 * @return the possuiVeiculoQuitado
		 */
		public Boolean isPossuiVeiculoQuitado() {
			return possuiVeiculoQuitado;
		}

		/**
		 * @param possuiVeiculoQuitado the possuiVeiculoQuitado to set
		 */
		public void setPossuiVeiculoQuitado(Boolean possuiVeiculoQuitado) {
			this.possuiVeiculoQuitado = possuiVeiculoQuitado;
		}

		/**
		 * @return the possuiExperienciaCredito
		 */
		public Boolean isPossuiExperienciaCredito() {
			return possuiExperienciaCredito;
		}

		/**
		 * @param possuiExperienciaCredito the possuiExperienciaCredito to set
		 */
		public void setPossuiExperienciaCredito(Boolean possuiExperienciaCredito) {
			this.possuiExperienciaCredito = possuiExperienciaCredito;
		}

		/**
		 * @return the localExperiencia
		 */
		public String getLocalExperiencia() {
			return localExperiencia;
		}

		/**
		 * @param localExperiencia the localExperiencia to set
		 */
		public void setLocalExperiencia(String localExperiencia) {
			this.localExperiencia = localExperiencia;
		}

		/**
		 * @return the planoExperiencia
		 */
		public Integer getPlanoExperiencia() {
			return planoExperiencia;
		}

		/**
		 * @param planoExperiencia the planoExperiencia to set
		 */
		public void setPlanoExperiencia(Integer planoExperiencia) {
			this.planoExperiencia = planoExperiencia;
		}

		/**
		 * @return the valorPrestacaoExperiencia
		 */
		public Integer getValorPrestacaoExperiencia() {
			return valorPrestacaoExperiencia;
		}

		/**
		 * @param valorPrestacaoExperiencia the valorPrestacaoExperiencia to set
		 */
		public void setValorPrestacaoExperiencia(Integer valorPrestacaoExperiencia) {
			this.valorPrestacaoExperiencia = valorPrestacaoExperiencia;
		}

		/**
		 * @return the inicioExperienciaCredito
		 */
		public Date getInicioExperienciaCredito() {
			return inicioExperienciaCredito;
		}

		/**
		 * @param inicioExperienciaCredito the inicioExperienciaCredito to set
		 */
		public void setInicioExperienciaCredito(Date inicioExperienciaCredito) {
			this.inicioExperienciaCredito = inicioExperienciaCredito;
		}

		/**
		 * @return the classificao
		 */
		public String getClassificao() {
			return classificao;
		}

		/**
		 * @param classificao the classificao to set
		 */
		public void setClassificao(String classificao) {
			this.classificao = classificao;
		}

		/**
		 * @return the possuiCartaoFinanceira
		 */
		public Boolean isPossuiCartaoFinanceira() {
			return possuiCartaoFinanceira;
		}

		/**
		 * @param possuiCartaoFinanceira the possuiCartaoFinanceira to set
		 */
		public void setPossuiCartaoFinanceira(Boolean possuiCartaoFinanceira) {
			this.possuiCartaoFinanceira = possuiCartaoFinanceira;
		}

		/**
		 * @return the possuiContaCorrente
		 */
		public Boolean isPossuiContaCorrente() {
			return possuiContaCorrente;
		}

		/**
		 * @param possuiContaCorrente the possuiContaCorrente to set
		 */
		public void setPossuiContaCorrente(Boolean possuiContaCorrente) {
			this.possuiContaCorrente = possuiContaCorrente;
		}

		/**
		 * @return the possuiDependente
		 */
		public Boolean isPossuiDependente() {
			return possuiDependente;
		}

		/**
		 * @param possuiDependente the possuiDependente to set
		 */
		public void setPossuiDependente(Boolean possuiDependente) {
			this.possuiDependente = possuiDependente;
		}

		/**
		 * @return the quantidadeDependentes
		 */
		public Integer getQuantidadeDependentes() {
			return quantidadeDependentes;
		}

		/**
		 * @param quantidadeDependentes the quantidadeDependentes to set
		 */
		public void setQuantidadeDependentes(Integer quantidadeDependentes) {
			this.quantidadeDependentes = quantidadeDependentes;
		}

		/**
		 * @return the nomeCartao
		 */
		public String getNomeCartao() {
			return nomeCartao;
		}

		/**
		 * @param nomeCartao the nomeCartao to set
		 */
		public void setNomeCartao(String nomeCartao) {
			this.nomeCartao = nomeCartao;
		}

		/**
		 * @return the capturarFoto
		 */
		public Boolean isCapturarFoto() {
			return capturarFoto;
		}

		/**
		 * @param capturarFoto the capturarFoto to set
		 */
		public void setCapturarFoto(Boolean capturarFoto) {
			this.capturarFoto = capturarFoto;
		}

		/**
		 * @return the capturarDocumento
		 */
		public Boolean isCapturarDocumento() {
			return capturarDocumento;
		}

		/**
		 * @param capturarDocumento the capturarDocumento to set
		 */
		public void setCapturarDocumento(Boolean capturarDocumento) {
			this.capturarDocumento = capturarDocumento;
		}

		/**
		 * @return the capturarBiometria
		 */
		public Boolean isCapturarBiometria() {
			return capturarBiometria;
		}

		/**
		 * @param capturarBiometria the capturarBiometria to set
		 */
		public void setCapturarBiometria(Boolean capturarBiometria) {
			this.capturarBiometria = capturarBiometria;
		}

		/**
		 * @return the filler
		 */
		public String getFiller() {
			return filler;
		}

		/**
		 * @param filler the filler to set
		 */
		public void setFiller(String filler) {
			this.filler = filler;
		}
	}
	
	//1832 a 1839	Tabela de Financiamento	8	N	Identificação da tabela de financiamento  (COP’s) referente ao crédito solicitado, específica para o lojista ou Crédito Pessoal 		X
	//1840 a 1840	Sinal da Carência 	1	A	Sinal da carência  (+) - Positiva  (-) - Negativa	"+"  -  "-" 	X
	//1841 a 1842	Carência 	2	N	Quantidade de Dias Para Ajuste do Vencimento da Prestação		X
	//1843 a 1843	Forma de pagamento	1	N	"Indicação da Forma de Cobrança
	//0) Carnë
	//1) averbação em folha
	//2) Cheque Pré
	//3) Extrato Rotativo
	//4) Extrato Parcelado
	//5) Debito em Conta"	“0”  “1”  “2”   “3”  ”4”, "5"	X
	//1844 a 1851	Data da  Operação	8	N	Data da Realização da Operação		X
	//1852 a 1853	Produto (Tabela de Produto)	2	N	Informar o Produto		X
	//1854 a 1855	Prestações	2	N	Indicar O Nº de Parcelas do contrato		X
	//1856 a 1862	Taxa Mensal	7	N	Taxa de Juros Aplicada Ao Mês (2 inteiras e 5 decimais)		X
	//1863 a 1869	Taxa Anual	7	N	Taxa de Juros Aplicada Ao Ano (3 inteiras 4 decimais)		
	//1870 a 1884	Valor da Entrada (não é mais utilizado)	15	N	Valor da Entrada (não é mais utilizado)		
	//1885 a 1885	Tipo de Pagamento	1	N	0-Pré 1-Pós (Flag que indica se a negociação será efetuada com Pré fixado ou Pos fixado)	“0”  “1”	X
	//1886 a 1887	Top	2	N	Tipo de Operação		X
	//1888 a 1902	Valor Tac	15	N	Valor da TAC (em R$)		X
	//1903 a 1903	Pag_Tac	1	N	Flag que indica a forma de pagamento da TAC ( 0 -Financiada   1- A vista 2 - Descontada em (RO))	“0”   “1”    “2”	X
	//1904 a 1918	Valor  da Operação/Solicitado	15	N	Valor solicitado pelo cliente (em R$)		X
	//1919 a 1933	Valor Total do Financiamento	15	N	Valor Total do Financiamento (em R$).		X
	//1934 a 1948	Valor da Prestação 	15	N	Valor A Ser Pago Mensalmente Já Com Taxa de Juros (em R$).		X
	//1949 a 1956	Vencimento 1ª prestação	8	N	Data do primeiro  vencimento		X
	//1957 a 1981	Descrição do bem	25	A	Identificação da mercadoria financiada (obrigatório para TOP 31 e 34)		
	//1982 a 1982	Imp_Carne	1	A	Flag que indica que o lojista vai imprimir carnê na loja (0-Não   e     1-Sim)	“0”     “1”	X
	//1983 a 1997	Nº Pedido	15	A	Campo para o lojista associar o número de pedido, nota fiscal, etc.		
	//1998 a 2008	Nº do CD	11	A	Numeração gráfica pré impressa do comprovante de débito ( não é obrigatorio)		
	//2009 a 2019	CPF do Vendedor	11	A	Identificação do vendedor/atendente  responsável pela operação		X
	//2020 a 2033	Telefone	14	A	Telefone do Vendedor		X
	//2034 a 2034	Pre-Pago	1	A	"Indica a compra de telefone celular Pre-Pago
	//  0 - Default - Não
	//  1 - Pre     - Sim"	 '0' '1'	X
	//2035 a 2035	Leva na Hora	1	A	"Indica se o cliente levara a mercadoria na hora
	//  0 - Default - Não
	//  1 - leva    - Sim"	 '0' '1'	X
	//2036 a 2036	Beta-Gama	1	A	"Indicadore de Fraude
	//  0 - Default - Sem fraude
	//  2 - Beta    - Susp fraude
	//  1 - Gama    - Confirm fraude"	 '0' '1' '2'	X
	//2037 a 2046	Promotor	10	N	Código do Promotor		
	//2047 a 2047	Indicador aceita consulta ao sysbacen	1	A	Indica se o cliente permitiu a consulta ao sysbacen 0- Não(Default), 1 - Sim	 '0' '1'	X
	//2048 a 2054	CET Mensal (%)	7	N	Taxa Mensal do Custo efetivo Total (2 decimais)	"Preencher com o valor 
	//informado pelo Simulador ou zero caso o simulador não foi utilizado"	
	//2055 a 2061	CET Anual  (%)	7	N	Taxa Anual   do Custo efetivo Total (2 decimais)	"Preencher com o valor 
	//informado pelo Simulador ou zero caso o simulador não foi utilizado"	
	//2062 a 2068	IOF	7	N	Valor do IOF (2 casas decimais)	"Preencher com o valor 
	//informado pelo Simulador ou zero caso o simulador não foi utilizado"	
	//2069 a 2076	Data do Evento	8	N	Data da Entrega do Bem/Serviço		X. Se o produto for cessão
	//2077 a 2091	Valor da Entrada ao Lojista	15	N	Valor dado de entrada ao Lojista		
	//2092 a 2141	Filler	50	A
	public static class DadoOperacao {
		
		@MapAtributo(tamanho = 8)
		private Integer tabelaFinanciamento;
		
		@MapAtributo
		private String sinalCarencia;
		
		@MapAtributo(tamanho = 2)
		private Integer carencia;
		
		@MapAtributo
		private FormaPagamento formaPagamento;
		
		@MapAtributo(tamanho = 8)
		private Date dataOperacao;
		
		@MapAtributo(tamanho = 2)
		private Integer produto;
		
		@MapAtributo(tamanho = 2)
		private Integer prestacoes;
		
		@MapAtributo(tamanho = 7, scale = 5)
		private Double taxaMensal;
		
		@MapAtributo(tamanho = 7, scale = 4)
		private Double taxaAnual;
		
		@MapAtributo(tamanho = 15, scale = 2)
		private Double valorEntrada;
		
		@MapAtributo
		private Integer tipoPagamento;
		
		@MapAtributo(tamanho = 2)
		private Integer top;
		
		@MapAtributo(tamanho = 15)
		private Integer valorTac;
		
		@MapAtributo(tamanho = 1)
		private Integer pagTac;
		
		@MapAtributo(tamanho = 15)
		private Integer valorOperacaoSolicitado;
		
		@MapAtributo(tamanho = 15)
		private Integer valorTotalFinanciado;
		
		@MapAtributo(tamanho = 15)
		private Integer valorPrestacao;
		
		@MapAtributo(tamanho = 8)
		private Date vencimentoPrimeiraPrestacao;
		
		@MapAtributo(tamanho = 25)
		private String descricaoDoBem;
		
		@MapAtributo
		private Boolean imprimeCarne;
		
		@MapAtributo(tamanho = 15)
		private String numeroPedido;
		
		@MapAtributo(tamanho = 11)
		private String numeroCd;
		
		@MapAtributo(tamanho = 11)
		private String cpfVendedor;
		
		@MapAtributo(tamanho = 14)
		private String telefoneVendedor;
		
		@MapAtributo
		private Boolean prePago;
		
		@MapAtributo
		private Boolean levaNaHora;
		
		@MapAtributo
		private Integer betaGama;
		
		@MapAtributo(tamanho = 10)
		private String promtor;
		
		@MapAtributo
		private Boolean aceitaConsultaSysBacen;
		
		@MapAtributo(tamanho = 7, scale = 2)
		private Double cetMensal;
		
		@MapAtributo(tamanho = 7, scale = 2)
		private Double cetAnual;
		
		@MapAtributo(tamanho = 7, scale = 2)
		private Double valorIof;
 
		@MapAtributo(tamanho = 8)
		private Date dataEvento;
		
		@MapAtributo(tamanho = 15)
		private Integer valorEntradaLojista;
		
		@MapAtributo(tamanho = 50, trim = false)
		private String filler;

		public Integer getTabelaFinanciamento() {
			return tabelaFinanciamento;
		}

		public void setTabelaFinanciamento(Integer tabelaFinanciamento) {
			this.tabelaFinanciamento = tabelaFinanciamento;
		}

		public String getSinalCarencia() {
			return sinalCarencia;
		}

		public void setSinalCarencia(String sinalCarencia) {
			this.sinalCarencia = sinalCarencia;
		}

		public Integer getCarencia() {
			return carencia;
		}

		public void setCarencia(Integer carencia) {
			this.carencia = carencia;
		}

		public FormaPagamento getFormaPagamento() {
			return formaPagamento;
		}

		public void setFormaPagamento(FormaPagamento formaPagamento) {
			this.formaPagamento = formaPagamento;
		}

		public Date getDataOperacao() {
			return dataOperacao;
		}

		public void setDataOperacao(Date dataOperacao) {
			this.dataOperacao = dataOperacao;
		}

		public Integer getProduto() {
			return produto;
		}

		public void setProduto(Integer produto) {
			this.produto = produto;
		}

		public Integer getPrestacoes() {
			return prestacoes;
		}

		public void setPrestacoes(Integer prestacoes) {
			this.prestacoes = prestacoes;
		}

		public Double getTaxaMensal() {
			return taxaMensal;
		}

		public void setTaxaMensal(Double taxaMensal) {
			this.taxaMensal = taxaMensal;
		}

		public Double getTaxaAnual() {
			return taxaAnual;
		}

		public void setTaxaAnual(Double taxaAnual) {
			this.taxaAnual = taxaAnual;
		}

		public Double getValorEntrada() {
			return valorEntrada;
		}

		public void setValorEntrada(Double valorEntrada) {
			this.valorEntrada = valorEntrada;
		}

		public Integer getTipoPagamento() {
			return tipoPagamento;
		}

		public void setTipoPagamento(Integer tipoPagamento) {
			this.tipoPagamento = tipoPagamento;
		}

		public Integer getTop() {
			return top;
		}

		public void setTop(Integer top) {
			this.top = top;
		}

		public Integer getValorTac() {
			return valorTac;
		}

		public void setValorTac(Integer valorTac) {
			this.valorTac = valorTac;
		}

		public Integer getPagTac() {
			return pagTac;
		}

		public void setPagTac(Integer pagTac) {
			this.pagTac = pagTac;
		}

		public Integer getValorOperacaoSolicitado() {
			return valorOperacaoSolicitado;
		}

		public void setValorOperacaoSolicitado(Integer valorOperacaoSolicitado) {
			this.valorOperacaoSolicitado = valorOperacaoSolicitado;
		}

		public Integer getValorTotalFinanciado() {
			return valorTotalFinanciado;
		}

		public void setValorTotalFinanciado(Integer valorTotalFinanciado) {
			this.valorTotalFinanciado = valorTotalFinanciado;
		}

		public Integer getValorPrestacao() {
			return valorPrestacao;
		}

		public void setValorPrestacao(Integer valorPrestacao) {
			this.valorPrestacao = valorPrestacao;
		}

		public Date getVencimentoPrimeiraPrestacao() {
			return vencimentoPrimeiraPrestacao;
		}

		public void setVencimentoPrimeiraPrestacao(Date vencimentoPrimeiraPrestacao) {
			this.vencimentoPrimeiraPrestacao = vencimentoPrimeiraPrestacao;
		}

		public String getDescricaoDoBem() {
			return descricaoDoBem;
		}

		public void setDescricaoDoBem(String descricaoDoBem) {
			this.descricaoDoBem = descricaoDoBem;
		}

		public Boolean isImprimeCarne() {
			return imprimeCarne;
		}

		public void setImprimeCarne(Boolean imprimeCarne) {
			this.imprimeCarne = imprimeCarne;
		}

		public String getNumeroPedido() {
			return numeroPedido;
		}

		public void setNumeroPedido(String numeroPedido) {
			this.numeroPedido = numeroPedido;
		}

		public String getNumeroCd() {
			return numeroCd;
		}

		public void setNumeroCd(String numeroCd) {
			this.numeroCd = numeroCd;
		}

		public String getCpfVendedor() {
			return cpfVendedor;
		}

		public void setCpfVendedor(String cpfVendedor) {
			this.cpfVendedor = cpfVendedor;
		}

		public String getTelefoneVendedor() {
			return telefoneVendedor;
		}

		public void setTelefoneVendedor(String telefoneVendedor) {
			this.telefoneVendedor = telefoneVendedor;
		}

		public Boolean isPrePago() {
			return prePago;
		}

		public void setPrePago(Boolean prePago) {
			this.prePago = prePago;
		}

		public Boolean isLevaNaHora() {
			return levaNaHora;
		}

		public void setLevaNaHora(Boolean levaNaHora) {
			this.levaNaHora = levaNaHora;
		}

		public Integer getBetaGama() {
			return betaGama;
		}

		public void setBetaGama(Integer betaGama) {
			this.betaGama = betaGama;
		}

		public String getPromtor() {
			return promtor;
		}

		public void setPromtor(String promtor) {
			this.promtor = promtor;
		}

		public Boolean isAceitaConsultaSysBacen() {
			return aceitaConsultaSysBacen;
		}

		public void setAceitaConsultaSysBacen(Boolean aceitaConsultaSysBacen) {
			this.aceitaConsultaSysBacen = aceitaConsultaSysBacen;
		}

		public Double getCetMensal() {
			return cetMensal;
		}

		public void setCetMensal(Double cetMensal) {
			this.cetMensal = cetMensal;
		}

		public Double getCetAnual() {
			return cetAnual;
		}

		public void setCetAnual(Double cetAnual) {
			this.cetAnual = cetAnual;
		}

		public Double getValorIof() {
			return valorIof;
		}

		public void setValorIof(Double valorIof) {
			this.valorIof = valorIof;
		}

		public Date getDataEvento() {
			return dataEvento;
		}

		public void setDataEvento(Date dataEvento) {
			this.dataEvento = dataEvento;
		}

		public Integer getValorEntradaLojista() {
			return valorEntradaLojista;
		}

		public void setValorEntradaLojista(Integer valorEntradaLojista) {
			this.valorEntradaLojista = valorEntradaLojista;
		}

		public String getFiller() {
			return filler;
		}

		public void setFiller(String filler) {
			this.filler = filler;
		}
	}
	
//	2352 a 2354	CÓDIGO DO BANCO dos cheques	3	N	Código do Banco da primeira faixa de cheques para as operações de cheque-pré	Se produto igual a "2"	X
//	2355 a 2358	AGÊNCIA  DE DESTINO dos cheques	4	N	Código da Agência Bancária da primeira faixa de cheques para as operações de cheque-pré	Se produto igual a "2"	X
//	2359 a 2359	DV Agencia dos cheques	1	A			
//	2360 a 2372	Codigo da Conta	13	N			X
//	2373 a 2374	DV da Conta	2	A			X
//	2375 a 2380	NÚMERO DO CHEQUE do Primeiro Cheque da 1a. Faixa de Cheques	6	N	Número do primeiro cheque da primeira faixa de cheques para as operações de cheque-pré	Se produto igual a "2"	X
//	2381 a 2386	NÚMERO DO CHEQUE do Último Cheque da 1a. Faixa de Cheques	6	N	Número do último cheque da primeira faixa de cheques para as operações de cheque-pré	Se produto igual a "2"	X
//	2387 a 2392	NÚMERO DO CHEQUE do Primeiro Cheque da 2a. Faixa de Cheques	6	N	Número do primeiro cheque da segunda faixa de cheques para as operações de cheque-pré		
//	2393 a 2398	NÚMERO DO CHEQUE do Último Cheque da 2a. Faixa de Cheques	6	N	Número do último cheque da segunda faixa de cheques para as operações de cheque-pré		
//	2399 a 2406	Data da abertura da conta corrente	8	N	Data da abertura da conta corrente	Se produto igual a "2"	X
//	2407 a 2454	Filler	48	A
	public static class DadoCheque {
		
		@MapAtributo(tamanho = 3)
		private Integer codigoBanco;
		
		@MapAtributo(tamanho = 4)
		private Integer agenciaDestino;
		
		@MapAtributo
		private String dvAgenciaDestino;
		
		@MapAtributo(tamanho = 13)
		private Integer codigoConta;
		
		@MapAtributo(tamanho = 2)
		private String dvConta;
		
		@MapAtributo(tamanho = 6)
		private Integer numeroPrimeiroChequeFaixa1;
		
		@MapAtributo(tamanho = 6)
		private Integer numeroUltimoChequeFaixa1;
		
		@MapAtributo(tamanho = 6)
		private Integer numeroPrimeiroChequeFaixa2;
		
		@MapAtributo(tamanho = 6)
		private Integer numeroUltimoChequeFaixa2;
		
		@MapAtributo(tamanho = 8)
		private Date dataAberturaConta;
		
		@MapAtributo(tamanho = 48, trim = false)
		private String filler;

		public Integer getCodigoBanco() {
			return codigoBanco;
		}

		public void setCodigoBanco(Integer codigoBanco) {
			this.codigoBanco = codigoBanco;
		}

		public Integer getAgenciaDestino() {
			return agenciaDestino;
		}

		public void setAgenciaDestino(Integer agenciaDestino) {
			this.agenciaDestino = agenciaDestino;
		}

		public String getDvAgenciaDestino() {
			return dvAgenciaDestino;
		}

		public void setDvAgenciaDestino(String dvAgenciaDestino) {
			this.dvAgenciaDestino = dvAgenciaDestino;
		}

		public Integer getCodigoConta() {
			return codigoConta;
		}

		public void setCodigoConta(Integer codigoConta) {
			this.codigoConta = codigoConta;
		}

		public String getDvConta() {
			return dvConta;
		}

		public void setDvConta(String dvConta) {
			this.dvConta = dvConta;
		}

		public Integer getNumeroPrimeiroChequeFaixa1() {
			return numeroPrimeiroChequeFaixa1;
		}

		public void setNumeroPrimeiroChequeFaixa1(Integer numeroPrimeiroChequeFaixa1) {
			this.numeroPrimeiroChequeFaixa1 = numeroPrimeiroChequeFaixa1;
		}

		public Integer getNumeroUltimoChequeFaixa1() {
			return numeroUltimoChequeFaixa1;
		}

		public void setNumeroUltimoChequeFaixa1(Integer numeroUltimoChequeFaixa1) {
			this.numeroUltimoChequeFaixa1 = numeroUltimoChequeFaixa1;
		}

		public Integer getNumeroPrimeiroChequeFaixa2() {
			return numeroPrimeiroChequeFaixa2;
		}

		public void setNumeroPrimeiroChequeFaixa2(Integer numeroPrimeiroChequeFaixa2) {
			this.numeroPrimeiroChequeFaixa2 = numeroPrimeiroChequeFaixa2;
		}

		public Integer getNumeroUltimoChequeFaixa2() {
			return numeroUltimoChequeFaixa2;
		}

		public void setNumeroUltimoChequeFaixa2(Integer numeroUltimoChequeFaixa2) {
			this.numeroUltimoChequeFaixa2 = numeroUltimoChequeFaixa2;
		}

		public Date getDataAberturaConta() {
			return dataAberturaConta;
		}

		public void setDataAberturaConta(Date dataAberturaConta) {
			this.dataAberturaConta = dataAberturaConta;
		}

		public String getFiller() {
			return filler;
		}

		public void setFiller(String filler) {
			this.filler = filler;
		}
	}
}
