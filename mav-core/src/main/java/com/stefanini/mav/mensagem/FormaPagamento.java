package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.MapeamentoNaoEncontrado;

public enum FormaPagamento {
	
	CARNE(0),
	AVERBACAO_FOLHA(1), 
	CHEQUE_PRE(2),
	EXTRATO_ROTATIVO(3),
	EXTRATO_PARCELADO(4),
	DEBITO_CONTA(5);
	
	private Integer codigo;
	
	private FormaPagamento(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public static FormaPagamento parse(Integer in) throws MapeamentoNaoEncontrado {
		
		for (FormaPagamento formaPagamento : values()) {

			if (formaPagamento.getCodigo().equals(in)) {
				return formaPagamento;
			}
		}

		throw new MapeamentoNaoEncontrado(in + " para " + FormaPagamento.class);
	}
}
