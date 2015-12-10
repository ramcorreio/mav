package com.stefanini.mav.es;

import java.util.Date;

import com.stefanini.mav.mensagem.Cabecalho;
import com.stefanini.mav.mensagem.Cabecalho.Fluxo;

@ConfiguracaoMensagem(inicio = 25, sentido = Fluxo.ENTRADA)
public class MapPosicaoInicio {
	
	private Cabecalho cabecalho;

	private String nome;

	@MapAtributo(tamanho = 3)
	private Integer idade;

	@MapAtributo(tamanho = 8)
	private Date data;
	
	@MapAtributo
	private Boolean temFilhos;

	@MapAtributo(tamanho = 9)
	private Double salario;
	
	public MapPosicaoInicio(Cabecalho cabecalho) {
		this.cabecalho = cabecalho;
	}
	
	public Cabecalho getCabecalho() {
		return cabecalho;
	}

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
