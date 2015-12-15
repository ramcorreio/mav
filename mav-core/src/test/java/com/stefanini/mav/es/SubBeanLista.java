package com.stefanini.mav.es;

public class SubBeanLista {

	@MapAtributo(tamanho = 2)
	private Integer id;

	@MapAtributo(tamanho = 15)
	private String descricao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
