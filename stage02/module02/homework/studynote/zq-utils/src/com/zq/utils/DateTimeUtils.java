package com.zq.utils;

import org.junit.Test;

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

    public static java.sql.Date getSqlDate(String date){
        date = date.trim().replaceAll(" {2,}"," ").
                replaceAll("[ \\./\\\\年月日]", "-");
        return java.sql.Date.valueOf(date);
    }

    public static java.sql.Time getSqlTime(String time){
        time = time.trim().replaceAll(" {2,}"," ").
                replaceAll("[ \\./\\\\时分秒]", ":");
        return java.sql.Time.valueOf(time);
    }
}
