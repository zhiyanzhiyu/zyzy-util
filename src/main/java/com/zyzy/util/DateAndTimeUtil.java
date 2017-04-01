/**
 * 
 */
package com.zyzy.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateAndTimeUtil {
	public static final String WEEK_FORMAT = "E";
	public static final String HOUR_MINUTE_FORMAT = "HHmm";
	public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_SHORT_CHINESE = "yyyy年MM月dd日";

	public static Date formatDate(String av_string) {
		SimpleDateFormat lv_sdf = new SimpleDateFormat(DEFAULT_PATTERN);
		try {
			return lv_sdf.parse(av_string);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String dateToString(Date av_date) {
		SimpleDateFormat lv_sdf = new SimpleDateFormat(DEFAULT_PATTERN);
		try {
			return lv_sdf.format(av_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat lv_sdf = new SimpleDateFormat(pattern);
		try {
			return lv_sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String dayOfWeek() {
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek < 0)
			dayOfWeek = 0;
		return dayNames[dayOfWeek];
	}

	public static String formatLongDate(Date av_date) {
		SimpleDateFormat lv_sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return lv_sdf.format(av_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String formatYearMonth(Date av_date) {
		SimpleDateFormat lv_sdf = new SimpleDateFormat("yyyy-MM");
		try {
			return lv_sdf.format(av_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date formatDate(String av_string, String format) {
		if (null == av_string || null == format) {
			return null;
		}
		SimpleDateFormat lv_sdf = new SimpleDateFormat(format);
		try {
			return lv_sdf.parse(av_string);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date DateAddDay(Date av_date, int av_days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(av_date);
		calendar.add(Calendar.DATE, av_days);
		return calendar.getTime();
	}

	public static Date getTheNextDate(Date date) {
		if (date == null)
			return null;
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		Calendar c2 = Calendar.getInstance();
		c2.set(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DATE), 0, 0, 0);
		long millis = c2.getTimeInMillis();
		millis = millis - millis % 1000 + 86400000;
		c2.setTimeInMillis(millis);
		return c2.getTime();
	}

	public static Date getTheTodayEndDate(Date date) {
		if (date == null)
			return null;
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		c1.set(Calendar.HOUR_OF_DAY, 23);
		c1.set(Calendar.MINUTE, 59);
		c1.set(Calendar.SECOND, 59);
		return c1.getTime();
	}

	public static Date getTheTodayStartDate(Date date) {
		if (date == null)
			return null;
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		return c1.getTime();
	}

	public static Date skipDateTime(Date d, int skipDay) {
		if (d == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.DATE, skipDay);
		return calendar.getTime();
	}

	public static String skipDateTime(String timeStr, int skipDay) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat lv_sdf = new SimpleDateFormat(pattern);
		try {
			Date d = lv_sdf.parse(timeStr);
			Date date = skipDateTime(d, skipDay);
			return lv_sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String skipDate(String dateStr, int skipDay) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat lv_sdf = new SimpleDateFormat(pattern);
		try {
			Date d = lv_sdf.parse(dateStr);
			Date date = skipDateTime(d, skipDay);
			return lv_sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean betweenTime(Date av_d, String av_startTime, String av_endTime) {
		SimpleDateFormat lv_sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (null == av_d)
			return false;
		try {
			String lv_today = lv_sdf.format(new Date());

			Date lv_start = formatDate(lv_today + " " + av_startTime);
			Date lv_end = formatDate(lv_today + " " + av_endTime);
			if (av_d.after(lv_start) && av_d.before(lv_end))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String patternDate(Date date, String pattern) {
		SimpleDateFormat lv_sdf = new SimpleDateFormat(pattern);
		return lv_sdf.format(date);
	}

	/**
	 * 根据当前月份得到前12个月份
	 * 
	 * @return
	 */
	public static List<String> getBefore12Month() {
		List<String> allMonth = new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			Calendar ca = Calendar.getInstance();
			ca.setTime(new Date());
			ca.add(Calendar.MONTH, -i); // 月份减1
			Date lastMonth = ca.getTime();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
			allMonth.add(sf.format(lastMonth));
		}
		return allMonth;
	}

	/**
	 * 两个日期之间相差天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getBetweenDay(Date startDate, Date endDate) {
		if (startDate == null || endDate == null)
			return 0;
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);
		long l = Math.abs(end.getTimeInMillis() - start.getTimeInMillis());
		long dateMillins = 86400000l;// 24 * 60 * 60 * 1000l;
		long d = l / dateMillins;
		return d;
	}

	public static Calendar getDayEndTime(Calendar c) {
		if (c == null)
			c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}

	public static String getDayBeforeDay(Date date, int days) {
		return getDay(date, days);
	}

	/**
	 * 
	 * @param date
	 * @param days
	 *            多少天前
	 * @return
	 */
	public static String getDay(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		Date current = date;
		calendar.setTime(current);

		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - days);
		Date yesterday = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(yesterday);
	}

	public static Date getDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		Date current = date;
		calendar.setTime(current);

		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);
		Date yesterday = calendar.getTime();
		return yesterday;
	}

	/**
	 * 查上一个月
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static String getMonthBeforeMonth(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		Date current = date;
		calendar.setTime(current);
		calendar.add(Calendar.MONTH, months);
		Date yesterday = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(yesterday);
	}

	/**
	 * 计算每月第几天
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		String day = sdf.format(date);
		return Integer.parseInt(day);
	}

	public static Date getMonth(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		Date current = date;
		calendar.setTime(current);
		calendar.add(Calendar.MONTH, months);
		Date yesterday = calendar.getTime();
		return yesterday;
	}

	/**
	 * 时间戳转换成日期格式字符串
	 * 
	 * @param seconds
	 *            精确到秒的字符串
	 * @param formatStr
	 * @return
	 */
	public static String timeStamp2Date(String seconds, String format) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return "";
		}
		if (format == null || format.isEmpty())
			format = DEFAULT_PATTERN;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds + "000")));
	}

	/**
	 * 日期格式字符串转换成时间戳
	 * 
	 * @param date
	 *            字符串日期
	 * @param format
	 *            如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static long date2TimeStamp(String date_str, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);

			Date date = sdf.parse(date_str);
			long timeStemp = date.getTime();
			return timeStemp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0l;
	}

	public static Timestamp date2TimeStamp2(String date_str, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);

			Date date = sdf.parse(date_str);
			long timeStemp = date.getTime();
			Timestamp ts = new Timestamp(timeStemp);
			return ts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取得当前时间戳（精确到秒）
	 * 
	 * @return
	 */
	public static String timeStamp() {
		long time = System.currentTimeMillis();
		String t = String.valueOf(time / 1000);
		return t;
	}

	public static void main(String[] args) {
		System.out.println(DateAddDay(new Date(), -365));
		// System.out.println(betweenTime(new Date(),"21:30:00","22:00:00"));
		System.out.println(formatLongDate(new Date()));
		System.out.println(formatLongDate(DateAddDay(new Date(), -365)));

		Calendar c2 = Calendar.getInstance();
		// c2.add(Calendar.DATE, 1);
		c2.set(Calendar.HOUR, 6);
		c2.set(Calendar.AM_PM, 0);
		System.out.println(c2.get(Calendar.YEAR));
		System.out.println(c2.get(Calendar.MONTH));
		System.out.println(c2.get(Calendar.DATE));
		System.out.println(c2.get(Calendar.AM));
		System.out.println(c2.get(Calendar.HOUR));
		System.out.println(c2.get(Calendar.MINUTE));
		System.out.println(c2.get(Calendar.SECOND));

		// System.out.println(dateToString(c2.getTime()));
		// c2.set(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH),
		// c1.get(Calendar.DATE), 0, 0, 0);

		System.out.println(24 * 60 * 60 * 1000);

		// Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.DATE, -1);
		// System.out.println(getBetweenDay(cal.getTime(), new Date()));
	}
}
