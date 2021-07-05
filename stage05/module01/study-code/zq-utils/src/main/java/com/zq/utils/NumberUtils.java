package com.zq.utils;

/**
 * @ClassName: NumberUtils
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/6/2 19:54
 * @Version: V1.0
 */
public class NumberUtils {
    public static final char[] CN_ARR = new char [] {'零','一','二','三','四','五','六','七','八','九'};
    public static final char[] CN_UPPER_ARR = new char [] {'零','壹','贰','叁','肆','伍','陆','柒','捌','玖'};
    public static final char[] UNIT_ARR = new char [] {'十','百','千','万','亿'};
    public static final char[] UNIT_UPPER_ARR = new char [] {'拾','佰','仟','万','亿'};
    public static final String ALL_CN_NUM = "零一二三四五六七八九十百千万亿";
    public static final String ALL_CN_UPPER_NUM = "零壹贰叁肆伍陆柒捌玖拾佰仟万亿";

    /**
     * 将0-9的阿拉伯数字转换为汉字数字
     * @param i
     * @return
     */
    public static String intToCn(int i){
        return  String.valueOf(CN_ARR[i]);
    }
}