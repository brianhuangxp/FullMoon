package com.ringcentral.fullmoon.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public final static String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";
	public final static String DEFAULT_TIME_FORMAT = "HH:mm:ss";
	public final static String DEFAULT_DATE_AND_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
	public final static String DATE_FORMAT1 = "yyyy-MM-dd";
	public final static String DATE_FORMAT2 = "yyyy-M-dd";
	public final static String DATE_FORMAT3 = "yy-M-dd";
	public final static String DATE_FORMAT4 = "yy-M-d";
	public final static String TIME_FORMAT1 = "hh:mm:ss";
	public final static String TIME_FORMAT2 = "h:m:s";
	public final static String DATE_AND_TIME_FORMAT1 = "yyyy-MM-dd HH:mm:ss";
	public final static String DATE_AND_TIME_FORMAT2 = "yyyy-MM-dd hh:mm:ss";
	public final static String DATE_AND_TIME_FORMAT3 = "yy-MM-dd HH:mm:ss";
	public final static String DATE_AND_TIME_FORMAT4 = "yy-M-dd HH:mm:ss";
	public final static String DATE_AND_TIME_FORMAT5 = "yy-M-dd hh:mm:ss";

	public static String format(String dateForamt, Date date) {
		DateFormat df = new SimpleDateFormat(dateForamt);
		return df.format(date);
	}

	public static Date parse(String dateForamt, String dateStr)
			throws ParseException {
		DateFormat df = new SimpleDateFormat(dateForamt);
		return df.parse(dateStr);
	}

	public static String formatDate(Date date) {
		return format(DEFAULT_DATE_FORMAT, date);
	}

	public static String formatTime(Date date) {
		return format(DEFAULT_TIME_FORMAT, date);
	}

	public static Date parseDateAndTime(String dateStr) throws ParseException {
		return parse(DEFAULT_DATE_AND_TIME_FORMAT, dateStr);
	}

	public static Date parseDate(String dateStr) throws ParseException {
		return parse(DEFAULT_DATE_FORMAT, dateStr);
	}

	public static Date parseTime(String dateStr) throws ParseException {
		return parse(DEFAULT_TIME_FORMAT, dateStr);
	}

	public static String formatDateAndTime(Date date) {
		return format(DEFAULT_DATE_AND_TIME_FORMAT, date);
	}

	public static Date now() {
		return (new Date());
	}

	/**
	 * ��ʱ����ϻ��ȥָ�����룬�룬�֣�ʱ���졢�»���ȣ����ر䶯���ʱ��
	 * 
	 * @param date
	 *            Ҫ�Ӽ�ǰ��ʱ�䣬�������Ϊ��ǰ����
	 * @param field
	 *            ʱ������Calendar.MILLISECOND,Calendar.SECOND,Calendar.MINUTE,<br>
	 *            Calendar.HOUR,Calendar.DATE, Calendar.MONTH,Calendar.YEAR
	 * @param amount
	 *            ��ָ��ʱ����Ӽ���ʱ������������Ϊ�ӣ�����Ϊ����
	 * @return �䶯���ʱ��
	 */
	public static Date add(Date date, int field, int amount) {
		if (date == null) {
			date = now();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);

		return cal.getTime();
	}

	public static Date addMilliSecond(Date date, int amount) {
		return add(date, Calendar.MILLISECOND, amount);
	}

	public static Date addSecond(Date date, int amount) {
		return add(date, Calendar.SECOND, amount);
	}

	public static Date addMiunte(Date date, int amount) {
		return add(date, Calendar.MINUTE, amount);
	}

	public static Date addHour(Date date, int amount) {
		return add(date, Calendar.HOUR, amount);
	}

	public static Date addDay(Date date, int amount) {
		return add(date, Calendar.DATE, amount);
	}

	public static Date addMonth(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
	}

	public static Date addYear(Date date, int amount) {
		return add(date, Calendar.YEAR, amount);
	}

}
