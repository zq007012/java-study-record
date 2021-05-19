package com.lagou.zq.studycode.task2;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTest {
    public static void main(String[] args) {
        FileWriter fw1 = null;
        try {
            fw1 = new FileWriter("d:/aaa.txt",true);
            //fw1.append("刘涛");
            //fw1.append("舒畅");
            fw1.write("刘亦菲");
            fw1.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("我是finally语句里的内容");
            if (null != fw1) {
                try {
                    fw1.close();
                    System.out.println("fw1 = " + fw1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
