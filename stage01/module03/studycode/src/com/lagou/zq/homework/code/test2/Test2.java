package com.lagou.zq.homework.code.test2;

import java.util.Set;
import java.util.TreeSet;

/*2. 编程题

        编程获取两个指定字符串中的最大相同子串并打印出来。

        如： s1="asdafghjka", s2="aaasdfg" 他们的最大子串为"asd"

        提示： 将短的那个串进行长度依次递减的子串与较长的串比较。


        思路: 1. 获取较短字符串的所有子串
              2. 将这些子串放到一个集合subStrs中,要求集合中的这些子串不能重复, 且要按照字符串的长度由大到小的顺序排序
              3. 遍历subStrs集合, 判断较长字符串是否包含遍历到的元素, 第一个判断结果为true的就是最长的共同字串

        */
public class Test2 {
    public static void main(String[] args) {
        String s1 = "asdafghjka";
        String s2 = "aaasdfg";
        String maxSubString = maxSubString(s1, s2);
        if (null != maxSubString){
            System.out.println("字符串: " + "\"" + s1 + "\"和" + "字符串: " + "\"" + s2 + "\"" +
                    "中最大的相同子串是: " + "\"" + maxSubString + "\"");
        }else{
            System.out.println("字符串: " + "\"" + s1 + "\"和" + "字符串: " + "\"" + s2 + "\"没有相同的子串");
        }
    }

    /**
     * 获取两个字符串中最长的共同子串
     * @param s1
     * @param s2
     * @return 返回值就是最长的共同字串, 如果返回值为null就说明没有共同字串
     */
    private static String maxSubString(String s1, String s2) {
        /*1. 获取较短字符串的所有子串
        2. 将这些子串放到一个集合中,要求集合中的这些子串不能重复, 且要按照字符串的长度由大到小的顺序排序*/
        //1.获取较短的字符串和较长的字符串
        String minStr = s1.length() >= s2.length() ? s2 : s1;
        String maxStr = s1.length() >= s2.length() ? s1 : s2;

        //2.创建一个TreeSet集合,要求这个集合中的元素以长度的以长度由大到小,这样的话需要用到比较器排序
        /*Set<String> subStrs = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                        //以长度由大到小排序, 如果长度相同, 那就用String类的自然排序规则来排序
                int result = o2.length() - o1.length();
                if (result == 0){
                    return o1.compareTo(o2);
                }
                return result;
            }
        });*/
        //简写成lambda表达式
        Set<String> subStrs = new TreeSet<>((String o1, String o2) -> {
            //以长度由大到小排序, 如果长度相同, 那就用String类的自然排序规则来排序
            int result = o2.length() - o1.length();
            if (result == 0){
                return o1.compareTo(o2);
            }
            return result;
        });

        //3. 获取所有子串,并将子串存到set集合中,这样就能剔除掉相同的子串,并使子串以长度由长到短的顺序排序
        for (int i = 0 ; i < minStr.length(); i++){
            for (int j = i + 1 ; j <= minStr.length(); j++){
                //System.out.println(minStr.substring(i,j));
                subStrs.add(minStr.substring(i,j));
            }
        }
        //System.out.println(subStrs);
        //4.遍历subStrs, 较长字符串第一个包含的元素就是最长的共同字串了
        for (String subStr :
                subStrs) {
            if (maxStr.contains(subStr)){
                System.out.println(subStr + "是第一个遍历到的共同子串, 由于subStrs集合中的字符串长度是由长" +
                        "到短地排序的,所以可以确定" + subStr + "是最长的共同子串, 剩下的子串就不需要遍历了");
                return subStr;
            }
            System.out.println("遍历到了子串" + subStr + ",但" + subStr + "不是共同子串");
        }
        return null;
    }
}
