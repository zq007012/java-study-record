package com.lagou.zq.homework.code.test3;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
3. 编程题

  使用线程池将一个目录中的所有内容拷贝到另外一个目录中，包含子目录中的内容。
 */
public class Test3 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        //1.获取用户要复制的目录
        File oldDir = getOldDir(scanner);
        //2. 获取用户要将目录复制到哪里
        File newDir = getNewDir(scanner);

        //3. 开始复制目录
        copyDirTo(oldDir, newDir);
    }

    /**
     * 使用多线程复制目录下的内容
     * @param oldDir
     * @param newDir
     */
    private static void copyDirTo(File oldDir, File newDir) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        copyDirTo(oldDir,newDir,threadPool);
        threadPool.shutdown();
    }

    /**
     * 使用线程池技术把源目录里面的内容复制到目标目录中
     * @param oldDir
     * @param newDir
     * @param threadPool
     */
    private static void copyDirTo(File oldDir, File newDir,ExecutorService threadPool) {
        //1. 获取本文件夹下的所有文件
        File[] subFiles = oldDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });

        //2. 用不同的线程池里的线程复制这些文件
        for (File subFile:
             subFiles) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    long thread = Thread.currentThread().getId();
                    System.out.println("线程" + thread + "正在复制文件" + "\""+subFile.getAbsolutePath() + "\"中...");
                    copyFile(subFile,new File(newDir,subFile.getName()));
                    System.out.println("线程" + thread + "复制文件" + "\""+subFile.getAbsolutePath() + "\"成功");
                    System.out.println("====================================================================");
                }
            });

        }

        //3. 获取本文件下的所有子文件
        File[] subDirs = oldDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        //4. 使用递归复制子文件夹下的所有内容
        for (File subDir :
                subDirs) {
            File newSubDir = new File(newDir, subDir.getName());
            newSubDir.mkdirs();
            copyDirTo(subDir,newSubDir,threadPool);
        }
    }

    /**
     * 获取用户要把原目录里的内容复制到的目标文件夹
     * @param scanner
     * @return
     */
    private static File getNewDir(Scanner scanner) {
        File newDir;
        while (true) {
            System.out.println("请输入目标目录");
            String oldDirPath = scanner.next();
            newDir = new File(oldDirPath);
            //
            if (!newDir.exists()){
                if (newDir.mkdirs()){
                    break;
                }else{
                    System.out.println("您输入的目录不存在,且不可被创建, 请重新输入一个目录");
                }
            }

            if (newDir.isFile()){
                System.out.println("您输入的是一个文件路径, 请输入一个目录路径");
                continue;
            }

            if (newDir.isDirectory()){
                break;
            }
        }
        return newDir;
    }

    /**
     * 获取用户要复制的目录
     * @param scanner
     * @return
     */
    private static File getOldDir(Scanner scanner) {
        File oldDir;
        while (true) {
            System.out.println("请输入要复制的目录");
            String oldDirPath = scanner.next();
            oldDir = new File(oldDirPath);
            if (!oldDir.exists()){
                System.out.println("您输入的目录不存在, 请重新输入");
                continue;
            }

            if (oldDir.isFile()){
                System.out.println("您输入的是一个文件路径, 请输入一个目录路径");
                continue;
            }

            if (oldDir.isDirectory()){
                break;
            }
        }
        return oldDir;
    }

    /**
     * 讲一个文件里的内容复制到另一个文件中
     * @param source
     * @param target
     */
    private static void copyFile(File source, File target){
        File parentDir = target.getParentFile();
        if (!parentDir.exists()){
            parentDir.mkdirs();
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            target.createNewFile();
            bis = new BufferedInputStream(new FileInputStream(source));
            bos = new BufferedOutputStream(new FileOutputStream(target));
            byte[] buffer = new byte[4096];
            int len = 0;
            while (-1 != (len = bis.read(buffer))) {
                bos.write(buffer,0 , len);
                bos.flush();
            }
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
    }
}
