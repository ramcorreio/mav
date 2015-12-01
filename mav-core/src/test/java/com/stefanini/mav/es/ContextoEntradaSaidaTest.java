package com.stefanini.mav.es;

import java.text.ParseException;

import org.exparity.hamcrest.BeanMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import com.stefanini.mav.util.UtilsDate;

public class ContextoEntradaSaidaTest {
	
	@Test(expected = MapeamentoNaoEncontrado.class)
	public void noMap() throws MapeamentoNaoEncontrado {
		
		String entrada = "rodrigo afonso macedo    037281119781000034523";
		ContextoEntradaSaida.ler(entrada, NoMap.class);
		
	}
	
	@Test
	public void lerAtributoBean() throws MapeamentoNaoEncontrado, ParseException {
		
		String entrada = "rodrigo afonso macedo    037281119781000034523";
		MapAtributoBean expected = new MapAtributoBean();
		expected.setNome("rodrigo afonso macedo");
		expected.setIdade(37);
		expected.setData(UtilsDate.parse("28111978"));
		expected.setTemFilhos(true);
		expected.setSalario(345.23);
		
		MapAtributoBean b = ContextoEntradaSaida.ler(entrada, MapAtributoBean.class);
		MatcherAssert.assertThat(b, BeanMatchers.theSameAs(expected));
		
	}
	
	@Test
	public void lerAtributoSubBean() throws MapeamentoNaoEncontrado, ParseException {
		
		String entrada = "rodrigo afonso macedo    037281119781000034523045Opa!!!    ";
		MapSubBean expected = new MapSubBean();
		expected.setNome("rodrigo afonso macedo");
		expected.setIdade(37);
		expected.setData(UtilsDate.parse("28111978"));
		expected.setTemFilhos(true);
		expected.setSalario(345.23);
		expected.setBean(new SubBean());
		expected.getBean().setConta(45);
		expected.getBean().setTexto("Opa!!!");
		
		MapSubBean b = ContextoEntradaSaida.ler(entrada, MapSubBean.class);
		MatcherAssert.assertThat(b, BeanMatchers.theSameAs(expected));
		
	}

}
