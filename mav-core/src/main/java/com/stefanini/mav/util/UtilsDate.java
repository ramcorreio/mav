package com.stefanini.mav.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UtilsDate {
	
	public enum FormatadorData {
		
		DATA_CURTA("MMyyyy"), DATA("ddMMyyyy"), DATA_TEMPO("ddMMyyyyHHmmss");
		
		private String mascara;
		
		private DateFormat formatador;
		
		private FormatadorData(String mascara) {
			this.mascara = mascara;
			this.formatador = new SimpleDateFormat(mascara);
		}
		
		public String getMascara() {
			return mascara;
		}
		
		public DateFormat getFormatador() {
			return formatador;
		}
	}
	
	public static final Map<String, FormatadorData> FORMATADORES = new HashMap<>(); 
	
	static {
		FORMATADORES.put(FormatadorData.DATA_CURTA.getMascara(), FormatadorData.DATA_CURTA);
		FORMATADORES.put(FormatadorData.DATA.getMascara(), FormatadorData.DATA);
		FORMATADORES.put(FormatadorData.DATA_TEMPO.getMascara(), FormatadorData.DATA_TEMPO);
	}

	private UtilsDate() {
	}
	
	public static String format(Date date, DateFormat format) {

		return format.format(date);
	}

	/*public static String format(Date date) {

		return format(date, FormatadorData.DATA.getFormatador());
	}*/
	
	/*public static String formatDateTime(Date date) {

		return format(date, FormatadorData.DATA_TEMPO.getFormatador());
	}*/
	
	/*public static Date parse(String input) throws ParseException {
		
		return parse(input, FormatadorData.DATA.getFormatador());
	}*/
	
	public static Date parse(String input, FormatadorData format) throws ParseException {
		
		return parse(input, format.getFormatador());
	}
	
	public static Date parse(String input, DateFormat format) throws ParseException {
		
		Calendar cal = parseToCalendar(input, format);
		//zerarHora(cal);
		return cal.getTime();
	}
	
	/*public static Date parseDateHora(String input) throws ParseException {
		
		return parseToCalendar(input, FormatadorData.DATA_TEMPO.getFormatador()).getTime();
	}*/

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
