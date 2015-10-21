package br.com.datagrupo.biller.service;

import br.com.datagrupo.biller.domain.HeadOffice;
import br.com.datagrupo.biller.dto.ContractDTO;

public interface IContractManager {
	
	public void create(ContractDTO contract);
	
	public void update(ContractDTO contract);
	
	public ContractDTO get(Long id);

	public ContractDTO listAll();
	
	public ContractDTO listAllServices();
	
	public void createService(ContractDTO service);
	
	public ContractDTO getOfferedService(Long id);
	
	public ContractDTO listAllCustomers();
	
	public void createCustomer(ContractDTO customer);
	
	public void updateCustomer(ContractDTO customer);
	
	public ContractDTO getCustomer(Long id);

	public ContractDTO findCustomerByName(String name);
	
	public ContractDTO findMainOwner(HeadOffice office);
	
	public ContractDTO findSubsidiaryByName(HeadOffice office, String name);
}
