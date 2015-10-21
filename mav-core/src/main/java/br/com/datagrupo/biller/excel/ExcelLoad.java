package br.com.datagrupo.biller.excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import br.com.datagrupo.biller.domain.Contract;
import br.com.datagrupo.biller.domain.Contract.Frequency;
import br.com.datagrupo.biller.domain.Customer;
import br.com.datagrupo.biller.domain.HeadOffice;
import br.com.datagrupo.biller.domain.OfferedService;
import br.com.datagrupo.biller.domain.SelectedService;
import br.com.datagrupo.biller.domain.Subsidiary;
import br.com.datagrupo.biller.dto.ContractDTO;
import br.com.datagrupo.biller.service.IContractManager;
import br.com.datagrupo.biller.service.impl.ServiceLocator;

public class ExcelLoad {
	
	private HSSFWorkbook wb; 
	
	private IContractManager manager;
	
	public ExcelLoad(HSSFWorkbook wb) {
		this.wb = wb;
		manager = ServiceLocator.getInstance().getContractManager();
	}
	
	public int getLines() throws IOException {
		
		return getSheetAt(0).getPhysicalNumberOfRows();		
	}
	
	public void close() throws IOException{
		wb.close();
	}
	
	private HSSFSheet getSheetAt(int sheet) {
		return wb.getSheetAt(sheet);
	}
	
	public <T> Customer loadCustomer(String line) {
		
		String[] values = line.split("-");
		for (int i = 0; i < values.length; i++) {
			values[i] = values[i].trim();
		}
		
		ContractDTO customer = manager.findCustomerByName(values[0]);
		if(customer.getCustomers().isEmpty()) {
			
			customer.setCustomer(new HeadOffice());
			customer.getCustomer().setName(values[0]);
			manager.createCustomer(customer);
			return customer.getCustomer();
		}
		else if(values.length == 1) {
			return customer.getCustomers().iterator().next();
		}
		
		customer = manager.getCustomer(customer.getCustomers().iterator().next().getId());
		ContractDTO subsidiary = manager.findSubsidiaryByName(customer.getCustomer(), values[1]);
		if(subsidiary.getSubsidiaries().isEmpty()) {
			
			//customer = manager.getCustomer(customer.getCustomers().iterator().next().getId());
			Subsidiary sub = new Subsidiary();
			sub.setName(values[1]);
			sub.setOffice(customer.getCustomer());
			customer.getCustomer().getSubsidiaries().add(sub);
			manager.updateCustomer(customer);
			return manager.findSubsidiaryByName(sub.getOffice(), sub.getName()).getSubsidiaries().iterator().next();
		}
		else {
			return subsidiary.getSubsidiaries().iterator().next();
		}
	}
	
	public List<Customer> loadCustomers() {
		
		List<Customer> customers = new ArrayList<Customer>(); 
		
		int lines = getSheetAt(0).getPhysicalNumberOfRows();
		for (int i = 3; i < lines; i++) {
			
			String value = getSheetAt(0).getRow(i).getCell(0).getStringCellValue();
			if(value.trim().isEmpty()) {
				break;
			}
			
			Customer customer = loadCustomer(value);
			if(customer instanceof Subsidiary || customers.contains(customer)) {
				continue;
			}
			
			customers.add(customer);
		}
		
		return customers;
	}
	
	public List<Contract> loadConstracts() {
		
		List<Contract> contracts = new ArrayList<Contract>(); 
		
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
		int lines = getSheetAt(0).getPhysicalNumberOfRows();
		for (int i = 3; i < lines; i++) {
			
			String value = getSheetAt(0).getRow(i).getCell(0).getStringCellValue();
			if(value.trim().isEmpty()) {
				break;
			}
			
			String nf = getSheetAt(0).getRow(i).getCell(1).getStringCellValue();
			Date review = getSheetAt(0).getRow(i).getCell(2).getDateCellValue();
			Date day = getSheetAt(0).getRow(i).getCell(3).getDateCellValue();
			String frequency = getSheetAt(0).getRow(i).getCell(4).getStringCellValue();
			
			HSSFCell cellValue = getSheetAt(0).getRow(i).getCell(6);
			Double baseValue = evaluator.evaluate(cellValue).getNumberValue();
			String serviceName = getSheetAt(0).getRow(i).getCell(7).getStringCellValue();
			
			
			Customer customer = loadCustomer(value);
			Subsidiary owner = null;
			if(customer instanceof HeadOffice) {
				owner = manager.findMainOwner(HeadOffice.class.cast(customer)).getSubsidiary();
			}
			else {
				owner = (Subsidiary) customer;	
			}
			
			ContractDTO dto = new ContractDTO();
			dto.setContract(new Contract());
			dto.getContract().setOwner(owner);
			dto.getContract().setNf("X".equals(nf.trim()));
			dto.getContract().setExpirationDay(1);
			dto.getContract().setExpirationDate(review);
			dto.getContract().setFrequency(Frequency.MENSAL);
			if(frequency.split("/").length == 3) {
				dto.getContract().setFrequency(Frequency.TRIMESTRAL);		
			}
			
			
			
			
			if(day != null) {
				Calendar c = Calendar.getInstance();
				c.setTime(day);
				dto.getContract().setExpirationDay(c.get(Calendar.DAY_OF_MONTH));
			}
			
			ContractDTO s = new ContractDTO();
			s.setService(new OfferedService());
			s.getService().setDescription(serviceName);
			s.getService().setDiscontinued(true);
			s.getService().setValue(baseValue);
			manager.createService(s);
			
			Random r = new Random();
			SelectedService ss = new SelectedService();
			ss.setDescription("Selected " + serviceName);
			ss.setUnitValue(s.getService().getValue());
			ss.setQuantity(r.nextInt(15));
			ss.setService(s.getService());
			
			dto.getContract().getServices().add(ss);
			manager.create(dto);
			
			contracts.add(dto.getContract());
		}
		
		return contracts;
	}

}
