package com.stefanini.mav.mensagem;

import java.io.Serializable;

public class DadoComplementar implements Serializable {

	private static final long serialVersionUID = -3527343656488827048L;

	private boolean emancipado;

	private String codigoProduto;

	public boolean isEmancipado() {
		return emancipado;
	}

	protected void setEmancipado(boolean emancipado) {
		this.emancipado = emancipado;
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	protected void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

}
