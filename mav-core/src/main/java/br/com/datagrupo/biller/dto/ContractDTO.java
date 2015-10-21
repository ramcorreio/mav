package br.com.datagrupo.biller.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.datagrupo.biller.domain.Contract;
import br.com.datagrupo.biller.domain.HeadOffice;
import br.com.datagrupo.biller.domain.OfferedService;
import br.com.datagrupo.biller.domain.Subsidiary;

public class ContractDTO implements Serializable{

	private static final long serialVersionUID = -7379174871771240926L;

	private HeadOffice customer;

	private List<HeadOffice> customers = new ArrayList<HeadOffice>();

	private Subsidiary subsidiary;

	private List<Subsidiary> subsidiaries = new ArrayList<Subsidiary>();

	private Contract contract = new Contract();

	private List<Contract> contracts = new ArrayList<Contract>();

	private OfferedService service;

	private List<OfferedService> services = new ArrayList<OfferedService>();

	public HeadOffice getCustomer() {
		return customer;
	}

	public void setCustomer(HeadOffice customer) {
		this.customer = customer;
	}

	public List<HeadOffice> getCustomers() {
		return customers;
	}

	public void setCustomers(List<HeadOffice> customers) {
		this.customers = customers;
	}

	public Subsidiary getSubsidiary() {
		return subsidiary;
	}

	public void setSubsidiary(Subsidiary subsidiary) {
		this.subsidiary = subsidiary;
	}

	public List<Subsidiary> getSubsidiaries() {
		return subsidiaries;
	}

	public void setSubsidiaries(List<Subsidiary> subsidiaries) {
		this.subsidiaries = subsidiaries;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public OfferedService getService() {
		return service;
	}

	public void setService(OfferedService service) {
		this.service = service;
	}

	public List<OfferedService> getServices() {
		return services;
	}

	public void setServices(List<OfferedService> services) {
		this.services = services;
	}
}
