package com.stefanini.mav.es;

public abstract class AdaptadorTipo<T> {
	
	public abstract void escrever() throws MapeamentoNaoEncontrado;
	
	public abstract T ler(String in, SimpleAttr map)  throws MapeamentoNaoEncontrado;

}
