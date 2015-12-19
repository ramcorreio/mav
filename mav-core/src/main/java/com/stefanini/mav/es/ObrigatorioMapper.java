package com.stefanini.mav.es;

public abstract class ObrigatorioMapper extends BaseMapper {
	
	private Boolean obrigatorio = false;
	
	public Boolean isObrigatorio() {
		return obrigatorio;
	}
	
	public void setObrigatorio(Boolean obrigatorio) {
		this.obrigatorio = obrigatorio;
	}
	
	@Override
	public String toString() {
		
		StringBuilder b = new StringBuilder();
		b.append(super.toString());
		b.append(", ");
		b.append("obrigatorio: " + obrigatorio);
		
		return b.toString();	
	}

}
