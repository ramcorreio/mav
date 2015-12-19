package com.stefanini.mav.es;

public class SimpleMapper extends ObrigatorioMapper {

	private int tamanho;

	private int scale;

	private boolean trim;

	private String formato;
	
	private String comparadorPositivo;
	
	private String comparadorNegativo;
	
	private boolean zeroEsquerda;

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}	

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public boolean isTrim() {
		return trim;
	}

	public void setTrim(boolean trim) {
		this.trim = trim;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}
	
	public String getComparadorPositivo() {
		return comparadorPositivo;
	}
	
	public void setComparadorPositivo(String comparadorPositivo) {
		this.comparadorPositivo = comparadorPositivo;
	}
	
	public String getComparadorNegativo() {
		return comparadorNegativo;
	}
	
	public void setComparadorNegativo(String comparadorNegativo) {
		this.comparadorNegativo = comparadorNegativo;
	}
	
	public boolean isZeroEsquerda() {
		return zeroEsquerda;
	}
	
	public void setZeroEsquerda(boolean zeroEsquerda) {
		this.zeroEsquerda = zeroEsquerda;
	}
	
	@Override
	public String toString() {
		
		StringBuilder b = new StringBuilder();
		b.append("{");
		b.append(super.toString());
		b.append(", ");
		b.append("tamanho: " + tamanho);
		b.append(", ");
		b.append("scale: " + scale);
		b.append(", ");
		b.append("trim: " + trim);
		b.append(", ");
		b.append("formato: " + formato);
		b.append(", ");
		b.append("comparadorPositivo: " + comparadorPositivo);
		b.append(", ");
		b.append("comparadorNegativo: " + comparadorNegativo);
		b.append(", ");
		b.append("zeroEsquerda: " + zeroEsquerda);
		b.append("}");
		
		return b.toString();
	}
}
