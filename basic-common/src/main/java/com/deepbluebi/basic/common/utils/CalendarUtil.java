package com.deepbluebi.basic.common.utils;


import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述：日期时间转换工具类
 *
 * @author zhanghao
 * @create 2018-11-15-11:35
 */
public class CalendarUtil {

    /**
     * 一天的毫秒数
     */
    public static final int MS_ONE_DAY = 24 * 60 * 60 * 1000;


    public static String toStr_yyyyMMddHHmmss(Date date) {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    }

    public static String toStr_yyyy_MM_dd_HH_mm_ss(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static String toStr_yyyy_MM_dd_HH_mm(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
    }

    public static String toStr_yyyy_MM_dd(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static String toStr_yyyyMMdd(Date date) {
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    public static String toStr_HH_mm(Date date) {
        return new SimpleDateFormat("HH:mm").format(date);
    }

    public static String toStr(Date date, String formatString) {
        return new SimpleDateFormat(formatString).format(date);
    }

    public static Date parse_yyyy_MM_dd(String dateStr) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
    }

    public static Date parse_yyyy_MM_dd_HH_mm_ss(String dateStr) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
    }

    public static Date parse_HH_mm_ss(String time) throws ParseException {
        return new SimpleDateFormat("HH:mm:ss").parse(time);
    }

    public static Date parse_HH_mm(String time) throws ParseException {
        return new SimpleDateFormat("HH:mm").parse(time);
    }


    /**
     * 获取当前时间星期几的数字
     * 注意 周日返回是 0
     *
     * @param date
     * @return
     */
    public static int getWeekOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (week < 0) {
            week = 0;
        }
        return week;
    }

    /**
     * 获取当前时间星期几 中文
     *
     * @param date
     * @return
     */
    public static String getWeekOfDateChinese(Date date) {
        String[] a = {"日", "一", "二", "三", "四", "五", "六"};
        return a[getWeekOfDate(date)];
    }


    /**
     * 根据开始时间 和 结束时间 String 获取所有时间
     *
     * @param beginDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static List<Date> getBetweenDateStr(String beginDate, String endDate) throws ParseException {
        List<Date> dateList = new ArrayList<>();
        Date beginDay = CalendarUtil.parse_yyyy_MM_dd(beginDate);
        Date endDay = CalendarUtil.parse_yyyy_MM_dd(endDate);

        Date diffDay = beginDay;
        while (diffDay.getTime() <= endDay.getTime()) {
            dateList.add(diffDay);
            diffDay = new Date(diffDay.getTime() + MS_ONE_DAY);
        }
        return dateList;
    }


    /**
     * 根据开始时间 和 结束时间 获取所有时间
     *
     * @param beginDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static List<Date> getBetweenDate(Date beginDate, Date endDate) {
        List<Date> dateList = new ArrayList<>();
        Date diffDay = beginDate;
        while (diffDay.getTime() <= endDate.getTime()) {
            dateList.add(diffDay);
            diffDay = new Date(diffDay.getTime() + MS_ONE_DAY);
        }
        return dateList;
    }

    /**
     * 获取i天后的时间
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getNextDaysOfDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        date = calendar.getTime();
        return date;
    }


    /**
     * 例：
     * 将 2018-12-17
     * 时间 28:45 转换成 2018-12-18 04:45:00
     *
     * @param date
     * @param time
     * @return
     * @throws ParseException
     */
    public static String getSmallFullDateByDayAndTime(Date date, String time) {
        if (time.compareTo("24:00") > 0) {
            time = String.format("%02d", (Integer.parseInt(time.split(":")[0]) - 24))
                    + ":" + time.split(":")[1];
            date = getNextDaysOfDate(date, 1);
        }
        return toStr_yyyy_MM_dd(date) + " " + time + ":00";
    }

    /**
     * 例：
     * 将 2018-12-17
     * 时间 28:45 转换成 2018-12-18 04:45:59
     *
     * @param date
     * @param time
     * @return
     * @throws ParseException
     */
    public static String getBigFullDateByDayAndTime(Date date, String time) {
        if (time.compareTo("24:00") > 0) {
            time = String.format("%02d", (Integer.parseInt(time.split(":")[0]) - 24))
                    + ":" + time.split(":")[1];
            date = getNextDaysOfDate(date, 1);
        }
        return toStr_yyyy_MM_dd(date) + " " + time + ":59";
    }

