package br.com.datagrupo.biller.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customer", uniqueConstraints = @UniqueConstraint(columnNames = {"customer_type", "cnpj"}))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "customer_type")
public abstract class Customer implements Serializable {

	private static final long serialVersionUID = 5040829163587694064L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 14, unique = true)
	private String cnpj;

	@NotNull
	@Column(length = 150, nullable = false)
	private String name;

	public Long getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj == null || ((Customer)obj).id == null || this.id == null){
			return false;
		}
		
		if(!(obj instanceof Customer)) {
			return false;
		}
		
		return id.equals(Customer.class.cast(obj).id);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
