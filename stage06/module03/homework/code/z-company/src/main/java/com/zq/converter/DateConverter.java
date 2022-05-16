package com.zq.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/12 10:24
 */
public class DateConverter implements Converter<String, LocalDate>{

    @Override
    public LocalDate convert(String source) {
        source = source.replaceAll("[/._]", "-");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(source, dateTimeFormatter);
    }
}
