package com.stefanini.mav.es;

public class ListaMapper<T extends BaseMapper> extends BaseMapper {

	private int maxSize;
	
	private T mapper;
	
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	
	public int getMaxSize() {
		return maxSize;
	}
	
	public T getMapper() {
		return mapper;
	}
	
	public void setMapper(T mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public String toString() {
		
		StringBuilder b = new StringBuilder();
		b.append("{");
		b.append(super.toString());
		b.append(", ");
		b.append("maxSize: " + maxSize);
		b.append(", ");
		b.append("mapper: " + mapper);
		b.append("}");
		
		return b.toString();
	}
}
