package com.stefanini.mav.es;

public abstract class BaseMapper {
	
	private Class<?> tipo;
	
	private String nome;
	
	private String path;
	
	@Override
	public boolean equals(Object obj) {
		
		if(!BaseMapper.class.isInstance(obj)) {
			
			return false;
		}
		
		BaseMapper outro = BaseMapper.class.cast(obj);
		return path.equals(outro.path);
	}
	
	public Class<?> getTipo() {
		return tipo;
	}
	
	public void setTipo(Class<?> tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public int hashCode() {
		
		return path.hashCode();
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public String toString() {
		
		StringBuilder b = new StringBuilder();
		b.append("nome: " + nome);
		b.append(", ");
		b.append("tipo: " + tipo.getSimpleName());
		b.append(", ");
		b.append("path: " + path);
		return b.toString();
	}

}
