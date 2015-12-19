package com.stefanini.mav.es;


import java.text.ParseException;
import java.util.Date;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.stefanini.mav.util.UtilsDate;

/**
 * 
 * Layout das mensagens está mav/mav-core/docs/TRS_CDC-EP_V9-Layout_Completo_082015.xls
 * 
 * @author Rodrigo
 *
 */
public class AdaptadorTipoTest {
	
	
	private String ler(String input, int inicio, int tamanho) {
		
		return AdaptadorTipo.lerStringCheia(input, inicio, tamanho);
	}
	
	private AdaptadorTipo<?> getAdaptador(Class<?> clazz) {
		
		return AdaptadorTipo.adapters.get(clazz);
	}
	
	@Test
	public void lerStringCheia() throws MapeamentoNaoEncontrado {
		
		SimpleMapper map = new SimpleMapper();
		map.setTrim(false);
		
		String entrada = "008630460980008P4201170358    UILSON  A00621708940029";
		String saida = getAdaptador(String.class).ler(ler(entrada, 30, 8), map).toString();
		MatcherAssert.assertThat("UILSON  ", Matchers.equalTo(saida));
	}
	
	@Test
	public void lerString() throws MapeamentoNaoEncontrado {
		
		SimpleMapper map = new SimpleMapper();
		map.setTrim(true);
		
		String entrada = "008630460980008P4201170358    UILSON  A00621708940029";
		String saida = getAdaptador(String.class).ler(ler(entrada, 30, 8), map).toString();
		MatcherAssert.assertThat("UILSON", Matchers.equalTo(saida));
	}
	
	@Test
	public void lerIntZeroEsquerda() throws MapeamentoNaoEncontrado {
		
		String entrada = "008630460980008P4201170358    UILSON  A00621708940029";
		Integer saida = Integer.class.cast(getAdaptador(Integer.class).ler(ler(entrada, 5, 7), null));
		MatcherAssert.assertThat(460980, Matchers.equalTo(saida));
	}
	
	@Test
	public void escreverIntEspacoEsquerda() throws MapeamentoNaoEncontrado {
		
		SimpleMapper map = new SimpleMapper();
		map.setTamanho(7);
		map.setZeroEsquerda(false);
		
		String entrada = "00863  60980008P4201170358    UILSON  A00621708940029";
		String saida = getAdaptador(Integer.class).escrever(60980, map);
		MatcherAssert.assertThat(saida, Matchers.equalTo(ler(entrada, 5, 7)));
	}
	
	@Test
	public void lerBooleanTrue() throws MapeamentoNaoEncontrado {

		SimpleMapper map = new SimpleMapper();
		map.setComparadorPositivo("1");
		map.setComparadorNegativo("0");
		
		String entrada = "008630460980008P4201170358    UILSON  A00621708940029";
		Boolean saida = Boolean.class.cast(getAdaptador(Boolean.class).ler(ler(entrada, 19, 1), map));
		MatcherAssert.assertThat(true, Matchers.equalTo(saida));
	}
	
	@Test
	public void lerBooleanFalse() throws MapeamentoNaoEncontrado {
		
		SimpleMapper map = new SimpleMapper();
		map.setComparadorPositivo("1");
		map.setComparadorNegativo("0");
		
		String entrada = "008630460980008P4201170358    UILSON  A00621708940029";
		Boolean saida = Boolean.class.cast(getAdaptador(Boolean.class).ler(ler(entrada, 1, 1), map));
		MatcherAssert.assertThat(false, Matchers.equalTo(saida));
	}
	
	@Test
	public void lerBooleanNull() throws MapeamentoNaoEncontrado {
		
		String entrada = "008630460980008P4201170358    UILSON  A00621708940029";
		Boolean saida = Boolean.class.cast(getAdaptador(Boolean.class).ler(ler(entrada, 1, 1), null));
		MatcherAssert.assertThat(saida, Matchers.nullValue());
	}
	
	@Test
	public void lerData() throws ParseException, MapeamentoNaoEncontrado {
		
		SimpleMapper map = new SimpleMapper();
		map.setFormato(UtilsDate.FormatadorData.DATA.getMascara());
		Date expected = UtilsDate.parse("20151808", UtilsDate.FormatadorData.DATA);
		
		String entrada = "Xx   2508201518081502                    ";
		Date saida = Date.class.cast(getAdaptador(Date.class).ler(ler(entrada, 9, 8), map));
		MatcherAssert.assertThat(expected, Matchers.equalTo(saida));
	}
	

	@Test
	public void lerDouble() throws ParseException, MapeamentoNaoEncontrado {
		
		SimpleMapper map = new SimpleMapper();
		map.setTamanho(7);
		map.setScale(5);
		
		String input = "0649000";
		Double expected = 6.49;
		Double saida = Double.class.cast(getAdaptador(Double.class).ler(ler(input, 0, 7), map));
		MatcherAssert.assertThat(saida, Matchers.equalTo(expected));
	}
	
	@Test
	public void escreverDouble() throws ParseException, MapeamentoNaoEncontrado {
		
		SimpleMapper map = new SimpleMapper();
		map.setTamanho(7);
		map.setScale(2);
		
		Double valor = 6.77;
		String expected = "0000677";
		
		String saida = getAdaptador(Double.class).escrever(valor, map);
		MatcherAssert.assertThat(saida, Matchers.equalTo(expected));
	}
	
	
	@Test
	public void escreverString() {
		
		String entrada = "MAV";
		String expected = entrada.concat("  ");
		String saida = AdaptadorTipo.escreverString(5, entrada);
		
		MatcherAssert.assertThat(expected, Matchers.equalTo(saida));
	}
}
