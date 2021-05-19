package com.zq.utils;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * @ClassName: DateTimeUtils
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/13 21:48
 * @Version: V1.0
 */
public class DateTimeUtils {
    public static String getDate(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }

    public static String getTime(){
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public static String getDateTime(){
        return LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyy.MM.dd HH:mm:ss"));

    }


}
