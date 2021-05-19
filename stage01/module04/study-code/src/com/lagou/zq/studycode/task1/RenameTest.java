package com.lagou.zq.studycode.task1;

import java.io.File;

public class RenameTest {
    public static void main(String[] args) {
        File dir1 = new File("d:/aaa/bbb/ccc");
        //dir1.mkdirs();
        File dirDest = new File("d:/ddd");
        dir1.renameTo(dirDest);
        System.out.println(dir1.getAbsolutePath());
    }
}
