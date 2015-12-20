package com.stefanini.mav.mensagem;

public enum CodigoMensagem {
	
	C0100, C0110, C0200, C0210, C0450, C0460, C0670, C0680, C9100, C9200, C9300, C9400, C9450, C9670;
	
	public static CodigoMensagem parse(int tipo) {
		
		return parse(String.format("%04d", tipo)); 
	}
	
	
	public static CodigoMensagem parse(String s) {
		
		return Enum.valueOf(CodigoMensagem.class, String.format("C%s", s)); 
	}
	
	public String toNumberString() {
		return name().replaceAll("C", ""); 
	}
	
	public int toInt() throws NumberFormatException {
		
		return Integer.valueOf(toNumberString()); 
	}
	
	public boolean isOk() {
		
		return name().startsWith("C0") ? true : false; 
	}
	
}
