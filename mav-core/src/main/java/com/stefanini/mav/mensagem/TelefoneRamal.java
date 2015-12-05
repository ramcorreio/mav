package com.stefanini.mav.mensagem;

import com.stefanini.mav.es.MapAtributo;

public class TelefoneRamal extends Telefone {

	@MapAtributo(tamanho = 4)
	private Integer ramal;

	public Integer getRamal() {
		return ramal;
	}

	public void setRamal(Integer ramal) {
		this.ramal = ramal;
	}

}
