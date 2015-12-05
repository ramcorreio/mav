package com.stefanini.mav.mensagem;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.stefanini.mav.es.MapeamentoNaoEncontrado;

/**
 * Classe utilizada para tetar o componente que faz o parse do código da mensagem
 * 
 */
public class StatusPropostaTest {
	
	@Test
	public void stringToTipo() throws MapeamentoNaoEncontrado{
		
		StatusProposta p = StatusProposta.parse("02");
		MatcherAssert.assertThat(StatusProposta.ELEGIVEL, Matchers.is(Matchers.equalTo(p)));
	}
	
	@Test(expected = MapeamentoNaoEncontrado.class)
	public void tipoNaoEcontrado() throws MapeamentoNaoEncontrado{
		
		StatusProposta.parse("06");
		//MatcherAssert.assertThat(StatusProposta.ELEGIVEL, Matchers.is(Matchers.equalTo(p)));
		
	}
	
	@Test
	public void tipoToInt(){
		
		MatcherAssert.assertThat(4, Matchers.is(Matchers.equalTo(StatusProposta.NAO_ELEGIVEL.toInt())));
	}
	
	/*@Test
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
	}*/

}
