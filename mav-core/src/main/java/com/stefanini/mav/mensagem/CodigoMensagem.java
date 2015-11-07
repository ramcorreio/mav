package com.stefanini.mav.mensagem;

public enum CodigoMensagem {
	
	C0100, C0110, C0450, C0460, C0670, C0680;
	
	public static CodigoMensagem parse(int tipo) throws NumberFormatException {
		
		return parse(String.format("%04d", tipo)); 
	}
	
	
	public static CodigoMensagem parse(String s) throws NumberFormatException {
		
		return Enum.valueOf(CodigoMensagem.class, String.format("C%s", s)); 
	}
	
	public String toNumberString() throws NumberFormatException {
		return name().replaceAll("C", ""); 
	}
	
	public int toInt() throws NumberFormatException {
		
		return Integer.valueOf(toNumberString()); 
	}
	
}
