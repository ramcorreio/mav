package br.com.datagrupo.biller.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class SelectedService implements Serializable{

	private static final long serialVersionUID = 1824890410760916662L;

	@NotNull
	@ManyToOne(optional = false)
	private OfferedService service;

	@NotNull
	@Size(min = 3, max = 200)
	@Column(nullable = false)
	private String description;

	@NotNull
	@Min(0)
	@Column(nullable = false)
	private Double unitValue;

	@NotNull
	@Min(1)
	@Column(nullable = false)
	private Integer quantity;
	
	public OfferedService getService() {
		return service;
	}

	public void setService(OfferedService service) {
		this.service = service;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(Double unitValue) {
		this.unitValue = unitValue;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
