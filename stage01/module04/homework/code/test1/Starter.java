package com.lagou.zq.homework.code.test1;
/*
4. 编程题

 使用 List 集合实现简易的学生信息管理系统，要求打印字符界面提示用户选择相应的功 能，
 根据用户输入的选择去实现增加、删除、修改、查找以及遍历所有学生信息的功能。

 其中学生的信息有：学号、姓名、年龄。 要求： 尽量将功能拆分为多个.java 文件。
 */

/*1. 编程题

  基于学生信息管理系统增加以下两个功能：

  a.自定义学号异常类和年龄异常类，并在该成员变量不合理时产生异常对象并抛出。

  b.当系统退出时将 List 集合中所有学生信息写入到文件中，当系统启动时读取文件中所有学生信息到 List 集合中。*/
public class Starter {
    public static void main(String[] args) {
        new StudentManagerSys().start();
    }
}
