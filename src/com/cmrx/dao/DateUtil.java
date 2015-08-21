package com.cmrx.dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtil {

	/**
	 * 格式化日期
	 * 
	 * @param dateStr
	 *            字符型日期
	 * @param format
	 *            格式
	 * @return 返回日期
	 */
	public static java.util.Date parseDate(String dateStr, String format) {
		java.util.Date date = null;
		try {
			java.text.DateFormat df = new java.text.SimpleDateFormat(format);
			date = df.parse(dateStr);
		} catch (Exception e) {
		}
		return date;
	}

	public static java.util.Date parseDate(String dateStr) {
		return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
	}

	public static java.util.Date parseDate(java.sql.Date date) {
		return date;
	}

	public static java.sql.Date parseSqlDate(java.util.Date date) {
		if (date != null)
			return new java.sql.Date(date.getTime());
		else
			return null;
	}

	public static java.sql.Date parseSqlDate(String dateStr, String format) {
		java.util.Date date = parseDate(dateStr, format);
		return parseSqlDate(date);
	}

	public static java.sql.Date parseSqlDate(String dateStr) {
		return parseSqlDate(dateStr, "yyyy-MM-dd");
	}

	public static java.sql.Timestamp parseTimestamp(String dateStr,
			String format) {
		java.util.Date date = parseDate(dateStr, format);
		if (date != null) {
			long t = date.getTime();
			return new java.sql.Timestamp(t);
		} else
			return null;
	}

	public static java.sql.Timestamp parseTimestamp(String dateStr) {
		return parseTimestamp(dateStr, "yyyy-MM-dd  HH:mm:ss");
	}

	/**
	 * 格式化输出日期
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            格式
	 * @return 返回字符型日期
	 */
	public static String format(java.util.Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				java.text.DateFormat df = new java.text.SimpleDateFormat(format);
				result = df.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static String format(java.util.Date date) {
		return format(date, "yyyy-MM-dd");
	}

	public static String format2(java.util.Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 返回年份
	 * 
	 * @param date
	 *            日期
	 * @return 返回年份
	 */
	public static int getYear(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.YEAR);
	}

	/**
	 * 返回月份
	 * 
	 * @param date
	 *            日期
	 * @return 返回月份
	 */
	public static int getMonth(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MONTH) + 1;
	}

	/**
	 * 返回日份
	 * 
	 * @param date
	 *            日期
	 * @return 返回日份
	 */
	public static int getDay(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回小时
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.HOUR_OF_DAY);
	}

	/**
	 * 返回分钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回秒钟
	 */
	public static int getSecond(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.SECOND);
	}

	/**
	 * 返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 返回字符型当前日期
	 * 
	 * @return 返回字符型日期
	 */
	public static String getStringCurrentDate() {
		return format(new java.util.Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 返回字符型当前日期
	 * 
	 * @return 返回字符型日期
	 */
	public static String getStringCurrentDate(String format) {
		return format(new java.util.Date(), format);
	}

	/**
	 * 返回Util.Date当前日期
	 * 
	 * @return 返回当前日期
	 */
	public static java.util.Date getCurrentDate() {
		return parseDate(getStringCurrentDate());
	}

	/**
	 * 返回Util.Date当前日期
	 * 
	 * @return 返回当前日期
	 */
	public static java.util.Date getCurrentDate(String format) {
		return parseDate(getStringCurrentDate(format),format);
	}

	/**
	 * 日期相加
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 */
	public static java.util.Date addDate(java.util.Date date, int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 日期相减
	 * 
	 * @param date
	 *            日期
	 * @param date1
	 *            日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(java.util.Date date, java.util.Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	/**
	 * 得到本周周一
	 * 
	 * @return yyyy-MM-dd
	 */
	public static java.util.Date getMondayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		return c.getTime();
	}

	/**
	 * 得到本周周日
	 * 
	 * @return yyyy-MM-dd
	 */
	public static java.util.Date getSundayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 7);
		return c.getTime();
	}

	/**
	 * 得到本月第一天,年月输入“0”表示当前年当前月
	 * 
	 * @return yyyy-MM-dd
	 */
	public static java.util.Date getFistDayOfThisMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		if (year != 0)
			c.set(Calendar.YEAR, year);
		if (month != 0)
			c.set(Calendar.MONTH, month-1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	/**
	 * 得到本月最后一天,年月输入“0”表示当前年当前月
	 * 
	 * @return yyyy-MM-dd
	 */
	public static java.util.Date getLastDayOfThisMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		if (year != 0)
			c.set(Calendar.YEAR, year);
		if (month != 0)
			c.set(Calendar.MONTH, month-1);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(c.DAY_OF_MONTH));
		return c.getTime();
	}
	
	/***
	 * 获取当前时间 yyyy-MM-dd HH:mm:ss 
	 * @return 字符串格式
	 */
	public static String getCurrentDatetimeStr(){
		return format(getCurrentDate("yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
	}
	
	/***
	 * 获取当前时间 yyyy-MM-dd HH:mm:ss 
	 * @return 字符串格式
	 */
	public static java.sql.Timestamp getCurrentDatetimeDateTime(){
		return java.sql.Timestamp.valueOf(format(getCurrentDate("yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
	}
	
	/***
	 * 获取当前时间 yyyy-MM-dd HH:mm:ss 
	 * @return java.util.Date
	 */
	public static java.util.Date getCurrentDatetime(){
		return getCurrentDate("yyyy-MM-dd HH:mm:ss");
	}
}
