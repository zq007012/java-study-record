package com.lagou.zq.homework.code.test1;

/*1. 编程题
 编程统计字符串"ABCD123!@#$%ab"中大写字母、小写字母、数字、其它字符的个数并打 印出来。
思路:
    分析题目后觉得String类的 maches(String regex)方法适合处理这个问题
 */
public class Test1 {
    public static void main(String[] args) {
        String origin = "ABCD123!@#$%ab";
        //1. 把这个origin拆分成多个长度为1的字符串, 这些字符串组成一个数组
        String[] strs = origin.split("");
        /*2. 对strs数组中的字符串进行统计
                要统计的有四类:
                            大写字母-----对应的regex是[A-Z]
                            小写字母-----对应的regex是[a-z]
                            数字    -----对应的regex是[0-9]
                            其他字符-----对应的regex是[^A-Za-z0-9]
        */
        //声明四个计数器,用来统计各类字符出现的次数
        int upperCase = 0;
        int lowerCase = 0;
        int digital = 0 ;
        int others = 0;
        //声明四个StringBuilder,用来记录这些字符都是哪些
        StringBuilder upperSB = new StringBuilder("分别是: ");
        StringBuilder lowerSB = new StringBuilder("分别是: ");
        StringBuilder digitalSB = new StringBuilder("分别是: ");
        StringBuilder othersSB = new StringBuilder("分别是: ");
        //使用foreach循环对strs数组进行遍历
        for (String s:
             strs) {
            if (s.matches("[A-Z]")){
                upperCase++;
                upperSB.append(s + ",");
                System.out.println("发现大写字母" + s + ", 大写字母出现" + upperCase + "次了");
            }else if(s.matches("[a-z]")){
                lowerCase++;
                lowerSB.append(s + ",");
                System.out.println("发现小写字母" + s + ", 小写字母出现" + lowerCase + "次了");
            }else if(s.matches("[0-9]")){
                digital++;
                digitalSB.append(s + ",");
                System.out.println("发现数字字符" + s + ", 数字字符出现" + digital + "次了");
            }else{
                others++;
                othersSB.append(s + ",");
                System.out.println("发现其他字符" + s + ", 其他字符出现" + others + "次了");

            }
        }
        System.out.println("------------统计结束----------------");
        String upperStr = "大写字符出现了" + upperCase + "次," +
                upperSB.deleteCharAt(upperSB.length() - 1).toString();
        String lowerStr = "小写字符出现了" + lowerCase + "次," +
                lowerSB.deleteCharAt(lowerSB.length() - 1).toString();
        String digitalStr = "数字字符出现了" + digital + "次," +
                digitalSB.deleteCharAt(digitalSB.length() - 1).toString();
        String othersStr = "其他字符出现了" + others + "次," +
                othersSB.deleteCharAt(othersSB.length() - 1).toString();
        System.out.println(upperStr);
        System.out.println(lowerStr);
        System.out.println(digitalStr);
        System.out.println(othersStr);
    }
}
