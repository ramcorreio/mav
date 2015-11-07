package com.stefanini.mav.mensagem;

import java.io.Serializable;

public class DadoComplementar implements Serializable {

	private static final long serialVersionUID = -3527343656488827048L;

	private boolean clienteEmancipado;

	private String codigoProduto;

	public boolean isClienteEmancipado() {
		return clienteEmancipado;
	}

	protected void setClienteEmancipado(boolean clienteEmancipado) {
		this.clienteEmancipado = clienteEmancipado;
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	protected void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

}
