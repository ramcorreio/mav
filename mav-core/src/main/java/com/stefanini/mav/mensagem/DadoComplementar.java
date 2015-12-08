package com.stefanini.mav.mensagem;

import java.io.Serializable;

public class DadoComplementar implements Serializable {

	private static final long serialVersionUID = -3527343656488827048L;

	private Boolean emancipado;

	private String codigoProduto;

	public Boolean isEmancipado() {
		return emancipado;
	}

	public void setEmancipado(Boolean emancipado) {
		this.emancipado = emancipado;
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

}
