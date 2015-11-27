package com.stefanini.mav.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilsDate {
	
	private static final String MASK_SHORT = "MMyyyy";

	private static final String MASK_DATE = "dd".concat(MASK_SHORT);
	
	private static final String MASK_TIME = MASK_DATE.concat("HHmmss");
	
	public static final SimpleDateFormat DATE_FORMAT_SHORT = new SimpleDateFormat(MASK_SHORT);
	
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(MASK_DATE);
	
	public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(MASK_TIME);

	private UtilsDate() {
	}
	
	public static String format(Date date, DateFormat format) {

		return format.format(date);
	}

	public static String format(Date date) {

		return format(date, DATE_FORMAT);
	}
	
	public static String formatDateTime(Date date) {

		return format(date, DATE_TIME_FORMAT);
	}
	
	public static Date parse(String input) throws ParseException {
		
		return parse(input, DATE_FORMAT);
	}
	
	public static Date parse(String input, DateFormat format) throws ParseException {
		
		Calendar cal = parseToCalendar(input, format);
		zerarHora(cal);
		return cal.getTime();
	}
	
	public static Date parseDateHora(String input) throws ParseException {
		
		return parseToCalendar(input, DATE_TIME_FORMAT).getTime();
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
