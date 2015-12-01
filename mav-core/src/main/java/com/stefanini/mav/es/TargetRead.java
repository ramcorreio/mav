package com.stefanini.mav.es;

import java.util.List;

public class TargetRead {
	
	private Object instance;
	
	private List<String> campos;
	
	public Object getInstance() {
		return instance;
	}
	
	public void setInstance(Object intance) {
		this.instance = intance;
	}
	
	public List<String> getCampos() {
		return campos;
	}
	
	public void setCampos(List<String> campos) {
		this.campos = campos;
	}
}
