package com.lagou.zq.homework.code.util;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public class Toolkit {
    /**
     * 这是一个可以批量关闭可关闭资源的方法,如果这个资源是可以刷新的,那么就会先刷新这个资源后再
     * 将其关闭
     * @param resources
     */
    public static void closeResources(Closeable... resources){
        int length = resources.length;
        //1. 循环获取所有的资源
        for (int i = 0 ; i < length ; i++){
            Closeable resource = resources[i];
            if (null != resource) {
                //2. 判断这个资源是否可以刷新, 如果可以,就先调用这个资源的flush方法
                if (resource instanceof Flushable) {
                    try {
                        ((Flushable) resource).flush();
                    } catch (IOException e) {
                        System.out.println("第" + (i + 1) + "个资源刷新时失败");
                        e.printStackTrace();
                    }
                }

                //3. 关闭这个资源
                try {
                    resource.close();
                } catch (IOException e) {
                    System.out.println("第" + (i + 1) + "个资源关闭时失败");
                    e.printStackTrace();
                }
            }

        }
    }
}
