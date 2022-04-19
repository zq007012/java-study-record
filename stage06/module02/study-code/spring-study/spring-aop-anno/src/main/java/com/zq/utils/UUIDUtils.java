package com.zq.utils;

import java.util.UUID;

/**
 * UUID生成器工具集
 * @author      zq007
 * @date        2021/3/13 21:46
 * @version     V1.0
 */
public class UUIDUtils {
    /**
     * 生成一个长度为32的不会重复的uuid字符串
     * @return  返回一个长度为32的不会重复的uuid字符串
     */
    public static String generateUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
