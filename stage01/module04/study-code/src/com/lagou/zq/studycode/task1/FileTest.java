package com.lagou.zq.studycode.task1;

import java.io.*;
import java.nio.file.Files;

public class FileTest {
    public static void main(String[] args) throws IOException {
        File dir = new File("D:\\FileTest\\第01阶段\\模块1 Java编程基" +
                "础\\01任务一 初识计算机和Java语言\\01_课件\\JavaSE_Chaper01_Mark");
        try {
            long length = dirLength(dir);
            System.out.println(dir.getName() + "的长度是:" + length );
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Over");
    }

    private static long dirLength(File dir) throws FileNotFoundException,RuntimeException {
        return dirLength(dir,0);
    }

    /**
     * 这是一个获取文件夹大小的方法, 其实就是文件夹内所有文件,包括子文件夹内的所有文件的大小之和
     * @param dir
     * @param length  length是用来记录长度的之和,调用该方法时length的值只能是0, 否则获取到的文件夹的大小是错的
     * @return 返回的是文件夹的大小
     * @throws FileNotFoundException
     * @throws RuntimeException
     */
    private static long dirLength(File dir, long length) throws FileNotFoundException,RuntimeException {
        if (dir.exists() && dir.isDirectory()){
            File[] files = dir.listFiles();
            /**
             * 思路就是获取本文件夹下所有子文件和子文件夹的长度之和
             */
            for (File file :
                    files) {
                if (file.isFile()){
                    length += file.length();
                }

                if (file.isDirectory()){
                    length = dirLength(file, length);
                    //length += dirLength(file, 0);

                }
            }
            return length;
        }else if (dir.exists() && dir.isFile()){
            throw new RuntimeException(dir.getAbsolutePath() + "是个文件, 不是个文件夹");
        }else{
            throw new FileNotFoundException("找不到路径为" + dir.getAbsolutePath() + "的文件夹");
        }

    }

    private static void showAll(File dir,int grade) {
        if (dir.exists()){
            String gradeString = gradeString(grade);
            grade++;
            System.out.println(gradeString + dir.getName());
            File[] dirs = dir.listFiles((File pathname) -> {
                return pathname.isDirectory();
            });
            for (File theDir :
                    dirs) {
                showAll(theDir, grade);
            }

            File[] files = dir.listFiles(pathname -> pathname.isFile());
            for (File file :
                    files) {
                System.out.println(gradeString(grade) + file.getName());
            }
        }else {
            System.out.println("文件夹" + dir.getName() + "不存在");
        }
    }

    private static String gradeString(int grade) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0 ; i < grade ; i++){
            sb.append("--|");
        }
        return sb.toString();

    }
}
