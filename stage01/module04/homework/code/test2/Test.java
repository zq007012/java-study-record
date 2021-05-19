package com.lagou.zq.homework.code.test2;

import java.io.*;

/*
2. 编程题

  实现将指定目录中的所有内容删除，包含子目录中的内容都要全部删除。
 */
public class Test {
    public static void main(String[] args) {
        //1. 获取用户要删除的文件夹
        File deleteDir;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                //获取用户要删除的文件夹
                deleteDir = getDeleteDir(br);

            if (null == deleteDir){
                System.out.println("您将要退出删除文件夹的功能");
                return;
            }
            //2. 删除本文件夹及之下的所有内容
            if (deleteAll(deleteDir)) {
                System.out.println("文件夹===" + deleteDir.getPath() + "===删除成功");
                System.out.println("-------------------------------------------------------");
            } else {
                System.out.println("文件夹===" + deleteDir.getPath() + "===删除失败");
                System.out.println("-------------------------------------------------------");
            }
        }
    }

    private static File getDeleteDir(BufferedReader br) {
        File deleteDir;
        System.out.println("请输入要删除的目录的路径(输入\"-1\"退出本功能)");
        String dirPath = null;
        try {
            dirPath = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ("-1".equals(dirPath)){
            return null;
        }
        deleteDir = new File(dirPath);
        if (!deleteDir.exists()) {
            System.out.println(deleteDir.getAbsolutePath() + "不存在");
            return getDeleteDir(br);
        }

        if (deleteDir.isFile()) {
            System.out.println("您输入的是个文件,不是目录");
            return getDeleteDir(br);
        }

        return deleteDir;
    }

    /**
     * 删除本文件夹下的所有内容,以及删除本文件夹
     * @param dir
     * @return 是否删除成功
     */
    private static boolean deleteAll(File dir) {
        return deleteAll(dir, 0);
    }

    /**
     * 删除本文件夹下的所有内容,以及删除本文件夹
     * @param dir
     * @param degree 用来提示正在删除的子文件夹时该文件下的几级子文件夹
     * @return
     */
    private static boolean deleteAll(File dir, int degree) {
        String degreeMessage = getDegreeMessage(degree);
        String dirInfo = degreeMessage + "\"" + dir.getName() + "\"";
        System.out.println("正在删除" + dirInfo + "中...");
        //1. 获取本文件夹下的所有子文件的集合
        File[] subFiles = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        //2 . 删除所有子文件
        for (File subFile :
                subFiles) {
            String subFileInfo = dirInfo + "下的文件" + "\"" + subFile.getName() + "\"";
            System.out.println("正在删除" + subFileInfo + "中...");

            if (subFile.delete()) {
                System.out.println(subFileInfo + "删除成功");
            } else {
                System.out.println(subFileInfo + "删除失败");
            }
        }

        //3. 或取本文件夹下的子文件夹
        File[] subDirs = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });

        //4. 通过递归删除每一个子文件夹
        degree++;
        for (File subDir :
                subDirs) {
            deleteAll(subDir,degree);
        }

        //5. 删除完文件夹下的文件以及子文件夹就轮到删除自己了
        boolean result = dir.delete();
        //文件夹是否删除成功,也可以用来确定子文件夹是否删除成功
        if (result){
            System.out.println(dirInfo + "删除成功");
        }else{
            System.out.println(dirInfo + "删除失败");
        }

        //5. 返回删除本文件夹是否成功
        return result;

    }

    private static String getDegreeMessage(int degree) {
        if (0 == degree) {
            return "目录";
        }
        return degree + "级子目录";
    }
}
