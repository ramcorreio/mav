package com.stefanini.mav.core;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "mensagem_parceira")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({ 
	@NamedQuery(name = "MensagemParceira.all", query = "select mp from MensagemParceira mp"), 
	@NamedQuery(name = "MensagemParceira.chaveParceira", query = "select mp from MensagemParceira mp where mp.chaveParceira = :chaveParceira")
})
public class MensagemParceira implements Serializable {

	private static final long serialVersionUID = -5241529310575479494L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String chaveParceira;
	
	@OneToMany(cascade = CascadeType.REFRESH)
	private List<Mensagem> mensagens;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getChaveParceira() {
		return chaveParceira;
	}
	
	public void setChaveParceira(String chaveParceira) {
		this.chaveParceira = chaveParceira;
	}
	
	public List<Mensagem> getMensagens() {
		return mensagens;
	}
	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

}
