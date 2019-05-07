package com.lancslot.morn.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DateUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static final String YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String MMDD = "MM-dd";
    public static final String YYYYMMDD_2 = "yyyyMMdd";

    public static Date getDate(Date current) {
        long commentDate = dateToLong(current);
        long commentReplyDate = commentDate + new Random().nextInt(500) * 10000;
        long time = System.currentTimeMillis();
        if (commentReplyDate > time) {
            commentReplyDate = time;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(commentReplyDate);
        try {
            return format.parse(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long dateToLong(Date date) {
        return date == null ? 0L : date.getTime();
    }

    /**
     * 取得指定日期N天后的日期
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    public static Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();

        return currDate;
    }

    public static int getCurrentDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        return dayOfMonth;
    }

    /**
     * 时间字符串转换成日期
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date string2Date(String dateStr, String pattern) {
        try {
            DateFormat df = new SimpleDateFormat(pattern);
            return df.parse(dateStr);
        } catch (ParseException e) {
            logger.info("string2Date error:dateStr=" + dateStr, e);
        }
        return null;
    }

    public static String date2String(Date date, String pattern) {
        try {
            DateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        } catch (Exception e) {
            logger.info("date2String error:date=" + date, e);
        }
        return null;
    }

    /**
     * 当天距月底剩余多少天
     *
     * @return
     */
    public static int getRemainDays(int year, int month) {
        Date now = new Date();
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.set(Calendar.YEAR, year);
        aCalendar.set(Calendar.MONTH, month - 1);
        aCalendar.set(Calendar.DATE, aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date lastDateOfPreviousMonth = aCalendar.getTime();
        long diff = lastDateOfPreviousMonth.getTime() - now.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }


    /**
     * 本周剩余多少天
     *
     * @return
     */
    public static int getWeekRemainDays(int year, int week) {
        Date now = new Date();
        Date lastDateOfPreviousMonth = getWeekEndDate(year, week);
        long diff = lastDateOfPreviousMonth.getTime() - now.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    /**
     * 获得月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        return month + 1;
    }


    /**
     * 获得年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获得周数
     *
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        return week;
    }

    /**
     * 获得一周第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getWeekStartDate(Integer year, Integer week) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 获得一周最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getWeekEndDate(Integer year, Integer week) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return cal.getTime();
    }

    /**
     * 获得月份最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getMonthEndDate(Integer year, Integer month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * 获得月份第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getMonthStartDate(Integer year, Integer month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * 获取某个时间是一年中的第几周
     *
     * @param c
     * @return
     */
    public static int getWeekNum(Calendar c) {
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        int week = c.get(Calendar.WEEK_OF_YEAR);
        return week;
    }

    /**
     * 获取某年的最大周数
     * https://stackoverflow.com/questions/8723862/calculate-number-of-weeks-in-a-given-year
     * https://en.wikipedia.org/wiki/ISO_week_date#Calculation
     *
     * @param year
     * @return
     */
    public static int getMaxWeekNoByYear(int year) {
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, year);
//        cal.set(Calendar.MONTH, Calendar.DECEMBER);
//        cal.set(Calendar.DAY_OF_MONTH, 31);
//
//        int ordinalDay = cal.get(Calendar.DAY_OF_YEAR);
//        int weekDay = cal.get(Calendar.DAY_OF_WEEK) - 1; // Sunday = 0
//        int numberOfWeeks = (ordinalDay - weekDay + 10) / 7;
//        return numberOfWeeks;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.WEEK_OF_YEAR);
    }

    public static void main(String[] args) {
        System.out.println(addDays(new Date(),0));
    }


    /**
     * 给定年，周，判断是否属于当前周
     * 计算周的算法参考：
     * https://en.wikipedia.org/wiki/ISO_week_date#Calculation
     *
     * @param year
     * @param week
     * @return
     */
    public static boolean isCurrentWeek(Integer year, Integer week) {
        Calendar currentCalendar = Calendar.getInstance();
        int currentYear = currentCalendar.get(Calendar.YEAR);
//        int currentWeek = currentCalendar.get(Calendar.WEEK_OF_YEAR);

        int ordinalDay = currentCalendar.get(Calendar.DAY_OF_YEAR);
        int weekDay = currentCalendar.get(Calendar.DAY_OF_WEEK) - 1; // Sunday = 0
        int currentWeek = (ordinalDay - weekDay + 10) / 7;
        logger.info("currentWeek" + currentWeek);

        return year.intValue() == currentYear && week.intValue() == currentWeek;
    }

    /**
     * 给定日期，判断是否属于当前周
     * 计算周的算法参考：
     * https://en.wikipedia.org/wiki/ISO_week_date#Calculation
     *
     * @param date
     * @return
     */
    public static boolean isCurrentWeek(Date date) {
        Calendar currentCalendar = Calendar.getInstance();
        int currentYear = currentCalendar.get(Calendar.YEAR);
//        int currentWeek = currentCalendar.get(Calendar.WEEK_OF_YEAR);

        int ordinalDay = currentCalendar.get(Calendar.DAY_OF_YEAR);
        int weekDay = currentCalendar.get(Calendar.DAY_OF_WEEK) - 1; // Sunday = 0
        int currentWeek = (ordinalDay - weekDay + 10) / 7;
        logger.info("currentWeek" + currentWeek);

        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        int targetOrdinalDay = currentCalendar.get(Calendar.DAY_OF_YEAR);
        int targetWeekDay = currentCalendar.get(Calendar.DAY_OF_WEEK) - 1; // Sunday = 0
        int targetWeek = (targetOrdinalDay - targetWeekDay + 10) / 7;

        logger.info("targetWeek " + targetWeek);

        return currentWeek == targetWeek && targetYear == targetYear;
    }

    /**
     * 给定年，周，判断是否属于当前月份
     * 计算周的算法参考：
     * https://en.wikipedia.org/wiki/ISO_week_date#Calculation
     *
     * @param year
     * @param month
     * @return
     */
    public static boolean isCurrentMonth(Integer year, Integer month) {
        Calendar currentCalendar = Calendar.getInstance();
        int currentYear = currentCalendar.get(Calendar.YEAR);
        int currentMonth = currentCalendar.get(Calendar.MONTH) + 1;

        logger.info("currentMonth " + currentMonth);

        return year.intValue() == currentYear && month.intValue() == currentMonth;
    }

    /**
     * 给定年，周，判断是否属于当前月份
     * 计算周的算法参考：
     * https://en.wikipedia.org/wiki/ISO_week_date#Calculation
     *
     * @param date
     * @return
     */
    public static boolean isCurrentMonth(Date date) {
        Calendar currentCalendar = Calendar.getInstance();
        int currentYear = currentCalendar.get(Calendar.YEAR);
        int currentMonth = currentCalendar.get(Calendar.MONTH) + 1;

        logger.info("currentMonth " + currentMonth);

        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        int targetMonth = currentCalendar.get(Calendar.MONTH) + 1;

        return targetYear == currentYear && targetMonth == currentMonth;
    }

    public static Date addMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    public static boolean isToday(Date date) {
        Calendar c1 = Calendar.getInstance(); // today

        Calendar c2 = Calendar.getInstance();
        c2.setTime(date);

        return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR));
    }

    public static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}
