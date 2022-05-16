package com.zq.converter;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        //将. / \ 替换成 -
        s=s.replaceAll("[./\\\\]","-");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
