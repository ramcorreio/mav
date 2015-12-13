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
		private Documento identidade;
		
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

		public Documento getIdentidade() {
			return identidade;
		}

		public void setIdentidade(Documento identidade) {
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
}
