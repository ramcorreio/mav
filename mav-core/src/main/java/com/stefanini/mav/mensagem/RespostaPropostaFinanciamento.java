package com.stefanini.mav.mensagem;

import java.util.Date;
import java.util.List;

import com.stefanini.mav.es.ConfiguracaoMensagem;
import com.stefanini.mav.es.MapAtributo;
import com.stefanini.mav.es.MapBean;
import com.stefanini.mav.es.MapLista;
import com.stefanini.mav.mensagem.Cabecalho.Fluxo;

@ConfiguracaoMensagem(inicio = 83, sentido = Fluxo.SAIDA)
public class RespostaPropostaFinanciamento extends MensagemBasica {

	private static final long serialVersionUID = 2703729128402664733L;
	
	@MapAtributo
	private String liberacaoCessao;
	
	@MapAtributo(tamanho = 255)
	private String restricao;
	
	@MapAtributo(tamanho = 7)
	private Integer codAutorizador;
	
	@MapAtributo(tamanho = 8)
	private Date dataAutorizacao;
	
	@MapAtributo(tamanho = 50, trim = false)
	private String fillerRespostaProposta;
	
	@MapAtributo(tamanho = 2)
	private Integer prestacoes;
	
	@MapAtributo(tamanho = 15)
	private Integer valorPrestacao;
	
	@MapBean
	private DadoCarne dadosImpressao;
	
	@MapBean
	private DadoCliente dadosCliente;
	
	@MapBean
	private DadoConjuge dadosConjuge;
	
	@MapLista(maxSize = 2, bean = @MapBean)
	private List<Referencia> referenciasPessoais;
	
	@MapLista(maxSize = 2, bean = @MapBean)
	private List<Referencia> referenciasComerciais;
	
	@MapBean
	private Banco referenciaBancaria;
	
	@MapBean
	private DadoComplementar dadosComplementares;
	
	@MapBean
	private DadoOperacao dadosOperacao;
	
	@MapAtributo(tamanho = 80, trim = false)
	private String fillerPerfilOferta;
	
	@MapAtributo
	private String indicadorCapturarFoto;
	
	@MapAtributo
	private String indicadorCapturarDocumento;
	
	@MapAtributo
	private String indicadorCapturarBiometria; 
	
	@MapAtributo(tamanho = 17, trim = false)
	private String fillerCapturaDigital;
	
	@MapAtributo
	private Boolean adesaoSeguro;
	
	@MapAtributo
	private String formaPagamentoAcessorio;
	
	@MapAtributo
	private Integer quantidadeSeguro;
	
	@MapBean
	private DadoSeguroPremista dadosSeguroPremista;
	
	public RespostaPropostaFinanciamento(String id, Cabecalho cabecalho) {
		super(id, cabecalho);
	}

	public String getLiberacaoCessao() {
		return liberacaoCessao;
	}

	public void setLiberacaoCessao(String liberacaoCessao) {
		this.liberacaoCessao = liberacaoCessao;
	}

	public String getRestricao() {
		return restricao;
	}

	public void setRestricao(String restricao) {
		this.restricao = restricao;
	}

	public Integer getCodAutorizador() {
		return codAutorizador;
	}

	public void setCodAutorizador(Integer codAutorizador) {
		this.codAutorizador = codAutorizador;
	}

	public Date getDataAutorizacao() {
		return dataAutorizacao;
	}

	public void setDataAutorizacao(Date dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}

	public String getFiller() {
		return fillerRespostaProposta;
	}

	public void setFillerRespostaProposta(String fillerRespostaProposta) {
		this.fillerRespostaProposta = fillerRespostaProposta;
	}
	
	public Integer getPrestacoes() {
		return prestacoes;
	}
	
	public void setPrestacoes(Integer prestacoes) {
		this.prestacoes = prestacoes;
	}
	
	public Integer getValorPrestacao() {
		return valorPrestacao;
	}
	
	public void setValorPrestacao(Integer valorPrestacao) {
		this.valorPrestacao = valorPrestacao;
	}
	
	public DadoCarne getDadosImpressao() {
		return dadosImpressao;
	}
	
	public void setDadosImpressao(DadoCarne dadosImpressao) {
		this.dadosImpressao = dadosImpressao;
	}
	
	public DadoCliente getDadosCliente() {
		return dadosCliente;
	}
	
	public void setDadosCliente(DadoCliente dadosCliente) {
		this.dadosCliente = dadosCliente;
	}
	
	public DadoConjuge getDadosConjuge() {
		return dadosConjuge;
	}
	
	public void setDadosConjuge(DadoConjuge dadosConjuge) {
		this.dadosConjuge = dadosConjuge;
	}
	
	public List<Referencia> getReferenciasPessoais() {
		return referenciasPessoais;
	}
	
	public void setReferenciasPessoais(List<Referencia> referenciasPessoais) {
		this.referenciasPessoais = referenciasPessoais;
	}
	
	public List<Referencia> getReferenciasComerciais() {
		return referenciasComerciais;
	}
	
	public void setReferenciasComerciais(List<Referencia> referenciasComerciais) {
		this.referenciasComerciais = referenciasComerciais;
	}
	
	public Banco getReferenciaBancaria() {
		return referenciaBancaria;
	}
	
	public void setReferenciaBancaria(Banco referenciaBancaria) {
		this.referenciaBancaria = referenciaBancaria;
	}
	
	public DadoComplementar getDadosComplementares() {
		return dadosComplementares;
	}
	
	public void setDadosComplementares(DadoComplementar dadosComplementares) {
		this.dadosComplementares = dadosComplementares;
	}
	
	public DadoOperacao getDadosOperacao() {
		return dadosOperacao;
	}
	
	public void setDadosOperacao(DadoOperacao dadosOperacao) {
		this.dadosOperacao = dadosOperacao;
	}
	
	public String getFillerPerfilOferta() {
		return fillerPerfilOferta;
	}

	public void setFillerPerfilOferta(String fillerPerfilOferta) {
		this.fillerPerfilOferta = fillerPerfilOferta;
	}

	public String getIndicadorCapturarFoto() {
		return indicadorCapturarFoto;
	}

	public void setIndicadorCapturarFoto(String indicadorCapturarFoto) {
		this.indicadorCapturarFoto = indicadorCapturarFoto;
	}

	public String getIndicadorCapturarDocumento() {
		return indicadorCapturarDocumento;
	}

	public void setIndicadorCapturarDocumento(String indicadorCapturarDocumento) {
		this.indicadorCapturarDocumento = indicadorCapturarDocumento;
	}

	public String getIndicadorCapturarBiometria() {
		return indicadorCapturarBiometria;
	}

	public void setIndicadorCapturarBiometria(String indicadorCapturarBiometria) {
		this.indicadorCapturarBiometria = indicadorCapturarBiometria;
	}

	public String getFillerCapturaDigital() {
		return fillerCapturaDigital;
	}

	public void setFillerCapturaDigital(String fillerCapturaDigital) {
		this.fillerCapturaDigital = fillerCapturaDigital;
	}

