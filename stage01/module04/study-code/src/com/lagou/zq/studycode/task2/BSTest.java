package com.lagou.zq.studycode.task2;

import java.io.*;

public class BSTest {
    public static void main(String[] args) {
        //比较自定义了byte数组缓冲区的缓冲流与没自定义byte数组缓冲区的缓冲流复制同一个文件的速度
        //long time1 = copy("e:/延迟退休到65岁将带来哪些变化.mp4",
        //"e:/烈阳天道.mp4");
        long time2 = copyWithoutByteBuffer("e:/延迟退休到65岁将带来哪些变化.mp4",
                "e:/烈阳天道2.mp4");
                //System.out.println(time1);
        System.out.println(time2);
        //结果是带byte数组缓冲区的缓冲流速度更快
    }

    /**
     * 这是一个没有byte数组缓冲区的复制文件的方法(使用了缓冲流)
     * @param s 源文件
     * @param s1 源文件副本
     * @return 返回复制一个文件所需的时间
     */
    private static long copyWithoutByteBuffer(String s, String s1) {
        long time1 = 0;
        long time2 = 0;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(s);
            fos = new FileOutputStream(s1);
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            time1 = System.currentTimeMillis();
            int result;
            while ((result = bis.read()) != -1){
                bos.write(result);
            }
            time2 = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("这是finally语句里的东西");
            if (bos != null){
                try {
                    bos.close();
                    System.out.println("fos = " + fos);
                    System.out.println("bos = " + bos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null){
                try {
                    bis.close();
                    System.out.println("fis = " + fis);
                    System.out.println("bis = " + bis);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


        return time2 - time1;
    }

    /**
     * 这是一个有数组缓冲区的复制文件的方法(使用了缓冲流)
     * @param s
     * @param s1
     * @return 返回复制一个文件所需的时间
     */
    private static long copy(String s, String s1) {
        long time1 = 0;
        long time2 = 0;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(s));
            bos = new BufferedOutputStream(new FileOutputStream(s1));
            byte[] buffer = new byte[8192];
            int len;
            time1 = System.currentTimeMillis();
            while ((len = bis.read(buffer)) != -1){
                bos.write(buffer,0,len);
                //bos.flush();
            }
            time2 = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


        return time2 - time1;

    }
}
