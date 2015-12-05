package com.stefanini.mav.es;

import java.util.Date;

public class SubBeanHerdado extends SuperBean {

	private SubBean subBean;

	@MapAtributo(tamanho = 8)
	private Date hoje;
	
	public SubBean getSubBean() {
		return subBean;
	}
	
	public void setSubBean(SubBean subBean) {
		this.subBean = subBean;
	}

	public Date getHoje() {
		return hoje;
	}

	public void setHoje(Date hoje) {
		this.hoje = hoje;
	}
	
	public static class SubSubBean {

		private String nome;

		public String getNome() {
			return nome;
		}
		
		public void setNome(String nome) {
			this.nome = nome;
		}

	}
	
	public static class SubBean {

		private SubSubBean subSubBean;
		
		private String nome;
		
		public SubSubBean getSubSubBean() {
			return subSubBean;
		}
		
		public void setSubBean(SubSubBean subSubBean) {
			this.subSubBean = subSubBean;
		}

		public String getNome() {
			return nome;
		}
		
		public void setNome(String nome) {
			this.nome = nome;
		}

	}
}
