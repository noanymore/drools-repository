package me.sdevil507.drools.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Slf4j
public class DateUtils {

  public static final String YY_M_D = "yyyy/MM/dd";
  public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
  public static final String yyyyMMdd = "yyyy-MM-dd";
  public static final String yyyyMMdd_SLASH = YY_M_D;
  public static final String yyyy = "yyyy";
  public static final String yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
  public static final String yyMd = "yyyyMMdd";
  public static final String DD = "dd";
  public static final String MM_DD = "MM/dd";
  public static final String M_D = "M/d";
  public static final String MD_DD_ONE = "M月d日";
  public static final String MM_DD_HH_MM_SS = "MM/dd HH:mm";
  public static final String MM_DD_TWO = "MM月dd日";
  public static final String YYYY_MM_DD = "yyyy年MM月dd日";
  public static final String YYYYMMDD = yyMd;
  public static final String YYMMDD = "yyMMdd";
  public static final String HH_MM = "HH:mm";
  public static final String MM_DD_HH_MM = "MM月dd日 HH:mm";
  public static final String YYYY_MM_DD_HH_MM = "yyyy年MM月dd日 HH:mm";
  public static final String YY_M_D_H_M = "yyyy/MM/dd HH:mm";
  public static final String YY_M_D_H_M_S = "yyyy/MM/dd HH:mm:ss";
  public static final String YMDHMS = "yyyyMMddHHmmss";
  public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
  public static final String HHmmss = "HH:mm:ss";
  public static final String yyyymmddhhmm = "yyyyMMddHHmm";
  public static final String yyyymmdd = "yyyy.MM.dd";
  public static final String MMddHHmm = "MM-dd HH:mm";
  public static final int SECONDS_IN_DAY = 60 * 60 * 24;
  public static final String yyyyMMddRegex =
      "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|"
          +
          "((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|"
          +
          "((0[48]|[2468][048]|[3579][26])00))0229)$";

  public static String format(Date d, String format) {
    if (Objects.isNull(d)) {
      return "";
    }
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(d);
  }


  /**
   * 根据生日获取周岁
   *
   * @param birthday
   * @return
   */
  public static int getCurrentAge(Date birthday) {
    if (birthday == null) {
      return -1;
    }
    // 当前时间
    Calendar curr = Calendar.getInstance();
    // 生日
    Calendar born = Calendar.getInstance();
    born.setTime(birthday);
    // 年龄 = 当前年 - 出生年
    int age = curr.get(Calendar.YEAR) - born.get(Calendar.YEAR);
    if (age <= 0) {
      return 0;
    }
    // 如果当前月份小于出生月份: age-1
    // 如果当前月份等于出生月份, 且当前日小于出生日: age-1
    int currMonth = curr.get(Calendar.MONTH);
    int currDay = curr.get(Calendar.DAY_OF_MONTH);
    int bornMonth = born.get(Calendar.MONTH);
    int bornDay = born.get(Calendar.DAY_OF_MONTH);
    if ((currMonth < bornMonth) || (currMonth == bornMonth && currDay <= bornDay)) {
      age--;
    }
    return age < 0 ? 0 : age;
  }

