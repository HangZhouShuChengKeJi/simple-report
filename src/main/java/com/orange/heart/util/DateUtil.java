package com.orange.heart.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Date parseDate(String date, String patter[]) throws ParseException {
		return DateUtils.parseDate(date, patter);
	}

	public static Date parseDate(String date, String patter) throws ParseException {
		return parseDate(date, new String[] { patter });
	}

	public static String formatDate(Date date, String patter) {
		return DateFormatUtils.format(date, patter);
	}

	public static Date parseDateYmd(String date) throws ParseException {
		return parseDate(date, "yyyy-MM-dd");
	}

	public static String formatDateYmd(Date date) {
		return DateFormatUtils.format(date, "yyyy-MM-dd");
	}

	public static String ymdFormat(Date date) {
		return formatDateYmd(date);
	}

	public static Date parseDateYmdH(String date) throws ParseException {
		return parseDate(date, "yyyy-MM-dd HH");
	}

	public static String formatDateYmdH(Date date) {
		return DateFormatUtils.format(date, "yyyy-MM-dd HH");
	}

	public static String ymdhFormat(Date date) {
		return formatDateYmdH(date);
	}

	public static Date parseDateYmdHm(String date) throws ParseException {
		return parseDate(date, "yyyy-MM-dd HH:mm");
	}

	public static String formatDateYmdHm(Date date) {
		return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm");
	}

	public static String ymdhmFormat(Date date) {
		return formatDateYmdHm(date);
	}

	public static Date parseDateYmdHms(String date) throws ParseException {
		return parseDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDateYmdHms(Date date) {
		return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String ymdhmsFormat(Date date) {
		return formatDateYmdHms(date);
	}

	public static Date parseDateHms(String date) throws ParseException {
		return parseDate(date, "HH:mm:ss");
	}

	public static String formatDateHms(Date date) {
		return DateFormatUtils.format(date, "HH:mm:ss");
	}

	public static String hmsFormat(Date date) {
		return formatDateHms(date);
	}

	public static Date parseDateHms2(String date) throws ParseException {
		return parseDate(date, "HHmmss");
	}

	public static String formatDateHms2(Date date) {
		return DateFormatUtils.format(date, "HHmmss");
	}

	public static String hms2Format(Date date) {
		return formatDateHms2(date);
	}

	public static String getDiffStringDate(Date dt, int diff) {
		Calendar ca = Calendar.getInstance();
		if (dt == null) {
			ca.setTime(new Date());
		} else {
			ca.setTime(dt);
		}
		ca.add(Calendar.DATE, diff);
		return formatDateYmd(ca.getTime());
	}

	public static String getDiffStringDateAll(Date dt, int diff) {
		Calendar ca = Calendar.getInstance();

		if (dt == null) {
			ca.setTime(new Date());
		} else {
			ca.setTime(dt);
		}

		ca.add(Calendar.DATE, diff);

		return formatDateYmdHms(ca.getTime());
	}

	private static SimpleDateFormat	simpleDateFormat	= new SimpleDateFormat();

	public static synchronized String getDate2Str(String format, Date date) {
		simpleDateFormat.applyPattern(format);
		return simpleDateFormat.format(date);
	}

	private static synchronized Date getStrToDate(String format, String str) {
		simpleDateFormat.applyPattern(format);
		ParsePosition parseposition = new ParsePosition(0);
		return simpleDateFormat.parse(str, parseposition);
	}

	public static String getDate2SStr(Date date) {
		return getDate2Str("yyyy-MM-dd HH:mm:ss", date);
	}

	public static String getDate2MStr(Date date) {
		return getDate2Str("yyyy-MM-dd HH:mm", date);
	}

	public static String getDate2HStr(Date date) {
		return getDate2Str("yyyy-MM-dd HH", date);
	}

	public static Date getStr2SDate(String str) {
		return getStrToDate("yyyy-MM-dd HH:mm:ss", str);
	}

	public static Date getStr2MDate(String str) {
		return getStrToDate("yyyy-MM-dd HH:mm", str);
	}

	public static Date getStr2HDate(String str) {
		return getStrToDate("yyyy-MM-dd HH", str);
	}

	public static Date getStr2Date(String str) {
		return getStrToDate("yyyy-MM-dd", str);
	}

	public static Date getDate2Date(Date date) {
		String dateStr = getDate2SStr(date);
		return getStr2Date(dateStr);
	}

	public static Date getDate2HDate(Date date) {
		String dateStr = getDate2SStr(date);
		return getStr2HDate(dateStr);
	}

	public static Date getDate2MDate(Date date) {
		String dateStr = getDate2SStr(date);
		return getStr2MDate(dateStr);
	}

	public static int getIntervalDaysByDay(Date beginDate, Date endDate) {
		beginDate = getDate2Date(beginDate);
		endDate = getDate2Date(endDate);
		long milliSecond = endDate.getTime() - beginDate.getTime();
		int day = (int) (milliSecond / 24L / 60L / 60L / 1000L);
		return day;
	}

	public static int getIntervalMinute(Date beginDate, Date endDate) {
		long milliSecond = endDate.getTime() - beginDate.getTime();
		int minute = (int) (milliSecond / 60L / 1000L);
		return minute;
	}

	public static int getIntervalDaysByTime(Date beginDate, Date endDate) {
		long milliSecond = endDate.getTime() - beginDate.getTime();
		int day = (int) (milliSecond / 24L / 60L / 60L / 1000L);
		return day;
	}

	public static final Date increaseDate(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, days);

		return cal.getTime();
	}
	
	public static final Date increaseDate(Date date, int field, int increase) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, increase);

		return cal.getTime();
	}

	public static final boolean dateAfterCompare(Date d1, Date d2) {
		if (d1 == null || d2 == null)
			return false;
		try {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(d1);
			Calendar c2 = Calendar.getInstance();
			c2.setTime(d2);

			long p1 = c1.getTimeInMillis();
			long p2 = c2.getTimeInMillis();
			if (p1 < p2)
				return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static final Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static String getSurplusTime(Date targetDate) {
		long time = targetDate.getTime() - new Date().getTime();
		String str = "";
		if (time >= 24 * 3600 * 1000) {
			str += (time / (24 * 3600 * 1000)) + "��";
			time = time % (24 * 3600 * 1000);
		}
		if (time >= 3600 * 1000) {
			str += (time / (3600 * 1000)) + "Сʱ";
			time = time % (3600 * 1000);
		}
		if (time >= 60 * 1000) {
			str += (time / (60 * 1000)) + "��";
			time = time % (60 * 1000);
		}
		if (time >= 1000) {
			str += (time / 1000) + "��";
			time = time % 1000;
		}
		return str;
	}

	public static Date UTCToDate(long utc) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(utc * 1000);
		return calendar.getTime();
	}

	public static String UTCToDateToString(long utc) {
		return formatDateYmdHms(UTCToDate(utc));
	}

	public static void main(String[] args) {
		try {
			System.out.println(formatDateYmdHms(UTCToDate(1389252977)));
			System.out.println(getSurplusTime(DateUtil.parseDateYmdHms("2014-02-01 10:41:22")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}