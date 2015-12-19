package com.stefanini.mav.es;

import java.util.LinkedList;
import java.util.List;

public class BeanMapper extends ObrigatorioMapper {
	
	private List<BaseMapper> mappers = new LinkedList<>();
	
	private Boolean propagar;
	
	public List<BaseMapper> getMappers() {
		return mappers;
	}
	
	public Boolean isPropagar() {
		return propagar;
	}
	
	public void setPropagar(Boolean propagar) {
		this.propagar = propagar;
	}
	
	@Override
	public String toString() {
		
		StringBuilder b = new StringBuilder();
		b.append("{");
		b.append(super.toString());
		b.append(", ");
		b.append("mappers: " + mappers);
		b.append("}");
		
		return b.toString();
	}

}
