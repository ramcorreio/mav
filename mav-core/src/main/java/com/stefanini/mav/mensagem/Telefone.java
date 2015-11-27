package com.stefanini.mav.mensagem;

public class Telefone {
	
	private Integer ddd;
	
	private Integer numero;
	
	private Integer ramal;
	
	@Override
	public boolean equals(Object obj) {
		
		if(!Telefone.class.isInstance(obj)) {
			
			return false;
		}
		
		Telefone outro = Telefone.class.cast(obj);		
		return ddd.equals(outro.ddd) && numero.equals(outro.numero) && (ramal == null ? ramal == outro.ramal : ramal.equals(outro.ramal));
	}
	
	public Integer getDdd() {
		return ddd;
	}
	
	protected void setDdd(Integer ddd) {
		this.ddd = ddd;
	}
	
	public Integer getNumero() {
		return numero;
	}
	
	protected void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public Integer getRamal() {
		return ramal;
	}
	
	protected void setRamal(Integer ramal) {
		this.ramal = ramal;
	}
	

}
