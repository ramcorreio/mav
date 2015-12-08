package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.MapAtributo;

public class Endereco {

	@MapAtributo(tamanho = 40)
	private String logradouro;

	@MapAtributo(tamanho = 5)
	private String numero;

	@MapAtributo(tamanho = 15)
	private String complemento;

	@MapAtributo(tamanho = 20)
	private String bairro;

	@MapAtributo(tamanho = 20)
	private String cidade;

	@MapAtributo(tamanho = 2)
	private String uf;

	@MapAtributo(tamanho = 8)
	private Integer cep;
	
	@Override
	public boolean equals(Object obj) {
		
		if(!Endereco.class.isInstance(obj)) {
			
			return false;
		}
		
		Endereco outro = Endereco.class.cast(obj);
		
		return logradouro.equals(outro.logradouro)
				&&	numero.equals(outro.numero)
				&&	complemento.equals(outro.complemento)
				&&	bairro.equals(outro.bairro)
				&&	cidade.equals(outro.cidade)
				&&	uf.equals(outro.uf)
				&&	cep.equals(outro.cep);
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

}
