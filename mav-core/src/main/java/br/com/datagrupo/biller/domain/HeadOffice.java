package br.com.datagrupo.biller.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({ 
	@NamedQuery(name = "HeadOffice.all", query = "select h from HeadOffice h"),
	@NamedQuery(name = "HeadOffice.findCustomerByName", query = "select h from HeadOffice h where h.name = :name"),
})
public class HeadOffice extends Customer {

	private static final long serialVersionUID = 7083945834867864644L;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "office", fetch=FetchType.EAGER)
	private List<Subsidiary> subsidiaries = new ArrayList<Subsidiary>();
	
	public List<Subsidiary> getSubsidiaries() {
		return subsidiaries;
	}
	
	public void setSubsidiaries(List<Subsidiary> subsidiaries) {
		this.subsidiaries = subsidiaries;
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
