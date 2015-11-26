package com.stefanini.mav.mensagem;

public class Endereco {

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private String cidade;

	private String uf;

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
