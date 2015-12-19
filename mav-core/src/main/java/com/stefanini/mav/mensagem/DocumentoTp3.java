package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.MapAtributo;
import com.stefanini.mav.es.MapBean;

public class DocumentoTp3 {

	@MapAtributo(tamanho = 10)
	private String numero;

	@MapBean
	private DocumentoAttr2 attr;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public DocumentoAttr2 getAttr() {
		return attr;
	}
	
	public void setAttr(DocumentoAttr2 attr) {
		this.attr = attr;
	}
}
