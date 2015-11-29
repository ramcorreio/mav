package com.stefanini.mav.mensagem;

import java.util.Date;
import java.util.LinkedList;

public class DadoPessoal extends DadoPessoalBasico {

	private static final long serialVersionUID = 4901056768962549954L;

	private boolean emancipado;

	private String tipoPersonalidade;

	private String usuarioCpf;

	private Documento documentoIdentificacao;

	private Boolean conjugeCompoeRenda;

	private String nome;

	private String localNascimento;

	private String sexo;

	private String nacionalidade;

	private String naturalidade;

	private String nomeMae;

	private String nomePai;

	private Integer estadoCivil;

	private String serieCarteiraProfissional;

	private Integer carteiraProfissional;

	private Integer tipoTelefone;

	private Integer tipoResidencia;

	private Date resideDesde;

	private Telefone telefone;

	private String email;

	private Boolean possuiPatrimonio;
	
	private LinkedList<Patrimonio> patrimonio;

	private String codigoPais;

	private String ufNaturalidade;

	private String filler2;

	private Telefone celular;

	@Override
	public boolean equals(Object obj) {

		if (!DadoPessoal.class.isInstance(obj)) {

			return false;
		}

		DadoPessoal outro = DadoPessoal.class.cast(obj);

		return emancipado == outro.emancipado 
				&& tipoPersonalidade.equals(outro.tipoPersonalidade)
				&& usuarioCpf.equals(outro.usuarioCpf) 
				&& documentoIdentificacao.equals(outro.documentoIdentificacao)/*
				&& conjugeCompoeRenda.equals(outro.conjugeCompoeRenda) 
				&& nome.equals(outro.nome)
				&& localNascimento.equals(outro.localNascimento) 
				&& sexo.equals(outro.sexo)
				&& nacionalidade.equals(outro.nacionalidade) 
				&& naturalidade.equals(outro.naturalidade)
				&& nomeMae.equals(outro.nomeMae) 
				&& nomePai.equals(outro.nomePai)
				&& carteiraProfissional.equals(outro.carteiraProfissional)
				&& serieCarteiraProfissional.equals(outro.serieCarteiraProfissional)
				&& estadoCivil.equals(outro.estadoCivil)*/;
	}

	public boolean isEmancipado() {
		return emancipado;
	}

	protected void setEmancipado(boolean emancipado) {
		this.emancipado = emancipado;
	}

	public String getTipoPersonalidade() {
		return tipoPersonalidade;
	}

	protected void setTipoPersonalidade(String tipoPersonalidade) {
		this.tipoPersonalidade = tipoPersonalidade;
	}

	public String getUsuarioCpf() {
		return usuarioCpf;
	}

	protected void setUsuarioCpf(String usuarioCpf) {
		this.usuarioCpf = usuarioCpf;

	}

	public Documento getDocumentoIdentificacao() {
		return documentoIdentificacao;
	}

	protected void setDocumentoIdentificacao(Documento documentoIdentificacao) {
		this.documentoIdentificacao = documentoIdentificacao;
	}

	public Boolean isConjugeCompoeRenda() {
		return conjugeCompoeRenda;
	}

	protected void setConjugeCompoeRenda(Boolean conjugeCompoeRenda) {
		this.conjugeCompoeRenda = conjugeCompoeRenda;
	}

	public String getNome() {
		return nome;
	}

	protected void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocalNascimento() {
		return localNascimento;
	}

	protected void setLocalNascimento(String localNascimento) {
		this.localNascimento = localNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	protected void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	protected void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	protected void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;

	}

	public String getNomeMae() {
		return nomeMae;
	}

	protected void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return nomePai;
	}

	protected void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public Integer getCarteiraProfissional() {
		return carteiraProfissional;
	}

	protected void setCarteiraProfissional(Integer carteiraProfissional) {
		this.carteiraProfissional = carteiraProfissional;

	}

	public String getSerieCarteiraProfissional() {
		return serieCarteiraProfissional;
	}

	protected void setSerieCarteiraProfissional(String serieCarteiraProfissional) {
		this.serieCarteiraProfissional = serieCarteiraProfissional;

	}

	public Integer getEstadoCivil() {
		return estadoCivil;
	}

	protected void setEstadoCivil(Integer estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	public Telefone getTelefone() {
		return telefone;
	}
	
	protected void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	public Integer getTipoTelefone() {
		return tipoTelefone;
	}

	protected void setTipoTelefone(Integer tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
		
	}
	
	public Integer getTipoResidencia() {
		return tipoResidencia;
	}

	protected void setTipoResidencia(Integer tipoResidencia) {
		this.tipoResidencia = tipoResidencia;
		
	}
	
	public Date getResideDesde() {
		return resideDesde;
	}

	protected void setResideDesde(Date reside) {
		this.resideDesde = reside;
		
	}
	
	public Telefone getCelular() {
		return celular;
	}

	protected void setCelular(Telefone celular) {
		this.celular = celular;
	}
	
	public String getEmail() {
		return email;
	}
	
	protected void setEmail(String email) {
		this.email = email;
	}
	
	public Boolean isPossuiPatrimonio() {
		return possuiPatrimonio;
	}
	
	protected void setPossuiPatrimonio(Boolean possuiPatrimonio) {
		this.possuiPatrimonio = possuiPatrimonio;
	}
	
	public LinkedList<Patrimonio> getPatrimonio() {
		return patrimonio;
	}

	protected void setPatrimonio(LinkedList<Patrimonio> patrimonio) {
		this.patrimonio = patrimonio;
	}
	
	public String getCodigoPais() {
		return codigoPais;
	}

	protected void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
		
	}
	
	public String getUfNaturalidade() {
		return ufNaturalidade;
	}

	protected void setUFNaturalidade(String ufNaturalidade) {
		this.ufNaturalidade = ufNaturalidade;
		
	}
	
	public String getFiller2() {
		return filler2;
	}

	protected void setFiller2(String filler2) {
		this.filler2 = filler2;
	}
	
}
