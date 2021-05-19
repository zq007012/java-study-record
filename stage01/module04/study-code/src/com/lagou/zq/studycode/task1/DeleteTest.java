package com.lagou.zq.studycode.task1;

import java.io.File;

public class DeleteTest {
    public static void main(String[] args) {
        File file = new File("D:\\FileTest");
        System.out.println(file.exists());
        dirDelete(file);

    }

    private static void dirDelete(File dir) {
        File[] files = dir.listFiles();
        for (File file :
                files) {
            if (file.isFile()) {
                file.delete();
                System.out.println(file.getName() + "被删除了");
            }
            if (file.isDirectory()){
                dirDelete(file);
            }
        }
        dir.delete();
        System.out.println(dir.getName() + "被删除了");
    }
}