  /**
   * 根据出生日期计算年龄
   */
  public static Integer getAgeByBirthday(String birthday) {
    if (Objects.isNull(birthday)) {
      return null;
    }
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
      Date birthDay = sdf.parse(birthday);
      Calendar cal = Calendar.getInstance();

      if (cal.before(birthDay)) {
        throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
      }
      int yearNow = cal.get(Calendar.YEAR);
      int monthNow = cal.get(Calendar.MONTH);
      int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
      cal.setTime(birthDay);

      int yearBirth = cal.get(Calendar.YEAR);
      int monthBirth = cal.get(Calendar.MONTH);
      int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
      int age = yearNow - yearBirth;
      if (monthNow <= monthBirth) {
        if (monthNow == monthBirth) {
          if (dayOfMonthNow < dayOfMonthBirth) {
            age--;
          }
        } else {
          age--;
        }
      }
      return age;
    } catch (Exception e) {
      return null;
    }
  }

  public static String getDateFormat(Date date, String formatStr) {
    if (date == null || formatStr == null) {
      return "";
    }
    SimpleDateFormat ymdFormat = new SimpleDateFormat(formatStr);
    return ymdFormat.format(date);
  }

  /**
   * 字符传化成时间类型
   */
  public static Date strintToDatetime(String ds, String DateFormat) {
    if (StringUtils.isEmpty(ds)) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);
    try {
      return sdf.parse(ds);
    } catch (ParseException e) {
      return null;
    }
  }

  /**
   * 根据毫秒返回 yyyy-MM-dd HH:mm:ss 格式日期
   */
  public static String getNormalTime(long value) {
    SimpleDateFormat format = new SimpleDateFormat(yyyyMMddHHmmss);
    return format.format(new Date(value));
  }

  /**
   * 根据毫秒返回 yyyy-MM-dd 格式日期
   */
  public static String getNormalDayTime(long value) {
    SimpleDateFormat format = new SimpleDateFormat(yyyyMMdd);
    return format.format(new Date(value));
  }

  /**
   * 根据毫秒返回 yyyy 格式日期
   */
  public static String getNormalYearTime(long value) {
    SimpleDateFormat format = new SimpleDateFormat(yyyy);
    return format.format(new Date(value));
  }

  /**
   * 获取该日期当月第一天
   */
  public static Date getMonthStart(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int index = calendar.get(Calendar.DAY_OF_MONTH);
    calendar.add(Calendar.DATE, (1 - index));
    return calendar.getTime();
  }

  /**
   * 获取该日期当月最后一天
   */
  public static Date getMonthEnd(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.MONTH, 1);
    int index = calendar.get(Calendar.DAY_OF_MONTH);
    calendar.add(Calendar.DATE, (-index));
    return calendar.getTime();
  }

  /**
   * 获取传入日期的下一天
   */
  public static Date getNext(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DATE, 1);
    return calendar.getTime();
  }

  /**
   * 获取传入日期的前一天
   */
  public static Date getBefore(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DATE, -1);
    return calendar.getTime();
  }

  /**
   * 比较两个日期的前后
   */
  public static boolean isDateBefore(Date date1, Date date2) {
    return date1 != null && date2 != null && date1.before(date2);
  }


  /**
   * 比较两个日期的前后
   */
  public static boolean isDateBefore(String date1, String date2, String format) {
    Date d1 = strintToDatetime(date1, format);
    Date d2 = strintToDatetime(date2, format);
    return isDateBefore(d1, d2);
  }

  /**
   * 某日期前后n天的零点是否大于当前时间的毫秒数
   */
  public static boolean getDateStartBeforeNow(Date date, int days) {
    //传入日期的日期n天前后计算
    if (Objects.isNull(date)) {
      return false;
    }
    Date newDate = addToDay(date, days);
    long t1 = getDateStartMillisecond(newDate);
    return t1 - System.currentTimeMillis() > 0;
  }

  /**
   * 该日期开始时候的毫秒数
   */
  public static long getDateStartMillisecond(Date date) {
    String zeroDate = format(date, yyyyMMdd + " 00:00:00");
    Date strintToDatetime = strintToDatetime(zeroDate, yyyyMMddHHmmss);
    return Objects.isNull(strintToDatetime) ? null : strintToDatetime.getTime();
  }

  /**
   * 该日期结束时候的毫秒数
   */
  public static long getDateEndMillisecond(Date date) {
    String zeroDate = format(date, yyyyMMdd + " 23:59:59");
    Date datetime = strintToDatetime(zeroDate, yyyyMMddHHmmss);
    return Objects.isNull(datetime) ? null : datetime.getTime();
  }

  /**
   * 当前日期N天后的0点时间距离
   */
  public static Duration getAfterDateDuration(int day) {
    LocalDateTime after = LocalDateTime
        .of(java.time.LocalDate.now().plusDays(day), LocalTime.MIN);
    return Duration.between(LocalDateTime.now(), after);
  }

  /**
   * 该日期0点开始的时间
   */
  public static Date getDateStartDate(Date date) {
    return strintToDatetime(format(date, yyyyMMdd + " 00:00:00"), yyyyMMddHHmmss);
  }

  /**
   * 该日期24点结束的时间
   */
  public static Date getDateEndDate(Date date) {
    return strintToDatetime(format(date, yyyyMMdd + " 23:59:59"), yyyyMMddHHmmss);
  }

  /**
   * 获取两个日期之间的日期
   *
   * @param start 开始日期
   * @param end   结束日期
   * @return 日期集合
   */
  public static List<Date> getBetweenDates(Date start, Date end) {
    List<Date> result = new ArrayList<>();
    Calendar tempStart = Calendar.getInstance();
    tempStart.setTime(start);
    Calendar tempEnd = Calendar.getInstance();
    tempEnd.setTime(end);
    if (getDateFormat(tempStart.getTime(), yyyyMMdd)
        .equals(getDateFormat(tempEnd.getTime(), yyyyMMdd))) {
      result.add(end);
      return result;
    }
    if (!isDateBefore(start, end)) {
      return result;
    }
    while (isDateBefore(tempStart.getTime(), tempEnd.getTime())) {
      result.add(tempStart.getTime());
      tempStart.add(Calendar.DAY_OF_YEAR, 1);
    }
    return result;
  }

  /**
   * 获取日期为一个礼拜中的第几天
   */
  public static int getDayForWeek(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    //按中国礼拜一作为第一天所以这里减1,注：java中日期计算按照礼拜天为1，礼拜一为2
    int weekday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
    // 如果是礼拜天 返回中国习惯的第七天
    return weekday == 0 ? 7 : weekday;
  }

  /**
   * 获得当前日期 + N个天 之后的日期
   */
  public static Date addToDay(Date date, int days) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DAY_OF_YEAR, days);
    return calendar.getTime();
  }

  /**
   * 获得当前日期 + N个天 之后的日期
   */
  public static Date addToYear(Date date, int year) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.YEAR, year);
    return calendar.getTime();
  }

  /**
   * 获得当前月份 + N个月 之后的日期
   */
  public static Date addToMonth(Date date, int month) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.MONTH, month);
    return calendar.getTime();
  }
  /**
   * 是否是同一年
   */
  public static boolean isSameYear(Date date1, Date date2) {
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(date1);

    Calendar cal2 = Calendar.getInstance();
    cal2.setTime(date2);

    return cal1.get(Calendar.YEAR) == cal2
        .get(Calendar.YEAR);
  }

  public static int betweenDays(Date d1, Date d2) {
    double t1 = d1.getTime();
    double t2 = d2.getTime();
    double v = (t1 - t2) / (1000 * 3600 * 24);
    return (int) Math.ceil(Math.abs(v));
  }


  public static int betweenMins(Date d1, Date d2) {
    double t1 = d1.getTime();
    double t2 = d2.getTime();
    double v = (t1 - t2) / (1000 * 60);
    return (int) Math.ceil(v);
  }

  // 计算两个日期天数
  public static long getDaySub(Date d1, Date d2) {
    double t1 = d1.getTime();
    double t2 = d2.getTime();
    double v = (t1 - t2) / (1000 * 3600 * 24);
    long day = (d1.getTime() - d2.getTime()) / (SECONDS_IN_DAY * 1000);
    return day;
  }

  public static Long getSecondBetweenDays(Date d1, Date d2) {
    Long t1 = d1.getTime();
    Long t2 = d2.getTime();
    return (t1 - t2) / 1000;
  }

  public static Long getMinutesBetweenDays(Date d1, Date d2) {
    Long t1 = d1.getTime();
    Long t2 = d2.getTime();
    return (t1 - t2) / (60 * 1000);
  }

  public static Date parseDate(String expireStr, String format) {
    if (StringUtils.isEmpty(expireStr)) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    try {
      return sdf.parse(expireStr);
    } catch (ParseException e) {
      return null;
    }
  }

  /**
   * 在当前时间后的多少时间
   */
  public static Date addMins(int addMins) {
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(System.currentTimeMillis());
    cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + addMins);
    Date dateinvalid = cal.getTime();
    return dateinvalid;
  }

  /**
   * 在当前时间后的多少秒
   */
  public static Date addSecs(int addSecs) {
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(System.currentTimeMillis());
    cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + addSecs);
    Date dateinvalid = cal.getTime();
    return dateinvalid;
  }

  /**
   * 在当前时间后的多少时间
   */
  public static Date addMins(Date date, int addMins) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + addMins);
    Date dateinvalid = cal.getTime();
    return dateinvalid;
  }

  /**
   * @param datetime 日期 例:2017-10-17
   * @return String 例:星期二
   * @doc 日期转换星期几
   * @history 2017年10月17日 上午9:55:30 Create by 【hsh】
   */
  public static String dateToWeek(String datetime) {
    if (datetime == null || "".equals(datetime)) {
      return "";
    }
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
    String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    Calendar cal = Calendar.getInstance(); // 获得一个日历
    Date datet = null;
    try {
      datet = f.parse(datetime);
      cal.setTime(datet);
    } catch (ParseException e) {
      log.warn("exception:", e);
    }
    int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
    if (w < 0) {
      w = 0;
    }
    return weekDays[w];
  }

  public static String dateToWeekV2(Date date) {
    if (date == null) {
      return "";
    }
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
    String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    Calendar cal = Calendar.getInstance(); // 获得一个日历
    cal.setTime(date);
    int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
    if (w < 0) {
      w = 0;
    }
    return weekDays[w];
  }

  /**
   * reserveDay: 2019-08-15 reserveForbiddenTime:  09:00:00 比较机构的最晚预约时间是否可约
   */
  public static boolean isNotReserveForbidden(String reserveForbiddenTime) {
    if (reserveForbiddenTime == null) {
      return true;
    }
    String dateString =
        DateUtils.format(new Date(), DateUtils.yyyyMMdd) + " " + reserveForbiddenTime;
    Date date = strintToDatetime(dateString, yyyyMMddHHmmss);
    if (isDateBefore(new Date(), date)) {
      return true;
    }
    return false;
  }

  /**
   * 获取日期中的月份
   *
   * @return
   */
  public static Integer getDateMonth(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int i = cal.get(Calendar.MONTH);
    return i + 1;
  }

  /**
   * 获取日期中的天
   */
  public static Integer getDateDay(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int i = cal.get(Calendar.DAY_OF_MONTH);
    return i;
  }


  /**
   * 时间后推
   *
   * @param days
   * @return
   */
  public static Date getDateBefor(Date time, int days) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(time);
    calendar.add(Calendar.DAY_OF_MONTH, days);
    return calendar.getTime();
  }


  public static boolean isToday(Date dateTime) {
    if (Objects.isNull(dateTime)) {
      return false;
    }
    return Objects.equals(format(dateTime, yyyyMMdd), format(new Date(), yyyyMMdd));
  }

  public static boolean isToday(String dateStr) {
    if (Objects.isNull(dateStr)) {
      return false;
    }
    return Objects.equals(dateStr, format(new Date(), yyyyMMdd));
  }

  /**
   * 小时时间处理：08:30处理成 8:30
   */
  public static String getHourStr(String time) {
    if (isEmpty(time)) {
      return "";
    }
    if (time.startsWith("0") && time.length() == 5) {
      return time.substring(1, 5);
    }
    return time;
  }


  public static Long getFormatHourTime(Long reserveDate, String hourtime) {
    if (hourtime.length() == 4) {
      hourtime = "0" + hourtime;
    }
    String date = DateUtils.format(new Date(reserveDate), yyyyMMdd) + " " + hourtime;
    Date date1 = DateUtils.strintToDatetime(date, yyyyMMddHHmm);
    if (Objects.isNull(date1)) {
      return 0L;
    }
    return date1.getTime();
  }

  public static String stringToWeekV3(String date) {
    String weekDays = null;
    if (date == null) {
      return "";
    }
    switch (date) {
      case "1":
        weekDays = "星期一";
        break;
      case "2":
        weekDays = "星期二";
        break;
      case "3":
        weekDays = "星期三";
        break;
      case "4":
        weekDays = "星期四";
        break;
      case "5":
        weekDays = "星期五";
        break;
      case "6":
        weekDays = "星期六";
        break;
      case "7":
        weekDays = "星期日";
        break;
      default:
        break;
    }
    return weekDays;
  }


  public static boolean dateBeforeByHour(String hhmm) {
    if (Objects.isNull(hhmm)) {
      return false;
    }
    Date date = DateUtils.strintToDatetime(format(new Date(), yyyyMMdd) + " " + hhmm, yyyyMMddHHmm);
    return isDateBefore(new Date(), date);
  }


  public static int getDays(int year, int month) {
    int days = 0;
    boolean isLeapYear = false;
    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
      isLeapYear = true;
    } else {
      isLeapYear = false;
    }
    switch (month) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        days = 31;
        break;
      case 2:
        if (isLeapYear) {
          days = 29;
        } else {
          days = 28;
        }
        break;
      case 4:
      case 6:
      case 9:
      case 11:
        days = 30;
        break;
      default:
        break;
    }
    return days;
  }

  /**
   * 判断时间是否在时间段内
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

    if ((date.after(begin) && date.before(end)) || date.equals(begin) || date.equals(end)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 组装两日期相差时分秒
   *
   * @param begin
   * @return
   */
  public static String getWaitTime(Date begin, Date end) {
    // 获取相差秒数
    StringBuilder time = new StringBuilder();
    long second = DateUtils.getSecondBetweenDays(begin, end);

    String seconds = String.format("%02d", second % 60);
    String minute = String.format("%02d", second / 60 % 60);
    String hour = String.format("%02d", second / 3600);

    if (StringUtils.isNotBlank(hour)) {
      time.append(hour);
      time.append(":");
    }
    if (StringUtils.isNotBlank(minute)) {
      time.append(minute);
      time.append(":");
    }
    if (StringUtils.isNotBlank(seconds)) {
      time.append(seconds);
    }
    return time.toString();
  }

  public static String getDayStart(Date date, String format) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    Date zero = calendar.getTime();
    return format(zero, format);
  }

  //日时分字符串处理
  public static String getDayHourMinStr(int day, int hour, int min) {
    StringBuilder time = new StringBuilder();
    if (min == 0) {
      if (hour == 0) {
        time.append(day).append("天");
        return time.toString();
      } else {
        if (day == 0) {
          time.append(hour).append("小时");
          return time.toString();
        }
        time.append(day).append("天").append(hour).append("小时");
        return time.toString();
      }
    } else {
      if (day == 0) {
        if (hour == 0) {
          time.append(min).append("分钟");
          return time.toString();
        }
        time.append(hour).append("小时").append(min).append("分钟");
        return time.toString();
      }
      time.append(day).append("天").append(hour).append("小时").append(min).append("分钟");
      return time.toString();
    }
  }


  /**
   * 当前系统时间 是否在时间段内
   *
   * @return
   * @throws Exception
   */
  public static boolean isTimeRange(String sTime, String eTime) {
    SimpleDateFormat df = new SimpleDateFormat("HH:mm");
    Date now = null;
    Date begin = null;
    Date end = null;
    try {
      now = df.parse(df.format(new Date()));
      begin = df.parse(sTime);
      end = df.parse(eTime);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    Calendar nowTime = Calendar.getInstance();
    nowTime.setTime(now);
    Calendar beginTime = Calendar.getInstance();
    beginTime.setTime(begin);
    Calendar endTime = Calendar.getInstance();
    endTime.setTime(end);
    if (nowTime.before(endTime) && nowTime.after(beginTime)) {
      return true;
    } else {
      return false;
    }
  }

  public static Date getDayStart(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    Date zero = calendar.getTime();
    return zero;
  }

  /**
   * 判断时间time1是否小于time2
   *
   * @param time1 格式为 HH:mm
   * @param time2 格式为 HH:mm
   * @return time1 < time2 true time1 >= time2 false
   */
  public static boolean isTimeBefore(String time1, String time2) {
    Date date1 = DateUtils.parseDate(time1, HHmmss);
    Date date2 = DateUtils.parseDate(time2, HHmmss);
    return isDateBefore(date1, date2);
  }

  public static Date dateStrToDate(String dateStr, String format) {
    if (format == null) {
      format = yyMd;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    try {
      return sdf.parse(dateStr);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Long dateStrToStamp(String dateStr, String format) {
    if (format == null) {
      format = yyMd;
    }
    return dateStrToDate(dateStr, format).getTime() / 1000;
  }

  public static String dateToDateStr(Date date, String format) {
    if (format == null) {
      format = yyMd;
    }
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      String dateStr = sdf.format(date);
      return dateStr;
    } catch (Exception e) {
      return "";
    }

  }

  /**
   * 校验时间点是否在范围之内
   *
   * @param hourTime 09:00:00
   * @return
   */
  public static boolean checkHourBetweenMinute(Date hourTime, Integer minute) {
    if (Objects.isNull(hourTime) || Objects.isNull(minute)) {
      return false;
    }
    //现在大于配置时间，同时配置时间5分钟后大于现在
    String hourtimeStr = format(hourTime, HHmmss);
    Date t2 = addMins(hourTime, 5);
    return isTimeRange(hourtimeStr, format(t2, HHmmss));
  }

  /**
   * 2个时间段之间相当于几周
   *
   * @return
   */
  public static int betweenOfWeek(Date start, Date end) {
    if (start == null || end == null) {
      return 0;
    }
    Duration duration = Duration.between(start.toInstant(), end.toInstant());

    return (int) Math.ceil(duration.toDays() / 7.0);
  }


  /**
   * 判断是否是同一天
   */
  public static boolean isSameDate(Date date1, Date date2) {
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(date1);

    Calendar cal2 = Calendar.getInstance();
    cal2.setTime(date2);

    boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
        .get(Calendar.YEAR);
    boolean isSameMonth = isSameYear
        && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
    return isSameMonth
        && cal1.get(Calendar.DAY_OF_MONTH) == cal2
        .get(Calendar.DAY_OF_MONTH);
  }
}
