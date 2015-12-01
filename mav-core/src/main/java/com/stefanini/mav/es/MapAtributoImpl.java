package com.stefanini.mav.es;

import java.lang.reflect.Field;

public class MapAtributoImpl {
	
	private Field field;
	
	private MapAtributo map;

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
	
	public String getNomeMetodo() {
		
		String fName = field.getName().substring(0, 1).toUpperCase().concat(field.getName().substring(1));
		return "set".concat(fName);
	}
	
	public MapAtributo getMap() {
		return map;
	}
	
	public void setMap(MapAtributo map) {
		this.map = map;
	}
}
