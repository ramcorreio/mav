package br.com.datagrupo.biller.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import br.com.datagrupo.biller.service.IContractManager;

public final class ServiceLocator implements Serializable {

	private static final long serialVersionUID = 8815838232418882949L;

	private static ServiceLocator INSTANCE = new ServiceLocator();
	
	private enum Service {
		
		CONTRACT_MANAGER("contractManager");
		
		private String name;
		
		private Service(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}

	@Autowired
	private ApplicationContext applicationContext;

	private ServiceLocator() {
	}

	public static ServiceLocator getInstance() {
		return INSTANCE;
	}
	
	public IContractManager getContractManager() {
		
		return applicationContext.getBean(Service.CONTRACT_MANAGER.getName(), IContractManager.class);
	}
}
