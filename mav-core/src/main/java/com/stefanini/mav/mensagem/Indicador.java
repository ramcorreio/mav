package com.stefanini.mav.mensagem;

import java.io.Serializable;

public class Indicador implements Serializable {
	
	private static final long serialVersionUID = -1235658988407468213L;

	private String identificadorCanal;
	
	private String versaoCanal;
	
	private String politica;
	
	private String ambiente;

	public String getIdentificadorCanal() {
		return identificadorCanal;
	}

	protected void setIdentificadorCanal(String identificadorCanal) {
		this.identificadorCanal = identificadorCanal;
	}

	public String getVersaoCanal() {
		return versaoCanal;
	}

	protected void setVersaoCanal(String versaoCanal) {
		this.versaoCanal = versaoCanal;
	}

	public String getPolitica() {
		return politica;
	}

	protected void setPolitica(String politica) {
		this.politica = politica;
	}

	public String getAmbiente() {
		return ambiente;
	}

	protected void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}


}
