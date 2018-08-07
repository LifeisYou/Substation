package com.xczn.substation.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zhangxiao
 * Date on 2018/5/16.
 */
public class TimeUtils {

    private static int[] _afxMonthDays = new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    private static final SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
    private static final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.CHINA);
    private static String dayNames[] = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    //获得当前时间
    public static String getCurrentTime(){
        Date date= new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    //获得当前日期，天
    public static String getCurrentDay(){
        Date date= new Date(System.currentTimeMillis());
        return dayFormat.format(date);
    }

    //获得当前日期，天
    public static String getCurrentTime(String currentTimeMillis){
        Date date= new Date(Long.valueOf(currentTimeMillis));
        return dayFormat.format(date);
    }

    //获得当前日期，天
    public static String getCurrentTime(long currentTimeMillis){
        Date date= new Date(currentTimeMillis);
        return dayFormat.format(date);
    }

    //获得当前日期，月
    public static String getCurrentMonth(){
        Date date= new Date(System.currentTimeMillis());
        return monthFormat.format(date);
    }

    //获得当前日期，年
    public static String getCurrentYear(){
        Date date= new Date(System.currentTimeMillis());
        return yearFormat.format(date);
    }

    private static Date double2Date(Double d)
    {
        Date t = null;
        try{
            Calendar base = Calendar.getInstance();
            //Delphi的日期类型从1899-12-30开始
            base.set(1899, 11, 30, 0, 0, 0);
            base.add(Calendar.DATE, d.intValue());
            base.add(Calendar.MILLISECOND, (int)((d % 1) * 24 * 60 * 60 * 1000));
            t = base.getTime();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return t;
    }

    public static double change2Double(String str) throws java.text.ParseException{

        Calendar curCalendar = Calendar.getInstance();
        curCalendar.setTime(simpleDateFormat.parse(str));

        return _DateFromTm(curCalendar.get(Calendar.YEAR), curCalendar.get(Calendar.MONTH)+1, curCalendar.get(Calendar.DAY_OF_MONTH),
                curCalendar.get(Calendar.HOUR_OF_DAY), curCalendar.get(Calendar.MINUTE), curCalendar.get(Calendar.SECOND), 0);
    }

    private static double _DateFromTm(int nYear, int nMonth, int nDay,
                                     int nHour, int nMinute, int nSecond, int nMillis)
    {
        // Validate year and month (ignore day of week and milliseconds)
        if (nYear > 9999 || nMonth < 1 || nMonth > 12)
            return 0;

        //  Check for leap year and set the number of days in the month
        Boolean bLeapYear = ((nYear & 3) == 0) && ((nYear % 100) != 0 || (nYear % 400) == 0);

        int nDaysInMonth =
                _afxMonthDays[nMonth] - _afxMonthDays[nMonth-1] +
                        ((bLeapYear && nDay == 29 && nMonth == 2) ? 1 : 0);

        // Finish validating the date
        if (nDay < 1 || nDay > nDaysInMonth ||
                nHour > 23 || nMinute > 59 ||
                nSecond > 59) {
            return 0;
        }

        // Cache the date in days and time in fractional days
        int nDate;
        double dblTime;

        //It is a valid date; make Jan 1, 1AD be 1
        nDate = nYear*365 + nYear/4 - nYear/100 + nYear/400 +
                _afxMonthDays[nMonth-1] + nDay;

        //  If leap year and it's before March, subtract 1:
        if (nMonth <= 2 && bLeapYear)
            --nDate;

        //  Offset so that 12/30/1899 is 0
        nDate -= 693959L;

        dblTime = ((nHour * 3600L) +  // hrs in seconds
                (nMinute * 60L) +  // mins in seconds
                (nSecond)) / 86400. + (nMillis) / 86400000.0;

        return (double) nDate + ((nDate >= 0) ? dblTime : -dblTime);
    }
    
    public static String date2String(Double d){
        return simpleDateFormat.format(double2Date(d));
    }

    /**
     * 微信时间戳转换
     * @param currentTimeMillis 时间戳
     * @return 格式化时间
     */
    public static String getNewChatTime(String currentTimeMillis) {
        long timesamp = Long.valueOf(currentTimeMillis);
        String result;

        Calendar todayCalendar = Calendar.getInstance();
        Calendar otherCalendar = Calendar.getInstance();
        otherCalendar.setTimeInMillis(timesamp);
        String timeFormat;
        String yearTimeFormat;
        String am_pm = "";

        //格式化天
        int hour = otherCalendar.get(Calendar.HOUR_OF_DAY);
        if(hour >= 0 && hour < 6){
            am_pm = "凌晨";
        }else if(hour >= 6 && hour < 12){
            am_pm = "早上";
        }else if(hour == 12){
            am_pm = "中午";
        }else if(hour > 12 && hour < 18){
            am_pm = "下午";
        }else if(hour >= 18){
            am_pm = "晚上";
        }

        timeFormat = "M月d日 " + am_pm + "HH:mm";
        yearTimeFormat = "yyyy年M月d日 " + am_pm + "HH:mm";
        boolean yearTemp = todayCalendar.get(Calendar.YEAR) == otherCalendar.get(Calendar.YEAR);
        if(yearTemp){
            int todayMonth = todayCalendar.get(Calendar.MONTH);
            int otherMonth = otherCalendar.get(Calendar.MONTH);
            if(todayMonth == otherMonth){//表示是同一个月
                int temp = todayCalendar.get(Calendar.DATE)-otherCalendar.get(Calendar.DATE);
                switch (temp) {
                    case 0:
                        result = getHourAndMin(timesamp);
                        break;
                    case 1:
                        result = "昨天 " + getHourAndMin(timesamp);
                        break;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        int dayOfMonth = otherCalendar.get(Calendar.WEEK_OF_MONTH);
                        int todayOfMonth = todayCalendar.get(Calendar.WEEK_OF_MONTH);
                        if(dayOfMonth == todayOfMonth){//表示是同一周
                            int dayOfWeek = otherCalendar.get(Calendar.DAY_OF_WEEK);
                            if(dayOfWeek != 1){//判断当前是不是星期日 如想显示为:周日 12:09 可去掉此判断
                                result = dayNames[otherCalendar.get(Calendar.DAY_OF_WEEK)-1] + getHourAndMin(timesamp);
                            }else{
                                result = getTime(timesamp,timeFormat);
                            }
                        }else{
                            result = getTime(timesamp,timeFormat);
                        }
                        break;
                    default:
                        result = getTime(timesamp,timeFormat);
                        break;
                }
            }else{
                result = getTime(timesamp,timeFormat);
            }
        }else{
            result=getYearTime(timesamp,yearTimeFormat);
        }
        return result;
    }
    /**
     * 当天的显示时间格式
     * @param time 时间戳
     * @return 时间
     */
    private static String getHourAndMin(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.CHINA);
        return format.format(new Date(time));
    }
    /**
     * 不同一周的显示时间格式
     * @param time 时间戳
     * @param timeFormat 时间格式
     * @return 返回周时间
     */
    private static String getTime(long time,String timeFormat) {
        SimpleDateFormat format = new SimpleDateFormat(timeFormat, Locale.CHINA);
        return format.format(new Date(time));
    }
    /**
     * 不同年的显示时间格式
     * @param time 时间戳
     * @param yearTimeFormat 时间格式
     * @return 年
     */
    private static String getYearTime(long time,String yearTimeFormat) {
        SimpleDateFormat format = new SimpleDateFormat(yearTimeFormat, Locale.CHINA);
        return format.format(new Date(time));
    }
}
