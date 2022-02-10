package com.zq.utils;


import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * 这是一个日期和时间相关的工具集, 可以以固定的格式返回当前的日期、时间和日期时间
 * @author      zq007
 * @date        2021/3/13 21:48
 * @version     V1.0
 */
public class DateTimeUtils {
    /**
     * 以"yyyy-MM-dd"的格式返回当前的日期
     * @return 以"yyyy-MM-dd"的格式返回当前的日期
     */
    public static String getDate(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * 以"HH:mm:ss"的格式返回当前的时间
     * @return 以"HH:mm:ss"的格式返回当前的时间
     */
    public static String getTime(){
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    /**
     * 以"yyyy-MM-dd HH:mm:ss"的格式返回当前的日期和时间
     * @return  以"yyyy-MM-dd HH:mm:ss"的格式返回当前的日期和时间
     */
    public static String getDateTime(){
        return LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    }


}
