package com.stefanini.mav.mensagem;

import java.io.Serializable;

public abstract class MensagemBasica implements Serializable {
	
	private static final long serialVersionUID = 203738241751201880L;
	
	private Cabecalho cabecalho;
	
	protected MensagemBasica(Cabecalho cabecalho) {
		this.cabecalho = cabecalho;
	}
	
	public Cabecalho getCabecalho() {
		return cabecalho;
	}
}
