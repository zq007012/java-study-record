package com.zq.utils;

import java.util.Scanner;

/**
 * @ClassName: InputUtils
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/6 12:15
 * @Version: V1.0
 */
public class InputUtils {
    /**
     * 可以获取用户输入的小数, 如果输入的不是数字, 可以提醒用户重新输入
     * @param warning
     * @param scanner
     * @return
     */
    public static double getDouble(String warning, Scanner scanner) {
        String input;
        while (true) {
            input = getString(warning, scanner);
            if (input.matches("\\d+\\.\\d+") || input.matches("\\d+")) {
                break;
            } else {
                System.out.println("您输入的不是一个数字, 请重新输入");
            }
        }
        return Double.parseDouble(input);
    }

    /**
     * 可以获取用户输入的字符串, 如果输入为空, 可以提醒用户重新输入
     * @param warning
     * @param scanner
     * @return
     */
    public static String getString(String warning, Scanner scanner) {
        System.out.println(warning);
        String input = null;

        while (true) {
            input = scanner.nextLine().trim();
            if (!input.equals("")) {
                break;
            } else {
                System.out.println("输入不能为空, 请重新输入");
                System.out.println(warning);
            }
        }
        return input;
    }
}
