package com.stefanini.mav.mensagem;

public class DadoComplementar {

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
