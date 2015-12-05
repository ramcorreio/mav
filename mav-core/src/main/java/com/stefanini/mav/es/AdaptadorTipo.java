package com.stefanini.mav.es;

public abstract class AdaptadorTipo<T> {
	
	private Class<?> tipoEnum;
	
	public Class<?> getTipoEnum() {
		return tipoEnum;
	}
	
	public void setTipoEnum(Class<?> tipoEnum) {
		this.tipoEnum = tipoEnum;
	}
	
	public abstract String escrever(Object in, SimpleMapper map) throws MapeamentoNaoEncontrado;
	
	public abstract T ler(String in, SimpleMapper map)  throws MapeamentoNaoEncontrado;

}
