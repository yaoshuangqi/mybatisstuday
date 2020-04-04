/* 
 * Copyright (c) 2015, QUANRONG E-COMMDERCE LTD. All rights reserved.
 */
package com.jdkcb.mybatisstuday.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author qr
 * @version 2015-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}


	public static String formatDate(Date date,String forDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(forDate);
		return sdf.format(date);
	}

	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到时分
	 */
	public static String getTimeHS(Date date) {
		return formatDate(date, "HH:mm");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}


	/**
	 * 获取两个日期之间的所有月份
	 * @param minDate
	 * @param maxDate
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMM");//格式化为年月

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(sdf.parse(minDate));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		max.setTime(sdf.parse(maxDate));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf1.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}

		return result;
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}


    /**
     *  获得指定日期的前后天
     *
     * @author 彭清龙
     * @date 2018/8/15 11:45
     * @param [date, Year]
     *               Year 前后年数 -1 : 前一天 1: 后一天
     * @return java.util.Date
     */
    public static Date getSpecifiedYear(Date date, int Year)  {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, Year);
        return c.getTime();
    }
    /**
     * 获取指定日期的前一天  pql 改动
     * @return
     */
    public static Date getSpecifiedDayBefore(Date date) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE,day-1);
        return c.getTime();
    }

    /**
     *  获得指定日期的前后天
     *
     * @author 彭清龙
     * @date 2018/8/15 11:45
     * @param [date, day]
     *               day 前后天数 -1 : 前一天 1: 后一天
     * @return java.util.Date
     */

    public static Date getSpecifiedDay(Date date, int day)  {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

	/**
	 * 获取当前日期的前一天  吴长明改动
	 * @return
	 */
	public static String getSpecifiedDayBefore(String... pattern) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE,day-1);
		return formatDate(c.getTime(),pattern);
	}

	/**
	 * 获取指定日期的前一天  llx 改动
	 * @return
	 */
	public static String getyesterdayDate(Date date) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE,day-1);
			return formatDate(c.getTime(),"yyyy-MM-dd");
		}catch (Exception e){
			e.printStackTrace();
			return formatDate(new Date(),"yyyy-MM-dd");
		}
	}
	/**
	 * 获取指定日期的前一月  gyf 改动
	 * @return
	 */
	public static String getPrevMonth (Date date,SimpleDateFormat sdf) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		return sdf.format(calendar.getTime());
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	/**
	 * 获取两个日期之间的天数
	 *
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(String before, String after) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date beforeD = sdf.parse(before);
			Date parseA = sdf.parse(after);
			long beforeTime = beforeD.getTime();
			long afterTime = parseA.getTime();
			return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 获取两个日期之间的小时
	 *
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoHours(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60.0);
	}


	public static final int daysBetween(Date early, Date late) {

		java.util.Calendar calst = java.util.Calendar.getInstance();
		java.util.Calendar caled = java.util.Calendar.getInstance();
		calst.setTime(early);
		caled.setTime(late);
		//设置时间为0时
		calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
		calst.set(java.util.Calendar.MINUTE, 0);
		calst.set(java.util.Calendar.SECOND, 0);
		caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
		caled.set(java.util.Calendar.MINUTE, 0);
		caled.set(java.util.Calendar.SECOND, 0);
		//得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
				.getTime().getTime() / 1000)) / 3600 / 24;

		return days;
	}
	private static void setMinTime(Calendar calendar){
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

	public static Date getBeginOfDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		setMinTime(calendar);
		return calendar.getTime();
	}

	public static Date getPrevDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}

	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 获取某日期的月份
	 * @param date
	 * @return
	 */
	public static Integer getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取某日期的日数
	 * @param date
	 * @return
	 */
	public static Integer getDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day=cal.get(Calendar.DATE);//获取日
		return day;
	}

	/**
	 * 获取当前月份的天数
	 * @return
     */
	public static Integer getCurrMonthDays() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		return a.get(Calendar.DATE);
	}

	/**
	 * 获得日期的天数
	 * @param date
	 * @return
     */
	public static Integer getMonthDays(String date) {
		Date _date = parseDate(date);
		Calendar a = Calendar.getInstance();
		a.setTime(_date);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		return a.get(Calendar.DATE);
	}
	/**
	 * 是否为同一年（同一月，同一天）
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDate(Date date1, Date date2) {
		try {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);

			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);

			boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);

			boolean isSameMonth = isSameYear
					&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
			/*boolean isSameDate = isSameMonth
					&& cal1.get(Calendar.DAY_OF_MONTH) == cal2
					.get(Calendar.DAY_OF_MONTH);*/

			return isSameMonth;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 *获取某个月的第一天
	 * @return
	 */
	public static String getMinDayInMonth(String date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(date));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return formatDate(calendar.getTime());
	}

	/**
	 *获取某个月的最后一天
	 * @return
	 */
	public static String getMaxDayInMonth(String date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(date));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return formatDate(calendar.getTime());
	}

	/**
	 * 获取下一个日期的当前时间
	 * @param date
	 * @return
     */
	public static Date getNextDayDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH,+1);
		//System.out.print(formatDate(cal.getTime(),"yyyy-MM-dd HH:mm:ss"));
		return cal.getTime();
	}

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));


		/*Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate("2018-03-05"));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date date = calendar.getTime();
		System.out.print(formatDate(date));*/

    }


	public static String getYesterdayStartTime(Date beginDate) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = "";
		try {
			dateStr = sdf.format(DateUtils.getSpecifiedDayBefore(beginDate));
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		return dateStr+" 00:00:00.000";
	}

	public static String getYesterdayEndTime(Date endDate) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = "";
		try {
			dateStr = sdf.format(DateUtils.getSpecifiedDayBefore(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		return dateStr+" 23:59:59.999";
	}


	public static String getStartTime(Date beginDate) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(beginDate)+" 00:00:00.000";
	}

	public static String getEndTime(Date endDate) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(endDate)+" 23:59:59.999";
	}

	/**
	 * 获取某日期的月份
	 * @param date
	 * @return
	 */
	public static String getMonth(String date) {
		String[] str = date.split("-");
		String month = str[1];
		if (month.startsWith("0")) {
			return month.replace("0", "");
		} else {
			return month;
		}
	}


	/**
	 * 计算两个时间相差多少个年
	 *
	 * @return
	 * @throws ParseException
	 */
	public static int yearsBetween(String start, String end) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		DateFormat df = DateFormat.getDateInstance();
		Date earlydate = new Date();
		Date latedate = new Date();
		try {
			startDate.setTime(sdf.parse(start));
			endDate.setTime(sdf.parse(end));
		}catch (Exception e){
			return 0;
		}
		return (endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR));
	}

	/**
	 * 根据身份证获得年龄
	 * @param IdNO
	 * @return
     */
	public static Integer IdNOToAge(String IdNO){
		int leh = IdNO.length();
		String dates="";
		if (leh == 18) {
			//int se = Integer.valueOf(IdNO.substring(leh - 1)) % 2;
			dates = IdNO.substring(6, 10);
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			String year=df.format(new Date());
			int u=Integer.parseInt(year)-Integer.parseInt(dates);
			return u;
		}else{
			dates = IdNO.substring(6, 8);
			return Integer.parseInt(dates);
		}

	}

	/**
	 * @Description: 返回日期之间相差月份
	 * @Param: [minDate, maxDate]
	 * @return: java.util.List<java.lang.String>
	 * @Author: 高一帆
	 *  加了啥东西记得跟我说一声哈
	 * @Date: 2019/4/2
	 */
	public static int getMonthCount(Date minDate, Date maxDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

		String sMinDate = sdf.format(minDate);
		String sMaxDate = sdf.format(maxDate);

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(sdf.parse(sMinDate));
		max.setTime(sdf.parse(sMaxDate));

		int month = max.get(Calendar.MONTH) - min.get(Calendar.MONTH);
		int year = (max.get(Calendar.YEAR) - min.get(Calendar.YEAR)) * 12;
		return Math.abs(month + year);
	}
	/**
	 * @Description: 返回日期之间相差月份
	 * @Param: [minDate, maxDate]
	 * @return: java.util.List<java.lang.String>
	 * @Author: 高一帆
	 *  加了啥东西记得跟我说一声哈
	 * @Date: 2019/4/2
	 */
	public static int getMonthCount(String minDate, String maxDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月


		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(sdf.parse(minDate));
		max.setTime(sdf.parse(maxDate));

		int month = max.get(Calendar.MONTH) - min.get(Calendar.MONTH);
		int year = (max.get(Calendar.YEAR) - min.get(Calendar.YEAR)) * 12;
		return Math.abs(month + year);
	}

	/**
	 * 返回日期相差的分钟
	 */
	public static Long getMinuteCount(Date InDate,Date outDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");//格式化为年月
		Long minute = 1L;
		try {
			minute = (sdf.parse(sdf.format(outDate)).getTime() - sdf.parse(sdf.format(InDate)).getTime()) / 1000 / 60;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return minute;
	}


	/**
	* @Description: 根据日期获取表名
	* @Param: [date]
	* @return: java.lang.String
	* @Author: 高一帆
	*  加了啥东西记得跟我说一声哈
	* @Date: 2019/4/23
	*/
	public static String getTable(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyMM");//格式化为年月
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date dNow = Calendar.getInstance().getTime();
			String sNow = sdf.format(dNow);
			String s = null;
			s = sdf.format(dateFormat.parse(date));

			if (!s.equals(sNow)){
				return "_"+s;
			}
			return " ";
		} catch (ParseException e) {
			return " ";
		}
	}
	/**
	* @Description: 根据日期获取表名
	* @Param: [date]
	* @return: java.lang.String
	* @Author: 高一帆
	*  加了啥东西记得跟我说一声哈
	* @Date: 2019/4/23
	*/
	public static String getTable(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMM");//格式化为年月
		Date dNow = Calendar.getInstance().getTime();
		String sNow = sdf.format(dNow);
		String s = sdf.format(date);
		if (!s.equals(sNow)){
			return "_"+s;
		}
		return " ";
	}

    public static List<String> getTables(String startDate, String endDate) {
		List<String> tables=new ArrayList<>();
		try {
			int month = DateUtils.getMonthCount(startDate,endDate);
			//解决分表问题
			SimpleDateFormat format = new SimpleDateFormat("yyMM");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dNow = Calendar.getInstance().getTime();
			Calendar calendar=Calendar.getInstance();
			//将最小时间转成日期
			Date parse = sdf.parse(startDate );
			calendar.setTime(parse);

			String sNow = format.format(dNow);
//x循环添加需要查询的表
			for (int i = 0; i <= month ; i++) {
				String table = format.format(calendar.getTime());
				if (!table.equals(sNow)){
					tables.add("_"+table);
				}else {
					tables.add(" ");
				}
				calendar.add(Calendar.MONTH,1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tables;
	}
    public static List<String> getTables(Date startDate, Date endDate) {
		List<String> tables=new ArrayList<>();
		try {
			int month = DateUtils.getMonthCount(startDate,endDate);
			//解决分表问题
			SimpleDateFormat format = new SimpleDateFormat("yyMM");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dNow = Calendar.getInstance().getTime();
			Calendar calendar=Calendar.getInstance();
			//将最小时间转成日期
			Date parse = sdf.parse(sdf.format(startDate));
			calendar.setTime(parse);
			String sNow = format.format(dNow);
//x循环添加需要查询的表
			for (int i = 0; i <= month ; i++) {
				String table = format.format(calendar.getTime());
				if (!table.equals(sNow)){
					tables.add("_"+table);
				}else {
					tables.add(" ");
				}
				calendar.add(Calendar.MONTH,1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tables;
	}

}
