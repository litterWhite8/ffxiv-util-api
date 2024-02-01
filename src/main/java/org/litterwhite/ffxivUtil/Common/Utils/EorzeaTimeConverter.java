package org.litterwhite.ffxivUtil.Common.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EorzeaTimeConverter {

    private static final long YEAR = 33177600;
    private static final long MONTH = 2764800;
    private static final long DAY = 86400;
    private static final long HOUR = 3600;
    private static final long MINUTE = 60;
    private static final long SECOND = 1;

    private static final double EORZEA_TIME_CONSTANT = 3600.0 / 175.0;

    public static class EorzeaTime {
        public long YearVal;
        public long MonthVal;
        public long DayVal;
        public long HourVal;
        public long MinuteVal;
        public long SecondVal;
    }

    public static EorzeaTime convertToEorzeaTime(Date date) {
        long earthTime = date.getTime() / 1000;
        long eorzeaTime = (long) Math.floor(earthTime * EORZEA_TIME_CONSTANT);

        EorzeaTime ret = new EorzeaTime();
        ret.YearVal = (long) Math.floor((double) (eorzeaTime / YEAR)) + 1;
        ret.MonthVal = (long) Math.floor((double) (eorzeaTime / MONTH % 12)) + 1;
        ret.DayVal = (long) Math.floor((double) (eorzeaTime / DAY % 32)) + 1;
        ret.HourVal = (long) Math.floor((double) (eorzeaTime / HOUR % 24));
        ret.MinuteVal = (long) Math.floor((double) (eorzeaTime / MINUTE % 60));
        ret.SecondVal = (long) Math.floor((double) (eorzeaTime / SECOND % 60));

        return ret;
    }

    public static String convertToEorzeaTimeString(Date date, String format) {
        long earthTime = date.getTime() / 1000;
        long eorzeaTime = (long) Math.floor(earthTime * EORZEA_TIME_CONSTANT);

        String yearVal = String.valueOf((long) Math.floor((double) (eorzeaTime / YEAR)) + 1);
        String monthVal = formatZero(String.valueOf((long) Math.floor((double) (eorzeaTime / MONTH % 12)) + 1));
        String dayVal = formatZero(String.valueOf((long) Math.floor((double) (eorzeaTime / DAY % 32)) + 1));
        String hourVal = formatZero(String.valueOf((long) Math.floor((double) (eorzeaTime / HOUR % 24))));
        String minuteVal = formatZero(String.valueOf((long) Math.floor((double) (eorzeaTime / MINUTE % 60))));
        String secondVal = formatZero(String.valueOf((long) Math.floor((double) (eorzeaTime / SECOND % 60))));

        return String.format(format, yearVal, monthVal, dayVal, hourVal, minuteVal, secondVal);
    }

    public static Date parseEorzeaTimeString(String timeString, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(timeString);
    }

    public static Date convertToEarthTime(String timeString, String format) throws ParseException {
        Date date = parseEorzeaTimeString(timeString, format);
        long years = date.getYear() + 1900;
        long months = date.getMonth() + 1;
        long days = date.getDate();
        long hours = date.getHours();
        long minutes = date.getMinutes();
        long seconds = date.getSeconds();

        long utc = (long) ((double) ((years - 1) * YEAR + (months - 1) * MONTH + (days - 1) * DAY + hours * HOUR + minutes * MINUTE + seconds) / EORZEA_TIME_CONSTANT);
        return new Date(utc * 1000);
    }

    private static String formatZero(String str) {
        return str.length() == 1 ? "0" + str : str;
    }

    public static void main(String[] args) throws ParseException {
        // 当前时间
        Date now = new Date();

        // 将当前时间转换为 Eorzea 时间
        EorzeaTime eorzeaTime = convertToEorzeaTime(now);
        System.out.println("Eorzea Time: " + eorzeaTime.YearVal + "-" + eorzeaTime.MonthVal + "-" + eorzeaTime.DayVal + " " + eorzeaTime.HourVal + ":" + eorzeaTime.MinuteVal + ":" + eorzeaTime.SecondVal);

        // 将当前时间按指定格式转换为 Eorzea 时间字符串
        String eorzeaTimeString = convertToEorzeaTimeString(now, "%s-%s-%s %s:%s:%s");
        System.out.println("Eorzea Time String: " + eorzeaTimeString);

        // 将 Eorzea 时间字符串转换为地球时间
        String eorzeaTimeStringToConvert = "924-06-30 08:01:22";
        Date earthTime = convertToEarthTime(eorzeaTimeStringToConvert, "yyyy-MM-dd HH:mm:ss");
        System.out.println("Converted Earth Time: " + earthTime);
    }
}
