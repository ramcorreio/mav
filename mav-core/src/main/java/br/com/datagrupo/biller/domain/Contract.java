package br.com.datagrupo.biller.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "contract")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({ @NamedQuery(name = "Contract.all", query = "select c from Contract c") })
public class Contract implements Serializable {

	private static final long serialVersionUID = -1578361555645031205L;
	
	public static enum Frequency {
        MENSAL,
        BIMESTRAL,
        TRIMESTRAL,
        QUADRIMESTRAL,
        SEMESTRAL,
        ANUAL,
        BIANUAL,
        TRIANUAL
    }
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Subsidiary owner = new Subsidiary();
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Frequency frequency;

	@Column(nullable = false)
	private boolean nf;

	@NotNull
	@Min(0)
	@Max(31)
	@Column(name = "expiration_day", nullable = false)
	private Integer expirationDay;

	@Column(name = "start_date")
	private Date startDate;

	@NotNull
	@Column(name = "expiration_date", nullable = false)
	private Date expirationDate;

	@Temporal(TemporalType.DATE)
    private Date created;

    @Temporal(TemporalType.DATE)
    private Date modified;

    @ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name = "seleted_services",  joinColumns = @JoinColumn(name = "contract_id", referencedColumnName = "id"))
	private List<SelectedService> services = new ArrayList<SelectedService>();
	
	@PrePersist
	public void onInsert() {  
		this.created = new Date(); 
	}
	
	@PreUpdate
	public void setLastUpdate() {  
		this.modified = new Date(); 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOwner(Subsidiary owner) {
		this.owner = owner;
	}

	public Subsidiary getOwner() {
		return owner;
	}
	
	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}
	
	public Frequency getFrequency() {
		return frequency;
	}

	public boolean isNf() {
		return nf;
	}

	public void setNf(boolean nf) {
		this.nf = nf;
	}

	public Integer getExpirationDay() {
		return expirationDay;
	}

	public void setExpirationDay(Integer expirationDay) {
		this.expirationDay = expirationDay;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public List<SelectedService> getServices() {
		return services;
	}

	public void setServices(List<SelectedService> services) {
		this.services = services;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public Date getModified() {
		return modified;
	}
}
