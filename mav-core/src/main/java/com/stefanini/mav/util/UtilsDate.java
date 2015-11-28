package com.stefanini.mav.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilsDate {
	
	private static final String MASCARA_CURTA = "MMyyyy";

	private static final String MASCARA_DATA = "dd".concat(MASCARA_CURTA);
	
	private static final String MASCATA_TEMPO = MASCARA_DATA.concat("HHmmss");
	
	public static final SimpleDateFormat FORMATADOR_DATA_CURTA = new SimpleDateFormat(MASCARA_CURTA);
	
	public static final SimpleDateFormat FORMATADOR_DATA = new SimpleDateFormat(MASCARA_DATA);
	
	public static final SimpleDateFormat FORMATADOR_TEMPO = new SimpleDateFormat(MASCATA_TEMPO);

	private UtilsDate() {
	}
	
	public static String format(Date date, DateFormat format) {

		return format.format(date);
	}

	public static String format(Date date) {

		return format(date, FORMATADOR_DATA);
	}
	
	public static String formatDateTime(Date date) {

		return format(date, FORMATADOR_TEMPO);
	}
	
	public static Date parse(String input) throws ParseException {
		
		return parse(input, FORMATADOR_DATA);
	}
	
	public static Date parse(String input, DateFormat format) throws ParseException {
		
		Calendar cal = parseToCalendar(input, format);
		zerarHora(cal);
		return cal.getTime();
	}
	
	public static Date parseDateHora(String input) throws ParseException {
		
		return parseToCalendar(input, FORMATADOR_TEMPO).getTime();
	}

	private static Calendar parseToCalendar(String input, DateFormat format) throws ParseException {

		Calendar cal = Calendar.getInstance();
		cal.setTime(format.parse(input));
		return cal;
	}

	protected static Calendar zerarHora(Calendar cal) {

		return iniciarHora(cal, 0, 0, 0);
	}
	
	protected static Calendar iniciarHora(Calendar cal, int hora, int minuto, int segundo) {

		cal.set(Calendar.HOUR_OF_DAY, hora);
		cal.set(Calendar.MINUTE, minuto);
		cal.set(Calendar.SECOND, segundo);
		cal.set(Calendar.MILLISECOND, 00);
		return cal;

	}

}
