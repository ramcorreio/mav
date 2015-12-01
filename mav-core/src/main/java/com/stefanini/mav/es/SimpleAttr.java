package com.stefanini.mav.es;

public class SimpleAttr extends AbstractAttr {
	
	private static final String SET_PREFIX = "set";
	
	public static class AttrImpl {
		
		private int tamanho;
		
		private boolean obrigatorio;
		
		private int scale;
		
		private boolean trim;
		
		private String formato;
		
		protected AttrImpl() {
		}

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
	}

	protected SimpleAttr() {
	}
	
	private Object instance;
	
	private String campo;
	
	private AttrImpl attr;
	
	public Object getInstance() {
		return instance;
	}
	
	protected void setInstance(Object instance) {
		this.instance = instance;
	}
	
	public String getCampo() {
		return campo;
	}

	protected void setCampo(String campo) {
		this.campo = campo;
	}
	
	public String getNomeMetodo() {
		
		String fName = campo.substring(0, 1).toUpperCase().concat(campo.substring(1));
		return SET_PREFIX.concat(fName);
	}
	
	public AttrImpl getAttr() {
		return attr;
	}
	
	protected void setAttr(AttrImpl attr) {
		this.attr = attr;
	}
	
	@Override
	public void invoke() {
	}
}
