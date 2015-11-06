package com.stefanini.mav.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilDate {

	private static final String MASK_DATE = "ddMMyyyy";
	
	private static final String MASK_TIME = "HHmmss";
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(MASK_DATE);
	
	private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat(MASK_DATE.concat(MASK_TIME));

	private UtilDate() {
	}
	
	public static String format(Date date, DateFormat format) {

		return format.format(date);
	}

	public static String format(Date date) {

		return format(date, dateFormat);
	}
	
	public static String formatDateTime(Date date) {

		return format(date, dateTimeFormat);
	}
	
	public static Date parse(String input) throws ParseException {
		
		Calendar cal = parse(input, dateFormat);
		zerarHora(cal);
		return cal.getTime();
	}
	
	public static Date parseDateHora(String input) throws ParseException {
		
		return parse(input, dateTimeFormat).getTime();
	}

	private static Calendar parse(String input, DateFormat format) throws ParseException {

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
