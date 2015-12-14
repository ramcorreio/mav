package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.MapAtributo;
import com.stefanini.mav.es.MapBean;

public class DocumentoTp1 {

	@MapAtributo(tamanho = 15)
	private String numero;

	@MapBean
	private DocumentoAttr attr;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public DocumentoAttr getAttr() {
		return attr;
	}
	
	public void setAttr(DocumentoAttr attr) {
		this.attr = attr;
	}
}
