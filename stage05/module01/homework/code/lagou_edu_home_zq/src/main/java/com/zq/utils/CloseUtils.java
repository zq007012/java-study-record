package com.zq.utils;

import java.io.Flushable;
import java.io.IOException;

/**
 * 这是一个用来关闭资源的工具集
 * @author      zq007
 * @date        2021/1/18 20:08
 * @version     V1.0
 */
public class CloseUtils {
    /**
     * 用来批量关闭可关闭的资源, 当刷新或关闭资源失败时, 可以提醒是第几个资源刷新或关闭失败了
     * @param resources
     * @throws Exception
     */
    public static void closeResources(AutoCloseable... resources) throws Exception {
        int length = resources.length;
        for (int i = 0; i < length; i++) {
            //先刷新可刷新资源, 然后再关闭这个资源
            AutoCloseable resource = resources[i];
            if (null != resource) {
                if (resource instanceof Flushable) {
                    try {
                        ((Flushable) resource).flush();
                    } catch (IOException e) {
                        throw new IOException(
                                e.getMessage() + System.lineSeparator() +
                                        "第" + (i + 1) + "个资源刷新失败了", e);
                    }
                }

                try {
                    resource.close();
                } catch (Exception e) {
                    throw new Exception(
                            e.getMessage() + System.lineSeparator() +
                                    "第" + (i + 1) + "个资源关闭失败了", e);
                }
            }
        }
    }

    /**
     * 静默关闭资源, 关闭资源时产生的任何异常都不会被抛出
     * @param resources
     */
    public static void closeResourcesQuietly(AutoCloseable... resources){
        try {
            closeResources(resources);
        } catch (Exception e) {
            //quiety
        }
    }
}
