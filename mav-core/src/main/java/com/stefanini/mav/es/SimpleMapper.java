package com.stefanini.mav.es;

public class SimpleMapper extends BaseMapper {

	private int tamanho;

	private boolean obrigatorio;

	private int scale;

	private boolean trim;

	private String formato;
	
	private String comparador;

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public boolean isObrigatorio() {
		return obrigatorio;
	}

	public void setObrigatorio(boolean obrigatorio) {
		this.obrigatorio = obrigatorio;
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
	
	public String getComparador() {
		return comparador;
	}
	
	public void setComparador(String comparador) {
		this.comparador = comparador;
	}
	
	@Override
	public String toString() {
		
		StringBuilder b = new StringBuilder();
		b.append("{");
		b.append(super.toString());
		b.append(", ");
		b.append("tamanho: " + tamanho);
		b.append(", ");
		b.append("obrigatorio: " + obrigatorio);
		b.append(", ");
		b.append("scale: " + scale);
		b.append(", ");
		b.append("trim: " + trim);
		b.append(", ");
		b.append("formato: " + formato);
		b.append(", ");
		b.append("comparador: " + comparador);
		b.append("}");
		
		return b.toString();
	}
}
