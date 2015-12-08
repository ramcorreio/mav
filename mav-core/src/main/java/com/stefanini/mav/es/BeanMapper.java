package com.stefanini.mav.es;

import java.util.LinkedList;
import java.util.List;

public class BeanMapper extends BaseMapper {
	
	private List<BaseMapper> mappers = new LinkedList<>();
	
	public List<BaseMapper> getMappers() {
		return mappers;
	}
	
	@Override
	public String toString() {
		
		StringBuilder b = new StringBuilder();
		b.append("{");
		b.append(super.toString());
		b.append("}");
		
		return b.toString();
	}

}
