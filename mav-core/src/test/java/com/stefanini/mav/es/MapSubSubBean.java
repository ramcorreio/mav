package com.stefanini.mav.es;

import java.util.Date;

public class MapSubSubBean {

	@MapAtributo(@Mapper(tamanho = 25)) 
	private String nome;

	@MapAtributo(@Mapper(tamanho = 3))
	private Integer idade;

	@MapAtributo(@Mapper(tamanho = 8))
	private Date data;
	
	@MapAtributo
	private Boolean temFilhos;

	@MapAtributo(@Mapper(tamanho = 9))
	private Double salario;

	@MapBean({ 
		@Mapper(path = "bean.conta", tamanho = 3), 
		@Mapper(path = "bean.texto", tamanho = 10),
		@Mapper(path = "hoje", tamanho = 8) 
	})
	private SubSubBean bean;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Boolean isTemFilhos() {
		return temFilhos;
	}

	public void setTemFilhos(Boolean temFilhos) {
		this.temFilhos = temFilhos;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public SubSubBean getBean() {
		return bean;
	}

	public void setBean(SubSubBean bean) {
		this.bean = bean;
	}
}
