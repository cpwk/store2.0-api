package com.maidao.edu.store.common.util;

import com.sunnysuperman.commons.util.FormatUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    public static String getToday() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String getFormatedDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
    }

    public static String getDate_hours_string(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hours);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(cal.getTime());
    }

    public static boolean isToday(Date date) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH) + 1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH) + 1;
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        return year1 == year2 && month1 == month2 && day1 == day2;
    }

    public static boolean isYestoday(Date date) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH) + 1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH) + 1;
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        return year1 == year2 && month1 == month2 && day1 == day2 - 1;
    }

    public static int getDistMins(Date stime, Date etime) {
        try {
            int mins = 0;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(stime);
            long timestart = calendar.getTimeInMillis();
            calendar.setTime(etime);
            long timeend = calendar.getTimeInMillis();
            mins = (int) (Math.abs((timeend - timestart)) / (1000 * 60));
            return mins;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getDistSeconds(Date stime, Date etime) {
        try {
            int mins = 0;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(stime);
            long timestart = calendar.getTimeInMillis();
            calendar.setTime(etime);
            long timeend = calendar.getTimeInMillis();
            mins = (int) (Math.abs((timeend - timestart)) / 1000);
            return mins;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Date getDate_days_night(Date date, int days) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        cal.set(Calendar.HOUR_OF_DAY, 3);
        return cal.getTime();
    }

    public static Date getDate_mins(Date date, int mins) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, mins);
        return cal.getTime();
    }

    public static Date getDate_days(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static String getHistoryTimeStr(Date stime) {

        try {
            long day = 0;
            long hour = 0;
            long min = 0;
            long diff = System.currentTimeMillis() - stime.getTime();
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);

            if (day > 0) {
                if (day == 1) {
                    return "昨天";
                } else {
                    return day + "天前";
                }
            } else if (hour > 0)
                return hour + " 小时前";
            else
                return min + " 分钟前";
        } catch (Exception e) {
            return "时间获取失败";
        }

    }

    public static int date2day(Calendar cal) {
        return cal.get(Calendar.YEAR) * 10000 + (cal.get(Calendar.MONTH) + 1) * 100 + cal.get(Calendar.DAY_OF_MONTH);
    }

    public static Date parseDate(String s) {
        if (s.charAt(s.length() - 1) == 'Z') {
            // parse iso8601
            return FormatUtil.parseISO8601Date(s);
        }
        // yyyy(-|/)MM(-|/)dd HH(:mm)(:ss)
        s = s.trim();
        String dateString = s;
        String timeString = null;
        int timeIndex = s.indexOf(' ');
        if (timeIndex > 0) {
            dateString = s.substring(0, timeIndex);
            timeString = s.substring(timeIndex + 1);
        }
        Calendar cal = Calendar.getInstance();
        cal.clear();
        {
            List<String> tokens = StringUtils.split(dateString, dateString.indexOf('/') > 0 ? "/" : "-");
            if (tokens.size() != 3) {
                return null;
            }
            int year = Integer.parseInt(tokens.get(0).trim());
            int month = Integer.parseInt(tokens.get(1).trim());
            int day = Integer.parseInt(tokens.get(2).trim());
            if (year < 2000) {
                return null;
            }
            int theDay = year * 10000 + month * 100 + day;
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.DAY_OF_MONTH, day);
            if (date2day(cal) != theDay) {
                return null;
            }
        }
        if (timeString != null) {
            List<String> tokens = StringUtils.split(timeString, ":");
            int hour = Integer.parseInt(tokens.get(0).trim());
            if (hour < 0 || hour >= 24) {
                return null;
            }
            int minute = tokens.size() > 1 ? Integer.parseInt(tokens.get(1).trim()) : 0;
            if (minute < 0 || minute >= 60) {
                return null;
            }
            int second = tokens.size() > 2 ? Integer.parseInt(tokens.get(2).trim()) : 0;
            if (second < 0 || second >= 60) {
                return null;
            }
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.SECOND, second);
        }
        return cal.getTime();
    }

    public static Long addDays(Long time, int days) {
        if (time == null) {
            return 0L;
        }
        return time + days * 24 * 60 * 60 * 1000;
    }

}