	public Boolean getAdesaoSeguro() {
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

	public DadoSeguroPremista getDadosSeguroPremista() {
		return dadosSeguroPremista;
	}

	public void setDadosSeguroPremista(DadoSeguroPremista dadosSeguroPremista) {
		this.dadosSeguroPremista = dadosSeguroPremista;
	}

	public String getFillerRespostaProposta() {
		return fillerRespostaProposta;
	}



	//0422 a 0451	Nome_Cedente	30	A	Nome do Cedente 	
	//0452 a 0453	Especie_Doc	2	A	Especie do Documento	
	//0454 a 0454	Aceite	1	A	Identificação do aceite Default=N	
	//0455 a 0457	CIP	3	A	CIP	
	//0458 a 0460	Carteira	3	A	Tipo de cobrança	
	//0461 a 0465	Especie	5	A	Tipo de Moeda	
	//0466 a 0480	Quantidade	15	N	Quantidade relacionada a especie de moeda (cinco (5) decimais)	
	//0481 a 0495	Valor_Mora_Dia	15	N	Valor do juros de mora expresso em quantidade de moeda (2) decimais)	
	//0496 a 0510	Val_A_Pagar	15	N	Valor da parcela a pagar com os encargos	
	//0511 a 0513	Banco	3	N	Codigo do Banco	
	//0514 a 0517	Agencia_Cedente	4	N	Codigo da Agencia	
	//0518 a 0518	Agencia_Digito_Cedente	1	A	Digito verificador da Agencia	
	//0519 a 0525	Codigo_Cedente	7	N	Número da conta do cedente	
	//0526 a 0526	Digito_Cedente	1	A	Digito verificador da C.C. do cedente	
	//0527 a 0606	Msg_01	80	A	Campo de Instrução	
	//0607 a 0686	Msg_02	80	A	Campo de Instrução	
	//0687 a 0766	Msg_03	80	A	Campo de Instrução	
	//0767 a 0846	Msg_04	0080	A	Campo de Instrução	
	//0847 a 0926	Msg_05	0080	A	Campo de Instrução	
	//0927 a 1006	Msg_06	0080	A	Campo de Instrução	
	//1007 a 1086	Filler	80	A
	public static class DadoCarne {
		
		@MapAtributo(tamanho = 30)
		private String nomeCedente;
		
		@MapAtributo(tamanho = 2)
		private String especieDocumento;
		
		@MapAtributo(comparador = "S")
		private Boolean aceite;
		
		@MapAtributo(tamanho = 3)
		private String cip;
		
		@MapAtributo(tamanho = 3)
		private String carteira;
		
		@MapAtributo(tamanho = 5)
		private String especie;	
		
		@MapAtributo(tamanho = 15, scale = 5)
		private Double quantidade;
		
		@MapAtributo(tamanho = 15, scale = 2)
		private Double valorMoraDia;
		
		@MapAtributo(tamanho = 15)
		private Integer valorPagar;	

		@MapAtributo(tamanho = 3)
		private Integer banco;
		
		@MapAtributo(tamanho = 4)
		private Integer agenciaCedente;
		
		@MapAtributo
		private String dvAgenciaCedente;
		
		@MapAtributo(tamanho = 7)
		private Integer codigoCedente;
		
		@MapAtributo
		private String digitoCedente;
			
		@MapLista(maxSize = 6, attr = @MapAtributo(tamanho = 80))
		private List<String> mensagens;
		
		@MapAtributo(tamanho = 80, trim = false)
		private String filler;

		public String getNomeCedente() {
			return nomeCedente;
		}

		public void setNomeCedente(String nomeCedente) {
			this.nomeCedente = nomeCedente;
		}

		public String getEspecieDocumento() {
			return especieDocumento;
		}

		public void setEspecieDocumento(String especieDocumento) {
			this.especieDocumento = especieDocumento;
		}

		public Boolean isAceite() {
			return aceite;
		}

		public void setAceite(Boolean aceite) {
			this.aceite = aceite;
		}

		public String getCip() {
			return cip;
		}

		public void setCip(String cip) {
			this.cip = cip;
		}

		public String getCarteira() {
			return carteira;
		}

		public void setCarteira(String carteira) {
			this.carteira = carteira;
		}

		public String getEspecie() {
			return especie;
		}

		public void setEspecie(String especie) {
			this.especie = especie;
		}

		public Double getQuantidade() {
			return quantidade;
		}

		public void setQuantidade(Double quantidade) {
			this.quantidade = quantidade;
		}

		public Double getValorMoraDia() {
			return valorMoraDia;
		}

		public void setValorMoraDia(Double valorMoraDia) {
			this.valorMoraDia = valorMoraDia;
		}

		public Integer getValorPagar() {
			return valorPagar;
		}

		public void setValorPagar(Integer valorPagar) {
			this.valorPagar = valorPagar;
		}

		public Integer getBanco() {
			return banco;
		}

		public void setBanco(Integer banco) {
			this.banco = banco;
		}

		public Integer getAgenciaCedente() {
			return agenciaCedente;
		}

		public void setAgenciaCedente(Integer agenciaCedente) {
			this.agenciaCedente = agenciaCedente;
		}

		public String getDvAgenciaCedente() {
			return dvAgenciaCedente;
		}

		public void setDvAgenciaCedente(String dvAgenciaCedente) {
			this.dvAgenciaCedente = dvAgenciaCedente;
		}

		public Integer getCodigoCedente() {
			return codigoCedente;
		}

		public void setCodigoCedente(Integer codigoCedente) {
			this.codigoCedente = codigoCedente;
		}

		public String getDigitoCedente() {
			return digitoCedente;
		}

		public void setDigitoCedente(String digitoCedente) {
			this.digitoCedente = digitoCedente;
		}

		public List<String> getMensagens() {
			return mensagens;
		}

		public void setMensagens(List<String> mensagens) {
			this.mensagens = mensagens;
		}

		public String getFiller() {
			return filler;
		}

		public void setFiller(String filler) {
			this.filler = filler;
		}
	}

	
	//1087 a 1116	Nome	30	A	Nome do cliente	
	//1117 a 1117	Tipo de personalidade do CPF	1	A	T = Tipo de Pessoa (F – Fisica).	“F”
	//1118 a 1131	CPF 	14	A	CPF / CGC do cliente  	
	//1132 a 1132	Correspondência	1	N	1 – Residencial      2 - comercial (Identificação da Localização do recebimento do documento	“1”  “ 2”
	//1133 a 1147	Identidade	15	A	Numero do documento de identidade do cliente	
	//1148 a 1149	Tipo de Documento	2	A	"01 - Carteira Identidade
	//02 - Carteira Estrangeiro
	//03 - Passaporte 
	//04 - Carteira Identidade profissional
	//05 - Carteira militar
	//06 - Carteira trabalho e Previdência Social
	//07 - carteira identidade indigena
	//08 - carteira ministério público
	//09 - carteira nacional habilitação
	//10 - cart. identidade serv. publico federal
	//11 - numero identificação trabalhador
	//12 - numero beneficiario
	//13 - certidao nascimento
	//14 - identidade estrangeiro - merc"	
	//1150 a 1154	Órgão Emissor	5	A	Órgão emissor do documento de identidade do cliente	Ver tabela de dominio
	//1155 a 1156	UF Órgão Emissor	2	A		
	//1157 a 1164	Data Emissão	8	N	Data de emissão da identidade	
	//1165 a 1165	Conjuge C_Renda	1	N	 0 - Não     1 - Sim   (Flag que indica que conjuge compõe renda) Null - Nenhum (qualquer caso que não casado)	“0”    “1”
	//1166 a 1205	Local de Nascimento	40	A		
	//1206 a 1213	Data de Nascimento	8	N	Data de nascimento do cliente	
	//1214 a 1214	Sexo	1	A	M – Masculino     F – Feminino         	“M”   ”F”
	//1215 a 1215	Nacionalidade	1	N	"Nacionalidade do Cliente
	//0-Brasileiro
	//1-Estrangeiro        "	“0”   “1”
	//1216 a 1230	Naturalidade	15	A	Naturalidade do cliente	
	//1231 a 1260	Nome da Mãe	30	A	Nome da Mãe do Cliente 	
	//1261 a 1290	Nome do Pai	30	A	Nome do Pai do Cliente 	
	//1291 a 1295	Carteira Profissional	5	N	Número da Carteira Profissional do Cliente (PC)	
	//1296 a 1300	Série	5	A	Número de Série da Carteira Profissional do cliente (PC)	
	//1301 a 1301	Estado Civil	1	N	ver tabela de dominio	
	//1302 a 1341	Logradouro	40	A	Dados da residência do cliente	
	//1342 a 1346	Numero	5	A		
	//1347 a 1361	Complemento	15	A		
	//1362 a 1376	Bairro	15	A	Bairro endereço residencial	
	//1377 a 1391	Cidade	15	A	Cidade endereço residencial	
	//1392 a 1393	UF	2	A	Abreviação do Estado onde o cliente reside	
	//1394 a 1401	CEP	8	N	CEP endereço residencial	
	//1402 a 1404	DDD	3	N	DDD telefone residencial	
	//1405 a 1413	Telefone	9	N	Telefone residencial para contato	
	//1414 a 1417	Ramal	4	N	Ramal do telefone residencial do cliente	
	//1418 a 1418	Tipo Telefone	1	N	ver tabela de dominio	
	//1419 a 1419	Tipo Residencia	1	N	ver tabela de dominio	
	//1420 a 1427	Data Admissão 	8	N	Dia Mês e ano de Admissão na Empresa          	
	//1428 a 1457	Empresa	30	A	Empresa Em Que Trabalha o Cliente                              	
	//1458 a 1497	Logradouro	40	A	Endereço onde Trabalha o Cliente	
	//1498 a 1502	Numero	5	A		
	//1503 a 1517	Complemento	15	A		
	//1518 a 1532	Bairro	15	A	Bairro onde Trabalha o Cliente	
	//1533 a 1547	Cidade	15	A	Cidade onde Trabalha o Cliente	
	//1548 a 1549	UF	2	A	Unidade Federativa onde Trabalha o Cliente	
	//1550 a 1557	CEP	8	N	CEP onde trabalha o cliente	
	//1558 a 1560	DDD	3	N	DDD da Cidade Onde Trabalha o Cliente	
	//1561 a 1569	Telefone	9	N	Telefone do Trabalho do Cliente	
	//1570 a 1573	Ramal	4	N	Ramal do Trabalho do Cliente	
	//1574 a 1584	Valor Renda Líquida 	11	N	Renda Líquida do Cliente (em R$)                                              	
	//1585 a 1604	Cargo	20	A	Cargo do Cliente	
	//1605 a 1624	Profissão	20	A	Profissão do Cliente	
	//1625 a 1625	Aposentado	1	A	"Aponta se o cliente é aposentado:
	//S - Sim; N - Não"	"S"  "N"
	//1626 a 1626	Pensionista	1	A	"Aponta se o cliente é Pensionista:
	//S - Sim; N - Não"	"S"  "N"
	//1627 a 1627	Uso exclusivo da Losango	1	A	Uso exclusivo da Losango	
	//1628 a 1633	Reside desde	6	N	MMAAAA	
	//1634 a 1636	DDD Celular	3	N		
	//1637 a 1645	Telefone Celular	9	N		
	//1646 a 1705	Email	60	A		
	//1706 a 1707	Orgão Beneficio	2	A		
	//1708 a 1727	Número do benefício	20	A		
	//1728 a 1728	Cliente Possui Patrimonio?	1	N		
	//1729 a 1729	Tipo de Patrimonio 1	1	N		
	//1730 a 1734	Patrimonio 1	5	A		
	//1735 a 1745	Valor do Patrimonio 1	11	N		
	//1746 a 1746	Tipo de Patrimonio 2	1	N		
	//1747 a 1751	Patrimonio 2	5	A		
	//1752 a 1762	Valor do Patrimonio 2	11	N		
	//1763 a 1763	Tipo de Patrimonio 3	1	N		
	//1764 a 1768	Patrimonio 3	5	A		
	//1769 a 1779	Valor do Patrimonio 3	11	N		
	//1780 a 1780	Tipo de Patrimonio 4	1	N		
	//1781 a 1785	Patrimonio 4	5	A		
	//1786 a 1796	Valor do Patrimonio 4	11	N		
	//1797 a 1797	Filler	1	A		
	//1798 a 1799	Código do País (informar se nacionalidade = estrangeiro)	2	A	Tabela de Paises	
	//1800 a 1801	Código da UF da Naturalidade (informar se nacionalidade = brasileiro)	2	A	Tabela de UF	
	//1802 a 1807	Mes/Ano Comprovante de Renda	6	A		
	//1808 a 1809	Tipo Comprovante de Renda	2	A		
	//1810 a 1811	Ocupação nova	2	A	Código da Profissão	
	//1812 a 1819	Data de Vencimento do Documento de identificação	8	A		
	//1820 a 1833	CNPJ Cliente	14	A		
	//1834 a 1834	Flag Emancipado	1	A		
	//1835 a 1835	Origem do patrimonio 1	1	A		
	//1836 a 1836	Origem do patrimonio 2	1	A		
	//1837 a 1837	Origem do patrimonio 3	1	A		
	//1838 a 1838	Origem do patrimonio 4	1	A		
	//1839 a 1847	Filler	9	A		
	public static class DadoCliente {
		
		@MapAtributo(tamanho = 30)
		private String nome;
		
		@MapAtributo
		private String tipoPersonalidadeCpf;
		
		@MapAtributo(tamanho = 14)
		private String cpf;
		
		@MapAtributo
		private String correspondencia;
		
		@MapBean
		private DocumentoTp1 identidade;
		
		@MapAtributo
		private Boolean conjugeCompoeRenda;
		
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
		private String nomeDaMae;
		
		@MapAtributo(tamanho = 30)
		private String nomeDoPai;
		
		@MapAtributo(tamanho = 5)
		private Integer ctps;
		
		@MapAtributo(tamanho = 5)
		private String serieCtps;
		
		@MapAtributo
		private Integer estadoCivil;
		
		@MapBean
		private EnderecoTp2 enderecoResidencia;
		
		@MapBean
		private TelefoneRamal telefoneResidencia;
		
		@MapAtributo
		private Integer tipoTelefone;
		
		@MapAtributo
		private Integer tipoResidencia;
		
		@MapAtributo(tamanho = 8)
		private Date dataAdmissao;
		
		@MapAtributo(tamanho = 30)
		private String empresa;
		
		@MapBean
		private EnderecoTp2 enderecoTrabalho;
		
		@MapBean
		private TelefoneRamal telefoneTrabalho;
		
		@MapAtributo(tamanho = 11)
		private Integer valorRendaLiquida;
		
		@MapAtributo(tamanho = 20)
		private String cargo;
		
		@MapAtributo(tamanho = 20)
		private String profissao;
		
		@MapAtributo(comparador = "S")
		private Boolean aposentado;
		
		@MapAtributo(comparador = "S")
		private Boolean pensionista;
		
		@MapAtributo
		private String usoExclusivoLosango;
		
		@MapAtributo(tamanho = 6, formato = "MMyyyy")
		private Date resideDesde;
		
		@MapBean
		private Telefone celular;
		
		@MapAtributo(tamanho = 60)
		private String email;
		
		@MapAtributo(tamanho = 2)
		private String orgaoBeneficio;
		
		@MapAtributo(tamanho = 20)
		private String numeroBeneficio;
		
		@MapAtributo
		private Boolean possuiPatrimonio;
		
		@MapAtributo(tamanho = 68, trim = false)
		private String patrimonio;
				
		@MapAtributo(trim = false)
		private String fillerPatrimonio;
		
		@MapAtributo(tamanho = 2)
		private String codigoPais;
		
		@MapAtributo(tamanho = 2)
		private String codigoUfNaturalidade;
		
		@MapAtributo(tamanho = 6, formato = "MMyyyy")
		private Date dataComprovanteRenda;
		
		@MapAtributo(tamanho = 2)
		private String tipoComprovante;
		
		@MapAtributo(tamanho = 2)
		private String ocupacaoNova;
		
		@MapAtributo(tamanho = 8)
		private Date dataVencimentoDocumentoIdentificacao;
		
		@MapAtributo(tamanho = 14)
		private String cnpj;
		
		@MapAtributo
		private Boolean emancipado;
		
		@MapAtributo(tamanho = 4)
		private String origemPatrimonio;
		
		@MapAtributo(tamanho = 9, trim = false)
		private String filler;

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getTipoPersonalidadeCpf() {
			return tipoPersonalidadeCpf;
		}

		public void setTipoPersonalidadeCpf(String tipoPersonalidadeCpf) {
			this.tipoPersonalidadeCpf = tipoPersonalidadeCpf;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public String getCorrespondencia() {
			return correspondencia;
		}

		public void setCorrespondencia(String correspondencia) {
			this.correspondencia = correspondencia;
		}

		public DocumentoTp1 getIdentidade() {
			return identidade;
		}

		public void setIdentidade(DocumentoTp1 identidade) {
			this.identidade = identidade;
		}

		public Boolean isConjugeCompoeRenda() {
			return conjugeCompoeRenda;
		}

		public void setConjugeCompoeRenda(Boolean conjugeCompoeRenda) {
			this.conjugeCompoeRenda = conjugeCompoeRenda;
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

		public String getNomeDaMae() {
			return nomeDaMae;
		}

		public void setNomeDaMae(String nomeDaMae) {
			this.nomeDaMae = nomeDaMae;
		}

		public String getNomeDoPai() {
			return nomeDoPai;
		}

		public void setNomeDoPai(String nomeDoPai) {
			this.nomeDoPai = nomeDoPai;
		}

		public Integer getCtps() {
			return ctps;
		}

		public void setCtps(Integer ctps) {
			this.ctps = ctps;
		}

		public String getSerieCtps() {
			return serieCtps;
		}

		public void setSerieCtps(String serieCtps) {
			this.serieCtps = serieCtps;
		}

		public Integer getEstadoCivil() {
			return estadoCivil;
		}

		public void setEstadoCivil(Integer estadoCivil) {
			this.estadoCivil = estadoCivil;
		}

		public EnderecoTp2 getEnderecoResidencia() {
			return enderecoResidencia;
		}

		public void setEnderecoResidencia(EnderecoTp2 enderecoResidencia) {
			this.enderecoResidencia = enderecoResidencia;
		}

		public TelefoneRamal getTelefoneResidencia() {
			return telefoneResidencia;
		}

		public void setTelefoneResidencia(TelefoneRamal telefoneResidencia) {
			this.telefoneResidencia = telefoneResidencia;
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

		public EnderecoTp2 getEnderecoTrabalho() {
			return enderecoTrabalho;
		}

		public void setEnderecoTrabalho(EnderecoTp2 enderecoTrabalho) {
			this.enderecoTrabalho = enderecoTrabalho;
		}

		public TelefoneRamal getTelefoneTrabalho() {
			return telefoneTrabalho;
		}

		public void setTelefoneTrabalho(TelefoneRamal telefoneTrabalho) {
			this.telefoneTrabalho = telefoneTrabalho;
		}

		public Integer getValorRendaLiquida() {
			return valorRendaLiquida;
		}

		public void setValorRendaLiquida(Integer valorRendaLiquida) {
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

		public Date getResideDesde() {
			return resideDesde;
		}

		public void setResideDesde(Date resideDesde) {
			this.resideDesde = resideDesde;
		}

		public Telefone getCelular() {
			return celular;
		}

		public void setCelular(Telefone celular) {
			this.celular = celular;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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

		public Date getDataComprovanteRenda() {
			return dataComprovanteRenda;
		}

		public void setDataComprovanteRenda(Date dataComprovanteRenda) {
			this.dataComprovanteRenda = dataComprovanteRenda;
		}

		public String getTipoComprovante() {
			return tipoComprovante;
		}

		public void setTipoComprovante(String tipoComprovante) {
			this.tipoComprovante = tipoComprovante;
		}

		public String getOcupacaoNova() {
			return ocupacaoNova;
		}

		public void setOcupacaoNova(String ocupacaoNova) {
			this.ocupacaoNova = ocupacaoNova;
		}

		public Date getDataVencimentoDocumentoIdentificacao() {
			return dataVencimentoDocumentoIdentificacao;
		}

		public void setDataVencimentoDocumentoIdentificacao(Date dataVencimentoDocumentoIdentificacao) {
			this.dataVencimentoDocumentoIdentificacao = dataVencimentoDocumentoIdentificacao;
		}

		public String getCnpj() {
			return cnpj;
		}

		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
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
	
	//1848 a 1877	Nome Do Cônjuge	30	A	Nome do Cônjuge do Cliente	
	//1878 a 1917	Local de Nascimento	40	A		
	//1918 a 1925	Data de Nascimento	8	N	Data nascimento do cônjuge	
	//1926 a 1936	CPF	11	N	CPF do Cônjuge	
	//1937 a 1946	Identidade	10	A	Número da Identidade do Cônjuge	
	//1947 a 1948	Tipo de Documento	2	A	"01 - Carteira Identidade
	//02 - Carteira Estrangeiro
	//03 - Passaporte 
	//04 - Carteira Identidade profissional
	//05 - Carteira militar
	//06 - Carteira trabalho e Previdência Social
	//07 - carteira identidade indigena
	//08 - carteira ministério público
	//09 - carteira nacional habilitação
	//10 - cart. identidade serv. publico federal
	//11 - numero identificação trabalhador
	//12 - numero beneficiario
	//13 - certidao nascimento
	//14 - identidade estrangeiro - merc"	
	//1949 a 1953	Órgão Emissor	5	A	Órgão Emissor do Documento de Identidade do Cônjuge	Ver tabela de dominio
	//1954 a 1955	UF Órgão Emissor	2	A		
	//1956 a 1963	Data Emissão	8	N	Data de emissão da identidade	
	//1964 a 1988	Empresa 	25	A	Empresa Em Que o Cônjuge Trabalha	
	//1989 a 1996	Data  Admissão	8	N	Data da Admissão Na Empresa	
	//1997 a 2036	Logradouro	40	A	Endereço Comercial do Cônjuge	
	//2037 a 2041	Numero	5	A		
	//2042 a 2056	Complemento	15	A		
	//2057 a 2071	Bairro	15	A	Bairro onde trabalha o cônjuge	
	//2072 a 2086	Cidade	15	A	Cidade Onde Trabalha o cônjuge	
	//2087 a 2088	UF	2	A	Abreviatura da Unidade Federativa 	
	//2089 a 2096	CEP	8	N	CEP do endereço comercial do cônjuge	
	//2097 a 2099	DDD	3	N	DDD da Cidade Onde Trabalha o cônjuge	
	//2100 a 2108	Telefone	9	N	Telefone do Trabalho do cônjuge	
	//2109 a 2112	Ramal	4	N	Ramal do Trabalho do cônjuge	
	//2113 a 2132	Cargo	20	A	Cargo do Cônjuge	
	//2133 a 2152	Profissão	20	A	Profissão do Conjuge	
	//2153 a 2153	Aposentado	1	A	"Aponta se o cliente é aposentado:
	//S - Sim; N - Não"	"S"  "N"
	//2154 a 2154	Pensionista	1	A	"Aponta se o cliente é Pensionista:
	//S - Sim; N - Não"	"S"  "N"
	//2155 a 2155	Uso exclusivo da Losango	1	A	Uso exclusivo da Losango	
	//2156 a 2166	Valor Renda Líquida 	11	N	Renda Líquida do Cônjuge (em R$)                                            	
	//2167 a 2236	Patrimônio	70	A		
	//2237 a 2237	Nacionalidade	1	N	"Nacionalidade do Conjuge
	//0-Brasileiro
	//1-Estrangeiro        "	
	//2238 a 2239	Código do País (informar se nacionalidade = estrangeiro)	2	A	Tabela de Paises	
	//2240 a 2241	Código da UF da Naturalidade (informar se nacionalidade = brasileiro)	2	A	Tabela de UF	
	//2242 a 2247	Mes/Ano Comprovante de Renda	6	A		
	//2248 a 2249	Tipo Comprovante de Renda	2	A		
	//2250 a 2251	Ocupação nova	2	A	Código da Profissão	
	//2252 a 2252	Sexo do Conjuge	1	A		
	//2253 a 2266	CNPJ Conjuge	14	A		
	//2267 a 2286	Filler	20	A
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
		private EnderecoTp2 enderecoComercial;
		
		@MapBean
		private TelefoneRamal telefoneTrabalho;
		
		@MapAtributo(tamanho = 20)
		private String cargo;
		
		@MapAtributo(tamanho = 20)
		private String profissao;
		
		@MapAtributo(comparador = "S")
		private Boolean aposentado;
		
		@MapAtributo(comparador = "S")
		private Boolean pensionista;
		
		@MapAtributo
		private String usoExclusivoLosango;
		
		@MapAtributo(tamanho = 11)
		private Integer valorRendaLiquida;
		
		@MapAtributo(tamanho = 70, trim = false)
		private String patrimonio;
		
		@MapAtributo
		private String nacionalidade;
		
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

		public EnderecoTp2 getEnderecoComercial() {
			return enderecoComercial;
		}

		public void setEnderecoComercial(EnderecoTp2 enderecoComercial) {
			this.enderecoComercial = enderecoComercial;
		}

		public TelefoneRamal getTelefoneTrabalho() {
			return telefoneTrabalho;
		}

		public void setTelefoneTrabalho(TelefoneRamal telefoneTrabalho) {
			this.telefoneTrabalho = telefoneTrabalho;
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

		public Integer getValorRendaLiquida() {
			return valorRendaLiquida;
		}

		public void setValorRendaLiquida(Integer valorRendaLiquida) {
			this.valorRendaLiquida = valorRendaLiquida;
		}

		public String getPatrimonio() {
			return patrimonio;
		}

		public void setPatrimonio(String patrimonio) {
			this.patrimonio = patrimonio;
		}

		public String getNacionalidade() {
			return nacionalidade;
		}

		public void setNacionalidade(String nacionalidade) {
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
	
	//2505 a 2506	Escolaridade	2	A	Codigo da Escolaridade	
	//2507 a 2526	Formação	20	A	Formação	
	//2527 a 2527	Indicador Possui cartão	1	A	Indicador se possui cartão	0 - Não 1 - Sim
	//2528 a 2528	Indicador Possui veículo próprio	1	A	Indicador Possui veículo próprio	0 - Não 1 - Sim
	//2529 a 2538	Placa	10	A		
	//2539 a 2598	Renavam	60	A		
	//2599 a 2599	Indicador Possui veículo quitado	1	A	Indicador Possui veículo quitado	0 - Não 1 - Sim
	//2600 a 2600	Possui experiencia de crédito	1	A	Indicador Possui experiência	0 - Não 1 - Sim
	//2601 a 2620	Local da Experiência	20	A		
	//2621 a 2622	Plano da Experiência	2	N		
	//2623 a 2637	Valor da Prestação da Experiência	15	N		
	//2638 a 2643	Inicio da Experiência de Crédito	6	N	Inicio da Experiência MMAAAA	
	//2644 a 2683	Classificação do Cliente no Lojista	40	A		
	//2684 a 2684	Indicador Possui Cartão Financeira	1	A	Indicador Possui Cartão Financeira	0 - Não 1 - Sim
	//2685 a 2685	Indicador Possui Conta Corrente	1	A	Indicador Possui Conta Corrente	0 - Não 1 - Sim
	//2686 a 2686	Indicador Possui dependente	1	A		0 - Não 1 - Sim
	//2687 a 2688	Quantidade de dependentes	2	N		
	//2689 a 2703	Nome do cartão	15	A		
	//2704 a 2704	Indicador DDA	1	A	Indicador se o cliente é DDA	0 - Não 1 - Sim
	//2705 a 2754	Filler	50	A
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

		@MapAtributo(tamanho = 15)
		private Integer valorPrestacaoExperiencia;
		
		@MapAtributo(tamanho = 6)
		private Date dataInicioExperienciaCredito;
		
		@MapAtributo(tamanho = 40)
		private String classificacaoClienteLojista;
		
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
		private Boolean dda;
		
		@MapAtributo(tamanho = 50, trim = false)
		private String filler;

		public String getEscolaridade() {
			return escolaridade;
		}

		public void setEscolaridade(String escolaridade) {
			this.escolaridade = escolaridade;
		}

		public String getFormacao() {
			return formacao;
		}

		public void setFormacao(String formacao) {
			this.formacao = formacao;
		}

		public Boolean isPossuiCartao() {
			return possuiCartao;
		}

		public void setPossuiCartao(Boolean possuiCartao) {
			this.possuiCartao = possuiCartao;
		}

		public Boolean isPossuiVeiculoProprio() {
			return possuiVeiculoProprio;
		}

		public void setPossuiVeiculoProprio(Boolean possuiVeiculoProprio) {
			this.possuiVeiculoProprio = possuiVeiculoProprio;
		}

		public String getPlaca() {
			return placa;
		}

		public void setPlaca(String placa) {
			this.placa = placa;
		}

		public String getRenavam() {
			return renavam;
		}

		public void setRenavam(String renavam) {
			this.renavam = renavam;
		}

		public Boolean isPossuiVeiculoQuitado() {
			return possuiVeiculoQuitado;
		}

		public void setPossuiVeiculoQuitado(Boolean possuiVeiculoQuitado) {
			this.possuiVeiculoQuitado = possuiVeiculoQuitado;
		}

		public Boolean isPossuiExperienciaCredito() {
			return possuiExperienciaCredito;
		}

		public void setPossuiExperienciaCredito(Boolean possuiExperienciaCredito) {
			this.possuiExperienciaCredito = possuiExperienciaCredito;
		}

		public String getLocalExperiencia() {
			return localExperiencia;
		}

		public void setLocalExperiencia(String localExperiencia) {
			this.localExperiencia = localExperiencia;
		}

		public Integer getPlanoExperiencia() {
			return planoExperiencia;
		}

		public void setPlanoExperiencia(Integer planoExperiencia) {
			this.planoExperiencia = planoExperiencia;
		}

		public Integer getValorPrestacaoExperiencia() {
			return valorPrestacaoExperiencia;
		}

		public void setValorPrestacaoExperiencia(Integer valorPrestacaoExperiencia) {
			this.valorPrestacaoExperiencia = valorPrestacaoExperiencia;
		}

		public Date getDataInicioExperienciaCredito() {
			return dataInicioExperienciaCredito;
		}

		public void setDataInicioExperienciaCredito(Date dataInicioExperienciaCredito) {
			this.dataInicioExperienciaCredito = dataInicioExperienciaCredito;
		}

		public String getClassificacaoClienteLojista() {
			return classificacaoClienteLojista;
		}

		public void setClassificacaoClienteLojista(String classificacaoClienteLojista) {
			this.classificacaoClienteLojista = classificacaoClienteLojista;
		}

		public Boolean isPossuiCartaoFinanceira() {
			return possuiCartaoFinanceira;
		}

		public void setPossuiCartaoFinanceira(Boolean possuiCartaoFinanceira) {
			this.possuiCartaoFinanceira = possuiCartaoFinanceira;
		}

		public Boolean isPossuiContaCorrente() {
			return possuiContaCorrente;
		}

		public void setPossuiContaCorrente(Boolean possuiContaCorrente) {
			this.possuiContaCorrente = possuiContaCorrente;
		}

		public Boolean isPossuiDependente() {
			return possuiDependente;
		}

		public void setPossuiDependente(Boolean possuiDependente) {
			this.possuiDependente = possuiDependente;
		}

		public Integer getQuantidadeDependentes() {
			return quantidadeDependentes;
		}

		public void setQuantidadeDependentes(Integer quantidadeDependentes) {
			this.quantidadeDependentes = quantidadeDependentes;
		}

		public String getNomeCartao() {
			return nomeCartao;
		}

		public void setNomeCartao(String nomeCartao) {
			this.nomeCartao = nomeCartao;
		}

		public Boolean isDda() {
			return dda;
		}

		public void setDda(Boolean dda) {
			this.dda = dda;
		}

		public String getFiller() {
			return filler;
		}

		public void setFiller(String filler) {
			this.filler = filler;
		}
	}
	
	//2755 a 2755	Operação	1	A	Flag operação (1 -EP , 2- CDC)	“1”   “2”
	//2756 a 2756	Primeira Compra	1	A	"Flag de Primeira Compra
	//0 - Não
	//1 - Sim"	“0”   “1”
	//2757 a 2771	Pedido	15	A	Campo do número de pedido, nota fiscal, etc. do lojista	
	//2772 a 2801	Nome Lojista	30	A	Nome do Lojista	
	//2802 a 2805	Conveniada	4	N	Código da Empresa Conveniada	
	//2806 a 2806	Forma Pgto	1	N	"Indicação da Forma de Cobrança
	//0) Carnë
	//1) averbação em folha
	//2) Cheque Pré
	//3) Extrato Rotativo
	//4) Extrato Parcelado
	//5) Debito em Conta"	"0"  “1”   “2”  “3”  “4”, "5"
	//2807 a 2808	Carência	2	N	Quantidade de Dias Para Ajuste do Vencimento da Prestação	
	//2809 a 2810	Top	2	N	Tipo de Operação	
	//2811 a 2818	Tab Financiamento	8	N	Identificação da tabela de financiamento  (COP’s) referente ao crédito solicitado, específica para o lojista ou Crédito Pessoal 	
	//2819 a 2819	Tipo de Pagamento	1	N	0-Pré 1-Pós (Flag que indica se a negociação será efetuada com Pré fixado ou Pos fixado)	“0”   “1”
	//2820 a 2826	Taxa Anual	7	N	Taxa de Juros Aplicada Ao Ano	
	//2827 a 2833	Taxa Mensal	7	N	Taxa de Juros Aplicada Ao Mês	
	//2834 a 2841	Data da Operação	8	N	Data da Operação	
	//2842 a 2856	Valor  da Operação/Solicitado	15	N	Valor solicitado pelo cliente (em R$)	
	//2857 a 2857	Pag_Tac	1	N	Flag que indica a forma de pagamento da TAC ( 0 -Financiada   1- A vista 2 - Descontada em (RO))	“0”   “1”    “2”
	//2858 a 2872	Valor Tac	15	N	Valor da TAC (em R$)	
	//2873 a 2887	Valor da Entrada	15	N	Valor cobrado no ato da venda (em R$).	
	//2888 a 2902	Valor Total do Financiamento	15	N	Valor Total do Financiamento (em R$).	
	//2903 a 2907	Valor Tarifa Bancaria	5	N	Tarifa Bancária (2 casas decimais) (em R$)	
	//2908 a 2909	Produto	2	N	Produto (Top + Forma Pgto)	
	//2910 a 2916	CET Mensal (%)	7	A	Taxa Mensal do Custo efetivo Total (2 decimais)	
	//2917 a 2923	CET Anual  (%)	7	A	Taxa Anual   do Custo efetivo Total (2 decimais)	
	//2924 a 2930	IOF	7	N	Valor do IOF (2 casas decimais)	
	//2931 a 2931	Indicador se existe RPS	1	N	Mostra se os campos do RPS estão preenchidos	0 - Não, 1 - Sim, 2 - Erro
	//2932 a 2949	Número do RPS	18	A	Campo de Instrução para o RPS	
	//2950 a 2954	Série do RPS	5	A	Campo de Instrução para o RPS	
	//2955 a 2962	Data de Emissão do RPS	8	A	Campo de Instrução para o RPS	
	//2963 a 2967	VLR Alíquota	5	A	Campo de Instrução para o RPS	
	//2968 a 2971	Filial Losango	4	A	Campo de Instrução para o RPS	
	//2972 a 3021	Nome da Filial Losango	0050	A	Usado no RPS	
	//3022 a 3035	CNPJ Filial Losango	0014	A	Usado no RPS	
	//3036 a 3055	Inscrição da Filial Losango	0020	A	Usado no RPS	
	//3056 a 3120	Logradouro da Filial Losango	0065	A	Usado no RPS	
	//3121 a 3125	Numero (Endereço) da Filial Losango	0005	A	Usado no RPS	
	//3126 a 3140	Complemento da Filial Losango	0015	A	Usado no RPS	
	//3141 a 3175	Bairro da Filial Losango	0035	A	Usado no RPS	
	//3176 a 3210	Cidade da Filial Losango	0035	A	Usado no RPS	
	//3211 a 3212	UF da Filial Losango	0002	A	Usado no RPS	
	//3213 a 3220	Cep da Filial Losango	0008	A	Usado no RPS	
	//3221 a 3221	Pre-Pago	1	A	Indica a compra de telefone celular Pre-Pago: 0 -Default ; 1 - Pre	 '0' '1'
	//3222 a 3222	Leva na Hora	1	A	 Indica se o cliente levara a mercadoria na hora:   0 - Default ; 1 - leva	 '0' '1'
	//3223 a 3223	Beta-Gama	1	A	"Indicadore de Fraude
	//  0 - Default - Sem fraude
	//  2 - Beta    - Susp fraude
	//  1 - Gama    - Confirm fraude"	 '0' '1' '2'
	//3224 a 3233	Promotor	10	N		
	//3234 a 3234	Indicador aceita consulta ao sysbacen	1	A		 '0' '1'
	//3235 a 3242	Data do evento (entrega do bem)	8	N	Data do Bem	
	//3243 a 3257	Valor da Entrada Lojista	15	N		
	//3258 a 3258	Quantidade máxima de reanálise	1	N		
	//3259 a 3259	Produto Cessão	1	N	0 - Não, 1 - Sim	
	//3260 a 3309	Filler	50	A
	public static class DadoOperacao {
		
		@MapAtributo
		private Integer operacao;
		
		@MapAtributo
		private Boolean primeiraCompra;
		
		@MapAtributo(tamanho = 15)
		private String pedido;
		
		@MapAtributo(tamanho = 30)
		private String nomeLojista;
		
		@MapAtributo(tamanho = 4)
		private Integer conveniada;
		
		@MapAtributo
		private Integer formaPagamento;
		
		@MapAtributo(tamanho = 2)
		private Integer carencia;
		
		@MapAtributo(tamanho = 2)
		private Integer top;	
		
		@MapAtributo(tamanho = 8)
		private Integer tabelaFinanciamento;
		
		@MapAtributo
		private Integer tipoPagamento;
		
		@MapAtributo(tamanho = 7)
		private Integer taxaAnual;
		
		@MapAtributo(tamanho = 7)
		private Integer taxaMensal;
		
		@MapAtributo(tamanho = 8)
		private Date dataOperacao;
		
		@MapAtributo(tamanho = 15)
		private Integer valorOperacao;
		
		@MapAtributo
		private Integer pagTac;
		
		@MapAtributo(tamanho = 15)
		private Integer valorTac;
		
		@MapAtributo(tamanho = 15)
		private Integer valorEntrada;
		
		@MapAtributo(tamanho = 15)
		private Integer valorTotalFinanciamento;
		
		@MapAtributo(tamanho = 5, scale = 2)
		private Double valorTarifaBancaria;
		
		@MapAtributo(tamanho = 2)
		private Integer produto;
		
		@MapAtributo(tamanho = 7, scale = 2)
		private Double cetMensal;
		
		@MapAtributo(tamanho = 7, scale = 2)
		private Double cetAnual;
		
		@MapAtributo(tamanho = 7, scale = 2)
		private Double iof;

		@MapAtributo
		private Integer existeRps;
		
		@MapAtributo(tamanho = 18)
		private String numeroRps;
		
		@MapAtributo(tamanho = 5)
		private String serieRps;

		@MapAtributo(tamanho = 8)
		private Date dataEmissaoRps;
		
		@MapAtributo(tamanho = 5)
		private String valorAliquota;
		
		@MapAtributo(tamanho = 4)
		private String filialLosango;
		
		@MapAtributo(tamanho = 50)
		private String nomeFilialLosango;
		
		@MapAtributo(tamanho = 14)
		private String cnpjFilialLosango;
		
		@MapAtributo(tamanho = 20)
		private String inscricaoFilialLosango;
		
		@MapBean
		private EnderecoTp3 endereoFilialLosango;
		
		@MapAtributo
		private String prePago;
	
		@MapAtributo
		private Boolean levaNaHora;

		@MapAtributo
		private Integer betaGama;
		
		@MapAtributo(tamanho = 10)
		private Integer promotor;
		
		@MapAtributo
		private Boolean aceitaConsultaSysBacen;
		
		@MapAtributo(tamanho = 8)
		private Date dataEvento;
		
		@MapAtributo(tamanho = 15)
		private Integer valorEntradaLojista;
		
		@MapAtributo
		private Integer quantidadeMaxReanalise;

		@MapAtributo
		private Boolean produtoCessao;
		
		@MapAtributo(tamanho = 50, trim = false)
		private String filler;

		public Integer getOperacao() {
			return operacao;
		}

		public void setOperacao(Integer operacao) {
			this.operacao = operacao;
		}

		public Boolean isPrimeiraCompra() {
			return primeiraCompra;
		}

		public void setPrimeiraCompra(Boolean primeiraCompra) {
			this.primeiraCompra = primeiraCompra;
		}

		public String getPedido() {
			return pedido;
		}

		public void setPedido(String pedido) {
			this.pedido = pedido;
		}

		public String getNomeLojista() {
			return nomeLojista;
		}

		public void setNomeLojista(String nomeLojista) {
			this.nomeLojista = nomeLojista;
		}

		public Integer getConveniada() {
			return conveniada;
		}

		public void setConveniada(Integer conveniada) {
			this.conveniada = conveniada;
		}

		public Integer getFormaPagamento() {
			return formaPagamento;
		}

		public void setFormaPagamento(Integer formaPagamento) {
			this.formaPagamento = formaPagamento;
		}

		public Integer getCarencia() {
			return carencia;
		}

		public void setCarencia(Integer carencia) {
			this.carencia = carencia;
		}

		public Integer getTop() {
			return top;
		}

		public void setTop(Integer top) {
			this.top = top;
		}

		public Integer getTabelaFinanciamento() {
			return tabelaFinanciamento;
		}

		public void setTabelaFinanciamento(Integer tabelaFinanciamento) {
			this.tabelaFinanciamento = tabelaFinanciamento;
		}

		public Integer getTipoPagamento() {
			return tipoPagamento;
		}

		public void setTipoPagamento(Integer tipoPagamento) {
			this.tipoPagamento = tipoPagamento;
		}

		public Integer getTaxaAnual() {
			return taxaAnual;
		}

		public void setTaxaAnual(Integer taxaAnual) {
			this.taxaAnual = taxaAnual;
		}

		public Integer getTaxaMensal() {
			return taxaMensal;
		}

		public void setTaxaMensal(Integer taxaMensal) {
			this.taxaMensal = taxaMensal;
		}

		public Date getDataOperacao() {
			return dataOperacao;
		}

		public void setDataOperacao(Date dataOperacao) {
			this.dataOperacao = dataOperacao;
		}

		public Integer getValorOperacao() {
			return valorOperacao;
		}

		public void setValorOperacao(Integer valorOperacao) {
			this.valorOperacao = valorOperacao;
		}

		public Integer getPagTac() {
			return pagTac;
		}

		public void setPagTac(Integer pagTac) {
			this.pagTac = pagTac;
		}

		public Integer getValorTac() {
			return valorTac;
		}

		public void setValorTac(Integer valorTac) {
			this.valorTac = valorTac;
		}

		public Integer getValorEntrada() {
			return valorEntrada;
		}

		public void setValorEntrada(Integer valorEntrada) {
			this.valorEntrada = valorEntrada;
		}

		public Integer getValorTotalFinanciamento() {
			return valorTotalFinanciamento;
		}

		public void setValorTotalFinanciamento(Integer valorTotalFinanciamento) {
			this.valorTotalFinanciamento = valorTotalFinanciamento;
		}

		public Double getValorTarifaBancaria() {
			return valorTarifaBancaria;
		}

		public void setValorTarifaBancaria(Double valorTarifaBancaria) {
			this.valorTarifaBancaria = valorTarifaBancaria;
		}

		public Integer getProduto() {
			return produto;
		}

		public void setProduto(Integer produto) {
			this.produto = produto;
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

		public Double getIof() {
			return iof;
		}

		public void setIof(Double iof) {
			this.iof = iof;
		}

		public Integer getExisteRps() {
			return existeRps;
		}

		public void setExisteRps(Integer existeRps) {
			this.existeRps = existeRps;
		}

		public String getNumeroRps() {
			return numeroRps;
		}

		public void setNumeroRps(String numeroRps) {
			this.numeroRps = numeroRps;
		}

		public String getSerieRps() {
			return serieRps;
		}

		public void setSerieRps(String serieRps) {
			this.serieRps = serieRps;
		}

		public Date getDataEmissaoRps() {
			return dataEmissaoRps;
		}

		public void setDataEmissaoRps(Date dataEmissaoRps) {
			this.dataEmissaoRps = dataEmissaoRps;
		}

		public String getValorAliquota() {
			return valorAliquota;
		}

		public void setValorAliquota(String valorAliquota) {
			this.valorAliquota = valorAliquota;
		}

		public String getFilialLosango() {
			return filialLosango;
		}

		public void setFilialLosango(String filialLosango) {
			this.filialLosango = filialLosango;
		}

		public String getNomeFilialLOsango() {
			return nomeFilialLosango;
		}

		public void setNomeFilialLosango(String nomeFilialLosango) {
			this.nomeFilialLosango = nomeFilialLosango;
		}

		public String getCnpjFilialLosango() {
			return cnpjFilialLosango;
		}

		public void setCnpjFilialLosango(String cnpjFilialLosango) {
			this.cnpjFilialLosango = cnpjFilialLosango;
		}

		public String getInscricaoFilialLosango() {
			return inscricaoFilialLosango;
		}

		public void setInscricaoFilialLosango(String inscricaoFilialLosango) {
			this.inscricaoFilialLosango = inscricaoFilialLosango;
		}

		public EnderecoTp3 getEndereoFilialLosango() {
			return endereoFilialLosango;
		}

		public void setEndereoFilialLosango(EnderecoTp3 endereoFilialLosango) {
			this.endereoFilialLosango = endereoFilialLosango;
		}

		public String getPrePago() {
			return prePago;
		}

		public void setPrePago(String prePago) {
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

		public Integer getPromotor() {
			return promotor;
		}

		public void setPromotor(Integer promotor) {
			this.promotor = promotor;
		}

		public Boolean isAceitaConsultaSysBacen() {
			return aceitaConsultaSysBacen;
		}

		public void setAceitaConsultaSysBacen(Boolean aceitaConsultaSysBacen) {
			this.aceitaConsultaSysBacen = aceitaConsultaSysBacen;
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

		public Integer getQuantidadeMaxReanalise() {
			return quantidadeMaxReanalise;
		}

		public void setQuantidadeMaxReanalise(Integer quantidadeMaxReanalise) {
			this.quantidadeMaxReanalise = quantidadeMaxReanalise;
		}

		public Boolean isProdutoCessao() {
			return produtoCessao;
		}

		public void setProdutoCessao(Boolean produtoCessao) {
			this.produtoCessao = produtoCessao;
		}

		public String getFiller() {
			return filler;
		}

		public void setFiller(String filler) {
			this.filler = filler;
		}
	}
	
	//3413 a 3414	tipo do seguro	2	A	Tipo de produto acessório	
	//3415 a 3418	código do seguro	4	A	Codigo do produto acessório	
	//3419 a 3433	valor do seguro	15	N	Valor do produto acessório (em R$)	
	//3434 a 3441	Valor da Cobertura do seguro	8	N		
	//3442 a 3443	Plano Maximo do Seguro	2	N		
	//3444 a 3451	Codigo do produto do Seguro	8	N
	public static class DadoSeguroPremista {
		
		@MapAtributo(tamanho = 2)
		private String tipoSeguro;
		
		@MapAtributo(tamanho = 4)
		private String codigoSeguro;
		
		@MapAtributo(tamanho = 15)
		private Integer valorSeguro;
		
		@MapAtributo(tamanho = 8)
		private Integer valorCoberturaSeguro;	

		@MapAtributo(tamanho = 2)
		private Integer planoMaximoSeguro;
		
		@MapAtributo(tamanho = 8)
		private Integer codigoProdutoSeguro;

		public String getTipoSeguro() {
			return tipoSeguro;
		}

		public void setTipoSeguro(String tipoSeguro) {
			this.tipoSeguro = tipoSeguro;
		}

		public String getCodigoSeguro() {
			return codigoSeguro;
		}

		public void setCodigoSeguro(String codigoSeguro) {
			this.codigoSeguro = codigoSeguro;
		}

		public Integer getValorSeguro() {
			return valorSeguro;
		}

		public void setValorSeguro(Integer valorSeguro) {
			this.valorSeguro = valorSeguro;
		}

		public Integer getValorCoberturaSeguro() {
			return valorCoberturaSeguro;
		}

		public void setValorCoberturaSeguro(Integer valorCoberturaSeguro) {
			this.valorCoberturaSeguro = valorCoberturaSeguro;
		}

		public Integer getPlanoMaximoSeguro() {
			return planoMaximoSeguro;
		}

		public void setPlanoMaximoSeguro(Integer planoMaximoSeguro) {
			this.planoMaximoSeguro = planoMaximoSeguro;
		}

		public Integer getCodigoProdutoSeguro() {
			return codigoProdutoSeguro;
		}

		public void setCodigoProdutoSeguro(Integer codigoProdutoSeguro) {
			this.codigoProdutoSeguro = codigoProdutoSeguro;
		}
		
	}
}
