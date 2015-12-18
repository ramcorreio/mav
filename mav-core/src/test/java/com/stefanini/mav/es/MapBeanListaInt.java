package com.stefanini.mav.es;

import java.util.Date;
import java.util.List;

public class MapBeanListaInt {

	@MapAtributo(tamanho = 25)
	private String nome;

	@MapAtributo(tamanho = 3)
	private Integer idade;

	@MapAtributo(tamanho = 8)
	private Date data;

	@MapAtributo
	private Boolean temFilhos;

	@MapAtributo(tamanho = 9)
	private Double salario;

	@MapBean
	private SubSubBean subSubBean;
	
	@MapLista(maxSize = 4, attr = @MapAtributo(tamanho = 15))
	private List<Integer> coisas;
	
	@MapAtributo(tamanho = 10, trim = false)
	private String filler;
	
	@MapAtributo(tamanho = 2)
	private Integer valor;

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

	public SubSubBean getSubSubBean() {
		return subSubBean;
	}

	public void setSubSubBean(SubSubBean subSubBean) {
		this.subSubBean = subSubBean;
	}
	
	public List<Integer> getCoisas() {
		return coisas;
	}
	
	public void setCoisas(List<Integer> coisas) {
		this.coisas = coisas;
	}
	
	public String getFiller() {
		return filler;
	}
	
	public void setFiller(String filler) {
		this.filler = filler;
	}
	
	public Integer getValor() {
		return valor;
	}
	
	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public static class SubBean {

		@MapAtributo(tamanho = 3)
		private Integer conta;

		@MapAtributo(tamanho = 10)
		private String texto;

		public Integer getConta() {
			return conta;
		}

		public void setConta(Integer conta) {
			this.conta = conta;
		}

		public String getTexto() {
			return texto;
		}

		public void setTexto(String texto) {
			this.texto = texto;
		}
	}

	public static class SubSubBean {

		@MapBean
		private SubBean subBean;

		@MapAtributo(tamanho = 8)
		private Date hoje;

		public SubBean getSubBean() {
			return subBean;
		}

		public void setSubBean(SubBean subBean) {
			this.subBean = subBean;
		}

		public Date getHoje() {
			return hoje;
		}

		public void setHoje(Date hoje) {
			this.hoje = hoje;
		}

	}
}
