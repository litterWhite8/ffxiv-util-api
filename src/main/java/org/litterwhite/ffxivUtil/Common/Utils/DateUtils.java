package org.litterwhite.ffxivUtil.Common.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
 
    /** 系统默认 日期类型 yyyy-MM-dd */
    public static final String DATE_PATTERN_DEFAULT = "yyyy-MM-dd";
 
    /** 时间 日期类型 HH:mm:ss */
    public static final String DATE_PATTERN_TIME = "HH:mm:ss";
 
    /** 日期时间 日期类型 yyyy-MM-dd HH:mm:ss */
    public static final String DATE_PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
 
    /** 时期格式 yyyy-MM-dd */
    public static DateFormat dateformater;
 
    /** 时间格式 HH:mm:ss */
    public static DateFormat timeformater;
 
    /** 日期时间格式 yyyy-MM-dd HH:mm */
    public static DateFormat dateTimeformater;
 
    static {
        if (DateUtils.dateformater == null) {
            DateUtils.dateformater = new SimpleDateFormat(DateUtils.DATE_PATTERN_DEFAULT);
        }
        if (DateUtils.timeformater == null) {
            DateUtils.timeformater = new SimpleDateFormat(DateUtils.DATE_PATTERN_TIME);
        }
 
        if (DateUtils.dateTimeformater == null) {
            DateUtils.dateTimeformater = new SimpleDateFormat(DateUtils.DATE_PATTERN_DATETIME);
        }
    }
 
    /** 一天毫秒数 */
    public static final long DAY_IN_MILLISECOND = 1000 * 3600 * 24;
 
    /** 一小时毫秒数 */
    public static final long HOUR_IN_MILLISECOND = 1000 * 3600;
 
    /** 构造方法私有化 */
    private DateUtils() {
    }
 
    /**
     * 得到现在时间
     *
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringToday() {
        final Date currentTime = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        final String dateString = formatter.format(currentTime);
        return dateString;
    }
 
    /**
     *
     * 根据 yyyy-MM-dd 字符串解析成相应的日期
     *
     * @param strDate
     *            yyyy-MM-dd 格式的日期
     *
     * @return 转换后的日期
     *
     */
    public static Date parseDate(String strDate) {
        Date date = null;
        if (strDate != null && !strDate.isEmpty()) {
            try {
                date = DateUtils.dateformater.parse(strDate);
            }
            catch (final Exception e) {
                e.printStackTrace();
                return date;
            }
        }
        return date;
    }

 
    /**
     *
     * 根据 HH:mm:ss 字符串解析成相应的时间格式
     *
     * @param strTime
     *            HH:mm:ss 格式的时间格式
     *
     * @return 转换后的时间
     *
     */
    public static Date parseTime(String strTime) {
        Date date = null;
        try {
            date = DateUtils.timeformater.parse(strTime);
        }
        catch (final Exception e) {
            e.printStackTrace();
            return date;
        }
        return date;
    }
 
    /**
     *
     * 根据yyyy-MM-dd HH:mm字符串解析成相应的日期时间
     * @param strDateTime 以"yyyy-MM-dd HH:mm:ss"为格式的时间字符串
     * @return 转换后的日期
     *
     */
    public static Date parseDateTime(String strDateTime) {
        Date date = new Date();
        try {
            date = DateUtils.dateTimeformater.parse(strDateTime);
        }
        catch (final Exception e) {
            e.printStackTrace();
            return date;
        }
        return date;
    }
 

    /**
     *
     * 转换日期为 yyyy-MM-dd 格式的字符串
     *
     * @param date
     *            需要转换的日期
     *
     * @return 转换后的日期字符串
     *
     */
    public static String formatDate(Date date) {
        String str = "";
        if (date != null) {
            str = DateUtils.dateformater.format(date);
        }
        return str;
 
    }
 
    /**
     *
     * 转换指定日期成时间格式 HH:mm:ss 格式的字符串
     *
     * @param date
     *            指定的时间
     *
     * @return 转换后的字符串
     *
     */
    public static String formatTime(Date date) {
        return DateUtils.timeformater.format(date);
    }
 
    /**
     *
     * 转换指定日期成 yyyy-MM-dd HH:mm:ss 格式的字符串
     *
     * @param date
     *            需要转换的日期
     *
     * @return 转换后的字符串
     *
     */
    public static String formatDateTime(Date date) {
        return DateUtils.dateTimeformater.format(date);
    }


 
    /**
     *
     * 在指定的日期基础上，增加或是减少天数
     *
     * @param date
     *            指定的日期
     *
     * @param days
     *            需要增加或是减少的天数，正数为增加，负数为减少
     *
     * @return 增加或是减少后的日期
     *
     */
    public static Date dateDayAdd(Date date, int days) {
        final long now = date.getTime() + (days * DateUtils.DAY_IN_MILLISECOND);
        return new Date(now);
    }

 
    /**
     *
     * 根据 yyyyMMdd HH:mm 日期格式，转换成数据库使用的时间戳格式
     *
     * @param strTimestamp
     *            以 yyyyMMdd HH:mm 格式的时间字符串
     *
     * @return 转换后的时间戳
     *
     */
    public static java.sql.Timestamp parseTimestamp(String strTimestamp) {
        return new java.sql.Timestamp(DateUtils.parseDateTime(strTimestamp).getTime());
    }

}