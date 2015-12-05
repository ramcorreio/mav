package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.MapeamentoNaoEncontrado;

/**
 * 
 * REF13. Código Retorno A0062 – Proposta Elegível REF14. Código Retorno A0063 –
 * Segue Fluxo, com pendência REF15. Código Retorno A0064 – Proposta Não
 * Elegível REF16. Código de Retorno A0057 – Proposta Com Time Out.
 * 
 * @author Rodrigo
 */
public enum StatusProposta {

	ELEGIVEL("A0062"), COM_PENDENCIA("A0063"), NAO_ELEGIVEL("A0064"), TIME_OUT("A0057");

	private String codigo;

	private StatusProposta(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public static StatusProposta parse(String codigo) throws MapeamentoNaoEncontrado {

		for (StatusProposta status : values()) {

			if (status.getCodigo().replaceAll("A006", "0").equals(codigo)) {
				return status;
			}
		}

		throw new MapeamentoNaoEncontrado(codigo + " para " + StatusProposta.class);
	}

	public Integer toInt() throws NumberFormatException {

		return Integer.valueOf(codigo.replaceAll("A006", "0"));
	}
}
