package com.stefanini.mav.mensagem;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Classe utilizada para tetar o componente que faz o parse do código da mensagem
 * 
 */
public class CodigoMensagemParseTest {
	
	@Test
	public void intToTipo(){
		
		CodigoMensagem t = CodigoMensagem.parse(110);
		MatcherAssert.assertThat(CodigoMensagem.C0110, Matchers.is(Matchers.equalTo(t)));
	}
	
	@Test
	public void stringToTipo(){
		
		CodigoMensagem t = CodigoMensagem.parse("0110");
		MatcherAssert.assertThat(CodigoMensagem.C0110, Matchers.is(Matchers.equalTo(t)));
		
	}
	
	@Test
	public void tipoToInt(){
		
		MatcherAssert.assertThat(110, Matchers.is(Matchers.equalTo(CodigoMensagem.C0110.toInt())));
	}
	
	@Test
	public void tipoToString(){
		
		MatcherAssert.assertThat("0110", Matchers.is(Matchers.equalTo(CodigoMensagem.C0110.toNumberString())));
	}
	
	@Test
	public void isOK(){
		
		MatcherAssert.assertThat(true, Matchers.is(Matchers.equalTo(CodigoMensagem.C0100.isOk())));
	}
	
	@Test
	public void isNotOK(){
		
		MatcherAssert.assertThat(false, Matchers.is(Matchers.equalTo(CodigoMensagem.C9100.isOk())));
	}

}
