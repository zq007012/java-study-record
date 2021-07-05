package com.zq;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2021/7/5 10:13
 */
public class Starter {
    public static void main(String[] args) {
        File nowDir = new File("").getAbsoluteFile();
        System.out.println("您现在所在的目录名为'" + nowDir.getName() + "'");
        System.out.println("开始将本目录下的所有md文件中的图片的本地路径替换为网路路径");
        String urlPrefix = "https://gitee.com/zq007012/java-study-record/raw/master/study-note/"
                + URLEncoder.encode(nowDir.getName(), StandardCharsets.UTF_8) + "/";
        System.out.println(urlPrefix);
        File[] mdFiles = nowDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    return false;
                }

                String fileName = pathname.getName();
                if (!fileName.contains(".")) {
                    return false;
                }
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                return ".md".equals(suffix);
            }
        });
        if (mdFiles != null && mdFiles.length > 0) {
            System.out.println("一共有" + mdFiles.length + "个md文件需要转换");
        } else {
            System.out.println("没有md文件需要转换");
            return;
        }

        File netImageMdDir = new File(nowDir, "mdWithNetImageUrl");
        if (!netImageMdDir.exists() || !netImageMdDir.isDirectory()) {
            System.out.println("mdWithNetImageUrl文件夹不存在");
            boolean result = netImageMdDir.mkdir();
            System.out.println("mdWithNetImageUrl文件夹'" + (result ? "成功" : "失败"));
        } else {
            System.out.println("mdWithNetImageUrl文件夹已存在, 直接使用");
        }

        for (File file : mdFiles) {
            String fileName = file.getName();
            System.out.println("复制文件'" + fileName + "'到目录'mdWithNetImageUrl'下, 并把文件中图片的路径替换为网络路径");

            PrintWriter pw = null;
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
                pw = new PrintWriter(new File(netImageMdDir, fileName));

                String line = null;
                while ((line = br.readLine()) != null) {
                    if (line.contains("](")) {
                        System.out.println("----------找到了一个----------");
                        System.out.println("替换前: " + line);
                        line = line.replace("](",
                                "](" + urlPrefix);
                        System.out.println("替换后: " + line);

                    } else if (line.contains("<img src=\"")) {
                        System.out.println("替换前: " + line);
                        line = line.replace("<img src=\"", "<img src=\"" + urlPrefix);
                        System.out.println("替换后: " + line);
                    }
                    pw.println(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                if (pw != null) {
                    pw.close();
                }
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("文件" + fileName + "转换结束");
        }
        System.out.println("所有文件转换结束");
    }
}
