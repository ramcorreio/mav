package br.com.datagrupo.biller.service.impl;

import java.io.Serializable;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import br.com.datagrupo.biller.domain.Contract;
import br.com.datagrupo.biller.domain.HeadOffice;
import br.com.datagrupo.biller.domain.OfferedService;
import br.com.datagrupo.biller.domain.Subsidiary;
import br.com.datagrupo.biller.dto.ContractDTO;
import br.com.datagrupo.biller.service.IContractManager;

@Service
public class ContractManager extends BaseManager implements IContractManager, Serializable {

	private static final String SUBSIDIARY_NAME_OWNER = "Matriz";

	private static final long serialVersionUID = -5457393389295796500L;
	
	private static String CONTRACT_ALL = "Contract.all";
	
	private static String OFFERED_SERVICE_ALL = "OfferedService.all";
	
	private static String HEAD_OFFICE_ALL = "HeadOffice.all";
	
	private static String HEAD_OFFICE_BY_NAME = "HeadOffice.findCustomerByName";
	
	private static String SUBSIDIARY_BY_NAME = "Subsidiary.findSubsidiaryByName";
	

	@Transactional(value = TxType.REQUIRED)
	public void create(ContractDTO contract) {
		
		create(contract.getContract());
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void update(ContractDTO contract) {
		
		update(contract.getContract());
	}

	@Transactional(value = TxType.NOT_SUPPORTED)
	public ContractDTO get(Long id) {
		
		ContractDTO dto = new ContractDTO();
		dto.setContract(get(Contract.class, id));
		Hibernate.initialize(dto.getContract().getServices());
		
		return dto;
	}
	
	@Transactional(value = TxType.NOT_SUPPORTED)
	public ContractDTO listAll() {
		
		ContractDTO dto = new ContractDTO();
		dto.setContracts(createNamedQuery(CONTRACT_ALL, Contract.class).getResultList());
		
		return dto;
	}
	
	@Transactional(value = TxType.NOT_SUPPORTED)
	public ContractDTO listAllServices() {
		
		ContractDTO dto = new ContractDTO();
		dto.setServices(createNamedQuery(OFFERED_SERVICE_ALL, OfferedService.class).getResultList());
		
		return dto;
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void createService(ContractDTO service) {
		create(service.getService());
	}
	
	@Transactional(value = TxType.NOT_SUPPORTED)
	public ContractDTO getOfferedService(Long id) {
		return null;
	}
	
	@Transactional(value = TxType.NOT_SUPPORTED)
	public ContractDTO listAllCustomers() {
		
		ContractDTO dto = new ContractDTO();
		TypedQuery<HeadOffice> q = createNamedQuery(HEAD_OFFICE_ALL, HeadOffice.class);
		dto.setCustomers(q.getResultList());
		
		return dto;
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void createCustomer(ContractDTO customer) {
		
		Subsidiary sub = new Subsidiary();
		sub.setName(SUBSIDIARY_NAME_OWNER);
		sub.setCnpj(customer.getCustomer().getCnpj());
		sub.setOffice(customer.getCustomer());
		customer.getCustomer().getSubsidiaries().add(sub);
		create(customer.getCustomer());
		Hibernate.initialize(customer.getCustomer().getSubsidiaries());
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void updateCustomer(ContractDTO customer) {
		update(customer.getCustomer());
	}
	
	@Transactional(value = TxType.NOT_SUPPORTED)
	public ContractDTO getCustomer(Long id) {
		
		ContractDTO dto = new ContractDTO();
		dto.setCustomer(get(HeadOffice.class, id));
		Hibernate.initialize(dto.getCustomer().getSubsidiaries());
		return dto;
	}
	
	@Transactional(value = TxType.NOT_SUPPORTED)
	public ContractDTO findCustomerByName(String name) {
		
		TypedQuery<HeadOffice> q = createNamedQuery(HEAD_OFFICE_BY_NAME, HeadOffice.class);
		q.setParameter("name", name);
		
		ContractDTO dto = new ContractDTO();
		dto.setCustomers(q.getResultList());
		return dto;
	}
	
	@Transactional(value = TxType.NOT_SUPPORTED)
	public ContractDTO findMainOwner(HeadOffice office) {
		
		ContractDTO dto = findSubsidiaryByName(office, SUBSIDIARY_NAME_OWNER);
		dto.setSubsidiary(dto.getSubsidiaries().iterator().next());
		dto.getSubsidiaries().clear();
		return dto;
	}
	
	@Transactional(value = TxType.NOT_SUPPORTED)
	public ContractDTO findSubsidiaryByName(HeadOffice office, String name) {
		
		TypedQuery<Subsidiary> q = createNamedQuery(SUBSIDIARY_BY_NAME, Subsidiary.class);
		q.setParameter("owner", office.getId());
		q.setParameter("name", name);
		
		ContractDTO dto = new ContractDTO();
		dto.setSubsidiaries(q.getResultList());
		return dto;
	}
}

