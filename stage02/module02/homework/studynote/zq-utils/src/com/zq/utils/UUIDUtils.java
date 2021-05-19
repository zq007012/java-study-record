package com.zq.utils;

import java.util.UUID;

/**
 * @ClassName: UUIDUtils
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/13 21:46
 * @Version: V1.0
 */
public class UUIDUtils {
    /**
     * 生成一个长度为32的不会重复的uuid字符串
     * @return
     */
    public static String generateUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