    /**
     * 验证时间字符串格式输入是否正确
     *
     * @param timeStr
     * @return
     */
    public static boolean valiDateTimeWithLongFormat(String timeStr) {
        String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) "
                + "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(timeStr);
        if (matcher.matches()) {
            pattern = Pattern.compile("(\\d{4})-(\\d+)-(\\d+).*");
            matcher = pattern.matcher(timeStr);
            if (matcher.matches()) {
                int y = Integer.valueOf(matcher.group(1));
                int m = Integer.valueOf(matcher.group(2));
                int d = Integer.valueOf(matcher.group(3));
                if (d > 28) {
                    Calendar c = Calendar.getInstance();
                    c.set(y, m - 1, 1);
                    int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                    return (lastDay >= d);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 验证时间字符串格式输入是否正确 格式 '2019-10-11'
     *
     * @param timeStr
     * @return
     */
    public static boolean valiDateTimeWithNoTimeFormat(String timeStr) {
        String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(timeStr);
        if (matcher.matches()) {
            pattern = Pattern.compile("(\\d{4})-(\\d+)-(\\d+).*");
            matcher = pattern.matcher(timeStr);
            if (matcher.matches()) {
                int y = Integer.valueOf(matcher.group(1));
                int m = Integer.valueOf(matcher.group(2));
                int d = Integer.valueOf(matcher.group(3));
                if (d > 28) {
                    Calendar c = Calendar.getInstance();
                    c.set(y, m - 1, 1);
                    int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                    return (lastDay >= d);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * @param target 需要被比较的时间
     * @param source 比较时间
     *               前面大于后面返回true，否则返回false
     * @return 比如target 2019-03-13 12:00:00 source 2019-03-13 8:00:00 返回true
     */
    public static boolean compareDate(String target, String source) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        boolean ok = false;
        try {
            Date dt1 = df.parse(target);
            Date dt2 = df.parse(source);
            if (dt1.getTime() > dt2.getTime()) {
                ok = true;
            } else {
                ok = false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            ok = false;
        }
        return ok;
    }

    /**
     * 间隔时间
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static long intervalTime(String beginTime, String endTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long intervalTime = 0L;
        try {
            intervalTime = format.parse(endTime).getTime() - format.parse(beginTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return intervalTime;
    }

    /* 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        if (date.after(begin) && date.before(end)) {
            return true;
        } else if (nowTime.compareTo(beginTime) == 0 || nowTime.compareTo(endTime) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断时间是否在时间范围内，格式 08:00-18:00
     *
     * @param beginTime  HH:mm
     * @param endTime    HH:mm
     * @param accessDate 时间
     */
    public static boolean timeInRange(String beginTime, String endTime, Date accessDate) {
        if (StringUtils.isEmpty(beginTime) || StringUtils.isEmpty(endTime)) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(accessDate);
        String access = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
        // 00:00到00:00为无效时段
        if (beginTime.compareTo("00:00") == 0 && endTime.compareTo("00:00") == 0) {
            return false;
        }
        try {
            Date begin = parse_HH_mm(beginTime);
            Date end = parse_HH_mm(endTime);
            Date accessTime = parse_HH_mm(access);
            if (accessTime.getTime() >= begin.getTime() && accessTime.getTime() <= end.getTime()) {
                return true;
            }
        } catch (ParseException e) {
            return false;
        }
        return false;
    }


    /**
     * 获取日期 0点0分0秒的时间
     *
     * @param accessDate
     * @return
     * @throws ParseException
     */
    public static Date getTheDate(Date accessDate) throws ParseException {
        String dateStr = toStr_yyyy_MM_dd(accessDate);
        return parse_yyyy_MM_dd(dateStr);
    }

    /**
     * 返回明天
     *
     * @param today
     * @return
     */
    public static Date tomorrow(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        return calendar.getTime();
    }

    public static void main(String[] args) {
//        System.out.println(valiDateTimeWithNoTimeFormat("2012-10-32"));
//        System.out.println(intervalTime("2012-10-01 13:00:00", "2012-10-01 15:01:00") > 120 * 60 * 1000);
        Date date = CalendarUtil.getNextDaysOfDate(new Date(), 5);
        System.out.println(getWeekOfDate(date));
    }
}