package com.stefanini.mav.es;

import java.util.Date;

public class MapAtributoBean {

	@MapAtributo(nome = "nome", tamanho = 25)
	private String nome;

	@MapAtributo(nome = "idade", tamanho = 3)
	private Integer idade;

	@MapAtributo(nome = "data", tamanho = 8)
	private Date data;

	@MapAtributo(nome = "temFilhos")
	private Boolean temFilhos;

	@MapAtributo(nome = "salario", tamanho = 9)
	private Double salario;

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

}
