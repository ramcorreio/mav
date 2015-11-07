package com.stefanini.mav.mensagem;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

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

}
