package www.lagou.utils;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName: DateUtils
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/12 13:40
 * @Version: V1.0
 */
public class DateUtils {
    public static String getDateTime(){
      return DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss").
              format(LocalDateTime.now());
    }

}
