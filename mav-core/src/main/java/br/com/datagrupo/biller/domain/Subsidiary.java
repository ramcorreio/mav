package br.com.datagrupo.biller.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
	@NamedQuery(name = "Subsidiary.all", query = "select s from Subsidiary s"),
	@NamedQuery(name = "Subsidiary.findSubsidiaryByName", query = "select s from Subsidiary s where s.office.id = :owner and s.name = :name")
	
})
public class Subsidiary extends Customer {

	private static final long serialVersionUID = 1561389192712549042L;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	private HeadOffice office = new HeadOffice();
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "owner")
	private List<Contract> contracts = new ArrayList<Contract>();

	public HeadOffice getOffice() {
		return office;
	}

	public void setOffice(HeadOffice office) {
		this.office = office;
	}
	
	public List<Contract> getContracts() {
		return contracts;
	}
	
	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
}
