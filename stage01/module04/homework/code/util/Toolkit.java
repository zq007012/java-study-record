package com.lagou.zq.homework.code.util;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public class Toolkit {
    public static void closeResources(Closeable... resources){
        int length = resources.length;
        for (int i = 0 ; i < length ; i++){
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
