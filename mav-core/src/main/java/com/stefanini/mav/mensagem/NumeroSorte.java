package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.MapAtributo;

//3501 a 3505	Série do Número da Sorte 1	5	N		
//3506 a 3512	Número da Sorte 1	7	N
public class NumeroSorte {

	@MapAtributo(tamanho = 5)
	private Integer serie;

	@MapAtributo(tamanho = 7)
	private Integer numero;

	public Integer getSerie() {
		return serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}
