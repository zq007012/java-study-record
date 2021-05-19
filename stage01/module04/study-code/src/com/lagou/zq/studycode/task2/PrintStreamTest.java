package com.lagou.zq.studycode.task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintStreamTest {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream printStream = new PrintStream("/aaa.txt");
        printStream.println("吃了吗?");
        printStream.close();
    }
}
