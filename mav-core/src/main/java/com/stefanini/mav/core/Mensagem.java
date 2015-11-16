package com.stefanini.mav.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.stefanini.mav.mensagem.CodigoMensagem;

@Entity
@Table(name = "mensagem")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({ @NamedQuery(name = "Mensagem.all", query = "select m from Mensagem m") })
public class Mensagem implements Serializable {

	private static final long serialVersionUID = -5241529310575479494L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer numeroTransacao;

	@Enumerated(EnumType.STRING)
	private CodigoMensagem codigo;

	private String dump;

	@Temporal(TemporalType.DATE)
	@Column(updatable = false)
	private Date data;

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

}
