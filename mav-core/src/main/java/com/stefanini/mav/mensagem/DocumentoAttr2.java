package com.stefanini.mav.mensagem;

import java.util.Date;

import com.stefanini.mav.es.MapAtributo;

public class DocumentoAttr2 extends AttrComum {

	@MapAtributo(tamanho = 8, obrigatorio = true)
	private Date dataEmissao;

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

}
