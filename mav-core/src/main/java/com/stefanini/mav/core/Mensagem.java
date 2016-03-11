package com.stefanini.mav.core;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.stefanini.mav.mensagem.Cabecalho.Fluxo;
import com.stefanini.mav.mensagem.CodigoMensagem;

@Entity
@Table(
		name = "mensagem", 
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"numeroTransacao", "codigo", "numeroProposta"}),
				@UniqueConstraint(columnNames = {"numeroTransacao", "codigo", "data"}),
		}
)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({ 
	@NamedQuery(name = "Mensagem.todas", query = "select m from Mensagem m"),
	@NamedQuery(name = "Mensagem.processadas", query = "select count(m) from Mensagem m inner join m.parceiras"),
	@NamedQuery(name = "Mensagem.contar", query = "select count(m) from Mensagem m"),
	@NamedQuery(name = "Mensagem.recuperar", query = "select m from Mensagem m where m.numeroTransacao = :nt and m.codigo = :cd and m.numeroProposta = :np")
})
public class Mensagem implements Serializable {

	private static final long serialVersionUID = -5241529310575479494L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer numeroTransacao;
	
	@Column(nullable = false)
	private String numeroProposta;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Fluxo fluxo;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CodigoMensagem codigo;

	@Column(nullable = false)
	@Lob
	private String dump;

	@Temporal(TemporalType.DATE)
	@Column(updatable = false)
	private Date data;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "mensagens")
	private List<MensagemParceira> parceiras;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumeroTransacao() {
		return numeroTransacao;
	}

	public void setNumeroTransacao(Integer numeroTransacao) {
		this.numeroTransacao = numeroTransacao;
	}
	
	public String getNumeroProposta() {
		return numeroProposta;
	}
	
	public void setNumeroProposta(String numeroProposta) {
		this.numeroProposta = numeroProposta;
	}
	
	public Fluxo getFluxo() {
		return fluxo;
	}
	
	public void setFluxo(Fluxo fluxo) {
		this.fluxo = fluxo;
	}

	public CodigoMensagem getCodigo() {
		return codigo;
	}

	public void setCodigo(CodigoMensagem codigo) {
		this.codigo = codigo;
	}

	public String getDump() {
		return dump;
	}

	public void setDump(String dump) {
		this.dump = dump;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public List<MensagemParceira> getParceiras() {
		return parceiras;
	}
	
	public void setParceiras(List<MensagemParceira> parceiras) {
		this.parceiras = parceiras;
	}
}
