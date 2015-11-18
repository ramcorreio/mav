package com.stefanini.mav.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class UtilDateTest {

	private Date criarData(int dia, int mes, int ano) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, dia);
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.YEAR, ano);
		return UtilsDate.zerarHora(cal).getTime();
	}
	
	private Date criarDataHora(int dia, int mes, int ano, int hora, int minuto, int segundo) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, dia);
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.YEAR, ano);
		return UtilsDate.iniciarHora(cal, hora, minuto, segundo).getTime();
	}

	@Test
	public void parse() throws ParseException {

		Date esperado = criarData(2, Calendar.FEBRUARY, 1969);
		Date valor = UtilsDate.parse("02021969");
		MatcherAssert.assertThat(valor, Matchers.is(Matchers.equalTo(esperado)));
	}

	@Test
	public void format() throws ParseException {

		String esperado = "02011969";
		String valor = UtilsDate.format(criarData(2, Calendar.JANUARY, 1969));
		MatcherAssert.assertThat(valor, Matchers.is(Matchers.equalTo(esperado)));
	}
	
	@Test
	public void parseDateTime() throws ParseException {

		Date esperado = criarDataHora(2, Calendar.FEBRUARY, 1969, 18, 39, 40);
		Date valor = UtilsDate.parseDateHora("02021969183940");
		MatcherAssert.assertThat(valor, Matchers.samePropertyValuesAs(esperado));
	}
}