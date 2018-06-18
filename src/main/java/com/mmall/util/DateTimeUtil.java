package com.mmall.util;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeUtil {

	// joda-time
	// String to Date
	// Date to String

	/**
	 * String转换成Date
	 * 
	 * @param dateTimeStr
	 * @param formatter
	 * @return
	 */
	public static final String STAND_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static Date strToDate(String dateTimeStr) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STAND_FORMAT);
		DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
		return dateTime.toDate();
	}

	public static Date strToDate(String dateTimeStr, String formatStr) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
		DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
		return dateTime.toDate();
	}

	public static String dateToStr(Date date) {
		if (date == null) {
			return StringUtils.EMPTY;
		}
		DateTime dateTime = new DateTime(date);
		return dateTime.toString(STAND_FORMAT);
	}

	public static String dateToStr(Date date, String formatStr) {
		if (date == null) {
			return StringUtils.EMPTY;
		}
		DateTime dateTime = new DateTime(date);
		return dateTime.toString(formatStr);
	}

	public static void main(String[] args) {
		System.out.println(DateTimeUtil.dateToStr(new Date()));
		System.out.println(DateTimeUtil.strToDate("2011-01-01 10:10:10"));
	}

}
