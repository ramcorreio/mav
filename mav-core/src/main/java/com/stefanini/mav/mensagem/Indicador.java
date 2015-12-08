package com.stefanini.mav.mensagem;

import java.io.Serializable;

public class Indicador implements Serializable {
	
	private static final long serialVersionUID = -1235658988407468213L;

	private String identificadorCanal;
	
	private String versaoCanal;
	
	private String politica;
	
	private String ambiente;
	
	@Override
	public boolean equals(Object obj) {
		
		if(!Indicador.class.isInstance(obj)) {
			return false;
		}
		
		Indicador outro = Indicador.class.cast(obj);
		
		return identificadorCanal.equals(outro.identificadorCanal)
				&& versaoCanal.equals(outro.versaoCanal)
				&& politica.equals(outro.politica)
				&& ambiente.equals(outro.ambiente);
	}

	public String getIdentificadorCanal() {
		return identificadorCanal;
	}

	public void setIdentificadorCanal(String identificadorCanal) {
		this.identificadorCanal = identificadorCanal;
	}

	public String getVersaoCanal() {
		return versaoCanal;
	}

	public void setVersaoCanal(String versaoCanal) {
		this.versaoCanal = versaoCanal;
	}

	public String getPolitica() {
		return politica;
	}

	public void setPolitica(String politica) {
		this.politica = politica;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}


}
