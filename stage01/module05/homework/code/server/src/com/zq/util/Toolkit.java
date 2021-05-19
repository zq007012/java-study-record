package com.zq.util;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/**
 * @ClassName: Toolkit
 * @Description: 这是一个自定义的工具包
 * @Author: zq007
 * @Date: 2021/1/18 20:08
 * @Version: V1.0
 */
public class Toolkit {
    /**
     * 用来批量关闭可关闭的资源, 当刷新或关闭资源失败时, 可以提醒是第几个资源刷新或关闭失败了
     * @param resources
     */
    public static void closeResources(Closeable... resources){
        int length = resources.length;
        for (int i = 0 ; i < length ; i++){
            //先刷新可刷新资源, 然后再关闭这个资源
            Closeable resource = resources[i];
            if (null != resource) {
                if (resource instanceof Flushable) {
                    try {
                        ((Flushable) resource).flush();
                    } catch (IOException e) {
                        System.out.println("第" + (i + 1) + "个资源刷新时失败");
                        e.printStackTrace();
                    }
                }

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
