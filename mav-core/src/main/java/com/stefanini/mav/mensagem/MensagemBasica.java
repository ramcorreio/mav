package com.stefanini.mav.mensagem;

import java.io.Serializable;

public abstract class MensagemBasica implements Serializable {
	
	private static final long serialVersionUID = 203738241751201880L;
	
	private String id;
	
	private Cabecalho cabecalho;
	
	protected MensagemBasica(String id, Cabecalho cabecalho) {
		this.id = id;
		this.cabecalho = cabecalho;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(!MensagemBasica.class.isInstance(obj)) {
			return false;
		}
		
		return MensagemBasica.class.cast(obj).id.equals(this.id);
	}
	
	public String getId() {
		return id;
	}
	
	protected void setId(String id) {
		this.id = id;
	}
	
	public Cabecalho getCabecalho() {
		return cabecalho;
	}
	
	protected void setCabecalho(Cabecalho cabecalho) {
		this.cabecalho = cabecalho;
	}
}
