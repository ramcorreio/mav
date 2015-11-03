package com.stefanini.mav.mensagem;

public enum TipoMensagem {
	
	T0100, C0110, C0450, C0460, C0670, C0680;
	
	
	public static TipoMensagem parse(String s) throws NumberFormatException {
		
		return Enum.valueOf(TipoMensagem.class, "C" + s); 
	}
	
}
